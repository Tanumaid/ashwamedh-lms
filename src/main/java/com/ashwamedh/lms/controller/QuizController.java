package com.ashwamedh.lms.controller;

import com.ashwamedh.lms.model.Course;
import com.ashwamedh.lms.model.Question;
import com.ashwamedh.lms.repository.CourseRepository;
import com.ashwamedh.lms.repository.QuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private com.ashwamedh.lms.repository.TestScoreRepository testScoreRepository;

    @GetMapping("/course/{id}/test")
    public String takeTest(@PathVariable Long id, Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("loggedInUser") == null) {
            request.getSession().setAttribute("redirectUrl", "/course/" + id + "/test");
            return "redirect:/login";
        }
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return "redirect:/";
        }
        List<Question> questions = questionRepository.findByCourseId(id);
        model.addAttribute("course", course);
        model.addAttribute("questions", questions);
        return "test";
    }

    @PostMapping("/course/{id}/test")
    public String submitTest(@PathVariable Long id, HttpServletRequest request, Model model) {
        com.ashwamedh.lms.model.User user = (com.ashwamedh.lms.model.User) request.getSession().getAttribute("loggedInUser");
        if (user == null) {
            request.getSession().setAttribute("redirectUrl", "/course/" + id + "/test");
            return "redirect:/login";
        }
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null) {
            return "redirect:/";
        }
        
        List<Question> questions = questionRepository.findByCourseId(id);
        int score = 0;
        
        for (Question q : questions) {
            String submittedAnswer = request.getParameter("question_" + q.getId());
            if (submittedAnswer != null && submittedAnswer.equals(q.getCorrectAnswer())) {
                score++;
            }
        }
        
        // Save the test score
        com.ashwamedh.lms.model.TestScore testScore = new com.ashwamedh.lms.model.TestScore(null, user, course, score, questions.size(), java.time.LocalDateTime.now());
        testScoreRepository.save(testScore);
        
        model.addAttribute("course", course);
        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        
        return "test-result";
    }
}
