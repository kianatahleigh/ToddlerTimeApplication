package org.example.toddlertimeapplication.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "parents")
public class Parent implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull
    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotNull
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;


    @NotNull
    @NotBlank(message = "Password is required")
    private String password;

    @Transient // Not persisted in the database
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;


    @NotNull
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Min(value = 1, message = "You must have at least one child")
    private int numberOfChildren;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "At least one child is required")
    private List<Child> children;
    // Implement UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return roles/authorities (if applicable)
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }


    public String getConfirmPassword() {
        return this.confirmPassword;
    }





    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize this if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize this if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize this if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize this if needed
    }
}