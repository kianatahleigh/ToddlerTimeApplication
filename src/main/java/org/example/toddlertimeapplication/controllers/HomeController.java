package org.example.toddlertimeapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home() {
        return "home"; // Returns the name of the view (Thymeleaf template) for the home page
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Returns the name of the view for the login page
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup"; // Returns the name of the view for the sign-up page
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password"; // Returns the name of the view for the forgot password page
    }
}