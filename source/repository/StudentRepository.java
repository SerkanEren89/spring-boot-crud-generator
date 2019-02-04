package com.opticatr.example.crud.repository;

import com.opticatr.example.crud.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Student repository
 *
 * @author eren
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByUuid(UUID uuid);
}
