package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.JingjiDao;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.JingjiVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PlayerInfoVo;

@Service
public class JingjiService {

	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	JingjiDao	jingjiDao;
	
	@Autowired
	PlayerInfoDao	playerInfoDao;
	
	//竞技场战斗
	public MessageRespVo JingjiBatttle(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		JingjiVo jingjiVo = JSON.parseObject(gcrv.getData().toString(),JingjiVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == jingjiVo.getUid() ) {
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		
		//判断二者在竞技场的关系
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(jingjiVo.getUid());
		
		
		
		//战斗
		
		
		//判断胜负，或交换名次
		
		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	//进入竞技场排名
	public MessageRespVo EnterJingji(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		JingjiVo jingjiVo = JSON.parseObject(gcrv.getData().toString(),JingjiVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == jingjiVo.getUid() ) {
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		
		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}
