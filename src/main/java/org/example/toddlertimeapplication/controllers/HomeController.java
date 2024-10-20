package org.example.toddlertimeapplication.controllers;

import org.example.toddlertimeapplication.model.Parent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/") // Root mapping
public class HomeController {

    // Redirect root URL to /home
    @GetMapping
    public String homeRedirect() {
        return "redirect:/home";
    }

    // Handler for the home page
    @GetMapping("/home")
    public String home(Model model) {
        Parent parent = new Parent();
        model.addAttribute("parent", parent);
        return "HomePage"; // Your HomePage template
    }

    // Handler for the forgot password page
    @GetMapping("/forgot/password")
    public String forgotPassword(Model model) {
        return "ForgotPassword"; // Your ForgotPassword template
    }

    // Other handlers (contact, about, etc.)
    @GetMapping("/contact")
    public String contact(Model model) {
        return "Contact"; // Your Contact template
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "About"; // Your About template
    }

}