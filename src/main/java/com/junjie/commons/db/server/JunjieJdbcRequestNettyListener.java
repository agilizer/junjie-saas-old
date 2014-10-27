package com.junjie.commons.db.server;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JunjieDbOptionBean;

public class JunjieJdbcRequestNettyListener implements Processor, JunjieJdbcRequestListener{
	private static final Logger log = LoggerFactory
			.getLogger(JunjieJdbcRequestNettyListener.class);
	private JunjieJdbcTemplateServer junjieJdbcTemplateServer;
	private Map<Integer, JunjieJdbcRequest> requestMap = null ;
	
	@SuppressWarnings("unchecked")
	public void process(Exchange exchange) {
		JunjieDbOptionBean optionBean = (JunjieDbOptionBean) exchange.getIn()
				.getBody();
		String sql = optionBean.getSql();
		Map<String, Object> params = optionBean.getParams();
		int option = optionBean.getOption();
		if (null == sql || option == 0) {
			log.error("sql or option can not be null");
			throw new IllegalArgumentException("sql or option can not be null");
		} else {
			String dbInfoKey = optionBean.getDbInfoKey();
			Object result = null;
			switch (option) {
			case (JdbcConstants.QUERY_FOR_LIST): {
				String countSql = (String) params
						.get(JdbcConstants.KEY_COUNT_SQL);
				Map<String, Object> queryParams = (Map<String, Object>) params
						.get(JdbcConstants.KEY_QUERY_PARAMS);
				int max = (int) params.get(JdbcConstants.KEY_MAX);
				int offset = (int) params.get(JdbcConstants.KEY_OFFSET);
				result = junjieJdbcTemplateServer.queryForList(dbInfoKey,
						countSql, sql, queryParams, max, offset);
				break;
			}
			case (JdbcConstants.QUERY_FOR_MAP): {
				Map<String, Object> queryParams = (Map<String, Object>) params
						.get(JdbcConstants.KEY_QUERY_PARAMS);
				result = junjieJdbcTemplateServer.queryForMap(dbInfoKey, sql,
						queryParams);
				break;
			}
			case (JdbcConstants.QUERY_FOR_LONG): {
				Map<String, Object> queryParams = (Map<String, Object>) params
						.get(JdbcConstants.KEY_QUERY_PARAMS);
				result = junjieJdbcTemplateServer.queryForCount(dbInfoKey, sql,
						queryParams);
				break;
			}
			case (JdbcConstants.UPDATE): {
				Map<String, Object> updateParams = (Map<String, Object>) params
						.get(JdbcConstants.KEY_QUERY_PARAMS);
				result = junjieJdbcTemplateServer.update(dbInfoKey, sql,
						updateParams);
				break;
			}
			case (JdbcConstants.EXECUTE): {
				result = junjieJdbcTemplateServer.execute(dbInfoKey, sql);
				break;
			}
			default:
				 JunjieJdbcRequest method = requestMap.get(option+"");
				 if(method!=null){
					 result = method.doWith(optionBean);
				 }else{
					 log.warn("no request method to doWith key:"+option+" found!!!!!");
				 }
			}
			if(result == null){
				exchange.getOut().setBody(JdbcConstants.NULL);
			}else{
				exchange.getOut().setBody(result);
			}
			
		}
	}
	
	@Override
	public Map<Integer, JunjieJdbcRequest> getMethodsMap() {
		return requestMap;
	}

	@Override
	public synchronized void addMethod(Integer key, JunjieJdbcRequest method) {
		if(requestMap.get(key)!=null){
			log.error("junjieJdbcRequest key:"+key+" is already exist!!!!");
		}else{
			requestMap.put(key, method);
		}
	}

	public JunjieJdbcTemplateServer getJunjieJdbcTemplateServer() {
		return junjieJdbcTemplateServer;
	}

	public void setJunjieJdbcTemplateServer(
			JunjieJdbcTemplateServer junjieJdbcTemplateServer) {
		this.junjieJdbcTemplateServer = junjieJdbcTemplateServer;
	}

	public Map<Integer, JunjieJdbcRequest> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<Integer, JunjieJdbcRequest> requestMap) {
		this.requestMap = requestMap;
	}

	

}
