package com.example.jpa.service.lecture;

import com.example.jpa.model.Lecture;

import java.util.List;

public interface ILectureService {
    // Create a new lecture
    Lecture createLecture(Lecture lecture);

    // Get lecture details by ID
    Lecture getLectureById(Long lectureId);

    // Update lecture details
    Lecture updateLecture(Long lectureId, Lecture lecture);

    // Delete a lecture by ID
    void deleteLecture(Long lectureId);

    // Get all lectures of a section
    List<Lecture> getLecturesBySectionId(Long sectionId);
}
