package com.shinian.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.BaseDaoJdbcImpl;
import com.shinian.vo.AccPushVo;
import com.shinian.vo.AccToken;
import com.shinian.vo.RegisterReqVo;
@Repository
public class AccTokenDao extends BaseDaoJdbcImpl<RegisterReqVo, Integer>{

	public AccTokenDao() {
		super("");
	}

	public void insert(final int uid ,final String token, final long expire){
		final String sql = "REPLACE INTO acc_token SET uid = ?, token = ?,expire = ?";
		this.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, uid);
                ps.setString(2, token);
                ps.setLong(3, expire);
                return ps;
            }					
        });
    
        return ;
	}

	public AccToken getToken(final int uid ){
		final String sql = "select uid,token,expire from acc_token where uid = ? ";
		List<AccToken> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccToken.class),
				new Object[]{uid});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public AccToken getToken(final String token ,final int uid ){
		final String sql = "select * from acc_token where token = ? and uid = ? ";
		List<AccToken> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccToken.class),
				new Object[]{token,uid});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public int updateBindMail( final int uid ,final String token ,final String expire )
	{
		final String sql = " update acc_token set expire = ? ,token = ? where uid = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{uid,token,expire});
	}
	
	/********************************************* acc_push **********************************************/
	public void insertPush(final int uid ,final int channelId,final String updateTime){
		final String sql = "REPLACE INTO acc_push SET uid = ?, channel_id = ?,update_time = ?";
		this.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, uid);
                ps.setInt(2, channelId);
                ps.setString(3, updateTime);
                return ps;
            }					
        });
    
        return ;
	}
	public List<AccPushVo> getAccPushList(final String startTime,final String endTime)
	{
		final String sql = " select uid,channel_id from acc_push  where update_time >= ? and update_time <= ? ";
		return this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccPushVo.class),
				new Object[]{startTime,endTime});
	}
	
}
