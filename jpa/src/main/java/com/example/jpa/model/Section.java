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
public class Section extends BaseEntity{

    private String name;
    private  int sectionOrder;
@ManyToOne
@JoinColumn(name="course_id")
    private Course course;
@OneToMany(mappedBy = "section")
private List<Lecture> lectures;
}
