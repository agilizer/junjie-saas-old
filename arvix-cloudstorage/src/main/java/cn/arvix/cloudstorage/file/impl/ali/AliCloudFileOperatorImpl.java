package cn.arvix.cloudstorage.file.impl.ali;

import cn.arvix.cloudstorage.file.*;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghaiyang on 16/4/13.
 */
public class AliCloudFileOperatorImpl implements CloudFileOperator {
    private static final Logger logger = LoggerFactory.getLogger(AliCloudFileOperatorImpl.class);
    private AuthKey authKey;
    private String endPoint;
    private OSSClient ossClient;
    private String bucketName;

    public AliCloudFileOperatorImpl(String bucketName, AuthKey authKey, String endPoint) {
        this.bucketName = bucketName;
        this.authKey = authKey;
        this.endPoint = endPoint;
        ossClient = new OSSClient(endPoint, authKey.getAccessKey(), authKey.getSecretKey());
    }

    @Override
    public CloudFile storeFile(File file) {
        String key = CloudFileUtil.getUniqueKey(KEY_PREFIX, file.getName());
        PutObjectResult putObjectResult = null;
        CloudFile cloudFile = null;
        try {
            putObjectResult = ossClient.putObject(bucketName, key, file);
            cloudFile = new CloudFile();
            cloudFile.setName(file.getName());
            cloudFile.setKey(key);
            cloudFile.setCloudFileType(CloudFileType.getFileType(file.getName()));
            cloudFile.setLength(file.length());
            long time = System.currentTimeMillis();
            cloudFile.setDateCreated(time);
            cloudFile.setLastAccessed(time);
        } catch (OSSException e) {
            logger.error(getErrorMsg("Upload file", e).toString());
            return null;
        } catch (ClientException e) {
            logger.error("[Ali]Upload file failed: " + e.getMessage());
            return null;
        }
        return cloudFile;
    }

    @Override
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

    @Override
    public CloudFile storeFile(InputStream inputStream, String fileName) {
        String key = CloudFileUtil.getUniqueKey(KEY_PREFIX, fileName);
        PutObjectResult putObjectResult = null;
        CloudFile cloudFile = null;
        try {
            putObjectResult = ossClient.putObject(bucketName, key, inputStream);
            cloudFile = new CloudFile();
            cloudFile.setName(fileName);
            cloudFile.setKey(key);
            cloudFile.setCloudFileType(CloudFileType.getFileType(fileName));
            if (cloudFile.isCaculateFileLength()) {
                cloudFile.setLength(CloudFileUtil.getStreamLength(inputStream));
            } else {
                cloudFile.setLength(-1);
            }
            long time = System.currentTimeMillis();
            cloudFile.setDateCreated(time);
            cloudFile.setLastAccessed(time);
        } catch (OSSException e) {
            logger.error(getErrorMsg("Upload file", e).toString());
            return null;
        } catch (ClientException e) {
            logger.error("[Ali]Upload file failed: " + e.getMessage());
            return null;
        }
        return cloudFile;
    }

    private String getErrorMsg(String op, OSSException e) {
        StringBuilder errorMsg = new StringBuilder();
        errorMsg.append("[Ali]" + op + " failed: ");
        errorMsg.append("[Error Message: " + e.getErrorMessage() + "] ");
        errorMsg.append("[Error Code: " + e.getErrorCode() + "] ");
        errorMsg.append("[Request Id: " + e.getRequestId() + "] ");
        errorMsg.append("[Host ID: " + e.getHostId() + "] ");
        return errorMsg.toString();
    }

    @Override
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

    @Override
    public boolean deleteFile(CloudFile cloudFile) {
        try {
            ossClient.deleteObject(bucketName, cloudFile.getKey());
        } catch (OSSException e) {
            logger.error(getErrorMsg("Delete file", e).toString());
            return false;
        } catch (ClientException e) {
            logger.error("[Ali]Delete file failed: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFile(String key) {
        try {
            ossClient.deleteObject(bucketName, key);
        } catch (OSSException e) {
            logger.error(getErrorMsg("Delete file", e).toString());
            return false;
        } catch (ClientException e) {
            logger.error("[Ali]Delete file failed: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<String> deleteFiles(List<String> keyList) {
        DeleteObjectsResult deleteObjectsResult = null;
        List<String> resultList = new ArrayList<String>();
        try {
            deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keyList));
        } catch (OSSException e) {
            logger.error(getErrorMsg("Delete file", e).toString());
            return resultList;
        } catch (ClientException e) {
            logger.error("[Ali]Delete files failed: " + e.getMessage());
            return resultList;
        }
        resultList.addAll(deleteObjectsResult.getDeletedObjects());
        return resultList;
    }

    @Override
    public String getDownloadUrl(String key, String domain) {
        return null;
    }

    @Override
    public InputStream getDownloadStream(String key, String domain) {
        OSSObject ossObject = null;
        try {
            ossObject = ossClient.getObject(bucketName, key);
        } catch (OSSException e) {
            logger.error(getErrorMsg("Get download stream", e).toString());
            return null;
        } catch (ClientException e) {
            logger.error("[Ali]Get download stream failed: " + e.getMessage());
            return null;
        }
        return ossObject == null ? null : ossObject.getObjectContent();
    }

    @Override
    public void close() {
        ossClient.shutdown();
    }
}
