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
import com.shinian.vo.ArmoryVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PropInfoVo;

@Repository
public class ArmoryDao {
	
	public ArmoryVo addArmoryToPlayer(String uid, ArmoryVo armory){
		int rt = insert(uid, armory);
		armory.setId(rt);
		armory.setUid(uid);
		
		return armory;
	}
	
	private int insert(final String uid, final ArmoryVo armory){
		final String sql = "insert into game_armory_info(`comId`, `uid`, `npcId`, `loaded`, `amount`, `gaoji`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, armory.getComId()); 
                ps.setString(2, uid); 
                ps.setInt(3, armory.getNpcId()); 
                ps.setInt(4, armory.getLoaded()); 
                ps.setInt(5, armory.getAmount()); 
                ps.setInt(6, armory.getGaoji()); 
                ps.setInt(7, armory.getHealth()); 
                ps.setInt(8, armory.getAttack()); 
                ps.setInt(9, armory.getHujia()); 
                ps.setInt(10, armory.getPojia()); 
                ps.setInt(11, armory.getFachuan()); 
                ps.setInt(12, armory.getFakang()); 
                ps.setInt(13, armory.getBaoji()); 
                ps.setInt(14, armory.getRenxing()); 
                ps.setInt(15, armory.getMingzhong()); 
                ps.setInt(16, armory.getShanbi()); 
                ps.setInt(17, armory.getXixue()); 
                ps.setInt(18, armory.getFantan()); 
                ps.setInt(19, armory.getJiyun()); 
                ps.setInt(20, armory.getKangyun()); 
                ps.setInt(21, armory.getGedang()); 
                ps.setInt(22, armory.getGedangPoss()); 
                ps.setInt(23, armory.getReduce()); 
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
}
