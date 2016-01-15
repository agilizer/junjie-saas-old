package com.agilemaster.partbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.partbase.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
