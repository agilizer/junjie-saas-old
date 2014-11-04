package com.agilemaster.parta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.parta.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
