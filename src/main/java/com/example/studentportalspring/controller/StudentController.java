package com.example.studentportalspring.controller;

import com.example.studentportalspring.model.Student;
import com.example.studentportalspring.service.CourseService;
import com.example.studentportalspring.service.SkillService;
import com.example.studentportalspring.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;
    private final SkillService skillService;
    private final CourseService courseService;

    @GetMapping("/students")
    public String students(ModelMap modelMap, @RequestParam(value = "skillId",required = false) Integer skillId,
                           @RequestParam(value = "courseId",required = false) Integer courseId) {

        List<Student> students = studentService.getStudents(courseId, skillId);
        modelMap.addAttribute("students",students);
        return "students";
    }

    @GetMapping("/student/add")
    public String addStudentPage(ModelMap modelMap){
        modelMap.addAttribute("courses", courseService.findAll());
        modelMap.addAttribute("skills", skillService.findAll());
        return "addStudent";
    }

    @PostMapping("/student/add")
    public String addStudent(@ModelAttribute Student student, @RequestParam("picture") MultipartFile multipartFile ){
        studentService.save(student,multipartFile);
        return "redirect:/students";
    }
}
