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
import com.shinian.vo.GamePassVo;
import com.shinian.vo.PassLogVo;
import com.shinian.vo.PassVo;

@Repository
public class PassDao {

	//pass insert
	public int insertPass(final int id, final int passId, final String uid)
	{
		String tempSql = "";
		switch( passId ){
		case 1:
			tempSql = "insert into game_pass values(?, ?, ?, '0', '0', '0', '0', '0', '0', '10111', '0', '10112', " +
					"'0', '10113', '0', '10121', '0', '10122', '0', '10123', '0', '10131', '0', '10132', '0', '10133', '0', " +
					"'10141', '0', '10142', '0', '10143', '0', '10151', '0', '10152', '0', '10153', '0', '10161', '0', '10162', " +
					"'0', '10163', '0', '20111', '0', '20112', '0', '20113', '0', '20121', '0', '20122', '0', '20123', '0', " +
					"'20131', '0', '20132', '0', '20133', '0', '20141', '0', '20142', '0', '20143', '0', '20151', '0', '20152', " +
					"'0', '20153', '0', '20161', '0', '20162', '0', '20163', '0', '30113', '0', '30123', '0', '30133', '0', " +
					"'30143', '0', '30153', '0', '30163', '0')";
			break;
		case 2:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10211', '0', '10212', " +
					"'0', '10213', '0', '10221', '0', '10222', '0', '10223', '0', '10231', '0', '10232', '0', '10233', '0', " +
					"'10241', '0', '10242', '0', '10243', '0', '10251', '0', '10252', '0', '10253', '0', '10261', '0', '10262'," +
					"'0', '10263', '0', '20211', '0', '20212', '0', '20213', '0', '20221', '0', '20222', '0', '20223', '0', " +
					"'20231', '0', '20232', '0', '20233', '0', '20241', '0', '20242', '0', '20243', '0', '20251', '0', '20252'," +
					" '0', '20253', '0', '20261', '0', '20262', '0', '20263', '0', '30213', '0', '30223', '0', '30233', '0', " +
					"'30243', '0', '30253', '0', '30263', '0')";
			break;
		case 3:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10311', '0', '10312', '0', '10313', '0', '10321', '0', '10322', '0', '10323', '0', '10331', '0', '10332', '0', '10333', '0', '10341', '0', '10342', '0', '10343', '0', '10351', '0', '10352', '0', '10353', '0', '10361', '0', '10362', '0', '10363', '0', '20311', '0', '20312', '0', '20313', '0', '20321', '0', '20322', '0', '20323', '0', '20331', '0', '20332', '0', '20333', '0', '20341', '0', '20342', '0', '20343', '0', '20351', '0', '20352', '0', '20353', '0', '20361', '0', '20362', '0', '20363', '0', '30313', '0', '30323', '0', '30333', '0', '30343', '0', '30353', '0', '30363', '0')";
			break;
		case 4:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10411', '0', '10412', '0', '10413', '0', '10421', '0', '10422', '0', '10423', '0', '10431', '0', '10432', '0', '10433', '0', '10441', '0', '10442', '0', '10443', '0', '10451', '0', '10452', '0', '10453', '0', '10461', '0', '10462', '0', '10463', '0', '20411', '0', '20412', '0', '20413', '0', '20421', '0', '20422', '0', '20423', '0', '20431', '0', '20432', '0', '20433', '0', '20441', '0', '20442', '0', '20443', '0', '20451', '0', '20452', '0', '20453', '0', '20461', '0', '20462', '0', '20463', '0', '30413', '0', '30423', '0', '30433', '0', '30443', '0', '30453', '0', '30463', '0')";
			break;
		case 5:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10511', '0', '10512', '0', '10513', '0', '10521', '0', '10522', '0', '10523', '0', '10531', '0', '10532', '0', '10533', '0', '10541', '0', '10542', '0', '10543', '0', '10551', '0', '10552', '0', '10553', '0', '10561', '0', '10562', '0', '10563', '0', '20511', '0', '20512', '0', '20513', '0', '20521', '0', '20522', '0', '20523', '0', '20531', '0', '20532', '0', '20533', '0', '20541', '0', '20542', '0', '20543', '0', '20551', '0', '20552', '0', '20553', '0', '20561', '0', '20562', '0', '20563', '0', '30513', '0', '30523', '0', '30533', '0', '30543', '0', '30553', '0', '30563', '0')";
			break;
		case 6:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10611', '0', '10612', '0', '10613', '0', '10621', '0', '10622', '0', '10623', '0', '10631', '0', '10632', '0', '10633', '0', '10641', '0', '10642', '0', '10643', '0', '10651', '0', '10652', '0', '10653', '0', '10661', '0', '10662', '0', '10663', '0', '20611', '0', '20612', '0', '20613', '0', '20621', '0', '20622', '0', '20623', '0', '20631', '0', '20632', '0', '20633', '0', '20641', '0', '20642', '0', '20643', '0', '20651', '0', '20652', '0', '20653', '0', '20661', '0', '20662', '0', '20663', '0', '30613', '0', '30623', '0', '30633', '0', '30643', '0', '30653', '0', '30663', '0')";
			break;
		case 7:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10711', '0', '10712', '0', '10713', '0', '10721', '0', '10722', '0', '10723', '0', '10731', '0', '10732', '0', '10733', '0', '10741', '0', '10742', '0', '10743', '0', '10751', '0', '10752', '0', '10753', '0', '10761', '0', '10762', '0', '10763', '0', '20711', '0', '20712', '0', '20713', '0', '20721', '0', '20722', '0', '20723', '0', '20731', '0', '20732', '0', '20733', '0', '20741', '0', '20742', '0', '20743', '0', '20751', '0', '20752', '0', '20753', '0', '20761', '0', '20762', '0', '20763', '0', '30713', '0', '30723', '0', '30733', '0', '30743', '0', '30753', '0', '30763', '0')";
			break;
		case 8:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10811', '0', '10812', '0', '10813', '0', '10821', '0', '10822', '0', '10823', '0', '10831', '0', '10832', '0', '10833', '0', '10841', '0', '10842', '0', '10843', '0', '10851', '0', '10852', '0', '10853', '0', '10861', '0', '10862', '0', '10863', '0', '20811', '0', '20812', '0', '20813', '0', '20821', '0', '20822', '0', '20823', '0', '20831', '0', '20832', '0', '20833', '0', '20841', '0', '20842', '0', '20843', '0', '20851', '0', '20852', '0', '20853', '0', '20861', '0', '20862', '0', '20863', '0', '30813', '0', '30823', '0', '30833', '0', '30843', '0', '30853', '0', '30863', '0')";
			break;
		case 9:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '10911', '0', '10912', '0', '10913', '0', '10921', '0', '10922', '0', '10923', '0', '10931', '0', '10932', '0', '10933', '0', '10941', '0', '10942', '0', '10943', '0', '10951', '0', '10952', '0', '10953', '0', '10961', '0', '10962', '0', '10963', '0', '20911', '0', '20912', '0', '20913', '0', '20921', '0', '20922', '0', '20923', '0', '20931', '0', '20932', '0', '20933', '0', '20941', '0', '20942', '0', '20943', '0', '20951', '0', '20952', '0', '20953', '0', '20961', '0', '20962', '0', '20963', '0', '30913', '0', '30923', '0', '30933', '0', '30943', '0', '30953', '0', '30963', '0')";
			break;
		case 10:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '11011', '0', '11012', '0', '11013', '0', '11021', '0', '11022', '0', '11023', '0', '11031', '0', '11032', '0', '11033', '0', '11041', '0', '11042', '0', '11043', '0', '11051', '0', '11052', '0', '11053', '0', '11061', '0', '11062', '0', '11063', '0', '21011', '0', '21012', '0', '21013', '0', '21021', '0', '21022', '0', '21023', '0', '21031', '0', '21032', '0', '21033', '0', '21041', '0', '21042', '0', '21043', '0', '21051', '0', '21052', '0', '21053', '0', '21061', '0', '21062', '0', '21063', '0', '31013', '0', '31023', '0', '31033', '0', '31043', '0', '31053', '0', '31063', '0')";
			break;
		case 11:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '11111', '0', '11112', '0', '11113', '0', '11121', '0', '11122', '0', '11123', '0', '11131', '0', '11132', '0', '11133', '0', '11141', '0', '11142', '0', '11143', '0', '11151', '0', '11152', '0', '11153', '0', '11161', '0', '11162', '0', '11163', '0', '21111', '0', '21112', '0', '21113', '0', '21121', '0', '21122', '0', '21123', '0', '21131', '0', '21132', '0', '21133', '0', '21141', '0', '21142', '0', '21143', '0', '21151', '0', '21152', '0', '21153', '0', '21161', '0', '21162', '0', '21163', '0', '31113', '0', '31123', '0', '31133', '0', '31143', '0', '31153', '0', '31163', '0')";
			break;
		case 12:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '11211', '0', '11212', '0', '11213', '0', '11221', '0', '11222', '0', '11223', '0', '11231', '0', '11232', '0', '11233', '0', '11241', '0', '11242', '0', '11243', '0', '11251', '0', '11252', '0', '11253', '0', '11261', '0', '11262', '0', '11263', '0', '21211', '0', '21212', '0', '21213', '0', '21221', '0', '21222', '0', '21223', '0', '21231', '0', '21232', '0', '21233', '0', '21241', '0', '21242', '0', '21243', '0', '21251', '0', '21252', '0', '21253', '0', '21261', '0', '21262', '0', '21263', '0', '31213', '0', '31223', '0', '31233', '0', '31243', '0', '31253', '0', '31263', '0')";
			break;
		case 13:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '11311', '0', '11312', '0', '11313', '0', '11321', '0', '11322', '0', '11323', '0', '11331', '0', '11332', '0', '11333', '0', '11341', '0', '11342', '0', '11343', '0', '11351', '0', '11352', '0', '11353', '0', '11361', '0', '11362', '0', '11363', '0', '21311', '0', '21312', '0', '21313', '0', '21321', '0', '21322', '0', '21323', '0', '21331', '0', '21332', '0', '21333', '0', '21341', '0', '21342', '0', '21343', '0', '21351', '0', '21352', '0', '21353', '0', '21361', '0', '21362', '0', '21363', '0', '31313', '0', '31323', '0', '31333', '0', '31343', '0', '31353', '0', '31363', '0')";
			break;
		case 14:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '11411', '0', '11412', '0', '11413', '0', '11421', '0', '11422', '0', '11423', '0', '11431', '0', '11432', '0', '11433', '0', '11441', '0', '11442', '0', '11443', '0', '11451', '0', '11452', '0', '11453', '0', '11461', '0', '11462', '0', '11463', '0', '21411', '0', '21412', '0', '21413', '0', '21421', '0', '21422', '0', '21423', '0', '21431', '0', '21432', '0', '21433', '0', '21441', '0', '21442', '0', '21443', '0', '21451', '0', '21452', '0', '21453', '0', '21461', '0', '21462', '0', '21463', '0', '31413', '0', '31423', '0', '31433', '0', '31443', '0', '31453', '0', '31463', '0')";
			break;
		case 15:
			tempSql = "insert into game_pass values (?, ?, ?, '0', '0', '0', '0', '0', '0', '11511', '0', '11512', '0', '11513', '0', '11521', '0', '11522', '0', '11523', '0', '11531', '0', '11532', '0', '11533', '0', '11541', '0', '11542', '0', '11543', '0', '11551', '0', '11552', '0', '11553', '0', '11561', '0', '11562', '0', '11563', '0', '21511', '0', '21512', '0', '21513', '0', '21521', '0', '21522', '0', '21523', '0', '21531', '0', '21532', '0', '0', '0', '21541', '0', '21542', '0', '21543', '0', '21551', '0', '21552', '0', '21553', '0', '21561', '0', '21562', '0', '21563', '0', '31513', '0', '31523', '0', '31533', '0', '31543', '0', '0', '0', '31563', '0')";
			break;
		}
		final String sql = tempSql;		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt( 1, id );
                ps.setInt( 2, passId );                	
                ps.setString( 3, uid ); 
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	public int getPassCount()
	{
		final String sql = " select count(*) from game_pass";
		int count = WebConstant.gameJdbc.getJdbcTemplate().queryForInt(sql,new Object[]{});
		
		return count;
	}
	
	private String[] getPrfixByBattleId(int battleId){
		
		String[] colName = { "ptStars", "pt", "pt" };
		int temp = battleId/10000;
		if( temp>3 || temp<1 ){
			return null;
		}
		switch( battleId/10000 ){
		case 1:
			colName[0] = "ptStars";
			colName[2] = "pt";
			break;
		case 2:
			colName[0] = "emStars";
			colName[2] = "em";
			break;
		case 3:
			colName[0] = "dyStars"; 
			colName[2] = "dy";
			break;				
		}
		
		temp = 3 * ( (battleId/10)%10-1 );
		temp += battleId % 10;
		
		colName[2] += Integer.toString(temp);
		colName[1] = colName[2];
		colName[2] += "Stars";
		colName[1] += "Id";
		
		return colName;
	}
	
	//pass get 
	public PassVo getPassByBattleId( int battleId, String uid )
	{
		String 	passStarsColName;
		String	battleIdName;
		String	battleStarsColName;
		
		String[] colName = getPrfixByBattleId(battleId);
		if( colName == null ){
			return null;
		}
		passStarsColName = colName[0];
		battleIdName = colName[1];
		battleStarsColName = colName[2];		
		
		final String sql = " select `uid`, `" + passStarsColName + "` as `passStars`, `"
				+ battleStarsColName + "` as `battleStars`, `" + battleIdName + "` as " +
				"`battle` from game_pass where `uid`=? and `" + battleIdName + "`=?";
		List<PassVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassVo.class),new Object[]{uid,battleId});
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//pass update 
	public int updatePassByBattleId( int passStars, int battleStars, int battleId, String uid )
	{
		String 	passStarsColName;
		String	battleIdName;
		String	battleStarsColName;
		
		String[] colName = getPrfixByBattleId(battleId);
		if( colName == null ){
			return 0;
		}
		passStarsColName = colName[0];
		battleIdName = colName[1];
		battleStarsColName = colName[2];
		
		final String sql = "update game_pass set `" + passStarsColName + "`=?, `" 
				+ battleStarsColName + "`=? where `uid`=? and `" + battleIdName + "`=?";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, passStars, battleStars, uid, battleId);		
	}
	
	//passLog get 
	public PassLogVo getPassLog(int battleId, String uid, String date)
	{
		final String sql = " select `id`, `count`,`date`,`uid`,`battleId` from game_pass_log " +
				"where battleId = ? and uid = ? and date=?";
		List<PassLogVo> list = WebConstant.gameJdbc.getJdbcTemplate().query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PassLogVo.class),new Object[]{battleId,uid,date});
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	
	//passLog insert 
	public int insertPassLog(final PassLogVo passLog)
	{
		final String sql = "insert into game_pass_log(`count`,`date`,`uid`,`battleId`) values(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		WebConstant.gameJdbc.getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException{
                PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, passLog.getCount()); 
                ps.setString(2, passLog.getDate()); 
                ps.setString(3, passLog.getUid());
                ps.setInt(4, passLog.getBattleId());
                
                return ps;
            }					
        }, keyHolder);
    
        return keyHolder.getKey().intValue();
	}
	
	//passLog update 
	public int updatePassLog( int count, String uid, int battleId )
	{
		final String sql = "update game_pass_log set `count`=? where `uid`=? and `battleId`=? ";
		return WebConstant.gameJdbc.getJdbcTemplate().update(sql, count, uid, battleId);		
	}	
	
}
