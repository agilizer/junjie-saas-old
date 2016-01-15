package com.junjie.commons.utils;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.SerializationUtils;

import com.alibaba.fastjson.JSON;

public class FastJsonSerializer<T> implements RedisSerializer<T> {
	
	Class<T> type;
	public FastJsonSerializer(Class<T> type) {
		this.type = type;
	}
	@Override
	public byte[] serialize(T t) throws SerializationException {
		return JSON.toJSONBytes(t);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (bytes==null) {
			return null;
		}
		return JSON.parseObject(bytes,type);
	}

}
