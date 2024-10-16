package org.example.toddlertimeapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "children")
public class Child {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Child's first name is required")
        private String firstName;

        @NotBlank(message = "Child's last name is required")
        private String lastName;

        @Past(message = "Date of birth must be in the past")
        private LocalDate dateOfBirth;

        @Min(value = 0, message = "Age cannot be negative")
        private int age;

        @NotBlank(message = "Gender is required")
        private String gender;

        @ManyToOne
        @JoinColumn(name = "parent_id")
        private Parent parent;

        @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
        private List<Task> tasks;
    }