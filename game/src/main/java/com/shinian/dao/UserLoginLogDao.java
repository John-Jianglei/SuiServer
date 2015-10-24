package com.shinian.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.PlayerInfoVo;

@Repository
public class UserLoginLogDao{
	
	public PlayerInfoVo getPlayerInfoByUid(String uid){
		String sql = "select uid,name,gender,level,create_time,vip_level,current_exp,silver,fame,gold,current_strength from game_player_info where uid = ?";
		List<PlayerInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PlayerInfoVo.class), new Object[]{uid});
		
		if(pivList != null && pivList.size() > 0){
			return pivList.get(0);
		}
		
		return null;
	}
}
