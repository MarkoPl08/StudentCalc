package com.grade.studentcalc.repository;

import com.grade.studentcalc.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByUsername(String username);
}
