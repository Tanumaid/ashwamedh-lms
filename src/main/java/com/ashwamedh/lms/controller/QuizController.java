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

    @GetMapping("/course/{id}/test")
    public String takeTest(@PathVariable Long id, Model model) {
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
        
        model.addAttribute("course", course);
        model.addAttribute("score", score);
        model.addAttribute("total", questions.size());
        
        return "test-result";
    }
}
