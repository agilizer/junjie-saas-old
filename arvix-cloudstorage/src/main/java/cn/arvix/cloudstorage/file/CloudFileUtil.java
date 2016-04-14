package cn.arvix.cloudstorage.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by wanghaiyang on 16/4/13.
 */
public class CloudFileUtil {
	private static final Logger logger = LoggerFactory.getLogger(CloudFileUtil.class);
	/**
	 * 
	 * @param domain
	 * @param key
	 * @return 文件访问地址
	 */
	public static String getPrivateUrl(String domain, String key) {
		if (!domain.startsWith("http")) {
			domain = "http://" + domain;
		}
		if (!domain.endsWith("/")) {
			domain = domain + "/";
		}
		return domain + key;
	}

	public static String getUniqueKey(String prefix) {
		return prefix + "-" + UUID.randomUUID().toString();
	}

	/**
	 * 对于大文件,不建议使用
	 * @return 流的长度
     */
	public static int getStreamLength(InputStream inputStream) {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		byte[] buffer = new byte[1024];
		int len = -1;
		int totalLen = 0;
		try {
			while ((len = bufferedInputStream.read(buffer)) != -1) {
                totalLen += len;
            }
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return totalLen;
	}
}