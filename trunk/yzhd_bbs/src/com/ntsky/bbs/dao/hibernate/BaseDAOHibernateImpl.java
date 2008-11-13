package com.ntsky.bbs.dao.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger; 
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mysql.jdbc.Connection;
import com.ntsky.bbs.dao.BaseDAO;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectNotExistException;

public class BaseDAOHibernateImpl extends HibernateDaoSupport implements BaseDAO {
	
	public static Logger logger = Logger.getLogger(sun.reflect.Reflection.getCallerClass(1));
	
	/**
	 * 取得数据库连接
	 * @return connection 
	 */
	public Connection getConnection() throws DAOException {
		Session session = null;
		Connection conn = null;
		try {
			session = (Session) getSession();
			conn = (Connection) session.connection();
		} catch (Exception e) {
			logger.error("sql操作失败 : " + e.getMessage());
		} finally {
			try{
				conn.close();
			}catch(Exception ex){				
			}
			releaseSession(session);
		}
		return conn;
	}
	
	/**
	 * 删除数据
	 * @param object 待删除的对象
	 */
    public void delete(Object object) throws DAOException {
    	try{
    		getHibernateTemplate().delete(object);
    	}
    	catch(DataAccessException dataAccessException){
    		logger.error("删除对象错误.", dataAccessException);
    		throw new DAOException("删除对象错误");
    	}
    	catch(Exception exception){
    		logger.error("删除对象错误." , exception);
    		throw new DAOException("删除对象错误");
    	}
    }
    
    /**
     * 根据hsql查询数据
     * @param hsql hibernate sql
     * @return list 数据集合
     */
	public List find(String hsql) throws DAOException {
		try{
			return getHibernateTemplate().find(hsql);
		}
		catch(DataAccessException dataAccessException){
			logger.error("查询对象发生错误. ", dataAccessException);
			throw new DAOException("查询对象发生错误");
		}
		catch(Exception exception){
			logger.error("查询对象发生错误. ", exception);
			throw new DAOException("查询对象发生错误");			
		}
	}

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
    public void executeHsql(String hsql) throws DAOException{
		Session session = null;
		try {
			session = (Session) getSession();
			int result = session.createQuery(hsql).executeUpdate();
		}
    	catch(DataAccessException dataAccessException){
    		logger.error("批处理失败 : " + dataAccessException.getMessage());
    		throw new DAOException("批处理发生错误");
    	}
    	catch(Exception exception){
    		logger.error("批处理失败 : " + exception.getMessage());
    		throw new DAOException("批处理发生错误");
    	}  		
		finally {
			releaseSession(session);
		}
    }	
    
