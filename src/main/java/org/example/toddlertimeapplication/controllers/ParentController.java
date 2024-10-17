package org.example.toddlertimeapplication.controllers;

import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    // Parent  profile`
    @GetMapping("/profile")
    public String parentProfile(Principal principal, Model model) {
        Optional<Parent> parent = parentService.findByEmail(principal.getName()); // Assuming you identify parents by username
        model.addAttribute("parent", parent);
        return "ParentProfile";  // Thymeleaf template for displaying parent profile
    }

    // Parent dashboard
    @GetMapping("/dashboard")
    public String parentDashboard(Principal principal, Model model) {
        Optional<Parent> parent = parentService.findByEmail(principal.getName()); // Assuming you identify parents by username
        model.addAttribute("parent", parent);
        return "ParentDashboard";  // Thymeleaf template for displaying parent dashboard
    }

    // Save a newly created parent
    @PostMapping("/save")
    public String saveParent(@ModelAttribute("parent") Parent parent) {
        parentService.saveParent(parent);
        return "redirect:/parent/dashboard";  // Redirect to the parent profile after saving
    }

    @GetMapping
    public String logout(Model model) {
        return "HomePage";
    }

}
