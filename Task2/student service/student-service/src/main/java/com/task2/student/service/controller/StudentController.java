package com.task2.student.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/student")
    public String getStudent() {

        //need to return subject data along with name
        // need to hit this end point http://localhost:8081/subject
        //make a call using RestTemplate
        String subject = restTemplate.getForObject("http://localhost:8081/subject",String.class);
        return "Student : Hass" + subject;
    }
}
