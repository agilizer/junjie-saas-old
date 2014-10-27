package com.junjie.commons.db.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.PaginationHelper;

public class JunjieJdbcTemplateServer  implements  JunjieJdbcOptionsServer{
	
    private JunjieJdbcAccessor  junjieJdbcAccessor;
	public Map<String,NamedParameterJdbcTemplate> jdbcTemplateMap = new ConcurrentHashMap<String,NamedParameterJdbcTemplate>();
    
	private NamedParameterJdbcTemplate genJdbcTemplateByKey(String dbInfokey){
		NamedParameterJdbcTemplate jdbcTemplate = jdbcTemplateMap.get(dbInfokey);
		if(jdbcTemplate==null){
			DataSource dataSource = junjieJdbcAccessor.genDataSource(dbInfokey);
			jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
			jdbcTemplateMap.put(dbInfokey, jdbcTemplate);
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
	public int update(String dbInfoKey, String sql, Map<String,Object> updateParams)  {
		return genJdbcTemplateByKey(dbInfoKey).update(sql, updateParams);
	}
	
	@Override
	public int execute(String dbInfoKey, final String  sql) {
		Map<String,Object> updateParams = null;
		return  genJdbcTemplateByKey(dbInfoKey).update(sql, updateParams);
	}

	public JunjieJdbcAccessor getJunjieJdbcAccessor() {
		return junjieJdbcAccessor;
	}

	public void setJunjieJdbcAccessor(JunjieJdbcAccessor junjieJdbcAccessor) {
		this.junjieJdbcAccessor = junjieJdbcAccessor;
	}
	

}
