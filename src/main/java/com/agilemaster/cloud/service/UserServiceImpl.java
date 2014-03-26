package com.agilemaster.cloud.service;

import org.springframework.stereotype.Service;

import com.agilemaster.cloud.entity.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-29
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    public User findById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("test");
        return user;
    }
}
