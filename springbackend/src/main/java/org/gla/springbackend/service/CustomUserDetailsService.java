package org.gla.springbackend.service;

import org.gla.springbackend.entity.User;
import org.gla.springbackend.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * CustomUserDetailsService implements UserDetailsService interface to provide user details to Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor injection for UserRepository.
     *
     * @param userRepository The repository to access User data.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username.
     *
     * @param username The username entered by the user.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database using UserRepository
        User user = userRepository.findByUsername(username);
        if (user == null) {
            // If user not found, throw UsernameNotFoundException
            throw new UsernameNotFoundException("User not found");
        }
        // Return an instance of CustomUserDetails
        return new CustomUserDetails(user);
    }
}
