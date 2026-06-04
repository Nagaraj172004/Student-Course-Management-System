package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> studentList;

    public StudentService() {
        this.studentList = new ArrayList<>();
    }

    public void addStudent(String firstName, String lastName, String email, String batch) {
        int newId = IdGenerator.getNextStudentId();
        Student newStudent = new Student(newId, firstName, lastName, email, batch);
        studentList.add(newStudent);
        System.out.println("Student added successfully! Assigned ID: " + newId);
    }

    public void viewAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found in the system.");
            return;
        }

        System.out.println("\n--- List of Students ---");
        for (Student s : studentList) {
            System.out.println("ID: " + s.getId() + " | Name: " + s.getDisplayName() + " | Active: " + s.isActive());
        }
    }

    public Student findStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s; // Student found Yes
            }
        }
        return null;
    }

    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        if (student != null) {
            student.setActive(false);
            System.out.println("Student ID " + id + " has been successfully deactivated.");
        } else {
            System.out.println("Error: Student with ID " + id + " not found.");
        }
    }
}