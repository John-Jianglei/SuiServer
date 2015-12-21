package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Message;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.vo.TestReqVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class TestService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	PropInfoDao propInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	public MessageRespVo getArmoryRedisVo(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		TestReqVo nrv = JSON.parseObject(gcrv.getData().toString(),TestReqVo.class);
		result.setTs(gcrv.getTs());
		
		ArmoryRedisVo piv = redisCacheUtil.getArmoryByComId(nrv.getId());
		if(piv == null){
			result.setCode(Message.MSG_CODE_TEST_NOT_EXIST);
			result.setMsg(Message.MSG_TEST_NOT_EXIST);
			return result;
		}
				
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}