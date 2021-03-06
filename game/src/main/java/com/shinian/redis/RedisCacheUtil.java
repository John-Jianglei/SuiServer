package com.shinian.redis;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.shinian.service.CommonDataService;
import com.shinian.util.Constant;
import com.shinian.vo.AnnexPackRedisVo;
import com.shinian.vo.ArmoryJinjieRedisVo;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.BuyStrengthRedisVo;
import com.shinian.vo.CombatPowerCoffiRedisVo;
import com.shinian.vo.JinengRedisVo;
import com.shinian.vo.JingjiRedisVo;
import com.shinian.vo.JinjieMaterialRedisVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcUpdateRedisVo;
import com.shinian.vo.PassNameRedisVo;
import com.shinian.vo.PassZhanyiRedisVo;
import com.shinian.vo.PlayerExpRedisVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.VipPrivilegeRedisVo;
import com.shinian.vo.YuanfenInfoRedisVo;


@Component
public class RedisCacheUtil {
	
	@Autowired
	CommonDataService commonDataService;
	
	private static final Logger log = LoggerFactory.getLogger(RedisCacheUtil.class);
	
	public void initCacheInfo(){
		getSensitiveCharacter();
	}
	
	public String getSensitiveCharacter()
	{
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String key = RedisKeyDefine.KEY_COMMON_SENSITIVE_CHARACTER;
  			if(jedis.exists(key)){				
				String value = jedis.get(key);
				return value;
			}
			else{
				String sensitiveCharacter = "";
				List<Map<String,Object>> list = commonDataService.getSensitiveCharacterList();

				if( null != list && list.size() > 0 )
				{
					for (Map<String, Object> map2 : list) 
					{
						String value = map2.get("name")+"";
						 sensitiveCharacter +=value+"|";
					}
				}
				if( !"".equals( sensitiveCharacter ) )
				{
					sensitiveCharacter = sensitiveCharacter.substring(0, sensitiveCharacter.length()-1);
					jedis.set(key, sensitiveCharacter);
				}
				
				return sensitiveCharacter;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		return "";
	}
	
	public JinjieMaterialRedisVo getJinjieNeedMByPinjie(int pinjie)
	{
		JinjieMaterialRedisVo jinjieM = new JinjieMaterialRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_NPC_JINJIE_MATERIAL;
			String key = String.format(prefix, pinjie);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jinjieM.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jinjieM.fromList(list);
					return jinjieM;
				}
			}
			else{
				JinjieMaterialRedisVo v = commonDataService.getJinjieNeedMByPinjie(pinjie);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public NpcInfoRedisVo getNpcInfoByComId(int comId)
	{
		NpcInfoRedisVo npc = new NpcInfoRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_NPC_INFO;
			String key = String.format(prefix, comId);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, npc.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					npc.fromList(list);
					return npc;
				}
			}
			else{
				NpcInfoRedisVo v = commonDataService.getNpcInfoByComId(comId);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}

