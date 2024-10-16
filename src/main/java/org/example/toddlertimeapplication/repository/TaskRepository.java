package org.example.toddlertimeapplication.repository;

import org.example.toddlertimeapplication.model.Child;
import org.example.toddlertimeapplication.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByChild(Child child);

    List<Task> findByChildId(Long child_id);
}