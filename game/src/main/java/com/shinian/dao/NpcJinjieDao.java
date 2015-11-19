package com.shinian.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.NpcJinjieVo;


@Repository
public class NpcJinjieDao {
	
	//获得材料comId
	public NpcJinjieVo getNpcComIdByid(String uid, int id){
		
		final String sql = " select `comId`,`pinjie` from game_npc_info where uid = ? and id = ?";
	
		List<NpcJinjieVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{uid,id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;	
	}
	
	
	//获得武将升级需要的材料
	public NpcJinjieVo getJinjieNeedMByPinjie(int nexpinjie){
		
		final String sql = " select `silver`,`jinjiedan`, `fivecolorstone`,`tigertally`, `eviltally`," +
				"`ploughtally`, `sttally`,`suitangmedal` from common_rpc_jinjie_material where nextpinjie = ?";
		List<NpcJinjieVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{nexpinjie});	
	
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	}
	
	//获得材料comId
	public int getComIdByEname(String ename){
		
		final String sql = " select `comId` from common_prop_info where id = ?";
		int comId = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{ename});	
		
		return comId;		
	}
	
	public int getMaterialNumbyId(int comId, String uid){
		
		final String sql = " select `amount` from game_prop_info where comId=? and npcId=-1 and uid = ?";
		int CardNum = WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{comId,uid});		
		
		return CardNum;	
	}
	
	//获得武将已有的材料
	public List<NpcJinjieVo> getRpcHasMByLevel(int nexlevel){
		
		final String sql = " select `jinjiedan`, `fivecolorstone`,`tigertally`, `eviltally`," +
				"`ploughtally`, `sttally`,`suitangmedal` from common_rpc_jinjie_material where id = ?";
		List<NpcJinjieVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{nexlevel});	
		
		return list;		
	}
	
//	public long getSilverByUid(String uid){
//		
//		final String sql = " select `experience` from common_npc_experience where level = ?";
//		//WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});
//		//WebConstant.commonJdbc.getJdbcTemplate().queryForObject( sql, new Object[]{level}, Integer.class);
//		long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});		
//		
//		return exp;		
//	}
	
	public List<NpcJinjieVo> getSilverByUid(String uid){
		
		final String sql = " select `silver` from game_player_info where uid = ?";
		List<NpcJinjieVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcJinjieVo.class),new Object[]{uid});		
		
		return list;		
	}
	
	public int getExpCardNumbyId(int comId, String uid){
		
		final String sql = " select `amount` from game_prop_info where comId=? and npcId=-1 and uid = ?";
		int CardNum = WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{comId,uid});		
		
		return CardNum;	
	}
	
	public long getCardExpById(int comId){		
		
		final String sql = " select `val` from common_prop_info where nature=200 and comId = ?";
		long cardExp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{comId});
	
		return cardExp;
		
	}
	
	//modify 
	public int setExpById(int id, int level, long npcExp){
		
		String sql = "update game_npc_info set `experience` = ? ,`level` = ? where id = ?";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, npcExp, level, id);		

		return row;
	}
	
}
