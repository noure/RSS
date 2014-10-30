package com.ensa.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ensa.jpa.entity.Blog;
import com.ensa.jpa.entity.Item;
import com.ensa.jpa.entity.User;
import com.ensa.jpa.exception.RssException;
import com.ensa.jpa.repository.BlogRepository;
import com.ensa.jpa.repository.ItemRepository;
import com.ensa.jpa.repository.UserRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	RssService rssService;

	@Autowired
	ItemRepository itemRepository;

	public void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item savedItems = itemRepository.findByBlogAndLink(blog,
						item.getLink());
				if (savedItems == null) {
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}

		} catch (RssException e) {
			e.printStackTrace();
		}

	}
	
	@Scheduled(fixedDelay=3600000)
	public void reload(){
		
			List<Blog> blogs = blogRepository.findAll();
			for (Blog blog : blogs) {
				saveItems(blog);
			}
	}

	public void addBlog(Blog blog, String name) {

		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name==authentication.name or hasRole('ROLE_ADMIN')")
	public void detele(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {

		return blogRepository.findOne(id);
	}

}
