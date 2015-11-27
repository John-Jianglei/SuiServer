package com.shinian.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.JinjieMaterialRedisVo;
import com.shinian.vo.NpcJinjieVo;
import com.shinian.vo.PropInfoReqVo;


@Repository
public class NpcJinjieDao {
	
	//获得材料comId
	public NpcJinjieVo getNpcJinjieByid(int id){
		
		final String sql = " select `comId`,`pinjie`,`level`,`uid` from game_npc_info where id = ?";
	
		List<NpcJinjieVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;	
	}
	
//	//获得材料comId
//	public int getComIdByEname(String ename){
//		
//		final String sql = " select `comId` from common_prop_info where id = ?";
//		int comId = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{ename});	
//		
//		return comId;		
//	}	
	
	public int getMaterialNumbyId(int comId, String uid){
		
		int npcId = -1;
		final String sql = " select `amount` from game_prop_info where comId=? and npcId=? and uid = ?";
//		/int materialNum = WebConstant.gameJdbc.getJdbcTemplate().queryForObject(sql,new Object[]{comId,uid},Integer.class);
		List<PropInfoReqVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropInfoReqVo.class),new Object[]{comId,npcId,uid});	
		
		if(list != null && list.size() > 0){
			return list.get(0).getAmount();
		}

		return 0;	
	}
	
	//获得武将已有的材料
//	public List<NpcJinjieVo> getRpcHasMByLevel(int nexlevel){
//		
//		final String sql = " select `jinjiedan`, `fivecolorstone`,`tigertally`, `eviltally`," +
//				"`ploughtally`, `sttally`,`suitangmedal` from common_rpc_jinjie_material where id = ?";
//		List<NpcJinjieVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{nexlevel});	
//		
//		return list;		
//	}
	
//	public long getSilverByUid(String uid){
//		
//		final String sql = " select `experience` from common_npc_experience where level = ?";
//		//WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});
//		//WebConstant.commonJdbc.getJdbcTemplate().queryForObject( sql, new Object[]{level}, Integer.class);
//		long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});		
//		
//		return exp;		
//	}
	
	public BigInteger getSilverByUid(String uid){
		
		final String sql = " select `silver` from game_player_info where uid = ?";
		List<NpcJinjieVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{uid});		
		
		if(list != null && list.size() > 0){
			return list.get(0).getSilver();
		}
		
		BigInteger retSilver = new BigInteger("0");
		
		return retSilver;		
	}
	
	public int setSilverbyId(BigInteger silver,String uid){
		
		String sql = "update game_player_info set `silver` = ? where uid = ?";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, silver, uid);		

		return row;
	}
	
	public int setNpcInfobyId(int pinjie, int attack, int health, int npcId){
		
		String sql = "update game_npc_info set `pinjie` = ? , `attack` = ?, `health` = ? where id = ?";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, pinjie, attack, health, npcId);		

		return row;
	}
	
	public int deleteNpcbyId(int npcId){
		
		String sql = "delete from game_npc_info where id = ?";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, npcId);		

		return row;
	}
	
}
