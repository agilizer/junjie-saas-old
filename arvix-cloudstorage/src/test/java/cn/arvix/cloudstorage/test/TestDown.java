package cn.arvix.cloudstorage.test;

import org.junit.Test;

import cn.arvix.cloudstorage.file.AuthKey;
import cn.arvix.cloudstorage.file.CloudFileManager;
import cn.arvix.cloudstorage.file.CloudFileOperator;
import cn.arvix.cloudstorage.file.StorageServiceProvider;

public class TestDown {
	private static StorageServiceProvider storageServiceProvider = StorageServiceProvider.QINIU;
	private static CloudFileOperator cloudFileOperator;
	private static String accessToken = "onIcMZEEnIFGr1u49cjRkCJJbuep7SKsvDbSuZSf";
	private static String secretToken = "lpwkbVFXR6an3_Sn2133kwYFOwbyzveBu4S73YPQ";
	private static String bucketName = "whydebucket";
	private static String defaultDomain = "7xt43s.com1.z0.glb.clouddn.com";

	@Test
	public void test() throws Exception {
		init();
		testGetUrl();
	}

	public void init() throws Exception {
		System.err.println("Init...");
		AuthKey authKey = new AuthKey(accessToken, secretToken);
		cloudFileOperator = CloudFileManager.getFileOperator(storageServiceProvider,
				new CloudFileManager.InitPackage(bucketName, authKey));
		System.err.println("Inited.");
	}
	
	public void testGetUrl() throws Exception {
        System.err.println("testDownload...");
        //http://7xswnm.com2.z0.glb.clouddn.com/file-6bff993d-5367-420c-8bdc-e2d0e89e3caf?e=1460713879&token=onIcMZEEnIFGr1u49cjRkCJJbuep7SKsvDbSuZSf:jSFKMFxtWrGcof_Oo-tUz-r0amc=
        System.out.println("Private bucket download url: " + cloudFileOperator.getDownloadUrl("452aMkAmjrQ/pan/low/e7919e4dd4574d6ba78594c5072c6ddb_skybox0.jpg", defaultDomain));
      
    }
}
