package com.agilemaster.partbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.partbase.entity.User;
import com.agilemaster.partbase.service.UserService;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-29
 * <p>Version: 1.0
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
    private UserService userService;   

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(@PathVariable("id") Long id) {
        return null ;//userService.findById(1L);
    }
}
