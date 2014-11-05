package com.agilemaster.parta.controller.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agilemaster.parta.StaticMethod;
import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JunjieDbOptionBean;
import com.junjie.commons.db.client.JunjieJdbcTemplate;

@RestController("/api/v1")
public class CompanyApiController {
	@Autowired
	private JunjieJdbcTemplate junjieJdbcTemplate;
	private String intSql;
	@PostConstruct
	public void init(){
		
	}	
	@RequestMapping("/api/v1/initPlugin")
	public Map<String,Object> initPlugin(@RequestParam(required = true) String key,
    		@RequestParam(required = true)String userId,
    		@RequestParam(required = true)String username,@RequestParam(required = true) String dataSourceKey){
		Map<String,Object> result = StaticMethod.genResult();
		try {
			File sqlFile = ResourceUtils.getFile("classpath:init-ddl.sql");
			String sql = FileUtil.readAsString(sqlFile);
			List<String> dbInfokeys = new ArrayList<String>();
			dbInfokeys.add(dataSourceKey);
 			junjieJdbcTemplate.runScriptByDbInfoKeys(sql, dbInfokeys);
 			// insert into user(user_id,username)values('aaa','bbbb')
 			String  initSql = "insert into user(user_id,username)values(:userId,:username)";
 			JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
 			optionBean.setDbInfoKey(dataSourceKey);
 			Map<String, Object> headers = new HashMap<String, Object>();
 			Map<String, Object> queryParams = new HashMap<String, Object>();
 			queryParams.put("userId", userId);
 			queryParams.put("username", username);
 			headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
 			optionBean.setOption( JdbcConstants.UPDATE);
 			optionBean.setSql(initSql);
 			optionBean.setParams(headers);
 			junjieJdbcTemplate.execute(optionBean);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return result;
		
	}
	

}
