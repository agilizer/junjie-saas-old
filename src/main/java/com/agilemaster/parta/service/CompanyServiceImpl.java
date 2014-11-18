package com.agilemaster.parta.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JunjieDbOptionBean;
import com.junjie.commons.db.client.JunjieJdbcTemplate;
import com.junjie.commons.utils.JunjieConstants;
import com.junjie.commons.utils.JunjieMessageSource;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service
public class CompanyServiceImpl implements CompanyService{
	private static final Logger log = LoggerFactory
			.getLogger(CompanyServiceImpl.class);
	@Autowired
	JunjieMessageSource junjieMessageSource;
	@Autowired
	private JunjieJdbcTemplate junjieJdbcTemplate;
	@Override
	public Map<String, Object> initCompany(String authKey,String userId, String username,
			String dataSourceKey) {
		Map<String,Object> result = JunjieStaticMethod.genResult();
		try {
			File sqlFile = ResourceUtils.getFile("classpath:init-ddl.sql");
			String sql = FileUtil.readAsString(sqlFile);
			List<String> dbInfokeys = new ArrayList<String>();
			dbInfokeys.add(dataSourceKey);
 			junjieJdbcTemplate.runScriptByDbInfoKeys(sql, dbInfokeys);
 			// insert into user(user_id,username)values('aaa','bbbb')
 			String  initSql = "insert into user(id,username,date_created,last_Updated)values(:userId,:username,CURDATE(),CURDATE());";
 		    initSql = initSql +"insert into sys_resource(id,name,permission,available,type)values(1,'超级管理员','*:*',true,3);";
 		    initSql = initSql +"insert into sys_resource(id,name,permission,available,type)values(2,'用户管理','userAdmin:*',true,3);";
 		    initSql = initSql +"insert into SYS_ROLE (id,role,description)values(1,'ROLE_ADMIN','超级管理员');";
 		    initSql = initSql +"insert into SYS_ROLE_RESOURCES  (sys_role,RESOURCES)values(1,1);";
 		    initSql = initSql +"insert into USER_ROLES(user,roles)values('"+userId+"',1);";
 		    initSql = initSql +"insert into BUILD_PROJECT(id,author,name,code,date_Created)values(1,'"+userId+"','默认项目','default',CURDATE());";
 		   initSql = initSql +"insert into event_type  (id,name,DESCRIPTION)values(1,'日常事务',''),(2,'会议',''),(3,'通知','');";
 			JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
 			optionBean.setDbInfoKey(dataSourceKey);
 			Map<String, Object> headers = new HashMap<String, Object>();
 			Map<String, Object> queryParams = new HashMap<String, Object>();
 			queryParams.put("userId", userId);
 			queryParams.put("username", username);
 			queryParams.put("buildAuthor", userId);
 			headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
 			optionBean.setOption( JdbcConstants.UPDATE);
 			optionBean.setSql(initSql);
 			optionBean.setParams(headers);
 			junjieJdbcTemplate.execute(optionBean);
 			result.put(JunjieConstants.SUCCESS,true);
		} catch (FileNotFoundException e) {
			log.error("init company Sql file not found!!!",e);
			result.put(JunjieConstants.ERROR_CODE, JunjieConstants.FILE_NOT_FOUND);
			result.put(JunjieConstants.MSG, junjieMessageSource.getMessage("init.company.sql.not.found", null, null));
		} catch (IOException e) {
			log.error("init company Sql file IOException",e);
			result.put(JunjieConstants.ERROR_CODE, JunjieConstants.FILE_NOT_FOUND);
			result.put(JunjieConstants.MSG, junjieMessageSource.getMessage("init.company.sql.IOException", null, null));
		}  
		return result;
	}

}
