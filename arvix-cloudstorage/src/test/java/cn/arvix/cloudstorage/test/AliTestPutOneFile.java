package cn.arvix.cloudstorage.test;

import java.io.File;

import cn.arvix.cloudstorage.file.AuthKey;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class AliTestPutOneFile {

	public static void main(String args[]) {
		String accessToken = "a7OZhIQTDgIemjwz";
		String secretToken = "moAT09EaU1Pz4d4JuA9GZ1mwRw1aQn";
		String endPoint = "http://oss-cn-beijing.aliyuncs.com";
		String bucketName = "vr-data-test";
		AuthKey authKey = new AuthKey(accessToken, secretToken);
		OSSClient ossClient = new OSSClient(endPoint, authKey.getAccessKey(),
				authKey.getSecretKey());
		ObjectMetadata meta = new ObjectMetadata();
		File file = new File("/home/abel/test.json");
		// 设置ContentLength为1000
		meta.setContentLength(file.length());
		meta.setContentType("application/json;charset=UTF-8");
		ossClient.putObject(bucketName, "452aMkAmjrQ/files", file,meta);
		System.out.print("finish--->");
	}
}
