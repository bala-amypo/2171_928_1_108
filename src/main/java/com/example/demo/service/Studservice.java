package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Studentity;

public interface StudentService {

    Studentity saveData(Studentity st);

    Studentity insertStudentity(Studentity newStudent);

    List<Studentity> getAllStudentity();

    Optional<Studentity> getOneStudent(Long id);

    void deleteStudent(Long id);

    Student insertStudentity(Student st);
    
}