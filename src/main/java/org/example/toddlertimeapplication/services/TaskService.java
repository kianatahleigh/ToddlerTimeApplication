package org.example.toddlertimeapplication.services;


import org.example.toddlertimeapplication.model.Child;
import org.example.toddlertimeapplication.model.Status;
import org.example.toddlertimeapplication.model.Task;
import org.example.toddlertimeapplication.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void saveAllTasks(List<Task> tasks) {
        taskRepository.saveAll(tasks);

    }

    public List<Task> getTasksByChild(Child child) {
        return taskRepository.findByChild(child);
    }

    public void updateTaskStatus(Long taskId, Status status) {
        Task task = getTaskById(taskId);
        task.setStatus(status);
        taskRepository.save(task);
    }

    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getTasksByChildId(Long childId) {
        return taskRepository.findByChildId(childId);
    }
}