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
import com.shinian.vo.YuanfenInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.vo.NpcInfoVo;
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

		}
		
		return army;
	}
	
	public NpcInfoVo syncNpcById(int id)		
	{
		NpcInfoVo npc = npcInfoService.getNpcInfoById(id);
		List<NpcInfoVo> army = armyInfoService.getArmyOnBattle(npc.getUid());
		
		Map<Integer, NpcInfoVo> mpArmy = new HashMap<Integer, NpcInfoVo>();  
		for (NpcInfoVo p : army){
			mpArmy.put(p.getId(), p);
		}
		
		Set<Integer> npcSet = new HashSet<Integer>();
		Set<Integer> setYuanfen = new HashSet<Integer>();
		npcSet.clear();
		setYuanfen.clear();

		// synthesize props: anv = bnv + pv 
		List<PropInfoVo> plist = propInfoService.getPropListOfNpc(id);	// synthesize props
		for (PropInfoVo prop:plist){
			updateNpcNatureByProp(npc, prop.getComId());
		}


		// synthesize yuanfen: anv = anv + bnv*yv
//		setYuanfen = checkYuanfen(npc, army, plist);
//		npcSet.addAll(setYuanfen);
		
		if (npc.getYuanfen1() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen1());
		if (npc.getYuanfen2() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen2());
		if (npc.getYuanfen3() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen3());
		if (npc.getYuanfen4() > 0) updateNpcNatureByYuanfen(npc, npc.getYuanfen4());
		
		
		// synthesize skills
		
		
		
		syncNatureDao.updateNpcNature(npc);
		return npc;
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

/*					
YuanfenInfoRedisVo y1 = redisCacheUtil.getYuanfenInfoByComId(Math.abs(p.getYuanfen1()));
if ((y1.getCategory() == YUANFEN_CATEGORY_NPC) && (y1.getObjId() == npc.getComId())) {
	p.enableYuanfen1();
	npcSet.add(p.getId());
}

YuanfenInfoRedisVo y2 = redisCacheUtil.getYuanfenInfoByComId(Math.abs(p.getYuanfen2()));
if ((y2.getCategory() == YUANFEN_CATEGORY_NPC) && (y2.getObjId() == npc.getComId())) {
	p.enableYuanfen2();
	npcSet.add(p.getId());
}

YuanfenInfoRedisVo y3 = redisCacheUtil.getYuanfenInfoByComId(Math.abs(p.getYuanfen3()));
if ((y3.getCategory() == YUANFEN_CATEGORY_NPC) && (y3.getObjId() == npc.getComId())) {
	p.enableYuanfen3();
	npcSet.add(p.getId());
}

YuanfenInfoRedisVo y4 = redisCacheUtil.getYuanfenInfoByComId(Math.abs(p.getYuanfen4()));
if ((y4.getCategory() == YUANFEN_CATEGORY_NPC) && (y4.getObjId() == npc.getComId())) {
	p.enableYuanfen4();
	npcSet.add(p.getId());
*/
