package com.shinian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.NpcUpdateVo;
import com.shinian.vo.NpcInfoVo;

@Repository
public class NpcUpdateDao{
	
	public List<NpcUpdateVo> getRpcById(int id){
		
		final String sql = " select `experience`, `level`,`uid` from game_npc_info where id = ?";
		List<NpcUpdateVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcUpdateVo.class),new Object[]{id});	
		
		return list;		
	}
	
	public long getExpBylevel(int level){
		
		final String sql = " select `experience` from common_npc_experience where level = ?";
		//WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});
		//WebConstant.commonJdbc.getJdbcTemplate().queryForObject( sql, new Object[]{level}, Integer.class);
		long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});		
		
		return exp;		
	}
	
	public int getPlayerLevelByUid(String uid){
		
		final String sql = " select `level` from game_player_info where uid = ?";
		int level = WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{uid});		
		
		return level;		
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
	
	//modify wupin table
	public int setCardNumById(int kk){		
		int row = 0;
		return row;		
	}

	
}
