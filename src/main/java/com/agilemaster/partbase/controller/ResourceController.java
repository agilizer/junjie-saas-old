package com.agilemaster.partbase.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.service.ResourceService;

/**
 * 
 * @author abel.lee
 *
 * 2014年11月26日
 */
@RestController
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("resource:list")
    @RequestMapping(value = "/api/v1/resource/list")
    public List<?> list() {
        return resourceService.findAll();
    }

}
