package com.junjie.commons.test.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.junjie.commons.db.server.JunjieJdbcAccessor;

public class JunjieJdbcAccessorImpl implements JunjieJdbcAccessor{

	private String DIR_NAME = "database";
	@Override
	public DataSource genDataSource(String dbInfoKey) {
		DruidDataSource druidDs = new DruidDataSource();
		druidDs.setUrl("jdbc:h2:file:./" + DIR_NAME  + "/"+dbInfoKey+";INIT=runscript from './src/test/resources/sql/test.sql'");
	    druidDs.setInitialSize(500);
	    druidDs.setMaxActive(500);
	    druidDs.setMinIdle(200);
	    druidDs.setMaxWait(60000);
	    druidDs.setTimeBetweenEvictionRunsMillis(60000);
	    druidDs.setValidationQuery("SELECT 'x'");
	    druidDs.setTestWhileIdle(true);
	    druidDs.setTestOnBorrow(false);
	    druidDs.setTestOnReturn(false);
	    try {
			druidDs.init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//
//		 JdbcDataSource ds = new JdbcDataSource();  
//         ds.setURL(	"jdbc:h2:file:./" + DIR_NAME  + "/"+dbInfoKey+ 
//        		 "");  
		return druidDs;
	}

}
