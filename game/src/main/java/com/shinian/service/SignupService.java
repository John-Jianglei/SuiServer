package com.shinian.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcAddDao;
import com.shinian.dao.PassDao;
import com.shinian.dao.SignupDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Config;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.SignupReqVo;


@Service
public class SignupService {
		
	@Autowired
	SignupDao signupDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcAddDao npcAddDao;
	
	@Autowired
	PassDao	passDao;
	
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

		if (null == srv.getUid() || "".equals(srv.getUid()) ) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		if ( null == srv.getUid() || 0 == srv.getCm()) {
			result.setCode(Message.MSG_CODE_NO_CAMP);
			result.setMsg(Message.MSG_NO_CAMP);
			return result;
		}		
		
		String g_player_uid = StringUtils.join(String.valueOf(Config.SERVERID), "-", srv.getUid());
		
		if(!signupDao.isUidNameUnique(g_player_uid, srv.getName())){
			result.setCode(Message.MSG_CODE_PLAYER_IDNAME_DUPLICATIVE);
			result.setMsg(Message.MSG_PLAYER_IDNAME_DUPLICATIVE);
			return result;
		}
		PlayerInfoVo piv = new PlayerInfoVo();
		piv.setUid(g_player_uid);
		piv.setName(srv.getName());
		piv.setGender(srv.getGender());
		piv.setCamp(srv.getCm());
		
		//给改用户分配初始银两和声望，初始银两100万，声望2000，黄金300
		piv.setSilver(1000000);
		piv.setFame(2000);
		piv.setGold(300);
		
//		int row = signupDao.insertPlayer(g_player_uid, srv.getName(), srv.getGender());
//		if (row == 0){
//			result.setCode(Message.MSG_CODE_CREATEPLAYER_FAIL);
//			result.setMsg(Message.MSG_CREATEPLAYER_FAIL);
//			return result;
//		}
//
//		PlayerInfoVo piv = signupDao.getPlayerInfoByUid(g_player_uid);
//		
//		if(piv == null){
//			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
//			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
//			return result;
//		}
		
		//初始化一些信息
		//1、给该用户添加初始武将
		NpcInfoVo niv = new NpcInfoVo();
		int[] comId = new int[3];
		NpcInfoRedisVo[] nirv = new NpcInfoRedisVo[3];
		switch( srv.getCm() ){
		case 1:
			comId[0] = 102;
			comId[1] = 122;
			comId[2] = 129;
			break;
		case 2:
			comId[0] = 202;
			comId[1] = 226;
			comId[2] = 228;
			break;
		case 3:
			comId[0] = 302;
			comId[1] = 326;
			comId[2] = 331;
			break;
		}
		
		for( int i=0; i<3; i++ ){
			nirv[i] = redisCacheUtil.getNpcInfoByComId(comId[i]);
			if (nirv[i] == null){
				result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
				result.setMsg(Message.MSG_NPC_NOT_EXIST);
				return result;
			}
			niv = npcAddDao.addNpcToPlayer(piv.getUid(), nirv[i]);
//			if (piv.getPosition() < Constant.CON_ARMY_SIZE){
//				syncNatureService.refreshArmy(piv.getUid());
//			}
		}
		
		if( signupDao.insertPlayer(piv) != 1 ){
			result.setCode(Message.MSG_CODE_CREATEPLAYER_FAIL);
			result.setMsg(Message.MSG_CREATEPLAYER_FAIL);
			return result;
		}
		
		//2、给该用户生成关卡信息
		int count = passDao.getPassCount();
		for( int i=1; i<=15; i++ ){
			if( 0== passDao.insertPass(count+1, 1, piv.getUid()) ){				
				result.setCode(Message.MSG_CODE_ADD_PASS_FAIL);
				result.setMsg(Message.MSG_ADD_PASS_FAIL);
				return result;
			}
		}
		
		//result.setData(piv);		
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
