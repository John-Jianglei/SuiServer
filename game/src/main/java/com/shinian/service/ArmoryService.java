package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.ArmoryDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Constant;
import com.shinian.util.Message;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.ArmoryReqVo;
import com.shinian.vo.ArmoryVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcInfoVo;
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
	
	@Autowired
	NpcInfoService npcInfoService;

	//	Input: npcId, id(id of the game_armory_info)
	public MessageRespVo loadArmoryToNpc(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		ArmoryReqVo nrv = JSON.parseObject(gcrv.getData().toString(),ArmoryReqVo.class);
		result.setTs(gcrv.getTs());
		
		NpcInfoVo npc = npcInfoService.getNpcInfoById(nrv.getNpcId());
		if (npc == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}

		ArmoryVo armory = armoryDao.getArmoryById(nrv.getId());
		if (armory == null){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_EXIST);
			result.setMsg(Message.MSG_ARMORY_NOT_EXIST);
			return result;
		}
		
		if (npc.getUid() != armory.getUid()){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_MATCH_NPC);
			result.setMsg(Message.MSG_ARMORY_NOT_MATCH_NPC);
			return result;
		}
		
		int category = redisCacheUtil.getArmoryByComId(armory.getComId()).getCategory();
		List<ArmoryVo> armoryList = armoryDao.getLoadedArmorys(nrv.getNpcId());
		
		for (ArmoryVo a:armoryList){
			if (category == redisCacheUtil.getArmoryByComId(a.getComId()).getCategory()) {
				armoryDao.loadArmoryToNpc(nrv.getNpcId(), a.getId(), false);
			}
		}
		
		armoryDao.loadArmoryToNpc(nrv.getNpcId(), nrv.getId(), true);
		armory.setLoaded(1);
		armory.setNpcId(nrv.getNpcId());

		result.setData(armory);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
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
	
	public MessageRespVo getArmoryListByCategory(HttpServletRequest request, HttpServletResponse response,String jsonStr)
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
		
		List<ArmoryVo> armoryList = armoryDao.getArmoryList(nrv.getUid());
		List<ArmoryVo> retList = new ArrayList<ArmoryVo>();
		int category = nrv.getCategory();
		
		for (ArmoryVo armory:armoryList){
			if (redisCacheUtil.getArmoryByComId(armory.getComId()).getCategory() == category) retList.add(armory);
		}
		
		if(retList == null || retList.size() == 0){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_EXIST);
			result.setMsg(Message.MSG_ARMORY_NOT_EXIST);
			return result;
		}
		
		result.setData(retList);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}