package com.junjie.commons.db.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JdbcPage;
import com.junjie.commons.db.JunjieDbOptionBean;

/**
 * 客户端jdbc操作方法，客户端可以自己对这个类进行扩展和方法封装
 * @author abel.lee
 */
@SuppressWarnings("unchecked")
public class JunjieJdbcTemplate implements JunjieJdbcOptions{
	private static final Logger log = LoggerFactory
			.getLogger(JunjieJdbcTemplate.class);
	private JunjieJdbcRequest junjieJdbcRequest;
	
	@Override
	public Long queryForLong(String sql, Map<String, Object> queryParams) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.QUERY_FOR_LONG);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result =junjieJdbcRequest.sendJdbcMessageSync(optionBean);
		return (Long) result;
	}
	@Override
	public JdbcPage queryForList(String sql, String countSql,Map<String, Object> queryParams,
			int max, int offset) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_COUNT_SQL,countSql);
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		headers.put(JdbcConstants.KEY_MAX,max);
		headers.put(JdbcConstants.KEY_OFFSET,offset);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.QUERY_FOR_LIST);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = junjieJdbcRequest.sendJdbcMessageSync( optionBean);
		return (JdbcPage) result;
	}
	
	@Override
	public List queryForList(String sql, Map<String, Object> queryParams) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.QUERY_FOR_LIST_ALL);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = junjieJdbcRequest.sendJdbcMessageSync( optionBean);
		return (List) result;
	}


	@Override
	public Map<String, Object> queryForMap(String sql,
			Map<String, Object> queryParams) throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.QUERY_FOR_MAP);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result =junjieJdbcRequest.sendJdbcMessageSync(optionBean);
		return (Map<String, Object>) result;
	}

	@Override
	public int update(String sql, Map<String, Object> queryParams){
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.UPDATE);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result =junjieJdbcRequest.sendJdbcMessageSync( optionBean);
		return (int)result ;
	}
	@Override
	public int execute(String sql) {
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.EXECUTE);
		Object result = junjieJdbcRequest.sendJdbcMessageSync(optionBean);
		return (int) result;
	}
	public JunjieJdbcRequest getJunjieJdbcRequest() {
		return junjieJdbcRequest;
	}
	public void setJunjieJdbcRequest(JunjieJdbcRequest junjieJdbcRequest) {
		this.junjieJdbcRequest = junjieJdbcRequest;
	}
	@Override
	public List<Integer> updateByDbInfoKeys(String sql, List<String> dbInfoKeys) {
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_EXECUTE_KEYS,dbInfoKeys);
		optionBean.setOption( JdbcConstants.EXECUTE_KEYS);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = junjieJdbcRequest.sendJdbcMessageSync(optionBean);
		return (List<Integer>) result;
	}
	@Override
	public List<Integer> runScriptByDbInfoKeys(String sql,
			List<String> dbInfoKeys) {
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_EXECUTE_KEYS,dbInfoKeys);
		optionBean.setOption( JdbcConstants.RUN_SCRIPT);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		Object result = junjieJdbcRequest.sendJdbcMessageSync(optionBean);
		return (List<Integer>) result;
	}
	@Override
	public Object execute(JunjieDbOptionBean optionBean) {
		return junjieJdbcRequest.sendJdbcMessageSync(optionBean);
	}
	
}
