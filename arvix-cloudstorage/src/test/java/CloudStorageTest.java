import cn.arvix.cloudstorage.file.*;
import org.junit.Test;

import java.io.*;
import java.util.*;

import com.qiniu.http.Client;

/**
 * Created by wanghaiyang on 16/4/13.
 */
public class CloudStorageTest {
    private static StorageServiceProvider storageServiceProvider = StorageServiceProvider.QINIU;
    private static CloudFileOperator cloudFileOperator;
    private static String accessToken = "onIcMZEEnIFGr1u49cjRkCJJbuep7SKsvDbSuZSf";
    private static String secretToken = "lpwkbVFXR6an3_Sn2133kwYFOwbyzveBu4S73YPQ";
    private static String bucketName = "whydebucket";
    private static String filePath = "D:/home/testImage";
    private static String downloadPath = "/Users/wanghaiyang/Desktop/downloadimage.jpg";
    private static String defaultDomain = "7xswnm.com2.z0.glb.clouddn.com";
    private static List<String> uploadedFileKey = new ArrayList<String>();

    @Test
    public void test() throws Exception {
        init();
        final Vector<String> keys = new Vector<String>();
        File file = new File("/Users/wanghaiyang/Desktop/屏幕快照 2016-04-10 上午3.39.26.png");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                cloudFileOperator.deleteFiles(keys);
                System.out.println("Clean.");
            }
        });
        while (true) {
            try {
                CloudFile cloudFile = cloudFileOperator.storeFile(new FileInputStream(file), file.getName());
                if (cloudFile != null) {
                    System.out.println("Key: " + cloudFile.getKey());
                    keys.add(cloudFile.getKey());
                }
            } catch (Exception e) {

            }
        }

//        testUpload();
//        testDownload();
//        testDelete();
//        testShutdown();
    }

    public void init() throws Exception {
        System.err.println("Init...");
        AuthKey authKey = new AuthKey(accessToken, secretToken);
        cloudFileOperator = CloudFileManager.getFileOperator(storageServiceProvider, new CloudFileManager.InitPackage(bucketName, authKey));
        System.err.println("Inited.");
    }

    public void testUpload() throws Exception {
        System.err.println("testUpload...");
        //prepare
        File dir = new File(filePath);
        File[] files = dir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.getPath().endsWith(".png")) {
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
        System.out.println("Private bucket download url: " + cloudFileOperator.getDownloadUrl(uploadedFileKey.get(0), defaultDomain));
        File file = new File(downloadPath);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(cloudFileOperator.getDownloadStream(uploadedFileKey.get(1), defaultDomain));
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = bufferedInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
        //多文件下载测试
        List<String> keyList = new ArrayList<String>();
        keyList.add(uploadedFileKey.get(0));
        keyList.add(uploadedFileKey.get(1));
        System.out.println("Private bucket download urls: " + cloudFileOperator.getDownloadUrls(keyList, defaultDomain));
        List<InputStream> inputStreamList = cloudFileOperator.getDownloadStreams(keyList, defaultDomain);
        System.out.println("InputStream Array Length: " + inputStreamList.size());
        for (InputStream inputStream : inputStreamList) {
            inputStream.close();
        }
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
