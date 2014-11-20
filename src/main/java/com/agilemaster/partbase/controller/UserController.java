package com.agilemaster.partbase.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.service.UserService;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieStaticMethod;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
@RestController("")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    
    @RequiresUser
    @RequestMapping("/api/v1/currentUser")
	public Map<String,Object> currentUser() {
    	Map<String,Object> result = JunjieStaticMethod.genResult();
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		if(principal==null){
			result.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_LOGIN);
			result.put(JunjieConstants.MSG, "not login!");
			log.info("--->principal   is null  session.id:"+subject.getSession().getId() );
		}else{
			String username = (String) principal;
			Session session  = subject.getSession();
			log.info("--->principal   is  {}  session.id:"+subject.getSession().getId() ,username);
	    	log.info("dataSourceKey-->{}",session.getAttribute(JunjieConstants.DATA_SOURCE_KEY));
	    	result.put(JunjieConstants.SUCCESS, true);
	    	result.put(JunjieConstants.DATA, userService.findByUsernameMap(username));
		}
		return result;
	}
    @RequiresPermissions("userAdmin:create")
    @RequestMapping(value = "/api/v1/companyAddUser", method = RequestMethod.GET)
    public Map<String, Object> companyAddUser(String username,String password) {
    	Map<String,Object> result = JunjieStaticMethod.genResult();
    	if(username==null||username.trim().equals("")){
    		result.put(JunjieConstants.MSG, "用户名不能为空！");
    		result.put(JunjieConstants.ERROR_CODE, JunjieConstants.REGISTER_NULL_USERNAME);
    		return result;
    	}
    	if(password==null||password.length()<6||password.length()>30){
			result.put(JunjieConstants.MSG, "密码需要６－３０位！");
    		result.put(JunjieConstants.ERROR_CODE, JunjieConstants.REG_PWD_WRONG);
    		return result;
		}
    	result =  userService.createUser(username, password);
        return result;
    }
    @RequiresPermissions("userAdmin:create")
    @RequestMapping(value = "/api/v1/changeResources")
    public Map<String, Object> changeResources(String resourcesId,String username) {
    	Map<String,Object> result = JunjieStaticMethod.genResult();
    	result =  userService.updateResource(resourcesId, username);
        return result;
    }
    @RequiresPermissions("userAdmin:create")
    @RequestMapping(value = "/api/v1/getResources")
    public Map<String, Object> genResources(String username) {
    	Map<String,Object> result = JunjieStaticMethod.genResult();
    	result = userService.genResource(username);
        return result;
    }
    
    @RequiresPermissions("*:*")
    @RequestMapping(value = "/api/v1/userList")
    public JdbcPage userList(int max,int offset) {
        return userService.listUser(max, offset);
    }
    @RequiresUser
    @RequestMapping(value = "/api/v1/userListSelect")
    public List userListSelect() {
        return userService.userListSelect();
    }
}
