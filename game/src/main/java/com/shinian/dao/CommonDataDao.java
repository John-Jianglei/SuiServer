package com.shinian.dao;



import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.NpcInfoVo;

@Repository
public class CommonDataDao  {

	public List<Map<String,Object>> getSensitiveCharacterList()
	{
		final String sql = " select `name` from common_sensitive_character where `status`= 1 ";	
		return WebConstant.commonJdbc.getJdbcTemplate().queryForList(sql);
	}
	
	public NpcInfoVo getNpcInfoByComId(final int comId)
	{
		final String sql = " select `id` as `comId`,`name`,`gender`,`star`,`category`,`camp`,`health`,`attack`,`defense`,`desc` from common_npc_info where id = ? and status = 1";
		List<NpcInfoVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoVo.class),new Object[]{comId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}
	
	
}
