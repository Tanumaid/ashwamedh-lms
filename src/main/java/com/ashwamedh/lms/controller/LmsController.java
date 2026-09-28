package com.ashwamedh.lms.controller;

import com.ashwamedh.lms.model.Course;
import com.ashwamedh.lms.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class LmsController {

    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void init() {
        if(courseRepository.count() == 0) {
            courseRepository.save(new Course(null, "Digital Ashwamedh Gurukul - Basic", "Foundational knowledge covering human values, basic vedic concepts, and core skills.", "https://via.placeholder.com/400x200?text=Basic+Course"));
            courseRepository.save(new Course(null, "Digital Ashwamedh Gurukul - Advanced", "In-depth knowledge of advanced vedic topics and advanced skill development.", "https://via.placeholder.com/400x200?text=Advanced+Course"));
            courseRepository.save(new Course(null, "Kaun Banega Crorepati Dharma Pariksha", "Prepare for the ultimate Dharma test. Coming soon!", "https://via.placeholder.com/400x200?text=Dharma+Pariksha"));
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "index";
    }

    @GetMapping("/course/{id}")
    public String courseDetails(@PathVariable Long id, Model model) {
        Course course = courseRepository.findById(id).orElse(null);
        model.addAttribute("course", course);
        return "course-details";
    }
}
