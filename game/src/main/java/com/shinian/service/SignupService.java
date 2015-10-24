package com.shinian.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.SignupDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Config;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.SignupReqVo;


@Service
public class SignupService {
		
	@Autowired
	SignupDao signupDao;
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	public MessageRespVo signup(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		SignupReqVo srv = JSON.parseObject(gcrv.getData().toString(),SignupReqVo.class);
		result.setTs(gcrv.getTs());
		
		if (null == srv.getName() || "".equals(srv.getName()) || !checkName(srv.getName())) {
			result.setCode(Message.MSG_CODE_PLAYERNAME_INAPPROPRIATE);
			result.setMsg(Message.MSG_PLAYERNAME_INAPPROPRIATE);
			return result;
		}

		if (null == srv.getUid() || "".equals(srv.getUid())) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		String g_player_uid = StringUtils.join(String.valueOf(Config.SERVERID), "-", srv.getUid());
		
		if(!signupDao.isUidNameUnique(g_player_uid, srv.getName())){
			result.setCode(Message.MSG_CODE_PLAYER_IDNAME_DUPLICATIVE);
			result.setMsg(Message.MSG_PLAYER_IDNAME_DUPLICATIVE);
			return result;
		}
		
		int row = signupDao.insertPlayer(g_player_uid, srv.getName(), srv.getGender());
		if (row == 0){
			result.setCode(Message.MSG_CODE_CREATEPLAYER_FAIL);
			result.setMsg(Message.MSG_CREATEPLAYER_FAIL);
			return result;
		}

		PlayerInfoVo piv = signupDao.getPlayerInfoByUid(g_player_uid);
		
		if(piv == null){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		return result;
	}
	
	private boolean checkName(String name){	// return true if name is appropriate
		String filter = redisCacheUtil.getSensitiveCharacter();
		Matcher m=Pattern.compile( filter ).matcher( name );
		if( m.find() ) {
			return false;
		}
		 
		return true;
	}
}
