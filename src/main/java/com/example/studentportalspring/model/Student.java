package com.example.studentportalspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;

    @ManyToOne
    private Course course;
    private String pictureName;

    @ManyToMany
    @JoinTable(name = "student_skill", joinColumns = @JoinColumn(name="student_id"),
    inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

}

