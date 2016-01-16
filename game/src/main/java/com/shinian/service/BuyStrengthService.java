package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.BuyStrengthDao;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Constant;
import com.shinian.util.DateUtil;
import com.shinian.util.Message;
import com.shinian.util.Util;
import com.shinian.vo.BuyStrengthLogVo;
import com.shinian.vo.BuyStrengthRedisVo;
import com.shinian.vo.BuyStrengthReturnVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.VipPrivilegeRedisVo;

@Service
public class BuyStrengthService {
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	@Autowired
	BuyStrengthDao buyStrengthDao;
	
	@Autowired
	PlayerInfoDao playerInfoDao;
	
	public MessageRespVo BuyStrength(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		BuyStrengthLogVo buyStrengthLogVo = JSON.parseObject(gcrv.getData().toString(),BuyStrengthLogVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == buyStrengthLogVo.getUid() ) {
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		
		//判断二者在竞技场的关系
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(buyStrengthLogVo.getUid());
		int vipLevel = piv.getVip_Level();
		VipPrivilegeRedisVo vprv = redisCacheUtil.getVipPrivilegeByVip(vipLevel);
		//体力已满不能购买
		if( piv.getCurrent_strength() >= vprv.getMaxStrength() ){
			result.setCode(Message.MSG_CODE_CANNOT_BUY_STRENGTH);
			result.setMsg(Message.MSG_CANNOT_BUY_STRENGTH);
			return result;	
		}
		//元宝不够不能购买
		if( piv.getGold()-buyStrengthLogVo.getGold() < 0 ){
			result.setCode(Message.MSG_CODE_NOT_ENOUGH_GOLD);
			result.setMsg(Message.MSG_NOT_ENOUGH_GOLD);
			return result;	
		}
		
		String date = DateUtil.getTodayStr();
		BuyStrengthLogVo bslv = buyStrengthDao.getBuyStrengthLog(buyStrengthLogVo.getUid(), date);
		if( bslv==null ){
			bslv = new BuyStrengthLogVo();
			bslv.setUid(buyStrengthLogVo.getUid());
			bslv.setDate(date);
			bslv.setCount(0);
			
			buyStrengthDao.insertBuyStrengthLogLog( bslv );
		}

		if( bslv.getCount() >= vprv.getBuyStrength() )
		{
			result.setCode(Message.MSG_CODE_BUY_STRENGTH_FULL);
			result.setMsg(Message.MSG_BUY_STRENGTH_FULL);
			return result;			
		}
		BuyStrengthRedisVo bsrv = redisCacheUtil.getBuyStrengthBySeq(bslv.getCount()+1);
		if( bsrv.getGold() != buyStrengthLogVo.getGold() ){
			result.setCode(Message.MSG_CODE_BUY_STRENGTH_ERROR);
			result.setMsg(Message.MSG_BUY_STRENGTH_ERROR);
			return result;	
		}		
		
		//实质阶段，体力加10，减元宝，记录日志
		piv.setCurrent_strength(piv.getCurrent_strength()+Constant.CON_BUY_ONE_STRENGTH);
		piv.setGold(piv.getGold()-buyStrengthLogVo.getGold());
		bslv.setCount(bslv.getCount()+1);		
		
		//写数据库
		playerInfoDao.updatePlayer(piv);
		buyStrengthDao.updateBuyStrengthLogLog(bslv.getCount(), buyStrengthLogVo.getUid(), date);
		
		BuyStrengthReturnVo bsrtnv = new BuyStrengthReturnVo();
		bsrtnv.setN(bslv.getCount());
		bsrtnv.setUid(buyStrengthLogVo.getUid());
		bsrtnv.setGold(piv.getGold());
		bsrtnv.setSh(piv.getCurrent_strength());
		
		result.setData(bsrtnv);
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}
