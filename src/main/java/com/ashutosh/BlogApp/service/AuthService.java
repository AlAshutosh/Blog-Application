package com.ashutosh.BlogApp.service;

import com.ashutosh.BlogApp.entity.User;
import com.ashutosh.BlogApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser (User user) {
        // Check if the username is already taken
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }

        // Store the password as plain text (no encoding)
        userRepository.save(user);
    }

    public User authenticate(String username, String rawPassword) {
        System.out.println(username);
        System.out.println(rawPassword);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("UserNameDB: "+user.getUsername());
            System.out.println("PasswordDB: "+user.getPassword());
            System.out.println("IdDB: "+user.getId());

            // Compare the raw password directly
            if (rawPassword.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}