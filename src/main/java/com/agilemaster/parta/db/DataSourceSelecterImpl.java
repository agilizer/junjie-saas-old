package com.agilemaster.parta.db;

import com.junjie.commons.db.client.DataSourceSelecter;

public class DataSourceSelecterImpl implements DataSourceSelecter{

	  java.util.Random r=new java.util.Random(1);
	 private  int dbInstanceCount = 500;
	@Override
	public String getCurrentDataSourceKey() {
       return "key"+r.nextInt(dbInstanceCount);
	}
	public  static void main(String args[]){
		  java.util.Random r=new java.util.Random(1);
		  for(int i=0;i<10;i++){
			  System.out.println(r.nextInt(1));
		  }
	}
	public int getDbInstanceCount() {
		return dbInstanceCount;
	}
	public void setDbInstanceCount(int dbInstanceCount) {
		this.dbInstanceCount = dbInstanceCount;
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "test DataSourceSslecterImpl";
	}
	
	
}
