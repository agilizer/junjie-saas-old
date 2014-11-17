package com.agilemaster.parta.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.entity.User;
import com.agilemaster.parta.service.ShareService;
import com.agilemaster.parta.util.BeanToMapUtil;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.client.JunjieJdbcOptions;
@Service
public class UserDaoImpl implements UserDao {
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	@Autowired
	private JunjieJdbcOptions JunjieJdbcOptions;
	@Autowired
	private ShareService shareService;
	@Override
	public void save(User user) {
		shareService.save(user);
	}
	@Override
	public void delete(User user) {
	}
	@Override
	public User findByUsername(String username) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("username", username);
		Map<String,Object> result = JunjieJdbcOptions.queryForMap("select * from user where username=:username", queryParams);
		User user = BeanToMapUtil.convertMap(User.class,result);
		return user;
	}
	@Override
	public Map<String,Object> findByUsernameMap(String username) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("username", username);
		Map<String,Object> result = JunjieJdbcOptions.queryForMap("select * from user where username=:username", queryParams);
		return result;
	}

	@Override
	public Long count() {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		Long result = JunjieJdbcOptions.queryForLong("select count(*) from user", queryParams);
		return result;
	}

	@Override
	public void update(User user) {
		
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> genRole(String username) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("username", username);
		List<Map> resultMap = JunjieJdbcOptions.queryForList("select r.role from SYS_ROLE  r ,USER  u ,USER_ROLES u_r   where u.username=:username  and  r.id = u_r.roles and   u_r.user=u.user_Id",  queryParams);
		List<String> result = new ArrayList<String>();
		genMapToList(resultMap,result);
		return result;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<String> genPermissions(String username) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("username", username);
		List<Map> resultResource = JunjieJdbcOptions.queryForList("select r.permission from sys_resource r,USER u,USER_RESOURCES u_r where u.username=:username  and r.id=u_r.resources and u.user_id=u_r.user",  queryParams);
		List<Map> resultRole = JunjieJdbcOptions.queryForList("select r.permission from sys_resource r,SYS_ROLE_RESOURCES r_r where  r.id=r_r.resources and r_r.sys_role in " +
             " (select r.id from SYS_ROLE  r ,USER  u ,USER_ROLES u_r   where u.username=:username   and  r.id = u_r.roles and   u_r.user=u.user_Id)",  queryParams);
		List<String> result = new ArrayList<String>();
		genMapToList(ListUtils.sum(resultResource, resultRole),result);
		return result ;
	}
	/**
	 * note resoutMap just one filed 
	 * @param input
	 * @param output
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void genMapToList(List<Map> input,List output){
		for(Map map:input){
			output.addAll(map.values());
		}
	}
	@Override
	public JdbcPage listUser(int max, int offset) {
		return JunjieJdbcOptions.queryForList("select user_id,username,full_name,date_created from User", 
				"select count(*) from user", null, max, offset);
	}
	@Override
	public void updateResource(String resourcesId, String userId) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("userId", userId);
		JunjieJdbcOptions.execute("delete  from USER_RESOURCES where user=:userId");
		String[] resourceArray = resourcesId.split(",");
		for(String resource:resourceArray){
			try{
				queryParams.clear();
				queryParams.put("userId", userId);
			    queryParams.put("resources", Integer.parseInt(resource)); 
				JunjieJdbcOptions.update("insert into  USER_RESOURCES(user,resources) values(:userId,:resources)",queryParams);
			}catch(Exception e ){
				log.warn("update resource error  resource "+resource +"  userId "+userId,e);
			}
		}
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List genResource(String userId) {
		Map<String,Object> queryParams = new HashMap<String,Object>();
		queryParams.put("userId", userId);
		List<Map> resultResource = JunjieJdbcOptions.queryForList("select r.id,r.permission,r.name from sys_resource r,USER u,USER_RESOURCES u_r where u.user_id=:userId  and r.id=u_r.resources and u.user_id=u_r.user",  queryParams);
		List<Map> resultRole = JunjieJdbcOptions.queryForList("select r.id,r.permission,r.name from sys_resource r,SYS_ROLE_RESOURCES r_r where  r.id=r_r.resources and r_r.sys_role in " +
             " (select r.id from SYS_ROLE  r ,USER  u ,USER_ROLES u_r   where u.user_id=:userId   and  r.id = u_r.roles and   u_r.user=u.user_Id)",  queryParams);
		return ListUtils.sum(resultResource, resultRole) ;
	}
}
