package com.kgpalle.moneymind.service;

import com.kgpalle.moneymind.dto.StudentDTO;
import com.kgpalle.moneymind.entity.Student;
import com.kgpalle.moneymind.mapper.StudentMapper;
import com.kgpalle.moneymind.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private StudentRepository studentRepository;

    StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> getAll() {
        List<Student> students = this.studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        return studentDTOS;
    }

    public StudentDTO create(Student student) {
        Student student1 = this.studentRepository.save(student);
        return studentMapper.toDTO(student);
    }
}
