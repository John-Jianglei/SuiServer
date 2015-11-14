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
import com.shinian.vo.NpcInfoRedisVo;

@Repository
public class NpcAddDao {
	

	public NpcInfoVo addNpcToPlayer(String uid, NpcInfoRedisVo npcRedis){
		String sql = "select `id`, `comId`, `uid`, `position` from game_npc_info where `uid` = ? order by `position` desc";
		List<NpcInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class), new Object[]{uid});
		
		int position = 1;
		
		if(pivList != null && pivList.size() > 0){
			position = pivList.get(0).getPosition() + 1;
		}
		
		NpcInfoVo npc = npcRedis.initGameNpc();
		npc.setUid(uid);
		npc.setPosition(position);
		int rt = insert(npc);
		npc.setId(rt);

		return npc;
	}
	
	private int insert(final NpcInfoVo npc){
		final String sql = "insert into game_npc_info(`comId`, `uid`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, npc.getComId()); 
                ps.setString(2, npc.getUid()); 
                ps.setInt(3, npc.getPosition()); 
                ps.setInt(4, npc.getHealth()); 
                ps.setInt(5, npc.getAttack()); 
                ps.setInt(6, npc.getHujia()); 
                ps.setInt(7, npc.getPojia()); 
                ps.setInt(8, npc.getFachuan()); 
                ps.setInt(9, npc.getFakang()); 
                ps.setInt(10, npc.getBaoji()); 
                ps.setInt(11, npc.getRenxing()); 
                ps.setInt(12, npc.getMingzhong()); 
                ps.setInt(13, npc.getShanbi()); 
                ps.setInt(14, npc.getXixue()); 
                ps.setInt(15, npc.getFantan()); 
                ps.setInt(16, npc.getJiyun()); 
                ps.setInt(17, npc.getKangyun()); 
                ps.setInt(18, npc.getGedang()); 
                ps.setInt(19, npc.getGedangPoss()); 
                ps.setInt(20, npc.getReduce());
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
}
