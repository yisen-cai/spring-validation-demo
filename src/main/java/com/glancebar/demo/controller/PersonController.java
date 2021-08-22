package com.glancebar.demo.controller;

import com.glancebar.demo.vo.Person;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author YISHEN CAI
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PropertyEditorRegistrar customPropertyEditorRegistrar;

    public PersonController(PropertyEditorRegistrar customPropertyEditorRegistrar) {
        this.customPropertyEditorRegistrar = customPropertyEditorRegistrar;
    }


    @PostMapping
    public String addPerson(@Valid @RequestBody Person person) {
        return person.getName();
    }

    @GetMapping("/parse")
    public Long parseDate(@RequestParam Date date) {
        return date.getTime();
    }
}
