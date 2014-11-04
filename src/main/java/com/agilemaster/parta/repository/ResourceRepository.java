package com.agilemaster.parta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.parta.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}

