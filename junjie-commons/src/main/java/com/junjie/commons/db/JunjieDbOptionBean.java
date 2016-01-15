package com.junjie.commons.db;

import java.io.Serializable;
import java.util.Map;

public class JunjieDbOptionBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6019313419076207406L;
    private int option =0;
    private String dbInfoKey = "";
    private String sql ="";
    private Map<String,Object> params;
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
	public String getDbInfoKey() {
		return dbInfoKey;
	}
	public void setDbInfoKey(String dbInfoKey) {
		this.dbInfoKey = dbInfoKey;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
    
    
}
