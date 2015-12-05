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
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PropInfoVo;

@Repository
public class PropInfoDao {
	

	public PropInfoVo addPropertyToPlayer(String uid, int comId, int amount){
		String sql = "select `id`, `comId`, `uid`, `npcId`, `position`, `amount` from game_prop_info where `uid` = ? and `comId` = ?";
		List<PropInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PropInfoVo.class), new Object[]{uid, comId});
		
		if(pivList != null && pivList.size() > 0){
			sql = "update game_prop_info set `amount` = ? where `uid` = ? and `comId` = ?";
			pivList.get(0).setAmount(amount + pivList.get(0).getAmount());
			WebConstant.gameJdbc.getJdbcTemplate().update(sql, pivList.get(0).getAmount(), uid, comId);
			return pivList.get(0);
		}
		
		PropInfoVo pvo = new PropInfoVo();
		int rt = insert(uid, comId, amount);
		pvo.setId(rt);
		pvo.setComId(comId);
		pvo.setUid(uid);
		pvo.setAmount(amount);
		return pvo;
	}
	
	private int insert(final String uid, final int comId, final int amount){
		final String sql = "insert into game_prop_info(`uid`, `comId`, `amount`) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setString(1, uid);
                ps.setInt(2, comId);
                ps.setInt(3, amount);
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	
	public List<PropInfoVo> getPropListOfPlayer(String uid)
	{
		final String sql = "select `id`, `comId`, `uid`, `npcId`, `position`, `amount` from game_prop_info where `uid` = ?";
		List<PropInfoVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropInfoVo.class),new Object[]{uid});
		
		return list;
	}
	
	public List<PropInfoVo> getPropListOfNpc(int npcId)
	{
		final String sql = "select `id`, `comId`, `uid`, `npcId`, `position`, `amount` from game_prop_info where `npcId` = ?";
		List<PropInfoVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropInfoVo.class),new Object[]{npcId});
		
		return list;
	}	
	
	public PropInfoVo getPropOfPlayerByComId(String uid, int comId)
	{
		String sql = "select `id`, `comId`, `uid`, `npcId`, `position`, `amount` from game_prop_info where `uid` = ? and comId = ?";
		List<PropInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(PropInfoVo.class), new Object[]{uid, comId});
		
		if(pivList != null && pivList.size() > 0){
			return pivList.get(0);
		}
		
		return null;
	}
	
	public int delPropertyOfPlayer(PropInfoVo pv)
	{
		String sql = "delete from game_prop_info where `id` = ?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, pv.getId());
	}
	
	public int updatePropertyOfPlayer(PropInfoVo pv)
	{
		String sql = "update game_prop_info set `amount` = ? where `id` = ?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, pv.getAmount(), pv.getId());
	}
	
}
