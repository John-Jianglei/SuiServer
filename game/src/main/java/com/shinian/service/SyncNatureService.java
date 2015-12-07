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
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.YuanfenInfoRedisVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;
import com.shinian.util.Nature;



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
	public NpcInfoVo syncNpcById(int id)		
	{
		NpcInfoVo npc = npcInfoService.getNpcInfoById(id);
		List<NpcInfoVo> army = armyInfoService.getArmyOnBattle(npc.getUid());
		
		Map<Integer, NpcInfoVo> mpArmy = new HashMap<Integer, NpcInfoVo>();  
		for (NpcInfoVo p : army){
			mpArmy.put(p.getId(), p);
		}
		
		Set<Integer> npcSet = new HashSet<Integer>();
		npcSet.clear();

		// synthesize props: anv = bnv + pv 
		List<PropInfoVo> plist = propInfoService.getPropListOfNpc(id);	// synthesize props
		for (PropInfoVo prop:plist){
			updateNpcNatureByProp(npc, prop.getComId());
		}


		// synthesize yuanfen: anv = anv + bnv*yv
		npcSet.addAll(checkYuanfen(npc, mpArmy, plist));
		
		if (npc.getYuanfen1() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen1());
		if (npc.getYuanfen2() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen2());
		if (npc.getYuanfen3() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen3());
		if (npc.getYuanfen4() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen4());
		
		
		// synthesize skills
		
		
		
		syncNatureDao.updateNpcNature(npc);
		return npc;
	}
	
	private Set<Integer> checkYuanfen(NpcInfoVo npc, Map<Integer, NpcInfoVo> army, List<PropInfoVo> plist)
	{
		Set<Integer> npcSet = new HashSet<Integer>();
		npcSet.clear();
		
		
		
		return npcSet; 
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