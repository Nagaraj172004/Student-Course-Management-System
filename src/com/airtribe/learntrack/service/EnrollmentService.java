package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class EnrollmentService {
    private ArrayList<Enrollment> enrollmentList;

    public EnrollmentService() {
        this.enrollmentList = new ArrayList<>();
    }

    public void enrollStudent(int studentId, int courseId, String date) {
        int newId = IdGenerator.getNextEnrollmentId();
        Enrollment newEnrollment = new Enrollment(newId, studentId, courseId, date);
        enrollmentList.add(newEnrollment);
        System.out.println("Enrollment successful! Assigned Enrollment ID: " + newId);
    }

    public void viewStudentEnrollments(int studentId) {
        boolean found = false;
        System.out.println("\n--- Enrollments for Student ID " + studentId + " ---");
        for (Enrollment e : enrollmentList) {
            if (e.getStudentId() == studentId) {
                System.out.println("Enrollment ID: " + e.getId() +
                        " | Course ID: " + e.getCourseId() +
                        " | Date: " + e.getEnrollmentDate() +
                        " | Status: " + e.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No enrollments found for this student.");
        }
    }

    public void updateEnrollmentStatus(int enrollmentId, String newStatus) {
        for (Enrollment e : enrollmentList) {
            if (e.getId() == enrollmentId) {
                e.setStatus(newStatus.toUpperCase());
                System.out.println("Enrollment ID " + enrollmentId + " status updated to " + newStatus.toUpperCase() + ".");
                return;
            }
        }
        System.out.println("Error: Enrollment ID " + enrollmentId + " not found.");
    }
}