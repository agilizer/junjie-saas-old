package com.junjie.commons.file;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * 封装文件（第三方或者是自建存储）操作，提供统一接口
 * @author abel.lee
 *
 */
public interface JunjieFileOperation {
	/**
	 * 唯一key前缀
	 */
	String KEY_PREFIX="file";
	/**
	 * @param file
	 * @return JunjieFile实例，注意为null时表示存储出错
	 */
	JunjieFile storeFile(File file);
	/**
	 * 
	 * @param file
	 * @return  JunjieFile实例，注意为null时表示存储出错
	 */
	JunjieFile storeFile(InputStream inputStream,String fileName);
	/**
	 * 根据key获取相应的url访问地址，图片或者pdf等用。
	 * @param key
	 * @return
	 */
	String accessFileUrl(String key);	
	/**
	 * 根据junjieFile获取相应的url访问地址，图片或者pdf等用。
	 * @param junjieFile
	 * @return
	 */
	String accessFileUrl(JunjieFile junjieFile);
	
	/**
	 * delete by key
	 * @param key
	 */
	void delete(String key);
	
	

}
