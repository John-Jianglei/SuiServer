package com.shinian.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinian.dao.CommonDataDao;
import com.shinian.vo.NpcInfoRedisVo;
import com.shinian.vo.PropInfoRedisVo;


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
	
	
}