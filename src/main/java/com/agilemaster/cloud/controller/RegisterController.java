package com.agilemaster.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agilemaster.cloud.service.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
    @RequestMapping("/register")
    public String register(@RequestParam(required = true) String username,@RequestParam(required = true) String password,
    		@RequestParam(required = true) String passwordConfirm,
    		@RequestParam(required = true) String companyName,Model model) {
    	
        return "";
    }
    @RequestMapping("/registerPage")
    public String registerPage(){
    	return "register";
    }

}
