package com.ashwamedh.lms.controller;

import com.ashwamedh.lms.model.User;
import com.ashwamedh.lms.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {
        // Simple check to prevent duplicate emails
        if (userRepository.findByEmail(email) != null) {
            return "redirect:/register?error";
        }
        User user = new User(null, firstName + " " + lastName, email, password, "STUDENT");
        userRepository.save(user);
        return "redirect:/login?registered";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);
            String redirectUrl = (String) session.getAttribute("redirectUrl");
            if (redirectUrl != null) {
                session.removeAttribute("redirectUrl");
                return "redirect:" + redirectUrl;
            }
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
