package com.example.jpa.service.author;

import com.example.jpa.model.Author;

public interface IAuthorService {
    // Create a new author
    Author createAuthor(Author author);

    // Get author details by ID
    Author getAuthorById(Long authorId);

    // Update author details
    Author updateAuthor(Long authorId, Author author);

    // Delete an author by ID
    void deleteAuthor(Long authorId);

    // Get a list of all authors
    List<Author> getAllAuthors();

    // Assign an author to a course
    void assignAuthorToCourse(Long authorId, Long courseId);
}
