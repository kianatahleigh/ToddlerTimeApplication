package org.example.toddlertimeapplication.repository;

import java.util.Optional;
import org.example.toddlertimeapplication.model.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByEmail(String email);

    Parent findByPhoneNumber(String phoneNumber);

}