package com.agilemaster.parta.dao;

import java.util.List;
import java.util.Map;

import com.agilemaster.parta.entity.User;
import com.junjie.commons.db.JdbcPage;

public interface UserDao {
	String NAME_SPACE="user";
	void save(User user);
	void update(User user);
	void delete(User user);
	User findByUsername(String username);
	User findByUserId(Long userId);
	Long count();
	Map<String, Object> findByUsernameMap(String username);
	List<String> genRole(String username);
	List<String> genPermissions(String username);
	JdbcPage listUser(int max, int offset) ;
	List genResource(String userId);
	/**
	 * 
	 * @param resourcesId   1,2,3
	 * @param userId
	 */
	void updateResource(String resourcesId, String userId );
	List userListSelect();
}
