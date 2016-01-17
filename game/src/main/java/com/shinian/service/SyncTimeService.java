package com.shinian.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.ArmoryDao;
import com.shinian.dao.PlayerNewsDao;
import com.shinian.dao.PropInfoDao;
import com.shinian.util.Constant;
import com.shinian.util.DateUtil;
import com.shinian.util.Message;
import com.shinian.vo.AnnexPackRedisVo;
import com.shinian.vo.ArmoryRedisVo;
import com.shinian.vo.ArmoryReqVo;
import com.shinian.vo.ArmoryVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.MessageRespVo;
import com.shinian.vo.NewsReqVo;
import com.shinian.vo.NewsRespVo;
import com.shinian.vo.NewsVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PlayerNewsTimeVo;
import com.shinian.vo.PropInfoReqVo;
import com.shinian.vo.PropInfoRedisVo;
import com.shinian.vo.PropInfoVo;
import com.shinian.vo.SyncTimeReqVo;
import com.shinian.vo.SyncTimeRespVo;



@Service
public class SyncTimeService {
	
	public MessageRespVo getCurrentTime(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		MessageRespVo result = new MessageRespVo();

		CommonReqVo gcrv = JSON.parseObject(jsonStr, CommonReqVo.class);		
		SyncTimeReqVo nrv = JSON.parseObject(gcrv.getData().toString(), SyncTimeReqVo.class);
		result.setTs(gcrv.getTs());
	
		SyncTimeRespVo resp = new SyncTimeRespVo();
		resp.setCurrentTime( DateUtil.getCurrentTime() );
		
		result.setData(resp);		
		result.setCode(Message.MSG_CODE_OK);
		
		return result;
	}
}
		