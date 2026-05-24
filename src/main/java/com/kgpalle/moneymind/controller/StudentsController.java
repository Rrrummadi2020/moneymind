package com.kgpalle.moneymind.controller;

import com.kgpalle.moneymind.dto.StudentDTO;
import com.kgpalle.moneymind.entity.Student;
import com.kgpalle.moneymind.mapper.StudentMapper;
import com.kgpalle.moneymind.repository.StudentRepository;
import com.kgpalle.moneymind.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/students")
@RestController
public class StudentsController {

    private StudentRepository studentRepository;

    private StudentMapper studentMapper;

    private StudentService studentService;

    StudentsController(StudentRepository studentRepository, StudentMapper studentMapper, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.studentService = studentService;
    }
    @GetMapping("/")
    private List<StudentDTO> fetchAll() {
        return studentService.getAll();
    }

    @PostMapping("/")
    private StudentDTO createStudent(@RequestBody Student student) {
        return this.studentService.create(student);
    }
}
