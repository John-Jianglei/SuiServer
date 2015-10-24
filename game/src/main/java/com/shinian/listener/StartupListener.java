package com.shinian.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.shinian.dao.impl.SimpleJdbcTemplate;
import com.shinian.dao.impl.WebConstant;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Config;


public class StartupListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		try {
			ServletContext context = event.getServletContext();
			ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

			// Spring 配置文件的相关信息配置到全局变量中
			Config.ctx = ctx;
			Config.initConfig();
			
			DruidDataSource commonDataSource = (DruidDataSource) ctx.getBean("commonDataSource");
			DruidDataSource gameDataSource = (DruidDataSource) ctx.getBean("gameDataSource");
			
			
			WebConstant.commonJdbc = new SimpleJdbcTemplate(commonDataSource);
			WebConstant.gameJdbc = new SimpleJdbcTemplate(gameDataSource);
				
			RedisCacheUtil redisCacheUtil = (RedisCacheUtil) Config.ctx.getBean("redisCacheUtil");
			redisCacheUtil.initCacheInfo();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
