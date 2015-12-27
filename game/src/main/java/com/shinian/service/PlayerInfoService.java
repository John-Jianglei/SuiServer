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
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PlayerInfoVo;


@Service
public class PlayerInfoService {
		
	@Autowired
	PlayerInfoDao playerInfoDao;

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
	
	public PlayerInfoVo getPlayerById(String uid)
	{
		return playerInfoDao.getPlayerInfoByUid(uid);
	}
	
}
