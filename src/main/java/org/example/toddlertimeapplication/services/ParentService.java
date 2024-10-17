package org.example.toddlertimeapplication.services;


import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "reset-token"; // Replace with actual token generation logic
    }


}
