package cn.arvix.cloudstorage.file.impl.qiniu;

import cn.arvix.cloudstorage.file.*;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QiniuCloudFileOperatorImpl implements CloudFileOperator {
	private static final Logger logger = LoggerFactory.getLogger(QiniuCloudFileOperatorImpl.class);
	private static String upToken = null;
    private static long upTokenLastUpdateTime = 0;
    private static long upTokenExpireTime = 3600L;

	private AuthKey authKey;
	private UploadManager uploadManager;
	private BucketManager bucketManager;
    private Auth auth;
	private String bucketName;

	public QiniuCloudFileOperatorImpl(String bucketName, AuthKey authKey) {

		this(bucketName, authKey, 3600L);
	}

	public QiniuCloudFileOperatorImpl(String bucketName, AuthKey authKey, long upTokenExpireTime) {
		this.bucketName = bucketName;
		this.authKey = authKey;
		this.upTokenExpireTime = upTokenExpireTime;
		uploadManager = new UploadManager();
        auth = getAuth();
		bucketManager = new BucketManager(auth);
	}

	private CloudFile uploadFile(File file, InputStream ins, String fileName) {
		String key = null;
		Response res = null;
		CloudFile cloudFile = new CloudFile();
		try {
			if (file != null) {
				key = CloudFileUtil.getUniqueKey(KEY_PREFIX, file.getName());
				res = uploadManager.put(file, key, getUpToken());
				cloudFile.setName(file.getName());
				cloudFile.setLength(file.length());
			} else if (ins != null && fileName != null && !fileName.equals("")) {
				key = CloudFileUtil.getUniqueKey(KEY_PREFIX, fileName);
				byte[] buffer = getBuffer(ins);
				res = uploadManager.put(buffer, key, getUpToken());
				cloudFile.setName(fileName);
				cloudFile.setLength(buffer.length);
			} else {
				throw new IllegalArgumentException("file and inputstream can't be both null");
			}

		} catch (Exception e) {
			logger.error("[Qiniu]Upload failed: " + e);
			return null;
		}
		if (!res.isOK()) {
			logger.error("[Qiniu]Upload failed, Error code: " + res.statusCode);
			if (res.statusCode == 401) {
				refreshUpToken(true);
			}
			return null;
		}
		long time = System.currentTimeMillis();
		cloudFile.setDateCreated(time);
		cloudFile.setLastAccessed(time);
		cloudFile.setKey(key);
		cloudFile.setCloudFileType(CloudFileType.getFileType(cloudFile.getName()));
		return cloudFile;
	}

	private byte[] getBuffer(InputStream ins) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = ins.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}
		return byteArrayOutputStream.toByteArray();
	}

	public CloudFile storeFile(File file) {
		return uploadFile(file, null, null);
	}

	public List<CloudFile> storeFiles(List<File> fileList) {
		List<CloudFile> cloudFileList = new ArrayList<CloudFile>();
		for (File file : fileList) {
			CloudFile cloudFile = storeFile(file);
			if (cloudFile != null) {
				cloudFileList.add(cloudFile);
			}
		}
		return cloudFileList;
	}

	public CloudFile storeFile(InputStream inputStream, String fileName) {
		return uploadFile(null, inputStream, fileName);
	}

	public List<CloudFile> storeFiles(Map<String, InputStream> keyInsMap) {
		List<CloudFile> cloudFileList = new ArrayList<CloudFile>();
		for (Map.Entry<String, InputStream> entry : keyInsMap.entrySet()) {
			CloudFile cloudFile = storeFile(entry.getValue(), entry.getKey());
			if (cloudFile != null) {
				cloudFileList.add(cloudFile);
			}
		}
		return cloudFileList;
	}

	public boolean deleteFile(CloudFile cloudFile) {
		return deleteFile(cloudFile.getKey());
	}

	public boolean deleteFile(String key) {
		try {
			bucketManager.delete(bucketName, key);
		} catch (QiniuException e) {
			logger.error("[Qiniu]Delete file[" + key + "] failed: " + e);
			return false;
		}
		return true;
	}

	public List<String> deleteFiles(List<String> keyList) {
		List<String> resultKeyList = new ArrayList<String>();
		for (String key : keyList) {
			if (deleteFile(key)) {
				resultKeyList.add(key);
			}
		}
		return resultKeyList;
	}

	public String getDownloadUrl(String key, String domain) {
		String privateUrl = CloudFileUtil.getPrivateUrl(domain, key);
		return auth.privateDownloadUrl(privateUrl, upTokenExpireTime);
	}

	public InputStream getDownloadStream(String key, String domain) {
		String url = getDownloadUrl(domain, key);
		InputStream inputStream = null;
		try {
			URL downloadUrl = new URL(url);
			inputStream = downloadUrl.openStream();
		} catch (Exception e) {
			logger.error("[Qiniu]Get download stream failed: " + e);
		}
		return inputStream;
	}

	@Override
	public void close() {

	}

	private Auth getAuth() {
        return Auth.create(authKey.getAccessKey(), authKey.getSecretKey());
    }

    private String getUpToken() {
    	if (upToken != null && !expired()) {
    		return upToken;
    	}
    	return refreshUpToken(false);
    }

	private String refreshUpToken(boolean force) {
		synchronized (this) {
			if (force || upToken == null || expired()) {
				upToken = auth.uploadToken(bucketName, (String)null, upTokenExpireTime, (StringMap)null, true);
				refreshExpiredTime();
			}
		}
		return upToken;
	}

    private boolean expired() {
    	long curTime = System.currentTimeMillis();
    	long expireTimeInterval = upTokenExpireTime < 60 ? upTokenExpireTime : (upTokenExpireTime - 60);
    	return curTime - upTokenLastUpdateTime < expireTimeInterval;
    }

    private void refreshExpiredTime() {
    	upTokenLastUpdateTime = System.currentTimeMillis();
    }

}