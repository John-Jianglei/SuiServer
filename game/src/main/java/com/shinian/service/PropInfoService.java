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
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class PropInfoService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	PropInfoDao propInfoDao;
	
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
	
	public MessageRespVo addPropertyToPlayer(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PropInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(),PropInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (!playerInfoService.isUidExist(nrv.getUid())){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		if (!redisCacheUtil.isPropComIdExist(nrv.getComId())){
			result.setCode(Message.MSG_CODE_PROP_NOT_EXIST);
			result.setMsg(Message.MSG_PROP_NOT_EXIST);
			return result;
		}

		
		PropInfoVo piv = propInfoDao.addPropertyToPlayer(nrv.getUid(), nrv.getComId(), nrv.getAmount());		
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public int addPropertyToPlayer(String uid, int comId, int amount)
	{
		if (!playerInfoService.isUidExist(uid)) return 0;

		if (!redisCacheUtil.isPropComIdExist(comId)) return 0;
		
		propInfoDao.addPropertyToPlayer(uid, comId, amount);		

		return 1;
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
	
	public List<PropInfoVo> getPropListOfNpc(int npcId)
	{
		return propInfoDao.getPropListOfNpc(npcId);		
	}
	
	
	public PropInfoVo getPropOfPlayerByComId(String uid, int comId)
	{
		if (!playerInfoService.isUidExist(uid)){
			return null;
		}
		
		if (!redisCacheUtil.isPropComIdExist(comId)){
			return null;
		}

		return propInfoDao.getPropOfPlayerByComId(uid, comId);
	}
	
	public MessageRespVo consumePropertyOfPlayer(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PropInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(),PropInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (!playerInfoService.isUidExist(nrv.getUid())){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		PropInfoVo pv = getPropOfPlayerByComId(nrv.getUid(), nrv.getComId());
		
		if (null == pv){
			result.setCode(Message.MSG_CODE_PROP_NOT_EXIST);
			result.setMsg(Message.MSG_PROP_NOT_EXIST);
			return result;
		}

		if (nrv.getAmount() > pv.getAmount()){
			result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
			result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
			return result;
		}
		
		if(nrv.getAmount() == pv.getAmount()){
			propInfoDao.delPropertyOfPlayer(pv);
			result.setCode(Message.MSG_CODE_PROP_EMPTY);
			result.setMsg(Message.MSG_PROP_EMPTY);
			return result;
		}

		pv.setAmount(pv.getAmount() - nrv.getAmount());
		propInfoDao.updatePropertyOfPlayer(pv);
		result.setData(pv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	public PropInfoVo consumePropertyOfPlayer(String uid, int comId, int amount)
	{
		PropInfoVo pv = getPropOfPlayerByComId(uid, comId);
		
		if ((null == pv) || (amount > pv.getAmount())){
			return null;
		}
		
		if(amount == pv.getAmount()){
			propInfoDao.delPropertyOfPlayer(pv);
			return null;
		}

		pv.setAmount(pv.getAmount() - amount);
		propInfoDao.updatePropertyOfPlayer(pv);
		
		return pv;
	}
}
