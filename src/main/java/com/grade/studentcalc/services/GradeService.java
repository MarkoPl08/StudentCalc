package com.grade.studentcalc.services;

import com.grade.studentcalc.entity.Grade;
import com.grade.studentcalc.entity.Student;
import com.grade.studentcalc.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addStudent(Student student) {
        if (student.getGrades() != null) {
            for (Grade grade : student.getGrades()) {
                grade.setStudent(student);
            }
        }

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        studentRepository.save(student);
        return "Student added";
    }


    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public double calculateFinalGrade(String studentId) {
        Student student = getStudentById(studentId);
        if (student == null || student.getGrades().isEmpty()) {
            return 0.0;
        }
        double total = student.getGrades().stream().mapToDouble(grade -> grade.getGrade()).sum();
        return total / student.getGrades().size();
    }

    @Transactional
    public void recalculateAllGpas() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            double newGpa = calculateGPA(student.getStudentId());
            student.setGpa(newGpa);
            studentRepository.save(student);
        }
    }

    public double calculateGPA(String studentId) {
        Student student = getStudentById(studentId);
        if (student == null || student.getGrades().isEmpty()) {
            return 0.0;
        }

        double totalGPA = student.getGrades().stream()
                .mapToDouble(grade -> convertGradeToGPA(grade.getGrade()))
                .sum();

        return totalGPA / student.getGrades().size();
    }

    private double convertGradeToGPA(double grade) {
        if (grade >= 90) return 4.0;
        if (grade >= 80) return 3.0;
        if (grade >= 70) return 2.0;
        if (grade >= 60) return 1.0;
        return 0.0;
    }

    public String checkProgress(String studentId, double targetGPA) {
        double currentGPA = calculateGPA(studentId);
        if (currentGPA >= targetGPA) {
            return "You are on track!";
        } else {
            return "You need to improve your GPA to meet your academic goals.";
        }
    }
}
