package com.junjie.commons.test.file.qiniu;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.junjie.commons.file.JunjieFile;
import com.junjie.commons.file.JunjieFileOperation;
import com.junjie.commons.test.BaseTest;

public class QiniuFileTest extends BaseTest{

	@Autowired
	JunjieFileOperation junjieFileOperation;
	@Test
	public void test() {
		File file = new File("src/test/resources/电子科技大学非全日制硕士招生简章.doc");
		JunjieFile junjieFile = junjieFileOperation.storeFile(file);
		String key = junjieFile.getKey();
		log.info("junjieFile->"+junjieFile.getKey());
		String downloadUrl = junjieFileOperation.accessFileUrl(key);
		log.info("downloadUrl->"+downloadUrl);
		
	}
	@Test
	public void testPhoto(){
		File file = new File("src/test/resources/photo.png");
		JunjieFile junjieFile = junjieFileOperation.storeFile(file);
		String key = junjieFile.getKey();
		log.info("junjieFile->"+junjieFile.getKey());
		String downloadUrl = junjieFileOperation.accessFileUrl(key);
		log.info("downloadUrl->"+downloadUrl);
	}

}
