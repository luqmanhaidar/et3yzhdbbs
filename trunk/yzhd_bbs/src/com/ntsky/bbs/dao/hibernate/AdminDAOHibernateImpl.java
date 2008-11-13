package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import com.ntsky.bbs.dao.AdminDAO;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.exception.DAOException;

/**
 * 管理员信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class AdminDAOHibernateImpl extends BaseDAOHibernateImpl implements AdminDAO {

	/**
	 * 查找全部的管理员信息
	 * 
	 * @return List 管理员集合
	 */
	public List findAdmins() throws DAOException {
		try{
			return super.find("from Admin as admin");
		}
		catch(DAOException daoException){
			throw new DAOException("列表管理员发生错误.");
		}
	}
	
	/**
	 * 根据统计编号查找的统计数据
	 * 
	 * @param adminId 管理编号
	 * @return Admin 统计数据
	 */	
	public Admin findAdmin(int adminId) throws DAOException {
		try {
			return (Admin)super.load(Admin.class,new Long(adminId));
		}
		catch(DAOException daoException){
			throw new DAOException("根据管理员编号取得管理员信息失败");
		}
	}

	/**
	 * 根据用户名和密码查询管理员
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @return Admin 管理员信息
	 */
	public Admin findAdmin(String username, String password) throws DAOException {
		try{
			List list = super.find("from Admin as admin where admin.username=? and admin.password=?",new String[]{username,password});
			if(list!=null&&list.size()>0){
				return (Admin)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException daoException){
			throw new DAOException("根据用户名和密码取得管理员信息失败");
		}
	}
	
	/**
	 * 根据用户名查询管理员
	 * 
	 * @param username 用户名
	 * @return Admin 管理员信息
	 */
	public Admin findAdmin(String username) throws DAOException {
		try{
			List list = super.find("from Admin as admin where admin.username=?",new String[]{username});
			if(list!=null&&list.size()>0){
				return (Admin)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException daoException){
			throw new DAOException("根据用户名取得管理员信息失败");
		}
	}	

	/**
	 * 更新管理员密码
	 * 
	 * @param adminId 管理员编号
	 * @param password 新密码
	 * @throws DAOException 
	 */
	public void updateAdminPassword(int adminId,String password) throws DAOException{
		try{
			super.executeHsql("update Admin set password='"+password+"' where id='"+adminId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更新管理员密码失败");
		}		
	}

	
	/**
	 * 管理员登录时更新登录时间和IP
	 * 
	 * @param adminId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 */
	public void updateLoginInfo(int adminId,String lastLoginTime,String lastLoginIp) throws DAOException{
		try{
			super.executeHsql("update Admin set lastLoginTime='"+lastLoginTime+"',lastLoginIp='"+lastLoginIp+"' where id='"+adminId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更新管理员登陆信息发生错误.");
		}		
	}
	
}
