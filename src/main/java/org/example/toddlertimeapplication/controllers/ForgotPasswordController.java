package org.example.toddlertimeapplication.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forgot/password")
public class ForgotPasswordController {

    @GetMapping("/forgot/password")
    public String forgotPassword() {
        return "ForgotPassword";
    }




}
