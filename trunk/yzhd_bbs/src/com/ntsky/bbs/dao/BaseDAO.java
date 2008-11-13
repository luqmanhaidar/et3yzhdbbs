package com.ntsky.bbs.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;

/**
 * DAO公共数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:25 $
 */
public interface BaseDAO {
	
	/**
	 * 取得数据库连接
	 * @return connection 
	 */
	public Connection getConnection() throws DAOException;
	
	/**
	 * 删除数据
	 * @param object 待删除的对象
	 */
    public void delete(Object object) throws DAOException ;
    /**
     * 根据hsql查询数据
     * @param hsql hibernate sql
     * @return List 数据集合
     */
	public List find(String hsql) throws DAOException ;
	
    /**
     * 根据hsql查询数据
     * @param hsql hibernate sql
     * @param param sql字段
     * @return List 数据集合
     */
	//public List find(String hsql,Object param) throws DAOException ;
	
    /**
     * 根据hsql查询数据
     * @param hsql hibernate sql
     * @param param sql字段
     * @return List 数据集合
     */
	public List find(String hsql,Object[] params) throws DAOException ;
	
	/**
	 * 根据查询名称取得集合
	 * @param queryName hbm文件中定义的查询名称
	 * @return List 对象集合
	 */
	public List findByNameQuery(String queryName, Object[] params) throws DAOException ;
	/**
	 * 根据Class以及标示载入对象
	 * @param clazz
	 * @param id
	 * @return object 对象
	 */
	public Object load(Class clazz, Serializable id) throws DAOException ;	
	
	/**
	 * 保存数据
	 * @param object 对象数据
	 */
    public void save(Object object) throws DAOException ;
	
	/**
	 * 保存对象
	 * @param object 对象数据
	 */
    public void saveOrUpdate(Object object) throws DAOException ;
    
	/**
	 * 更新对象
	 * @param object 对象数据
	 */
    public void update(Object object) throws DAOException ;
    
	/**
	 * 删除对象
	 * @param clazz 对象类别
	 * @param id 对象标识
	 */
	 public void delete(Class clazz, Serializable id) throws DAOException ;

	/**
	 * 根据对象取得信息
	 * @param clazz 对象名称
	 * @param id 序列化主键
	 * @return object 查询出的对象
	 */
	public Object get(Class clazz, Serializable id) throws DAOException ;

	/**
	 * 取得对象总数
	 * @param hsql hibernate sql
	 * @param params 数组对象
	 * @return int 对象总数
	 */
	//public int getTotalObject(String hsql, Object[] params) ;
	
	/**
	 * 取得对象总数
	 * @param hql hibernate sql
	 * @return Object 对象总数
	 */
	public Object findByAggregate(String hsql) throws DAOException ;
	
    /**
     * 执行Hsql语句
     * <pre>
     * 	 性能优化方法
     * </pre>
     * <ol>
     * 	<li>执行批量操作，比如一次性执行多条SQL</li>
     *  <li>删除操作，避免先select再delete</li> 
     * </ol>
     * 
     * @param hsql 查询语句
     */
    public void executeHsql(String hsql) throws DAOException ;
    
    /**
     * 执行sql语句进行批量操作
     * 
     * @param sql 查询语句
     */
    /*public void executeSql(String sql);*/	
	
}