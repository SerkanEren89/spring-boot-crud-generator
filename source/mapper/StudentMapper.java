package com.opticatr.example.crud.mapper;

import com.opticatr.example.crud.dto.StudentDto;
import com.opticatr.example.crud.model.Student;
import org.mapstruct.Mapper;

/**
 * Student mapper
 *
 * @author eren
 */
@Mapper
public interface StudentMapper {

    StudentDto toStudentDto(Student student);

    Student toStudent(StudentDto studentDto);
}
