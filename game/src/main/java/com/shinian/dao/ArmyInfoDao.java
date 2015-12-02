package com.shinian.dao;



import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.NpcInfoVo;

@Repository
public class ArmyInfoDao {

	public List<NpcInfoVo> getArmyByUid(String uid)
	{
		final String sql = " select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce` from game_npc_info where uid = ?";
		List<NpcInfoVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class),new Object[]{uid});
		
		return list;
	}
	
	public List<NpcInfoVo> getArmyOnBattleByUid(String uid)
	{
		final String sql = " select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce` from game_npc_info where uid = ? and position < 6";
		List<NpcInfoVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class),new Object[]{uid});
		
		return list;
	}
	
	
}
