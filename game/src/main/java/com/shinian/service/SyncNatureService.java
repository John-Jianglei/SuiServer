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
import com.shinian.vo.NpcInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;



@Service
public class SyncNatureService {
		
	@Autowired
	PropInfoDao propInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcInfoService npcInfoService;
	
	
	public NpcInfoVo syncNpcById(int id)
	{
		NpcInfoVo npc = npcInfoService.getNpcInfoById(id);
		
		
		
		npcInfoService.updateNpcNatureById(npc);
		return npc;
	}
	
	
}