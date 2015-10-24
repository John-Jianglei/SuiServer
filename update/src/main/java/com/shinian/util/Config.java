package com.shinian.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;


public class Config {	
	public static String ACCOUNT_URL;
	public static String STATIC_URL;
	
	public static ApplicationContext ctx = null;
	private static Logger log = Logger.getLogger(Config.class);
	
	public static final void initConfig() 
	{
		ResourceBundle config = ResourceBundle.getBundle("config");
		String env = config.getString("env");
		String prefix = env + ".";
		
		ACCOUNT_URL = config.getString(prefix + "account_server.url");
		STATIC_URL = config.getString(prefix + "static_server.url");
		
		log.info("initConfig:ready.");
		
	}
}
