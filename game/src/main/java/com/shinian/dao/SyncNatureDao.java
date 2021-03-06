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
public class SyncNatureDao {
	

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
		int rt = 0;
		pvo.setId(rt);
		pvo.setComId(comId);
		pvo.setUid(uid);
		pvo.setAmount(amount);
		return pvo;
	}
	
	
	public int updateNpcNature(NpcInfoVo npc)
	{
		String sql = "update game_npc_info set `health` = ?, `attack` = ?,  `hujia` = ?,  `pojia` = ?,  `fachuan` = ?,  `fakang` = ?,  `baoji` = ?,  `renxing` = ?,  `mingzhong` = ?,  `shanbi` = ?,  `xixue` = ?,  `fantan` = ?,  `jiyun` = ?,  `kangyun` = ?,  `gedang` = ?,  `gedangPoss` = ?,  `reduce` = ?, `yuanfen1` = ?, `yuanfen2` = ?, `yuanfen3` = ?, `yuanfen4` = ? where `id` = ?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, npc.getHealth(),  npc.getAttack(),  npc.getHujia(),  npc.getPojia(),  npc.getFachuan(),  npc.getFakang(),  npc.getBaoji(),  npc.getRenxing(),  npc.getMingzhong(),  npc.getShanbi(),  npc.getXixue(),  npc.getFantan(),  npc.getJiyun(),  npc.getKangyun(),  npc.getGedang(),  npc.getGedangPoss(),  npc.getReduce(), npc.getYuanfen1(), npc.getYuanfen2(), npc.getYuanfen3(), npc.getYuanfen4(), npc.getId());
	}
	
}