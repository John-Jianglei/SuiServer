package com.shinian.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;


@Repository
public class JingjiDao {

	public int getAmount(){
		
		int id = 1;
		final String sql = " select `amount` from game_jingji_amount where id=?";
		int amount = WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{id});		
		
		return amount;	
	}
	
	public int setAmount(int amount){
		
		int id = 1;
		String sql = "update game_jingji_amount set `amount` = ? where id = ?";
		int row = WebConstant.gameJdbc.getJdbcTemplate().update(sql, amount, id);		

		return row;
	}
	
}
