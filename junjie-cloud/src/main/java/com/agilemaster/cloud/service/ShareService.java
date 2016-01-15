package com.agilemaster.cloud.service;

import com.agilemaster.cloud.entity.Company;

public interface ShareService {
	
	Company findCompanyByKey(String key);
	int sendSMS(String phoneNumber,String content, Company company);

}
