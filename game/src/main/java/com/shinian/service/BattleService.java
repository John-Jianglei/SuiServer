package com.shinian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.NpcInfoDao;
import com.shinian.dao.PassDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Constant;
import com.shinian.util.Message;
import com.shinian.util.RandomUtil;
import com.shinian.vo.ActionVo;
import com.shinian.vo.BattleReqVo;
import com.shinian.vo.BattleReturnVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.JinengRedisVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NpcBattleVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PassZhanyiRedisVo;
import com.shinian.vo.RewardVo;

@Service
public class BattleService {
		
	@Autowired
	PlayerInfoService playerInfoService;
	
	@Autowired
	ArmyInfoService armyInfoService;

	@Autowired
	PropInfoDao propInfoDao;
	
	@Autowired
	PassDao passDao;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;

	private NpcBattleVo[] offArmy;
	private NpcBattleVo[] defArmy;
	
	public BattleReturnVo pve( String uid, int battleId ){
		
		BattleReturnVo batRtn = new BattleReturnVo();		
		PassZhanyiRedisVo pzyv = redisCacheUtil.getPassZhanyiInfoById(battleId);
		
		List<NpcInfoVo> oArmy = armyInfoService.getArmyOnBattle(uid);
		List<NpcInfoVo> dArmy = new ArrayList<NpcInfoVo>();
		NpcInfoVo[] npciv = new NpcInfoVo[6];
		NpcInfoRedisVo[] defv = new NpcInfoRedisVo[6];
		
		//得到防御方阵容
		for( int i=0; i<6; i++ ){
			switch(i){
			case 0:
				defv[i] = redisCacheUtil.getNpcInfoByComId(pzyv.getComId0());
				if( defv[i]==null ){
					break;
				}
				npciv[i] = defv[i].initGameNpc();
				npciv[i].setPosition(i);
				npciv[i].setAttack( pzyv.getAttackTimes() * defv[i].getAttack());
				npciv[i].setHealth( pzyv.getAttackTimes() * defv[i].getHealth());
				dArmy.add(npciv[i]);
				break;
			case 1:
				defv[i] = redisCacheUtil.getNpcInfoByComId(pzyv.getComId1());
				if( defv[i]==null ){
					break;
				}
				npciv[i] = defv[i].initGameNpc();
				npciv[i].setPosition(i);
				npciv[i].setAttack( pzyv.getAttackTimes() * defv[i].getAttack());
				npciv[i].setHealth( pzyv.getAttackTimes() * defv[i].getHealth());
				dArmy.add(npciv[i]);
				break;
			case 2:
				defv[i] = redisCacheUtil.getNpcInfoByComId(pzyv.getComId2());
				if( defv[i]==null ){
					break;
				}
				npciv[i] = defv[i].initGameNpc();
				npciv[i].setPosition(i);
				npciv[i].setAttack( pzyv.getAttackTimes() * defv[i].getAttack());
				npciv[i].setHealth( pzyv.getAttackTimes() * defv[i].getHealth());
				dArmy.add(npciv[i]);
				break;
			case 3:
				defv[i] = redisCacheUtil.getNpcInfoByComId(pzyv.getComId3());
				if( defv[i]==null ){
					break;
				}
				npciv[i] = defv[i].initGameNpc();
				npciv[i].setPosition(i);
				npciv[i].setAttack( pzyv.getAttackTimes() * defv[i].getAttack());
				npciv[i].setHealth( pzyv.getAttackTimes() * defv[i].getHealth());
				dArmy.add(npciv[i]);
				break;
			case 4:
				defv[i] = redisCacheUtil.getNpcInfoByComId(pzyv.getComId4());
				if( defv[i]==null ){
					break;
				}
				npciv[i] = defv[i].initGameNpc();
				npciv[i].setPosition(i);
				npciv[i].setAttack( pzyv.getAttackTimes() * defv[i].getAttack());
				npciv[i].setHealth( pzyv.getAttackTimes() * defv[i].getHealth());
				dArmy.add(npciv[i]);
				break;
			case 5:
				defv[i] = redisCacheUtil.getNpcInfoByComId(pzyv.getComId5());
				if( defv[i]==null ){
					break;
				}
				npciv[i] = defv[i].initGameNpc();
				npciv[i].setPosition(i);
				npciv[i].setAttack( pzyv.getAttackTimes() * defv[i].getAttack());
				npciv[i].setHealth( pzyv.getAttackTimes() * defv[i].getHealth());
				dArmy.add(npciv[i]);
				break;
			}
		}
		
		offArmy = initBattleArmy(oArmy);
		defArmy = initBattleArmy(dArmy);
		
		List<ActionVo> lav = battle(offArmy, defArmy);
//		RewardVo reward = postWar(offArmy);
		
		batRtn.setDefArmy(dArmy);
		batRtn.setActions(lav);
		batRtn.setStar(rewardStar(offArmy));
		
		return batRtn;
	}
	
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
//		RewardVo reward = postWar(offArmy);
				
