package com.shinian.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.shinian.service.AccountService;
import com.shinian.util.Message;
import com.shinian.util.MessageRespVo;
import com.shinian.util.RequestParamUtil;
import com.shinian.util.ResponseParamUtil;
import com.shinian.vo.AccToken;
import com.shinian.vo.CommonReqVo;


@Controller
@RequestMapping(value = "") 
public class AccountController {
	@Autowired
	AccountService accountService;
	
	private static Logger log = Logger.getLogger(AccountController.class);
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void onRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo result = new MessageRespVo();
		String jsonStr = "";
		String jsonResp = "";
		try {
			//log client request
			jsonStr = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonStr + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonStr, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.register(request,response, jsonStr);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void onLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo  result = new MessageRespVo();
		String jsonStr = "";
		String jsonResp = "";
		try {
			//log client request
			jsonStr = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonStr + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonStr, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.login(request,response, jsonStr);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	@RequestMapping(value = "/bindingMail", method = RequestMethod.POST)
	public void onBindingMail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo  result = new MessageRespVo();
		String jsonReq = "";
		String jsonResp = "";
		try {
			//log client request
			jsonReq = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonReq + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.bindingMail(request, response, jsonReq);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	@RequestMapping(value = "/getEmail", method = RequestMethod.POST)
	public void ongetEmail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo  result = new MessageRespVo();
		String jsonReq = "";
		String jsonResp = "";
		try {
			//log client request
			jsonReq = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonReq + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.getEmail(request, response, jsonReq);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	@RequestMapping(value = "/bindEmail", method = RequestMethod.POST)
	public void onbindEmail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo  result = new MessageRespVo();
		String jsonReq = "";
		String jsonResp = "";
		try {
			//log client request
			jsonReq = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonReq + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.bindEmail(request, response, jsonReq);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	public void onUpdatePwd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo  result = new MessageRespVo();
		String jsonReq = "";
		String jsonResp = "";
		try {
			//log client request
			jsonReq = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonReq + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.updatePwd(request, response, jsonReq);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	@RequestMapping(value = "/findPwd", method = RequestMethod.POST)
	public void onFindPwd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		long begin = System.currentTimeMillis();		
		MessageRespVo  result = new MessageRespVo();
		String jsonReq = "";
		String jsonResp = "";
		try {
			//log client request
			jsonReq = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:{" + jsonReq + "}");
		} catch (UnsupportedEncodingException e) {
			CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
			result.setTs( crv.getTs() );
			result.setCode( Message.msgCode_exception );
			result.setMsg( Message.msg_exception );
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}

		result = accountService.findPwd(request, response, jsonReq);
		jsonResp = JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response, jsonResp);
		
		long end = System.currentTimeMillis();		
		log.info("execution time：" + (end-begin) + "ms");
	}
	
	@RequestMapping(value = "/checkToken", method = RequestMethod.GET)
	public void onGetToken(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int uid = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("uid"),"0"));
		String token = StringUtils.defaultIfEmpty(request.getParameter("token"), "");
		
//		if(uid == 0 || "".equals(token)){
//			String resp = "{\"result\":\"0\"}";
//			ResponseParamUtil.writeJsonMessage(response,resp);
//			return;
//		}
//		
		AccToken result = accountService.checkToken(uid,token);
		
		String resp =JSON.toJSONString(result);
		ResponseParamUtil.writeJsonMessage(response,resp);			
	}
	
	
	
}