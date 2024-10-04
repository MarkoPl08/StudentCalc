package com.grade.studentcalc.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.grade.studentcalc.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Repository
public class GradeRepository {

    @Autowired
    private Firestore firestore;

    public String addStudent(Student student) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("students").document(student.getStudentId());
        ApiFuture<WriteResult> result = docRef.set(student);
        return result.get().getUpdateTime().toString();
    }

    public Student getStudentById(String studentId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("students").document(studentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(Student.class);
        } else {
            return null;
        }
    }

    public String updateStudent(String studentId, Map<String, Object> updates) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("students").document(studentId);
        ApiFuture<WriteResult> future = docRef.update(updates);
        return future.get().getUpdateTime().toString();
    }

    public String deleteStudent(String studentId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("students").document(studentId);
        ApiFuture<WriteResult> writeResult = docRef.delete();
        return writeResult.get().getUpdateTime().toString();
    }

    public List<Student> getAllStudents() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("students").get();
        List<Student> students = future.get().toObjects(Student.class);
        return students;
    }
}
