//战役关卡
package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcJinjieDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.JinjieMaterialRedisVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcJinjieRespVo;
import com.shinian.vo.NpcJinjieVo;

@Service
public class StageService {

	@Autowired
	NpcJinjieDao npcJinjieDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	PropInfoService propInfoService;

	public MessageRespVo battle(HttpServletRequest request, HttpServletResponse response,String jsonStr){
		
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		NpcJinjieVo npcuv = JSON.parseObject(gcrv.getData().toString(),NpcJinjieVo.class);
		//返回给客户端的数据
		NpcJinjieRespVo npcJinjieRespv = new NpcJinjieRespVo();
		result.setTs(gcrv.getTs());
		
		if (0 == npcuv.getId() || "".equals(npcuv.getId()) 
				|| "".equals(npcuv.getComsumedid())
				|| null == npcuv.getUid()) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		result.setData(npcJinjieRespv);	
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}