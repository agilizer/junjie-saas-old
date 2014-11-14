package com.agilemaster.parta.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.util.BeanToMapUtil;
import com.junjie.commons.db.client.JunjieJdbcTemplate;

@Service
public class ShareServiceImpl implements ShareService{
	private static final Logger log = LoggerFactory
			.getLogger(ShareServiceImpl.class);
	@Autowired
	private JunjieJdbcTemplate junjieJdbcTemplate;
	@Value(value = "${junjie.cloud.url}")
	private String cloudUrl = "";
	@PostConstruct
	public void init(){
		log.info("------------------------->cloudUrl {}",cloudUrl);
	}
	@Override
	public void save(Object domain) {
		StringBuffer  sql = new StringBuffer("insert into ");
		String className = domain.getClass().getSimpleName();
		className = Character.toLowerCase(className.charAt(0))+className.substring(1);
		String tableName = BeanToMapUtil.propertyToField(className);
		sql.append(tableName);
		Map<String,Object> insertMap =  BeanToMapUtil.convertBean(domain);
		StringBuffer fileSql = new StringBuffer("(");
		StringBuffer valuesSql = new StringBuffer("(");
		for(String key:insertMap.keySet()){
			fileSql.append(key).append(",");
			valuesSql.append(":").append(key).append(",");
		}
		sql = sql.append(fileSql.subSequence(0, fileSql.length()-1)).append(")values").append(valuesSql.subSequence(0, valuesSql.length()-1)).append(");");
		junjieJdbcTemplate.update(sql.toString(), insertMap);
	}

	@Override
	public void update(Object object) {
		
	}

	@Override
	public String cloudUrl() {
		return cloudUrl;
	}

}
