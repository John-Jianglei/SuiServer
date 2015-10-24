package com.shinian.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.BaseDaoJdbcImpl;
import com.shinian.vo.AccInfo;
import com.shinian.vo.AccInfoEmail;
import com.shinian.vo.RegisterReqVo;

@Repository
public class AccountDao extends BaseDaoJdbcImpl<RegisterReqVo, Integer> {
	public AccountDao() {
		super("");
	}
	@Autowired
	AccTokenDao accTokenDao;
	public int register(final String email, final String password,final int channel){
		final String sql = "insert into acc_info(username,password,reg_date,channel) values(?,MD5(?),?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date now = new Date();
                
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setString(3, sdf.format(now));
                ps.setInt(4, channel);
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	public AccInfo isUserExist(final String email, final String password){
		final String sql = "select uid from acc_info where username = ? and password = MD5(?)";
		List<AccInfo> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{email,password});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public AccInfo isUserNoExist(final String email){
		final String sql = "select uid,channel from acc_info where username = ? ";
		List<AccInfo> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{email});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public int updateBindMail( final String bindingMail ,final String username )
	{
		final String sql = " update acc_info set bind_email = ? where username = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{bindingMail,username});
	}
	public int updatePwd( final String password ,final String username )
	{
		final String sql = " update acc_info set password = MD5(?) where username = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{password,username});
	}
	public AccInfo getAccInfo(final String username ){
		final String sql = "select * from acc_info where username = ? ";
		List<AccInfo> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{username});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public AccInfo isEmailNoExist(final String email,final int uid ){
		final String sql = "select uid from acc_info where bind_email = ? and uid != ? ";
		List<AccInfo> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{email,uid});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public AccInfo isUserNoExistUid(final int uid){
		final String sql = "select uid from acc_info where uid = ? ";
		List<AccInfo> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{uid});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public AccInfo getAccInfoById(final int uid){
		final String sql = "select bind_email,uid from acc_info where uid = ? ";
		List<AccInfo> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{uid});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	public List<AccInfo> getAccInfoList(final String time ){
		final String sql = "select uid,channel from acc_info where reg_date = ? ";
		return  this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfo.class),
				new Object[]{time});
		
	}
	public int updateBindMail( final String bindingMail ,final int uid )
	{
		final String sql = " update acc_info set bind_email = ? where uid = ? ";
		return this.getJdbcTemplate().update(sql, new Object[]{bindingMail,uid});
	}
	/*********************************** acc_info_email ************************************/
	
	public int insertEmail(final int uid,final int emailStatus,final String emailCode,final String validTime){
		final String sql = "insert into acc_info_email(`uid`,email_status,email_code,valid_time) values(?,?,?,?)";
		this.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, uid);
                ps.setInt(2, emailStatus);
                ps.setString(3, emailCode);
                ps.setString(4, validTime);
                return ps;
            }					
        });
    
        return 0;
	}
	
	public int updateEmail(final AccInfoEmail vo )
	{
		final String sql = " update  acc_info_email set email_code = ? ,  email_status = ? ,valid_time = ? where uid = ? ";
		return getJdbcTemplate().update(sql,new Object[]{vo.getEmailCode(),vo.getEmailStatus(),vo.getValidTime(),vo.getUid()});
	}
	public AccInfoEmail getEmailById(final int uid ){
		final String sql = "select uid,email_status,email_code,valid_time from acc_info_email where uid = ? ";
		List<AccInfoEmail> aiList = this.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AccInfoEmail.class),
				new Object[]{uid});
		
		if(aiList != null && aiList.size() > 0){
			return aiList.get(0);
		}
		
		return null;
	}
	
}
