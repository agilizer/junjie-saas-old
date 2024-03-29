package com.junjie.commons.db;

public interface JdbcConstants {
	String OPTION_KEY="option";
	int QUERY_FOR_LIST = 1;
	int QUERY_FOR_MAP = 2;
	int QUERY_FOR_LONG = 6;
	int UPDATE = 3;
	int EXECUTE = 4;
	int EXECUTE_KEYS =  5;
	int RUN_SCRIPT = 8;
	int QUERY_FOR_LIST_ALL = 10;
	String KEY_EXECUTE_KEYS ="keys";
	String KEY_QUERY_PARAMS= "queryParams";
	String KEY_MAX="max";
	String KEY_OFFSET="offset";
	String KEY_COUNT_SQL="countSql";
	String DB_INFO_KEY="dbInfoKey";
	// use to fix netty send null result bug
	String NULL = "##null##";
}
