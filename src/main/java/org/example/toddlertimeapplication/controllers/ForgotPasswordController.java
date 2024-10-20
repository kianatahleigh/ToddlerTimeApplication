package org.example.toddlertimeapplication.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController {


    @GetMapping("/reset")
    public String forgotPassword(Model model) {
        return "ForgotPassword"; // Your ForgotPassword template
    }

}


