package com.agilemaster.partbase.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.service.HomeService;
import com.agilemaster.partbase.service.UserService;

/**
 * @author asdtiang
 */
@RestController("")
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private HomeService homeService;
	@Autowired
	private UserService userService;

	@RequestMapping("/api/v1/index")
	public Map<String,Object> index() {
		return homeService.index();
	}

	@RequestMapping("/welcome")
	public String welcome() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		if (session.getAttribute("haha") != null) {
			System.out.println("get from session haha:"+ session.getAttribute("haha"));
		} else {
			session.setAttribute("haha", "wowowo---->");
			System.out.println("put haha--------------->");
		}
		return "welcome";
	}

}
