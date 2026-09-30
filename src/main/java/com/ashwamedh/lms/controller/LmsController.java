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
            courseRepository.save(new Course(null, "Digital Ashwamedh Gurukul - Basic", "Foundational knowledge covering human values, basic vedic concepts, and core skills for everyday life.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/ChatGPT-Image-Jul-24-2026-03_51_48-PM.png"));
            courseRepository.save(new Course(null, "Digital Ashwamedh Gurukul - Advanced", "Deep dive into ancient wisdom blended with modern strategic thinking for true leadership.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/ChatGPT-Image-Aug-25-2026-10_00_25-PM-1.png"));
            courseRepository.save(new Course(null, "Kaun Banega Crorepati Dharma Pariksha", "Test your knowledge of the great epics and win exciting rewards while learning the path of Dharma.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/Mahabharata_-The-Great-Dharma-Trial.png"));
            courseRepository.save(new Course(null, "Epic Ramayana Mahapariksha", "A comprehensive study and examination of the glorious Ramayana and its eternal values.", "https://ashwamedhgurukul.org/wp-content/uploads/2026/08/Epic-Ramayana-Mahapariksha-Poster-1.png"));
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

    @Autowired
    private com.ashwamedh.lms.repository.EnrollmentRepository enrollmentRepository;

    @GetMapping("/dashboard")
    public String dashboard(jakarta.servlet.http.HttpSession session, Model model) {
        com.ashwamedh.lms.model.User user = (com.ashwamedh.lms.model.User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        List<com.ashwamedh.lms.model.Enrollment> enrollments = enrollmentRepository.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("enrollments", enrollments);
        return "dashboard";
    }

    @GetMapping("/enroll/{id}")
    public String enroll(@PathVariable Long id, jakarta.servlet.http.HttpSession session) {
        com.ashwamedh.lms.model.User user = (com.ashwamedh.lms.model.User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            // Check if already enrolled
            boolean alreadyEnrolled = enrollmentRepository.findByUser(user).stream()
                    .anyMatch(e -> e.getCourse().getId().equals(id));
            if (!alreadyEnrolled) {
                com.ashwamedh.lms.model.Enrollment e = new com.ashwamedh.lms.model.Enrollment(null, user, course, java.time.LocalDateTime.now());
                enrollmentRepository.save(e);
            }
        }
        return "redirect:/dashboard";
    }
}
