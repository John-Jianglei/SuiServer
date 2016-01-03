package com.shinian.util;

import java.io.Serializable;


public class Constant implements Serializable{
	private static final long serialVersionUID = 1L;
		
	public final static int CON_ARMY_SIZE = 6;
	
	public final static int CON_ARMY_POSITION_NULL = 0;
	public final static int CON_ARMY_POSITION_SELF = 1;
	public final static int CON_ARMY_POSITION_FRONT = 2;
	public final static int CON_ARMY_POSITION_REAR = 3;
	public final static int CON_ARMY_POSITION_ALL = 4;
	
	public final static int CON_BATTLE_MAX_SEQUENCE = 100;
	public final static int CON_BATTLE_MAX_REWARDSTAR = 3;

	
	public final static int CON_ARMORY_WEAPON = 1;
	public final static int CON_ARMORY_ARMOR = 2;
	public final static int CON_ARMORY_HORSE = 3;
	
	public final static int ARMORY_JINJIE_MAX_GENERAL_PINJIE = 9;
	
	public final static int ARMORY_JINJIE_PROP_JINHUASTONE = 200;
	public final static int ARMORY_JINJIE_PROP_AMBER = 201;
	public final static int ARMORY_JINJIE_PROP_XUANTIE = 202;
	public final static int ARMORY_JINJIE_PROP_COPPER = 203;
	public final static int ARMORY_JINJIE_PROP_MADENG = 204;
	public final static int ARMORY_JINJIE_PROP_PIGE = 205;
	
	public final static int NEWS_MAX_RETENTION_DAY = 7;

	

	
	
	//	Status of Npc in Battle, start from 100
 	public final static int CON_NPC_BATTLE_STATUS_FIGHT = 100;
 	public final static int CON_NPC_BATTLE_STATUS_DAZZLE = 101;
 	public final static int CON_NPC_BATTLE_STATUS_UNSTOPPABLE = 102;
 	
 	//	action of Npc in Battle, start from 200
 	//public final static int CON_NPC_BATTLE_ACT_NOTHING = 200;
 	//public final static int CON_NPC_BATTLE_ACT_HELP = 201;
 	
 	//	action of Npc in Battle
 	public final static int CON_NPC_BATTLE_ACT_MISS 		= 0x0001;
 	public final static int CON_NPC_BATTLE_ACT_BAOJI 		= 0x0002;
 	public final static int CON_NPC_BATTLE_ACT_DAZZLE 		= 0x0004;
 	public final static int CON_NPC_BATTLE_ACT_GEDANG 		= 0x0008;
 	public final static int CON_NPC_BATTLE_ACT_PHYFIGHT 	= 0x0010;
 	public final static int CON_NPC_BATTLE_ACT_MAGICFIGHT 	= 0x0020;


}
