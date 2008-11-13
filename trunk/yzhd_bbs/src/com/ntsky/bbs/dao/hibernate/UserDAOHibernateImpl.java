package com.ntsky.bbs.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.dao.UserDAO;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.domain.UserFace;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

public class UserDAOHibernateImpl extends BaseDAOHibernateImpl implements UserDAO {

	/**
	 * 查找全部的用户信息
	 * 
	 * @param paramsMap 用户条件数组
	 * @param pagination 分页对象
	 * @return List 用户集合
	 */
	public List findUsers (final Map paramsMap, final Pagination pagination) {
		return null;
	}

	/**
	 * 根据用户编号查找的用户数据
	 * 
	 * @param userId 用户编号
	 * @return User 用户对象
	 */	
	public User findUser(long userId) throws DAOException {
		try{
			return (User)super.get(User.class,new Long(userId));
		}
		catch(DAOException de){
			throw new DAOException("根据用户编号['"+userId+"']查找用户信息失败");
		}		
	}

	/**
	 * 根据用户名查找用户信息
	 * 
	 * @param username 用户名称
	 * @return User 用户对象(查找的用户不存在时,返回null)
	 */
	public User findUser(String username) throws DAOException {
		try{
			List list = super.find("from User as user where user.username=?",new String[]{username});
			if(list!=null&&list.size()>0){
				return (User)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException de){
			throw new DAOException("根据用户 "+username+" 查找用户信息失败");
		}
	}
	
	public String findRoleId(String username) throws DAOException {
		try{
			List list = super.find("from User as user where user.username=?",new String[]{username});
			if(list!=null&&list.size()>0){
				//System.out.println("fadsfadsfadsfsd"+((User)list.toArray()[0]).getRoles());
				return ((User)list.toArray()[0]).getRoles();
			}
			else{
				return null;
			}
		}
		catch(DAOException de){
			throw new DAOException("根据用户 "+username+" 查找用户信息失败");
		}
	}

	/**
	 * 根据用户名和密码查找用户信息
	 * 
	 * @param username 用户名
	 * @param password 用户密码
	 * @return User 用户对象
	 */
	public User findUser(String username, String password) throws DAOException {
		try{
			List list = super.find("from User as user where user.username=? and user.password=?",new String[]{username,password});
			if(list!=null&&list.size()>0){
				return (User)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException de){
			throw new DAOException("根据用户['"+username+"']和密码['"+password+"']查找用户信息失败");
		}
	}

	/**
	 * 根据用户角色列表用户
	 * @param roles
	 * @return
	 * @throws DAOException
	 */
	public List findUsersByRoles(String roles) throws DAOException{
		try{
			return super.find("from User as u where u.roles='"+roles+"'");
		}
		catch(DAOException de){
			throw new DAOException("根据用户角色列表用户信息失败");
		}
	}
	
	/**
	 * 取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult findUsers(String username,Map orderMap,Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class); 
			detachedCriteria.add(Restrictions.like("username","%"+username+"%"));
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("根据用户['"+username+"']列表用户信息失败");
		}
	}		
	
	/**
	 * 根据用户角色取得用户列表
	 * 
	 * @param username 用户名关键字
	 * @param roels 用户角色 -1 全部用户
	 * @param order 排序字段
	 * @return QueryResult 用户列表
	 * @throws ServiceException
	 */
	public QueryResult findUsersByRoles(String username,int roles, Map orderMap,Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class); 
			detachedCriteria.add(Restrictions.like("username","%"+username+"%"));
			if( roles!=-1 ){
				detachedCriteria.add(Restrictions.eq("roles",String.valueOf(roles)));
			}
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("根据用户['"+username+"']列表用户信息失败");
		}		
	}	

	
	
