package com.shinian.dao;



import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.JinengRedisVo;
import com.shinian.vo.JinjieMaterialRedisVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcUpdateRedisVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.YuanfenInfoRedisVo;


@Repository
public class CommonDataDao  {

	public List<Map<String,Object>> getSensitiveCharacterList()
	{
		final String sql = " select `name` from common_sensitive_character where `status`= 1 ";	
		return WebConstant.commonJdbc.getJdbcTemplate().queryForList(sql);
	}
	
	public NpcInfoRedisVo getNpcInfoByComId(final int comId)
	{
		final String sql = " select `id` as `comId`, `name`, `gender`, `star`, `category`, " +
				"`camp`, `health`, `attack`, `hujia`, `pojia`, `fachuan`, `fakang`, `baoji`, " +
				"`renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, " +
				"`gedang`, `gedangPoss`, `reduce`, `talent`, `talentVal`, `attackStep`, " +
				"`healthStep`, `levelupRate`, `pieces`, `maxPieces`, `desc`, `updateTime`, " +
				"`status`, `pieceId`, `skill1`, `skill2`, `skill3`, `skill4`, `skill5`, " +
				"`skill6`, `skill7`, `skill8`, `skill9`, `skill10`, `skill11` from common_npc_info where id = ? and status = 1";
		List<NpcInfoRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcInfoRedisVo.class),new Object[]{comId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}
	
	public PropInfoRedisVo getPropInfoByComId(final int comId)
	{
		final String sql = " select `comId`, `name`, `nature`, `val`, `star`, `desc`, `updateTime`, `status` from common_prop_info where comId = ? and status = 1";
		List<PropInfoRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PropInfoRedisVo.class),new Object[]{comId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}
	
	public YuanfenInfoRedisVo getYuanfenInfoByComId(final int comId)
	{
		final String sql = " select `comId`, `name`, `npcId`, `category`, `objId`, `addAttack`, `addHealth`, `desc`, `updateTime`, `status` from common_yuanfen_info where comId = ? and status = 1";
		List<YuanfenInfoRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(YuanfenInfoRedisVo.class),new Object[]{comId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}
	
	//获得武将升级需要的材料
	public JinjieMaterialRedisVo getJinjieNeedMByPinjie(int nextpinjie){
		
		final String sql = " select `silver`,`jinjiedan`, `fivecolorstone`,`tigertally`, `eviltally`," +
				"`ploughtally`, `sttally`,`suitangmedal` from common_npc_jinjie_material where nextpinjie = ?";
		List<JinjieMaterialRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(JinjieMaterialRedisVo.class),new Object[]{nextpinjie});	
	
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	}
	
	public NpcUpdateRedisVo getExpBylevel(int level){
		
		final String sql = " select `experience` from common_npc_experience where level = ?";
		//WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});
		//WebConstant.commonJdbc.getJdbcTemplate().queryForObject( sql, new Object[]{level}, Integer.class);
		long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{level});		
		
		List<NpcUpdateRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NpcUpdateRedisVo.class),new Object[]{level});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	
	}
	
	public JinengRedisVo getJinengInfoById(int id){
		
		final String sql = " select `desc`,`jineng_level`,`need_pinjie`,`init_nuqi`,`need_nuqi`," +
		"`consum_nuqi`,`remain_nuqi`,`attack_num`,`mubiao_pos`,`damage_type`,`damage_min`," +
		"`damage_max`,`add_health_type`,`add_health`,`shuxing_type`,`add_damage`," +
		"`add_tianming`,`is_relive`,`is_wudi`,`add_pojia`,`add_fachuan`,`add_baoji`," +
		"`add_mingzhong`,`add_shanbi` where nextpinjie = ?";

		long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{id});		
		
		List<JinengRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(JinengRedisVo.class),new Object[]{id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	
	}
	
}
