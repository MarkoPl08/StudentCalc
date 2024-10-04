package com.grade.studentcalc.controllers;

import com.grade.studentcalc.entity.Student;
import com.grade.studentcalc.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/students")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping
    public String addStudent(@RequestBody Student student) {
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
    public String updateStudent(@PathVariable String studentId, @RequestBody Map<String, Object> updates) {
        return gradeService.updateStudent(studentId, updates);
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable String studentId) {
        return gradeService.deleteStudent(studentId);
    }

    @GetMapping("/{studentId}/final-grade")
    public double getFinalGrade(@PathVariable String studentId) throws ExecutionException, InterruptedException {
        return gradeService.calculateFinalGrade(studentId);
    }

    @GetMapping("/{studentId}/gpa")
    public double getGPA(@PathVariable String studentId) throws ExecutionException, InterruptedException {
        return gradeService.calculateGPA(studentId);
    }

    @GetMapping("/{studentId}/progress")
    public String checkProgress(@PathVariable String studentId, @RequestParam double targetGPA) throws ExecutionException, InterruptedException {
        return gradeService.checkProgress(studentId, targetGPA);
    }
}
