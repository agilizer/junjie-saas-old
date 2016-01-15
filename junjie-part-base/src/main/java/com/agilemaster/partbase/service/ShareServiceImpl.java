package com.agilemaster.partbase.service;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.agilemaster.partbase.util.BeanToMapUtil;
import com.junjie.commons.db.client.JunjieJdbcOptions;
import com.junjie.commons.utils.JunjieCounter;
import com.junjie.commons.utils.JunjieStaticMethod;

@Service
public class ShareServiceImpl implements ShareService,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3886427808979402462L;
	private static final Logger log = LoggerFactory
			.getLogger(ShareServiceImpl.class);
	@Autowired
	private JunjieJdbcOptions junjieJdbcOptions;
	@Autowired
	private JunjieCounter junjieCounter;
	@Value(value = "${junjie.cloud.url}")
	private String cloudUrl = "";
	@Value(value = "${junjie.plugin.base.url}")
	private String pluginUrl = "";

	@PostConstruct
	public void init() {
		log.info("------------------------->cloudUrl {}", cloudUrl);
		log.info("------------------------->pluginUrl {}", pluginUrl);
	}

	
	public String getPluginUrl() {
		return pluginUrl;
	}

	private String genTableName(Class<?> clazz) {
		String className = clazz.getSimpleName();
		className = Character.toLowerCase(className.charAt(0))
				+ className.substring(1);
		return BeanToMapUtil.propertyToField(className);
	}

	private String genIdCountName(Class<?> clazz) {
		String className = clazz.getSimpleName();
		return className;
	}

	@Override
	public void save(Object domain) {
		save(domain, null);
	}

	@Override
	public Long saveAutoGenId(Object domain) {
		return saveAutoGenId(domain, null);
	}

	@Override
	public void update(Object object) {

	}

	@Override
	public String cloudUrl() {
		return cloudUrl;
	}

	@Override
	public Map<String, Object> findById(Class<?> clazz, Long id) {
		StringBuffer sql = new StringBuffer("select * from   ");
		String tableName = genTableName(clazz);
		sql.append(tableName).append(" where id=:objId ");
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("objId", id);
		return junjieJdbcOptions.queryForMap(sql.toString(), queryParams);
	}


	@Override
	public <T> T findObjectById(Class<T> clazz, Long id) {
		Map<String, Object> map = findById(clazz,id);
		if(map!=null){
			return BeanToMapUtil.convertMap(clazz, map);
		}else{
			return null;
		}
	}
	

	@Override
	public void save(Object domain, Map<String, Object> objectInsert) {
		String tableName = genTableName(domain.getClass());
		Map<String, Object> insertMap = BeanToMapUtil.convertBean(domain);
		if (null != objectInsert) {
			insertMap.putAll(objectInsert);
		}
		executeInsert(tableName, insertMap);
	}

	
	private void executeInsert(String tableName, Map<String, Object> insertMap) {
		StringBuffer sql = new StringBuffer("insert into ");
		sql.append(tableName);
		StringBuffer fileSql = new StringBuffer("(");
		StringBuffer valuesSql = new StringBuffer("(");
		Object value;
		List<String> sqlList = new ArrayList<String>();
		Method getIdMethod = null;
		Long idLong;
		StringBuffer listSqlTemp = null;
		List<String> removeKey = new ArrayList<String>();
		for (String key : insertMap.keySet()) {
			value = insertMap.get(key);
			if (value instanceof List) {// list property
				List<?> valueList = (List<?>) value;
				if (valueList.size() > 0) {
					Class<? extends Object> clazzListObject = valueList.get(0)
							.getClass();
					try {
						getIdMethod = clazzListObject.getMethod("getId");
						listSqlTemp = new StringBuffer("insert into ").append(tableName).append("_").append(key);
						listSqlTemp.append("(" + tableName + "," + key + ")").append("values");
						for (Object object : valueList) {
							idLong = (Long) getIdMethod.invoke(object);
							listSqlTemp.append("(" + insertMap.get("id") + ","+ idLong + "),");
						}
						sqlList.add(listSqlTemp.subSequence(0,listSqlTemp.length() - 1).toString()+ ";");
					} catch (Exception e) {
						log.error("valueList  gen sql warn---> key :" + key
								+ " value:", value, e);
					} finally {
						removeKey.add(key);
					}
				}
			} else {// object property
				if (!JunjieStaticMethod.isBaseObject(value)) {//非基本类型需要生成id value
					try {
						getIdMethod = value.getClass().getMethod("getId");
						idLong = (Long) getIdMethod.invoke(value);
						insertMap.put(key, idLong);
					} catch (Exception e) {
						log.error("value gen sql warn---> key :" + key
								+ " value:", value, e);
					}
				}
				fileSql.append(key).append(",");
				valuesSql.append(":").append(key).append(",");
			}
		}
		for(String keyRemoveTemp:removeKey){
			insertMap.remove(keyRemoveTemp);
		}
		sql = sql.append(fileSql.subSequence(0, fileSql.length() - 1)).append(")values").append(valuesSql.subSequence(0, valuesSql.length() - 1)).append(");");
		junjieJdbcOptions.update(sql.toString(), insertMap);
		// execute list sql
		if (sqlList.size() > 0) {
			String insertListSql = "";
			for (String sqlTemp : sqlList) {
				insertListSql = insertListSql + sqlTemp;
			}
			junjieJdbcOptions.update(insertListSql, null);
		}
	}

	@Override
	public Long saveAutoGenId(Object domain, Map<String, Object> objectInsert) {
		Class<? extends Object> clazz = domain.getClass();
		String tableName = genTableName(domain.getClass());
		Map<String, Object> insertMap = BeanToMapUtil.convertBean(domain);
		if (null != objectInsert) {
			insertMap.putAll(objectInsert);
		}
		String counterName = genIdCountName(clazz);
		Long id = junjieCounter.genUniqueLong(counterName);
		insertMap.put("id", id);
		executeInsert(tableName, insertMap);
		return id;
	}


}
