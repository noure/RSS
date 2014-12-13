package com.ensa.jpa.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ensa.jpa.entity.Role;
import com.ensa.jpa.entity.User;
import com.ensa.jpa.repository.UserRepository;

@Service
@Transactional 
public class CustomUserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		User user = userRepository.findByName(userName);

		List<Role> roles = user.getRoles();

		return new org.springframework.security.core.userdetails.User(userName,
				user.getPassword(), getGrantedAuthorities(getRolesIntoString(roles)));
	}

	public List<String> getRolesIntoString(List<Role>  roles) {
		List<String> stringroles = new ArrayList<String>();

		for (Role role : roles) {
			stringroles.add(role.getName());
		}
		return stringroles;
	}

	private  List<GrantedAuthority> getGrantedAuthorities(
			List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
