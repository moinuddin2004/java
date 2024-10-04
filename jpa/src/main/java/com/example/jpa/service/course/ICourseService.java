package com.example.jpa.service.course;

import com.example.jpa.model.Course;

public interface ICourseService {
    // Create a new course
    Course createCourse(Course course);

    // Get course details by ID
    Course getCourseById(Long courseId);

    // Update course details
    Course updateCourse(Long courseId, Course course);

    // Delete a course by ID
    void deleteCourse(Long courseId);

    // Get a list of all courses
    List<Course> getAllCourses();

    // Assign a section to a course
    void assignSectionToCourse(Long courseId, Long sectionId);

    // Assign an author to a course
    void assignAuthorsToCourse(Long courseId, List<Long> authorIds);
}
