package com.agilemaster.partbase.service.oauth2;

import javax.annotation.PostConstruct;

import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junjie.commons.cache.JunjieRedisCache;
import com.junjie.commons.cache.JunjieRedisCacheManager;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-2-17
 * <p>
 * Version: 1.0
 */
@Service
public class OAuthServiceImpl implements OAuthService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OAuthServiceImpl.class);
	private static final String REDIS_SHIRO_CACHE = "oauth-cache:";
	@Autowired
	private JunjieRedisCacheManager junjieRedisCacheManager;
	private JunjieRedisCache cache  = null;
	private long expireIn = 3600l;
	@PostConstruct
    public void init(){
    	    cache = junjieRedisCacheManager.getCache(REDIS_SHIRO_CACHE);
    }

	@Autowired
	private ClientService clientService;

	@Override
	public void addAuthCode(String authCode, String username) {
		try {
			cache.put(authCode, username,getExpireIn());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("put cache error", e);
		}
	}

	@Override
	public void addAccessToken(String accessToken, String username) {
		try {
			cache.put(accessToken, username,getExpireIn());
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("put cache error", e);
		}
	}

	@Override
	public String getUsernameByAuthCode(String authCode) {
		return getFromCache(authCode);
	}

	private String getFromCache(String key) throws CacheException {
		String result = cache.get(key,String.class);
		LOGGER.info("value : " + key + " from cache " + "result-->" + result);
		return result;
	}

	@Override
	public String getUsernameByAccessToken(String accessToken) {
		return getFromCache(accessToken);
	}

	@Override
	public boolean checkAuthCode(String authCode) {
		return getFromCache(authCode) != null;
	}

	@Override
	public boolean checkAccessToken(String accessToken) {
		return getFromCache(accessToken) != null;
	}

	@Override
	public boolean checkClientId(String clientId) {
		return clientService.findByClientId(clientId) != null;
	}

	@Override
	public boolean checkClientSecret(String clientSecret) {
		return clientService.findByClientSecret(clientSecret) != null;
	}

	public long  getExpireIn() {
		return  expireIn;
	}

	public void setExpireIn(long expireIn) {
		this.expireIn = expireIn;
	}

	

}
