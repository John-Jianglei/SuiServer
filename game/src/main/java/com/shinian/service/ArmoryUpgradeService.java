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
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class ArmoryUpgradeService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	ArmoryDao armoryDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcInfoService npcInfoService;

	//	Input: id(id of the game_armory_info)
	public MessageRespVo levelup(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		ArmoryReqVo nrv = JSON.parseObject(gcrv.getData().toString(),ArmoryReqVo.class);
		result.setTs(gcrv.getTs());
		
		ArmoryVo armory = armoryDao.getArmoryById(nrv.getId());
		if (armory == null){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_EXIST);
			result.setMsg(Message.MSG_ARMORY_NOT_EXIST);
			return result;
		}
				
		PlayerInfoVo player = playerInfoService.getPlayerById(armory.getUid());
		if (player == null){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		if (player.getSilver() < getLevelupSilver(armory)){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_MEET_CRITERIA);
			result.setMsg(Message.MSG_ARMORY_NOT_MEET_CRITERIA);
			return result;
		}
		
		playerInfoService.consumeSilver(player, getLevelupSilver(armory));
		
		armory.setLevel(armory.getLevel() + 1);
		armory.setAttack(getNextLevelAttack(armory));
		armory.setHealth(getNextLevelHealth(armory));
		armoryDao.levelup(armory.getId(), armory.getLevel(), armory.getAttack(), armory.getHealth());

		result.setData(armory);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	private int getNextLevelAttack(ArmoryVo armory)
	{
		ArmoryRedisVo arv = redisCacheUtil.getArmoryByComId(armory.getComId());
		
		return armory.getAttack() + arv.getAttackStep() + armory.getPinjie()*arv.getAttackStepJinglian();
	}
	
	private int getNextLevelHealth(ArmoryVo armory)
	{
		ArmoryRedisVo arv = redisCacheUtil.getArmoryByComId(armory.getComId());
		
		return armory.getHealth() + arv.getHealthStep() + armory.getPinjie()*arv.getHealthStepJinglian();
	}
		
	private int getLevelupSilver(ArmoryVo armory)
	{
		ArmoryRedisVo arv = redisCacheUtil.getArmoryByComId(armory.getComId());
		return arv.getInitSliver() + arv.getSliverStep()*armory.getLevel();
	}
}