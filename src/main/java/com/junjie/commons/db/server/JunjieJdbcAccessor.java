package com.junjie.commons.db.server;

import javax.sql.DataSource;

public interface JunjieJdbcAccessor {
	DataSource genDataSource(String dbInfoKey);
}
