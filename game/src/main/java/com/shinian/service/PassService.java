package com.shinian.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.PassDao;
import com.shinian.dao.PlayerInfoDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.redis.RedisCacheUtil;
import com.shinian.util.Constant;
import com.shinian.util.Message;
import com.shinian.util.Util;
import com.shinian.vo.BattleReturnVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.DropRewardVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.PassBattleRtnVo;
import com.shinian.vo.PassLogVo;
import com.shinian.vo.PassReturnVo;
import com.shinian.vo.PassVo;
import com.shinian.vo.PassZhanyiRedisVo;
import com.shinian.vo.PlayerExpRedisVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.RewardVo;

@Service
public class PassService {

	@Autowired
	PassDao passDao;
	
	@Autowired
	PlayerInfoDao playerInfoDao;
	
	@Autowired	
	ArmyInfoService armyInfoService;
	
	@Autowired	
	BattleService battleService;
	
	@Autowired
	RedisCacheUtil redisCacheUtil;
	
	@Autowired
	PropInfoDao propInfoDao;
	
	public MessageRespVo pass(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();
		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);
		PassVo passVo = JSON.parseObject(gcrv.getData().toString(),PassVo.class);
		result.setTs(gcrv.getTs());
		
		if ( null == passVo.getUid() || "".equals(passVo.getBattle()) 
				|| "".equals(passVo.getFlag())) {
			result.setCode(Message.MSG_CODE_PLAYERID_IS_NULL);
			result.setMsg(Message.MSG_PLAYERID_IS_NULL);
			return result;
		}
		
		//扫荡
		if(passVo.getFlag()==0){
			
			PassReturnVo	prtnv = sweepN( passVo, 0 );
			if( prtnv==null ){
				result.setCode(Message.MSG_CODE_SWEEP_ZERO);
				return result;
			}
			result.setData(prtnv);
		}
		//战斗
		else{
			PassBattleRtnVo pbrtnv = new PassBattleRtnVo();
			//调用战斗函数
			BattleReturnVo btlRtnv =  battleService.pve( passVo.getUid(), passVo.getBattle() );
			pbrtnv.setBrv(btlRtnv);
			//int stars = btlRtnv.getReward().getStar();
			int stars = btlRtnv.getStar();
			//胜利
			if( stars >=1 && stars<=3 ){
		
				PassReturnVo	prv = sweepN( passVo, 1 );
				if( prv==null ){
					result.setCode(Message.MSG_CODE_SWEEP_ZERO);
					return result;
				}
				pbrtnv.setPrv(prv);
				
				//修改当前战斗星级
				PassVo pv = passDao.getPassByBattleId(passVo.getBattle(), passVo.getUid());
				if( pv==null ){
					//是否此时插入15条记录，是否需要做判断要考虑
				}
				
				if( pv.getBattleStars()<stars ){
					//修改星级，前提是要为每个武将提前插入15条记录
					passDao.updatePassByBattleId(pv.getPassStars()+stars-pv.getBattleStars(),
							stars, pv.getBattle(), pv.getUid() );
				}
				
			}
			//失败
			else if( stars==0 ){
				pbrtnv.setPrv(null);
			}
			
			result.setData(pbrtnv);
		}
		
		result.setCode(Message.MSG_CODE_OK);
	
