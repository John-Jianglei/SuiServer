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
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcJinjieVo;

@Service
public class NpcJinjieService {
	
	@Autowired
	NpcJinjieDao npcJinjieDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	PropInfoService propInfoService;

	public MessageRespVo npcJinjie(HttpServletRequest request, HttpServletResponse response,String jsonStr){
		
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		NpcJinjieVo npcuv = JSON.parseObject(gcrv.getData().toString(),NpcJinjieVo.class);
		result.setTs(gcrv.getTs());
		
		if (0 == npcuv.getId() || "".equals(npcuv.getId()) 
				|| 0 == npcuv.getComsumedid() || "".equals(npcuv.getComsumedid())
				|| null == npcuv.getUid()) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		//parse list and get totalExp
		int npcId = npcuv.getId();
		int comsumedid = npcuv.getComsumedid();
		String Uid = npcuv.getUid();

		NpcJinjieVo npc1 = npcJinjieDao.getNpcComIdByid(Uid, npcId);
		NpcJinjieVo npc2 = npcJinjieDao.getNpcComIdByid(Uid, comsumedid);
		
		//判断进阶武将和升级武将是否相同武将
		if(npc1.getComId() != npc2.getComId()){
			result.setCode(Message.MSG_CODE_SEL_NPC_ERROR);
			result.setMsg(Message.MSG_SEL_NPC_ERROR);
			return result;
		}
		
		//获取升级所需材料，根据pinjie
		NpcJinjieVo jinjieMaterial = npcJinjieDao.getJinjieNeedMByPinjie(npc1.getPinjie()+1);
		
		//获取玩家已有材料
		
		//比较玩家是否有足够材料
		
		//减除玩家材料，写数据库
		
		//增加武将品阶，重新计算武将战力，写数据库，返回相应结果给客户端
		
		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}

}
