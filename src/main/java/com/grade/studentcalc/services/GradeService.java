package com.grade.studentcalc.services;

import com.grade.studentcalc.entity.Grade;
import com.grade.studentcalc.entity.Student;
import com.grade.studentcalc.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public String addStudent(Student student) {
        try {
            return gradeRepository.addStudent(student);
        } catch (ExecutionException | InterruptedException e) {
            return "Error adding student";
        }
    }

    public Student getStudentById(String studentId) {
        try {
            return gradeRepository.getStudentById(studentId);
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    public String updateStudent(String studentId, Map<String, Object> updates) {
        try {
            return gradeRepository.updateStudent(studentId, updates);
        } catch (ExecutionException | InterruptedException e) {
            return "Error updating student";
        }
    }

    public String deleteStudent(String studentId) {
        try {
            return gradeRepository.deleteStudent(studentId);
        } catch (ExecutionException | InterruptedException e) {
            return "Error deleting student";
        }
    }

    public List<Student> getAllStudents() {
        try {
            return gradeRepository.getAllStudents();
        } catch (ExecutionException | InterruptedException e) {
            return null;
        }
    }

    public double calculateFinalGrade(String studentId) throws ExecutionException, InterruptedException {
        Student student = gradeRepository.getStudentById(studentId);
        if (student == null || student.getGrades().isEmpty()) {
            return 0.0;
        }
        double total = student.getGrades().stream().mapToDouble(Grade::getGrade).sum();
        return total / student.getGrades().size();
    }

    public double calculateGPA(String studentId) throws ExecutionException, InterruptedException {
        Student student = gradeRepository.getStudentById(studentId);
        if (student == null || student.getGrades().isEmpty()) {
            return 0.0;
        }
        double totalGPA = 0.0;
        for (Grade grade : student.getGrades()) {
            totalGPA += convertGradeToGPA(grade.getGrade());
        }
        return totalGPA / student.getGrades().size();
    }

    private double convertGradeToGPA(double grade) {
        if (grade >= 90) return 4.0;
        if (grade >= 80) return 3.0;
        if (grade >= 70) return 2.0;
        if (grade >= 60) return 1.0;
        return 0.0;
    }

    public String checkProgress(String studentId, double targetGPA) throws ExecutionException, InterruptedException {
        double currentGPA = calculateGPA(studentId);
        if (currentGPA >= targetGPA) {
            return "You are on track!";
        } else {
            return "You need to improve your GPA to meet your academic goals.";
        }
    }

}
