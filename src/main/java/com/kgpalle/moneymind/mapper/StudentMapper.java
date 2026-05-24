package com.kgpalle.moneymind.mapper;

import com.kgpalle.moneymind.dto.StudentDTO;
import com.kgpalle.moneymind.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);

    Student toEntity(StudentDTO studentDTO);

    List<StudentDTO> toDTOs(List<Student> students);
}
