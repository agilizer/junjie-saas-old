package cn.arvix.script.cloudstorage;

import cn.arvix.cloudstorage.file.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanghaiyang on 16/4/15.
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
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static ConcurrentLinkedQueue<File> fileQueue = new ConcurrentLinkedQueue<File>();
    private static List<LocalFileSync> localFileSyncList = new ArrayList<LocalFileSync>();
    private static Jedis jedis = null;
    private static CountDownLatch countDownLatch = null;

    private CloudFileOperator cloudFileOperator = null;
    private Jedis redisClient = null;
    private volatile boolean stop = false;

    public LocalFileSync(Jedis jedis) {
        this.redisClient = jedis;
        cloudFileOperator = getCloudFileOperator();
    }

    @Override
    public void run() {
        while (!stop || fileQueue.size() > 0) {
            File file = fileQueue.poll();
            if (file != null) {
                CloudFile cloudFile = processFile(file);
                if (cloudFile != null) {
                    writeUploadRecord(cloudFile, file);
                }
            }
        }
        jedis.close();
        countDownLatch.countDown();
    }

    private void writeUploadRecord(CloudFile cloudFile, File file) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(cloudFile);
            redisClient.hset(hashMapName, getRedisKey(file.getAbsolutePath()), json);
        } catch (Exception e) {
            System.out.println("write data into redis failed: " + file.getAbsolutePath() + " | " + json);
            e.printStackTrace();
        }
    }

    private CloudFile processFile(File file) {
        CloudFile cloudFile = null;
        try {
            cloudFile = cloudFileOperator.storeFile(file);
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
        if (args.length < 1) {
            errorExit("Need a config file.", 1);
        }
        File configFile = new File(args[0]);
        if (!configFile.exists()) {
            errorExit("Config doesn't exit.", 1);
        }
        Properties config = new Properties();
        config.load(new FileInputStream(configFile));

        accessToken = config.getProperty("accessToken");
        secretToken = config.getProperty("secretToken");
        bucketName = config.getProperty("bucketName");
        rootDirPath = config.getProperty("rootPath");
        redisIp = config.getProperty("redisIp");
        redisPort = config.getProperty("redisPort");
        hashMapName = config.getProperty("hashMapName");
        threadNum = Integer.valueOf(config.getProperty("threadNum"));

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
            Jedis jedis = new Jedis(redisIp, Integer.valueOf(redisPort));
            LocalFileSync localFileSync = new LocalFileSync(jedis);
            executorService.execute(localFileSync);
            localFileSyncList.add(localFileSync);
        }
        executorService.shutdown();
        countDownLatch = new CountDownLatch(threadNum);
        jedis = new Jedis(redisIp, Integer.valueOf(redisPort));
        processDir(rootDir);
        for (LocalFileSync localFileSync : localFileSyncList) {
            localFileSync.stop();
        }
        jedis.close();
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
                    if (!jedis.hexists(hashMapName, getRedisKey(file.getAbsolutePath()))) {
                        fileQueue.add(file);
                    }
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
