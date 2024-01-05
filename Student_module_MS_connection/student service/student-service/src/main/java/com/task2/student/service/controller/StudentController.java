package com.task2.student.service.controller;

import com.task2.student.service.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        return "Student: " + studentName + ", Age: " + age + ", Town: " + town + ", Subject: " + subject;
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        String sql = "INSERT INTO studentInfo (name, age, town) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getTown());
        if (result > 0) {
            return "Student added successfully.";
        } else {
            return "Error adding student.";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        String sql = "DELETE FROM studentInfo WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);

        if (result > 0) {
            return "Student deleted successfully.";
        } else {
            return "Error deleting student.";
        }
    }

    @PutMapping("/update/{id}")
    public String updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        String sql = "UPDATE studentInfo SET name = ?, age = ?, town = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getTown(), id);

        if (result > 0) {
            return "Student updated successfully.";
        } else {
            return "Error updating student.";
        }
    }



}



