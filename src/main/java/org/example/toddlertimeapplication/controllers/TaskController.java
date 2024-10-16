package org.example.toddlertimeapplication.controllers;

import org.example.toddlertimeapplication.model.Child;
import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.model.Task;
import org.example.toddlertimeapplication.services.TaskService;
import org.example.toddlertimeapplication.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ChildService childService;

    // Parent view for listing all tasks
    @GetMapping
    public String listAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "TaskList";  // View for listing tasks
    }

    // Parent creating or editing a task
    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task());  // Pass an empty Task object to the form
        model.addAttribute("children", childService.getAllChildren());  // Pass children for assignment
        return "CreateTaskPage";  // Return the form view for task creation
    }

    // Save a new task
    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.saveTask(task);  // Save the task using your service
        return "redirect:/tasks";  // Redirect to the task list after creation
    }

    // Show the edit task form
    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);  // Get the task to edit
        model.addAttribute("task", task);
        model.addAttribute("children", childService.getAllChildren());  // Pass children for assignment
        return "TaskEdit";  // Return the form view for editing the task
    }

    // Update an existing task
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task) {
        task.setId(id);  // Set the id to update the correct task
        taskService.saveTask(task);  // Save the updated task
        return "redirect:/tasks";  // Redirect to the task list after updating
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
        return "redirect:/tasks";  // Redirect to the task list after deletion
    }

    // Optionally, you can add a method to show a child's task list
    @GetMapping("/child/{id}")
    public String listTasksForChild(@PathVariable Long id, Model model) {
        List<Task> tasks = taskService.getTasksByChildId(id);  // Get tasks for the specified child
        model.addAttribute("tasks", tasks);
        model.addAttribute("child", childService.getChildById(id));  // Get the child to display their name
        return "ChildTaskList";  // Return the view for displaying child's tasks
    }
}
