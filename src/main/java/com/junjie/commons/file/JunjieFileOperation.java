package com.junjie.commons.file;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 封装文件（第三方或者是自建存储）操作，提供统一接口
 * @author abel.lee
 *
 */
public interface JunjieFileOperation {
	/**
	 * @param file
	 * @return 文件存储的唯一标识，取文件时用这个标识
	 */
	String storeFile(File file);
	/**
	 * 
	 * @param file
	 * @return 文件存储的唯一标识，取文件时用这个标识
	 */
	String storeFile(InputStream inputStream);
	/**
	 * 根据文件key获取文件信息
	 * @param key
	 * @return
	 */
	JunjieFile accessFileInfo(String key);
	/**
	 * 根据文件key获取流
	 * @param key
	 * @return
	 */
	InputStream accessFileStream(String key);
	/**
	 * 根据文件key获取文件，并写入到流中
	 * @param key
	 * @param outStream
	 * @return
	 */
	void accessFileStream(String key,OutputStream outStream);
	/**
	 * 根据key获取相应的url访问地址，图片或者pdf等用。
	 * @param key
	 * @return
	 */
	String accessFileUrl(String key);
	
	/**
	 * 根据文件accessInfo获取流
	 * @param accessInfo
	 * @return
	 */
	InputStream accessFileStream(Map<String,String> accessInfo);
	/**
	 * 根据文件accessInfo获取文件，并写入到流中
	 * @param accessInfo	 
	 * @param outStream
	 * @return
	 */
	void accessFileStream(Map<String,String> accessInfo,OutputStream outStream);
	/**
	 * 根据accessInfo获取相应的url访问地址，图片或者pdf等用。
	 * @param key
	 * @return
	 */
	String accessFileUrl(Map<String,String> accessInfo);
	/**
	 * 根据文件junjieFile获取流
	 * @param junjieFile
	 * @return
	 */
	InputStream accessFileStream(JunjieFile junjieFile);
	/**
	 * 根据文件junjieFile获取文件，并写入到流中
	 * @param junjieFile	 
	 * @param outStream
	 * @return
	 */
	void accessFileStream(JunjieFile junjieFile,OutputStream outStream);
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
