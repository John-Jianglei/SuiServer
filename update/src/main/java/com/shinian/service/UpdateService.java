package com.shinian.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.UpdateDao;
import com.shinian.util.Config;
import com.shinian.util.MessageRespVo;
import com.shinian.util.ResponseParamUtil;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.NoticeVo;
import com.shinian.vo.ServerInfo;
import com.shinian.vo.ServerListVo;
import com.shinian.vo.UpdateReqVo;
import com.shinian.vo.UpdateRespVo;
import com.shinian.vo.UpdateWay;
import com.shinian.vo.UpdateWhiteList;
import com.shinian.vo.VersionInfo;

@Service
public class UpdateService {
	@Autowired
	UpdateDao updateDao;
			
	private static Logger log = Logger.getLogger(UpdateService.class);
	public void onCheckUpdate(HttpServletRequest request, HttpServletResponse response,String jsonStr) throws ParseException, UnsupportedEncodingException{
		
		CommonReqVo crv = JSON.parseObject(jsonStr, CommonReqVo.class);
		UpdateReqVo urv = JSON.parseObject(crv.getData().toString(), UpdateReqVo.class);
		
		VersionInfo  vi = doUpdate(urv.getChannelId(),urv.getVersion());
		if(vi != null){//has new version
			vi.setHas(1);
		}
		else{ //has no version
			vi = new VersionInfo();
			vi.setHas(0);
			
			List<ServerListVo> slv = updateDao.getServerList(urv.getChannelId());
			vi.setServerList(slv);
		}
		
		vi.setStaticUrl(Config.STATIC_URL);
		vi.setAccountUrl(Config.ACCOUNT_URL);		
		
		MessageRespVo mrv = new MessageRespVo();
		mrv.setTs(crv.getTs());
		mrv.setCode(200);
		mrv.setData(vi);
		
		String jsonResp = JSON.toJSONString(mrv);
		ResponseParamUtil.writeJsonMessage(response,jsonResp);
	}
	
	public void onGetServerList(HttpServletRequest request, HttpServletResponse response,String jsonStr) throws ParseException, UnsupportedEncodingException{
		
		CommonReqVo crv = JSON.parseObject(jsonStr, CommonReqVo.class);
		UpdateReqVo urv = JSON.parseObject(crv.getData().toString(), UpdateReqVo.class);
		
		/*if(urv.getChannelId() == -1){
			ServerInfo  si = new ServerInfo();
			
			si.setStaticUrl("http://update.91fifa.com:8002/");
			si.setAccountUrl("http://account.91fifa.com/");
			
			List<ServerListVo> slv = updateDao.getServerListMerge();
			si.setServerList(slv);
			
			MessageRespVo mrv = new MessageRespVo();
			mrv.setTs(crv.getTs());
			mrv.setData(si);
			mrv.setCode(200);
			
			String jsonResp = JSON.toJSONString(mrv);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}*/
		
		ServerInfo  si = new ServerInfo();
			
		int isAudit = 0;
		UpdateWhiteList uwl = updateDao.getServerWhiteList(urv.getChannelId());
		if(uwl != null){
			int found = 0;
			
			String str = uwl.getUids();
			String[] uidArray = str.split(",");
			
			String targetUid = urv.getUid().split("-")[1];
			for(int i=0; i<uidArray.length; i++){
				if(uidArray[i].equals(targetUid)){
					found = 1;
					break;
				}
			}
			
			if(found == 1){
				List<ServerListVo> slv = updateDao.getServerListAll(urv.getChannelId());
				si.setServerList(slv);
				isAudit = 1;
			}
			else{
				isAudit = getServerList(si,urv.getVersion(),urv.getChannelId());
			}
		}
		else{
			isAudit = getServerList(si,urv.getVersion(),urv.getChannelId());
		}		
		
		si.setStaticUrl(Config.STATIC_URL);
		si.setAccountUrl(Config.ACCOUNT_URL);
				
		MessageRespVo mrv = new MessageRespVo();
		mrv.setTs(crv.getTs());
		mrv.setData(si);
		
		if(isAudit == 1){
			mrv.setCode(200);
		}
		else{
			/*if(urv.getChannelId() == 100){
				mrv.setCode(0);
				mrv.setMsg("由于同步推渠道正在对游戏新版本进行审核，预计将于2月4日12:00结束，因此我们决定针对同步推的用户，会在原有更新补偿的基础上，再额外补偿600钻石。客服QQ：2851982702");
			}
			else{
				mrv.setCode(200);
			}*/
			mrv.setCode(200);
		}
		
		String jsonResp = JSON.toJSONString(mrv);
		ResponseParamUtil.writeJsonMessage(response,jsonResp);
	}

