package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.ArmoryDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Message;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.ArmoryReqVo;
import com.shinian.vo.ArmoryVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class ArmoryService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	ArmoryDao armoryDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	public MessageRespVo addArmoryToPlayer(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		ArmoryReqVo nrv = JSON.parseObject(gcrv.getData().toString(),ArmoryReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (!playerInfoService.isUidExist(nrv.getUid())){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		ArmoryRedisVo arvo = redisCacheUtil.getArmoryByComId(nrv.getComId()); 
		if (arvo == null){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_EXIST);
			result.setMsg(Message.MSG_ARMORY_NOT_EXIST);
			return result;
		}
		
		ArmoryVo armory = arvo.initGameArmory();
				
		ArmoryVo av = armoryDao.addArmoryToPlayer(nrv.getUid(), armory);		
		result.setData(av);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}