package com.task2.student.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

//@RestController
//public class StudentController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//    @GetMapping("/student")
//    public String getStudent() {
//
//        //need to return subject data along with name
//        // need to hit this end point http://localhost:8081/subject
//        //make a call using RestTemplate
//        String subject = restTemplate.getForObject("http://localhost:8081/subject",String.class);
//        return "Student : Hass" + subject;
//    }
//}

@RestController
public class StudentController {

    @Autowired
    private RestTemplate restTemplate;

    private Map<String, String> studentNames = new HashMap<>();

    public StudentController() {
        // Example data
        studentNames.put("1", "Hass");
        studentNames.put("2", "Alex");
        // Add more as needed
    }

    @GetMapping("/student/{id}")
    public String getStudent(@PathVariable String id) {
        String studentName = studentNames.getOrDefault(id, "Unknown Student");
        String subject = restTemplate.getForObject("http://localhost:8081/subject/" + id, String.class);
        return "Student: " + studentName + ", " + subject;
    }
}
