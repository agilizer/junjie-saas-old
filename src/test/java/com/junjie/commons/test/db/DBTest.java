package com.junjie.commons.test.db;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junjie.commons.db.client.JunjieJdbcTemplate;

@ContextConfiguration(locations = "classpath:spring-camel-test-db.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DBTest {
	public  final Logger log = LoggerFactory
			.getLogger(this.getClass());
	@Autowired
	private JunjieJdbcTemplate junjieJdbcTemplate;
	@Test
	public void test(){	
	
		//query for long
		assertEquals(0,junjieJdbcTemplate.queryForLong("select count(0) from sys_organization", null).intValue());
		long startTime = System.currentTimeMillis();
		assertEquals(1,junjieJdbcTemplate.update("insert into sys_organization(name,parent_id,parent_ids,available) values ('总公司insert', 0, '0/', true);",null));
		log.info("insert use Time:"+ (System.currentTimeMillis() - startTime));
		startTime = System.currentTimeMillis();
		assertEquals(1,junjieJdbcTemplate.queryForLong("select count(0) from sys_organization", null).intValue());
		log.info("query use Time:"+ (System.currentTimeMillis() - startTime));
		//query for map
		Map<String,Object> resultMap = junjieJdbcTemplate.queryForMap("select count(0) as sumOrg from sys_organization", null);
		log.info("query for map before insert:"+resultMap.get("sumorg")+resultMap.keySet());
		assertEquals(1l,resultMap.get("sumorg"));
		//insert paramsMap
		String sql = "insert into sys_organization(name,parent_id,parent_ids,available) values (:name, :parentId,:parentIds, :available);";
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		String name = "testName";
		paramsMap.put("name",name);
		paramsMap.put("parentId",0);
		paramsMap.put("parentIds","0/");
		paramsMap.put("available",true);
		assertEquals(1,junjieJdbcTemplate.update(sql,paramsMap));
		//query for map
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("name",name);
		resultMap = junjieJdbcTemplate.queryForMap("select * from sys_organization where name=:name", queryMap);
		assertEquals(name,resultMap.get("name"));
		//query for list
		List<Map<String,Object>> resultList = junjieJdbcTemplate.queryForList("select * from sys_organization", "select count(*) from sys_organization", null, 10, 0).getPageItems();
		assertEquals(2,resultList.size());
		//update 
		queryMap = new HashMap<String,Object>();
		String newName = "updateName";
		queryMap.put("name",name);
		queryMap.put("newName",newName);
		junjieJdbcTemplate.update("update sys_organization set name=:newName where name=:name", queryMap);
		queryMap = new HashMap<String,Object>();
		queryMap.put("name",newName);
		resultMap = junjieJdbcTemplate.queryForMap("select * from sys_organization where name=:name", queryMap);
		assertEquals(newName,resultMap.get("name"));
		assertEquals(2, junjieJdbcTemplate.update("delete from sys_organization;",null));
		assertEquals(0,junjieJdbcTemplate.queryForLong("select count(*) from sys_organization", null).intValue());
		log.info("drop table-->"+junjieJdbcTemplate.execute("drop table sys_organization"));
		log.info("create table-->"+junjieJdbcTemplate.execute("create table sys_organization (id bigint auto_increment,name varchar(100),parent_id bigint, parent_ids varchar(100),available bool default false,constraint pk_sys_organization primary key(id)) ;"));
		assertEquals(0,junjieJdbcTemplate.queryForLong("select count(*) from sys_organization", null).intValue());
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
