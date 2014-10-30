package com.ensa.jpa.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ensa.jpa.entity.Blog;
import com.ensa.jpa.service.BlogService;
import com.ensa.jpa.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	

	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog=blogService.findOne(id);
		blogService.detele(blog);

		return "redirect:/account.html";
	}

	
	


	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddBlog(Model model, @Valid @ModelAttribute Blog blog,BindingResult result, Principal principal) {
		
		if(result.hasErrors()) return account(model,principal);
		
		
		String name = principal.getName();

		blogService.addBlog(blog, name);

		return "redirect:/account.html";
	}

	

	@RequestMapping("/account")
	public String account(Model model, Principal principal) {

		String name = principal.getName();
		model.addAttribute("user", userService.findByName(name));

		return "account";
	}

	

}
