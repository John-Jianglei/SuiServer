package com.shinian.util;

import java.io.Serializable;


public class Constant implements Serializable{
	private static final long serialVersionUID = 1L;
		
	public final static int CON_ARMY_SIZE = 6;
	public final static int CON_BATTLE_MAX_SEQUENCE = 100;
	

	
	
	//	Status of Npc in Battle, start from 100
 	public final static int CON_NPC_BATTLE_STATUS_FIGHT = 100;
 	public final static int CON_NPC_BATTLE_STATUS_DAZZLE = 101;
 	public final static int CON_NPC_BATTLE_STATUS_UNSTOPPABLE = 102;
 	
 	//	action of Npc in Battle, start from 200
 	public final static int CON_NPC_BATTLE_ACT_NOTHING = 200;
 	public final static int CON_NPC_BATTLE_ACT_HELP = 201;
 	
 	//	action of Npc in Battle
 	public final static int CON_NPC_BATTLE_ACT_MISS 		= 0x0001;
 	public final static int CON_NPC_BATTLE_ACT_BAOJI 		= 0x0002;
 	public final static int CON_NPC_BATTLE_ACT_DAZZLE 		= 0x0004;
 	public final static int CON_NPC_BATTLE_ACT_GEDANG 		= 0x0008;
 	public final static int CON_NPC_BATTLE_ACT_PHYFIGHT 	= 0x0010;
 	public final static int CON_NPC_BATTLE_ACT_MAGICFIGHT 	= 0x0020;


}
