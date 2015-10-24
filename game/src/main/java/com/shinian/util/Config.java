package com.shinian.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;


public class Config {
	public static String REDIS_HOST;
	public static int REDIS_PORT;
	public static int REDIS_TIMEOUT;
	public static String REDIS_PASSWORD;
	public static String ACCOUNT_URL;
	public static int SERVERID;
	
	public static ApplicationContext ctx = null;
	private static Logger log = Logger.getLogger(Config.class);
	
	public static final void initConfig() {
		try {
			ResourceBundle config = ResourceBundle.getBundle("config");
			String env = config.getString("env");
			String prefix = env + ".";
			
			REDIS_HOST = config.getString(prefix + "redis.host");
			REDIS_PORT = Integer.parseInt(config.getString(prefix + "redis.port"));
			REDIS_TIMEOUT = Integer.parseInt(config.getString(prefix + "redis.timeout"));
			REDIS_PASSWORD = config.getString(prefix + "redis.password");

			ACCOUNT_URL = config.getString(prefix + "account_server.url");
			log.info("initConfig:ready.");
			
			SERVERID = Integer.valueOf( config.getString( prefix+"game_server.id" ));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("initConfig: failed!");
		}

	}
}
