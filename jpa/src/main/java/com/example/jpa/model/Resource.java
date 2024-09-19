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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("resource_type")
public class Resource extends BaseEntity {

    private String name;
    private int size;
    private String url;
    @OneToOne
    @JoinColumn(name="lecture_id")
    private Lecture lecture;

}
