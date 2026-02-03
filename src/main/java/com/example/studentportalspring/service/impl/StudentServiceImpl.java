package com.example.studentportalspring.service.impl;

import com.example.studentportalspring.model.Course;
import com.example.studentportalspring.model.Skill;
import com.example.studentportalspring.model.Student;
import com.example.studentportalspring.repository.StudentRepository;
import com.example.studentportalspring.service.CourseService;
import com.example.studentportalspring.service.SkillService;
import com.example.studentportalspring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SkillService skillService;
    private final CourseService courseService;
    @Value("${student.portal.image.directory.path}")
    private String imageDirectoryPath;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(Student student, MultipartFile multipartFile) {
        if(multipartFile != null && !multipartFile.isEmpty()){
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                student.setPictureName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> findByCourse(Course course) {
        if(course == null) {
            return List.of();
        }
        return studentRepository.findByCourse(course);
    }

    @Override
    public List<Student> findBySkill(Skill skill) {
        if(skill == null) {
            return List.of();
        }
        return studentRepository.findBySkill(skill);
    }

    @Override
    public List<Student> getStudents(Integer courseId, Integer skillId) {
        List<Student> result;
        if(skillId != null) {
            result = findBySkill(skillService.findById(skillId));
        } else if(courseId != null) {
            Course course = courseService.findById(courseId);
            result = findByCourse(course);
        } else {
            result = findAll();
        }
        return result;
    }

}
