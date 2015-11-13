package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcAddReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class NpcAddService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	NpcInfoDao npcInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	
	public MessageRespVo addNpcToPlayer(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PropInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(),NpcAddReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (!playerInfoService.isUidExist(nrv.getUid())){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		if (!redisCacheUtil.isNpcComIdExist(nrv.getComId())){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}

		
		NpcInfoVo piv = npcAddDao.addNpcToPlayer(nrv.getUid(), nrv.getComId());		
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public MessageRespVo getPropListOfPlayer(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PropInfoReqVo rv = JSON.parseObject(gcrv.getData().toString(),PropInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (!playerInfoService.isUidExist(rv.getUid())){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		List<PropInfoVo> list = propInfoDao.getPropListOfPlayer(rv.getUid());		
		
		if(list == null || list.size() == 0){
			result.setCode(Message.MSG_CODE_PROP_NOT_EXIST);
			result.setMsg(Message.MSG_PROP_NOT_EXIST);
			return result;
		}
		
		result.setData(list);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public List<PropInfoVo> getPropListOfPlayer(String uid)
	{
		if (!playerInfoService.isUidExist(uid)){
			return null;
		}
		
		return propInfoDao.getPropListOfPlayer(uid);		
	}
	
}
