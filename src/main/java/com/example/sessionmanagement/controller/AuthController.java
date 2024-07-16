package com.example.sessionmanagement.controller;

import com.example.sessionmanagement.model.Session;
import com.example.sessionmanagement.model.User;
import com.example.sessionmanagement.service.SessionService;
import com.example.sessionmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/login")
    public Session login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return sessionService.createSession(user.getId());
        }
        throw new RuntimeException("Invalid credentials");
    }

    @GetMapping("/validate")
    public boolean validateSession(@RequestParam String sessionId) {
        Session session = sessionService.findBySessionId(sessionId);
        return session != null && session.getExpirationTime() > System.currentTimeMillis();
    }
}
