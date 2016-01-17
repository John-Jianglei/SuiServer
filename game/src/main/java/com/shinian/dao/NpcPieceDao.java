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
import com.shinian.vo.NpcJinjieVo;
import com.shinian.vo.NpcPieceVo;

@Repository
public class NpcPieceDao {

	//获得npc碎片信息
	public NpcPieceVo getNpcPieceBy2Id(int comId, String uid){
		
		final String sql = " select `id`,`comId`,`uid`,`amount`,`updateTime` from game_npc_piece where comId = ? and uid=?";
	
		List<NpcPieceVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcPieceVo.class),new Object[]{comId,uid});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;	
	}
	
	//更新npc碎片数量
	public int updateNpcPiece(NpcPieceVo npv){
		
		final String sql = " update game_npc_piece set `amount`=? where id = ? ";

		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, npv.getAmount(), npv.getId() );		

		return row;
			
	}
	
	//插入一条记录
	public int insertNpcPiece(final NpcPieceVo npv){
		
		final String sql = "insert into game_npc_piece(`comId`,`uid`,`amount`,`updateTime`) values(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, npv.getComId()); 
                ps.setString(2, npv.getUid()); 
                ps.setInt(3, npv.getAmount());
                ps.setString(4, npv.getUpdateTime());
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
			
	}
	
}
