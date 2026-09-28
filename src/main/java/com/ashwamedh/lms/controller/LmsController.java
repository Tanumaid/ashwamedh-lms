package com.ashwamedh.lms.controller;

import com.ashwamedh.lms.model.Course;
import com.ashwamedh.lms.repository.CourseRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LmsController {

    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void init() {
        if(courseRepository.count() == 0) {
            courseRepository.save(new Course(null, "Digital Ashwamedh Gurukul - Basic", "Foundational knowledge covering human values, basic vedic concepts, and core skills for everyday life.", "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?auto=format&fit=crop&w=600&q=80"));
            courseRepository.save(new Course(null, "Digital Ashwamedh Gurukul - Advanced", "In-depth knowledge of advanced vedic topics, deep meditation techniques, and advanced skill development.", "https://images.unsplash.com/photo-1513258496099-48168024aec0?auto=format&fit=crop&w=600&q=80"));
            courseRepository.save(new Course(null, "Kaun Banega Crorepati Dharma Pariksha", "Prepare for the ultimate Dharma test. Interactive quizzes and historical deep dives. Coming soon!", "https://images.unsplash.com/photo-1505664159854-2338ce1f0088?auto=format&fit=crop&w=600&q=80"));
            courseRepository.save(new Course(null, "Vedic Mathematics", "Learn the ancient techniques of fast mental calculation and logical reasoning.", "https://images.unsplash.com/photo-1509228468518-180dd4864904?auto=format&fit=crop&w=600&q=80"));
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/login")
    public String doLogin() {
        // Mock login - redirects to dashboard
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "dashboard";
    }
}
