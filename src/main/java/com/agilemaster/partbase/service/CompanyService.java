package com.agilemaster.partbase.service;

import java.util.Map;

public interface CompanyService {
	
    Map<String,Object> initCompany(String authKey,String userId,String username,String dataSourceKey);
}
