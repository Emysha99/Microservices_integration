package com.task2.subject.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@RestController
//public class SubjectController {
//
//    private Map<String, String> studentSubjects = new HashMap<>();
//
//    public SubjectController() {
//        // Example data
//        studentSubjects.put("1", "Math");
//        studentSubjects.put("2", "Science");
//        // Add more as needed
//    }
//
//    @GetMapping("/subject/{studentId}")
//    public String getSubject(@PathVariable String studentId){
//        return "\nSubject : " + studentSubjects.getOrDefault(studentId, "Unknown");
//    }
//}

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllSubjectNames() {
        String sql = "SELECT subject_name FROM subjectInfo";
        List<String> subjectNames = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("subject_name"));
        return ResponseEntity.ok(subjectNames);
    }

    //get subject by subject id
//    @GetMapping("/{id}")
//    public String getSubjectName(@PathVariable Integer id) {
//        String sql = "SELECT subject_name FROM subject WHERE id = ?";
//        try {
//            return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
//        } catch (EmptyResultDataAccessException e) {
//            return "Subject not found";
//        }
//    }

    //get subject by student id
    @GetMapping("/{id}")
    public String getSubjectName(@PathVariable Integer id) {
       String sql = "SELECT subject_name FROM subjectInfo WHERE student_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
       } catch (EmptyResultDataAccessException e) {
            return "Subject not found";
        }
  }
}