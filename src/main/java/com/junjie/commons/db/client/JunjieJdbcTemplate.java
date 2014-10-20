package com.junjie.commons.db.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
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

	private String endpointUri;
	private ProducerTemplate producerTemplate;
	private DataSourceSelecter dataSourceSelecter;
	
	private void genDataSourceKey(Map<String, Object>  headers){
		headers.put(JdbcConstants.DB_INFO_KEY, dataSourceSelecter.getCurrentDataSourceKey());
	}
	private void genDataSourceKey(JunjieDbOptionBean  optionBean){
		optionBean.setDbInfoKey(dataSourceSelecter.getCurrentDataSourceKey());
	}
	@Override
	public JdbcPage queryForList(String sql, String countSql,Map<String, Object> queryParams,
			int max, int offset) {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_COUNT_SQL,countSql);
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		headers.put(JdbcConstants.KEY_MAX,max);
		headers.put(JdbcConstants.KEY_OFFSET,offset);
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		genDataSourceKey(optionBean);
		optionBean.setOption( JdbcConstants.QUERY_FOR_LIST);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		return (JdbcPage) producerTemplate.requestBody(endpointUri, optionBean);
	}


	@Override
	public Map<String, Object> queryForMap(String sql,
			Map<String, Object> queryParams) throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		genDataSourceKey(optionBean);
		optionBean.setOption( JdbcConstants.QUERY_FOR_MAP);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		return (Map<String, Object>) producerTemplate.requestBody(endpointUri, optionBean);
	}

	@Override
	public int update(String sql, Map<String, Object> queryParams)
			throws DataAccessException {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(JdbcConstants.KEY_QUERY_PARAMS,queryParams);
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		genDataSourceKey(optionBean);
		optionBean.setOption( JdbcConstants.UPDATE);
		optionBean.setSql(sql);
		optionBean.setParams(headers);
		return (int) producerTemplate.requestBody(endpointUri, optionBean);
	}
	@Override
	public boolean execute(String sql) {
		JunjieDbOptionBean optionBean = new JunjieDbOptionBean();
		optionBean.setOption( JdbcConstants.EXECUTE);
		genDataSourceKey(optionBean);
		return (boolean) producerTemplate.requestBody(endpointUri, optionBean);
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
