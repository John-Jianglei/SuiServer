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
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcUpdateRedisVo;
import com.shinian.vo.NpcUpdateVo;
import com.shinian.vo.PropInfoRedisVo;


@Service
public class NpcUpdateService {
		
	@Autowired
	NpcUpdateDao npcUpdateDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	PropInfoService propInfoService;
	
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
		//int tempCardId = 0;
		int[][] ExpCard = new int[expCardList.size()][2]; //每列是id和数量
		for( int i=0; i<expCardList.size(); i++ ){
			ExpCard[i][0] = 0;
			ExpCard[i][0] = 0;
		}
		
		for(ExpCardVo expCardVo : expCardList){
			for( int i=0; i<expCardList.size(); i++ ){
				if( ExpCard[i][0] ==0 || ExpCard[i][0]==expCardVo.getId() ){
					ExpCard[i][0] = expCardVo.getId();
					ExpCard[i][1]++;
					break;
				}
			}
			PropInfoRedisVo v = redisCacheUtil.getPropInfoByComId(expCardVo.getId());
			if( 200 == v.getNature() ){
				totalExp += v.getVal();
			}
		}
		
		//Has enough exp card in db?
		for( int i=0; i<expCardList.size(); i++ ){
			if( 0==ExpCard[i][0] ){
				break;
			}
			if( ExpCard[i][1] > npcUpdateDao.getExpCardNumbyId(ExpCard[i][0], Uid)){
				result.setCode(Message.MSG_CODE_PROP_NOT_EXIST);
				result.setMsg(Message.MSG_PROP_NOT_EXIST);
				return result;
			}
		}
					
		//cacu exp
		//get exp by id
		NpcUpdateVo updateNpcVo = npcUpdateDao.getNpcById(npcId);
		NpcInfoRedisVo npcCommon = redisCacheUtil.getNpcInfoByComId(updateNpcVo.getComId());
		if (npcCommon == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		NpcUpdateRedisVo npcUpdate;
		
//		for (NpcUpdateVo updateNpcVo : list){
			
		int playerLevel = npcUpdateDao.getPlayerLevelByUid(updateNpcVo.getUid());	
		int npcLevel = updateNpcVo.getLevel();
		long npcCurrentExp;
		long npcLastExp;	//武将最终经验
		long tempExp;
		if( npcLevel>playerLevel || npcLevel==200){
			result.setCode(Message.MSG_CODE_NPC_CANNOT_LEVEL);
			result.setMsg(Message.MSG_NPC_CANNOT_LEVEL);
			return result;
		}
			
		npcCurrentExp = updateNpcVo.getExperience();
		
		for( int j=npcLevel; j<=playerLevel; j++){
			//the MAX Level of rpc is 200				
			if( j==200 ){
				updateNpcVo.setLevel(j);
				npcUpdate = redisCacheUtil.getExpBylevel(200);
				npcLastExp = npcUpdate.getExperience();
				updateNpcVo.setExperience(npcLastExp);
				//write database
				npcUpdateDao.setExpById(npcId, j, npcLastExp);					
				break;
			}
			
			npcUpdate = redisCacheUtil.getExpBylevel(j+1>=200?200:j+1);
			tempExp = npcUpdate.getExperience();
			if( npcCurrentExp + totalExp < tempExp){
				updateNpcVo.setLevel(j);
				updateNpcVo.setExperience(npcCurrentExp + totalExp); //not level up
				//write database
				npcUpdateDao.setExpById(npcId, j, npcCurrentExp + totalExp);
				break;
			}
			if( npcCurrentExp + totalExp >= tempExp){
				npcCurrentExp = npcCurrentExp + totalExp - tempExp;
				totalExp = 0;
				
				if( j==playerLevel && npcCurrentExp>=tempExp){
					updateNpcVo.setLevel(j);
					updateNpcVo.setExperience(tempExp); 
					npcUpdateDao.setExpById(npcId, j, tempExp);
					break;
				}
			}				
		}		
		
		//update db for card number has been changed
		for( int i=0; i<expCardList.size(); i++ ){
			if( 0==ExpCard[i][0] ){
				break;
			}
			propInfoService.consumePropertyOfPlayer(Uid, ExpCard[i][0], ExpCard[i][1]);
		}
		
		//重新计算武将战力，写数据库，返回相应结果给客户端
		int newAttack = 0;
		int newHealth = 0;

		if( updateNpcVo.getPinjie()<10 ){
			newAttack = ( npcCommon.getAttack() + updateNpcVo.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + updateNpcVo.getPinjie() * 5 ) / 100;
			newHealth = ( npcCommon.getHealth() + updateNpcVo.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + updateNpcVo.getPinjie() * 5 ) / 100;
		}
		else if( updateNpcVo.getPinjie()<20 ){
			newAttack = ( npcCommon.getAttack() + updateNpcVo.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + (updateNpcVo.getPinjie()-10) * 7 + 11*5 ) / 100;
			newHealth = ( npcCommon.getHealth() + updateNpcVo.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + (updateNpcVo.getPinjie()-10) * 7 + 11*5 ) / 100;
		}
		else if( updateNpcVo.getPinjie()<30 ){
			newAttack = ( npcCommon.getAttack() + updateNpcVo.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + (updateNpcVo.getPinjie()-20) * 10 + 11*5 + 11*7 ) / 100;
			newHealth = ( npcCommon.getHealth() + updateNpcVo.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + (updateNpcVo.getPinjie()-20) * 10 + 11*5 + 11*7 ) / 100;
		}
		else{
			newAttack = ( npcCommon.getAttack() + updateNpcVo.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + (updateNpcVo.getPinjie()-30) * 15 + 11*5 + 11*7 + 11*10 ) / 100;
			newHealth = ( npcCommon.getHealth() + updateNpcVo.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + (updateNpcVo.getPinjie()-30) * 15 + 11*5 + 11*7 + 11*10 ) / 100;
		}
		
		//更新武将品阶、攻击和生命
		npcUpdateDao.setNpcInfobyId(updateNpcVo.getLevel(), newAttack, newHealth, npcId);
		
		updateNpcVo.setExpCardList(expCardList);
		updateNpcVo.setId(npcId);
		updateNpcVo.setUid(Uid);
		updateNpcVo.setHealth(newHealth);
		updateNpcVo.setAttack(newAttack);
		
		result.setData(updateNpcVo);		
		result.setCode(Message.MSG_CODE_OK);
	
		return result;
	}


}