		BattleReturnVo re = new BattleReturnVo();
		
		re.setDefArmy(dArmy);
		re.setActions(lav);
//		re.setStar(reward);
		re.setStar(rewardStar(offArmy));
		
		result.setData(re);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;

	}
	
	public List<ActionVo> battle(NpcBattleVo[] offArmy, NpcBattleVo[] defArmy)
	{
		if( null==offArmy || null==defArmy ){
			return null;
		}
		List<ActionVo> replay = new ArrayList<ActionVo>();
		
		int seq = 0;
		int[] targets;
		preWar(offArmy);
		preWar(defArmy);
		
		while (isSurvived(offArmy) && isSurvived(defArmy) && (seq < Constant.CON_BATTLE_MAX_SEQUENCE)){
			for (int i=0; i<Constant.CON_ARMY_SIZE; i++){
//				targets = (offArmy[i] != null) ? offArmy[i].selectActionTargets(getAliveNpc(defArmy)) : new int[0];
				targets = (offArmy[i] != null) ? selectActionTargets(offArmy[i], getAliveNpc(defArmy)) : new int[0];
				if (targets.length > 0) seq++;
				for (int j=0; j < targets.length; j++){
					int target = targets[j];
					if (defArmy[target].getNpc().getHealth() > 0){
//						ActionVo action = offArmy[i].attackAction(defArmy[target]);
						ActionVo action = getAttackAction(offArmy[i], defArmy[target]);
						action.setSeq(seq);
						action.setDoee(action.getDoee() + Constant.CON_ARMY_SIZE);
						action.setDoerHP(offArmy[i].getNpc().getHealth());
						action.setDoeeHP(defArmy[target].getNpc().getHealth());
						
						replay.add(action);
					}
				}
								
//				targets = (defArmy[i] != null) ? defArmy[i].selectActionTargets(getAliveNpc(offArmy)) : new int[0];
				targets = (defArmy[i] != null) ? selectActionTargets(defArmy[i], getAliveNpc(offArmy)) : new int[0];
				if (targets.length > 0) seq++;
				for (int j=0; j < targets.length; j++){
					int target = targets[j];
					int tempPos = offArmy[target].getNpc().getPosition();
					if (offArmy[target].getNpc().getHealth() > 0){
//						ActionVo action = defArmy[i].attackAction(offArmy[target]);
						ActionVo action = getAttackAction(defArmy[i], offArmy[target]);
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
	
	private int rewardStar(NpcBattleVo[] army)
	{
		int total = 0;
		int alive = 0;
		for (int i = 0; i < Constant.CON_ARMY_SIZE; i++){
			if (army[i] != null){
				total++;
				if (army[i].getNpc().getHealth() > 0) alive++;
			}
		}
		
		if (alive == 0) return 0;
		if (total == alive) return Constant.CON_BATTLE_MAX_REWARDSTAR;
		return ((total - alive) > 1) ? 1 : 2;
	}
	
	private RewardVo postWar(NpcBattleVo[] army)
	{
		RewardVo rw = new RewardVo();
//		rw.setStar(rewardStar(army));
		
		return rw;
	}
		
	private boolean isSurvived(NpcBattleVo[] army)
	{
		int alive = 0;
		for (int i = 0; i < Constant.CON_ARMY_SIZE; i++){
			if (army[i] != null){
				if (army[i].getNpc().getHealth() > 0) alive++;
			}
		}
		
		return (alive > 0) ? true : false;
	}
	
	public NpcBattleVo[] initBattleArmy(List<NpcInfoVo> army)
	{
		NpcBattleVo[] result = new NpcBattleVo[Constant.CON_ARMY_SIZE];
		for (NpcInfoVo npc:army){
			if (npc.getPosition() < Constant.CON_ARMY_SIZE){
				result[npc.getPosition()] = new NpcBattleVo();
				result[npc.getPosition()].setNpc(npc);
				result[npc.getPosition()].setStartHealth(npc.getHealth());
				if( npc.hasSkill4() ){
					result[npc.getPosition()].setNuqi(redisCacheUtil.getJinengInfoById(npc.getComId()).getInit_nuqi());
				}
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
	
	private ActionVo getAttackAction(NpcBattleVo offNpc, NpcBattleVo defNpc)
	{
		//	To Sun: replace NpcBattleVo.attackAction()
		ActionVo action = new ActionVo();

		action.setDoer(offNpc.getNpc().getPosition());
		action.setDoerHP(offNpc.getNpc().getHealth());
		action.setDoerNuqi(offNpc.getNuqi());
		action.setDoee(defNpc.getNpc().getPosition());
		action.setDoeeHP(defNpc.getNpc().getHealth());
		action.setDoeeNuqi(defNpc.getNuqi());
		
		NpcInfoDao npcinfodao = new NpcInfoDao();
		NpcInfoVo npcvo = npcinfodao.getNpcInfoById(offNpc.getNpc().getId());

		if (offNpc.getStatus() == Constant.CON_NPC_BATTLE_STATUS_DAZZLE){
//			action.setAct(Constant.CON_NPC_BATTLE_ACT_NOTHING);
			action.setImpact(0);
			action.setReflection(0);
			
			return action;
		}
		
		//	To Sun: add real fight here
		
		//攻击力、生命值、怒气值、暴击、命中...
		//1、计算是否命中
		Random random = new Random();
		int r = random.nextInt(11);
		float hv = (float)(r+1)/3 * (offNpc.getNpc().getMingzhong()+10)/(10+defNpc.getNpc().getShanbi());
		Float.compare(hv,0.0f);
		if( Float.compare(hv,1.0f)<0 ){
			//出现闪避
			hv = 0;
			action.setImpact(0);
			action.setReflection(0);
			if( (0x0001 & action.getAct()) ==0 ){
				action.setAct(action.getAct()+0x0001);
			}
			return action;
		}
		else if( Float.compare(hv,1.0f)>=0 && Float.compare(hv,2.0f)<0 ){
			//命中
			hv = 1;
		}
		else if( Float.compare(hv,2.0f)>=0 && offNpc.getNpc().getMingzhong()>100 ){
			//产生了额外伤害
			hv = 1 + (offNpc.getNpc().getMingzhong()+10)/(offNpc.getNpc().getMingzhong()+10+defNpc.getNpc().getShanbi());
		}
		else{
			hv = 1;
		}
		
		//2、计算基本伤害
		//生成9-11的随机数
		int dc = random.nextInt(3) % (20-10+1) + 10;
		int d01 = offNpc.getNpc().getAttack() * dc / 10;
		
		//3、计算破甲造成的伤害。(xap)暂时取2,最大护甲假设为300。
		int d02 = d01 * (offNpc.getNpc().getPojia()*2-defNpc.getNpc().getHujia()) / (300+defNpc.getNpc().getHujia());
		
		//4、普通附加伤害
		
		//根据武将技能判断是物攻型还是法攻型,要查数据库
		NpcInfoRedisVo nivo = redisCacheUtil.getNpcInfoByComId(defNpc.getNpc().getComId());
		JinengRedisVo jnvo = redisCacheUtil.getJinengInfoById(nivo.getSkill1());
		int damageType = jnvo.getDamage_type();
		int maxFachuan = 100;
		int d03 = 0;
		//怒气满才能发动必杀技
		if( offNpc.getNuqi() == 100 ){
			//物攻
			if( damageType==1 ){
				d03 = ( d01 + d02 ) * 115 / 100;
			}
			//法攻
			else if(damageType==2){
				d03 = ( d01 + d01*(offNpc.getNpc().getFachuan()-defNpc.getNpc().getFakang())/maxFachuan ) * 115 / 100;
			}
		}
		
		//5、暴击造成的伤害
		long d04 = 0;
		int rand  = 0;
		float bv = 0;	//暴击概率
		if( offNpc.getNuqi()<100 ){
			rand = random.nextInt(2);
		}
		else{
			rand = random.nextInt(3);
		}
		bv = rand * (offNpc.getNpc().getBaoji()+10)/(10+defNpc.getNpc().getRenxing());
		if( Float.compare(bv,1.0f)<0 ){
			bv = 0;
		}
		else if( Float.compare(bv,1.0f)>=0 && Float.compare(bv,2.0f)<0 ){
			bv = 1;
		}
		else if( Float.compare(bv,2.0f)>=0 && offNpc.getNpc().getBaoji()>100 ){
			bv = (Float.compare(bv,3.0f)<0)?bv:3;
		}
		else{
			bv = 1;
		}
		
		d04 = (long)(( d01 + d02 + d03 )*bv);
		
		if(Float.compare(bv,1.0f)>=0){
			if( (0x0002 & action.getAct()) ==0 ){
				action.setAct(action.getAct()+0x0002);
			}
		}
		
		//6、最终伤害
		long fd = 0;
		fd = (long)(( d01 + d02 + d03 )*bv*hv) +(long)( d01 + d02 + d03);
		
		//武将死亡事件发生
		if( fd >= defNpc.getNpc().getHealth() ){
			// 根据技能9，判断是否无敌
			if( offNpc.getNpc().hasSkill9() && offNpc.isHasWudi()==false ){				
				offNpc.setHasWudi(true);
				return action;				
			}
			//判断被攻击目标是否复活，技能7
			if( offNpc.getNpc().hasSkill7() && offNpc.isHasRelive()==false ){			
				//恢复生命和怒气值	
				offNpc.getNpc().setHealth(npcvo.getHealth());
				offNpc.setNuqi(100);
				
				offNpc.setHasRelive(true);
				return action;				
			}
		}
		//武将受到攻击，怒气值上升
		if( defNpc.getNuqi()+50<=100 ){
			defNpc.setNuqi(defNpc.getNuqi()+50);
			action.setDoeeNuqi(defNpc.getNuqi());
		}
		else{
			defNpc.setNuqi(100);
			action.setDoeeNuqi(100);
		}
		action.setImpact(fd);
		
		if( fd >= defNpc.getNpc().getHealth() ){
			defNpc.getNpc().setHealth(0);
			action.setDoeeHP(0);
		}
		else{
			defNpc.getNpc().setHealth( (int)(defNpc.getNpc().getHealth()-fd) );
			action.setDoeeHP(defNpc.getNpc().getHealth());
		}
		
		//7、是否被晕
		//已经处于被晕状态，则抗晕加10
		if( defNpc.getStatus()!= Constant.CON_NPC_BATTLE_STATUS_DAZZLE ){
			if( defNpc.getlastStatus()==Constant.CON_NPC_BATTLE_STATUS_DAZZLE ){
				defNpc.getNpc().setKangyun(defNpc.getNpc().getKangyun()+10);
			}

			rand = random.nextInt(100);
			if( rand <= offNpc.getNpc().getJiyun()-defNpc.getNpc().getKangyun() ){
				defNpc.setStatus( Constant.CON_NPC_BATTLE_STATUS_DAZZLE );
				if( (0x0004 & action.getAct()) ==0 ){
					action.setAct(action.getAct()+0x0004);
				}
			}
		}
		
		//8、吸血值
		//先设定吸血概率为50%,吸血能力0-100
		long sbh = 0;
		if( random.nextInt(100)<=50 ){
			//sbh = (fd.multiply(BigInteger.valueOf(npc.getXixue())).divide(BigInteger.valueOf(100))).longValue();
			sbh = fd * offNpc.getNpc().getXixue() / 100;
		}
		action.setXixue(sbh);
		long tempHealth = sbh + offNpc.getNpc().getHealth();
		//武将最大生命是
		tempHealth = tempHealth>offNpc.getStartHealth()?offNpc.getStartHealth():tempHealth;
		offNpc.getNpc().setHealth( (int)tempHealth );
		action.setDoerHP(tempHealth);
		
		//9、反弹值
		//long rbd = (fd.multiply(BigInteger.valueOf(npc.getXixue())).divide(BigInteger.valueOf(100))).longValue();
		long rbd = fd * offNpc.getNpc().getFantan() / 100;
		rbd = rbd<defNpc.getNpc().getHealth() ? rbd: defNpc.getNpc().getHealth();
		action.setReflection(rbd);
		tempHealth = (int)rbd>offNpc.getNpc().getHealth()? 0 : (offNpc.getNpc().getHealth()-(int)rbd);
		offNpc.getNpc().setHealth( (int)tempHealth );
		action.setDoerHP(tempHealth);
		
		//如果消耗nuqi，剩余怒气看技能10
		if(offNpc.getNuqi()==100){
			if( offNpc.getNpc().hasSkill10() ){
				offNpc.setNuqi(redisCacheUtil.getJinengInfoById(nivo.getSkill10()).getRemain_nuqi());
				action.setDoerNuqi(offNpc.getNuqi());
			}
			else{
				offNpc.setNuqi(0);
				action.setDoerNuqi(0);
			}
		}		
		
		return action;
	}
	
	private int[] selectActionTargets(NpcBattleVo offNpc, int[] targets)
	{
		//	To Sun: replace NpcBattleVo.selectActionTargets()
		int attackerPos = offNpc.getNpc().getPosition();
		if (offNpc.getNpc().getHealth() <= 0 || attackerPos>=6 || attackerPos<0 ){
			return new int[0];
		}
		
		//  列阵中一共6个位置，分别是0,1,2,3,4,5，位置描述和位置图在“策划设计”文档中有说明
		int[] myTarget = {-1,-1,-1,-1,-1,-1};
		for(int i:targets){
			if( i>=0 && i<=6 ){
				myTarget[i] = i;
			}
		}
		int[] n={};
		
		//即使攻击者在后排，也只优先攻击对手前排武将
		if( attackerPos>=3 ){
			attackerPos -= 3;
		}		

		NpcInfoRedisVo nivo = redisCacheUtil.getNpcInfoByComId(offNpc.getNpc().getComId());
		JinengRedisVo jnvo = new JinengRedisVo();
		//JinengRedisVo jnvo = redisCacheUtil.getJinengInfoById(nivo.);

		//根据技能2判断
		if( offNpc.getNuqi()==100 )
		{
			jnvo = redisCacheUtil.getJinengInfoById(nivo.getSkill2());			
		}
		//怒气值不满，根据技能1判断
		else{
			jnvo = redisCacheUtil.getJinengInfoById(nivo.getSkill1());	
		}

		n= getAttackPos( targets,  attackerPos, jnvo.getAttack_num(), jnvo.getMubiao_pos());			
		
		return n;

	}
	
	//aimPos:攻击目标位置：1默认（指单个目标，前排与攻击者对应位置）,
	//2随机目标，3前排随机目标，4后排随机目标，5随机纵列目标，6全体目标
	private int[] getAttackPos(int[] target, int attackerPos, int attackNum, int aimPos){
		
		if(target.length==0){
			return new int[0];
		}
		
		int[] myTarget = {-1,-1,-1,-1,-1,-1};
		for(int i:target){
			if( i>=0 && i<=6 ){
				myTarget[i] = i;
			}
		}		
		int[] n = new int[6];
		int[] ret = {};
		int temp = 0;
		int temp2 = 0;
		int[] rand;
		boolean isRightRand = true;
		
		switch(aimPos){
		case 1:
			ret = new int[1];
			if( myTarget[attackerPos] >=0 ){
				ret[0] = myTarget[attackerPos];
			}
			else{
				for( int i=0; i<6; i++ ){
					if( myTarget[i] >= 0 ){
						ret[0] = i;
						break;
					}
				}
			}
			break;
		//随机选取attackNum个目标
		case 2:
			if( attackNum >= 6 || target.length<=attackNum ){
				return target;
			}
			else{
				rand = RandomUtil.random(0, 5, attackNum );
			}
			//如果随机到阵亡武将，则重新随机
			while(true){
				isRightRand = true;
				for(int j:rand){
					if( myTarget[j] < 0 ){
						isRightRand = false;
						break;
					}
				}
				if(isRightRand){
					for(int j:rand){
						n[temp] = myTarget[j];
						temp++;
					}
					break;
				}
				ret = new int[temp];
				for(int i:ret){
					ret[i] = n[i];
				}
				rand = RandomUtil.random(0, 5, attackNum);
			}			
			break;
		//随机选取前排attackNum个目标
		case 3:
			temp = 0;
			for( int i=0; i<3; i++ ){
				if( myTarget[i] >= 0 ){
					temp++;
				}
			}
			//前排全部阵亡，则攻击后排
			if(temp==0){
				return getAttackPos(target, attackerPos, attackNum, 4);
			}
			//前排目标少于或等于能够攻击的目标
			else if( temp <= attackNum ){
				for( int i=0; i<3; i++ ){
					if( myTarget[i] >= 0 ){
						n[temp2] = myTarget[i];
						temp2++;
					}							
				}						
					
			}
			//前排目标多于能够攻击的目标
			else{
				rand = RandomUtil.random(0, 2, attackNum);
				//如果随机到阵亡武将，则重新随机
				while(true){
					isRightRand = true;
					for(int j:rand){
						if( myTarget[j] < 0 ){
							isRightRand = false;
							break;
						}
					}
					if(isRightRand){
						for(int j:rand){
							n[temp2] = myTarget[j];
							temp2++;
						}
						break;
					}
					rand = RandomUtil.random(0, 2, attackNum);
				}					
			}
			ret = new int[temp2];
			for(int i:ret){
				ret[i] = n[i];
			}
			break;
		//随机选取后排attackNum个目标
		case 4:
			temp = 0;
			for( int i=3; i<5; i++ ){
				if( myTarget[i] >= 0 ){
					temp++;
				}
			}
			//后排全部阵亡，则攻击前排
			if(temp==0){
				return getAttackPos(target, attackerPos, attackNum, 3);
			}
			//后排目标少于或等于能够攻击的目标
			else if( temp <= attackNum ){
				for( int i=3; i<5; i++ ){
					if( myTarget[i] >= 0 ){
						n[temp2] = myTarget[i];
						temp2++;
					}							
				}						
					
			}
			//后排目标多于能够攻击的目标
			else{
				rand = RandomUtil.random(3, 5, attackNum);
				//如果随机到阵亡武将，则重新随机
				while(true){
					isRightRand = true;
					for(int j:rand){
						if( myTarget[j] < 0 ){
							isRightRand = false;
							break;
						}
					}
					if(isRightRand){
						for(int j:rand){
							n[temp2] = myTarget[j];
							temp2++;
						}
						break;
					}
					rand = RandomUtil.random(3, 5, attackNum);
				}					
			}
			ret = new int[temp2];
			for(int i:ret){
				ret[i] = n[i];
			}
			break;
		case 5:
			Random r = new Random();
			temp = r.nextInt(2);
			//如果选到的某列全部阵亡，则不随机了，从剩下了两列中选一列
			if( myTarget[temp]<0 && myTarget[temp+3]<0 ){
				for(int i=0; i<3; i++){
					if( myTarget[i]>=0 || myTarget[i]>=0 ){
						temp = i;
						break;
					}
				}
			}

			if( myTarget[temp]>=0 ){
				n[temp2] = myTarget[temp];
				temp2++;
			}
			if( myTarget[temp+3]>=0 ){
				n[temp2] = myTarget[temp];
			}
			ret = new int[temp2];
			for(int i:ret){
				ret[i] = n[i];
			}
			break;
		case 6:
		default:
			ret=target;
			break;
		}
		
		
		return ret;
	}
}
