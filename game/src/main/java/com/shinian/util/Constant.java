package com.shinian.util;

import java.io.Serializable;


public class Constant implements Serializable{
	private static final long serialVersionUID = 1L;
		
	public final static int CON_ARMY_SIZE = 6;

	
	
	//	Status of Npc in Battle, start from 100
 	public final static int CON_NPC_BATTLE_STATUS_FIGHT = 100;
 	public final static int CON_NPC_BATTLE_STATUS_DAZZLE = 101;
 	public final static int CON_NPC_BATTLE_STATUS_UNSTOPPABLE = 102;
 	
 	//	action of Npc in Battle, start from 200
 	public final static int CON_NPC_BATTLE_ACT_NOTHING = 200;
 	public final static int CON_NPC_BATTLE_ACT_HELP = 201;
 	public final static int CON_NPC_BATTLE_ACT_PHYFIGHT = 202;
 	public final static int CON_NPC_BATTLE_ACT_MAGICFIGHT = 203;
 	
 	


}
