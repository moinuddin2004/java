package com.example.jpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Lecture extends BaseEntity {

    private String name;
    @ManyToOne
    @JoinColumn(name="section_id")
    private Section section;
    @OneToOne
    @JoinColumn(name="resource_id")
    private Resource resource;
}
