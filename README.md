# spring-validation-demo
Spring validation usage, custom validation, error handling and type converters with Jackson.


#### 基本说明

就是懒, 一些接口参数类型转换等很棘手的问题不想在每个地方写判断代码了, 而且感觉把这种基本校验代码写到业务逻辑中也不整洁, 这个地方参考了不少博客, 总结了一些常见的问题, 算是比较优雅的处理方式. 



#### 非Controller层全局异常处理

Spring开发中常见的异常都是Controller层的， 这种异常结合Spring提供的ControllerAdvice和ExceptionHandler很好解决, 但是像是在切面, 过滤器等前置处理器出现的异常时候, 会调用Spring封装的BasicErrorController, 这里面做了一些基础的操作, 返回的是一个

~~~json
// 后续就可以自定义格式
{
    "timestamp": 1630462932563,
    "status": 404,
    "error": "Not Found",
    "path": "/"
}
~~~

但有时候公司内部规范可能约束一个固定的返回模式, 这个时候就不大合适使用Spring默认的了, Spring提供覆写这个接口的选项, 详情可见`com.glancebar.demo.controller.GlobalErrorController`和`application.yml`配置.

这个异常一般使用不到, 在一些场景中如配置了接口参数类型转换, Jackson非法参数(直接就是非法类型的)这种, 还有访问的路径不存在, 过滤器认证, 切面等复杂操作异常等非Controller层的异常.



这边除了配置这个异常, 还处理到了这个异常消息的问题, 就是只显示自己抛出的(定义的Message), 不会抛出那种一大长串的内容, 这里通过使用Spring提供的`NestedRuntimeException`来获取rootCause, 之后取得自己定义的消息, 使得整体的流程更加可控, 返回消息更加整洁.



#### Controller层接口参数注入

[A Custom Data Binder in Spring MVC](https://www.baeldung.com/spring-mvc-custom-data-binder)

自定义认证授权逻辑时候, 希望在Controller自动注入全局认证信息这种需求, 或者前置处理完注入其他内容

~~~java
/**
 * Used to inject authentication principal.
 *
 * @author YISHEN CAI
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Authentication {
    Class<?> clazz() default String.class;
}

/**
 * Custom controller method Authentication argument resolver.
 *
 * @author YISHEN CAI
 */
public class AuthenticationResolver implements HandlerMethodArgumentResolver {


    /**
     * Support Authentication annotation.
     *
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Authentication.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }
}
~~~



##### 使用

~~~java

@GetMapping("/authentication")
public String parseAuthentication(@Authentication String authentication)
~~~



#### 请求参数类型转换

[Custom Type Converter in Spring MVC](https://www.javadevjournal.com/spring-mvc/custom-type-convertor-in-spring-mvc/)

[Validation, Data Binding, and Type Conversion](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/validation.html)

配置接口类型转LocalDate这样的, 如`http://localhost:8080/person/parse?date=2021-08-40`

~~~java
@GetMapping("/parse")
public Long parseDate(@RequestParam LocalDate date) {
    return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
}
~~~



#### Jackson序列化反序列化类型转换

配置LocalDate, 复杂对象的类型转换, Long类型在前端精度丢失问题的解决(序列化为字符串), 一些LocalDate类型序列化为字符串这种

~~~java
@Bean
public Jackson2ObjectMapperBuilder objectMapperBuilder(LocalDateTimeConverter localDateTimeConverter,
                                                       LocalDateConverter localDateConverter) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new KotlinModule());
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(localDateTimeConverter));
    builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(localDateConverter));
    return builder;
}
~~~



#### MessageSource使用

配置全局的消息模版, 避免使用编码的方式来定义消息, 这种MessageSource还支持i18n多国语言

[Custom Validation MessageSource in Spring Boot](https://www.baeldung.com/spring-custom-validation-message-source)

`src/main/resources/validation-messages_zh_CN.properties`

~~~properties
RequestParamObj.invalid.msg=page需要小于size
RequestParamObj.default.msg=非法参数, page must bigger than size
~~~

[MessageSource使用](https://www.logicbig.com/tutorials/spring-framework/spring-core/message-sources.html)

~~~java
public interface MessageSource {
    @Nullable
    String getMessage(
            String code,
            @Nullable Object[] args,
            @Nullable String defaultMessage,
            Locale locale); 
    String getMessage(String code, @Nullable Object[] args, Locale locale)
            throws NoSuchMessageException; 
    String getMessage(MessageSourceResolvable resolvable, Locale locale)
            throws NoSuchMessageException; 
}
~~~

