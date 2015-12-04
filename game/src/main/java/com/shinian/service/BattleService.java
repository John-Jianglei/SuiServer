package com.shinian.service;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Message;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.BattleReqVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.NpcBattleVo;
import com.shinian.vo.ActionVo;
import com.shinian.vo.RewardVo;
import com.shinian.vo.BattleReturnVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.service.PlayerInfoService;
import com.shinian.util.RandomUtil;
import com.shinian.util.Constant;

@Service
public class BattleService {
		
	@Autowired
	PlayerInfoService playerInfoService;
	
	@Autowired
	ArmyInfoService armyInfoService;

	@Autowired
	PropInfoDao propInfoDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	private NpcBattleVo[] offArmy;
	private NpcBattleVo[] defArmy;
	
	
	public MessageRespVo makeWar(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		BattleReqVo nrv = JSON.parseObject(gcrv.getData().toString(),BattleReqVo.class);
		result.setTs(gcrv.getTs());
		
		
		if (!playerInfoService.isUidExist(nrv.getOffUid()) || !playerInfoService.isUidExist(nrv.getDefUid()) ){
			result.setCode(Message.MSG_CODE_PLAYER_NOT_EXIST);
			result.setMsg(Message.MSG_PLAYER_NOT_EXIST);
			return result;
		}
		
		List<NpcInfoVo> oArmy = armyInfoService.getArmyOnBattle(nrv.getOffUid());
		List<NpcInfoVo> dArmy = armyInfoService.getArmyOnBattle(nrv.getDefUid());
		if ((oArmy == null) || (dArmy == null)){
			result.setCode(Message.MSG_CODE_NPC_NOT_EXIST);
			result.setMsg(Message.MSG_NPC_NOT_EXIST);
			return result;
		}
			
		offArmy = initBattleArmy(oArmy);
		defArmy = initBattleArmy(dArmy);
		
		List<ActionVo> lav = battle(offArmy, defArmy);
		List<RewardVo> rewardlist = postWar();
				
		BattleReturnVo re = new BattleReturnVo();
		
		re.setOffArmy(oArmy);
		re.setDefArmy(dArmy);
		re.setActions(lav);
		re.setRewards(rewardlist);
		
		result.setData(re);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;

	}
	
	public List<ActionVo> battle(NpcBattleVo[] offArmy, NpcBattleVo[] defArmy)
	{
		List<ActionVo> replay = new ArrayList<ActionVo>();
		
		int seq = 0;
		int[] targets;
		preWar(offArmy);
		preWar(defArmy);
		
		while (isSurvived(offArmy) && isSurvived(defArmy) && (seq < Constant.CON_BATTLE_MAX_SEQUENCE)){
			for (int i=0; i<Constant.CON_ARMY_SIZE; i++){
				targets = (offArmy[i] != null) ? offArmy[i].selectActionTargets(getAliveNpc(defArmy)) : new int[0];
				if (targets.length > 0) seq++;
				for (int j=0; j < targets.length; j++){
					int target = targets[j];
					if (defArmy[target].getNpc().getHealth() > 0){
						ActionVo action = offArmy[i].attackAction(defArmy[target]);
						action.setSeq(seq);
						action.setDoee(action.getDoee() + Constant.CON_ARMY_SIZE);
						action.setDoerHP(offArmy[i].getNpc().getHealth());
						action.setDoeeHP(defArmy[target].getNpc().getHealth());
						
						replay.add(action);
					}
				}
								
				targets = (defArmy[i] != null) ? defArmy[i].selectActionTargets(getAliveNpc(offArmy)) : new int[0];
				if (targets.length > 0) seq++;
				for (int j=0; j < targets.length; j++){
					int target = targets[j];
					if (offArmy[target].getNpc().getHealth() > 0){
						ActionVo action = defArmy[i].attackAction(offArmy[target]);
						action.setSeq(seq);
						action.setDoer(action.getDoer() + Constant.CON_ARMY_SIZE);
						action.setDoerHP(defArmy[i].getNpc().getHealth());
						action.setDoeeHP(offArmy[target].getNpc().getHealth());
						
						replay.add(action);
					}
				}
			}
		}
		
		
		return replay;
	}
	
	private void preWar(NpcBattleVo[] army)
	{
		
	}
	
	private List<RewardVo> postWar()
	{
		List<RewardVo> rl = null;
		return rl;
	}
		
	private boolean isSurvived(NpcBattleVo[] army)
	{
		boolean bool = true;
		for (int i = 0; i < Constant.CON_ARMY_SIZE; i++)
			if (army[i] != null) bool = bool && (army[i].getNpc().getHealth() > 0) ;
		
		return bool;
	}
	
	private NpcBattleVo[] initBattleArmy(List<NpcInfoVo> army)
	{
		NpcBattleVo[] result = new NpcBattleVo[Constant.CON_ARMY_SIZE];
		for (NpcInfoVo npc:army){
			if (npc.getPosition() < Constant.CON_ARMY_SIZE){
				result[npc.getPosition()] = new NpcBattleVo();
				result[npc.getPosition()].setNpc(npc);
			}
		}
		
		return result;
	}

	private int[] getAliveNpc(NpcBattleVo[] army)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < Constant.CON_ARMY_SIZE; i++)
			if ((army[i] != null) && (army[i].getNpc().getHealth() > 0)) list.add(army[i].getNpc().getPosition());

		int[] ret = new int[list.size()];
		for (int j = 0; j < list.size(); j++)
			ret[j] = list.get(j);
		return ret;
	}
}
