package org.gla.springbackend.repository;

import org.gla.springbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a repository bean
public interface UserRepository extends JpaRepository<User, Long> {
    // Method to find a user by username
    User findByUsername(String username);

    // Method to find a user by email
    User findByEmail(String email);
}

