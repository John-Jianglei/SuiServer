package com.shinian.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.shinian.dao.impl.WebConstant;
import com.shinian.vo.ArmoryVo;
import com.shinian.vo.NewsVo;
import com.shinian.vo.NpcInfoVo;
import com.shinian.vo.PlayerInfoVo;
import com.shinian.vo.PlayerNewsTimeVo;
import com.shinian.vo.PropInfoVo;

@Repository
public class PlayerNewsDao {
	
	public int insert(final NewsVo news){
		final String sql = "insert into game_news_info(`category`, `title`, `uid`, `fromUid`, `fromName`, `content`, `annexCate`, `annexId`, `amount`, `status`, `updateTime`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1 , news.getCategory());
                ps.setString(2 , news.getTitle());
                ps.setString(3 , news.getUid());
                ps.setString(4 , news.getFromUid());
                ps.setString(5 , news.getFromName());
                ps.setString(6 , news.getContent());
                ps.setInt(7 , news.getAnnexCate());
                ps.setInt(8 , news.getAnnexId());
                ps.setInt(9 , news.getAmount());
                ps.setInt(10 , news.getStatus());

                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	public List<NewsVo> getUserNews(final String uid,final String newsTime)
	{
		final String sql = " select `id`, `category`, `title`, `uid`, `fromUid`, `fromName`, `content`, `annexCate`, `annexId`, `amount`, `updateTime`, `status` from  game_news_info  where `status` = 0 and `uid` = ? and  `category` = 1 and `updateTime` >= ? ";
		return WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NewsVo.class),new Object[]{uid});
	}
	
	public List<NewsVo> getUserNews(final String uid)
	{
		final String sql = " select `id`, `category`, `title`, `uid`, `fromUid`, `fromName`, `content`, `annexCate`, `annexId`, `amount`, `updateTime`, `status` from  game_news_info  where `status` = 0 and `uid` = ? and  `category` = 1 ";
		return WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(NewsVo.class),new Object[]{uid});
	}
	
	public int getUserNewsCount(final String uid,final String newsTime)
	{
		final String sql = " select count(1) from  game_news_info  where `status` = 0 and `uid` = ? and  `category` = 1 and `updateTime` >= ? ";
		return WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql, new Object[]{uid, newsTime});
	}
	
	public int getUserNewsCount(final String uid)
	{
		final String sql = " select count(1) from  game_news_info  where `status` = 0 and `uid` = ? and  `category` = 1 ";
		return WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql, new Object[]{uid});
	}
	
	public PlayerNewsTimeVo getPlayerNewsTimeById(String uid)
	{
		final String sql = " select `uid`, `newsTime` from game_player_news_time where uid = ?  ";
		List<PlayerNewsTimeVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PlayerNewsTimeVo.class),new Object[]{uid});
		if( null != list && list.size() > 0 )
		{
			return list.get(0);
		}
		return null;	
	}
	
	public void updatePlayerNewsTimeById(final String uid)
	{
		final String sql = "REPLACE INTO game_player_news_time SET uid = ?, `newsTime` = now()";
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, uid);
                return ps;
            }					
        });
    
        return ;
	}
	
	public int setPlayerNewsStatus(int id, int status)
	{
		String sql = "update game_news_info set `status` = ? where `id` = ?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, status, id);
	}
	
	public int getGlobalNewsCount(final int vip, final String newsTime)
	{
		final String sql = " select count(1) from  game_global_news_info  where `status` = 1 and `vip` < ? and `updateTime` >= ? ";
		return WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql, new Object[]{vip+1, newsTime});
	}
	
	public int dumpGlobalNewsToUser(PlayerInfoVo player, String newsTime)
	{
		String sql = "INSERT INTO game_news_info(`category`, `title`, `uid`, `fromUid`, `content`, `annexCate`, `annexId`, `amount`, `updateTime`) SELECT `category`, `title`, ?, `0-0`, `content`, `annexCate`, `annexId`, `amount`, now() FROM game_global_news_info where `vip` < ? and `status` = 1 and `updateTime` >= ? ";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, player.getUid(), player.getVip_Level()+1, newsTime);
	}
}
