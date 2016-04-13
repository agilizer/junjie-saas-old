package cn.arvix.cloudstorage.file;

import cn.arvix.cloudstorage.file.impl.ali.AliCloudFileOperatorImpl;
import cn.arvix.cloudstorage.file.impl.qiniu.QiniuCloudFileOperatorImpl;

/**
 * Created by wanghaiyang on 16/4/13.
 */
public class CloudFileManager {
    //调用ali api需要先设置endpoint
    private static String aliEndPoint = "";

    public static CloudFileOperator getFileOperator(StorageServiceProvider storageServiceProvider, String bucketName, AuthKey authKey) {
        CloudFileOperator cloudFileOperator = null;
        switch (storageServiceProvider) {
            case ALI:
                cloudFileOperator = new AliCloudFileOperatorImpl(bucketName, authKey, aliEndPoint);
                break;
            case QINIU:
                cloudFileOperator = new QiniuCloudFileOperatorImpl(bucketName, authKey);
                break;
            case UPYUN:
                break;
            case UCLOUD:
                break;
            default:
        }
        return cloudFileOperator;
    }

    public static String getAliEndPoint() {
        return aliEndPoint;
    }

    public static void setAliEndPoint(String aliEndPoint) {
        CloudFileManager.aliEndPoint = aliEndPoint;
    }
}
