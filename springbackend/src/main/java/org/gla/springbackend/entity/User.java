package org.gla.springbackend.entity;

import jakarta.persistence.*; // JPA annotations for entity mapping
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.Data; // Lombok annotation for generating boilerplate code

@Entity // Marks this class as a JPA entity
@Table(name = "users") // Maps the entity to the "users" table in the database
@Data // Generates getters, setters, toString, equals, and hashCode methods
public class User {

    @Id // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment strategy for the primary key
    private Long id;

    @Column(nullable = false, unique = true) // Column constraints: cannot be null and must be unique
    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(nullable = false) // Cannot be null
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters long and include uppercase, lowercase letters, and numbers")
    private String password;

    @NotEmpty(message = "Email is required")
    @Column(nullable = false, unique = true) // Cannot be null and must be unique
    @Email(message = "Invalid email format")
    private String email;

    // Additional fields can be added here
}
