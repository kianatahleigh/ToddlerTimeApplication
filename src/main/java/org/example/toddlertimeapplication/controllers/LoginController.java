package org.example.toddlertimeapplication.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home") // Changed from "/login" to "/home"
public class LoginController {

    @GetMapping("/login") // Now maps to "/home/login"
    public String showLoginPage() {
        return "ParentLogin"; // Ensure this matches your Thymeleaf template
    }

}