		return result;
	}
	
	//一次扫荡掉落物品列表
	private List<DropRewardVo> sweep( int battleId, PassZhanyiRedisVo pzrv )
	{
		List<DropRewardVo> dropRewardlist = new ArrayList<DropRewardVo>();
		DropRewardVo drv;
		
		//PassZhanyiRedisVo pzrv = redisCacheUtil.getPassZhanyiInfoById(battleId);
		int[] rewardP = new int[4];
		int[] rewardSeq = { 0, 0, 0 };
		rewardP[0] = pzrv.getRewardP1();
		rewardP[1] = pzrv.getRewardP2() + rewardP[0];
		rewardP[2] = pzrv.getRewardP3() + rewardP[1];
		rewardP[3] = pzrv.getRewardP4() + rewardP[2];			
		Random rand = new Random();
		int tempRand = 0;
		int rewardNum = 0;	//reward数量
		
		//每次扫荡或战役最多掉三件奖励品
		for(int i=0; i<3; i++){
			tempRand = rand.nextInt(2*rewardP[3]);
			for( int j=0; j<4; j++ ){
				if( tempRand <= rewardP[j] ){
					//dropRewardlist.get(j).setRewardId(pzrv.getRewardId11());
					rewardNum++;
					//让物品填在前面
					if( i>0 && rewardSeq[i-1]==0 ){
						rewardSeq[i-1] = j+1;
					}
					else{
						rewardSeq[i] = j+1;
					}
					break;
				}					
			}				
		}
		
		for( int i=0; i<rewardNum; i++ ){
			switch(rewardSeq[i]){
			case 1:
				drv = new DropRewardVo();
				drv.setRewardId(pzrv.getRewardId11());
				drv.setRewardNum(pzrv.getRewardNum1());
				drv.setRewardType(pzrv.getRewardType1());
				dropRewardlist.add(drv);
				break;
			case 2:
				drv = new DropRewardVo();
				drv.setRewardId(pzrv.getRewardId12());
				drv.setRewardNum(pzrv.getRewardNum2());
				drv.setRewardType(pzrv.getRewardType2());
				dropRewardlist.add(drv);
				break;
			case 3:
				drv = new DropRewardVo();
				drv.setRewardId(pzrv.getRewardId13());
				drv.setRewardNum(pzrv.getRewardNum3());
				drv.setRewardType(pzrv.getRewardType3());
				dropRewardlist.add(drv);
				break;
			case 4:
				drv = new DropRewardVo();
				drv.setRewardId(pzrv.getRewardId14());
				drv.setRewardNum(pzrv.getRewardNum4());
				drv.setRewardType(pzrv.getRewardType4());
				dropRewardlist.add(drv);
				break;
			default:					
				break;
					
			}
		}
		return dropRewardlist;
	}
	
	//扫荡n次,
	//SweepTimes:0表示扫荡n次，要根据体力和可扫荡次数判断
	private PassReturnVo sweepN( PassVo passVo, int SweepTimes ){
		
		PassReturnVo	prtnv = new PassReturnVo();
		List<RewardVo> 	rewardList =  new ArrayList<RewardVo>();
		
		PlayerInfoVo piv = playerInfoDao.getPlayerInfoByUid(passVo.getUid());
		
		PassZhanyiRedisVo pzrv = redisCacheUtil.getPassZhanyiInfoById(passVo.getBattle());			//本次扫荡经验			
		//int	exp = pzrv.getExp();		

		String date = Util.getCurrentDate();	
		
		PassLogVo plv= passDao.getPassLog( passVo.getBattle(), passVo.getUid(), date );
		//如果没有则插入一条记录
		if( plv==null ){
			plv = new PassLogVo();
			plv.setBattleId(passVo.getBattle());
			plv.setUid(passVo.getUid());
			plv.setDate(date);
			plv.setCount(pzrv.getBattleCount());
			
			passDao.insertPassLog( plv );
		}
		
		//扫荡次数取可扫荡次数和武将体力中的小值
		if( SweepTimes==0 ){
			SweepTimes = plv.getCount()<piv.getCurrent_strength()?plv.getCount():piv.getCurrent_strength();
		}
		if( SweepTimes==0 || piv.getCurrent_strength()<=0 ){
			return null;
		}
		
		for( int i=0; i<SweepTimes; i++ ){
			RewardVo rv = new RewardVo();				
			rv.setDropRewardlist( sweep( passVo.getBattle(), pzrv));
			//rv.setStar(3);
			rewardList.add(rv);
		}
		
		//减少可扫荡次数
		passDao.updatePassLog( plv.getCount()-SweepTimes, passVo.getUid(), passVo.getBattle() );
		prtnv.setN( plv.getCount()-SweepTimes );
		
		//减少体力
		piv.setCurrent_strength(piv.getCurrent_strength()-SweepTimes);
		//增加银两
		piv.setSilver( piv.getSilver() + pzrv.getSliver()*SweepTimes );	
		//根据经验升级
		piv = playerUpdate( pzrv.getExp()*SweepTimes, piv );
		
		//写playerInfo信息进数据库
		playerInfoDao.updatePlayer(piv);	
		
		prtnv.setRewardList(rewardList);
		//还需将物品信息写进数据库
		for( RewardVo rewardVo : rewardList ){
			for( DropRewardVo dropRewardVo : rewardVo.getDropRewardlist() ){
				//待补充
			}			
		}
		
		prtnv.setExp(piv.getCurrent_exp());
		prtnv.setLevel(piv.getLevel());
		prtnv.setSliver(piv.getSilver());
		prtnv.setSte(piv.getCurrent_strength());
		prtnv.setUid(passVo.getUid());
		
		return prtnv;
	}
	
	//玩家升级
	public PlayerInfoVo playerUpdate( int exp, PlayerInfoVo piv ){
		
		int currentExp = piv.getCurrent_exp();
		int level = piv.getLevel();
		PlayerExpRedisVo perv = redisCacheUtil.getPlayerExpByLevel(level);
		int levelExp = perv.getExp();
		
		while(true){
			//不够升级
			if( exp < levelExp-currentExp ){
				currentExp +=  exp;				
				break;
			}
			else{
				exp = exp - ( levelExp - currentExp );
				if( level == Constant.CON_PLAYER_MAX_LEVEL ){
					currentExp = levelExp;
					break;
				}
				else{
					currentExp = 0;
					level += 1;
					perv = redisCacheUtil.getPlayerExpByLevel(level);
					levelExp = perv.getExp();
				}
			}
		}
		piv.setLevel(level);
		piv.setCurrent_exp(currentExp);
		
		//更新战力
		
		return piv;
	}
	
}
