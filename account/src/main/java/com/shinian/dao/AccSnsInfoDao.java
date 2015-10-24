package com.shinian.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shinian.dao.impl.BaseDaoJdbcImpl;
import com.shinian.util.ParamUtil;
import com.shinian.vo.AccInfo;
import com.shinian.vo.AccSnsInfoVo;
import com.shinian.vo.RegisterReqVo;

@Repository
public class AccSnsInfoDao extends BaseDaoJdbcImpl<RegisterReqVo, Integer> {
	public AccSnsInfoDao() {
		super("");
	}
	@Autowired
	AccountDao accountDao;
	
	public int insert(final int uid ,final String snsId, final int channelId,final String name){
		final String sql = "insert into acc_sns_info(uid,sns_id,channel_id,`name`) values(?,?,?,?)";
		this.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, uid);
                ps.setString(2, snsId);
                ps.setInt(3, channelId);
                ps.setString(4, name);
                
                return ps;
            }					
        });
    
        return 0;
	}
	public int update(final int uid, final String name )
	{
		final String sql = " update acc_sns_info set `name` = ? where uid = ?  ";
		return this.getJdbcTemplate().update(sql, new Object[]{name,uid});
	}
	public AccSnsInfoVo getAccSnsInfoById( final String snsId,final int channelId)
	{
		final String sql = "select uid,sns_id,channel_id,`name` from acc_sns_info where  sns_id = ? and channel_id = ?";
		List<AccSnsInfoVo> list = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccSnsInfoVo.class),new Object[]{snsId,channelId});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;
	}
	@Transactional
	public AccSnsInfoVo isAccSnsInfoExist(final String snsId,final String name,final int channelId){
		AccSnsInfoVo accSns = this.getAccSnsInfoById(snsId, channelId);
		if( null == accSns )
		{
			String email = this.getEmail( snsId,channelId );
			int uid = accountDao.register(email, ParamUtil.passWord,channelId);
			
			this.insert(uid, snsId, channelId, name);
			accSns = new AccSnsInfoVo();
			accSns.setChannelId(channelId);
			accSns.setName(name);
			accSns.setSnsId(snsId);
			accSns.setUid(uid);
		}else{
			accSns.setName(name);
			this.update(accSns.getUid(), name);
		}
		return accSns;
	}
	public String getEmail( final String snsId ,final int channelId )
	{
		String email = "";
		while( true )
		{
			long time = System.currentTimeMillis();
			if( channelId == ParamUtil.channelId360 )
			{
				email = time+"@360.com";
			}else if( channelId == ParamUtil.channelIdqq )
			{
				email = time+"@qq.com";
			}else if( channelId == ParamUtil.channelIdhw )
			{
				email = time+"@hw.com";
			}else if( channelId == ParamUtil.channelIduc )
			{
				email = time+"@uc.com";
			}else if( channelId == ParamUtil.channelIdxiaomi )
			{
				email = time+"@xiaomi.com";
			}else if( channelId == ParamUtil.channelIdbaidu )
			{
				email = time+"@baidu.com";
			}else if( channelId == ParamUtil.channelIdtongbu )
			{
				email = time+"@tongbu.com";
			}else if( channelId == ParamUtil.channelIdOppo )
			{
				email = time+"@oppo.com";
			}
			
			AccInfo accinfo = accountDao.isUserNoExist(email);
			if( null == accinfo )
			{
				break;
			}
			
		}
		
		return email;
	}
	
}
