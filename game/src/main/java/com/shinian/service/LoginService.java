package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.UserLoginLogDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.LoginReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.NpcInfoVo;

@Service
public class LoginService {
		
	@Autowired
	UserLoginLogDao userLoginLogDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	public MessageRespVo login(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		LoginReqVo lrv = JSON.parseObject(gcrv.getData().toString(),LoginReqVo.class);
		result.setTs(gcrv.getTs());
		
		String uid = lrv.getUid();
		PlayerInfoVo piv = userLoginLogDao.getPlayerInfoByUid(uid);
		if(piv == null){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
		}
		
		//测试redis数据
//		WujiangInfo wi = redisCacheUtil.getWujiangById(1000);
//		if(wi != null){
//			System.out.println(wi.getName() + "," + wi.getHp());
//		}
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}
