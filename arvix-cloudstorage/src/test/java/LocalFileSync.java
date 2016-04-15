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
    private static String accessToken = "onIcMZEEnIFGr1u49cjRkCJJbuep7SKsvDbSuZSf";
    private static String secretToken = "lpwkbVFXR6an3_Sn2133kwYFOwbyzveBu4S73YPQ";
    private static String bucketName = "whydebucket";

    private static String outputPath = "/tmp/cloud-storage-sync.key.list";
    private static File outputFile = null;
    private static BufferedWriter outputWriter = null;

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Need a specific root path.");
            System.exit(1);
            return;
        }
        File rootDir = new File(args[0]);
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
        if (args.length == 2) {
            outputPath = args[1];
        }
        outputFile = new File(outputPath);
        if (!outputFile.isFile()) {
            outputFile = null;
        } else {
            outputWriter = new BufferedWriter(new FileWriter(outputFile));
        }
        AuthKey authKey = new AuthKey(accessToken, secretToken);
        cloudFileOperator = CloudFileManager.getFileOperator(storageServiceProvider, new CloudFileManager.InitPackage(bucketName, authKey));
        File[] files = rootDir.listFiles();
        for (File file : files) {
            processDir(file);
        }

        if (outputWriter != null) {
            outputWriter.close();
        }
    }

    private static void processDir(File file) {
        if (!file.isHidden()) {
            try {
                if (file.isFile()) {
                    processFile(file);
                } else if (file.isDirectory()) {
                    processDir(file);
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
        }
    }
}
