package com.junjie.commons.db.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.springframework.dao.DataAccessException;

import com.junjie.commons.db.JdbcPage;

/**
 * 客户端jdbc操作方法，客户端可以自己对这个类进行扩展和方法封装
 * @author abel.lee
 */
@SuppressWarnings("unchecked")
public class JunjieJdbcTemplate implements JunjieJdbcOptions{

	private String endpointUri;
	private ProducerTemplate producerTemplate;
	private DataSourceSelecter dataSourceSelecter;
	
	private void genDataSourceKey(Map<String, Object>  headers){
		headers.put("dbInfoKey", dataSourceSelecter.getCurrentDataSourceKey());
	}
	@Override
	public JdbcPage queryForList(String sql, Map<String, Object> queryParams,
			int max, int offset) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("queryParams",queryParams);
		headers.put("max",max);
		headers.put("offset",offset);
		genDataSourceKey(headers);
		return (JdbcPage) producerTemplate.requestBodyAndHeaders(endpointUri, sql,headers);
	}


	@Override
	public Map<String, Object> queryForMap(String sql,
			Map<String, Object> queryParams) throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("queryParams",queryParams);
		genDataSourceKey(headers);
		return (Map<String, Object>) producerTemplate.requestBodyAndHeaders(endpointUri, sql,headers);
	}

	@Override
	public int update(String sql, Map<String, Object> queryParams)
			throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("queryParams",queryParams);
		genDataSourceKey(headers);
		return (int) producerTemplate.requestBodyAndHeaders(endpointUri, sql,headers);
	}
	@Override
	public boolean execute(String sql) {
		Map<String, Object> headers = new HashMap<String, Object>();
		genDataSourceKey(headers);
		return (boolean) producerTemplate.requestBodyAndHeaders(endpointUri, sql,headers);
	}

	public String getEndpointUri() {
		return endpointUri;
	}

	public void setEndpointUri(String endpointUri) {
		this.endpointUri = endpointUri;
	}

	public ProducerTemplate getProducerTemplate() {
		return producerTemplate;
	}

	public void setProducerTemplate(ProducerTemplate producerTemplate) {
		this.producerTemplate = producerTemplate;
	}


	public DataSourceSelecter getDataSourceSelecter() {
		return dataSourceSelecter;
	}


	public void setDataSourceSelecter(DataSourceSelecter dataSourceSelecter) {
		this.dataSourceSelecter = dataSourceSelecter;
	}
	

}
