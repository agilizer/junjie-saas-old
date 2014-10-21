package com.junjie.commons.db.server;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.junjie.commons.db.JdbcConstants;
import com.junjie.commons.db.JunjieDbOptionBean;

public class NettyMethodListener implements Processor {
	private static final Logger log = LoggerFactory
			.getLogger(NettyMethodListener.class);
	private JunjieJdbcTemplateServer junjieJdbcTemplateServer;

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
			}
			exchange.getOut().setBody(result);
		}
	}

	public JunjieJdbcTemplateServer getJunjieJdbcTemplateServer() {
		return junjieJdbcTemplateServer;
	}

	public void setJunjieJdbcTemplateServer(
			JunjieJdbcTemplateServer junjieJdbcTemplateServer) {
		this.junjieJdbcTemplateServer = junjieJdbcTemplateServer;
	}
	
}
