package org.example.toddlertimeapplication.controllers;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class SignUpController {

    @Autowired
    private ParentService parentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("parent", new Parent());
        return "SignUp";
    }

    @PostMapping("/signup")
    public String handleSignup(@ModelAttribute Parent parent,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "SignUp"; // Show signup page with errors
        }

        try {
            parentService.saveParent(parent);
            return "redirect:/home/login"; // Redirect to login after successful signup
        } catch (IllegalArgumentException e) {
            bindingResult.rejectValue("email", "emailExists", e.getMessage());
            return "SignUp"; // Return to signup page with error message
        } catch (Exception e) {
            // Log unexpected exceptions for debugging
            e.printStackTrace();
            model.addAttribute("errorMessage", "An unexpected error occurred.");
            return "SignUp";
        }
    }


    @GetMapping("/forgot/password")
    public String forgotPassword(Model model) {
        return "ForgotPassword";
    }
}
