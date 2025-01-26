package com.ashutosh.BlogApp.repository;

import com.ashutosh.BlogApp.entity.Blog;
import com.ashutosh.BlogApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByUser(User user);

    List<Blog> findByUserId(Long userId);
}
