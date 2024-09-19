package com.example.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Course extends BaseEntity {

    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name="authors_courses",
            joinColumns={
                    @JoinColumn(name="course_id")
            },
            inverseJoinColumns ={
                    @JoinColumn(name="author_id")
            }
    )
    private List<Author> authors;
    @OneToMany(mappedBy = "course")
    private List<Section> sections;



}
