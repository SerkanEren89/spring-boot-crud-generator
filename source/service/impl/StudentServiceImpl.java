package com.opticatr.example.crud.service.impl;

import com.opticatr.example.crud.dto.StudentDto;
import com.opticatr.example.crud.mapper.StudentMapper;
import com.opticatr.example.crud.model.Student;
import com.opticatr.example.crud.repository.StudentRepository;
import com.opticatr.example.crud.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Student service impl
 *
 * @author eren
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto findStudentByUuid(UUID uuid) {
        return studentMapper.toStudentDto(studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Not found")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = studentMapper.toStudent(studentDto);
        student.setUuid(UUID.randomUUID());
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentDto(savedStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStudentByUuid(UUID uuid, StudentDto studentDto) {
        Student studentToSave = studentMapper.toStudent(studentDto);
        Student existingStudent = studentRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("not found"));
        studentToSave.setId(existingStudent.getId());
        studentRepository.save(studentToSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteStudent(UUID uuid) {
        Student student = studentRepository
                .findByUuid(uuid).orElseThrow(() -> new RuntimeException("Not found"));
        studentRepository.delete(student);
    }
}
