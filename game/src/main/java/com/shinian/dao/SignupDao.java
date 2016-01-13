package com.shinian.dao;



import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.PlayerInfoVo;

@Repository
public class SignupDao{
	
	public int insertPlayer(String uid, String name, int gender){
		String sql = "insert into game_player_info(uid, name, gender, createTime) values(?, ?, ?, ?)";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, uid, name, gender, new Date());

		return row;
	}
	
	public int insertPlayer(PlayerInfoVo piv){
		String sql = "insert into game_player_info(uid, name, gender, silver, fame, gold, createTime) values(?, ?, ?, ?, ?, ?, ? )";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, piv.getUid(), piv.getName(), piv.getGender(), piv.getSilver(),
				piv.getFame(), piv.getGold(), new Date());

		return row;
	}
	
	public boolean isUidNameUnique(String uid, String name){	
		String sql = "select uid from game_player_info where uid = ? or name = ?";
		List<PlayerInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PlayerInfoVo.class), new Object[]{uid, name});
		
		if ((pivList == null) || (pivList.size() == 0))
			return true;
		else
			return false;
	} 
	
	public PlayerInfoVo getPlayerInfoByUid(String uid){
		String sql = "select uid, name from game_player_info where uid = ?";
		List<PlayerInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PlayerInfoVo.class), new Object[]{uid});
		
		if(pivList != null && pivList.size() > 0){
			return pivList.get(0);
		}
		
		return null;
	}
}
