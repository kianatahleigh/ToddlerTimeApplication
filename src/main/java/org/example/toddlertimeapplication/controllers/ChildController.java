package org.example.toddlertimeapplication.controllers;


import jakarta.validation.Valid;
import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.model.Status;
import org.example.toddlertimeapplication.services.ParentService;
import org.example.toddlertimeapplication.services.TaskService;
import org.example.toddlertimeapplication.services.ChildService;
import org.example.toddlertimeapplication.model.Child;
import org.example.toddlertimeapplication.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/children")
public class ChildController {

    @Autowired
    private ChildService childService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ParentService parentService;

    // View all children
    @GetMapping
    public String viewChild(Model model) {
        List<Child> children = childService.getAllChildren();
        model.addAttribute("children", children);
        return "ChildList";  // Thymeleaf template name
    }

    // Display form to create a new child
    @GetMapping("/new")
    public String showCreateChildForm(Model model) {
        Child child = new Child();

        Parent parent = parentService.getAuthenticatedParent(); // You need to implement this
        child.setParent(parent);

        model.addAttribute("child", child);
        model.addAttribute("parent", child.getParent());
        return "ChildRegistration";  // Thymeleaf template for child form
    }


    @PostMapping("/save")
    public String saveChild(@Valid @ModelAttribute("child") Child child, BindingResult result, Model model) {
        // If there are validation errors, return to the form
        if (result.hasErrors()) {
            return "ChildRegistration"; // Return to the form view if there are errors
        }

        try {
            System.out.println("froms save child function " + child.getParent());
            // The parentId is part of the child object now, no need for @RequestParam
            Parent parent = child.getParent();
            if (child.getParent() == null) {
                model.addAttribute("error", "Parent information is missing.");
                return "ParentLogin";
            }

            // Save the child and associate with parent
            childService.saveChild(child);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error saving child: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while saving the child's information.");
            return "ChildRegistration"; // Return to the form in case of an error
        }

        // Redirect after a successful save
        return "redirect:/parent/dashboard"; // Redirect after successful submission
    }



    // Display form to edit an existing child
    @GetMapping("/edit/{id}")
    public String showEditChildForm(@PathVariable Long id, Model model) {
        Child child = childService.getChildById(id);
        model.addAttribute("child", child);
        return "ChildRegistration";  // Return to the same form template for editing
    }

    // Update an existing child's information
    @PostMapping("/update")
    public String updateChild(@ModelAttribute("child") Child child) {
        childService.saveChild(child);  // Save updates to the child
        return "redirect:/children";  // Redirect to child list after updating
    }

    @GetMapping("/tasks/{childId}")
    public String childTaskHub(@PathVariable Long childId, Model model) {
        // Retrieve the Child  using childId
        Child child = childService.getChildById(childId);

        if (child == null) {
            // Handle the case where the child is not found
            model.addAttribute("error", "Child not found.");
            return "errorPage";  // Replace with your actual error page
        }

        // Fetch tasks associated with the child
        List<Task> tasks = taskService.getTasksByChild(child);

        // Add attributes to the model
        model.addAttribute("tasks", tasks);
        model.addAttribute("child", child);

        return "ChildTaskList";  // Thymeleaf template for task hub
    }

    // Update task status for a child
    @PostMapping("/tasks/update-status")
    public String updateTaskStatus(@RequestParam Long taskId, @RequestParam Status status) {
        taskService.updateTaskStatus(taskId, status);
        return "redirect:/children/tasks";  // Redirect back to task hub after update
    }
}
