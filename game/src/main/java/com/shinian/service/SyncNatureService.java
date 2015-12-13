package com.shinian.service;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.SyncNatureDao;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.JinengRedisVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoReqVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.YuanfenInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;
import com.shinian.util.Nature;
import com.shinian.util.Constant;



@Service
public class SyncNatureService {		//	update the actual nature, which synthesizes the value from prop, team, skill, etc.
		
	@Autowired
	PropInfoService propInfoService;
	
	@Autowired
	ArmyInfoService armyInfoService;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	NpcInfoService npcInfoService;
	
	@Autowired
	SyncNatureDao syncNatureDao;
	
	//	actual nature value(anv) is combination of base nature value(bnv), prop value(pv), yuanfen value(yv) and skills value(sv), 
	//	which is:  anv = bnv + pv + bnv*yv + bnv*sv
	public List<NpcInfoVo> refreshArmy(String uid)
	{
		List<NpcInfoVo> army = armyInfoService.getArmyOnBattle(uid);
		
		//	initialize Nature: anv = bnv
		for (NpcInfoVo npc : army) {
			npc.initNature();
			npc.disableYuanfen1();
			npc.disableYuanfen2();
			npc.disableYuanfen3();
			npc.disableYuanfen4();
		}

		for (NpcInfoVo npc : army){
			// synthesize props: anv = anv + pv 
			List<PropInfoVo> plist = propInfoService.getPropListOfNpc(npc.getId());	// synthesize props
			for (PropInfoVo prop:plist){
				updateNpcNatureByProp(npc, prop.getComId());
			}
			
			// synthesize yuanfen: anv = anv + bnv*yv
			checkYuanfen(npc, army, plist);
			
			if (npc.getYuanfen1() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen1());
			if (npc.getYuanfen2() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen2());
			if (npc.getYuanfen3() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen3());
			if (npc.getYuanfen4() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen4());
			
			// synthesize skills
			if (npc.hasSkill1()) updateArmyNatureBySkill(npc, army, npc.getSkill1());
			if (npc.hasSkill2()) updateArmyNatureBySkill(npc, army, npc.getSkill2());
			if (npc.hasSkill3()) updateArmyNatureBySkill(npc, army, npc.getSkill3());
			if (npc.hasSkill4()) updateArmyNatureBySkill(npc, army, npc.getSkill4());
			if (npc.hasSkill5()) updateArmyNatureBySkill(npc, army, npc.getSkill5());
			if (npc.hasSkill6()) updateArmyNatureBySkill(npc, army, npc.getSkill6());
			if (npc.hasSkill7()) updateArmyNatureBySkill(npc, army, npc.getSkill7());
			if (npc.hasSkill8()) updateArmyNatureBySkill(npc, army, npc.getSkill8());
			if (npc.hasSkill9()) updateArmyNatureBySkill(npc, army, npc.getSkill9());
			if (npc.hasSkill10()) updateArmyNatureBySkill(npc, army, npc.getSkill10());
			if (npc.hasSkill11()) updateArmyNatureBySkill(npc, army, npc.getSkill11());
		}
		
		for (NpcInfoVo p : army) syncNatureDao.updateNpcNature(p); 
		
		return army;
	}
	
	public List<NpcInfoVo> refreshArmy(String uid, NpcInfoVo npc)
	{
		List<NpcInfoVo> army = refreshArmy(uid);
		
		if (npc.getPosition() >= Constant.CON_ARMY_SIZE){
			//	initialize Nature: anv = bnv
			npc.initNature();
			npc.disableYuanfen1();
			npc.disableYuanfen2();
			npc.disableYuanfen3();
			npc.disableYuanfen4();

			List<PropInfoVo> plist = propInfoService.getPropListOfNpc(npc.getId());	// synthesize props
			for (PropInfoVo prop:plist){
				updateNpcNatureByProp(npc, prop.getComId());
			}
			
			syncNatureDao.updateNpcNature(npc);
			army.add(npc);
		}
		
		return army;
	}
	
