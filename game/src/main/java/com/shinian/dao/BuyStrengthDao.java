//购买体力
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
import com.shinian.vo.BuyStrengthLogVo;
import com.shinian.vo.PassLogVo;

@Repository
public class BuyStrengthDao {
	
	//BuyStrengthLog get 
	public BuyStrengthLogVo getBuyStrengthLog( String uid, String date )
	{
		final String sql = " select `id`,`uid`,`count`,`date` from game_buyStrength_log " +
				"where uid = ? and date=?";
		List<BuyStrengthLogVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(BuyStrengthLogVo.class),new Object[]{uid,date});
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//BuyStrengthLog insert 
	public int insertBuyStrengthLogLog(final BuyStrengthLogVo bsLog)
	{
		final String sql = "insert into game_buyStrength_log(`uid`,`date`,`count`) values(?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setString(1, bsLog.getUid());
                ps.setString(2, bsLog.getDate());
                ps.setInt(3, bsLog.getCount()); 
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	//BuyStrengthLog update 
	public int updateBuyStrengthLogLog( int count, String uid, String date )
	{
		final String sql = "update game_buyStrength_log set `count`=? where `uid`=? and `date`=?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, count, uid, date);		
	}
}
