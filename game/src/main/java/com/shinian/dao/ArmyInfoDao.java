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
		final String sql = " select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`, `healthBase`, `attackBase`, `hujiaBase`, `pojiaBase`, `fachuanBase`, `fakangBase`, `baojiBase`, `renxingBase`, `mingzhongBase`, `shanbiBase`, `xixueBase`, `fantanBase`, `jiyunBase`, `kangyunBase`, `gedangBase`, `gedangPossBase`, `reduceBase`, `skill1`, `skill2`, `skill3`, `skill4`, `skill5`, `skill6`, `skill7`, `skill8`, `skill9`, `skill10`, `skill11`, `yuanfen1`, `yuanfen2`, `yuanfen3`, `yuanfen4` from game_npc_info where uid = ?";
		List<NpcInfoVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class),new Object[]{uid});
		
		return list;
	}
	
	public List<NpcInfoVo> getArmyOnBattleByUid(String uid)
	{
		final String sql = " select `id`, `comId`, `uid`, `pinjie`, `level`, `experience`, `position`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`, `healthBase`, `attackBase`, `hujiaBase`, `pojiaBase`, `fachuanBase`, `fakangBase`, `baojiBase`, `renxingBase`, `mingzhongBase`, `shanbiBase`, `xixueBase`, `fantanBase`, `jiyunBase`, `kangyunBase`, `gedangBase`, `gedangPossBase`, `reduceBase`, `skill1`, `skill2`, `skill3`, `skill4`, `skill5`, `skill6`, `skill7`, `skill8`, `skill9`, `skill10`, `skill11`, `yuanfen1`, `yuanfen2`, `yuanfen3`, `yuanfen4` from game_npc_info where uid = ? and position < 6";
		List<NpcInfoVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class),new Object[]{uid});
		
		return list;
	}
	
	
}
