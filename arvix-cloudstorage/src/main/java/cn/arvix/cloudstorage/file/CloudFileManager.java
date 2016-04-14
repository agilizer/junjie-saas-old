package cn.arvix.cloudstorage.file;

import cn.arvix.cloudstorage.file.impl.ali.AliCloudFileOperatorImpl;
import cn.arvix.cloudstorage.file.impl.qiniu.QiniuCloudFileOperatorImpl;

/**
 * Created by wanghaiyang on 16/4/13.
 */
public class CloudFileManager {

    public static CloudFileOperator getFileOperator(StorageServiceProvider storageServiceProvider, InitPackage initPackage) {
        CloudFileOperator cloudFileOperator = null;
        String bucketName = initPackage.getBucketname();
        AuthKey authKey = initPackage.getAuthKey();
        String aliEndPoint = initPackage.getEndPoint();
        switch (storageServiceProvider) {
            case ALI:
                if (aliEndPoint != null) {
                    cloudFileOperator = new AliCloudFileOperatorImpl(bucketName, authKey, aliEndPoint);
                }
                break;
            case QINIU:
                cloudFileOperator = new QiniuCloudFileOperatorImpl(bucketName, authKey);
                break;
            case UPYUN:
                break;
            case UCLOUD:
                break;
            default:
                throw new IllegalArgumentException("Illegal Storage service provider.");
        }
        return cloudFileOperator;
    }

    public static class InitPackage {
        private String bucketname;
        //调用ali api需要先设置endpoint
        private String endPoint = null;
        private AuthKey authKey;

        public InitPackage(String bucketname, AuthKey authKey) {
            this(bucketname, authKey, null);
        }

        public InitPackage(String bucketname, AuthKey authKey, String endPoint) {
            this.bucketname = bucketname;
            this.authKey = authKey;
            this.endPoint = endPoint;
        }

        public String getBucketname() {
            return bucketname;
        }

        public void setBucketname(String bucketname) {
            this.bucketname = bucketname;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public AuthKey getAuthKey() {
            return authKey;
        }

        public void setAuthKey(AuthKey authKey) {
            this.authKey = authKey;
        }
    }
}
