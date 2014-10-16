package com.junjie.commons.db.client;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.junjie.commons.db.JunjieJdbcOptions;

/**
 * 客户端jdbc操作方法，客户端可以自己对这个类进行扩展和方法封装
 * @author abel.lee
 *
 */
public class JunjieJdbcTemplate implements JunjieJdbcOptions{

	
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

}
