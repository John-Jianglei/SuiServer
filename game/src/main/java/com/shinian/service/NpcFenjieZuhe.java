//武将分解与组合
package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcInfoDao;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.util.RandomUtil;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.CommonVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcAddReqVo;
import com.shinian.vo.NpcFenjieVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PlayerInfoVo;

@Service
public class NpcFenjieZuhe {
	
	@Autowired
	NpcInfoDao npcInfoDao;
	
	@Autowired
	PlayerInfoDao playerInfoDao;
	
	@Autowired
	PropInfoDao propInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	public MessageRespVo npcFenjie(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcFenjieVo npcFenjieVo = JSON.parseObject(gcrv.getData().toString(),NpcFenjieVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == npcFenjieVo.getNpcList() || null == npcFenjieVo.getUid()) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(npcFenjieVo.getUid());
		NpcFenjieVo nfrv = new NpcFenjieVo();
		nfrv.init();
		
		for( CommonVo commonVo : npcFenjieVo.getNpcList()){
			NpcInfoVo npcInfoVo = npcInfoDao.getNpcInfoById(commonVo.getId());
			if( npcInfoVo ==null ){
				result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
				result.setMsg(Message.MSG_NPC_NOT_EXIST);
				return result;
			}
			//不是此玩家的武将
			if( !npcFenjieVo.getUid().equals(npcInfoVo.getUid())){
				result.setCode(Message.MSG_CODE_SEL_NPC_ERROR);
				result.setMsg(Message.MSG_SEL_NPC_ERROR);
				return result;
			}
		
			NpcInfoRedisVo nirv = redisCacheUtil.getNpcInfoByComId(npcInfoVo.getComId());
			int[][] num;
		
			switch( nirv.getStar() ){
			case 3:
				//三星将魂
				num = new int[3][2];
				num[0][0] = 3;
				num[1][0] = 4;
				num[2][0] = 5;
				num[0][1] = 1;
				num[1][1] = 1;
				num[2][1] = 1;
				nfrv.setH3(getComRand(num) + nfrv.getH3());
				//虎符
				num = new int[3][2];
				num[0][0] = 0;
				num[1][0] = 1;
				num[2][0] = 2;
				num[0][1] = 3;
				num[1][1] = 5;
				num[2][1] = 2;
				nfrv.setT(getComRand(num) + nfrv.getT());
				
				piv.setSilver(piv.getSilver()+500);
				npcInfoDao.deleteNpcById(commonVo.getId());
				break;
			case 4:
				//四星将魂
				num = new int[7][2];
				num[0][0] = 4;
				num[1][0] = 5;
				num[2][0] = 6;
				num[3][0] = 7;
				num[4][0] = 8;
				num[5][0] = 9;
				num[6][0] = 10;
				num[0][1] = 1;
				num[1][1] = 1;
				num[2][1] = 1;
				num[3][1] = 1;
				num[4][1] = 1;
				num[5][1] = 1;
				num[6][1] = 1;
				nfrv.setH4(getComRand(num) + nfrv.getH4());
				//地煞令
				num = new int[3][2];
				num[0][0] = 0;
				num[1][0] = 1;
				num[0][1] = 1;
				num[1][1] = 2;
				nfrv.setE(getComRand(num) + nfrv.getE());
				//进阶丹
				num = new int[2][2];
				num[0][0] = 0;
				num[1][0] = 1;
				num[0][1] = 1;
				num[1][1] = 1;
				nfrv.setJ(getComRand(num) + nfrv.getJ());
				
				piv.setSilver(piv.getSilver()+5000);
				npcInfoDao.deleteNpcById(commonVo.getId());
				break;
			case 5:
				//五星将魂
				num = new int[7][2];
				num[0][0] = 2;
				num[1][0] = 3;
				num[2][0] = 4;
				num[3][0] = 5;
				num[4][0] = 6;
				num[5][0] = 7;
				num[6][0] = 8;
				num[0][1] = 1;
				num[1][1] = 1;
				num[2][1] = 1;
				num[3][1] = 1;
				num[4][1] = 1;
				num[5][1] = 1;
				num[6][1] = 1;
				nfrv.setH5(getComRand(num) + nfrv.getH5());
				//天罡令
				num = new int[2][2];
				num[0][0] = 0;
				num[1][0] = 1;
				num[0][1] = 1;
				num[1][1] = 1;
				nfrv.setP(getComRand(num) + nfrv.getP());
				//进阶丹
				num = new int[5][2];
				num[0][0] = 0;
				num[1][0] = 1;
				num[2][0] = 2;
				num[3][0] = 3;
				num[4][0] = 4;
				num[0][1] = 4;
				num[1][1] = 6;
				num[2][1] = 4;
				num[3][1] = 1;
				num[4][1] = 1;
				nfrv.setJ(getComRand(num) + nfrv.getJ());
				
				piv.setSilver(piv.getSilver()+50000);
				npcInfoDao.deleteNpcById(commonVo.getId());
				break;
			default:
				result.setCode(Message.MSG_CODE_NPC_FENJIE_EXCEPTION);
				result.setMsg(Message.MSG_NPC_FENJIE_EXCEPTION);
				return result;
			}	
		}
		
		nfrv.setNpcList(npcFenjieVo.getNpcList());
		
		//写数据库
		playerInfoDao.updatePlayer(piv);		//增加钱

		//写武将将魂数据库
		
		//写玩家物品数据库
		

		result.setData(nfrv);	
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
	//功能：获得掉落物品数量
	//参数：nAw数字和权值
	private int getComRand(int[][] nAw){
		
		int arrayLength = nAw.length;
		if( arrayLength==0 ){
			return 0;
		}
		if( nAw[0].length != 2 ){
			return 0;
		}
		
		int max = 0;
		for( int i=0; i<arrayLength; i++ ){			
			max += nAw[i][1];
		}
		
		int[] tempRand = RandomUtil.random(1, max, 1);
		
		max = 0;
		int rtnRand = 0;
		for( int i=0; i<arrayLength; i++ ){			
			max += nAw[i][1];
			if( tempRand[0] < max ){
				rtnRand = nAw[i][0];
				break;
			}
		}		
		
		return rtnRand;
	}
	
	public MessageRespVo npcZuhe(HttpServletRequest request, HttpServletResponse response,String jsonStr){
		
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcAddReqVo nrv = JSON.parseObject(gcrv.getData().toString(),NpcAddReqVo.class);
		result.setTs(gcrv.getTs());
		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
	
}
