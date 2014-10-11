package com.junjie.commons.file.qiniu;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.EncoderException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junjie.commons.file.JunjieFile;
import com.junjie.commons.file.JunjieFileOperation;
import com.junjie.commons.file.JunjieFileUtil;
import com.junjie.commons.utils.FastJsonSerializer;
import com.junjie.commons.utils.JunjieCounter;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;
import com.qiniu.api.rs.URLUtils;

/**
 * 七牛存储实现
 * @author abel.lee
 */
@Service
public class JunjieFileOperationQiNiuImpl implements JunjieFileOperation,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6890377656407660739L;
	private static final Logger log = LoggerFactory
			.getLogger(JunjieFileOperationQiNiuImpl.class);
	/**
	 * 
	 */
	private String accessKey = "e87kvjl4DW7ux7maTuV_vFwI6Nl_Eb4coSBLxaJo";
	private String secretKey = "gZokWEHzaSoRUOhcjSlMrYxNG6yluatBG0W3uRTn";
	private String domain = "junjie-file.qiniudn.com";
	private String bucketName = "junjie-file";
	private Mac mac ;
	private RSClient client;

	/**
	 * 上传token过期时间
	 */
	private long uptokenExpiresSeconds = 3600 * 24 * 30;// 一个月
	/**
	 * 下载url过期时间
	 */
	private int downloadExpiresSeconds = 3600 * 24 * 30;// 一个月
	/**
	 * 请求失败时重试次数
	 */
	private int retryTimes=3;
	private String uptoken;

	@Autowired
	private JunjieCounter junjieCounter;
	@Autowired
	private RedisConnectionFactory factory;
	private RedisTemplate<String, JunjieFile> redisTemplate;

	public class AccessInfo implements Serializable{
		private static final long serialVersionUID = 3691322655005427557L;
		public AccessInfo(){
			
		}
		public AccessInfo(String key,String hash){
			this.key = key;
			this.hash = hash;
		}
		String key;
		String hash;
	    String downloadUrl=null;
	    Long downloadUrlGenSeconds = 0l;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getHash() {
			return hash;
		}
		public void setHash(String hash) {
			this.hash = hash;
		}
		public String getDownloadUrl() {
			return downloadUrl;
		}
		public void setDownloadUrl(String downloadUrl) {
			this.downloadUrl = downloadUrl;
		}
		public Long getDownloadUrlGenSeconds() {
			return downloadUrlGenSeconds;
		}
		public void setDownloadUrlGenSeconds(Long downloadUrlGenSeconds) {
			this.downloadUrlGenSeconds = downloadUrlGenSeconds;
		}
	}
	@PostConstruct
	public void init() throws AuthException, JSONException {
		redisTemplate = new RedisTemplate<String, JunjieFile>();
		redisTemplate.setKeySerializer(new FastJsonSerializer<String>(String.class));
		redisTemplate.setValueSerializer(new FastJsonSerializer<JunjieFile>(JunjieFile.class));
		redisTemplate.setExposeConnection(true);
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.afterPropertiesSet();
		
		Config.ACCESS_KEY = accessKey;
		Config.SECRET_KEY = secretKey;
		mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		client = new RSClient(mac);
		uptoken = genUptoken();
	}

	private String genUptoken() throws AuthException, JSONException {
		PutPolicy putPolicy = new PutPolicy(bucketName);
		putPolicy.expires = uptokenExpiresSeconds;// 一个月
		return putPolicy.token(mac);
	}

	private String genDownloadUrl(String key) throws AuthException,
			JSONException, EncoderException {
		String baseUrl = URLUtils.makeBaseUrl(domain, key);
		GetPolicy getPolicy = new GetPolicy();
		getPolicy.expires = (int) downloadExpiresSeconds;
		return getPolicy.makeRequest(baseUrl, mac);
	}
	private JunjieFile storeFilePri(File file,InputStream inputStream,String fileName){
		PutExtra extra = new PutExtra();
		String key = junjieCounter.genUniqueKey(KEY_PREFIX)+"-"+fileName;
		JunjieFile junjieFile = null;
		PutRet ret = null;
		for (int i = 0; i < retryTimes; i++) {
			if(file!=null){
				ret = IoApi.putFile(uptoken, key, file, extra);
			}else{
				ret = IoApi.Put(uptoken, key, inputStream, extra);
			}
			if (ret.ok()) {
				log.info("upload file ok:" + file.getAbsolutePath());
				junjieFile = new JunjieFile();
				AccessInfo accessInfo = new AccessInfo(ret.getKey(),ret.getHash());
				junjieFile.setAccessInfo(accessInfo);
				junjieFile.setKey(key);
				junjieFile.setName(fileName);
				junjieFile.setLength(file.length());
				junjieFile.setJunjieFileType(JunjieFileUtil.genFileType(fileName));
				junjieFile.setDateCreated(System.currentTimeMillis());
				junjieFile.setLastAccessed(System.currentTimeMillis());
				log.info(JSON.toJSONString(junjieFile));
				redisTemplate.opsForValue().set(key, junjieFile);
				break;
			} else {
				// http://developer.qiniu.com/docs/v6/api/reference/codes.html
				// 401错误表示认证失败，重先生成uptoken
				if (ret.getStatusCode() == 401) {
					try {
						genUptoken();
					} catch (AuthException | JSONException e) {
						log.error("gen uptoken error!", e);
					}
				}
			}
		}
		return junjieFile;
	}

	@Override
	public JunjieFile storeFile(File file) {
		JunjieFile junjieFile = storeFilePri(file,null,file.getName());
		return junjieFile;
	}

	@Override
	public JunjieFile storeFile(InputStream inputStream,String fileName) {
		JunjieFile junjieFile = storeFilePri(null,inputStream,fileName);
		return junjieFile;
	}


	private AccessInfo genAccessInfoFromJsonObject(JSONObject jsonObject){
		AccessInfo accessInfo = new AccessInfo(jsonObject.getString("key"),jsonObject.getString("hash"));
		accessInfo.downloadUrl = jsonObject.getString("downloadUrl");
		accessInfo.downloadUrlGenSeconds = jsonObject.getLong("downloadUrlGenSeconds");
		return accessInfo;
		
	}
	/**
	 * 获取访问地址，如果地址没有缓存，则生成地址，然后缓存到redis
	 * 如果地址有缓存，先查看缓存地址是否过期，如果过期，则请求七牛服务。
	 */
	@Override
	public String accessFileUrl(String key) {
		JunjieFile junjieFile = redisTemplate.boundValueOps(key).get();
		AccessInfo accessInfo = genAccessInfoFromJsonObject((JSONObject)junjieFile.getAccessInfo());
		String downloadUrl = "";
		long currentSeconds = System.currentTimeMillis()/1000;
		if(null!=accessInfo.downloadUrl&&(accessInfo.downloadUrlGenSeconds-currentSeconds)<100){
			downloadUrl = accessInfo.downloadUrl;
		}else{
			try {
				downloadUrl = genDownloadUrl(accessInfo.key);
				accessInfo.downloadUrl = downloadUrl;
				accessInfo.downloadUrlGenSeconds = currentSeconds;
				junjieFile.setAccessInfo(accessInfo);
				redisTemplate.boundValueOps(key).set(junjieFile);
			} catch (AuthException | JSONException | EncoderException e) {
				log.error("gen downloadUrl error: key-->"+key,e);
			}
		}
		return downloadUrl;
	}

	/**
	 * 获取访问地址，如果地址没有缓存，则生成地址，然后缓存到redis
	 * 如果地址有缓存，先查看缓存地址是否过期，如果过期，则请求七牛服务。
	 */
	@Override
	public String accessFileUrl(JunjieFile junjieFile) {
		return accessFileUrl(junjieFile.getKey());
	}

	@Override
	public void delete(String key) {
        client.delete(bucketName, key);
	}

}
