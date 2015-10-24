package com.shinian.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shinian.service.DispatcherService;


@Controller
@RequestMapping(value = "")
public class GameController {
	@Autowired
	DispatcherService dispatcherService;
	private static Logger log = Logger.getLogger("game");
	
	@RequestMapping(value = "/game", method = RequestMethod.POST)
	public void onGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();	
		dispatcherService.dispatch(request,response);		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
}