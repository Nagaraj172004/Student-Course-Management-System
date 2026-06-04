package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.exception.EntityNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        boolean running = true;

        System.out.println("Welcome to the LearnTrack System!");

        while (running) {
            System.out.println("\n=================================");
            System.out.println("      LEARNTRACK MAIN MENU       ");
            System.out.println("=================================");
            System.out.println("1. Add New Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Deactivate a Student");
            System.out.println("5. Add New Course");
            System.out.println("6. View All Courses");
            System.out.println("7. Deactivate a Course");
            System.out.println("8. Enroll Student in Course");
            System.out.println("9. View Enrollments for a Student");
            System.out.println("10. Update Enrollment Status");
            System.out.println("11. Exit Application");
            System.out.print("Please select an option (1-11): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Batch Name: ");
                        String batch = scanner.nextLine();

                        studentService.addStudent(firstName, lastName, email, batch);
                        break;

                    case 2:
                        studentService.viewAllStudents();
                        break;

                    case 3:
                        System.out.print("Enter Student ID to search: ");
                        int searchId = Integer.parseInt(scanner.nextLine());
                        Student foundStudent = studentService.findStudentById(searchId);

                        if (foundStudent != null) {
                            System.out.println("\n--- Student Found ---");
                            System.out.println("Name: " + foundStudent.getDisplayName());
                            System.out.println("Email: " + foundStudent.getEmail());
                            System.out.println("Status: " + (foundStudent.isActive() ? "Active" : "Inactive"));
                        } else {
                            System.out.println("Student with ID " + searchId + " not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Student ID to deactivate: ");
                        int deactivateId = Integer.parseInt(scanner.nextLine());
                        studentService.deactivateStudent(deactivateId);
                        break;

                    case 5:
                        System.out.print("Enter Course Name: ");
                        String courseName = scanner.nextLine();
                        System.out.print("Enter Course Description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter Duration (in weeks): ");
                        int duration = Integer.parseInt(scanner.nextLine());
                        courseService.addCourse(courseName, description, duration);
                        break;

                    case 6:
                        courseService.viewAllCourses();
                        break;

                    case 7:
                        System.out.print("Enter Course ID to deactivate: ");
                        int courseId = Integer.parseInt(scanner.nextLine());
                        courseService.toggleCourseStatus(courseId, false);
                        break;
                    case 8:
                        System.out.print("Enter Student ID: ");
                        int sId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Course ID: ");
                        int cId = Integer.parseInt(scanner.nextLine());

                        // Using our custom exception to validate!
                        if (studentService.findStudentById(sId) == null) {
                            throw new EntityNotFoundException("Student with ID " + sId + " does not exist!");
                        }
                        if (courseService.findCourseById(cId) == null) {
                            throw new EntityNotFoundException("Course with ID " + cId + " does not exist!");
                        }

                        System.out.print("Enter Enrollment Date (DD/MM/YYYY): ");
                        String date = scanner.nextLine();
                        enrollmentService.enrollStudent(sId, cId, date);
                        break;

                    case 9:
                        System.out.print("Enter Student ID to view enrollments: ");
                        int viewStudentId = Integer.parseInt(scanner.nextLine());
                        enrollmentService.viewStudentEnrollments(viewStudentId);
                        break;

                    case 10:
                        System.out.print("Enter Enrollment ID to update: ");
                        int enrollId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Status (COMPLETED/CANCELLED): ");
                        String status = scanner.nextLine();
                        enrollmentService.updateEnrollmentStatus(enrollId, status);
                        break;

                    case 11:
                        running = false;
                        System.out.println("Exiting LearnTrack. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 11.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number, not text.");
            } catch (EntityNotFoundException e) {
                // Catching our custom exception right here!
                System.out.println("VALIDATION ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}