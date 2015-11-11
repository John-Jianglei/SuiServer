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
		
	
}
