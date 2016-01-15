package com.junjie.commons.cache;

import org.springframework.cache.Cache;

/**
 * add expiration method
 * 
 * @author abel.lee
 *
 */
public interface JunjieRedisCache extends Cache {
	/**
	 * 
	 * @param key
	 * @param value
	 * @param expiration
	 */
	void put(Object key, Object value,Long expirationSeconds);

}
