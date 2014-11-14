package com.agilemaster.parta.dao;

import java.util.List;
import java.util.Map;

import com.agilemaster.parta.entity.User;

public interface UserDao {
	String NAME_SPACE="user";
	void save(User user);
	void update(User user);
	void delete(User user);
	User findByUsername(String username);
	Long count();
	Map<String, Object> findByUsernameMap(String username);
	List<String> genRole(String username);
	List<String> genPermissions(String username);
}
