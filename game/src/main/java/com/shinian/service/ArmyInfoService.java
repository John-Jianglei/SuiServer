package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.UserLoginLogDao;
import com.shinian.dao.ArmyInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Config;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.ArmyReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcInfoVo;


@Service
public class ArmyInfoService {
		
	@Autowired
	ArmyInfoDao armyInfoDao;
		
	public MessageRespVo getArmy(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		ArmyReqVo arv = JSON.parseObject(gcrv.getData().toString(),ArmyReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (null == arv.getUid() || "".equals(arv.getUid())) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
				
		List<NpcInfoVo> list = armyInfoDao.getArmyByUid(arv.getUid());
		
		if(list == null || list.size() == 0){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		result.setData(list);		
		result.setCode(Message.MSG_CODE_OK);
		return result;
	}


}
