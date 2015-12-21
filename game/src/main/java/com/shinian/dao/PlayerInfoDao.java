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
import com.shinian.vo.PassLogVo;
import com.shinian.vo.PlayerInfoVo;

@Repository
public class PlayerInfoDao{
	
	public boolean isUidExist(String uid){	
		String sql = "select uid from game_player_info where uid = ?";
		List<PlayerInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PlayerInfoVo.class), new Object[]{uid});
		
		if ((pivList == null) || (pivList.size() == 0))
			return false;
		else
			return true;
	} 
	
	public int updatePlayer(final PlayerInfoVo piv)
	{
		final String sql = "update game_pass_log set `level`=?,`current_exp`=?,`vip_level`=?," +
				"`silver`=?,`fame`=?,`gold`=?,`current_strength`=? where `uid`=? ";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, piv.getLevel());
                ps.setInt(2, piv.getCurrent_exp()); 
                ps.setInt(3, piv.getVip_Level()); 
                ps.setInt(4, piv.getSilver()); 
                ps.setInt(5, piv.getFame()); 
                ps.setInt(6, piv.getGold());
                ps.setDate(7, (java.sql.Date) new Date());
                ps.setString(8, piv.getUid());
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}	
	
	
	public int insertPlayer(String uid, String name, int gender){
		String sql = "insert into game_player_info(uid, name, gender, create_time) values(?, ?, ?, ?)";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, uid, name, gender, new Date());

		return row;
	}
		
	public PlayerInfoVo getPlayerInfoByUid(String uid){
		String sql = "select id, name from game_player_info where uid = ?";
		List<PlayerInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PlayerInfoVo.class), new Object[]{uid});
		
		if(pivList != null && pivList.size() > 0){
			return pivList.get(0);
		}
		
		return null;
	}
}
