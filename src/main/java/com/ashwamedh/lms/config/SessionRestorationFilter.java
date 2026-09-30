package com.ashwamedh.lms.config;

import com.ashwamedh.lms.model.User;
import com.ashwamedh.lms.repository.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SessionRestorationFilter implements Filter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);

        if (session.getAttribute("loggedInUser") == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("userEmail".equals(cookie.getName())) {
                        String email = cookie.getValue();
                        User user = userRepository.findByEmail(email);
                        if (user != null) {
                            session.setAttribute("loggedInUser", user);
                        }
                        break;
                    }
                }
            }
        }
        
        chain.doFilter(request, response);
    }
}
