package org.example.toddlertimeapplication.controllers;

import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.services.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    // Parent profile
    @GetMapping("/profile")
    public String parentProfile(Principal principal, Model model) {
        Optional<Parent> parent = parentService.findByEmail(principal.getName());
        if (parent.isPresent()) {
            model.addAttribute("parent", parent.get());  // Unwrapping Optional
        } else {
            // Handle case where parent is not found (optional)
            model.addAttribute("parent", null);
        }
        return "ParentProfile";  // Thymeleaf template for displaying parent profile
    }

    // Parent dashboard
    @GetMapping("/dashboard")
    public String parentDashboard(Principal principal, Model model) {
        Optional<Parent> parentOpt = parentService.findByEmail(principal.getName());
        if (parentOpt.isPresent()) {
            Parent parent = parentOpt.get();
            model.addAttribute("parent", parent);
            model.addAttribute("children", parent.getChildren());  // Assuming Parent has a getChildren method
        } else {
            model.addAttribute("parent", null);  // Handle case where parent is not found
            model.addAttribute("children", new ArrayList<>());  // Empty children list to avoid null pointer
        }
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
