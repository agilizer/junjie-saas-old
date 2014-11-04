package com.agilemaster.cloud.controller;

import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agilemaster.cloud.entity.Resource;
import com.agilemaster.cloud.entity.User;
import com.agilemaster.cloud.service.ResourceService;
import com.agilemaster.cloud.service.UserService;
import com.agilemaster.cloud.web.bind.annotation.CurrentUser;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, Model model) {
//        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
//        List<Resource> menus = resourceService.findMenus(permissions);
//        model.addAttribute("menus", menus);
        System.out.println("asdfafasdfadsffffffffffffffffffffff!");
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
    	Subject subject = SecurityUtils.getSubject();
    	Session session  = subject.getSession();
    	if(session.getAttribute("haha")!=null){
    		System.out.println("get from session haha:"+session.getAttribute("haha"));
    	}else{
    		session.setAttribute("haha", "wowowo---->");
    		System.out.println("put haha--------------->");
    	}
    	
    	
        return "welcome";
    }


}
