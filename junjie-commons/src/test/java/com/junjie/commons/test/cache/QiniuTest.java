package com.junjie.commons.test.cache;

import java.io.File;

import org.json.JSONException;
import org.junit.Test;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class QiniuTest {

	@Test
	public void test() throws AuthException, JSONException {
		Config.ACCESS_KEY = "e87kvjl4DW7ux7maTuV_vFwI6Nl_Eb4coSBLxaJo";
		Config.SECRET_KEY = "gZokWEHzaSoRUOhcjSlMrYxNG6yluatBG0W3uRTn";
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// 请确保该bucket已经存在
		String bucketName = "junjie-file";
		PutPolicy putPolicy = new PutPolicy(bucketName);
		String uptoken = putPolicy.token(mac);
		PutExtra extra = new PutExtra();
		String key = "";
		File file;
		String localFile = "/home/asdtiang/电子科技大学非全日制硕士招生简章.doc";
		PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
		if (ret.ok()) {
			System.out.println("Successfully upload the file."+ret.getHash()+" aa-"+ret.getKey()+"-aaa");
		} else {
			System.out.println("opps, error : " + ret);
			return;
		}
	}

}
