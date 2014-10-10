package com.junjie.commons.file;

import java.io.Serializable;
import java.util.Map;

/**
 * junjie file,support 分布式文件系统或者第三方存储 
 * @author abel.lee
 *
 */
public class JunjieFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3488696115674649784L;
	private String name;
	private long dateCreated;
	private long lastAccessed;
	private JunjieFileType junjieFileType;
	private long length;
	/**
	 * 存储获取第三方存储服务所需要的信息
	 */
	private Map<String,String> accessInfo;
	
	
	
    public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public long getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}



	public long getLastAccessed() {
		return lastAccessed;
	}



	public void setLastAccessed(long lastAccessed) {
		this.lastAccessed = lastAccessed;
	}



	public JunjieFileType getJunjieFileType() {
		return junjieFileType;
	}



	public void setJunjieFileType(JunjieFileType junjieFileType) {
		this.junjieFileType = junjieFileType;
	}



	public long getLength() {
		return length;
	}



	public void setLength(long length) {
		this.length = length;
	}



	/**
	 * 存储获取第三方存储服务所需要的信息
	 * @return
	 */
	public Map<String, String> getAccessInfo() {
		return accessInfo;
	}


    /**
     * 存储获取第三方存储服务所需要的信息
     * @param accessInfo
     */
	public void setAccessInfo(Map<String, String> accessInfo) {
		this.accessInfo = accessInfo;
	}



	public static enum  JunjieFileType{
        EXCEL(""),
        WORD(""),
        GANTT(""),
        PHOTO(""),
        PDF(""),
        OTHERS("");
        String cssCode;
        JunjieFileType(String cssCode){
            this.cssCode = cssCode;
        }
        public String getCssCode(){
            return this.cssCode;
        }
    }
}
