package com.shinian.vo;

import java.io.Serializable;
import java.util.Random;
import java.math.*;

import com.shinian.util.Constant;
 

public class NpcBattleVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	private NpcInfoVo npc;
	private int nuqi;
	private int status;

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
		
		//2、计算基本伤害
		//生成9-11的随机数
		int dc = random.nextInt(3) % (20-10+1) + 10;
		int d01 = npc.getAttack() * dc / 10;
		
		//3、计算破甲造成的伤害。(xap)暂时取2,最大护甲假设为300。
		int d02 = d01 * (npc.getPojia()*2-doee.getNpc().getHujia()) / (300+doee.getNpc().getHujia());
		
		//4、普通附加伤害
		
		//根据武将技能1判断是物攻型还是法攻型,要查数据库
		int adc = 115;
		int maxFachuan = 100;
		int d03 = 0;
		if( nuqi == 100 ){
			//物攻
			d03 = ( d01 + d02 ) * 115 / 100;
			//法攻
			d03 = ( d01 + d01*(npc.getFachuan()-doee.getNpc().getFakang())/maxFachuan ) * 115 / 100;
			//怒气减100
			//nuqi = 0;
		}
		
		//5、暴击造成的伤害
		int d04 = 0;
		float bv = random.nextInt(3) * (npc.getBaoji()+10)/(npc.getBaoji()+10+doee.getNpc().getRenxing());
		if( nuqi<100 ){
			
		}
		else{
			
		}
		
		
		return action;
	}


	public int[] getActionTarget()
	{
		if (npc.getHealth() <= 0) return new int[0];
		
		int[] n = {2,4};
		//	To Sun: return action target number, eg {3,4,5}

		
		return n;
	}
	
	
}
