package com.task2.subject.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    @GetMapping("/subject")
    public String getSubject(){
        return "\nSubject : Science";
    }
}
