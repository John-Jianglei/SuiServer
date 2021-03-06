package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcAddDao;
import com.shinian.util.Constant;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcAddReqVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class NpcAddService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	NpcAddDao npcAddDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	@Autowired
	SyncNatureService syncNatureService;
	
	public MessageRespVo addNpcToPlayer(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcAddReqVo nrv = JSON.parseObject(gcrv.getData().toString(),NpcAddReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (!playerInfoService.isUidExist(nrv.getUid())){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		NpcInfoRedisVo npc = redisCacheUtil.getNpcInfoByComId(nrv.getComId());
		if (npc == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}

		NpcInfoVo piv = npcAddDao.addNpcToPlayer(nrv.getUid(), npc);
		if (piv.getPosition() < Constant.CON_ARMY_SIZE){
			syncNatureService.refreshArmy(piv.getUid());
		}
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public int addNpcToPlayer(String uid, int comId, int amount)
	{
		if (!playerInfoService.isUidExist(uid)) return 0;
		
		NpcInfoRedisVo npc = redisCacheUtil.getNpcInfoByComId(comId);
		if (npc == null) return 0;

		for (int i = 0; i < amount; i++){
			npcAddDao.addNpcToPlayer(uid, npc);
		}
		
		return 1;
	}
	
}
