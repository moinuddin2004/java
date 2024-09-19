package com.example.springSec;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class studentController {
    @Autowired
    UserRepo repo;
    List<Student> studentList = new ArrayList<>(
            List.of(
                    new Student(1, "Alice", 85),
                    new Student(2, "Bob", 90),
                    new Student(3, "Charlie", 78)
            )
    );

    @GetMapping("/students")
    public List<Student> getStudent() {
        return studentList;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        studentList.add(student);
        return student;
    }

    @GetMapping("/getCSRF")
    public CsrfToken getCSRFToken(HttpServletRequest req) {
        return (CsrfToken) req.getAttribute("_csrf");
    }


}
