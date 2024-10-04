package com.example.jpa.service.section;

import com.example.jpa.model.Section;

public interface ISectionService {
    // Create a new section
    Section createSection(Section section);

    // Get section details by ID
    Section getSectionById(Long sectionId);

    // Update section details
    Section updateSection(Long sectionId, Section section);

    // Delete a section by ID
    void deleteSection(Long sectionId);

    // Get all sections of a course
    List<Section> getSectionsByCourseId(Long courseId);

    // Assign a lecture to a section
    void assignLectureToSection(Long sectionId, Long lectureId);
}
