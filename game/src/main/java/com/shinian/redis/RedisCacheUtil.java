package com.shinian.redis;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.shinian.service.CommonDataService;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.PropInfoRedisVo;


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

	
	public boolean isPropComIdExist(int comId)
	{
		Jedis jedis = RedisMessageUtil.getInstance().getConnection();
		try{
			String prefix = RedisKeyDefine.KEY_COMMON_PROP_INFO;
			String key = String.format(prefix, comId);

			return jedis.exists(key);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			RedisMessageUtil.getInstance().closeConnection(jedis);
		}
		
		return false;
	}
	
}
