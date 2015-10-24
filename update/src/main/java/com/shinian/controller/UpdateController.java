package com.shinian.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shinian.service.UpdateService;
import com.shinian.util.RequestParamUtil;


@Controller
@RequestMapping(value = "") 
public class UpdateController {
	@Autowired
	UpdateService updateService;
	
	private static Logger log = Logger.getLogger(UpdateController.class);
	
	@RequestMapping(value = "/checkUpdate", method = RequestMethod.POST)
	public void onUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();
		
		String jsonStr = RequestParamUtil.getRequestContents(request,response);
		log.info("request:" + jsonStr);
		
		updateService.onCheckUpdate(request,response,jsonStr);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	@RequestMapping(value = "/getServerList", method = RequestMethod.POST)
	public void onGetServerList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();
		
		String jsonStr = RequestParamUtil.getRequestContents(request,response);
		log.info("request:" + jsonStr);
		
		updateService.onGetServerList(request,response,jsonStr);
		
		long end = System.currentTimeMillis();
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	@RequestMapping(value = "/getUpdateInfo", method = RequestMethod.POST)
	public void onGetUpdateWay(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();
		
		String jsonStr = RequestParamUtil.getRequestContents(request,response);
		log.info("request:" + jsonStr);
		
		updateService.onGetUpdateInfo(request,response,jsonStr);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}	
	
}