package com.task2.student.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RestController
//public class StudentController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private Map<String, String> studentNames = new HashMap<>();
//
//    public StudentController() {
//        // Example data
//        studentNames.put("1", "Hass");
//        studentNames.put("2", "Alex");
//        // Add more as needed
//    }
//
//    @GetMapping("/student/{id}")
//    public String getStudent(@PathVariable String id) {
//        String studentName = studentNames.getOrDefault(id, "Unknown Student");
//        String subject = restTemplate.getForObject("http://localhost:8081/subject/" + id, String.class);
//        return "Student: " + studentName + ", " + subject;
//    }
//}


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllStudentNames() {
        String sql = "SELECT name FROM studentInfo";
        List<String> studentNames = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
        return ResponseEntity.ok(studentNames);
    }


    @GetMapping("/{id}")
    public String getStudentInfo(@PathVariable Integer id) {
        // Updated SQL query to select name, age, and town
        String sql = "SELECT name, age, town FROM studentInfo WHERE id = ?";

        // Query for a map containing the results. Each key in the map will correspond to a column name.
        Map<String, Object> studentInfo = jdbcTemplate.queryForMap(sql, new Object[]{id});

        // Retrieve each attribute from the map
        String studentName = (String) studentInfo.get("name");
        Integer age = (Integer) studentInfo.get("age");
        String town = (String) studentInfo.get("town");

        // Call to another service to get the subject
        String subject = restTemplate.getForObject("http://localhost:8081/subject/" + id, String.class);

        // Return a string that includes the student's name, age, town, and subject
        return "Student: " + studentName + ", Age: " + age + ", Town: " + town + ", Subject: " + subject;
    }



}



