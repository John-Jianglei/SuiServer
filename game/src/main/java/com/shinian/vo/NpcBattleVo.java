package com.shinian.vo;

import java.io.Serializable;
import com.shinian.vo.ActionVo;
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
		
		return action;
	}


	public int[] getActionTarget()
	{
		int[] n = {2,4};
		//	To Sun: return action target number, eg {3,4,5}

		
		return n;
	}
	
	
}
