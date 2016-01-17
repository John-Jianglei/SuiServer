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
/*		String sql = "select `id`, `comId`, `uid`, `position` from game_npc_info where `uid` = ? order by `position` desc";
		List<NpcInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class), new Object[]{uid});
		
		int position = 0;
		
		if(pivList != null && pivList.size() > 0){
			position = pivList.get(0).getPosition() + 1;
		}*/
		
		NpcInfoVo npc = npcRedis.initGameNpc();
		npc.setUid(uid);
		int rt = insert(npc);
		npc.setId(rt);

		return npc;
	}
	
	private int insert(final NpcInfoVo npc){
		final String sql = "insert into game_npc_info(`comId`, `uid`, `position`, `healthBase`, `attackBase`, `hujiaBase`, " +
				"`pojiaBase`, `fachuanBase`, `fakangBase`, `baojiBase`, `renxingBase`, `mingzhongBase`, `shanbiBase`, " +
				"`xixueBase`, `fantanBase`, `jiyunBase`, `kangyunBase`, `gedangBase`, `gedangPossBase`, `reduceBase`, " +
				"`skill1`, `skill2`, `skill3`, `skill4`, `skill5`, `skill6`, `skill7`, `skill8`, `skill9`, `skill10`, " +
				"`skill11`, `yuanfen1`, `yuanfen2`, `yuanfen3`, `yuanfen4`) " +
				"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
				"?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, npc.getComId()); 
                ps.setString(2, npc.getUid()); 
                ps.setInt(3, npc.getPosition()); 
                ps.setInt(4, npc.getHealthBase()); 
                ps.setInt(5, npc.getAttackBase()); 
                ps.setInt(6, npc.getHujiaBase()); 
                ps.setInt(7, npc.getPojiaBase()); 
                ps.setInt(8, npc.getFachuanBase()); 
                ps.setInt(9, npc.getFakangBase()); 
                ps.setInt(10, npc.getBaojiBase()); 
                ps.setInt(11, npc.getRenxingBase()); 
                ps.setInt(12, npc.getMingzhongBase()); 
                ps.setInt(13, npc.getShanbiBase()); 
                ps.setInt(14, npc.getXixueBase()); 
                ps.setInt(15, npc.getFantanBase()); 
                ps.setInt(16, npc.getJiyunBase()); 
                ps.setInt(17, npc.getKangyunBase()); 
                ps.setInt(18, npc.getGedangBase()); 
                ps.setInt(19, npc.getGedangPossBase()); 
                ps.setInt(20, npc.getReduceBase());
                ps.setInt(21, npc.getSkill1());
                ps.setInt(22, npc.getSkill2());
                ps.setInt(23, npc.getSkill3());
                ps.setInt(24, npc.getSkill4());
                ps.setInt(25, npc.getSkill5());
                ps.setInt(26, npc.getSkill6());
                ps.setInt(27, npc.getSkill7());
                ps.setInt(28, npc.getSkill8());
                ps.setInt(29, npc.getSkill9());
                ps.setInt(30, npc.getSkill10());
                ps.setInt(31, npc.getSkill11());
                ps.setInt(32, npc.getYuanfen1());
                ps.setInt(33, npc.getYuanfen2());
                ps.setInt(34, npc.getYuanfen3());
                ps.setInt(35, npc.getYuanfen4());
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
}
