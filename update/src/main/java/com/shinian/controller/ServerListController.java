package com.shinian.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shinian.service.ServerListService;
import com.shinian.util.RequestParamUtil;


@Controller
@RequestMapping(value = "") 
public class ServerListController {
	@Autowired
	ServerListService serverListService;
	
	private static Logger log = Logger.getLogger(ServerListController.class);
	
	@RequestMapping(value = "/getServerUrl", method = RequestMethod.POST)
	public void onGetServerUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();
		
		String jsonStr = RequestParamUtil.getRequestContents(request,response);
		log.info("request:" + jsonStr);
		
		serverListService.onGetServerUrl(request,response,jsonStr);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	
	
}