package com.ensa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.jpa.entity.Blog;
import com.ensa.jpa.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	public List<Blog> findByUser(User user);
	
	
}
