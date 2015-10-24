package com.shinian.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.ServerListDao;
import com.shinian.util.MessageRespVo;
import com.shinian.util.ResponseParamUtil;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.ServerUrlReqVo;
import com.shinian.vo.ServerUrlRespVo;

@Service
public class ServerListService {
	@Autowired
	ServerListDao serverListDao;
	
	
	private static Logger log = Logger.getLogger(ServerListService.class);
	public void onGetServerUrl(HttpServletRequest request, HttpServletResponse response,String jsonStr) throws ParseException, UnsupportedEncodingException{
		
		CommonReqVo crv = JSON.parseObject(jsonStr, CommonReqVo.class);
		ServerUrlReqVo surv = JSON.parseObject(crv.getData().toString(), ServerUrlReqVo.class);
		
		String result = serverListDao.getServerUrl(surv.getChannelId(), surv.getServerNo());
		
		ServerUrlRespVo sv = new ServerUrlRespVo();
		if(result.length() > 0){
			sv.setResult(1);
			sv.setUrl(result);
		}
		else{
			sv.setResult(0);
			sv.setUrl("");
		}
		
		MessageRespVo mrv = new MessageRespVo();
		mrv.setTs(crv.getTs());
		mrv.setCode(200);
		mrv.setData(sv);
		
		String jsonResp = JSON.toJSONString(mrv);
		ResponseParamUtil.writeJsonMessage(response,jsonResp);
	}
	
}