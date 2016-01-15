package com.agilemaster.partbase.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.entity.ConfigDomain;
import com.agilemaster.partbase.service.ConfigDomainService;

@RestController
public class ConfigDomainController {
	@Autowired
	ConfigDomainService configDomainService;
	@RequiresUser
	@RequestMapping(value = "/api/v1/configDomain/list")
	public List<ConfigDomain> list() {
		return configDomainService.listConfigDomain();
	}
	@RequiresUser
	@RequestMapping(value = "/api/v1/configDomain/listMap")
	public Map<String,Object> listMap() {
		return configDomainService.list();
	}
}
