package com.agilemaster.parta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.dao.UserDao;
import com.agilemaster.parta.entity.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.saveOrUpdate(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
    	userDao.saveOrUpdate(user);
        return user;
    }

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);		
	}

	@Override
	public void changePassword(User user, String newPassword) {
		//user.setPassword(newPassword);
		 //加密密码
        passwordHelper.encryptPassword(user);
        userDao.saveOrUpdate(user);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

  

}
