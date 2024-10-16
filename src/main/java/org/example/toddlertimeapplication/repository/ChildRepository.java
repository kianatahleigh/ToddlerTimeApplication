package org.example.toddlertimeapplication.repository;

import org.example.toddlertimeapplication.model.Child;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
    Child findById(long id);

    List<Child> findByParentId(Long parentId);

    Child getChildByParentId(Long parent_id);

    Child getChildById(Long id);


}