package com.task2.subject.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@RestController
//public class SubjectController {
//
//    @GetMapping("/subject")
//    public String getSubject(){
//        return "\nSubject : Science";
//    }
//}

@RestController
public class SubjectController {

    private Map<String, String> studentSubjects = new HashMap<>();

    public SubjectController() {
        // Example data
        studentSubjects.put("1", "Math");
        studentSubjects.put("2", "Science");
        // Add more as needed
    }

    @GetMapping("/subject/{studentId}")
    public String getSubject(@PathVariable String studentId){
        return "\nSubject : " + studentSubjects.getOrDefault(studentId, "Unknown");
    }
}
