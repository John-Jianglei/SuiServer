package com.shinian.dao;



import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.NpcInfoRedisVo;

@Repository
public class CommonDataDao  {

	public List<Map<String,Object>> getSensitiveCharacterList()
	{
		final String sql = " select `name` from common_sensitive_character where `status`= 1 ";	
		return WebConstant.commonJdbc.getJdbcTemplate().queryForList(sql);
	}
	
	public NpcInfoRedisVo getNpcInfoByComId(final int comId)
	{
		final String sql = " select `id` as `comId`, `name`, `gender`, `star`, `category`, `camp`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`, `talent`, `talentVal`, `attackStep`, `healthStep`, `levelupRate`, `pieces`, `maxPieces`, `desc`, `updateTime`, `status` from common_npc_info where id = ? and status = 1";
		List<NpcInfoRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoRedisVo.class),new Object[]{comId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}
	
	
}
