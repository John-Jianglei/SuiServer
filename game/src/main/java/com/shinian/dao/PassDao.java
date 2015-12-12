package com.shinian.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.PassVo;
import com.shinian.vo.PassZhanyiVo;

@Repository
public class PassDao {

	public List<PassVo> getArmyOnBattleByUid(String uid)
	{
		final String sql = " select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce` from game_npc_info where uid = ? and position < 6";
		List<PassVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassVo.class),new Object[]{uid});
		
		return list;
	}
	
	//暂时先这么用，后面改redis
	public PassZhanyiVo getPassZhanyiById(int id)
	{
		final String sql = " select `name`,`comId0`, `comId1`, `comId2`, `comId3`, `comId4`, `comId5`," +
				" `attackTimes`, `battleCount`, `exp`, `sliver`, `comType1`, `comId11`, `comNum1`," +
				" `comType2`, `comId21`, `comNum2`, `comType3`, `comId31`, `comNum3`, `comType4`," +
				" `comId41`, `comNum4`, `comType5`, `comId51`, `comNum5`, `comType6`, `comId6`, " +
				"`comNum6` where id = ?";
		List<PassZhanyiVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassZhanyiVo.class),new Object[]{id});
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
}
