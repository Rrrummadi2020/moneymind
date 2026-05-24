package com.kgpalle.moneymind.controller;

import com.kgpalle.moneymind.entity.Student;
import com.kgpalle.moneymind.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/students")
@RestController
public class StudentsController {

    private StudentRepository studentRepository;

    StudentsController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @GetMapping("/")
    private List<Student> fetchAll() {
        return this.studentRepository.findAll();
    }

    @PostMapping("/")
    private Student createStudent(@RequestBody Student student) {
        Student createdStudent = this.studentRepository.save(student);
        return createdStudent;

    }
}
