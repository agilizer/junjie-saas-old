package com.agilemaster.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agilemaster.cloud.entity.Company;
import com.agilemaster.cloud.service.ShareService;

@Controller
@RequestMapping("/api/v1/sms")
public class SmsController {	
	@Autowired
	private ShareService shareService;
	
    @RequestMapping(value = "/send", method=RequestMethod.POST)
    public @ResponseBody Object send(@RequestParam(required = true) String key,
    		@RequestParam(required = true)String phoneNumbers,
    		@RequestParam(required = true)String content) {
    	Company company = shareService.findCompanyByKey(key);
    	if(company!=null){
    		shareService.sendSMS(phoneNumbers, content, company);
    		return "SUCCESS";
    	}else{
    		return "COMPANY_NOT_FOUND";
    	}
        
    }

}