	private void updateArmyNatureBySkill(NpcInfoVo npc, List<NpcInfoVo> army, int skill)
	{
		JinengRedisVo jnvo = redisCacheUtil.getJinengInfoById(Math.abs(skill));
		
		switch(jnvo.getAdd_health()){
		case Constant.CON_ARMY_POSITION_NULL:
			break;
			
		case Constant.CON_ARMY_POSITION_SELF:
			npc.setAttack(npc.getAttack() + npc.getAttackBase() * jnvo.getAdd_health() / 100);
			npc.setHealth(npc.getHealth() + npc.getHealthBase() * jnvo.getAdd_health() / 100);
			break;
			
		case Constant.CON_ARMY_POSITION_FRONT:
			for (NpcInfoVo p : army){
				if (p.getPosition() < (Constant.CON_ARMY_SIZE)/2){
					p.setAttack(p.getAttack() + p.getAttackBase() * jnvo.getAdd_health() / 100);
					p.setHealth(p.getHealth() + p.getHealthBase() * jnvo.getAdd_health() / 100);
				}
			}
			break;
			
		case Constant.CON_ARMY_POSITION_REAR:
			for (NpcInfoVo p : army){
				if (p.getPosition() > (Constant.CON_ARMY_SIZE)/2 - 1){
					p.setAttack(p.getAttack() + p.getAttackBase() * jnvo.getAdd_health() / 100);
					p.setHealth(p.getHealth() + p.getHealthBase() * jnvo.getAdd_health() / 100);
				}
			}
			break;
			
		case Constant.CON_ARMY_POSITION_ALL:
			for (NpcInfoVo p : army){
				p.setAttack(p.getAttack() + p.getAttackBase() * jnvo.getAdd_health() / 100);
				p.setHealth(p.getHealth() + p.getHealthBase() * jnvo.getAdd_health() / 100);
			}
			break;
			
		default:
			break;
		}
		
		switch(jnvo.getShuxing_type()){
		case Constant.CON_ARMY_POSITION_NULL:
			break;
			
		case Constant.CON_ARMY_POSITION_SELF:
			npc.setPojia(npc.getPojia() + jnvo.getAdd_pojia());
			npc.setFachuan(npc.getFachuan() + jnvo.getAdd_fachuan());
			npc.setBaoji(npc.getBaoji() + jnvo.getAdd_baoji());
			npc.setMingzhong(npc.getMingzhong() + jnvo.getAdd_mingzhong());
			npc.setShanbi(npc.getShanbi() + jnvo.getAdd_shanbi());
			break;
			
		case Constant.CON_ARMY_POSITION_FRONT:
			for (NpcInfoVo p : army){
				if (p.getPosition() < (Constant.CON_ARMY_SIZE)/2){
					p.setPojia(p.getPojia() + jnvo.getAdd_pojia());
					p.setFachuan(p.getFachuan() + jnvo.getAdd_fachuan());
					p.setBaoji(p.getBaoji() + jnvo.getAdd_baoji());
					p.setMingzhong(p.getMingzhong() + jnvo.getAdd_mingzhong());
					p.setShanbi(p.getShanbi() + jnvo.getAdd_shanbi());				
				}
			}
			break;
			
		case Constant.CON_ARMY_POSITION_REAR:
			for (NpcInfoVo p : army){
				if (p.getPosition() > (Constant.CON_ARMY_SIZE)/2 - 1){
					p.setPojia(p.getPojia() + jnvo.getAdd_pojia());
					p.setFachuan(p.getFachuan() + jnvo.getAdd_fachuan());
					p.setBaoji(p.getBaoji() + jnvo.getAdd_baoji());
					p.setMingzhong(p.getMingzhong() + jnvo.getAdd_mingzhong());
					p.setShanbi(p.getShanbi() + jnvo.getAdd_shanbi());				
				}
			}
			break;
			
		case Constant.CON_ARMY_POSITION_ALL:
			for (NpcInfoVo p : army){
				p.setPojia(p.getPojia() + jnvo.getAdd_pojia());
				p.setFachuan(p.getFachuan() + jnvo.getAdd_fachuan());
				p.setBaoji(p.getBaoji() + jnvo.getAdd_baoji());
				p.setMingzhong(p.getMingzhong() + jnvo.getAdd_mingzhong());
				p.setShanbi(p.getShanbi() + jnvo.getAdd_shanbi());			
			}
			break;
			
		default:
			break;
		}
	}
		
