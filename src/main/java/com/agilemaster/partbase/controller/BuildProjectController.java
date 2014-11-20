package com.agilemaster.partbase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.service.BuildProjectService;

@RestController
public class BuildProjectController {
	@Autowired
	BuildProjectService buildProjectService;
	@RequiresUser
	@RequestMapping("/api/v1/buildProject/list")
	public List create(HttpServletRequest request) {
		return buildProjectService.list();
	}
}
