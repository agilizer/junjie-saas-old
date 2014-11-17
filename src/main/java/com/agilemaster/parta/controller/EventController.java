package com.agilemaster.parta.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author abel.lee
 *2014年11月17日 下午4:48:34
 */
@RestController
public class EventController {
	
	  @RequestMapping("/userInfo")
	    public Map<String,Object> userInfo(HttpServletRequest request)  {
		  	
			return null;
	  }
	
}
