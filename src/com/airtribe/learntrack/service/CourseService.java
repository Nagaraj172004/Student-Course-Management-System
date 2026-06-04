package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.ArrayList;

public class CourseService {
    private ArrayList<Course> courseList;

    public CourseService() {
        this.courseList = new ArrayList<>();
    }

    public void addCourse(String name, String description, int duration) {
        int newId = IdGenerator.getNextCourseId();
        Course newCourse = new Course(newId, name, description, duration);
        courseList.add(newCourse);
        System.out.println("Course added successfully! Assigned Course ID: " + newId);
    }

    public void viewAllCourses() {
        if (courseList.isEmpty()) {
            System.out.println("No courses found in the system.");
            return;
        }

        System.out.println("\n--- List of Courses ---");
        for (Course c : courseList) {
            System.out.println("ID: " + c.getId() + " | Name: " + c.getCourseName() +
                    " | Duration: " + c.getDurationInWeeks() + " weeks" +
                    " | Active: " + c.isActive());
        }
    }

    public Course findCourseById(int id) {
        for (Course c : courseList) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void toggleCourseStatus(int id, boolean status) {
        Course course = findCourseById(id);
        if (course != null) {
            course.setActive(status);
            System.out.println("Course ID " + id + " is now " + (status ? "Active" : "Inactive") + ".");
        } else {
            System.out.println("Error: Course with ID " + id + " not found.");
        }
    }
}