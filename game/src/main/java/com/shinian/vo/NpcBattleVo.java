package com.shinian.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Constant;
import com.shinian.util.RandomUtil;
 

public class NpcBattleVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	private NpcInfoVo npc;
	private int nuqi;
	private int status;
	private boolean hasRelive = false;		//是否复活过
	private boolean hasWudi = false;		//是否无敌过

	public NpcInfoVo getNpc() {
		return npc;
	}
	public void setNpc(NpcInfoVo npc) {
		this.npc = npc;
	}	
	
	public int getNuqi() {
		return nuqi;
	}
	public void setNuqi(int nuqi) {
		this.nuqi = nuqi;
	}	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}	
	
	public ActionVo attackAction(NpcBattleVo doee){
		ActionVo action = new ActionVo();

		action.setDoer(npc.getPosition());
		action.setDoee(doee.getNpc().getPosition());

		if (status == Constant.CON_NPC_BATTLE_STATUS_DAZZLE){
			action.setAct(Constant.CON_NPC_BATTLE_ACT_NOTHING);
			action.setImpact(0);
			action.setReflection(0);
			
			return action;
		}
		
		//	To Sun: add real fight here
		
		//攻击力、生命值、怒气值、暴击、命中...
		//1、计算是否命中
		Random random = new Random();
		float hv = random.nextInt(3) * (npc.getMingzhong()+10)/(npc.getMingzhong()+10+doee.getNpc().getShanbi());
		Float.compare(hv,0.0f);
		if( Float.compare(hv,1.0f)<0 ){
			//出现闪避
			hv = 0;
			action.setImpact(1);
			action.setReflection(0);
			return action;
		}
		else if( Float.compare(hv,1.0f)>=0 && Float.compare(hv,2.0f)<0 ){
			//命中
			hv = 1;
		}
		else if( Float.compare(hv,2.0f)>=0 && npc.getMingzhong()>100 ){
			//产生了额外伤害
			hv = 1 + (npc.getMingzhong()+10)/(npc.getMingzhong()+10+doee.getNpc().getShanbi());
		}
		if( doee.getNuqi()+50<=100 ){
			doee.setNuqi(doee.getNuqi()+50);
		}
		else{
			doee.setNuqi(100);
		}
		
		//2、计算基本伤害
		//生成9-11的随机数
		int dc = random.nextInt(3) % (20-10+1) + 10;
		int d01 = npc.getAttack() * dc / 10;
		
		//3、计算破甲造成的伤害。(xap)暂时取2,最大护甲假设为300。
		int d02 = d01 * (npc.getPojia()*2-doee.getNpc().getHujia()) / (300+doee.getNpc().getHujia());
		
		//4、普通附加伤害
		
		//根据武将技能判断是物攻型还是法攻型,要查数据库
		RedisCacheUtil redisCacheUtil = new RedisCacheUtil();
		NpcInfoRedisVo nivo = redisCacheUtil.getNpcInfoByComId(npc.getId());
		JinengRedisVo jnvo = redisCacheUtil.getJinengInfoById(nivo.getSkill1());
		int damageType = jnvo.getDamage_type();
		int maxFachuan = 100;
		int d03 = 0;
		if( nuqi == 100 ){
			//物攻
			if( damageType==1 ){
				d03 = ( d01 + d02 ) * 115 / 100;
			}
			//法攻
			else if(damageType==2){
				d03 = ( d01 + d01*(npc.getFachuan()-doee.getNpc().getFakang())/maxFachuan ) * 115 / 100;
			}
		}
		
		//5、暴击造成的伤害
		BigInteger d04 = new BigInteger("0");
		int rand  = 0;
		float bv = 0;	//暴击概率
		if( nuqi<100 ){
			rand = random.nextInt(3);
		}
		else{
			rand = random.nextInt(4);
		}
		bv = rand * (npc.getBaoji()+10)/(npc.getBaoji()+10+doee.getNpc().getRenxing());
		if( Float.compare(bv,1.0f)<0 ){
			bv = 0;
		}
		else if( Float.compare(bv,1.0f)>=0 && Float.compare(bv,2.0f)<0 ){
			bv = 1;
		}
		else if( Float.compare(bv,2.0f)>=0 && npc.getBaoji()>100 ){
			bv = (Float.compare(bv,3.0f)<0)?bv:3;
		}
		
		d04 = BigInteger.valueOf( (long)(( d01 + d02 + d03 )*bv) );
		
		//6、最终伤害
		long fd = 0;
		fd = (long)(( d01 + d02 + d03 )*bv*hv) +(long)( d01 + d02 + d03);
		
		//武将死亡事件发生
		if( fd >= doee.getNpc().getHealth() ){
			// 根据技能9，判断是否无敌
			if( npc.isSkill9() ){
				hasWudi = true;
				return action;				
			}
			//判断被攻击目标是否复活，技能7
			if( npc.isSkill7() ){
				hasRelive = true;
				return action;				
			}
		}
		
		
		//7、是否被晕
		//已经处于被晕状态，则抗晕加10
		if( doee.getStatus()==Constant.CON_NPC_BATTLE_STATUS_DAZZLE ){
			doee.getNpc().setKangyun(doee.getNpc().getKangyun()+10);
		}

		rand = random.nextInt(100);
		if( rand <= npc.getJiyun()-doee.getNpc().getKangyun() ){
			doee.setStatus( Constant.CON_NPC_BATTLE_STATUS_DAZZLE );
		}
		
		//8、吸血值
		//先设定吸血概率为50%,吸血能力0-100
		long sbh = 0;
		if( random.nextInt(100)<=50 ){
			//sbh = (fd.multiply(BigInteger.valueOf(npc.getXixue())).divide(BigInteger.valueOf(100))).longValue();
			sbh = fd * npc.getXixue() / 100;
		}
		action.setXixue(sbh);
		
		//9、反弹值
		//long rbd = (fd.multiply(BigInteger.valueOf(npc.getXixue())).divide(BigInteger.valueOf(100))).longValue();
		long rbd = fd * npc.getXixue() / 100;
		rbd = rbd<doee.getNpc().getHealth() ? rbd: doee.getNpc().getHealth();
		action.setReflection(rbd);
		
		//如果消耗nuqi，技能10
		if(nuqi==100){
			if( npc.isSkill10() ){
				nuqi = redisCacheUtil.getJinengInfoById(nivo.getSkill10()).getRemain_nuqi();
			}
			else{
				nuqi = 0;
			}
		}	

		
		
		
		return action;
	}
	
