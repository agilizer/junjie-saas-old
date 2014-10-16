package com.junjie.commons.db.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.junjie.commons.db.JunjieJdbcOptions;

public class JunjieJdbcTemplateServer  implements  JunjieJdbcOptions{
	
    private JunjieJdbcAccessor  junjieJdbcAccessor;
	public Map<String,JdbcTemplate> jdbcTemplateMap = new ConcurrentHashMap<String,JdbcTemplate>();
    
	@Override
	public Map<String, Object> queryForMap(String sql)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql,
			Map<String, Integer> pageParams) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> queryForMap(String sql, Object[] args,
			int[] argTypes) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> queryForList(String sql, Object[] args,
			int[] argTypes, Map<String, Integer> pageParams)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String sql, Object[] args, int[] argTypes)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	public JunjieJdbcAccessor getJunjieJdbcAccessor() {
		return junjieJdbcAccessor;
	}

	public void setJunjieJdbcAccessor(JunjieJdbcAccessor junjieJdbcAccessor) {
		this.junjieJdbcAccessor = junjieJdbcAccessor;
	}
	

}
