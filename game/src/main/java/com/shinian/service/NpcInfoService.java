package com.shinian.service;

import java.util.List;

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
import com.shinian.vo.NpcInfoReqVo;
import com.shinian.vo.PositionReqVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;




@Service
public class NpcInfoService {
		
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcInfoDao npcInfoDao;
	
	@Autowired
	ArmyInfoService armyInfoService;
	
	@Autowired
	SyncNatureService syncNatureService;
	
	public MessageRespVo getCommNpcInfo(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(),NpcInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		NpcInfoRedisVo piv = redisCacheUtil.getNpcInfoByComId(nrv.getId());
		if(piv == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
				
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public MessageRespVo getNpcInfo(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(),NpcInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		NpcInfoVo piv = npcInfoDao.getNpcInfoById(nrv.getId());
		if(piv == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
				
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public NpcInfoVo getNpcInfoById(int id)
	{
		return npcInfoDao.getNpcInfoById(id);
	}
	
	public MessageRespVo setNpcPosition(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PositionReqVo nrv = JSON.parseObject(gcrv.getData().toString(),PositionReqVo.class);
		result.setTs(gcrv.getTs());

		NpcInfoVo reqNpc= npcInfoDao.getNpcInfoById(nrv.getId());
		if(reqNpc == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		int currentPosition = reqNpc.getPosition();
		
		NpcInfoVo tpNpc = getNpcByPosition(nrv.getUid(), nrv.getTargetPosition()); 		//Npc on target position
		if (null == tpNpc){
			npcInfoDao.setNpcPosition(nrv.getId(), nrv.getTargetPosition());
		}
		else if(tpNpc.getId() != nrv.getId()){
			npcInfoDao.setNpcPosition(nrv.getId(), tpNpc.getPosition());
			npcInfoDao.setNpcPosition(tpNpc.getId(), currentPosition);
		}
		
		List<NpcInfoVo> list = armyInfoService.getArmy(nrv.getUid());
		
		if(list == null || list.size() == 0){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		result.setData(list);		
		result.setCode(Message.MSG_CODE_OK);
		return result;
	}
	
	public NpcInfoVo getNpcByPosition(String uid, int position)
	{
		return npcInfoDao.getNpcByPosition(uid, position);
	}
}
