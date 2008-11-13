package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.dao.RoleDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.DetachedCriteria;
/**
 * 帮助信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/11/06 17:38:34 $
 */
public class RoleDAOHibernateImpl extends BaseDAOHibernateImpl implements RoleDAO {
	
	/**
	 * 查找全部的角色信息
	 * 
	 * @param type 是否为管理组
	 * @return List 用户列表
	 */
	public List findRoles(int type) throws DAOException {
		try{
			if( type==-1 ){
				return super.find("from Role as role");
			}
			else{
				return super.find("from Role as role where role.type=? and role.id!=2",new Integer(type));
			}
		}
		catch(DAOException daoException){
			throw new DAOException("查找全部的角色信息发生错误");
		}
	}
	
	/**
	 * 查找全部的角色信息
	 * 
	 * @return List 用户列表
	 */
	public List findRoles() throws DAOException {
		try{
			return super.find("from Role as role where id!=2");
		}
		catch(DAOException daoException){
			throw new DAOException("查找全部的角色信息发生错误");
		}	
	}	
	
	/**
	 * 根据角色编号查找的角色对象
	 * @param roleId 角色编号
	 * @return Role 角色对象 
	 */	
	public Role findRole(long roleId) throws DAOException {
		try{
			return  (Role)super.get(Role.class,new Long(roleId));
    	}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得 roleId(" +roleId+ ") 对应的角色信息发生错误，该信息可能被删除.");
		}
    	catch(Exception exception){
    		throw new DAOException("取得 roleId(" +roleId+ ") 对应的帮助信息发生错误");
    	}
	}
	
	/**
	 * 根据文章数查找角色
	 * @param minTopic 最少主题数  
	 * @return 角色对象
	 */
	public Role findRole(int minTopic) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
			detachedCriteria.add(Restrictions.or(Restrictions.lt("minTopic",new Integer(minTopic)),Restrictions.eq("minTopic",new Integer(minTopic))) );
			List list = super.find(detachedCriteria,1);
			return (Role)(list.toArray()[0]);
		}
		catch(DAOException daoException){
			throw new DAOException("查找全部的角色信息发生错误");
		}		
	}	

}