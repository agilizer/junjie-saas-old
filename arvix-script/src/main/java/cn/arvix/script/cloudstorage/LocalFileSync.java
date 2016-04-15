package cn.arvix.script.cloudstorage;

import cn.arvix.cloudstorage.file.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by wanghaiyang on 16/4/15.
 */
public class LocalFileSync {
    private static StorageServiceProvider storageServiceProvider = StorageServiceProvider.QINIU;
    private static CloudFileOperator cloudFileOperator;
    private static String accessToken = "";
    private static String secretToken = "";
    private static String bucketName = "";

    private static String  rootDirPath = "";
    private static String outputPath = "/tmp/cloud-storage-sync.key.list";
    private static File outputFile = null;
    private static BufferedWriter outputWriter = null;

    public static void main(String[] args) throws Exception {
        accessToken = System.getProperty("accessToken");
        secretToken = System.getProperty("secretToken");
        bucketName = System.getProperty("bucketName");
        rootDirPath = System.getProperty("rootPath");
        String outP = System.getProperty("outputPath");
        outputPath = outP == null ? outputPath : outP;

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
        if (outP == null) {
            System.out.println("use defaut output path: " + outputPath);
        }

        File rootDir = new File(rootDirPath);
        if (!rootDir.exists()) {
            System.err.println("Root path doesn't exit.");
            System.exit(2);
            return;
        }
        if (!rootDir.isDirectory()) {
            System.err.println("Root path must be a directory.");
            System.exit(3);
            return;
        }

        outputFile = new File(outputPath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        if (!outputFile.isFile()) {
            outputFile = null;
        } else {
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
        }
        AuthKey authKey = new AuthKey(accessToken, secretToken);
        cloudFileOperator = CloudFileManager.getFileOperator(storageServiceProvider, new CloudFileManager.InitPackage(bucketName, authKey));
        processDir(rootDir);
        if (outputWriter != null) {
            outputWriter.close();
        }
    }

    private static void errorExit(String msg, int code) {
        System.err.println(msg);
        System.exit(code);
        return;
    }

    private static void processDir(File file) {
        if (!file.isHidden()) {
            try {
                if (file.isFile()) {
                    processFile(file);
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

    private static void processFile(File file) throws Exception {
        CloudFile cloudFile = cloudFileOperator.storeFile(file);
        if (outputWriter != null) {
            outputWriter.write(file.getAbsolutePath() + "," + cloudFile.getKey());
            outputWriter.newLine();
            outputWriter.flush();
        }
    }
}
