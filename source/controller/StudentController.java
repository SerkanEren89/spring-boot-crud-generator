package com.opticatr.example.crud.controller;

import com.opticatr.example.crud.dto.StudentDto;
import com.opticatr.example.crud.model.Student;
import com.opticatr.example.crud.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

/**
 * Student controller
 *
 * @author eren
 */
@RestController
@RequestMapping("/students")
@Log4j2
@Api(value = "Student", tags = {"Student api"})
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{uuid}")
    @ApiOperation("Get a student by uuid")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 200, message = "Student found")})
    public StudentDto findStudentByUuid(@PathVariable UUID uuid) {
        log.info("get student with uuid {}.", uuid);
        return studentService.findStudentByUuid(uuid);
    }

    @PostMapping
    @ApiOperation(value = "Create new student", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student created successfully."),
            @ApiResponse(code = 400, message = "Invalid request."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    public ResponseEntity<StudentDto> saveStudent(@Validated @RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.saveStudent(studentDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{uuid}")
                .buildAndExpand(savedStudent.getUuid())
                .toUri();
        return ResponseEntity.created(location).body(savedStudent);
    }

    @PutMapping("/{uuid}")
    @ApiOperation("Update student with uuid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Student is updated successfully"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 404, message = "Student not found"),
            @ApiResponse(code = 500, message = "Internal error")})
    public ResponseEntity updateStudentByUuid(@PathVariable("uuid") UUID uuid,
                                              @Validated @RequestBody StudentDto studentDto) {
        log.info("Updating student with uuid: {}", uuid);
        if (!uuid.equals(studentDto.getUuid())) {
            throw new RuntimeException("bad request");
        }
        studentService.updateStudentByUuid(uuid, studentDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation("Delete student with uuid")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Student deleted successfully"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("uuid") UUID uuid) {
        log.info("Deleting Student with uuid: {}", uuid);
        studentService.deleteStudent(uuid);
    }
}
