package com.agilemaster.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public Object index() {
		return "test";
	}

}
