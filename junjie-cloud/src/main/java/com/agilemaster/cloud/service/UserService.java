package com.agilemaster.cloud.service;

import com.agilemaster.cloud.entity.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-29
 * <p>Version: 1.0
 */
public interface UserService {

    public User findById(Long id);
}
