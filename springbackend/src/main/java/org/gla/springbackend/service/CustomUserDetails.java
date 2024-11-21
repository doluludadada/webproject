package org.gla.springbackend.service;

import org.gla.springbackend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

/**
 * CustomUserDetails implements UserDetails interface to provide user information to Spring Security.
 * It wraps our custom User entity.
 */
public class CustomUserDetails implements UserDetails {

    private User user;

    /**
     * Constructor to initialize CustomUserDetails with a User object.
     * @param user The User entity from the database.
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user.
     * @return A collection of GrantedAuthority.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return user roles or authorities
        // Since we are not using roles now, return an empty list
        return java.util.Collections.emptyList();
    }

    /**
     * Returns the password used to authenticate the user.
     * @return The user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Returns the username used to authenticate the user.
     * @return The user's username.
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired.
     * @return true if the user's account is valid (non-expired), false if no longer valid (expired).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify this logic if account expiration is needed
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * @return true if the user is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify this logic if account locking is needed
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * @return true if the user's credentials are valid (non-expired), false if expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify this logic if credential expiration is needed
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true; // Modify this logic if user enabling/disabling is needed
    }
}
