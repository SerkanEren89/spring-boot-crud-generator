package com.opticatr.example.crud.service;

import com.opticatr.example.crud.dto.StudentDto;

import java.util.UUID;

/**
 * Student service
 *
 * @author eren
 */
public interface StudentService {

    /**
     * Find student by uuid
     *
     * @param uuid uuid
     * @return student dto
     */
    StudentDto findStudentByUuid(UUID uuid);

    /**
     * Save student dto
     *
     * @param studentDto student dto
     * @return saved student dto
     */
    StudentDto saveStudent(StudentDto studentDto);

    /**
     * Update student by uuid
     *
     * @param uuid       uuid
     * @param studentDto updated student
     */
    void updateStudentByUuid(UUID uuid, StudentDto studentDto);

    /**
     * Delete student by uuid
     *
     * @param uuid uuid
     */
    void deleteStudent(UUID uuid);
}
