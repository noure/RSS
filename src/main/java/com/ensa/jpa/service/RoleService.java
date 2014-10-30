package com.ensa.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensa.jpa.entity.Role;
import com.ensa.jpa.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	public Role findByName(String string) {
		return roleRepository.findByName(string);
	}
	
	

}
