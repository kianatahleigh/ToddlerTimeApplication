package org.example.toddlertimeapplication.controllers;

import jakarta.validation.Valid;
import org.example.toddlertimeapplication.model.Child;
import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.model.Task;
import org.example.toddlertimeapplication.repository.ChildRepository;
import org.example.toddlertimeapplication.repository.TaskRepository;
import org.example.toddlertimeapplication.services.ParentService;
import org.example.toddlertimeapplication.services.TaskService;
import org.example.toddlertimeapplication.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ChildService childService;

    @Autowired
    private ParentService parentService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ChildRepository childRepository;

    // Parent view for listing all tasks
    @GetMapping("/view")
    public String listAllTasks(Model model) {

        // Get the currently authenticated parent
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Assuming the email is used as the username
        Parent loggedInParent = parentService.getParentByEmail(email); // Fetch parent by email

        List<Task> tasks = taskService.getAllTasksByParentId(loggedInParent.getId());
        model.addAttribute("tasks", tasks);
        model.addAttribute("loggedInParent", loggedInParent);

        return "TaskList";  // View for listing tasks
    }


    // Parent creating or editing a task
    @GetMapping("/new")
    public String showCreateTaskPage(Model model) {
        Task task = new Task(); // Create a new task object
        model.addAttribute("task", task);

        // Get the currently authenticated parent
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Assuming the email is used as the username
        Parent loggedInParent = parentService.getParentByEmail(email); // Fetch parent by email

        // Fetch only the children for the logged-in parent
        List<Child> children = childService.getChildrenByParentId(loggedInParent.getId());
        model.addAttribute("children", children); // Pass only the parent's children for selection

        return "CreateTaskPage"; // Ensure this matches your template name
    }

    // Save a new task
    @PostMapping("/create")
    public String createTask(@ModelAttribute @Valid Task task,
                             @RequestParam Long childId, // Get childId from the request
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("children", childService.getChildrenByParentId(task.getChild().getParent().getId())); // Fetch children for the parent again
            return "CreateTaskPage"; // Return to the form view if there are errors
        }

        // Fetch the child and set it in the task
        Child child = childService.getChildById(childId);
        task.setChild(child); // Set the child in the task object

        taskService.saveTask(task); // Save the task
        return "redirect:/tasks/view"; // Redirect to the task list after creation
    }

    // Show the edit task form
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);  // Get the task to edit
        model.addAttribute("task", task);

        // Get the currently authenticated parent
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Assuming the email is used as the username
        Parent loggedInParent = parentService.getParentByEmail(email); // Fetch parent by email

        // Fetch only the children for the logged-in parent
        List<Child> children = childService.getChildrenByParentId(loggedInParent.getId());
        model.addAttribute("children", children);  // Pass children for assignment

        return "TaskEdit";  // Return the form view for editing the task
    }

    // Update an existing task
    
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task,
                             @RequestParam Long childId, RedirectAttributes redirectAttributes) {
        // Find the corresponding child by ID
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid child ID: " + childId));

        // Set the child on the task
        task.setChild(child);

        // Save the task with the updated child
        taskRepository.save(task);

        // Redirect to the parent dashboard or task list after saving
        redirectAttributes.addFlashAttribute("message", "Task updated successfully.");
        return "redirect:/tasks/view";
    }



    // Show the delete task confirmation page
    @GetMapping("/delete/{id}")
    public String showDeleteTaskConfirmation(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);  // Get the task to delete
        model.addAttribute("task", task);
        return "TaskDelete";  // Return the delete confirmation view
    }

    // Delete the task
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);  // Call the service to delete the task
        return "redirect:/tasks/view";  // Redirect to the task list after deletion
    }

    // Optionally, you can add a method to show a child's task list
        @GetMapping("/child/tasks/{id}")
        public String listTasksForChild(@PathVariable Long id, Model model) {
            Child child = childService.getChildById(id);
            List<Task> tasks = taskService.getTasksByChildId(id);
            model.addAttribute("child", child);
            model.addAttribute("tasks", tasks);
            return "ChildTask"; // Ensure this matches your Thymeleaf template name
        }


}
