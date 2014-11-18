package com.agilemaster.parta.service;

import java.util.Calendar;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.dao.UserDao;
import com.agilemaster.parta.entity.User;
import com.alibaba.fastjson.JSONObject;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieHttpService;
import com.junjie.commons.utils.JunjieStaticMethod;

/**
 * 
 * @author asdtiang
 *
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;
    @Autowired
    ShareService shareService;
	@Autowired
	JunjieHttpService junjieHttpService;
	private String createUserUrl = "/api/private/v1/companyAddUser.do";
    /**
     * 创建用户
     * @param user
     */
    public Map<String,Object >  createUser(String username,String password) {
    	Map<String,Object > result = JunjieStaticMethod.genResult();
    	String requestUrl = shareService.cloudUrl()+createUserUrl;
    	User adminUser = currentUser();
    	User user = new User();
    	user.setUsername(username);
    	user.setDateCreated(Calendar.getInstance());
    	user.setLastUpdated(Calendar.getInstance());
    	user.setApart(true);
    	user.setEnabled(true);
    	JSONObject createUserResult=junjieHttpService.requestJson(requestUrl, "username="+username+"&password="
    				+password+"&adminUsername="+adminUser.getUsername());
		if(createUserResult.getBooleanValue(JunjieConstants.SUCCESS)!=true){
			result.put(JunjieConstants.ERROR_CODE, createUserResult.getInteger(JunjieConstants.ERROR_CODE));
			result.put(JunjieConstants.MSG, createUserResult.getString(JunjieConstants.MSG));
		}else{
			Long userId = createUserResult.getLong(JunjieConstants.USSER_ID);
			user.setId(userId);
			userDao.save(user);
			result.put(JunjieConstants.SUCCESS, true);
		}
        return result;
    }

    @Override
    public User updateUser(User user) {
    	userDao.update(user);
        return user;
    }

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);		
	}

	@Override
	public void changePassword(User user, String newPassword) {
		//TODO
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	@Override
	public Map<String, Object> findByUsernameMap(String username) {
		return userDao.findByUsernameMap(username);
	}

	@Override
	public User currentUser() {
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		if(principal==null){
			return null;
		}else{
			String username = (String) principal;
			return userDao.findByUsername(username);
		}
	}
	@Override
	public Map<String, Object> currentUserMap() {
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		if(principal==null){
			return null;
		}else{
			String username = (String) principal;
			return userDao.findByUsernameMap(username);
		}
	}

	@Override
	public void genRolesAndPermissions(String username,
			SimpleAuthorizationInfo authorizationInfo) {
		authorizationInfo.addRoles(userDao.genRole(username));
		authorizationInfo.addStringPermissions(userDao.genPermissions(username));
	}

	@Override
	public Map<String, Object> updateResource(String resourcesId, String username ) {
		Map<String,Object> userMap = findByUsernameMap(username);
		Map<String,Object> resultMap = JunjieStaticMethod.genResult();
		if(userMap!=null){
			userDao.updateResource(resourcesId, userMap.get("id").toString());
			resultMap.put(JunjieConstants.SUCCESS, true);
		}else{
			resultMap.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
			resultMap.put(JunjieConstants.MSG, "user not found");
		}
 		return resultMap;
	}

	@Override
	public JdbcPage listUser(int max, int offset) {
		return userDao.listUser(max, offset);
	}

	@Override
	public Map<String, Object> genResource(String username) {
		Map<String,Object> userMap = findByUsernameMap(username);
		Map<String,Object> resultMap = JunjieStaticMethod.genResult();
		if(userMap!=null){
			resultMap.put(JunjieConstants.SUCCESS, true);
			resultMap.put(JunjieConstants.DATA, userDao.genResource(userMap.get("id").toString()));
		}else{
			resultMap.put(JunjieConstants.ERROR_CODE, JunjieConstants.NOT_FOUND);
			resultMap.put(JunjieConstants.MSG, "user not found");
		}
 		return resultMap;
	}

	@Override
	public boolean isAuth() {
		Subject sub = SecurityUtils.getSubject();
		return sub.getPrincipal()==null?false:true;
	}
}
