package org.example.toddlertimeapplication.services;


import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    public Parent getParentById(Long id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parent not found"));
    }

    public void saveParent(Parent parent) {
        // Check if email already exists
        if (parentRepository.findByEmail(parent.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Optionally, you can check for phone number uniqueness as well
        if (findByPhoneNumber(parent.getPhoneNumber()) != null) {
            throw new IllegalArgumentException("Phone number already exists");
        }

        // Encrypt the password
    // Log before saving
            System.out.println("Saving parent: " + parent);
        parent.setPassword(passwordEncoder.encode(parent.getPassword()));


        parent.setRole("PARENT");

        parentRepository.save(parent);
    }


    public Parent findByPhoneNumber(String phoneNumber) {
        return parentRepository.findByPhoneNumber(phoneNumber);
    }

    public void deleteParent(Long id) {
        parentRepository.deleteById(id);
    }
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }



    public Optional<Parent> findByEmail(String email) {
        return parentRepository.findByEmail(email);
    }

    public String generatePasswordResetToken(Parent parent) {
        // Logic for generating and storing the password reset token
        return "/forgot/password"; // Replace with actual token generation logic
    }

    public Parent getAuthenticatedParent() {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            // Handle the case when no one is authenticated
            throw new RuntimeException("No authenticated parent found");
        }

        // Assuming the email is used as the username in your UserDetails
        String email = authentication.getName();

        // Fetch the parent by email (or other identifier)
        Optional<Parent> parent = parentRepository.findByEmail(email);

        // Handle the case when the parent is not found
        return parent.orElseThrow(() -> new RuntimeException("Authenticated parent not found"));
    }


    public Parent getParentByEmail(String email) {
        return parentRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Parent not found"));
    }
}


