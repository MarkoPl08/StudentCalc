package com.grade.studentcalc.entity;

import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private List<Grade> grades;

    public Student() {
    }

    public Student(String studentId, String name, List<Grade> grades) {
        this.studentId = studentId;
        this.name = name;
        this.grades = grades;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}