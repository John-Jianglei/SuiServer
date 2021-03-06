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

@Repository
public class NpcInfoDao{
	
	public NpcInfoVo getNpcInfoById(int id){
		String sql = "select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`, `healthBase`, `attackBase`, `hujiaBase`, `pojiaBase`, `fachuanBase`, `fakangBase`, `baojiBase`, `renxingBase`, `mingzhongBase`, `shanbiBase`, `xixueBase`, `fantanBase`, `jiyunBase`, `kangyunBase`, `gedangBase`, `gedangPossBase`, `reduceBase`, `skill1`, `skill2`, `skill3`, `skill4`, `skill5`, `skill6`, `skill7`, `skill8`, `skill9`, `skill10`, `skill11`, `yuanfen1`, `yuanfen2`, `yuanfen3`, `yuanfen4` from game_npc_info where id = ?";
		List<NpcInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class), new Object[]{id});
		
		if(pivList != null && pivList.size() > 0){
			return pivList.get(0);
		}
		
		return null;
	}
	
	public NpcInfoVo getNpcByPosition(String uid, int position){
		String sql = "select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`, `healthBase`, `attackBase`, `hujiaBase`, `pojiaBase`, `fachuanBase`, `fakangBase`, `baojiBase`, `renxingBase`, `mingzhongBase`, `shanbiBase`, `xixueBase`, `fantanBase`, `jiyunBase`, `kangyunBase`, `gedangBase`, `gedangPossBase`, `reduceBase`, `skill1`, `skill2`, `skill3`, `skill4`, `skill5`, `skill6`, `skill7`, `skill8`, `skill9`, `skill10`, `skill11`, `yuanfen1`, `yuanfen2`, `yuanfen3`, `yuanfen4` from game_npc_info where `uid` = ? and `position` = ?";
		List<NpcInfoVo> pivList = WebConstant.gameJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class), new Object[]{uid, position});
		
		if(pivList != null && pivList.size() > 0){
			return pivList.get(0);
		}
		
		return null;
	}
	
	public int setNpcPosition(int id, int position){
		String sql = "update game_npc_info set `position` = ? where `id` = ?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, position, id);
	}
	
	public int deleteNpcById(int id){
		String sql = "delete from game_npc_info where `id` = ?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, id);
	}
	
}