//	public int[] selectActionTargets(int[] target)
//	{
//		int[] ret = {1};
//		return ret;
////		return selectActionTargets(target, this.getNpc().getPosition());
//	}


	//必须知道攻击者的位置，因为攻击时要攻击前排对应自己位置的武将
	public int[] selectActionTargets(int[] target )
	{
		int attackerPos = npc.getPosition();
		if (npc.getHealth() <= 0 || attackerPos>=6 || attackerPos<0 ){
			return new int[0];
		}
		
		//  列阵中一共6个位置，分别是0,1,2,3,4,5，位置描述和位置图在“策划设计”文档中有说明
		int[] myTarget = {-1,-1,-1,-1,-1,-1};
		for(int i:target){
			if( i>=0 && i<=6 ){
				myTarget[i] = i;
			}
		}
		int[] n={};
		
		//即使攻击者在后排，也只优先攻击对手前排武将
		if( attackerPos>=3 ){
			attackerPos -= 3;
		}		
		
		RedisCacheUtil redisCacheUtil = new RedisCacheUtil();
		NpcInfoRedisVo nivo = redisCacheUtil.getNpcInfoByComId(npc.getId());
		JinengRedisVo jnvo = new JinengRedisVo();
		//JinengRedisVo jnvo = redisCacheUtil.getJinengInfoById(nivo.);

		//根据技能2判断
		if( nuqi==100 )
		{
			jnvo = redisCacheUtil.getJinengInfoById(nivo.getSkill2());			
		}
		//怒气值不满，根据技能1判断
		else{
			jnvo = redisCacheUtil.getJinengInfoById(nivo.getSkill1());	
		}

		n= getAttackPos( target,  attackerPos, jnvo.getAttack_num(), jnvo.getMubiao_pos());			
		
		return n;
	}
	
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
		int[] n ={};
		int temp = 0;
		int temp2 = 0;
		int[] rand;
		boolean isRightRand = true;
		
		switch(aimPos){
		case 1:
			if( myTarget[attackerPos] >=0 ){
				n[0] = myTarget[attackerPos];
			}
			else{
				for( int i=0; i<6; i++ ){
					if( myTarget[i] >= 0 ){
						n[0] = i;
						break;
					}
				}
			}
			break;
		//随机选取attackNum个目标
		case 2:
			rand = RandomUtil.random(0, 5, attackNum );
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
				rand = RandomUtil.random(0, 2, attackNum);
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
			break;
		case 6:
		default:
			n=target;
			break;
		}
		
		
		return n;
	}

}
