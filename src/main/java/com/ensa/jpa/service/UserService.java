package com.ensa.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensa.jpa.entity.Blog;
import com.ensa.jpa.entity.Item;
import com.ensa.jpa.entity.Role;
import com.ensa.jpa.entity.User;
import com.ensa.jpa.repository.BlogRepository;
import com.ensa.jpa.repository.ItemRepository;
import com.ensa.jpa.repository.RoleRepository;
import com.ensa.jpa.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findOne(int id) {

		return userRepository.findOne(id);
	}
	
	public User findOne(String name) {

		return userRepository.findByName(name);
	}
	
	@Transactional
	public User findOneWithBlogs(int id) {
		User user = findOne(id);
		List<Blog> blogs = blogRepository.findByUser(user);

		for (Blog blog : blogs) {

			List<Item> items = itemRepository.findByBlog(blog,new PageRequest(0, 10, Direction.DESC, "publishDate"));
			blog.setItems(items);

		}
		user.setBlogs(blogs);

		return user;
	}

	public void save(User user) {
		BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
		String pass=user.getPassword();
		user.setPassword(encoder.encode(pass));
		System.out.println(" mot de passe encoder en bcrypth :::"+ encoder.encode(pass));
		
		user.setEnabled(true);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		user.setRoles(roles);
		userRepository.save(user);
		
	}

	public User findByName(String name) {
		
		User user = userRepository.findByName(name);
		
		return findOneWithBlogs(user.getId());
		
	}

	public void detele(int id) {
		userRepository.delete(id);
	}

}
