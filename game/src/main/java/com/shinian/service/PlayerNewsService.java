package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.ArmoryDao;
import com.shinian.dao.PlayerNewsDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Constant;
import com.shinian.util.DateUtil;
import com.shinian.util.Message;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.ArmoryReqVo;
import com.shinian.vo.ArmoryVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NewsReqVo;
import com.shinian.vo.NewsRespVo;
import com.shinian.vo.NewsVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PlayerNewsTimeVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class PlayerNewsService {
		
	@Autowired
	PlayerInfoService playerInfoService;

	@Autowired
	ArmoryDao armoryDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcInfoService npcInfoService;
	
	@Autowired
	PlayerNewsDao playerNewsDao;

	//	input: uid
	public MessageRespVo getPlayerNewsCount(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NewsReqVo nrv = JSON.parseObject(gcrv.getData().toString(), NewsReqVo.class);
		result.setTs(gcrv.getTs());
		
		PlayerInfoVo player = playerInfoService.getPlayerById(nrv.getUid());
		if (player == null){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		int pCount = 0;
		PlayerNewsTimeVo newsTime = playerNewsDao.getPlayerNewsTimeById( nrv.getUid() );
		if( null == newsTime ){
			pCount = playerNewsDao.getUserNewsCount( nrv.getUid() ); 
		}
		else{
			String uNewsTime = newsTime.getNewsTime();
			if( DateUtil.getDifferDays(uNewsTime, DateUtil.getCurrentTime()) > Constant.NEWS_MAX_RETENTION_DAY )
			{
				uNewsTime = DateUtil.getyesterday(-Constant.NEWS_MAX_RETENTION_DAY);
			}
			pCount = playerNewsDao.getUserNewsCount( nrv.getUid() , uNewsTime );
		}
		
		int gCount = getGlobalNewsCount(nrv.getUid());

		NewsRespVo resp = new NewsRespVo();
		resp.setCount( pCount+gCount );
		resp.setUid(nrv.getUid());
		
		result.setData(resp);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	private int getGlobalNewsCount(String uid)
	{
		int count = 0;
		
		return count;
	}
	
	public MessageRespVo getPlayerNewsList(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NewsReqVo nrv = JSON.parseObject(gcrv.getData().toString(), NewsReqVo.class);
		result.setTs(gcrv.getTs());
		
		PlayerInfoVo player = playerInfoService.getPlayerById(nrv.getUid());
		if (player == null){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		List<NewsVo> list;
		PlayerNewsTimeVo newsTime = playerNewsDao.getPlayerNewsTimeById( nrv.getUid() );
		if( null == newsTime ){
			list = playerNewsDao.getUserNews( nrv.getUid() ); 
		}
		else{
			String uNewsTime = newsTime.getNewsTime();
			if( DateUtil.getDifferDays(uNewsTime, DateUtil.getCurrentTime()) > Constant.NEWS_MAX_RETENTION_DAY )
			{
				uNewsTime = DateUtil.getyesterday(-Constant.NEWS_MAX_RETENTION_DAY);
			}
			list = playerNewsDao.getUserNews( nrv.getUid() , uNewsTime );
		}
		
		if( null != list && list.size() > 0 ){
			for (NewsVo news : list)
			{
				playerNewsDao.setPlayerNewsStatus( news.getId(), Constant.NEWS_STATUS_READ );
			}
			playerNewsDao.updatePlayerNewsTimeById( nrv.getUid() );
		}
			
		List<NewsVo> gList = getGlobalNews(nrv.getUid());
		if( null != gList && gList.size() > 0 ) list.addAll(gList);
					
		result.setData(list);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	private List<NewsVo> getGlobalNews(String uid)
	{
		return null;
	}
	
	public MessageRespVo getNewsAward(HttpServletRequest request, HttpServletResponse response,String jsonStr)
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
	
	public MessageRespVo getNews(HttpServletRequest request, HttpServletResponse response,String jsonStr)
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