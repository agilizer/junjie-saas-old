package com.junjie.commons.db.server;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.junjie.commons.db.JdbcPage;

/**
 * 数据库操作接口，server使用
 * @author abel.lee
 *
 */
public interface JunjieJdbcOptionsServer{
	/**
	 * 
	 * @param dbInfoKey
	 * @param sql
	 * @param queryMap
	 * @return
	 */
	Long queryForCount(String dbInfoKey,String sql,Map<String,Object> queryMap);
	/**
	 * 
	 * @param dbInfoKey
	 * @param sql
	 * @param queryMap
	 * @return
	 * @throws DataAccessException
	 */
	Map<String, Object> queryForMap(String dbInfoKey,String sql, Map<String ,Object> queryMap);
	/**
	 * 
	 * @param dbInfoKey
	 * @param countSql
	 * @param sqlFetch
	 * @param queryMap
	 * @param max
	 * @param offset
	 * @return
	 */
	JdbcPage queryForList(String dbInfoKey,String countSql,String sqlFetch, Map<String,Object> queryMap,int max,int offset) ;
	
	/**
	 * 
	 * @param dbInfoKey
	 * @param sql
	 * @param updateParams
	 * @return
	 * @throws DataAccessException
	 */
	int update(String dbInfoKey,String sql, Map<String,Object> updateParams) ;
	
	/**
	 * 执行sql语句
	 * @param dbInfoKey
	 * @param sql
	 * @return
	 */
	int  execute(String dbInfoKey, String sql);
	
	/**
	 * 根据数据库信息key，更新schema
	 * @param sql
	 * @param dbInfoKeys
	 * @return
	 */
	public List<Integer> updateByDbInfoKeys(String sql, List<String> dbInfoKeys);
	
	public List<Integer> runScriptByDbInfoKeys(String sql, List<String> dbInfoKeys);
}
