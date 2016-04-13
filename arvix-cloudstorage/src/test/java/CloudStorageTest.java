import cn.arvix.cloudstorage.file.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanghaiyang on 16/4/13.
 */
public class CloudStorageTest {
    private static StorageServiceProvider storageServiceProvider = StorageServiceProvider.QINIU;
    private static CloudFileOperator cloudFileOperator;
    private static String accessToken = "onIcMZEEnIFGr1u49cjRkCJJbuep7SKsvDbSuZSf";
    private static String secretToken = "lpwkbVFXR6an3_Sn2133kwYFOwbyzveBu4S73YPQ";
    private static String bucketName = "whydebucket";
    private static String filePath = "/Users/wanghaiyang/Desktop/Arvix/arvix-ober/src/main/resources/files/upload/defaultPhoto/";
    private static String downloadPath = "/Users/wanghaiyang/Desktop/downloadimage.jpg";
    private static String defaultDomain = "7xswnm.com2.z0.glb.clouddn.com";
    private static List<String> uploadedFileKey = new ArrayList<String>();

    @Test
    public void test() throws Exception {
        init();
        testUpload();
        testDownload();
        testDelete();
        testShutdown();
    }

    public void init() throws Exception {
        System.err.println("Init...");
        AuthKey authKey = new AuthKey(accessToken, secretToken);
        cloudFileOperator = CloudFileManager.getFileOperator(storageServiceProvider, bucketName, authKey);
        System.err.println("Inited.");
    }

    public void testUpload() throws Exception {
        System.err.println("testUpload...");
        //prepare
        File dir = new File(filePath);
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.getPath().endsWith(".jpg")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        List<File> fileList = new ArrayList<File>();
        Map<String, InputStream> keyInsMap = new HashMap<String, InputStream>();
        for (File file : files) {
            fileList.add(file);
            keyInsMap.put(file.getName(), new FileInputStream(file));
        }
        //开始上传
        //单个文件上传
        for (File file : files) {
            CloudFile cloudFile = cloudFileOperator.storeFile(new FileInputStream(file), file.getName());
            if (cloudFile != null) {
                System.out.println("Key: " + cloudFile.getKey());
                uploadedFileKey.add(cloudFile.getKey());
            }
        }
        List<CloudFile> cloudFileList01 = cloudFileOperator.storeFiles(fileList);
        System.out.println("keyList: " + cloudFileList01);
        for (CloudFile cloudFile : cloudFileList01) {
            uploadedFileKey.add(cloudFile.getKey());
        }
        //多文件上传
        List<CloudFile> cloudFileList02 = cloudFileOperator.storeFiles(keyInsMap);
        System.out.println("keyList: " + cloudFileList02);
        for (CloudFile cloudFile : cloudFileList02) {
            uploadedFileKey.add(cloudFile.getKey());
        }
        System.err.println("Upload test finished.");
    }

    public void testDownload() throws Exception {
        System.err.println("testDownload...");
        System.out.println("Private bucket download url: " + cloudFileOperator.getDownloadUrl(defaultDomain, uploadedFileKey.get(0)));
        File file = new File(downloadPath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(cloudFileOperator.getDownloadStream(defaultDomain, uploadedFileKey.get(1)));
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = bufferedInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
        System.err.println("Dowload test finished.");
    }

    public void testDelete() throws Exception {
        System.err.println("testDelete...");
        //删除文件
        List<String> rkeyList = cloudFileOperator.deleteFiles(uploadedFileKey);
        System.out.println("Deleted: " + rkeyList);
        System.err.println("Delete test finished.");
    }

    public void testShutdown() throws Exception {
        System.err.println("testShutdown...");
        cloudFileOperator.close();
        System.err.println("Shutdown test finished.");
    }
}
