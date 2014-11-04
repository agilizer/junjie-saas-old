package com.agilemaster.cloud.service;

import java.util.List;
import java.util.Set;

import com.agilemaster.cloud.entity.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(User user, String newPassword);
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);
}
