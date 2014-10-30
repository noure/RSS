package com.ensa.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.jpa.entity.Blog;
import com.ensa.jpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByBlog(Blog blog, Pageable pageable);
	Item findByBlogAndLink(Blog blog, String likn); 
}
