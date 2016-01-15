package com.agilemaster.cloud.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.agilemaster.cloud.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	Company findByAuthKey(String key);

}
