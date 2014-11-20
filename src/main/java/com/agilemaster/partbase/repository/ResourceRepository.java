package com.agilemaster.partbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.partbase.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}

