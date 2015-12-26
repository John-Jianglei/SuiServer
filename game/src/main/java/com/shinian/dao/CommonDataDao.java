package com.shinian.dao;



import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.CombatPowerCoffiRedisVo;
import com.shinian.vo.JinengRedisVo;
import com.shinian.vo.JingjiRedisVo;
import com.shinian.vo.JinjieMaterialRedisVo;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.NpcUpdateRedisVo;
import com.shinian.vo.PassNameRedisVo;
import com.shinian.vo.PassZhanyiRedisVo;
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
				"`skill6`, `skill7`, `skill8`, `skill9`, `skill10`, `skill11`, `yuanfen1`, `yuanfen2`, `yuanfen3`, `yuanfen4` from common_npc_info where id = ? and status = 1";
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
		"`add_mingzhong`,`add_shanbi` from common_jineng where id = ?";

		//long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{id});		
		
		List<JinengRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(JinengRedisVo.class),new Object[]{id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	
	}
	

	public ArmoryRedisVo getArmoryByComId(final int comId)
	{
		final String sql = "select `id`, `name`, `category`, `star`, `initSliver`, `sliverStep`, `attack`, `attackStep`, `attackStepJinglian`, `addAttack`, `addAttackGaoji`, `health`, `healthStep`, `healthStepJinglian`, `addHealth`, `addHealthGaoji`, `pojia`, `hujia`, `fachuan`, `fakang`, `baoji`, `renxing`, `mingzhong`, `shanbi`, `xixue`, `fantan`, `jiyun`, `kangyun`, `gedang`, `gedangPoss`, `reduce`, `pojiaGaoji`, `hujiaGaoji`, `fachuanGaoji`, `fakangGaoji`, `baojiGaoji`, `renxingGaoji`, `mingzhongGaoji`, `shanbiGaoji`, `xixueGaoji`, `fantanGaoji`, `jiyunGaoji`, `kangyunGaoji`, `gedangGaoji`, `gedangPossGaoji`, `reduceGaoji`, `pieceId`, `pieces`, `minPieces`, `desc`, `updateTime`, `status` from common_armory_info where id = ? and status = 1";
		List<ArmoryRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(ArmoryRedisVo.class),new Object[]{comId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}

	public PassNameRedisVo getPassNameInfoById(int id){
		
		final String sql = " select `name`,`pt1`,`pt2`,`pt3`,`pt4`,`pt5`,`pt6`,`pt7`,`pt8`,`pt9`,`pt10`," +
		"`pt11`,`pt12`,`pt13`,`pt14`,`pt15`,`pt16`,`pt17`,`pt18`,`ptSliver`,`ptGold`,`ptFame`,`em1`," +
		"`em2`,`em3`,`em4`,`em5`,`em6`,`em7`,`em8`,`em9`,`em10`,`em11`,`em12`,`em13`,`em14`,`em15`,`em16`" +
		"`em17`,`em18`,`emSliver`,`emGold`,`emFame`,`dy1`,`dy2`,`dy3`,`dy4`,`dy5`,`dy6`,`dy7`,`dy8`," +
		"`dy9`,`dy10`,`dy11`,`dy12`,`dy13`,`dy14`,`dy15`,`dy16`,`dy17`,`dy18`,`dySliver`,`dyGold`,`dyFame`" +
		"from common_pass_name where id = ?";

		//long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{id});		
		
		List<PassNameRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassNameRedisVo.class),new Object[]{id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	
	}
	
	public PassZhanyiRedisVo getPassZhanyiInfoById(int id){
		
		final String sql = " select `id`,`name`,`comId0`,`comId1`,`comId2`,`comId3`,`comId4`,`comId5`," +
		"`attackTimes`,`battleCount`,`exp`,`sliver`,`rewardType1`,`rewardId1`,`rewardNum1`,`rewardP1`," +
		"`rewardType2`,`rewardId2`,`rewardNum2`,`rewardP2`,`rewardType3`,`rewardId3`,`rewardNum3`," +
		"`rewardP3`,`rewardType4`,`rewardId4`,`rewardNum4`,`rewardP4` from common_pass_zhanyi where id = ?";

		//long exp = WebConstant.commonJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{id});		
		
		List<PassZhanyiRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassZhanyiRedisVo.class),new Object[]{id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;		
	
	}
	
	public CombatPowerCoffiRedisVo getCombatPowerCoffiById(int id){
	
		final String sql = " select `id`,`attackC`,`healthC`,`basePower`,`pojiaC`,`hujiaC`,`fachuanC`," +
				"`fakangC`,`baojiC`,`renxingC`,`mingzhongC`,`shanbiC`,`xixueC`,`fantanC`,`jiyunC`," +
				"`kangyunC`,`gedangC`,`reduceC` from common_power_coffi where id = ?";
				
		List<CombatPowerCoffiRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(CombatPowerCoffiRedisVo.class),
				new Object[]{id});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public JingjiRedisVo getJingjiInfoByPos(int pos){
		
		final String sql = " select `id`,`title`,`posTop`,`posBot`,`interval` " +
				"from common_jingji where posTop >= ? and posBot < ? ";
				
		List<JingjiRedisVo> list = WebConstant.commonJdbc.getJdbcTemplate().query(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(JingjiRedisVo.class),
				new Object[]{pos,pos});	
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	
	
}

