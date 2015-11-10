package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
