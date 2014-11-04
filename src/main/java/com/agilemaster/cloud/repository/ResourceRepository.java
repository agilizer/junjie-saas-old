package com.agilemaster.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.cloud.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}

