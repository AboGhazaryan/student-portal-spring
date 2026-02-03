package com.example.studentportalspring.service.impl;

import com.example.studentportalspring.model.Course;
import com.example.studentportalspring.repository.CourseRepository;
import com.example.studentportalspring.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        return  courseRepository.save(course);
    }

    @Override
    public Course findById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
     courseRepository.deleteById(id);
    }
}
