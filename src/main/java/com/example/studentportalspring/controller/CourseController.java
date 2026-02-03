package com.example.studentportalspring.controller;

import com.example.studentportalspring.model.Course;
import com.example.studentportalspring.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/courses")
    public String courses(ModelMap modelMap) {
        List<Course> courses = courseService.findAll();
        modelMap.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("/courses/delete")
    public String deleteCourse(@RequestParam("id") int id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/course/add")
    public String addCourse() {
        return "addCourse";
    }

    @PostMapping("/course/add")
    public String addCurse(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/courses";
    }
}


