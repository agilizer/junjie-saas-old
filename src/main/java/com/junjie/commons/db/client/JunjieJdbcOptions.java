package com.junjie.commons.db.client;

import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;

import com.junjie.commons.db.JdbcPage;

/**
 * 数据库操作接口，client使用,主要用对和服务器交互数据时使用
 * @author abel.lee
 *
 */
public interface JunjieJdbcOptions{

	/**
	 * 根据sql 和namedParam查询分页数据,max和offset为0时表示取所有数据
	 * @param sql
	 * @param queryParams
	 * @param max
	 * @param offset
	 * @return
	 * @throws DataAccessException
	 */
	JdbcPage queryForList(String sql,String countSql ,Map<String,Object> queryParams,int max,int offset) ;
	
	/**
	 * Query given SQL to create a prepared statement from SQL and a
	 * list of arguments to bind to the query, expecting a result Map.
	 * <p>The query is expected to be a single row query; the result row will be
	 * mapped to a Map (one entry for each column, using the column name as the key).
	 * @param sql SQL query to execute
	 * @param args arguments to bind to the query
	 * @param argTypes SQL types of the arguments
	 * (constants from {@code java.sql.Types})
	 * @return the result Map (one entry for each column, using the
	 * column name as the key)
	 * @throws IncorrectResultSizeDataAccessException if the query does not
	 * return exactly one row
	 * @throws DataAccessException if the query fails
	 * @see #queryForMap(String)
	 * @see ColumnMapRowMapper
	 * @see java.sql.Types
	 */
	Map<String, Object> queryForMap(String sql, Map<String,Object> queryParams) throws DataAccessException;
	/**
	 * Issue a single SQL update operation (such as an insert, update or delete statement)
	 * via a prepared statement, binding the given arguments.
	 * @param sql SQL containing bind parameters
	 * @param args arguments to bind to the query
	 * @param argTypes SQL types of the arguments
	 * (constants from {@code java.sql.Types})
	 * @return the number of rows affected
	 * @throws DataAccessException if there is any problem issuing the update
	 * @see java.sql.Types
	 */
	int update(String sql, Map<String,Object> queryParams) throws DataAccessException;
	
    boolean execute(String sql);
	   
}
