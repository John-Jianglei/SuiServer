package com.shinian.service;

import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.SyncNatureDao;
import com.shinian.util.Message;
import com.shinian.vo.JinengRedisVo;
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

		for (NpcInfoVo npc : army){
			// synthesize props: anv = bnv + pv 
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
			if (npc.isSkill1()) updateArmyNatureBySkill(npc, army, npc.getSkill1());
			if (npc.isSkill2()) updateArmyNatureBySkill(npc, army, npc.getSkill2());
			if (npc.isSkill3()) updateArmyNatureBySkill(npc, army, npc.getSkill3());
			if (npc.isSkill4()) updateArmyNatureBySkill(npc, army, npc.getSkill4());
			if (npc.isSkill5()) updateArmyNatureBySkill(npc, army, npc.getSkill5());
			if (npc.isSkill6()) updateArmyNatureBySkill(npc, army, npc.getSkill6());
			if (npc.isSkill7()) updateArmyNatureBySkill(npc, army, npc.getSkill7());
			if (npc.isSkill8()) updateArmyNatureBySkill(npc, army, npc.getSkill8());
			if (npc.isSkill9()) updateArmyNatureBySkill(npc, army, npc.getSkill9());
			if (npc.isSkill10()) updateArmyNatureBySkill(npc, army, npc.getSkill10());
			if (npc.isSkill11()) updateArmyNatureBySkill(npc, army, npc.getSkill11());
		}
		
		for (NpcInfoVo p : army) syncNatureDao.updateNpcNature(p); 
		
		return army;
	}
	
	public List<NpcInfoVo> refreshArmy(String uid, NpcInfoVo npc)
	{
		List<NpcInfoVo> army = refreshArmy(uid);
		
		if (npc.getPosition() >= Constant.CON_ARMY_SIZE){
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
		
		YuanfenInfoRedisVo yfrv2 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen2());
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
		
		YuanfenInfoRedisVo yfrv3 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen3());
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
		
		YuanfenInfoRedisVo yfrv4 = redisCacheUtil.getYuanfenInfoByComId(npc.getYuanfen4());
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
	
	private void updateNpcNatureByYuanfen(NpcInfoVo npc, int yfId)
	{
		YuanfenInfoRedisVo yf = redisCacheUtil.getYuanfenInfoByComId(yfId);
		npc.setAttack(npc.getAttack() + npc.getAttackBase() * yf.getAddAttack() / 100);
		npc.setHealth(npc.getHealth() + npc.getHealthBase() * yf.getAddHealth() / 100);
	}
	
	private void updateNpcNatureByProp(NpcInfoVo npc, int propComId)
	{
		PropInfoRedisVo prop = redisCacheUtil.getPropInfoByComId(propComId);
		
		switch(prop.getNature()){
		case Nature.NT_ATT_HEALTH:
			npc.setHealth(npc.getHealthBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_ATTACK: 
			npc.setAttack(npc.getAttackBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_HUJIA:
			npc.setHujia(npc.getHujiaBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_POJIA:
			npc.setPojia(npc.getPojiaBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_FACHUAN:
			npc.setFachuan(npc.getFachuanBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_FAKANG:
			npc.setFakang(npc.getFakangBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_BAOJI:
			npc.setBaoji(npc.getBaojiBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_RENXING:
			npc.setRenxing(npc.getRenxingBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_MINGZHONG:
			npc.setMingzhong(npc.getMingzhongBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_SHANBI:
			npc.setShanbi(npc.getShanbiBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_XIXUE:
			npc.setXixue(npc.getXixueBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_FANTAN:
			npc.setFantan(npc.getFantanBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_JIYUN:
			npc.setJiyun(npc.getJiyunBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_KANGYUN:
			npc.setKangyun(npc.getKangyunBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_GEDANG:
			npc.setGedang(npc.getGedangBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_GEDANGPOSS:
			npc.setGedangPoss(npc.getGedangPossBase() + prop.getVal());
			break;
			
		case Nature.NT_ATT_REDUCE:
			npc.setReduce(npc.getReduceBase() + prop.getVal());
			break;
			
		default:
			return;
		}
		
	}
	
}