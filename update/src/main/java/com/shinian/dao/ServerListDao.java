package com.shinian.dao;



import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.BaseDaoJdbcImpl;
import com.shinian.util.Constants;
import com.shinian.vo.ServerListVo;

@Repository
public class ServerListDao extends BaseDaoJdbcImpl<ServerListVo, Integer> {
	public ServerListDao() {
		super("");
	}
	
	public String getServerUrl(int channelId,int serverNo){
		List<ServerListVo> slv = new ArrayList<ServerListVo>();
		
		String sql = "";
		if(channelId == Constants.CHANNEL_ID_QQ){//qq only have 1 server
			sql = "select `url` from u_server_list where channel_id = 2 and server_no = 1";
			slv = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ServerListVo.class));
		}
		else if(channelId == Constants.CHANNEL_ID_IOS_OFFICIAL){
			sql = "select `url` from u_server_list where channel_id = ? and server_no = ?";
			slv = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(ServerListVo.class),
					new Object[]{channelId,serverNo});
		}
		else{//混服
			sql = "select `url` from u_server_list where channel_id != 2 and channel_id != ? and server_no = ?";
			slv = this.getJdbcTemplate().query(sql, ParameterizedBeanPropertyRowMapper.newInstance(
					ServerListVo.class),new Object[]{Constants.CHANNEL_ID_IOS_OFFICIAL,serverNo});
		}
		
		if(slv.size() > 0){
			return slv.get(0).getUrl();
		}
		
		return "";
	}
}
