package com.agilemaster.parta.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.parta.service.HomeService;
import com.agilemaster.parta.service.RoleService;
import com.agilemaster.parta.service.UserService;
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

    @Autowired
    private RoleService roleService;
    @Autowired
	private HomeService  homeService;
    
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
    @RequiresPermissions("user:create")
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
//
//    @RequiresPermissions("user:create")
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(User user, RedirectAttributes redirectAttributes) {
//        userService.createUser(user);
//        redirectAttributes.addFlashAttribute("msg", "新增成功");
//        return "redirect:/user";
//    }
//
//    @RequiresPermissions("user:update")
//    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
//    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
//        setCommonData(model);
//        model.addAttribute("user", userService.findOne(id));
//        model.addAttribute("op", "修改");
//        return "user/edit";
//    }
//
//    @RequiresPermissions("user:update")
//    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
//    public String update(User user, RedirectAttributes redirectAttributes) {
//        userService.updateUser(user);
//        redirectAttributes.addFlashAttribute("msg", "修改成功");
//        return "redirect:/user";
//    }
//
//    @RequiresPermissions("user:delete")
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
//    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
//        setCommonData(model);
//        model.addAttribute("user", userService.findOne(id));
//        model.addAttribute("op", "删除");
//        return "user/edit";
//    }
//
//    @RequiresPermissions("user:delete")
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
//    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        userService.deleteUser(id);
//        redirectAttributes.addFlashAttribute("msg", "删除成功");
//        return "redirect:/user";
//    }
//
//
//    @RequiresPermissions("user:update")
//    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
//    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.findOne(id));
//        model.addAttribute("op", "修改密码");
//        return "user/changePassword";
//    }
//
//    @RequiresPermissions("user:update")
//    @RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
//    public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
//        userService.changePassword(id, newPassword);
//        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
//        return "redirect:/user";
//    }
//
//    private void setCommonData(Model model) {
//        model.addAttribute("roleList", roleService.findAll());
//    }
}
