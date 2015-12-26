package com.shinian.util;

import java.io.Serializable;


public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
		
	public final static int MSG_CODE_OK = 200;
	
	public final static int MSG_CODE_EXCEPTION = 500;
	public final static String MSG_EXCEPTION = "服务器异常";
	
	public final static int MSG_CODE_ACCERROR = 301;
	public final static String MSG_ACCERROR = "账号错误";
		
	public final static int MSG_CODE_PLAYER_NOT_EXIST = 302;
	public final static String MSG_PLAYER_NOT_EXIST = "角色不存在";
	
	public final static int MSG_CODE_PLAYERID_IS_NULL = 303;
	public final static String MSG_PLAYERID_IS_NULL = "用户ID为空";

	public final static int MSG_CODE_PLAYERNAME_INAPPROPRIATE = 304;
	public final static String MSG_PLAYERNAME_INAPPROPRIATE = "角色名非法";
	
	public final static int MSG_CODE_PLAYER_IDNAME_DUPLICATIVE = 305;
	public final static String MSG_PLAYER_IDNAME_DUPLICATIVE = "角色名或角色ID重复";
	
	public final static int MSG_CODE_CREATEPLAYER_FAIL = 306;
	public final static String MSG_CREATEPLAYER_FAIL = "创建角色失败";
	
	public final static int MSG_CODE_NPC_NOT_EXIST = 307;
	public final static String MSG_NPC_NOT_EXIST = "武将不存在";
		
	public final static int MSG_CODE_PROP_NOT_EXIST = 308;
	public final static String MSG_PROP_NOT_EXIST = "物品不存在";
	
	public final static int MSG_CODE_NPC_CANNOT_LEVEL = 309;
	public final static String MSG_NPC_CANNOT_LEVEL = "武将不能升级";
		
	public final static int MSG_CODE_PROP_NOT_ENOUGH = 310;
	public final static String MSG_PROP_NOT_ENOUGH = "物品数量不足";
	
	public final static int MSG_CODE_PROP_EMPTY = 311;
	public final static String MSG_PROP_EMPTY = "物品用完";

	public final static int MSG_CODE_SEL_NPC_ERROR = 312;
	public final static String MSG_SEL_NPC_ERROR = "选择武将错误";

	public final static int MSG_CODE_TEST_NOT_EXIST = 313;
	public final static String MSG_TEST_NOT_EXIST = "测试对象不存在";
	
	public final static int MSG_CODE_ARMORY_NOT_EXIST = 314;
	public final static String MSG_ARMORY_NOT_EXIST = "装备不存在";
	
	public final static int MSG_CODE_SWEEP_ZERO = 315;
	public final static String MSG_SWEEP_ZERO = "可扫荡的次数不足";

	public final static int MSG_CODE_ARMORY_NOT_MATCH_NPC = 316;
	public final static String MSG_ARMORY_NOT_MATCH_NPC = "穿越了吧";
	
	public final static int MSG_CODE_JINGJI_ERROR = 317;
	public final static String MSG_JINGJI_ERROR = "竞技排名错误";

}
