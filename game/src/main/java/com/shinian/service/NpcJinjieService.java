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
		
		//parse list 
		int npcId = npcuv.getId();
		int comsumedid = npcuv.getComsumedid();
		String Uid = npcuv.getUid();
		npcJinjieRespv.setId(npcId);
		npcJinjieRespv.setComsumedid(comsumedid);
		npcJinjieRespv.setUid(Uid);		

		NpcJinjieVo npc1 = npcJinjieDao.getNpcJinjieByid(npcId);
		NpcJinjieVo npc2 = npcJinjieDao.getNpcJinjieByid(comsumedid);
		
		NpcInfoRedisVo npcCommon = redisCacheUtil.getNpcInfoByComId(npc1.getComId());
		if (npcCommon == null){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
		
		//获取升级所需材料，根据pinjie.  		//使用redis
		JinjieMaterialRedisVo jinjieNeedMaterial = npcJinjieDao.getJinjieNeedMByPinjie(npc1.getPinjie()+1);
		jinjieNeedMaterial.setNextpinjie(npc1.getPinjie()+1);
		
		if( jinjieNeedMaterial.getNextpinjie()%10!=0 ){
			
			//判断进阶武将和升级武将是否相同武将，是否是同一玩家的武将
			if(npc1.getComId() != npc2.getComId()
					|| !npc1.getUid().equals(npc2.getUid())){
				result.setCode(Message.MSG_CODE_SEL_NPC_ERROR);
				result.setMsg(Message.MSG_SEL_NPC_ERROR);
				return result;
			}			
		}
		
		//银两是否足够
		npc1.setSilver(npcJinjieDao.getSilverByUid(Uid));
		if(npc1.getSilver().compareTo(jinjieNeedMaterial.getSilver())<0){
			result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
			result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
			return result;				
		}
		npcJinjieRespv.setSilver(jinjieNeedMaterial.getSilver());
		
		//获取玩家已有材料,比较玩家是否有足够材料
		if( jinjieNeedMaterial.getJinjiedan() > 0 ){
			npc1.setJinjiedan(npcJinjieDao.getMaterialNumbyId(7, Uid));
			if(npc1.getJinjiedan()<jinjieNeedMaterial.getJinjiedan()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;				
			}
			//npc1.setJinjiedan( npc1.getJinjiedan()-jinjieNeedMaterial.getJinjiedan() );
			
			npcJinjieRespv.setJinjiedan(jinjieNeedMaterial.getJinjiedan());
		}
		
		if( jinjieNeedMaterial.getFivecolorstone() > 0 ){
			npc1.setFivecolorstone(npcJinjieDao.getMaterialNumbyId(8, Uid));	
			if(npc1.getFivecolorstone()<jinjieNeedMaterial.getFivecolorstone()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;		
			}
			//npc1.setFivecolorstone( npc1.getFivecolorstone()-jinjieNeedMaterial.getFivecolorstone() );
			npcJinjieRespv.setFivecolorstone(jinjieNeedMaterial.getFivecolorstone());

		}
		
		if( jinjieNeedMaterial.getTigertally() > 0 ){
			npc1.setTigertally(npcJinjieDao.getMaterialNumbyId(9, Uid));
			if(npc1.getTigertally()<jinjieNeedMaterial.getTigertally()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;	
			}
			//npc1.setTigertally( npc1.getTigertally()-jinjieNeedMaterial.getTigertally() );
			npcJinjieRespv.setTigertally(jinjieNeedMaterial.getTigertally());
		}
		
		if( jinjieNeedMaterial.getEviltally() > 0 ){
			npc1.setEviltally(npcJinjieDao.getMaterialNumbyId(10, Uid));
			if(npc1.getEviltally()<jinjieNeedMaterial.getEviltally()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;
			}
			//npc1.setEviltally( npc1.getEviltally()-jinjieNeedMaterial.getEviltally() );
			npcJinjieRespv.setEviltally(jinjieNeedMaterial.getEviltally());
		}
		
		if( jinjieNeedMaterial.getPloughtally() > 0 ){
			npc1.setPloughtally(npcJinjieDao.getMaterialNumbyId(11, Uid));
			if(npc1.getPloughtally()<jinjieNeedMaterial.getPloughtally()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;
			}
			//npc1.setPloughtally( npc1.getPloughtally()-jinjieNeedMaterial.getPloughtally() );
			npcJinjieRespv.setPloughtally(jinjieNeedMaterial.getPloughtally());
		}
		
		if( jinjieNeedMaterial.getSttally() > 0 ){
			npc1.setSttally(npcJinjieDao.getMaterialNumbyId(12, Uid));
			if(npc1.getSttally()<jinjieNeedMaterial.getSttally()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;
			}
			//npc1.setSttally( npc1.getSttally()-jinjieNeedMaterial.getSttally() );
			npcJinjieRespv.setSttally(jinjieNeedMaterial.getSttally());
		}
		
		if( jinjieNeedMaterial.getSuitangmedal() > 0 ){
			npc1.setSuitangmedal(npcJinjieDao.getMaterialNumbyId(13, Uid));
			if(npc1.getSuitangmedal()<jinjieNeedMaterial.getSuitangmedal()){
				result.setCode(Message.MSG_CODE_PROP_NOT_ENOUGH);
				result.setMsg(Message.MSG_PROP_NOT_ENOUGH);
				return result;
			}
			//npc1.setSuitangmedal( npc1.getSuitangmedal()-jinjieNeedMaterial.getSuitangmedal() );
			npcJinjieRespv.setSuitangmedal(jinjieNeedMaterial.getSuitangmedal());
		}
		
		//写数据库
		npcJinjieDao.setSilverbyId(npc1.getSilver().subtract(jinjieNeedMaterial.getSilver()), Uid);

		if(jinjieNeedMaterial.getJinjiedan()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 7, jinjieNeedMaterial.getJinjiedan());
		}
		if(jinjieNeedMaterial.getFivecolorstone()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 8, jinjieNeedMaterial.getFivecolorstone());
		}
		if(jinjieNeedMaterial.getTigertally()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 9, jinjieNeedMaterial.getTigertally());
		}
		if(jinjieNeedMaterial.getEviltally()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 10, jinjieNeedMaterial.getEviltally());
		}
		if(jinjieNeedMaterial.getPloughtally()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 11, jinjieNeedMaterial.getPloughtally());
		}
		if(jinjieNeedMaterial.getSttally()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 12, jinjieNeedMaterial.getSttally());
		}
		if(jinjieNeedMaterial.getSuitangmedal()>0){
			propInfoService.consumePropertyOfPlayer(Uid, 13, jinjieNeedMaterial.getSuitangmedal());
		}
		
		if( jinjieNeedMaterial.getNextpinjie()%10!=0 ){
			npcJinjieDao.deleteNpcbyId(comsumedid);
		}
		
		//增加武将品阶，重新计算武将战力，写数据库，返回相应结果给客户端
		int newPinjie = npc1.getPinjie()+1;
		npc1.setPinjie(newPinjie);
		int newAttack = 0;
		int newHealth = 0;

		if( newPinjie<10 ){
			newAttack = ( npcCommon.getAttack() + npc1.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + newPinjie * 5 ) / 100;
			newHealth = ( npcCommon.getHealth() + npc1.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + newPinjie * 5 ) / 100;
		}
		else if( newPinjie<20 ){
			newAttack = ( npcCommon.getAttack() + npc1.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + (newPinjie-10) * 7 + 11*5 ) / 100;
			newHealth = ( npcCommon.getHealth() + npc1.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + (newPinjie-10) * 7 + 11*5 ) / 100;
		}
		else if( newPinjie<30 ){
			newAttack = ( npcCommon.getAttack() + npc1.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + (newPinjie-20) * 10 + 11*5 + 11*7 ) / 100;
			newHealth = ( npcCommon.getHealth() + npc1.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + (newPinjie-20) * 10 + 11*5 + 11*7 ) / 100;
		}
		else{
			newAttack = ( npcCommon.getAttack() + npc1.getLevel() * npcCommon.getAttackStep() )
						* ( 100 + (newPinjie-30) * 15 + 11*5 + 11*7 + 11*10 ) / 100;
			newHealth = ( npcCommon.getHealth() + npc1.getLevel() * npcCommon.getHealthStep() )
						* ( 100 + (newPinjie-30) * 15 + 11*5 + 11*7 + 11*10 ) / 100;
		}
		
		//还需要根据品阶设置技能
		
		//更新武将品阶、攻击和生命
		npcJinjieDao.setNpcInfobyId(newPinjie, newAttack, newHealth, npcId);
		npcJinjieRespv.setPinjie(newPinjie);
		npcJinjieRespv.setAttack(newAttack);
		npcJinjieRespv.setHealth(newHealth);
		
		result.setData(npcJinjieRespv);	
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}

}
