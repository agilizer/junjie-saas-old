package com.agilemaster.partbase.service;
import java.util.Map;


public interface ShareService {
	/**
	 * 只能存储插入对象数据,List属性不能插入.
	 * @param domain
	 */
	void save(Object domain);
	/**
	 * 只能存储插入对象数据,List属性不能插入.
	 * @param domain
	 * @param objectInsert
	 */
	void save(Object domain,Map<String,Object> objectInsert);
	void update(Object object);
	Map<String,Object> findById(Class<?>  clazz,int id);
	String cloudUrl();
	public String getPluginUrl();
	/**
	 * 只能存储插入对象数据,List属性不能插入.
	 * @param domain
	 * @return
	 */
	Long saveAutoGenId(Object domain);
	/**
	 * 只能存储插入对象数据,List属性不能插入.
	 * @param domain
	 * @param objectInsert
	 * @return
	 */
	Long saveAutoGenId(Object domain,Map<String,Object> objectInsert);
}
