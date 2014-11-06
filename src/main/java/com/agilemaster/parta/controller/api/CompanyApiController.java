package com.agilemaster.parta.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.parta.service.CompanyService;
import com.junjie.commons.utils.JunjieStaticMethod;

@RestController("/api/v1")
public class CompanyApiController {
	@Autowired
	private CompanyService companyService;
	@RequestMapping("/api/v1/private/initDb")
	public Map<String,Object> initPlugin(@RequestParam(required = true) String key,
    		@RequestParam(required = true)String userId,
    		@RequestParam(required = true)String username,@RequestParam(required = true) String dataSourceKey){
		   return companyService.initCompany(key, userId, username, dataSourceKey);
	}
	
	@RequestMapping("/api/v1/private/jsonTest")
	public Map<String,Object> jsonTest(){
		return  JunjieStaticMethod.genResult();
	}
	

}
