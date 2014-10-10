package com.junjie.commons.file.qiniu;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.EncoderException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.junjie.commons.file.JunjieFile;
import com.junjie.commons.file.JunjieFileOperation;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.URLUtils;
/**
 * 七牛存储实现
 * @author abel.lee
 */
@Service
public class JunjieFileOperationQiNiuImpl implements JunjieFileOperation{

	private String accessKey="e87kvjl4DW7ux7maTuV_vFwI6Nl_Eb4coSBLxaJo";
	private String secretKey="gZokWEHzaSoRUOhcjSlMrYxNG6yluatBG0W3uRTn";
	private String domain="junjie-file.qiniudn.com";
	private String uptoken ;
	
	@PostConstruct
	public void init() throws AuthException, JSONException {
		uptoken = genUptoken();
	}
	private String genUptoken() throws AuthException, JSONException{
		Config.ACCESS_KEY = accessKey;
		Config.SECRET_KEY = secretKey;
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		String bucketName = "junjie-file";
		PutPolicy putPolicy = new PutPolicy(bucketName);
		putPolicy.expires = 3600*24*30;//一个月
		return  putPolicy.token(mac);
	}
	private String genDownloadUrl(String key) throws AuthException, JSONException, EncoderException{
		Config.ACCESS_KEY = accessKey;
		Config.SECRET_KEY = secretKey;
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        String baseUrl = URLUtils.makeBaseUrl(domain, key);
        GetPolicy getPolicy = new GetPolicy();
        getPolicy.expires = 3600*24*30;//一个月
        return getPolicy.makeRequest(baseUrl, mac);
	}
	@Override
	public String storeFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String storeFile(InputStream inputStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream accessFileStream(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accessFileStream(String key, OutputStream outStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String accessFileUrl(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JunjieFile accessFileInfo(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream accessFileStream(Map<String, String> accessInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accessFileStream(Map<String, String> accessInfo,
			OutputStream outStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String accessFileUrl(Map<String, String> accessInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream accessFileStream(JunjieFile junjieFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void accessFileStream(JunjieFile junjieFile, OutputStream outStream) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String accessFileUrl(JunjieFile junjieFile) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(String key) {
		// TODO Auto-generated method stub
		
	}

}
