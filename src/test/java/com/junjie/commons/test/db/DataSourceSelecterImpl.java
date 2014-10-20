package com.junjie.commons.test.db;

import com.junjie.commons.db.client.DataSourceSelecter;

public class DataSourceSelecterImpl implements DataSourceSelecter{

	  java.util.Random r=new java.util.Random(1);
	@Override
	public String getCurrentDataSourceKey() {
        if(r.nextBoolean()==true){
        	return "key1";
        }else{
        	return "key2";
        }
		
	}
	
}
