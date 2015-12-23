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
import com.shinian.vo.GamePassVo;
import com.shinian.vo.PassLogVo;
import com.shinian.vo.PassVo;

@Repository
public class PassDao {

	//pass insert
	public int insertPass(final GamePassVo gamePassVo)
	{
		final String sql = "insert into game_pass_log(`count`,`date`,`uid`,`battleId`) values(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
//                ps.setInt(1, passLog.getCount()); 
//                ps.setString(2, passLog.getDate()); 
//                ps.setString(3, passLog.getUid());
//                ps.setInt(4, passLog.getBattleId());
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	private String[] getPrfixByBattleId(int battleId){
		
		String[] colName = { "ptStars", "pt", "pt" };
		int temp = battleId/10000;
		if( temp>3 || temp<1 ){
			return null;
		}
		switch( battleId/10000 ){
		case 1:
			colName[0] = "ptStars";
			colName[2] = "pt";
			break;
		case 2:
			colName[0] = "emStars";
			colName[2] = "em";
			break;
		case 3:
			colName[0] = "dyStars"; 
			colName[2] = "dy";
			break;				
		}
		
		temp = 3 * ( (battleId/10)%10-1 );
		temp += battleId % 10;
		
		colName[2] += Integer.toString(temp);
		colName[1] = colName[2];
		colName[2] += "Stars";
		colName[1] += "Id";
		
		return colName;
	}
	
	//pass get 
	public PassVo getPassByBattleId( int battleId, String uid )
	{
		String 	passStarsColName;
		String	battleIdName;
		String	battleStarsColName;
		
		String[] colName = getPrfixByBattleId(battleId);
		if( colName == null ){
			return null;
		}
		passStarsColName = colName[0];
		battleIdName = colName[1];
		battleStarsColName = colName[2];		
		
		final String sql = " select `uid`, `" + passStarsColName + "` as `passStars`, `"
				+ battleStarsColName + "` as `battleStars`, `" + battleIdName + "` as " +
				"`battle` from game_pass where `uid`=? and `" + battleIdName + "`=?";
		List<PassVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassVo.class),new Object[]{uid,battleId});
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//pass update 
	public int updatePassByBattleId( int passStars, int battleStars, int battleId, String uid )
	{
		String 	passStarsColName;
		String	battleIdName;
		String	battleStarsColName;
		
		String[] colName = getPrfixByBattleId(battleId);
		if( colName == null ){
			return 0;
		}
		passStarsColName = colName[0];
		battleIdName = colName[1];
		battleStarsColName = colName[2];
		
		final String sql = "update game_pass set `" + passStarsColName + "`=?, `" 
				+ battleStarsColName + "`=? where `uid`=? and `" + battleIdName + "`=?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, passStars, battleStars, uid, battleId);		
	}
	
	//passLog get 
	public PassLogVo getPassLog(int battleId, String uid, String date)
	{
		final String sql = " select `id`, `count`,`date`,`uid`,`battleId` from game_pass_log " +
				"where battleId = ? and uid = ? and date=?";
		List<PassLogVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassLogVo.class),new Object[]{battleId,uid,date});
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//passLog insert 
	public int insertPassLog(final PassLogVo passLog)
	{
		final String sql = "insert into game_pass_log(`count`,`date`,`uid`,`battleId`) values(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, passLog.getCount()); 
                ps.setString(2, passLog.getDate()); 
                ps.setString(3, passLog.getUid());
                ps.setInt(4, passLog.getBattleId());
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	//passLog update 
	public int updatePassLog( int count, String uid, int battleId )
	{
		final String sql = "update game_pass_log set `count`=? where `uid`=? and `battleId`=? ";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, count, uid, battleId);		
	}	
	
}
