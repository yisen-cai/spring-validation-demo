package com.glancebar.demo.controller;

import com.glancebar.demo.vo.Contact;
import com.glancebar.demo.vo.DateInside;
import com.glancebar.demo.vo.Person;
import com.glancebar.demo.vo.RequestParamObj;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author YISHEN CAI
 */
@RestController
@RequestMapping("/person")
public class PersonController {


    @PostMapping
    public String addPerson(@Valid @RequestBody Person person) {
        return person.getName();
    }

    /**
     * Test type converter
     *
     * @param date
     * @return
     */
    @GetMapping("/parse")
    public Long parseDate(@RequestParam LocalDate date) {
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Body is ok too.
     *
     * @param dateInside
     * @return
     */
    @PostMapping("/parse-body")
    public Long parseObject(@Valid @RequestBody DateInside dateInside) {
        return dateInside.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @PostMapping("/parse-datetime")
    public LocalDateTime parseDateTime(@RequestParam LocalDateTime dateTime) {
        return dateTime;
    }

    @PostMapping("/validate-number")
    public boolean validatePhoneNumber(@Valid @RequestBody Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            result.getFieldErrors().forEach(fieldError ->
                    System.out.println(fieldError.getDefaultMessage())
            );
        }
        return true;
    }


    @GetMapping("/request-param")
    public int parseRequestParam(@Valid RequestParamObj paramObj) {
        return paramObj.getSize();
    }
}
