/*
 * <pre>
 * Author: Wei Rongting
 * Package Name: com.mobimtech.ivp.dao.jdbcimpl
 * </pre>
 */
package com.shinian.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.shinian.dao.BaseDao;
import com.shinian.util.PageList;
import com.shinian.util.Reflections;

/**
 * DAO 接口实现类
 * 
 * <pre>
 * Class Name: BaseDaoJdbcImpl
 * Modifications:
 * Modifier Wei Rongting; 2012-5-21; Create new Class BaseDaoJdbcImpl.
 * </pre>
 */
public class BaseDaoJdbcImpl<T, ID extends Serializable> extends JdbcDaoSupport implements BaseDao<T, ID> {

	protected org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
	protected String tableName = ""; // 自定义关联数据表名称
	private Class<T> entityClass;

	@Autowired
	public void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	public BaseDaoJdbcImpl(String tableName) {
		entityClass = Reflections.getSuperClassGenricType(getClass());
		this.tableName = StringUtils.isNotBlank(tableName) ? tableName : entityClass.getSimpleName();
	}

	/**
	 * 执行sql语句
	 * 
	 * @param sql
	 *            完整的sql语句
	 */
	public void executeSql(String sql) {
		getJdbcTemplate().execute(sql);
	}

	/**
	 * 获取指定值对象，需要编写VO
	 * 
	 * @param id
	 *            vo的ID
	 * @return
	 */
	public T get(Serializable id) {
		List<T> list = getJdbcTemplate().query(String.format("select * from %s where id = ?", this.tableName), new Object[]{id},
				ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	/**
	 * 获取指定值对象，需要编写VO,如果表主键的ID的命名不是id，需要指定别名
	 * 
	 * @param idName
	 *            数据库表的主键ID的名字
	 * @param id
	 *            vo的ID
	 * @return
	 */
	public T get(String idName, Serializable id) {
		idName = org.apache.commons.lang3.StringUtils.isNotBlank(idName) ? idName : "id";
		List<T> list = getJdbcTemplate().query(
				String.format("select * from %s where %s = ?", this.tableName, idName), new Object[]{id},
				ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
		if (list != null && !list.isEmpty())
			return list.get(0);
		return null;
	}

	/**
	 * 查询所有的VO
	 * 
	 * @return
	 */
	public List<T> getAll() {
		List<T> list = getJdbcTemplate().query(String.format("select * from %s", this.tableName),
				ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
		return list;
	}

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 */
	public void remove(ID id) {
		this.getJdbcTemplate().update("delete from " + this.tableName + " where id = ?", new Object[]{id});
	}

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 */
	public void remove(String idName, Serializable id) {
		idName = org.apache.commons.lang3.StringUtils.isNotBlank(idName) ? idName : "id";
		this.getJdbcTemplate().update(String.format("delete from " + this.tableName + " where %s = ?", idName), new Object[]{id});
	}

	/**
	 * 根据一组ID删除记录
	 * 
	 * @param id
	 */
	public void removeIds(ID[] ids) {
		for (ID id : ids) {
			this.remove(id);
		}
	}

	/**
	 * 删除全部记录
	 */
	public void removeAll() {
		this.getJdbcTemplate().update("truncate table " + this.tableName);
	}

	/**
	 * 无条件分页查询
	 * 
	 * @param pageNo
	 *            起始页码
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 */
	public PageList getPageList(int pageNo, int pageSize) {
		String countString = "select count(*) from " + this.tableName;
		String queryString = "select * from " + this.tableName;
		return getPageList(countString, queryString, new ArrayList<Object>(), pageNo, pageSize);
	}

	private PageList getPageList(String countString, String queryString, List<Object> params, int start, int pageSize) {
		PageList pageList = new PageList();
		int count = getJdbcTemplate().queryForInt(String.format(countString, params.toArray()));
		if (count == 0) {
			return pageList;
		}
		pageList.setPageSize(pageSize);
		pageList.setPageIndex(start);
		pageList.setRecordCount(count);
		pageList.initialize();
		if (count == 0) {
			return pageList;
		}

		pageList.setList(getJdbcTemplate().query(
				String.format(queryString + " limit " + start + ", " + pageSize, params.toArray()),new Object[]{},
				ParameterizedBeanPropertyRowMapper.newInstance(entityClass)));
		return pageList;
	}

	/**
	 * 含有查询条件的分页查询
	 * 
	 * @param conditions
	 *            查询条件 Map，key为对应表中的字段，boolean值需要转为数字
	 * @param pageNo
	 *            分页起始页码
	 * @param pageSize
	 *            每页显示条数
	 * @param orderType
	 *            排序类型 DESC | ASC
	 * @param orderField
	 *            排序字段
	 * @return
	 */
	public PageList getPageListByCondition(Map<String, Object> conditions, int pageNo, int pageSize, String orderType,
			String orderField) {

		StringBuffer countString = new StringBuffer("select count(*) from " + this.tableName);
		StringBuffer queryString = new StringBuffer(" select * from " + this.tableName);
		StringBuffer whereString = new StringBuffer(" where 1 = 1 ");

		List<Object> params = new ArrayList<Object>();
		setQueryParam(conditions, whereString, params);

		countString.append(whereString.toString());
		queryString.append(whereString.toString());

		if (StringUtils.isNotBlank(orderField)) {
			if (!"desc".equalsIgnoreCase(orderType)) {
				orderType = "";
			}
			queryString.append(" order by " + orderField + " " + orderType);
		}

		return getPageList(countString.toString(), queryString.toString(), params, pageNo, pageSize);
	}

	public PageList getPageListByCondition(Map<String, Object> conditions, int pageNo, int pageSize, String orderType,
			String orderField, String whereStr) {

		StringBuffer countString = new StringBuffer("select count(*) from " + this.tableName);
		StringBuffer queryString = new StringBuffer(" select * from " + this.tableName);
		StringBuffer whereString = new StringBuffer(" where 1 = 1 ");
		whereString.append(whereStr);
		List<Object> params = new ArrayList<Object>();
		setQueryParam(conditions, whereString, params);

		countString.append(whereString.toString());
		queryString.append(whereString.toString());

		if (StringUtils.isNotBlank(orderField)) {
			if (!"desc".equalsIgnoreCase(orderType)) {
				orderType = "";
			}
			queryString.append(" order by " + orderField + " " + orderType);
		}

		return getPageList(countString.toString(), queryString.toString(), params, pageNo, pageSize);
	}

	/**
	 * 设置参数
	 * 
	 * @param conditions
	 *            查询条件Map
	 * @param whereString
	 *            where 查询条件
	 * @param params
	 *            参数值
	 */
	private void setQueryParam(Map<String, Object> conditions, StringBuffer whereString, List<Object> params) {
		if (conditions == null) {
			return;
		}
		Set<Map.Entry<String, Object>> conditionSet = conditions.entrySet();
		for (Map.Entry<String, Object> condition : conditionSet) {
			String paramName = condition.getKey();
			Object paramValue = condition.getValue();
			if (StringUtils.isNotBlank(paramName) && paramValue != null) {
				if (paramValue instanceof String) {
					String value = paramValue.toString().trim();
					// 为了标识时间字段，需要在字段的后面添加后缀，处理时去掉
					if (paramName.endsWith("_beginTime")) {
						whereString.append(" and " + paramName.replaceAll("_beginTime", "") + " >= '%s' ");
						params.add(paramValue);
					} else if (paramName.endsWith("_endTime")) {
						whereString.append(" and " + paramName.replaceAll("_endTime", "") + " <= '%s' ");
						params.add(paramValue);
					} else {
						whereString.append(" and " + paramName + " like %s ");
						params.add("'%" + value + "%'");
					}
				} else {
					whereString.append(" and " + paramName + " = %d ");
					params.add(paramValue);
				}
			}
		}
	}

	@Override
	public void updateSpecifyData(Map<String, Object> updateConditions, Map<String, Object> whereConditions) {
		if (updateConditions == null) {
			return;
		}
		StringBuffer updateBuf = new StringBuffer("update ").append(this.tableName).append(" set ");
		Set<Map.Entry<String, Object>> conditionSet = updateConditions.entrySet();
		List<Object> argsList = new ArrayList<Object>();
		for (Map.Entry<String, Object> condition : conditionSet) {
			String paramName = condition.getKey();
			Object paramValue = condition.getValue();
			if (StringUtils.isNotBlank(paramName) && paramValue != null) {
				updateBuf.append(paramName + " = ? ,");
				argsList.add(paramValue);
			}
		}
		updateBuf.deleteCharAt(updateBuf.length()-1);
		if(whereConditions != null) {
			StringBuffer whereBuf = new StringBuffer(" where ");
			Set<Map.Entry<String, Object>> whereConditionSet = whereConditions.entrySet();
			for (Map.Entry<String, Object> condition : whereConditionSet) {
				String paramName = condition.getKey();
				Object paramValue = condition.getValue();
				if (StringUtils.isNotBlank(paramName) && paramValue != null) {
					whereBuf.append(paramName + " = ? and ");
					argsList.add(paramValue);
				}
			}
			whereBuf.delete(whereBuf.length() - 4, whereBuf.length()-1);
			updateBuf.append(whereBuf);
		}

		try{
			getJdbcTemplate().update(updateBuf.toString(), argsList.toArray());
		}
		catch(Exception e){
			logger.info("updateSpecifyData，SQL=["+updateBuf+","+Arrays.toString(argsList.toArray())+"]");
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.mobimtech.ivp.dao.BaseDao#save(java.util.Map)
	 */
	@Override
	public void save(Map<String, Object> saveConditions) {
		if (saveConditions == null) {
			return;
		}
		StringBuffer sqlBuf = new StringBuffer("insert into ").append(this.tableName).append("(");
		StringBuffer valueBuf = new StringBuffer("values(");
		Set<Map.Entry<String, Object>> conditionSet = saveConditions.entrySet();
		List<Object> list = new ArrayList<Object>(); //填入的参数
		for (Map.Entry<String, Object> condition : conditionSet) {
			String paramName = condition.getKey();
			Object paramValue = condition.getValue();
			if (StringUtils.isNotBlank(paramName) && paramValue != null) {
				sqlBuf.append(paramName+",");
				valueBuf.append("?,");
				list.add(paramValue);
			}
		}
		sqlBuf.deleteCharAt(sqlBuf.length()-1).append(") ");
		valueBuf.deleteCharAt(valueBuf.length()-1).append(")");
		sqlBuf.append(valueBuf);
		
		logger.debug("执行保存操作，SQL=["+sqlBuf+","+ Arrays.toString(list.toArray())+"]");
		getJdbcTemplate().update(sqlBuf.toString(), list.toArray());
	}
	
	/* (non-Javadoc)
	 * @see com.mobimtech.ivp.dao.BaseDao#save(java.util.Map)
	 */
	@Override
	public int insertAndGetKey(Map<String, Object> saveConditions) {
		if (saveConditions == null) {
			return 0;
		}
		KeyHolder keyHolder = new GeneratedKeyHolder();
		StringBuffer updateBuf = new StringBuffer("insert into ").append(this.tableName).append("(");
		StringBuffer valueBuf = new StringBuffer("values(");
		Set<Map.Entry<String, Object>> conditionSet = saveConditions.entrySet();
		List<Object> obValues =new  ArrayList<Object>();
		for (Map.Entry<String, Object> condition : conditionSet) {
			String paramName = condition.getKey();
			Object paramValue = condition.getValue();
			if (StringUtils.isNotBlank(paramName)&& paramValue != null) {
				updateBuf.append(paramName+",");
				valueBuf.append("?,");
				obValues.add(paramValue);
			}
		}
		
		updateBuf.deleteCharAt(updateBuf.length()-1).append(")");
		valueBuf.deleteCharAt(valueBuf.length()-1).append(")");
		final String sql =updateBuf.append(valueBuf).toString();
		final List<Object> finalObs= obValues;
		getJdbcTemplate().update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				PreparedStatement ps = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
				for(int i=0;i<finalObs.size();i++){
					ps.setObject(i+1, finalObs.get(i));
				}
				return ps;
			}
		}	
		, keyHolder);
		logger.debug("执行保存操作，SQL=["+sql+"]");
		return keyHolder.getKey().intValue();
	}
	
	

	private List getList(String countString, String queryString, List<Object> params)
	{
		return getJdbcTemplate().query(
				String.format(queryString , params.toArray()),
				ParameterizedBeanPropertyRowMapper.newInstance(entityClass));
	}
	
	/**
	 * 含有查询条件的分页查询
	 * 
	 * @param conditions
	 *            查询条件 Map，key为对应表中的字段，boolean值需要转为数字
	 * @param orderType
	 *            排序类型 DESC | ASC
	 * @param orderField
	 *            排序字段
	 * @return List
	 */
	public List getListByCondition(Map<String, Object> conditions, String orderType, String orderField) {
		StringBuffer countString = new StringBuffer("select count(*) from " + this.tableName);
		StringBuffer queryString = new StringBuffer(" select * from " + this.tableName);
		StringBuffer whereString = new StringBuffer(" where 1 = 1 ");

		List<Object> params = new ArrayList<Object>();
		setQueryParam(conditions, whereString, params);

		countString.append(whereString.toString());
		queryString.append(whereString.toString());

		if (StringUtils.isNotBlank(orderField)) {
			if (!"desc".equalsIgnoreCase(orderType)) {
				orderType = "";
			}
			queryString.append(" order by " + orderField + " " + orderType);
		}

		return getList(countString.toString(), queryString.toString(), params);
		
	}
	
	 

}
