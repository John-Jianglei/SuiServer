package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;




@Service
public class PropInfoService {
		
	@Autowired
	RedisCacheUtil redisCacheUtil;

	public MessageRespVo getCommPropInfo(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PropInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(),PropInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		PropInfoRedisVo piv = redisCacheUtil.getPropInfoByComId(nrv.getComId());
		if(piv == null){
			result.setCode(Message.MSG_CODE_PROP_NOT_EXIST);
			result.setMsg(Message.MSG_PROP_NOT_EXIST);
			return result;
		}
				
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
}
