package com.ensa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensa.jpa.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String string);

}
