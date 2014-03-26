package com.agilemaster.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.cloud.entity.Company;
import com.agilemaster.cloud.repository.CompanyRepository;

@Service
public class ShareServiceImpl implements ShareService{

	@Autowired
	CompanyRepository companyRepository;
	@Autowired
	private SmsService smsService;
	@Override
	public Company findCompanyByKey(String key) {
		return companyRepository.findByAuthKey(key);
	}
	@Override
	public int sendSMS(String phoneNumber, String content, Company company) {
		if(company!=null){
			smsService.sendSMS(phoneNumber, content);
		}
		return 0;
	}

}
