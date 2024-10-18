package org.example.toddlertimeapplication.services;

import jakarta.transaction.Transactional;
import org.example.toddlertimeapplication.model.Child;
import org.example.toddlertimeapplication.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ChildService {

    @Autowired
    private ChildRepository childRepository;

    // Get all children
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    // Get child by ID
    public Child getChildById(Long id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child not found with id: " + id));
    }


    public void saveChild(Child child) {
        childRepository.save(child);
    }
    // Delete a child by ID
    public void deleteChild(Long id) {
        childRepository.deleteById(id);
    }

    // Get a child by parent (assuming there is a parent-child relationship in your model)
    public List<Child> getChildrenByParentId(Long parentId) {
        return childRepository.findByParentId(parentId); // Assuming you have this method in your repository
    }

    public Child getChildByParentId(Long parentId) {
        return childRepository.getChildByParentId(parentId);
    }
}
