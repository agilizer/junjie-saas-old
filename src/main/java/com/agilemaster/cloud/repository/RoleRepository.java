package com.agilemaster.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.cloud.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
