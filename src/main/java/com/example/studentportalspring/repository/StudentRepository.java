package com.example.studentportalspring.repository;

import com.example.studentportalspring.model.Course;
import com.example.studentportalspring.model.Skill;
import com.example.studentportalspring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s from  Student s join s.skills sk where sk = :skill")
    List<Student> findBySkill(Skill skill);

    List<Student> findByCourse(Course course);
}
