package com.agilemaster.parta.db;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.junjie.commons.db.client.DataSourceSelecter;
import com.junjie.commons.utils.JunjieConstants;

public class DataSourceSelecterImpl implements DataSourceSelecter{

	  java.util.Random r=new java.util.Random(1);
	 private  int dbInstanceCount = 500;
	@Override
	public String getCurrentDataSourceKey() {
		Subject subject = SecurityUtils.getSubject();
		Session session  = subject.getSession();
       return (String) session.getAttribute(JunjieConstants.DATA_SOURCE_KEY);
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
