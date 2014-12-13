package com.ensa.jpa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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

@Transactional
@Service
public class InitDbService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	ItemRepository itemRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		userAdmin.setEmail("nourr@quoi.com");
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		User userNour = new User();
		userNour.setEnabled(true);
		userNour.setName("nour");
		userNour.setEmail("nourr@quoi.com");
		userNour.setPassword(encoder.encode("nourr"));
		roles.add(roleAdmin);
		roles.add(roleUser);
		userNour.setRoles(roles);

		userRepository.save(userNour);

		Blog blogJavaVids = new Blog();
		blogJavaVids.setName("JavaVids");
		blogJavaVids.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJavaVids.setUser(userNour);
		blogRepository.save(blogJavaVids);

		Item item1 = new Item();
		item1.setTitle("first");
		item1.setLink("http://www.javavids.com");
		item1.setPublishDate(new Date());
		item1.setBlog(blogJavaVids);
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setTitle("second");
		item2.setLink("http://www.javavids.com");
		item2.setPublishDate(new Date());
		item2.setBlog(blogJavaVids);
		itemRepository.save(item2);
		
		

	}

}
