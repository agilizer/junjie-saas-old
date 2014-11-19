package com.agilemaster.parta.service;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

import com.agilemaster.parta.entity.User;
import com.junjie.commons.db.JdbcPage;

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
    public Map<String,Object >  createUser(String username,String password);
    
    User currentUser();

    public User updateUser(User user);

    public void deleteUser(User user);
    public void genRolesAndPermissions(String username,SimpleAuthorizationInfo authorizationInfo);

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
    
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUserId(Long userId);

	Map<String, Object> findByUsernameMap(String username);

	Map<String, Object> currentUserMap();
	/**
	 * 
	 * @param permissionIds   1,2,3    
	 * @param userId 
	 * @return
	 */
	Map<String, Object> updateResource(String resourcesId,String username);
	JdbcPage listUser(int max,int offset);
	Map<String, Object> genResource(String username);
	boolean isAuth();

	public List userListSelect();
}
