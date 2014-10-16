package com.junjie.commons.db;

import java.util.List;
import java.util.Map;	

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;

/**
 * 数据库操作接口，server和client都使用
 * @author abel.lee
 *
 */
public interface JunjieJdbcOptions{
	/**
	 * Execute a query for a result Map, given static SQL.
	 * <p>Uses a JDBC Statement, not a PreparedStatement. If you want to
	 * execute a static query with a PreparedStatement, use the overloaded
	 * {@link #queryForMap(String, Object...)} method with {@code null}
	 * as argument array.
	 * <p>The query is expected to be a single row query; the result row will be
	 * mapped to a Map (one entry for each column, using the column name as the key).
	 * @param sql SQL query to execute
	 * @return the result Map (one entry for each column, using the
	 * column name as the key)
	 * @throws IncorrectResultSizeDataAccessException if the query does not
	 * return exactly one row
	 * @throws DataAccessException if there is any problem executing the query
	 * @see #queryForMap(String, Object[])
	 * @see ColumnMapRowMapper
	 */
	Map<String, Object> queryForMap(String sql) throws DataAccessException;
	/**
	 * Execute a query for a result list, given static SQL.
	 * <p>Uses a JDBC Statement, not a PreparedStatement. If you want to
	 * execute a static query with a PreparedStatement, use the overloaded
	 * {@code queryForList} method with {@code null} as argument array.
	 * <p>The results will be mapped to a List (one entry for each row) of
	 * Maps (one entry for each column using the column name as the key).
	 * Each element in the list will be of the form returned by this interface's
	 * queryForMap() methods.
	 * @param sql SQL query to execute
	 * @return an List that contains a Map per row
	 * @throws DataAccessException if there is any problem executing the query
	 * @see #queryForList(String, Object[])
	 */
	List<Map<String, Object>> queryForList(String sql,Map<String,Integer> pageParams) throws DataAccessException;
	
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
	Map<String, Object> queryForMap(String sql, Object[] args, int[] argTypes) throws DataAccessException;
	/**
	 * Query given SQL to create a prepared statement from SQL and a
	 * list of arguments to bind to the query, expecting a result list.
	 * <p>The results will be mapped to a List (one entry for each row) of
	 * Maps (one entry for each column, using the column name as the key).
	 * Thus  Each element in the list will be of the form returned by this interface's
	 * queryForMap() methods.
	 * @param sql SQL query to execute
	 * @param args arguments to bind to the query
	 * @param argTypes SQL types of the arguments
	 * (constants from {@code java.sql.Types})
	 * @return a List that contains a Map per row
	 * @throws DataAccessException if the query fails
	 * @see #queryForList(String)
	 * @see java.sql.Types
	 */
	List<Map<String, Object>> queryForList(String sql, Object[] args, int[] argTypes,Map<String,Integer> pageParams) throws DataAccessException;
	
	
	
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
	int update(String sql, Object[] args, int[] argTypes) throws DataAccessException;
	   
}
