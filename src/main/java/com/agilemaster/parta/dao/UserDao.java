package com.agilemaster.parta.dao;

import com.agilemaster.parta.entity.User;

public interface UserDao {
	String NAME_SPACE="user";
	void saveOrUpdate(User user);
	void delete(User user);
	User findByUsername(String username);
	Long count();
}
