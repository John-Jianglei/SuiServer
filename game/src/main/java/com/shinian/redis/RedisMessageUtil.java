package com.shinian.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.shinian.util.Config;

public final class RedisMessageUtil {
	private final static RedisMessageUtil POOL_UTIL = new RedisMessageUtil();

	public static RedisMessageUtil getInstance() {
		return POOL_UTIL;
	}

	private RedisMessageUtil() {
	}

	private JedisPool pool;
	private JedisPool getJedisPool() {
		synchronized (this) {
			if (pool == null) {
				init();
			}
		}
		return pool;
	}

	private void init() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxActive(300);
			config.setMaxIdle(30);
			config.setMaxWait(1000);
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, Config.REDIS_HOST, Config.REDIS_PORT, Config.REDIS_TIMEOUT, Config.REDIS_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("init redis error");
		}
	}

	/**
	 * 获取Redis连接
	 * 
	 * @return
	 */
	public Jedis getConnection() {
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
			jedis.auth(Config.REDIS_PASSWORD);
			jedis.select(0);
		} catch (Exception e) {
			if(jedis != null)
				getJedisPool().returnBrokenResource(jedis);
			e.printStackTrace();
			throw new RuntimeException("连接Redis异常.",e);
		}
		return jedis;

	}

	/**
	 * 关闭Redis连接
	 * 
	 * @param jedis
	 */
	public void closeConnection(Jedis jedis) {
		if (null != jedis) {
			try {
				getJedisPool().returnResource(jedis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
