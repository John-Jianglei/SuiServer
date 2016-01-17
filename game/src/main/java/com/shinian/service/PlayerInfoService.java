package com.shinian.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Config;
import com.shinian.util.Constant;
import com.shinian.util.DateUtil;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NewsReqVo;
import com.shinian.vo.NewsRespVo;
import com.shinian.vo.PlayerInfoReqVo;
import com.shinian.vo.PlayerInfoRespVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PlayerXTimeVo;
import com.shinian.vo.VipPrivilegeRedisVo;


@Service
public class PlayerInfoService {
		
	@Autowired
	PlayerInfoDao playerInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

//	private static String regExUid = "^[0-9]+-[1-9]+$"; 
	private static String regExUid = "^\\d+-\\d+$";

	public boolean isUidExist(String uid){
		Pattern p1 = Pattern.compile(regExUid); 
		Matcher m1 = p1.matcher(uid); 
		 
		if (!m1.matches()) return false;
		
		return playerInfoDao.isUidExist(uid);

	}
	
	public boolean consumeSilver(PlayerInfoVo player, int silver)
	{
		if (player.getSilver() < silver) return false;
		
		silver = player.getSilver() - silver;
		return playerInfoDao.updateSilver(player.getUid(), silver) > 0;
	}
	
	public int updateSilver(String uid, int silver)
	{
		return playerInfoDao.updateSilver(uid, silver);
	}
	
	public PlayerInfoVo getPlayerById(String uid)
	{
		return playerInfoDao.getPlayerInfoByUid(uid);
	}
	
	//	input: uid
	public MessageRespVo getPlayerStrength(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		PlayerInfoReqVo nrv = JSON.parseObject(gcrv.getData().toString(), PlayerInfoReqVo.class);
		result.setTs(gcrv.getTs());
		
		PlayerInfoVo player = getPlayerById(nrv.getUid());
		if (player == null){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		int strength = player.getCurrent_strength() ;

		VipPrivilegeRedisVo vprv = redisCacheUtil.getVipPrivilegeByVip( player.getVip_Level() );
		if (null == vprv){
			result.setCode(Message.MSG_CODE_VIPLEVEL_NOT_EXIST);
			result.setMsg(Message.MSG_VIPLEVEL_NOT_EXIST);
			return result;
		}
		
		if (strength < vprv.getMaxStrength()){
			PlayerXTimeVo lastTime = playerInfoDao.getStrengthTime( nrv.getUid() );
			
			if( null == lastTime ){
				strength = vprv.getMaxStrength();
			}
			else{
				int diffTimes = DateUtil.getDifferTimes( lastTime.getLastTime(), DateUtil.getCurrentTime() );
				strength += diffTimes / Constant.NEWS_MINUTE_PER_STRENGTH;
				strength = (strength > vprv.getMaxStrength()) ? vprv.getMaxStrength() : strength;	
			}
			
			player.setCurrent_strength(strength);
			playerInfoDao.updatePlayer(player);
		}
		
		playerInfoDao.updateStrengthTime( nrv.getUid() );
		
		PlayerInfoRespVo resp = new PlayerInfoRespVo();
		resp.setUid(nrv.getUid());
		resp.setStrength( strength );
		
		result.setData(resp);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	
}
