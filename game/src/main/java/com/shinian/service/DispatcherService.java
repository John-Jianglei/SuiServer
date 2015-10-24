package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.util.Message;
import com.shinian.util.RequestParamUtil;
import com.shinian.util.ResponseParamUtil;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;

@Service
public class DispatcherService {
	@Autowired
	LoginService loginService;
	
	@Autowired
	SignupService signupService;
	
	@Autowired
	NpcInfoService npcInfoService;
	
	@Autowired
	ArmyInfoService armyInfoService;
	
	
	private static Logger log = Logger.getLogger("game");
	
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response){
		String jsonStr = "";
		String jsonResp = "";
		MessageRespVo result = new MessageRespVo();
		
		try {
			//log client request
			jsonStr = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:" + jsonStr);
			
			int acid = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("acid"),"0"));
			String ver = StringUtils.defaultIfEmpty(request.getParameter("ver"), "");
			log.info("request acid = " + acid + ",ver=" + ver);
			
			switch (acid) {
			case 1001:
				result = loginService.login(request, response, jsonStr);///登录
				break;
			
			case 1002:
				result = signupService.signup(request, response, jsonStr);///Create game player
				break;
			
			case 1003:
				result = npcInfoService.getNpcInfo(request, response, jsonStr);///get common NPC info
				break;
	
			case 1004:
				result = armyInfoService.getArmy(request, response, jsonStr);///get Player's army info
				break;
				
			default:
				CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
				result.setTs( gcrv.getTs() );
				result.setCode(Message.MSG_CODE_EXCEPTION);
				result.setMsg(Message.MSG_EXCEPTION);
				jsonResp
				= JSON.toJSONString(result);
				ResponseParamUtil.writeJsonMessage(response,jsonResp);
				return;
			}
			
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
		}
		catch(Exception e){
			e.printStackTrace();

			CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
			result.setTs( gcrv.getTs() );
			result.setCode(Message.MSG_CODE_EXCEPTION);
			result.setMsg(Message.MSG_EXCEPTION);
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
		}
	
	}
}