	public void onGetUpdateInfo(HttpServletRequest request, HttpServletResponse response,String jsonStr){
		CommonReqVo crv = JSON.parseObject(jsonStr, CommonReqVo.class);
		UpdateReqVo urv = JSON.parseObject(crv.getData().toString(), UpdateReqVo.class);
		
		
		UpdateRespVo respVo = new UpdateRespVo();
		
		UpdateWay uw = updateDao.onGetUpdateWay(urv.getChannelId());				
		if(uw == null){
			respVo.setWay(0);
			respVo.setWayDesc("");
		}
		else{
			respVo.setWay(uw.getWay());
			respVo.setWayDesc(uw.getUpdateDesc());
		}
		
		int num = getVersionNum(urv.getVersion());
		int isInAudit = updateDao.isInAudit(urv.getChannelId(), num);
		if(isInAudit > 0){ //version is in audit status
			respVo.setServerStatus(1);
			respVo.setNoticeDesc("");
			respVo.setHas(0);
			
			MessageRespVo mrv = new MessageRespVo();
			mrv.setTs(crv.getTs());
			mrv.setCode(200);
			mrv.setData(respVo);
			
			String jsonResp = JSON.toJSONString(mrv);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
			return;
		}
		else{
			//get server status according to version and channel
			int yes = updateDao.getServerCount(urv.getChannelId(),num); 
			if(yes > 0){
				int s = updateDao.getServerStatus(urv.getChannelId(), num);
				if(s == 0){//server does not allow to run this version
					respVo.setHas(0);
					respVo.setServerStatus(0);
					respVo.setNoticeDesc("您的版本和服务器不匹配。请联系我们的官方客服QQ：2851982702");
					
					MessageRespVo mrv = new MessageRespVo();
					mrv.setTs(crv.getTs());
					mrv.setCode(200);
					mrv.setData(respVo);
					
					String jsonResp = JSON.toJSONString(mrv);
					ResponseParamUtil.writeJsonMessage(response,jsonResp);
					return;
				}
			}
			
			NoticeVo nv = updateDao.getNotice(urv.getChannelId());
			if(nv != null){	
				if(nv.getStatus() == 0){//server down
					boolean wl = isUserInWhiteList(urv.getUid(),urv.getChannelId());
					if(wl == false){
						respVo.setServerStatus(0);
						respVo.setNoticeDesc(nv.getNoticeDesc());
						respVo.setHas(0);
						
						MessageRespVo mrv = new MessageRespVo();
						mrv.setTs(crv.getTs());
						mrv.setCode(200);
						mrv.setData(respVo);
						
						String jsonResp = JSON.toJSONString(mrv);
						ResponseParamUtil.writeJsonMessage(response,jsonResp);
						return;
					}
				}
			}
		
			respVo.setServerStatus(1);
			respVo.setNoticeDesc("");
			
			VersionInfo  vi = doUpdate(urv.getChannelId(),urv.getVersion());
			if(vi != null){//has new version
				//check white list
				boolean enabled = isWhiteListEndabled(urv.getChannelId());
				if(enabled){
					boolean wl = isUserInWhiteList(urv.getUid(),urv.getChannelId());
					if(wl){
						respVo.setHas(1);
					}
					else{
						respVo.setHas(0);
					}
				}
				else{
					respVo.setHas(1);
				}
			}
			else{
				respVo.setHas(0);
			}
		
			MessageRespVo mrv = new MessageRespVo();
			mrv.setTs(crv.getTs());
			mrv.setCode(200);
			mrv.setData(respVo);
			
			String jsonResp = JSON.toJSONString(mrv);
			ResponseParamUtil.writeJsonMessage(response,jsonResp);
		}
		/*NoticeVo nv = updateDao.getNotice(urv.getChannelId());
		if(nv != null){
			respVo.setServerStatus(0);
			respVo.setNoticeDesc(nv.getNoticeDesc());
			
			if(nv.getStatus() == 0){//server down
				UpdateWhiteList uwl = updateDao.getUpdateWhiteList(urv.getChannelId());
				if(uwl != null){
					int found = 0;
					
					String str = uwl.getUids();
					String[] uidArray = str.split(",");
					
					String targetUid = urv.getUid().split("-")[1];
					for(int i=0; i<uidArray.length; i++){
						if(uidArray[i].equals(targetUid)){
							found = 1;
							break;
						}
					}
					
					if(found == 1){
						respVo.setServerStatus(1);//server is running
					}
				}
			}
		}
		else{
			respVo.setServerStatus(1);
			respVo.setNoticeDesc("");
		}
		
		VersionInfo  vi = doUpdate(urv.getChannelId(),urv.getVersion());
		if(vi != null){//has new version
			//check white list
			UpdateWhiteList uwl = updateDao.getUpdateWhiteList(urv.getChannelId());
			if(uwl != null){
				int found = 0;
				
				String str = uwl.getUids();
				String[] uidArray = str.split(",");
				
				String targetUid = urv.getUid().split("-")[1];
				for(int i=0; i<uidArray.length; i++){
					if(uidArray[i].equals(targetUid)){
						found = 1;
						break;
					}
				}
				
				if(found == 1){
					respVo.setHas(1);
				}
				else{
					respVo.setHas(0);
				}
			}
			else{
				respVo.setHas(1);
			}
		}
		else{ //has no version
			respVo.setHas(0);
			
			int num = getVersionNum(urv.getVersion());
			int yes = updateDao.getServerCount(urv.getChannelId(),num); 
			if(yes > 0){
				int s = updateDao.getServerStatus(urv.getChannelId(), num);
				if(s == 0){
					respVo.setServerStatus(0);
					respVo.setNoticeDesc("您的版本和服务器版本不匹配。请联系我们的官方客服QQ：2851982702");
				}
			}
		}
		
		MessageRespVo mrv = new MessageRespVo();
		mrv.setTs(crv.getTs());
		mrv.setCode(200);
		mrv.setData(respVo);
		
		String jsonResp = JSON.toJSONString(mrv);
		ResponseParamUtil.writeJsonMessage(response,jsonResp);*/
	}
	