	/**
	 * 用户登录时更新登录时间和IP
	 * 
	 * @param userId 用户编号
	 * @param lastLoginTime 最后登陆时间
	 * @param lastLoginIp 最后登陆的IP
	 * @throws ServiceException
	 */
	public void updateLoginInfo(int userId,String lastLoginTime,String lastLoginIp) throws DAOException {
		try{
			super.executeHsql("update User set lastLoginTime='"+lastLoginTime+"',lastLoginIp='"+lastLoginIp+"',loginTimes=loginTimes+1 where id='"+userId+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新用户登陆信息发生错误");
		}
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @param userId 用户名
	 * @param password 新密码
	 * @throws DAOException 数据处理异常
	 */	
	public void updateUserPassword(int userId,String password) throws DAOException{
		try{
			super.executeHsql("update User set password='"+password+"' where id='"+userId+"'");
		}
		catch(DAOException de){
			throw new DAOException("修改用户密码错误");
		}
	}	
	
	/**
	 * 用户总数
	 * @throws DAOException
	 */
	public int countUser() throws DAOException{
		try{
			return ((Integer)super.findByAggregate("select count(user.id) from User as user")).intValue();
		}
		catch(DAOException de){
			throw new DAOException("统计用户总数失败");
		}
	}
	
	/**
	 * 最新注册的用户
	 * @return
	 * @throws DAOException
	 */
	public User findNewlyUser() throws DAOException{
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
			List list = super.find(detachedCriteria,1);
			if(list!=null&&list.size()>0){
				return (User)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException de){
			throw new DAOException("取得最新注册的用户失败");
		}		
	}
	
	/**
	 * 更新用户中主题和贴子相关信息
	 * @param username
	 * @param type 类型（topic,post）
	 * @param operator 操作方式(inc +1 des -1)
	 * @throws DAOException
	 */
	public void updateAboutPost(String username,String type,String operator) throws DAOException{
		try{
			if(Symbols.POST.equals(type)){
				if(Symbols.INCREASE.equals(operator)){
					super.executeHsql("update User set totalPost=totalPost+1 where username='"+username+"'");
				}
				else{
					if(Symbols.DECREASE.equals(operator)){
						super.executeHsql("update User set totalPost=totalPost-1 where username='"+username+"'");
					}
				}
			}
			else{
				if(Symbols.TOPIC.equals(type)){
					if(Symbols.INCREASE.equals(operator)){
						super.executeHsql("update User set totalTopic=totalTopic+1 where username='"+username+"'");
					}
					else{
						if(Symbols.DECREASE.equals(operator)){
							super.executeHsql("update User set totalTopic=totalTopic-1 where username='"+username+"'");
						}
					}					
				}
			}
		}
		catch(DAOException de){
			throw new DAOException("更新用户中主题和贴子相关信息失败");
		}		
	}	
	
	/**
	 * 更新用户角色
	 * @param username 用户名
	 * @param roles 用户角色
	 * @throws DAOException
	 */
	public void updateRole(String username,String roles) throws DAOException {
		try{
			super.executeHsql("update User set roles='"+roles+"' where username='"+username+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新用户角色信息失败");
		}	
	}

	/**
	 * 更新用户为新角色
	 * @param oldRole 旧角色
	 * @param newRole 新角色
	 * @throws DAOException
	 */
	public void updateNewRole(String oldRole,String newRole) throws DAOException {
		try{
			super.executeHsql("update User set roles='"+newRole+"' where roles='"+oldRole+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新用户为新角色失败");
		}	
	}
	
	/**
	 * 根据操作更新用户金钱信息
	 * @param username
	 * @param money
	 * @throws DAOException
	 */
	public void updateMoney(String username,int money) throws DAOException{
		try{
			super.executeHsql("update User set money=money+"+money+" where username='"+username+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新用户金钱信息失败");
		}			
	}	
	
	/**
	 * 更新用户在线时长
	 * @param username 用户名
	 * @param time 在线时长
	 * @throws DAOException
	 */
	public void updateOnlineTime(String username,int time) throws DAOException {
		try{
			super.executeHsql("update User set onlineTime=onlineTime+"+time+" where username='"+username+"'");
		}
		catch(DAOException de){
			throw new DAOException("更新用户在线时长失败");
		}
	} 
	
	/**
	 * 锁定用户
	 * @param userId 用户编号 
	 * @param isLock 是否锁定
	 */
	public void lockUser(int userId,int isLock) throws DAOException {
		try{
			super.executeHsql("update User set isLock="+isLock+" where id="+userId);
		}
		catch(DAOException de){
			throw new DAOException("锁定用户操作发生错误...");
		}	
	}
	
	/**
	 * 设置论坛之星
	 * @param userId 用户编号 
	 * @param isStar是否论坛之星
	 */
	public void StarUser(int userId,int isStar) throws DAOException {
		try{
			super.executeHsql("update User set isStar="+isStar+" where id="+userId);
		}
		catch(DAOException de){
			throw new DAOException("设置论坛之星操作发生错误...");
		}	
	}
	/**
	 * 更新用户角色
	 * @param userId 用户编号
	 * @param roles 角色
	 * @throws DAOException
	 */
	public void updateUserRoles(int userId,String roles) throws DAOException {
		try{
			super.executeHsql("update User set roles="+roles+" where id="+userId);
		}catch(DAOException de){
			throw new DAOException("锁定用户操作发生错误...");
		}		
	}

	public List findStarUser(int num) throws DAOException {
		List stars=null;		
		stars= this.getSession().createCriteria(User.class)
		.add(Expression.eq("isStar", 1))
		.setMaxResults(num)
		.list();
		
		for(int i=0;i<stars.size();i++){
			User star=(User)stars.get(i);		
			UserFace uf=null;
			uf=(UserFace)this.getHibernateTemplate().find("from UserFace where id="+star.getId()).get(0);
			star.setUserFace(uf);
			
			List indexTopics=null;
			indexTopics=this.getSession().createCriteria(Topic.class)
			.add(Expression.like("username", star.getUsername()))
			.add(Expression.eq("isDelete", new Integer(0)))
			.setMaxResults(4)
			.addOrder(Order.desc("status"))
			.list();
			star.setIndexTopics(indexTopics);
		}
		return stars;
	}
	
}
