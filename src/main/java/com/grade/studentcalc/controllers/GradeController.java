package com.grade.studentcalc.controllers;

import com.grade.studentcalc.entity.Student;
import com.grade.studentcalc.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        return gradeService.addStudent(student);
    }

    @PostMapping("/register")
    public String registerStudent(@RequestBody Student student) {
        return gradeService.addStudent(student);
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable String studentId) {
        return gradeService.getStudentById(studentId);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return gradeService.getAllStudents();
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable String studentId, @RequestBody Student student) {
        gradeService.updateStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable String studentId) {
        gradeService.deleteStudent(studentId);
    }

    @GetMapping("/{studentId}/final-grade")
    public double getFinalGrade(@PathVariable String studentId) {
        return gradeService.calculateFinalGrade(studentId);
    }

    @GetMapping("/{studentId}/gpa")
    public double getGPA(@PathVariable String studentId) {
        return gradeService.calculateGPA(studentId);
    }

    @GetMapping("/{studentId}/progress")
    public String checkProgress(@PathVariable String studentId, @RequestParam double targetGPA) {
        return gradeService.checkProgress(studentId, targetGPA);
    }
}