	private VersionInfo doUpdate(int channelId,String version){
		log.info("doUpdate:"+channelId + "," +version);
		int num = getVersionNum(version);		
		return updateDao.getVersionInfo(channelId,num);		
	}
	
	private int getVersionNum(String version){
		String[] verNum = version.split("\\.");
		int num = Integer.parseInt(verNum[0]) * 1000 + Integer.parseInt(verNum[1]) * 100 + Integer.parseInt(verNum[2]);
		return num;
		
	}
	
	private int getServerList(ServerInfo si,String version,int channelId){
		int num = getVersionNum(version);
		int yes = updateDao.isInAudit(channelId,num); //如果配置了最新版本为审核版本
		if( yes > 0){
			List<ServerListVo> slv = new ArrayList<ServerListVo>();
			
			ServerListVo as = updateDao.getAuditServer(channelId);
			as.setFlag(1);
			slv.add(as);
			si.setServerList(slv);
			return 1; //audit
		}
		else{
			List<ServerListVo> slv = updateDao.getServerList(channelId);
			si.setServerList(slv);
			return 0; //normal
		}
	}
	
	private boolean isWhiteListEndabled(int channelId){
		UpdateWhiteList uwl = updateDao.getUpdateWhiteList(channelId);
		if(uwl != null){
			return true;
		}
		
		return false;
	}
	
	private boolean isUserInWhiteList(String uid, int channelId){
		boolean result = false;
		
		UpdateWhiteList uwl = updateDao.getUpdateWhiteList(channelId);
		if(uwl != null){
			int found = 0;
			
			String str = uwl.getUids();
			String[] uidArray = str.split(",");
			
			String targetUid = uid.split("-")[1];
			for(int i=0; i<uidArray.length; i++){
				if(uidArray[i].equals(targetUid)){
					found = 1;
					break;
				}
			}
			
			if(found == 1){
				result = true;
			}
		}
		
		return result;
	}
}
