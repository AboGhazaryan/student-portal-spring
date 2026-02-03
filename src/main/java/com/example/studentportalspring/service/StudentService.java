package com.example.studentportalspring.service;

import com.example.studentportalspring.model.Course;
import com.example.studentportalspring.model.Skill;
import com.example.studentportalspring.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {

    List<Student> findAll();

    Student save(Student student, MultipartFile multipartFile);

    List<Student> findByCourse(Course course);

    List<Student> findBySkill(Skill skill);

    List<Student> getStudents(Integer courseId, Integer skillId);


}
