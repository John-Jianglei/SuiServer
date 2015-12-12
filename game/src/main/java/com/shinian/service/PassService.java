package com.shinian.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.PassDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.BattleReturnVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcBattleVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PassVo;
import com.shinian.vo.PassZhanyiVo;

@Service
public class PassService {

	@Autowired
	PassDao passDao;
	
	@Autowired	
	ArmyInfoService armyInfoService;
	
	@Autowired	
	BattleService battleService;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	public MessageRespVo pass(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		PassVo passVo = JSON.parseObject(gcrv.getData().toString(),PassVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == passVo.getUid() || "".equals(passVo.getBattle()) 
				|| "".equals(passVo.getA())) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		//扫荡
		if(passVo.getA()==0){
			
		}
		//战斗
		else{
			//调用战斗函数
			BattleReturnVo btlRtnv =  battleService.pve( passVo.getUid(), passVo.getBattle() );				
			result.setData(btlRtnv);
		}
		
		result.setCode(Message.MSG_CODE_OK);
	
		return result;
	}
	
}
