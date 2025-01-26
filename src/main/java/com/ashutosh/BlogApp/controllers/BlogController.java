package com.ashutosh.BlogApp.controllers;

import com.ashutosh.BlogApp.entity.Blog;
import com.ashutosh.BlogApp.entity.User;
import com.ashutosh.BlogApp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
//        // Get the currently logged-in user's username
//        String username = getLoggedInUsername();
//        if (username == null) {
//            throw new RuntimeException("User is not logged in.");
//        }
//
//        // Retrieve blogs for the logged-in user by username
//        List<Blog> blogs = blogService.getUserBlogsByUsername(username);
//        model.addAttribute("blogs", blogs);
//        return "dashboard"; // Maps to dashboard.html
//    }

//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
//        // Logic to retrieve blogs for the logged-in user
//        // If you want to allow access without authentication, you may need to adjust this logic
//        List<Blog> blogs = blogService.getAllBlogs(); // Adjust this method as needed
//        model.addAttribute("blogs", blogs);
//        return "dashboard"; // Maps to dashboard.html
//    }


//    // BlogController.java
//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
//        List<Blog> blogs = blogService.getAllBlogs(); // Fetch all blogs
//        model.addAttribute("blogs", blogs);
//        return "dashboard"; // Maps to dashboard.html
//    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Blog> blogs = blogService.getAllBlogs(); // Fetch all blogs
        model.addAttribute("blogs", blogs);
        return "dashboard"; // Maps to dashboard.html
    }

    // Helper method to get the logged-in user's username
    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return null; // User is not logged in or session expired
        }
        return authentication.getName(); // Return the username of the logged-in user
    }

    @GetMapping("/create")
    public String showCreateBlogPage() {
        return "createBlog"; // Maps to createBlog.html
    }

//    @PostMapping("/create")
//    public String createBlog(@ModelAttribute Blog blog) {
//        // Replace with the actual logged-in user retrieval logic
//        Long userId = 1L; // This is mocked, implement actual logic for fetching logged-in user
//        blogService.createBlog(blog, userId);
//        return "redirect:/blogs/dashboard"; // Redirect to the list of blogs (or dashboard) after creating a blog
//    }
// BlogController.java
    @PostMapping("/create")
    public String createBlog(@ModelAttribute Blog blog, RedirectAttributes redirectAttributes) {
        blogService.createBlog(blog); // Call the service to create the blog
        redirectAttributes.addFlashAttribute("message", "Blog created successfully!"); // Add success message
        return "redirect:/blogs/dashboard"; // Redirect to the dashboard after creating the blog
    }


    @GetMapping("/{id}/edit")
    public String showEditBlogPage(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);
        return "editBlog"; // Create an editBlog.html file for this
    }

    @PostMapping("/{id}/update")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog updatedBlog) {
        blogService.updateBlog(id, updatedBlog);
        return "redirect:/blogs/dashboard"; // After updating, redirect to the dashboard
    }

    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/blogs/dashboard"; // After deleting, redirect to the dashboard
    }
}