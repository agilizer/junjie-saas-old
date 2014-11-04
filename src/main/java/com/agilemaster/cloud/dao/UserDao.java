package com.agilemaster.cloud.dao;

import com.agilemaster.cloud.entity.User;

public interface UserDao {
	String NAME_SPACE="user";
	void saveOrUpdate(User user);
	void delete(User user);
	User findByUsername(String username);
	Long count();
}
