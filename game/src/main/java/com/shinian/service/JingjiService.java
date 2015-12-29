package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.JingjiDao;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.BattleReturnVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.JingjiPosVo;
import com.shinian.vo.JingjiRedisVo;
import com.shinian.vo.JingjiReturnVo;
import com.shinian.vo.JingjiVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PlayerInfoVo;

@Service
public class JingjiService {

	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	JingjiDao	jingjiDao;
	
	@Autowired
	PlayerInfoDao	playerInfoDao;
	
	@Autowired	
	BattleService battleService;
	
	//竞技场战斗
	public MessageRespVo JingjiBatttle(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		JingjiVo jingjiVo = JSON.parseObject(gcrv.getData().toString(),JingjiVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == jingjiVo.getUid() || null == jingjiVo.getDuid() ) {
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		
		//判断二者在竞技场的关系
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(jingjiVo.getUid());
		PlayerInfoVo defPiv = playerInfoDao.getPlayerInfoByUid(jingjiVo.getDuid());
		if( piv==null || defPiv==null ){
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		if( piv.getJingjiPos()==0 ){
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}

		int[] defPos = new int[5];
		defPos[0] = piv.getAbovePos1();
		defPos[1] = piv.getAbovePos2();
		defPos[2] = piv.getAbovePos3();
		defPos[3] = piv.getAbovePos4();
		defPos[4] = piv.getAbovePos5();
		int i = 0;
		for( ; i<5; i++ ){
			if( defPiv.getJingjiPos() == defPos[i] ){
				break;
			}
		}
		//防御者在攻击者的范围之外
		if( i==5 ){
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}		
		
		//战斗
		BattleReturnVo btlRtnv =  battleService.pvp( jingjiVo.getUid(), jingjiVo.getDuid() );
		JingjiReturnVo jjRtnv = new JingjiReturnVo();
		jjRtnv.setBrv(btlRtnv);		
		
		//判断胜负，或交换名次
		int fame = 20;		//打不赢声望加20	
		if( btlRtnv.getStar()>0 ){
			//打赢声望加30
			fame = 30;
			if( defPiv.getJingjiPos() < piv.getJingjiPos() ){
				int temp = piv.getJingjiPos();
				piv.setJingjiPos(defPiv.getJingjiPos());
				defPiv.setJingjiPos(temp);
				int[] abovePos = getAbovePos(piv.getJingjiPos());
				piv.setAbovePos1(abovePos[0]);
				piv.setAbovePos2(abovePos[1]);
				piv.setAbovePos3(abovePos[2]);
				piv.setAbovePos4(abovePos[3]);
				piv.setAbovePos5(abovePos[4]);
				playerInfoDao.updatePlayer(defPiv);
			}
		}
		
		jjRtnv.setFame(fame);
		JingjiPosVo jpv = new JingjiPosVo();
		jjRtnv.setJpv(jpv);
		jjRtnv.getJpv().setUid(jingjiVo.getUid());
		jjRtnv.getJpv().setP(piv.getJingjiPos());
		jjRtnv.getJpv().setP1(piv.getAbovePos1());
		jjRtnv.getJpv().setP2(piv.getAbovePos2());
		jjRtnv.getJpv().setP3(piv.getAbovePos3());
		jjRtnv.getJpv().setP4(piv.getAbovePos4());
		jjRtnv.getJpv().setP5(piv.getAbovePos5());	
		
		//写数据库
		piv.setJingjiTitle(redisCacheUtil.getJingjiInfoByPos(piv.getJingjiPos()).getTitle());
		playerInfoDao.updatePlayer(piv);
		
		result.setData(jjRtnv);
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	//根据武将排名得到最近5名武将的排名
	private int[] getAbovePos(int pos){
		
		int[] abovePos = {0,0,0,0,0};
		JingjiRedisVo jjrv = redisCacheUtil.getJingjiInfoByPos(pos);
		//如果排名为1至5，比如第一则abovePos全为0
		if( pos<=5 ){ 
			abovePos[0] = pos-1;
			for( int i=1; i<5; i++ ){
				if( abovePos[i-1] ==0 ){
					break;
				}				
				abovePos[i] = abovePos[i-1]-1;
			}
			return abovePos;
		}
		
		abovePos[0] = pos;
		JingjiRedisVo jjrv2 = redisCacheUtil.getJingjiInfoById(jjrv.getId()-1);
		for( int i=0; i<5; i++ ){			
			if( i>0 ){
				abovePos[i] = abovePos[i-1];
			}
			if( abovePos[i]-jjrv.getInterval() < jjrv.getPosBot() ){
				if( abovePos[i] > jjrv2.getPosTop() ){
					abovePos[i] = jjrv2.getPosTop();
				}
				else{
					abovePos[i] = abovePos[i] - jjrv2.getInterval();
				}
			}
			else{
				abovePos[i] = abovePos[i] - jjrv.getInterval();
			}			
		}		
		
		return abovePos;
	}
	
	//进入竞技场排名
	public MessageRespVo EnterJingji(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		JingjiVo jingjiVo = JSON.parseObject(gcrv.getData().toString(),JingjiVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == jingjiVo.getUid() ) {
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(jingjiVo.getUid());
		JingjiPosVo jjpv = new JingjiPosVo();
		if( 0!= piv.getJingjiPos() ){
			result.setCode(Message.MSG_CODE_JINGJI_ERROR);
			result.setMsg(Message.MSG_JINGJI_ERROR);
			return result;
		}
		
		jjpv.setP(jingjiDao.getAmount()+1);
		piv.setJingjiPos(jjpv.getP());
		int[] abovePos = getAbovePos(piv.getJingjiPos());		
		piv.setAbovePos1(abovePos[0]);
		piv.setAbovePos2(abovePos[1]);
		piv.setAbovePos3(abovePos[2]);
		piv.setAbovePos4(abovePos[3]);
		piv.setAbovePos5(abovePos[4]);		
		piv.setJingjiTitle(redisCacheUtil.getJingjiInfoByPos(jjpv.getP()).getTitle());
		//写数据库
		jingjiDao.setAmount(jjpv.getP());		
		playerInfoDao.updatePlayer(piv);
		
		jjpv.setP1(abovePos[0]);
		jjpv.setP2(abovePos[1]);
		jjpv.setP3(abovePos[2]);
		jjpv.setP4(abovePos[3]);
		jjpv.setP5(abovePos[4]);
				
		result.setData(jjpv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}	
}
