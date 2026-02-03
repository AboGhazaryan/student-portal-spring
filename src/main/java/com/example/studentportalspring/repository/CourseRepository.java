package com.example.studentportalspring.repository;

import com.example.studentportalspring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
