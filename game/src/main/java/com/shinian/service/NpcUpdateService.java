package com.shinian.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcUpdateDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.ExpCardVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcUpdateVo;


@Service
public class NpcUpdateService {
		
	@Autowired
	NpcUpdateDao npcUpdateDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	public MessageRespVo npcUpdate(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		NpcUpdateVo npcuv = JSON.parseObject(gcrv.getData().toString(),NpcUpdateVo.class);
		result.setTs(gcrv.getTs());
		
		if (0 == npcuv.getId() || "".equals(npcuv.getId()) 
				|| null == npcuv.getExpCardList() || null == npcuv.getUid()) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		//parse list and get totalExp
		List<ExpCardVo> expCardList = npcuv.getExpCardList();
		int npcId = npcuv.getId();
		String Uid = npcuv.getUid();
		long totalExp = 0;
		
		for(ExpCardVo expCardVo : expCardList){
			totalExp += npcUpdateDao.getCardExpById(expCardVo.getId());
		}
					
		//cacu exp
		//1、get exp by id
		List<NpcUpdateVo> list = npcUpdateDao.getRpcById(npcId);
		
		if(list == null || list.size() == 0){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		for (NpcUpdateVo updateNpcVo : list){
			
			int playerLevel = npcUpdateDao.getPlayerLevelByUid(updateNpcVo.getUid());	
			int npcLevel = updateNpcVo.getLevel();
			long npcCurrentExp;
			
			if( npcLevel>playerLevel || npcLevel==200){
				result.setCode(Message.MSG_CODE_NPC_CANNOT_LEVEL);
				result.setMsg(Message.MSG_NPC_CANNOT_LEVEL);
				return result;
			}
			
			for( int j=npcLevel; j<=playerLevel; j++){
				//the MAX Level of rpc is 200				
				if( j==200 ){
					updateNpcVo.setLevel(j);
					updateNpcVo.setExperience(npcUpdateDao.getExpBylevel(200));
					break;
					//write database
				}
				npcCurrentExp = updateNpcVo.getExperience();
				if( npcCurrentExp + totalExp < npcUpdateDao.getExpBylevel(npcLevel+1>=200?200:npcLevel+1)){
					updateNpcVo.setLevel(j);
					updateNpcVo.setExperience(npcCurrentExp + totalExp); //not level up
					//write database
					break;
				}
				if( npcCurrentExp + totalExp >= npcUpdateDao.getExpBylevel(npcLevel+1>=200?200:npcLevel+1)){
					npcCurrentExp = npcCurrentExp + totalExp - npcUpdateDao.getExpBylevel(npcLevel+1>=200?200:npcLevel+1);
					totalExp = 0;
					
					if( j==playerLevel && npcCurrentExp>=npcUpdateDao.getExpBylevel(npcLevel+1>=200?200:npcLevel+1)){
						updateNpcVo.setLevel(j);
						updateNpcVo.setExperience(npcUpdateDao.getExpBylevel(npcLevel+1>=200?200:npcLevel+1)); 
						break;
					}
				}
				

				
				
			}		
			
			//Q? how to use redisCacheUtil ? syq
			//NpcInfoVo comNpcVo  = redisCacheUtil.getNpcInfoByComId(gameNpcVo.getComId());
			
			//token need not send back?? syq
			
			updateNpcVo.setExpCardList(expCardList);
			updateNpcVo.setId(npcId);
			updateNpcVo.setUid(Uid);

		}
		
		result.setData(list);		
		result.setCode(Message.MSG_CODE_OK);
	
		return result;
	}


}
