package com.example.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Author extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "author_seq")
//    @SequenceGenerator(name = "author_seq",
//            sequenceName = "author_seq",
//            allocationSize = 1
//    )
//    private Integer Id; // Integer = null
    private String firstName;
    private String lastName;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    private int age;
    @ManyToMany(mappedBy = "authors")
    private List<Course> courses;
    @Column(updatable = false,
    nullable = false)
    private LocalDateTime createdAt;
    @Column(insertable = false)
    private LocalDateTime lastModified;
}
