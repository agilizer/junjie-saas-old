package cn.arvix.cloudstorage.file;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface CloudFileOperator {
	/**
	 * 唯一key前缀
	 */
	String KEY_PREFIX="file";

	/**
	 * @param file
	 * @return null 表示存储失败
	 */
	public CloudFile storeFile(File file);
	/**
	 * 
	 * @param file
	 * @param key
	 * @return
	 */
	
	public CloudFile storeFile(File file,String key);

	/**
	 * @param fileList
	 * @return 存储成功的文件列表
	 */
	public List<CloudFile> storeFiles(List<File> fileList);

	/**
	 * @param inputStream
	 * @param fileName
	 * @return null 表示存储失败
	 */
	public CloudFile storeFile(InputStream inputStream, String fileName);

	/**
	 * @param Map:key -> InputStream
	 * @return 存储成功的文件列表
	 */
	public List<CloudFile> storeFiles(Map<String, InputStream> keyInsMap);

	/**
	 * @param cloudFile
	 * @return false 表示删除失败
	 */
	public boolean deleteFile(CloudFile cloudFile);

	/**
	 * @param key
	 * @return false 表示删除失败
	 */
	public boolean deleteFile(String key);

	/**
	 * @param keyList
	 * @return 成功删除的文件列表
	 */
	public List<String> deleteFiles(List<String> keyList);

	/**
	 * @param key
	 * @param domain 访问资源的域名
	 * @return 文件下载链接.由于Ali采用OSS模型,所以无法获取下载链接,返回null.七牛为私有空间下载链接
	 */
	public String getDownloadUrl(String key, String domain);

	public List<String> getDownloadUrls(List<String> keyList, String domain);

	/**
	 * @param key
	 * @param domain 访问资源的域名,对于ali云,此字段无用
	 * @return InputStream
	 */
	public InputStream getDownloadStream(String key, String domain);

	public List<InputStream> getDownloadStreams(List<String> keyList, String domain);

	/**
	 * ali api需要释放资源
	 */
	public void close();
}
