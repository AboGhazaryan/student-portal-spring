package com.example.studentportalspring.service;

import com.example.studentportalspring.model.Course;

import java.util.List;


public interface CourseService {

    List<Course> findAll();

    Course save(Course course);

    Course findById(Integer id);

    void deleteById(Integer id);
}
