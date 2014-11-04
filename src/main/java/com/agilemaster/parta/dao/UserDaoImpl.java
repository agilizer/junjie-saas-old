package com.agilemaster.parta.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.agilemaster.parta.entity.User;
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class UserDaoImpl implements UserDao {
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public void saveOrUpdate(User user) {
		redisTemplate.boundHashOps(NAME_SPACE).put(user.getUsername(), user);
	}

	@Override
	public void delete(User user) {
		redisTemplate.boundHashOps(NAME_SPACE).delete(user.getUsername());
	}

	@Override
	public User findByUsername(String username) {
		User user = (User) redisTemplate.boundHashOps(NAME_SPACE).get(username);
		return user;
	}

	@Override
	public Long count() {
		return redisTemplate.boundHashOps(NAME_SPACE).size();
	}

}
