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
import com.shinian.vo.ArmoryJinjieRedisVo;
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
	
	@Autowired
	PropInfoService propInfoService;

	//	Input: id(id of the game_armory_info)
	public MessageRespVo jinjie(HttpServletRequest request, HttpServletResponse response,String jsonStr)
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
		
		ArmoryJinjieRedisVo commJinjie = redisCacheUtil.getArmoryJinjieInfo(redisCacheUtil.getArmoryByComId(armory.getComId()).getStar(), redisCacheUtil.getArmoryByComId(armory.getComId()).getCategory(), armory.getPinjie()+1);
		if (null == commJinjie){
			result.setCode(Message.MSG_CODE_ARMORY_JINJIE_NOT_EXIST);
			result.setMsg(Message.MSG_ARMORY_JINJIE_NOT_EXIST);
			return result;
		}
		
		ArmoryJinjieRedisVo gameJinjie = getPropForArmoryJinjie(player);
		
		if (!meetJinjieCriteria(commJinjie, gameJinjie)){
			result.setCode(Message.MSG_CODE_ARMORY_NOT_MEET_CRITERIA);
			result.setMsg(Message.MSG_ARMORY_NOT_MEET_CRITERIA);
			return result;
		}
		
		consumePropForArmoryJinjie(player.getUid(), commJinjie);
		playerInfoService.consumeSilver(player, commJinjie.getSliver());
		
		updateJinjieArmoryVo(armory);
		armoryDao.updateArmory(armory);

		result.setData(armory);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	private void updateJinjieArmoryVo(ArmoryVo armory)
	{
		armory.setPinjie(armory.getPinjie()+1);
		ArmoryRedisVo arv = redisCacheUtil.getArmoryByComId(armory.getComId());
		
		if (armory.getPinjie() == Constant.ARMORY_JINJIE_MAX_GENERAL_PINJIE+1){
			armory.setHujia(arv.getHujiaGaoji());
			armory.setPojia(arv.getPojiaGaoji());
			armory.setFachuan(arv.getFachuanGaoji());
			armory.setFakang(arv.getFakangGaoji());
			armory.setBaoji	(arv.getBaojiGaoji());
			armory.setRenxing(arv.getRenxingGaoji());
			armory.setMingzhong(arv.getMingzhongGaoji());
			armory.setShanbi(arv.getShanbiGaoji());
			armory.setXixue(arv.getXixueGaoji());
			armory.setFantan(arv.getFantanGaoji());
			armory.setJiyun(arv.getJiyunGaoji());
			armory.setKangyun(arv.getKangyunGaoji());
			armory.setGedang(arv.getGedangGaoji());
			armory.setGedangPoss(arv.getGedangPossGaoji());
			armory.setReduce(arv.getReduceGaoji());
		}
		
		int attack = armory.getAttack() + armory.getLevel()*arv.getAttackStepJinglian();
		int health = armory.getHealth() + armory.getLevel()*arv.getHealthStepJinglian();
		
		if (armory.getPinjie() > Constant.ARMORY_JINJIE_MAX_GENERAL_PINJIE){
			attack += arv.getAddAttackGaoji();
			health += arv.getAddHealthGaoji();
		}
		else{
			attack += arv.getAddAttack();
			health += arv.getAddHealth();
		}

		armory.setAttack(attack);
		armory.setHealth(health);
	}
	
	private void consumePropForArmoryJinjie(String uid, ArmoryJinjieRedisVo commJinjie)
	{
		propInfoService.consumePropertyOfPlayer(uid, Constant.ARMORY_JINJIE_PROP_JINHUASTONE, commJinjie.getJinHuaStone());
		propInfoService.consumePropertyOfPlayer(uid, Constant.ARMORY_JINJIE_PROP_AMBER, commJinjie.getAmber());
		propInfoService.consumePropertyOfPlayer(uid, Constant.ARMORY_JINJIE_PROP_XUANTIE, commJinjie.getXuantie());
		propInfoService.consumePropertyOfPlayer(uid, Constant.ARMORY_JINJIE_PROP_COPPER, commJinjie.getCopper());
		propInfoService.consumePropertyOfPlayer(uid, Constant.ARMORY_JINJIE_PROP_MADENG, commJinjie.getMadeng());
		propInfoService.consumePropertyOfPlayer(uid, Constant.ARMORY_JINJIE_PROP_PIGE, commJinjie.getPige());	
	}
	
	private boolean meetJinjieCriteria(ArmoryJinjieRedisVo commJinjie, ArmoryJinjieRedisVo gameJinjie)
	{
		return	(gameJinjie.getSliver()				> commJinjie.getSliver()			)
				&& (gameJinjie.getJinHuaStone()		> commJinjie.getJinHuaStone()		)
				&& (gameJinjie.getAmber()			> commJinjie.getAmber()				)
				&& (gameJinjie.getXuantie()			> commJinjie.getXuantie()			)
				&& (gameJinjie.getCopper()			> commJinjie.getCopper()			)
				&& (gameJinjie.getMadeng()			> commJinjie.getMadeng()			)
				&& (gameJinjie.getPige()			> commJinjie.getPige()				) ;
	}
	
	private ArmoryJinjieRedisVo getPropForArmoryJinjie(PlayerInfoVo player)
	{
		ArmoryJinjieRedisVo ajv = new ArmoryJinjieRedisVo();
		
		ajv.setSliver(player.getSilver());
		
		List<PropInfoVo> list = propInfoService.getPropListOfPlayer(player.getUid());
		for (PropInfoVo prop:list){
			switch (prop.getComId()){
			case Constant.ARMORY_JINJIE_PROP_JINHUASTONE:
				ajv.setJinHuaStone(prop.getAmount());
				break;
				
			case Constant.ARMORY_JINJIE_PROP_AMBER:
				ajv.setAmber(prop.getAmount());
				break;
				
			case Constant.ARMORY_JINJIE_PROP_XUANTIE:
				ajv.setXuantie(prop.getAmount());
				break;
				
			case Constant.ARMORY_JINJIE_PROP_COPPER:
				ajv.setCopper(prop.getAmount());
				break;
				
			case Constant.ARMORY_JINJIE_PROP_MADENG:
				ajv.setMadeng(prop.getAmount());
				break;
				
			case Constant.ARMORY_JINJIE_PROP_PIGE:
				ajv.setPige(prop.getAmount());
				break;
				
			default:
				break;
			}
		}
		
		return ajv;
	}
	
	
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