    /**
     * 执行sql语句进行批量操作
     * 
     * @param sql 查询语句
     */
    public void executeSql(String sql) throws DAOException{
		Session session = null;
		Connection conn = null;
		PreparedStatement stmt =null;
		try {
			session = (Session) getSession();
			conn = (Connection) session.connection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (Exception e) {
			logger.error("sql操作失败 : " + e.getMessage());
		} finally {
			try{
				stmt.close();
				conn.close();
			}catch(Exception ex){				
			}
			releaseSession(session);
		}
    }
	
    /**
     * 根据hsql查询数据
     * @param hsql hibernate sql
     * @param param sql字段
     * @return List 数据集合
     */
	public List find(String hsql,Object param) throws DAOException {
		try{
			return getHibernateTemplate().find(hsql,param);
		}
		catch(DataAccessException dataAccessException){
			logger.error("查询对象发生错误. ", dataAccessException);
			throw new DAOException("查询对象发生错误");
		}
		catch(Exception exception){
			logger.error("查询对象发生错误. ", exception);
			throw new DAOException("查询对象发生错误");			
		}
	}	
	
    /**
     * 根据hsql查询数据
     * @param hsql hibernate sql
     * @param param sql字段
     * @return List 数据集合
     */
	public List find(String hsql,Object[] params) throws DAOException {
		try{
			return getHibernateTemplate().find(hsql,params);
		}
		catch(Exception exception){
			logger.error("查询对象发生错误. ", exception);
			throw new DAOException("查询对象发生错误");			
		}		
	}		

	/**
	 * 保存或更新数据
	 * @param object 对象数据
	 */
    public void saveOrUpdate(Object object) throws DAOException {
    	try{
    		getHibernateTemplate().saveOrUpdate(object);
    	    getHibernateTemplate().flush();
    	}
    	catch(DataAccessException dataAccessException){
    		logger.error("保存对象发生错误. " ,dataAccessException);
    		throw new DAOException("保存对象 ["+object.getClass().getName()+"]发生错误");
    	}
    	catch(Exception exception){
    		logger.error("保存对象发生错误. " ,exception);
    		throw new DAOException("保存对象 ["+object.getClass().getName()+"]发生错误");
    	}    	
     }	
	
	/**
	 * 更新对象
	 * @param object 对象数据
	 */
    public void update(Object object) throws DAOException {
    	try{
    		 getHibernateTemplate().update(object);
    	}
    	catch(DataAccessException dataAccessException){
    		logger.error("更新对象发生错误. " ,dataAccessException);
    		throw new DAOException("更新对象 [ "+object.getClass().getName()+" ] 发生错误");
    	}
    	catch(Exception exception){
    		logger.error("更新对象发生错误 . " ,exception);
    		throw new DAOException("更新对象 [ "+object.getClass().getName()+" ] 发生错误");
    	}  
    }	    
    
	/**
	 * 保存对象
	 * @param object 对象数据
	 */
    public void save(Object object) throws DAOException {
    	try{
    		getHibernateTemplate().save(object);
    	}
    	catch(DataAccessException dataAccessException){
    		logger.error("保存对象发生错误. " ,dataAccessException);
    		throw new DAOException("保存对象 ["+object.getClass().getName()+"]发生错误");
    	}
    	catch(Exception exception){
    		logger.error("保存对象发生错误. " , exception);
    		throw new DAOException("保存对象 ["+object.getClass().getName()+"]发生错误");
    	}    	
        // getHibernateTemplate().flush();
    }
	
	/**
	 * 删除对象
	 * @param clazz 对象类别
	 * @param id 对象标识
	 */
	 public void delete(Class clazz, Serializable id) throws DAOException {
	    try {
			getHibernateTemplate().delete(get(clazz, id));
		} catch (DataAccessException dataAccessException) {
			logger.error("删除对象发生错误. ",dataAccessException);
			throw new DAOException("删除标识为" + id + "的对象 [" + clazz.getName() + "]发生错误");
		} catch (Exception exception) {
			logger.error("保存对象发生错误. ", exception);
			throw new DAOException("删除标识为" + id + "的对象 [" + clazz.getName() + "]发生错误");
		}    	
	 }	

	/**
	 * 根据对象取得信息
	 * 
	 * @param clazz 对象名称
	 * @param id  序列化主键
	 * @return object 查询出的对象
	 */
	public Object get(Class clazz, Serializable id) throws DAOException{
    	Object object = null;
		try{
			object = getHibernateTemplate().get(clazz, id);
    	}
    	catch(DataAccessException dataAccessException){
    		logger.error("取得对象 ("+clazz.getName()+") 发生错误." ,dataAccessException);
    		throw new DAOException("取得对象错误");
    	}
    	catch(Exception exception){
    		logger.error("取得对象 ("+clazz.getName()+") 错误." ,exception);
    		throw new DAOException("取得对象错误");
    	}
    	if(object==null){
    		logger.warn("编号 : " + id + " 对应的信息不存在");
    		throw new ObjectNotExistException("编号 : " + id + " 对应的信息不存在");
    	}
    	return object;
	}

	/**
	 * 载入对象
	 * @param Class clazz 名称
	 * @param id 主键
	 */
	public Object load(Class clazz, Serializable id) throws DAOException {
		Object object = null;
		try{
			object = getHibernateTemplate().load(clazz, id);
    	}
    	catch(DataAccessException dataAccessException){
    		logger.error("取得对象 ("+clazz.getName()+") 发生错误. ", dataAccessException);
    		throw new DAOException("取得对象错误");
    	}
    	catch(Exception exception){
    		logger.error("取得对象 ("+clazz.getName()+") 错误. " , exception);
    		throw new DAOException("取得对象错误");
    	}
    	if(object==null){
    		logger.warn("编号 : " + id + "对应的信息不存在");
    		throw new ObjectNotExistException("编号 : " + id + "对应的信息不存在");
    	}
    	return object;
	}
	
	/**
	 * 取得SQL函数的值
	 * @param hql hibernate sql
	 * @return Object 对象总数
	 */
	public Object findByAggregate(String hsql) throws DAOException {
		Session session = null;
		try {
			session = (Session) getSession();
			return session.createQuery(hsql).iterate().next();
		}
		catch (DataAccessException dataAccessException) {
    		logger.error("取得函数值发生错误 . ", dataAccessException);
    		throw new DAOException("根据SQL函数取值发生错误");
		}
		catch(Exception ex){
    		logger.error("取得函数值发生错误 . " , ex);
    		throw new DAOException("根据SQL函数取值发生错误");
		}
		finally {
			releaseSession(session);
		}		
	}
	
	/**
	 * 根据查询名称取得集合
	 * @param queryName hbm文件中定义的查询名称
	 * @return List 对象集合
	 */
	public List findByNameQuery(String queryName) throws DAOException {
		try {
			return getHibernateTemplate().findByNamedQuery(queryName);
		}
		catch (DataAccessException dataAccessException) {
    		logger.error("根据" + queryName + "列表对象发生错误 . " , dataAccessException);
    		throw new DAOException("根据" + queryName + "列表对象发生错误");
		}
		catch(Exception ex){
    		logger.error("根据" + queryName + "列表对象发生错误 . " , ex);
    		throw new DAOException("根据" + queryName + "列表对象发生错误");
		}
	}

	/**
	 * 根据查询名称取得集合
	 * @param queryName hbm文件中定义的查询名称
	 * @return List 对象集合
	 */
	public List findByNameQuery(String queryName, Object[] params) throws DAOException {
		try {
			return getHibernateTemplate().findByNamedQuery(queryName,params);
		}
		catch (DataAccessException dataAccessException) {
    		logger.error("根据" + queryName + "列表对象发生错误 : " + dataAccessException.getMessage());
    		throw new DAOException("根据" + queryName + "列表对象发生错误");
		}
		catch(Exception ex){
    		logger.error("根据" + queryName + "列表对象发生错误 : " + ex.getMessage());
    		throw new DAOException("根据" + queryName + "列表对象发生错误");
		}
	}
	
	/**
	 * 分页查找
	 * @param detachedCriteria 
	 * @param orders 排序数组 orders为空的时候设定按ID降序排列
	 * @param pagination 分页对象
	 * @return QueryResult 检索结果
	 */
	public QueryResult findItemsByCriteria(final DetachedCriteria detachedCriteria,final Map orders,final Pagination pagination) throws DAOException {
		return (QueryResult) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				int totalRecord = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
				criteria.setProjection(null);
				detachedCriteria .setResultTransformer(CriteriaSpecification.ROOT_ENTITY); 
				// 添加排序列信息

				if(orders!=null&&orders.size()>0){
					Set set = orders.entrySet();
			        Iterator iterator = set.iterator();
			        Map.Entry entry = null;
			        while (iterator.hasNext()) {
			            entry = (Map.Entry) iterator.next();
			            if("asc".equals((String)entry.getValue())){
			            	criteria.addOrder(Order.asc((String)entry.getKey()));
			            }
			            else{
			            	if("desc".equals((String)entry.getValue())){
								criteria.addOrder(Order.desc((String)entry.getKey()));
							}
			            }
			        }
				}
				else{
					criteria.addOrder(Order.desc("id"));
				}
				if(logger.isDebugEnabled()){
					logger.debug("检索的起始位置 : " + pagination.getStart());
				}
				List items = criteria.setFirstResult(pagination.getStart()).setMaxResults(pagination.getRange()).list();
				if(logger.isDebugEnabled()){
					logger.debug("检索到的结果数 : " + items.size());
				}
				pagination.setTotalRecord(totalRecord);
				return new QueryResult(pagination,items);
			}
		}, true);
	}	

	/**
	 * 查找到的对象集合
	 * 
	 * @param detachedCriteria 
	 * @param dataNum 每页显示记录数
	 * @return List 查找到的对象集合
	 */
	public List find(final DetachedCriteria detachedCriteria,final int dataNum) throws DAOException {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = detachedCriteria.getExecutableCriteria(session);
				criteria.setProjection(null);
				detachedCriteria .setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
				criteria.addOrder(Order.desc("id"));
				List items = null;
				if( dataNum == 0 ){
					items = criteria.setFirstResult(0).list();
				}
				else{
					items = criteria.setFirstResult(0).setMaxResults(dataNum).list();
				}
				return items;
			}
		}, true);
	}	
	
}
