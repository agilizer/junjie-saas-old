package com.junjie.commons.db.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.aspectj.util.FileUtil;
import org.h2.store.fs.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.PaginationHelper;

public class JunjieJdbcTemplateServer  implements  JunjieJdbcOptionsServer{
	private static final Logger log = LoggerFactory
			.getLogger(JunjieJdbcTemplateServer.class);
    private JunjieJdbcAccessor  junjieJdbcAccessor;
	public Map<String,NamedParameterJdbcTemplate> jdbcTemplateMap = new ConcurrentHashMap<String,NamedParameterJdbcTemplate>();
    
	private NamedParameterJdbcTemplate genJdbcTemplateByKey(String dbInfokey){
		NamedParameterJdbcTemplate jdbcTemplate = jdbcTemplateMap.get(dbInfokey);
		if(jdbcTemplate==null){
			DataSource dataSource = junjieJdbcAccessor.genDataSource(dbInfokey);
			if(dataSource!=null){
				jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
				jdbcTemplateMap.put(dbInfokey, jdbcTemplate);
			}else{
				log.warn("dbInfoKey :"+dbInfokey+" not exist!!!!!!!");
			}
		}
		return jdbcTemplate;
	}
	@Override
	public Long queryForCount(String dbInfoKey, String sql,
			Map<String, Object> queryMap) {
		return genJdbcTemplateByKey(dbInfoKey).queryForObject(sql, queryMap,Long.class);
	}
	
	@Override
	public Map<String, Object> queryForMap(String dbInfoKey, String sql,
			Map<String,Object> queryMap)  {
		List<Map<String,Object>> resultList = genJdbcTemplateByKey(dbInfoKey).queryForList(sql, queryMap);
		Map<String, Object> result = null;
		if(resultList.size()==1){
			result = resultList.get(0);
		}
		return result;
	}

	@Override
	public JdbcPage queryForList(String dbInfoKey, String countSql,String sqlFetch,
			Map<String,Object> queryMap, int max,int offset)
			 {
		return PaginationHelper.fetchPage(genJdbcTemplateByKey(dbInfoKey), countSql, sqlFetch, queryMap, max, offset);
	}
	@Override
	public List queryForList(String dbInfoKey, String sql,
			Map<String, Object> queryMap) {
		return genJdbcTemplateByKey(dbInfoKey).queryForList(sql, queryMap);
	}

	@Override
	public int update(String dbInfoKey, String sql, Map<String,Object> updateParams)  {
		return genJdbcTemplateByKey(dbInfoKey).update(sql, updateParams);
	}
	
	@Override
	public int execute(String dbInfoKey, final String  sql) {
		Map<String,Object> queryMap = null;
		 return genJdbcTemplateByKey(dbInfoKey).update(sql, queryMap);
	}
	@Override
	public List<Integer> updateByDbInfoKeys(String sql, List<String> dbInfoKeys) {
		List<Integer> resultList = new ArrayList<Integer>();
		Map<String,Object> queryMap = null;
		NamedParameterJdbcTemplate jdbcTemplate = null;
		for(String dbInfoKey:dbInfoKeys){
			jdbcTemplate = genJdbcTemplateByKey(dbInfoKey);
			if(jdbcTemplate!=null){
				resultList.add(genJdbcTemplateByKey(dbInfoKey).update(sql, queryMap));
			}else{
				resultList.add(-1);
			}
		}
		return resultList;
	}
	@Override
	public List<Integer> runScriptByDbInfoKeys(String sql,
			List<String> dbInfoKeys) {
		List<Integer> resultList = new ArrayList<Integer>();
		Map<String,Object> queryMap = null;
		NamedParameterJdbcTemplate jdbcTemplate = null;
		String fileName = "./"+System.currentTimeMillis()+".sql";
		FileUtils.createFile(fileName);
		File file = new File(fileName);
		FileUtil.writeAsString(file, sql);
		String script = " RUNSCRIPT　from  '"+file.getAbsolutePath()+"'";
		for(String dbInfoKey:dbInfoKeys){
			jdbcTemplate = genJdbcTemplateByKey(dbInfoKey);
			if(jdbcTemplate!=null){
				resultList.add(genJdbcTemplateByKey(dbInfoKey).update(script, queryMap));
			}else{
				resultList.add(-1);
			}
		}
		FileUtils.delete(file.getAbsolutePath());
		return resultList;
	}


	public JunjieJdbcAccessor getJunjieJdbcAccessor() {
		return junjieJdbcAccessor;
	}

	public void setJunjieJdbcAccessor(JunjieJdbcAccessor junjieJdbcAccessor) {
		this.junjieJdbcAccessor = junjieJdbcAccessor;
	}
	
	
}
