package com.ashutosh.BlogApp.controllers;

import com.ashutosh.BlogApp.entity.User;
import com.ashutosh.BlogApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // Maps to signup.html
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        try {
            authService.registerUser (user);
            // Redirect to login page with a success message
            return "redirect:/auth/login?success=true";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "signup"; // Stay on the signup page with the error message
        }
    }

    // Show login page and handle success message if present
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "success", required = false) String success, Model model) {
        if (success != null) {
            model.addAttribute("success", "Signup successful! Please log in.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        System.out.println(username);
        System.out.println(password);
        User user = authService.authenticate(username, password);


        if (user != null) {
            System.out.println("UserNameDB: "+user.getUsername());
            System.out.println("PasswordDB: "+user.getPassword());
            System.out.println("IdDB: "+user.getId());
            // Set authentication manually in SecurityContextHolder
            Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Redirect to the dashboard on successful login
            return "redirect:/blogs/dashboard";
        } else {
            model.addAttribute("error", "Invalid credentials! Please try again.");
            return "login"; // Stay on the login page and show an error message
        }
    }
}