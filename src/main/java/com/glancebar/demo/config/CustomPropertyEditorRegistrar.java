package com.glancebar.demo.config;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author YISHEN CAI
 */
@Component("customPropertyEditorRegistrar")
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(CustomDateEditor.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE), false));
    }
}
