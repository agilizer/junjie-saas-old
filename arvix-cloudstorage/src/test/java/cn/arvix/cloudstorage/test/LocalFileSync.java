package cn.arvix.cloudstorage.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.arvix.cloudstorage.file.AuthKey;
import cn.arvix.cloudstorage.file.CloudFile;
import cn.arvix.cloudstorage.file.CloudFileManager;
import cn.arvix.cloudstorage.file.CloudFileOperator;
import cn.arvix.cloudstorage.file.StorageServiceProvider;

/**
 * 用做测试，上传到七牛服务器。
 * @author abel.lee
 *
 */
public class LocalFileSync implements Runnable {
    private static StorageServiceProvider storageServiceProvider = StorageServiceProvider.QINIU;
    private static String accessToken = "";
    private static String secretToken = "";
    private static String bucketName = "";

    private static String  rootDirPath = "";
    private static int rootDirPathLen = -1;

    private static String redisIp = "127.0.0.1";
    private static String redisPort = "6379";
    private static String hashMapName = "cloud_storage_data_index";

    private static int threadNum = 3;
    private static ConcurrentLinkedQueue<File> fileQueue = new ConcurrentLinkedQueue<File>();
    private static List<LocalFileSync> localFileSyncList = new ArrayList<LocalFileSync>();
    private static CountDownLatch countDownLatch = null;

    private CloudFileOperator cloudFileOperator = null;
    private volatile boolean stop = false;

    public LocalFileSync() {
        cloudFileOperator = getCloudFileOperator();
    }

    @Override
    public void run() {
        while (!stop || fileQueue.size() > 0) {
            File file = fileQueue.poll();
            if (file != null) {
                CloudFile cloudFile = processFile(file);
            }
        }
        countDownLatch.countDown();
    }

   

    private CloudFile processFile(File file) {
        CloudFile cloudFile = null;
        try {
        	String key = getRedisKey(file.getAbsolutePath());
        	System.out.println("upload:-->"+key);
        	key = key.replaceAll("\\\\", "/");
            cloudFile = cloudFileOperator.storeFile(file,key+"&");
            return cloudFile;
        } catch (Exception e) {
            System.out.println("store file failed: " + file.getAbsolutePath());
            e.printStackTrace();
        } finally {
            return cloudFile;
        }
    }

    private void stop() {
        stop = true;
    }

    public static void main(String[] args) throws Exception {
//        if (args.length < 1) {
//            errorExit("Need a config file.", 1);
//        }
//        File configFile = new File(args[0]);
//        if (!configFile.exists()) {
//            errorExit("Config doesn't exit.", 1);
//        }
//        Properties config = new Properties();
//        config.load(new FileInputStream(configFile));

        
//        accessToken=eQI8p9dDFM-OHbI7QCJcGTJ8OcWAaJHM4eD7r7D0
//        		secretToken=DsLSadqv7lSBaWw7dZj16FXIetErsJXGUcwDXCA7
//        		bucketName=vr-data-test-1
//        		rootPath=d:/home/qwe
        accessToken = "eQI8p9dDFM-OHbI7QCJcGTJ8OcWAaJHM4eD7r7D0";
        secretToken = "DsLSadqv7lSBaWw7dZj16FXIetErsJXGUcwDXCA7";
        bucketName = "vr-data-test-1";
        rootDirPath = "d:/home/qwe";
        threadNum = 8;

        if (accessToken == null) {
            errorExit("accessToken required.", 1);
        }
        if (secretToken == null) {
            errorExit("secretToken required.", 2);
        }
        if (bucketName == null) {
            errorExit("bucketName required", 3);
        }
        if (rootDirPath == null) {
            errorExit("rootPath required", 4);
        }
        if (redisIp == null) {
            errorExit("need redisIp.", 5);
        }
        if (redisPort == null) {
            errorExit("need redisPort", 6);
        }
        if (hashMapName == null) {
            errorExit("need hashMapName", 7);
        }
        if (threadNum <= 0) {
            errorExit("threadNum must >= 1", 8);
        }

        File rootDir = new File(rootDirPath);
        if (!rootDir.exists()) {
            System.err.println("Root path doesn't exit.");
            System.exit(5);
            return;
        }
        if (!rootDir.isDirectory()) {
            System.err.println("Root path must be a directory.");
            System.exit(6);
            return;
        }
        if (!rootDirPath.endsWith("/")) {
            rootDirPath += "/";
            rootDirPathLen = rootDirPath.length();
        } else {
            rootDirPathLen = rootDirPath.length();
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0 ; i < threadNum ; i++) {
            LocalFileSync localFileSync = new LocalFileSync();
            executorService.execute(localFileSync);
            localFileSyncList.add(localFileSync);
        }
        executorService.shutdown();
        countDownLatch = new CountDownLatch(threadNum);
        processDir(rootDir);
        for (LocalFileSync localFileSync : localFileSyncList) {
            localFileSync.stop();
        }
        countDownLatch.await();
    }

    private static CloudFileOperator getCloudFileOperator() {
        AuthKey authKey = new AuthKey(accessToken, secretToken);
        return CloudFileManager.getFileOperator(storageServiceProvider, new CloudFileManager.InitPackage(bucketName, authKey));
    }

    private static void processDir(File file) {
        if (!file.isHidden()) {
            try {
                if (file.isFile()) {
                    fileQueue.add(file);
                } else if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File _file : files) {
                        processDir(_file);
                    }
                }
            } catch (Exception e) {
                System.err.println("Failed to process: " + file.getAbsolutePath());
                e.printStackTrace();
            }
        }
    }

    private static String getRedisKey(String fullPath) {
        return fullPath.substring(rootDirPathLen);
    }

    private static void errorExit(String msg, int code) {
        System.err.println(msg);
        System.exit(code);
        return;
    }
}
