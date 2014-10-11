package com.junjie.commons.file;

import java.io.Serializable;

/**
 * junjie file,support 分布式文件系统或者第三方存储。
 * @author abel.lee
 *
 */
public class JunjieFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3488696115674649784L;
	private String name;
	private Long dateCreated;
	private Long lastAccessed;
	private JunjieFileType junjieFileType;
	private Long length;
	/**
	 * 文件序号key
	 */
	private String key;
	/**
	 * 存储获取第三方存储服务所需要的信息
	 */
	private Serializable accessInfo;
	
	
	
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



	public String getKey() {
		return key;
	}


	/**
	 * 获取第三方存储服务所需要的信息
	 * @return
	 */
	
	public Serializable getAccessInfo() {
		return accessInfo;
	}

    /**
     * 设置获取第三方存储服务所需要的信息
     * @param accessInfo
     */
	public void setAccessInfo(Serializable accessInfo) {
		this.accessInfo = accessInfo;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public static enum  JunjieFileType implements Serializable{
        EXCEL(""),
        WORD(""),
        GANTT(""),
        PHOTO(""),
        PDF(""),
        VOICE(""),
        MV(""),
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
