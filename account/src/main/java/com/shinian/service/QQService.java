package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shinian.dao.AccSnsInfoDao;
import com.shinian.util.Message;
import com.shinian.util.MessageRespVo;
import com.shinian.util.ParamUtil;
import com.shinian.vo.AccSnsInfoVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.QQGetTokenReqVo;
import com.shinian.vo.QQGetTokenRespVo;



@Service
public class QQService {
	
	@Autowired
	AccSnsInfoDao accSnsInfoDao;
	@Autowired
	AccountService accountService;
	
	public MessageRespVo getQQToken(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) throws Exception{
		MessageRespVo  message = new MessageRespVo();
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		message.setTs( crv.getTs() );
		Object data = crv.getData();
		QQGetTokenReqVo qhvo = JSONObject.parseObject(data.toString(), QQGetTokenReqVo.class);
		
		AccSnsInfoVo accSnsInfo = accSnsInfoDao.isAccSnsInfoExist(qhvo.getOpenId(), qhvo.getOpenId(), ParamUtil.channelIdqq);
		String token = accountService.genToken(  accSnsInfo.getUid() ,ParamUtil.channelIdqq);
		
		QQGetTokenRespVo resp = new QQGetTokenRespVo();
		resp.setToken( token );
		resp.setUid( accSnsInfo.getUid() );
		message.setCode( Message.msgCode_correct);
		message.setData(resp);
		return message;
	}
	
}