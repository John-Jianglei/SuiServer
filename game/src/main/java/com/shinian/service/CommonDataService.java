package com.shinian.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinian.dao.CommonDataDao;
import com.shinian.vo.ArmoryJinjieRedisVo;
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

@Service
public class CommonDataService {
	@Autowired
	CommonDataDao commonDataDao;	

	public  List<Map<String,Object>> getSensitiveCharacterList()
	{
		return commonDataDao.getSensitiveCharacterList();
	}
	
	public NpcInfoRedisVo getNpcInfoByComId(int comId)
	{
		return commonDataDao.getNpcInfoByComId(comId);
	}
	
	public PropInfoRedisVo getPropInfoByComId(int comId)
	{
		return commonDataDao.getPropInfoByComId(comId);
	}
	
	public ArmoryJinjieRedisVo getArmoryJinjieInfo(int star, int nextPinjie)
	{
		return commonDataDao.getArmoryJinjieInfo(star, nextPinjie);
	}
	
	public List<YuanfenInfoRedisVo> getYuanfenInfoByNpcId(int npcId)
	{
		return commonDataDao.getYuanfenInfoByNpcId(npcId);
	}
	
	public YuanfenInfoRedisVo getYuanfenInfoByComId(int comId)
	{
		return commonDataDao.getYuanfenInfoByComId(comId);
	}
	
	public JinjieMaterialRedisVo getJinjieNeedMByPinjie(int pinjie)
	{
		return commonDataDao.getJinjieNeedMByPinjie(pinjie);
	}
	
	public NpcUpdateRedisVo getExpBylevel(int level)
	{
		return commonDataDao.getExpBylevel(level);
	}
	
	public JinengRedisVo getJinengInfoById(int id)
	{
		return commonDataDao.getJinengInfoById(id);
	}
	
		public ArmoryRedisVo getArmoryByComId(int comId)
	{
		return commonDataDao.getArmoryByComId(comId);
	}

	public PassNameRedisVo getPassNameInfoById(int id){
		return commonDataDao.getPassNameInfoById(id);
	}
	public PassZhanyiRedisVo getPassZhanyiInfoById(int id){
		return commonDataDao.getPassZhanyiInfoById(id);
	}
	
	public CombatPowerCoffiRedisVo getCombatPowerCoffiById(int id){
		return commonDataDao.getCombatPowerCoffiById(id);
	}
	
	public JingjiRedisVo getJingjiInfoByPos(int pos){
		return commonDataDao.getJingjiInfoByPos(pos);
	}
	
	public JingjiRedisVo getJingjiInfoById(int id){
		return commonDataDao.getJingjiInfoById(id);
	}	
	
}