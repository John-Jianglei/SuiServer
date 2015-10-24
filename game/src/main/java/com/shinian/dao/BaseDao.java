/*
 * <pre>
 * Author: Wei Rongting
 * Package Name: com.mobimtech.ivp.dao
 * </pre>
 */
package com.shinian.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shinian.util.PageList;

/**
 * DAO接口
 * 
 * <pre>
 * Class Name: BaseDao
 * Modifications:
 * Modifier Wei Rongting; 2012-5-21; Create new Class BaseDao.
 * </pre>
 */
public interface BaseDao<T, ID extends Serializable> {
	/**
	 * 获取指定值对象，需要编写VO
	 * 
	 * @param id
	 *            vo的ID
	 * @return
	 */
	T get(Serializable id);

	/**
	 * 获取指定值对象，需要编写VO,如果表主键的ID的命名不是id，需要指定别名
	 * 
	 * @param idName
	 *            数据库表的主键ID的名字
	 * @param id
	 *            vo的ID
	 * @return
	 */
	public T get(String idName, Serializable id);

	/**
	 * 查询所有的VO
	 * 
	 * @return
	 */
	List<T> getAll();

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 */
	void remove(ID id);

	/**
	 * 根据ID删除记录
	 * 
	 * @param id
	 */
	public void remove(String idName, Serializable id);

	/**
	 * 根据一组ID删除记录
	 * 
	 * @param id
	 */
	void removeIds(ID[] ids);

	/**
	 * 删除全部记录
	 */
	void removeAll();

	/**
	 * 无条件分页查询
	 * 
	 * @param pageNo
	 *            起始页码
	 * @param pageSize
	 *            每页显示条数
	 * @return
	 */
	PageList getPageList(int pageNo, int pageSize);

	/**
	 * 含有查询条件的分页查询
	 * 
	 * @param conditions
	 *            查询条件 Map，key为VO中的字段，java规范命名，boolean值需要转为数字
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
	PageList getPageListByCondition(Map<String, Object> conditions, int pageNo, int pageSize, String orderType,
			String orderField);

	PageList getPageListByCondition(Map<String, Object> conditions, int pageNo, int pageSize, String orderType,
			String orderField, String whereStr);

	/**
	 * 修改指定的字段，key为指定的数据库字段，value为指定的值
	 * <pre>
	 * Author: Wei Rongting
	 * @param updateConditions 更新字段
	 * @param whereConditions 更新条件
	 * Modifications:
	 * Modifier Wei Rongting; 2012-6-8; Create new Method updateSpecifyData
	 * </pre>
	 */
	void updateSpecifyData(Map<String, Object> updateConditions, Map<String, Object> whereConditions);

	/**
	 * 保存，key为指定的数据库字段，value为指定的值
	 * <pre>
	 * Author: Wei Rongting
	 * @param saveConditions 保存字段对应值
	 * Modifications:
	 * Modifier Wei Rongting; 2012-6-8; Create new Method save
	 * </pre>
	 */
	public void save(Map<String, Object> saveConditions) ;
    /**
     * 保存，key为指定的数据库字段，value为指定的值 并返回该记录的主键ID
     * Author: tao.pan
     * Function: 
     * @param saveConditions
     * Modifications:
     * Modifier tao.pan; 2012-7-4; Create new Method
     */
	int insertAndGetKey(Map<String, Object> saveConditions);
	
	/**
	 * 含有查询条件的查询(不分页)
	 * 
	 * @param conditions
	 *            查询条件 Map，key为对应表中的字段，boolean值需要转为数字
	 * @param orderType
	 *            排序类型 DESC | ASC
	 * @param orderField
	 *            排序字段
	 * @return List
	 * Modifications:
	 * Modifier yanchun.lin; 2012-8-30; Create new Method save
	 */
	public List getListByCondition(Map<String, Object> conditions, String orderType, String orderField);

}