	public PropInfoRedisVo getPropInfoByComId(int comId)
	{
		PropInfoRedisVo pvo = new PropInfoRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_PROP_INFO;
			String key = String.format(prefix, comId);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, pvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					pvo.fromList(list);
					return pvo;
				}
			}
			else{
				PropInfoRedisVo v = commonDataService.getPropInfoByComId(comId);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}

	public YuanfenInfoRedisVo getYuanfenInfoByComId(int comId)
	{
		comId = Math.abs(comId);
		
		YuanfenInfoRedisVo pvo = new YuanfenInfoRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_YUANFEN_INFO;
			String key = String.format(prefix, comId);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, pvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					pvo.fromList(list);
					return pvo;
				}
			}
			else{
				YuanfenInfoRedisVo v = commonDataService.getYuanfenInfoByComId(comId);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}

	public NpcUpdateRedisVo getExpBylevel(int level)
	{
		NpcUpdateRedisVo nuvo = new NpcUpdateRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_NPC_EXPERIENCE;
			String key = String.format(prefix, level);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, nuvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					nuvo.fromList(list);
					return nuvo;
				}
			}
			else{
				NpcUpdateRedisVo v = commonDataService.getExpBylevel(level);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public boolean isPropComIdExist(int comId)
	{
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_PROP_INFO;
			String key = String.format(prefix, comId);

			if(jedis.exists(key)){
				return true;
			}
			
			PropInfoRedisVo v = commonDataService.getPropInfoByComId(comId);
			if(v != null){
				jedis.hmset(key, v.toMap());
				return true;
			}
			
			return false;			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return false;
	}
	
	public boolean isNpcComIdExist(int comId)
	{
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_NPC_INFO;
			String key = String.format(prefix, comId);

			if(jedis.exists(key)){
				return true;
			}
			
			NpcInfoRedisVo v = commonDataService.getNpcInfoByComId(comId);
			if(v != null){
				jedis.hmset(key, v.toMap());
				return true;
			}
			
			return false;			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return false;
	}
	
	public JinengRedisVo getJinengInfoById(int id)
	{
		id = Math.abs(id);
		JinengRedisVo jnvo = new JinengRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_NPC_JINENG;
			String key = String.format(prefix, id);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jnvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jnvo.fromList(list);
					return jnvo;
				}
			}
			else{
				JinengRedisVo v = commonDataService.getJinengInfoById(id);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	

	public ArmoryRedisVo getArmoryByComId(int comId)
	{
		ArmoryRedisVo pvo = new ArmoryRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_ARMORY_INFO;
			String key = String.format(prefix, comId);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, pvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					pvo.fromList(list);
					return pvo;
				}
			}
			else{
				ArmoryRedisVo v = commonDataService.getArmoryByComId(comId);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public boolean isArmoryComIdExist(int comId)
	{
		ArmoryRedisVo v = getArmoryByComId(comId);
		return (v != null) ? true : false;
	}

	public PassNameRedisVo getPassNameInfoById(int id)
	{
		id = Math.abs(id);
		PassNameRedisVo jnvo = new PassNameRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_PASS_NAME;
			String key = String.format(prefix, id);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jnvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jnvo.fromList(list);
					return jnvo;
				}
			}
			else{
				PassNameRedisVo v = commonDataService.getPassNameInfoById(id);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public PassZhanyiRedisVo getPassZhanyiInfoById(int id)
	{
		id = Math.abs(id);
		PassZhanyiRedisVo jnvo = new PassZhanyiRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_PASS_ZHANYI;
			String key = String.format(prefix, id);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jnvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jnvo.fromList(list);
					return jnvo;
				}
			}
			else{
				PassZhanyiRedisVo v = commonDataService.getPassZhanyiInfoById(id);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public CombatPowerCoffiRedisVo getCombatPowerCoffiById(int id)
	{
		id = Math.abs(id);
		CombatPowerCoffiRedisVo jnvo = new CombatPowerCoffiRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_COMBAT_POWER_COFFI;
			String key = String.format(prefix, id);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jnvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jnvo.fromList(list);
					return jnvo;
				}
			}
			else{
				CombatPowerCoffiRedisVo v = commonDataService.getCombatPowerCoffiById(id);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public JingjiRedisVo getJingjiInfoByPos(int pos)
	{
		pos = Math.abs(pos);
		JingjiRedisVo jnvo = new JingjiRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_JINGJI;
			String key = String.format(prefix, pos);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jnvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jnvo.fromList(list);
					return jnvo;
				}
			}
			else{
				JingjiRedisVo v = commonDataService.getJingjiInfoByPos(pos);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}	
	

	public ArmoryJinjieRedisVo getArmoryJinjieInfo(int star, int category, int nextPinjie)
	{
		if (!((nextPinjie == Constant.ARMORY_JINJIE_MAX_GENERAL_PINJIE+1) && (star==3 || star==4))) category = 0;

		ArmoryJinjieRedisVo pvo = new ArmoryJinjieRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_ARMORY_JINJIE;
			String key = String.format(prefix, star, category, nextPinjie);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, pvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					pvo.fromList(list);
					return pvo;
				}
			}
			else{
				ArmoryJinjieRedisVo v = commonDataService.getArmoryJinjieInfo(star, nextPinjie);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}

	public JingjiRedisVo getJingjiInfoById(int id)
	{
		id = Math.abs(id);
		JingjiRedisVo jnvo = new JingjiRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_JINGJI;
			String key = String.format(prefix, id);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, jnvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					jnvo.fromList(list);
					return jnvo;
				}
			}
			else{
				JingjiRedisVo v = commonDataService.getJingjiInfoById(id);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public AnnexPackRedisVo getAnnexPack(int id)
	{
		AnnexPackRedisVo pvo = new AnnexPackRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_ANNEXPACK;
			String key = String.format(prefix, id);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, pvo.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					pvo.fromList(list);
					return pvo;
				}
			}
			else{
				AnnexPackRedisVo v = commonDataService.getAnnexPack(id);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public VipPrivilegeRedisVo getVipPrivilegeByVip(int vipLevel)
	{
		VipPrivilegeRedisVo vprv = new VipPrivilegeRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_VIP_PRIVILEGE_ONE;
			String key = String.format(prefix, vipLevel);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, vprv.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					vprv.fromList(list);
					return vprv;
				}
			}
			else{
				VipPrivilegeRedisVo v = commonDataService.getVipPrivilegeByVip(vipLevel);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}	
	
//	public List<String> geingtVipPrivilege( int vipAll)
//	{
//		List<String> listVprv = new ArrayList<String>();
//		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
//		try{
//			String key = RedisKeyDefine.KEY_VIP_PRIVILEGE;
//			if(jedis.exists(key)){
//				
//				listVprv =  jedis.lrange(key, 0, -1);   ;
//				return listVprv;
//			}
//			else{
//				List<vipPrivilegeRedisVo> vipAllList = commonDataService.geingtVipPrivilege(vipAll);
//				if( null != vipAllList && vipAllList.size() > 0 )
//				{
//					for (vipPrivilegeRedisVo data : vipAllList) 
//					{
//						jedis.lpush(key, data.getPlayerId()+"");
//						list.add(data.getPlayerId()+"");
//					}
//				}
//				
//				return list;
//			}
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//		finally{
//			RedisMessageUtil.getInstance().closeConnection(jedis);
//		}
//		
//		return null;
//	}

	public PlayerExpRedisVo getPlayerExpByLevel(int level)
	{
		PlayerExpRedisVo vprv = new PlayerExpRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_PLAYER_EXP;
			String key = String.format(prefix, level);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, vprv.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					vprv.fromList(list);
					return vprv;
				}
			}
			else{
				PlayerExpRedisVo v = commonDataService.getPlayerExpByLevel(level);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}
	
	public BuyStrengthRedisVo getBuyStrengthBySeq(int seq)
	{
		BuyStrengthRedisVo vprv = new BuyStrengthRedisVo();
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_BUY_STRENGTH;
			String key = String.format(prefix, seq);

			if(jedis.exists(key)){
				List<String> list = jedis.hmget(key, vprv.getFieldNames());
				
				if(list != null && list.size() > 0 && list.get(0) != null) {					
					vprv.fromList(list);
					return vprv;
				}
			}
			else{
				BuyStrengthRedisVo v = commonDataService.getBuyStrengthBySeq(seq);
				if(v != null){
					jedis.hmset(key, v.toMap());
				}
				return v;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return null;
	}	
	
}

