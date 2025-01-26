package com.ashutosh.BlogApp.service;

import com.ashutosh.BlogApp.entity.Blog;
import com.ashutosh.BlogApp.entity.User;
import com.ashutosh.BlogApp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // Fetch all blogs
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll(); // Fetch all blogs from the repository
    }

    // Fetch a blog by its ID
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with ID: " + id));
    }

//    // Create a new blog (without user association)
//    public void createBlog(Blog blog, Long userId) {
//        // Set the default user ID (1L) for the blog
//        blog.setUser (new User(1L)); // Assuming you have a User entity with an ID of 1
//        blogRepository.save(blog); // Save the blog to the database
//    }

    // BlogService.java
    public void createBlog(Blog blog) {
        // Set the default user ID (1L) for the blog
        User defaultUser = new User();
        defaultUser.setId(1L); // Assuming you have a User entity with an ID of 1
        blog.setUser(defaultUser); // Associate the blog with the default user
        blogRepository.save(blog); // Save the blog to the database
    }

    // Update an existing blog
    public void updateBlog(Long id, Blog updatedBlog) {
        Blog blog = getBlogById(id); // Fetch the blog by its ID
        blog.setBody(updatedBlog.getBody());
        blogRepository.save(blog);
    }

    // Delete a blog by its ID
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
