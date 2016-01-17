package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.util.Message;
import com.shinian.util.RequestParamUtil;
import com.shinian.util.ResponseParamUtil;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;

@Service
public class DispatcherService {
	@Autowired
	LoginService loginService;
	
	@Autowired
	SignupService signupService;
	
	@Autowired
	NpcInfoService npcInfoService;
	
	@Autowired
	ArmyInfoService armyInfoService;
	
	@Autowired
	PropInfoService propInfoService;
	
	@Autowired
	NpcUpdateService npcUpdateService;
	
	@Autowired
	NpcAddService npcAddService;
	
	@Autowired
	BattleService battleService;

	@Autowired
	NpcJinjieService npcJinjieService;
	
	@Autowired
	SyncNatureService syncNatureService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	ArmoryService armoryService;
	
	@Autowired
	PassService passService; 
	
	@Autowired
	JingjiService jingjiService; 
	
	@Autowired
	BuyStrengthService buyStrengthServic;
	
	@Autowired
	NpcFenjieHechengService npcFenjieHechengService;

	@Autowired
	PlayerInfoService playerInfoService;
	
	@Autowired
	PlayerNewsService playerNewsService;

	@Autowired
	ArmoryUpgradeService armoryUpgradeService;
	
	@Autowired
	SyncTimeService syncTimeService;

	private static Logger log = Logger.getLogger("game");
	
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response){
		String jsonStr = "";
		String jsonResp = "";
		MessageRespVo result = new MessageRespVo();
		
		try {
			//log client request
			jsonStr = RequestParamUtil.getRequestContents(request,response);
			log.info("client request:" + jsonStr);
			
			int acid = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("acid"),"0"));
			String ver = StringUtils.defaultIfEmpty(request.getParameter("ver"), "");
			log.info("request acid = " + acid + ",ver=" + ver);
			
			switch (acid) {
			case 1001:
				result = loginService.login(request, response, jsonStr);///登录
				break;
			
			case 1002:
				result = signupService.signup(request, response, jsonStr);///Create game player
				break;
			
			case 1003:
				result = npcInfoService.getNpcInfo(request, response, jsonStr);///get game NPC info
				break;
	
			case 1004:
				result = armyInfoService.getArmy(request, response, jsonStr);///get Player's army info
				break;
				
			case 1005:
				result = npcUpdateService.npcUpdate(request, response, jsonStr);///update npc's level
				break;
				
			case 1006:
				result = npcInfoService.setNpcPosition(request, response, jsonStr);///set npc's position
				break;

			case 1007:
				result = npcJinjieService.npcJinjie(request, response, jsonStr);/// npc jinjie
				break;
				
			case 1008:
				result = npcAddService.addNpcToPlayer(request, response, jsonStr);///
				break;
				
			case 1010:
				result = battleService.makeWar(request, response, jsonStr);///
				break;
				
			case 1011:
				result = passService.pass(request, response, jsonStr);		/// 关卡
				break;
				
			case 1012:
				result = armoryService.addArmoryToPlayer(request, response, jsonStr);///	add armory to Player, not loaded to any npc
				break;
				
			case 1013:
				result = jingjiService.JingjiBatttle(request, response, jsonStr);		///	竞技场战斗
				break;
				
			case 1014:
				result = jingjiService.EnterJingji(request, response, jsonStr);			///	初次进入竞技场
				break;
				
			case 1015:
				result = buyStrengthServic.BuyStrength(request, response, jsonStr);		///	购买体力
				break;			

			case 1016:
				result = playerInfoService.getPlayerStrength(request, response, jsonStr);		///	获取玩家体力
				break;
				
			case 1017:
				result = npcFenjieHechengService.npcHecheng(request, response, jsonStr);		///	武将合成
				break;
				
			case 1018:
				result = playerNewsService.getPlayerNewsCount(request, response, jsonStr);		///	get player's news count
				break;
			
			case 1019:
				result = npcFenjieHechengService.npcFenjie(request, response, jsonStr);		///	武将分解
				break;
				
			case 1020:
				result = playerNewsService.getPlayerNewsList(request, response, jsonStr);		///	get player's news list
				break;
				
			case 1022:
				result = playerNewsService.getNewsAward(request, response, jsonStr);		///	get award from a specific news
				break;
				
			case 1024:
				result = armoryService.loadArmoryToNpc(request, response, jsonStr);///	loaded to any npc
				break;
				
			case 1026:
				result = armoryService.getArmoryListByCategory(request, response, jsonStr);///	get armory list by category: 0-鎵€鏈 1-姝﹀櫒 2-瑁呯敳 3-椹¬鍖
				break;
				
			case 1028:
				result = armoryUpgradeService.jinjie(request, response, jsonStr);///	armory jinjie
				break;
				
			case 1030:
				result = armoryUpgradeService.levelup(request, response, jsonStr);///	armory levelup
				break;

			case 1032:
				result = armoryUpgradeService.levelupMax(request, response, jsonStr);///	armory levelup
				break;

			case 1034:
				result = syncTimeService.getCurrentTime(request, response, jsonStr);///	sync time 
				break;

			case 9002:
				result = propInfoService.getCommPropInfo(request, response, jsonStr);	///get common property info
				break;
				
			case 9003:
				result = propInfoService.addPropertyToPlayer(request, response, jsonStr);	
				break;
				
			case 9004:
				result = propInfoService.getPropListOfPlayer(request, response, jsonStr);	
				break;

			case 9005:
				result = propInfoService.consumePropertyOfPlayer(request, response, jsonStr);	
				break;
				
			case 9006:
				result = battleService.makeWar(request, response, jsonStr);	
				break;
				
			case 9007:
				result = syncNatureService.testRefreshArmy(request, response, jsonStr);	
				break;
				
			case 9008:
				result = testService.getArmoryRedisVo(request, response, jsonStr);	
				break;
				
			default:
				CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
				result.setTs( gcrv.getTs() );
				result.setCode(Message.MSG_CODE_EXCEPTION);
				result.setMsg(Message.MSG_EXCEPTION);
				jsonResp
				= JSON.toJSONString(result);
				ResponseParamUtil.writeJsonMessage(response,jsonResp);
				return;
			}
			
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
		}
		catch(Exception e){
			e.printStackTrace();

			CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
			result.setTs( gcrv.getTs() );
			result.setCode(Message.MSG_CODE_EXCEPTION);
			result.setMsg(Message.MSG_EXCEPTION);
			jsonResp = JSON.toJSONString(result);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
		}
	
	}
}