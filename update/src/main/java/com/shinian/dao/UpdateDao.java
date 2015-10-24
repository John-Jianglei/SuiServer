package com.shinian.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.BaseDaoJdbcImpl;
import com.shinian.util.Constants;
import com.shinian.vo.NoticeVo;
import com.shinian.vo.ServerListVo;
import com.shinian.vo.UpdateWay;
import com.shinian.vo.UpdateWhiteList;
import com.shinian.vo.VersionInfo;

@Repository
public class UpdateDao extends BaseDaoJdbcImpl<VersionInfo, Integer> {
	public UpdateDao() {
		super("");
	}
	
	public VersionInfo getVersionInfo(int channelId,int verNum){
		String sql = "select url from u_version where channel_id = ? and version_num > ? order by version_num desc";
		
		List<VersionInfo> uiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(
				VersionInfo.class),new Object[]{channelId,verNum});
		
		if(uiList != null && uiList.size() > 0){
			return uiList.get(0);
		}
		
		return null;
	}
	
	public List<ServerListVo> getServerList(int channelId){
		String sql = "";
		if(channelId == Constants.CHANNEL_ID_QQ){
			sql = "select server_no,`name`,`desc`,url,flag from u_server_list where ((channel_id = 2 and server_no = 1) " +
					" or (channel_id != 2 and channel_id != ? and server_no != 1) ) and flag != 99 and status = 1 order by server_no desc";
		}
		else if(channelId == Constants.CHANNEL_ID_IOS_OFFICIAL){
			sql = "select server_no,`name`,`desc`,url,flag from u_server_list where channel_id = ? and flag != 99 and status = 1 order by server_no desc";
		}
		else{//混服
			sql = "select server_no,`name`,`desc`,url,flag from u_server_list where ((channel_id = 2 and server_no != 1) " +
					" or (channel_id != 2 and channel_id != ?) ) and flag != 99 and status = 1 order by server_no desc";
		}
		
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ServerListVo.class),
				new Object[]{Constants.CHANNEL_ID_IOS_OFFICIAL});
	}
	
	public ServerListVo getAuditServer(int channelId){
		String sql = "select server_no,`name`,`desc`,url,flag from u_server_list where channel_id=? and " +
					" flag = 99 and status=1 order by server_no desc";
			
		List<ServerListVo>  slvList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					ServerListVo.class),new Object[]{channelId});
		if(slvList != null && slvList.size() > 0){
			return slvList.get(0);
		}
		
		return null;
	}
	
	//including servers whose status is 0: just for internal test
	public List<ServerListVo> getServerListAll(int channelId){
		String sql = "";
		if(channelId == Constants.CHANNEL_ID_QQ){//qq
			sql = "select server_no,`name`,`desc`,url,flag from u_server_list where ((channel_id = 2 and server_no = 1) " +
					" or (channel_id != 2 and channel_id != ? and server_no != 1) ) and flag != 99 order by server_no desc";
		}
		else if(channelId == Constants.CHANNEL_ID_IOS_OFFICIAL){
			sql = "select server_no,`name`,`desc`,url,flag from u_server_list where channel_id = ? and flag != 99 order by server_no desc";
		}
		else{//混服
			sql = "select server_no,`name`,`desc`,url,flag from u_server_list where ((channel_id = 2 and server_no != 1) " +
					" or (channel_id != 2 and channel_id != ?) ) and flag != 99 order by server_no desc";			
		}
		
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ServerListVo.class),
				new Object[]{Constants.CHANNEL_ID_IOS_OFFICIAL});
	}
	
	public UpdateWay onGetUpdateWay(int channelId){
		String sql = "select way,update_desc from u_update_way where channel_id = ?";
		List<UpdateWay> uwList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
				UpdateWay.class),new Object[]{channelId});
		
		if(uwList != null && uwList.size() > 0){
			return uwList.get(0);
		}
		
		return null;
	}

	public NoticeVo getNotice(int channelId){
		String sql = "";
		List<NoticeVo> nvList = new ArrayList<NoticeVo>();
		
		sql = "select `notice_desc` from u_notice where channel_id = ? and status = 1";
		nvList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					NoticeVo.class),new Object[]{channelId});
		
		if(nvList != null && nvList.size() > 0){
			return nvList.get(0);
		}
		
		return null;
	}
	
	public UpdateWhiteList getUpdateWhiteList(int channelId){
		String sql = "";
		List<UpdateWhiteList> uwlList = new ArrayList<UpdateWhiteList>();
		
		if(channelId == Constants.CHANNEL_ID_QQ){//QQ
			sql = "select `channel_id`,uids from u_update_whitelist where channel_id = ? and status = 1";
			uwlList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					UpdateWhiteList.class),new Object[]{Constants.CHANNEL_ID_QQ});
		}
		else if(channelId == Constants.CHANNEL_ID_IOS_OFFICIAL){//ios app store
			sql = "select `channel_id`,uids from u_update_whitelist where channel_id = ? and status = 1";
			uwlList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					UpdateWhiteList.class),new Object[]{Constants.CHANNEL_ID_IOS_OFFICIAL});
		}
		else{
			sql = "select `channel_id`,uids from u_update_whitelist where channel_id != ? and channel_id != ? and status = 1";
			uwlList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					UpdateWhiteList.class),new Object[]{Constants.CHANNEL_ID_QQ,Constants.CHANNEL_ID_IOS_OFFICIAL});
		}
		
		
		if(uwlList != null && uwlList.size() > 0){
			return uwlList.get(0);
		}
		
		return null;
	}
	
	public UpdateWhiteList getServerWhiteList(int channelId){
		String sql = "";
		List<UpdateWhiteList> uwlList = new ArrayList<UpdateWhiteList>();
		
		if(channelId == Constants.CHANNEL_ID_QQ){//QQ
			sql = "select `channel_id`,uids from u_server_whitelist where channel_id = ? and status = 1";
			uwlList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					UpdateWhiteList.class),new Object[]{Constants.CHANNEL_ID_QQ});
		}
		else if(channelId == Constants.CHANNEL_ID_IOS_OFFICIAL){//ios app store
			sql = "select `channel_id`,uids from u_server_whitelist where channel_id = ? and status = 1";
			uwlList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					UpdateWhiteList.class),new Object[]{Constants.CHANNEL_ID_IOS_OFFICIAL});
		}
		else{
			sql = "select `channel_id`,uids from u_server_whitelist where channel_id != ? and channel_id != ? and status = 1";
			uwlList = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					UpdateWhiteList.class),new Object[]{Constants.CHANNEL_ID_QQ,Constants.CHANNEL_ID_IOS_OFFICIAL});
		}
		
		
		if(uwlList != null && uwlList.size() > 0){
			return uwlList.get(0);
		}
		
		return null;
	}
	
	public int isInAudit(int channelId,int versionNum){
		String sql = "select count(*) from u_update_audit where channel_id = ? and version = ? and status = 1";
		return this.getJdbcTemplate().queryForInt(sql, new Object[]{channelId,versionNum});
	}
	
	public int getServerStatus(int channelId, int versionNum){
		String sql = "select status from u_server_status where channel_id = ? and version = ?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[]{channelId,versionNum});
	}
	
	public int getServerCount(int channelId,int versionNum){
		String sql = "select count(*) from u_server_status where channel_id = ? and version = ?";
		return this.getJdbcTemplate().queryForInt(sql, new Object[]{channelId,versionNum});
	}
	
	public List<ServerListVo> getServerListMerge(){
		String sql = "select server_no,`name`,`desc`,url,flag from u_server_list where status = 0";
		return this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					ServerListVo.class));
	}
}