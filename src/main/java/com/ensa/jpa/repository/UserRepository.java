package com.ensa.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);
	

}