	private void checkYuanfen(NpcInfoVo npc, List<NpcInfoVo> army, List<PropInfoVo> plist)
	{
		YuanfenInfoRedisVo yfrv1 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen1());
		if (yfrv1 != null){
			switch(yfrv1.getCategory()){
			case Nature.YUANFEN_CATEGORY_NPC:
				for (NpcInfoVo p:army)
					if (p.getComId() == yfrv1.getObjId()) npc.enableYuanfen1();

				break;
				
			case Nature.YUANFEN_CATEGORY_PROP:
				for (PropInfoVo p:plist)
					if (p.getComId() == yfrv1.getObjId()) npc.enableYuanfen1();
						
				break;
				
			default:
				break;
			}			
		}

		
		YuanfenInfoRedisVo yfrv2 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen2());
		if (yfrv2 != null){
			switch(yfrv2.getCategory()){
			case Nature.YUANFEN_CATEGORY_NPC:
				for (NpcInfoVo p:army)
					if (p.getComId() == yfrv2.getObjId()) npc.enableYuanfen2();

				break;
				
			case Nature.YUANFEN_CATEGORY_PROP:
				for (PropInfoVo p:plist)
					if (p.getComId() == yfrv2.getObjId()) npc.enableYuanfen2();
						
				break;
				
			default:
				break;
			}
		}

		
		YuanfenInfoRedisVo yfrv3 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen3());
		if (yfrv2 != null){
			switch(yfrv3.getCategory()){
			case Nature.YUANFEN_CATEGORY_NPC:
				for (NpcInfoVo p:army)
					if (p.getComId() == yfrv3.getObjId()) npc.enableYuanfen3();

				break;
				
			case Nature.YUANFEN_CATEGORY_PROP:
				for (PropInfoVo p:plist)
					if (p.getComId() == yfrv3.getObjId()) npc.enableYuanfen3();
						
				break;
				
			default:
				break;
			}		
		}

		
		YuanfenInfoRedisVo yfrv4 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen4());
		if (yfrv2 != null){
			switch(yfrv4.getCategory()){
			case Nature.YUANFEN_CATEGORY_NPC:
				for (NpcInfoVo p:army)
					if (p.getComId() == yfrv4.getObjId()) npc.enableYuanfen4();

				break;
				
			case Nature.YUANFEN_CATEGORY_PROP:
				for (PropInfoVo p:plist)
					if (p.getComId() == yfrv4.getObjId()) npc.enableYuanfen4();
						
				break;
				
			default:
				break;
			}
		}
	}
	
	private void updateNpcNatureByYuanfen(NpcInfoVo npc, int yfId)
	{
		YuanfenInfoRedisVo yf = redisCacheUtil.getYuanfenInfoByComId(yfId);
		npc.setAttack(npc.getAttack() + npc.getAttackBase() * yf.getAddAttack() / 100);
		npc.setHealth(npc.getHealth() + npc.getHealthBase() * yf.getAddHealth() / 100);
	}
	
	private int updateNpcNatureByProp(NpcInfoVo npc, int propComId)
	{
		PropInfoRedisVo prop = redisCacheUtil.getPropInfoByComId(propComId);
		if (prop == null) return -1;
		
		switch(prop.getNature()){
		case Nature.NT_ATT_HEALTH:
			npc.setHealth(npc.getHealth() + prop.getVal());
			break;
			
		case Nature.NT_ATT_ATTACK: 
			npc.setAttack(npc.getAttack() + prop.getVal());
			break;
			
		case Nature.NT_ATT_HUJIA:
			npc.setHujia(npc.getHujia() + prop.getVal());
			break;
			
		case Nature.NT_ATT_POJIA:
			npc.setPojia(npc.getPojia() + prop.getVal());
			break;
			
		case Nature.NT_ATT_FACHUAN:
			npc.setFachuan(npc.getFachuan() + prop.getVal());
			break;
			
		case Nature.NT_ATT_FAKANG:
			npc.setFakang(npc.getFakang() + prop.getVal());
			break;
			
		case Nature.NT_ATT_BAOJI:
			npc.setBaoji(npc.getBaoji() + prop.getVal());
			break;
			
		case Nature.NT_ATT_RENXING:
			npc.setRenxing(npc.getRenxing() + prop.getVal());
			break;
			
		case Nature.NT_ATT_MINGZHONG:
			npc.setMingzhong(npc.getMingzhong() + prop.getVal());
			break;
			
		case Nature.NT_ATT_SHANBI:
			npc.setShanbi(npc.getShanbi() + prop.getVal());
			break;
			
		case Nature.NT_ATT_XIXUE:
			npc.setXixue(npc.getXixue() + prop.getVal());
			break;
			
		case Nature.NT_ATT_FANTAN:
			npc.setFantan(npc.getFantan() + prop.getVal());
			break;
			
		case Nature.NT_ATT_JIYUN:
			npc.setJiyun(npc.getJiyun() + prop.getVal());
			break;
			
		case Nature.NT_ATT_KANGYUN:
			npc.setKangyun(npc.getKangyun() + prop.getVal());
			break;
			
		case Nature.NT_ATT_GEDANG:
			npc.setGedang(npc.getGedang() + prop.getVal());
			break;
			
		case Nature.NT_ATT_GEDANGPOSS:
			npc.setGedangPoss(npc.getGedangPoss() + prop.getVal());
			break;
			
		case Nature.NT_ATT_REDUCE:
			npc.setReduce(npc.getReduce() + prop.getVal());
			break;
			
		default:
			break;
		}
		return 0;
	}
	
	
	public MessageRespVo testRefreshArmy(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		NpcInfoVo nrv = JSON.parseObject(gcrv.getData().toString(),NpcInfoVo.class);
		result.setTs(gcrv.getTs());
		
		List<NpcInfoVo> piv = refreshArmy(nrv.getUid());
				
		result.setData(piv);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}