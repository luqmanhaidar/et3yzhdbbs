package com.ntsky.bbs.dao.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.ntsky.bbs.dao.ForumDAO;
import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.jdbc.JDBCManager;

/**
 * 论坛信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.7 $ $Date: 2008/10/25 13:47:23 $
 */
public class ForumDAOHibernateImpl extends BaseDAOHibernateImpl implements ForumDAO {
	private TopicDAO topicDAO;
	private Statement stList = null;//一定要私有private，这样才安
	private ResultSet rsList = null;
	
	/**
	 * 查找全部的论坛信息
	 * <p>
	 * 	以树行结构显示整个论坛树
	 * </p>
	 * @return List 论坛集合
	 */
	public List findForums () throws DAOException {
		try{
			return super.find("from Forum as forum order by forum.branchId ,forum.displayOrder asc");
		}
		catch(DAOException daoException){
			throw new DAOException("取得整个论坛类别树发生错误...");
		}
	}
	
	/**
	 * 根据父类编号取得论坛列表
	 * @param parentId 父类编号
	 * @return 论坛版块列表
	 * @throws ServiceException
	 */
	public List findForums(int parentId) throws DAOException {
		try{
			return super.find("from Forum as forum where forum.parentId='"+parentId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("根据parentId : ["+parentId+"]取得整个论坛版块发生错误...");
		}
	}	
	
	/**
	 * 根据论坛编号查找论坛数据
	 * @param forumId 论坛标示
	 * @return Forum 论坛数据
	 */	
	public Forum findForum(int forumId) throws DAOException {
		try{
			return (Forum) super.get(Forum.class,new Long(forumId));
		}
		catch(DAOException daoException){
			throw new DAOException("根据["+forumId+"]取得论坛信息失败.");
		}
	}
	
	/**
	 * 取得最大的分支编号
	 * @return 最大分支编号
	 */
	public int findMaxBranchId() throws DAOException {
		try{			
			Object funValue = super.findByAggregate("select max(forum.branchId) from Forum as forum");
			if(funValue==null){
				return 0;
			}
			else{
				return ((Integer)funValue).intValue();
			}
		}
		catch(DAOException daoException){
			throw new DAOException("取得最大的分支编号错误");
		}
	}	
	
	/**
	 * 取得子层中最大的分支编号
	 * 
	 * @param parentId 父节点编号
	 * @int 取得节点的排序编号
	 */
	public int findChildMaxOrder(int parentId) throws DAOException{
		try{
			Object funValue = super.findByAggregate("select max(forum.displayOrder) from Forum as forum where forum.parentId='"+parentId+"'");
			if(funValue==null){
				return 0;
			}
			else{
				return ((Integer)funValue).intValue();
			}
		}
		catch(DAOException daoException){
			throw new DAOException("取得最大的分支编号错误");
		}
	}
	
	/**
	 * 批量更新显示次序
	 * @param branchId 分支编号
	 * @param displayOrder 显示次序
	 * @throws DAOException 数据处理异常
	 */
	public void batchUpdateDisplayOrder(int branchId,int displayOrder) throws DAOException{
		try{
			super.executeHsql("update Forum set displayOrder=displayOrder+1 where branchId='"+branchId+"' and displayOrder>'"+displayOrder+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("批量更新显示次序发生错误");
		}
	}	
	
	/**
	 * 更新论坛版块序号
	 * 
	 * @param oldBranchId 旧分支编号
	 * @param newbranchId 新分支编号
	 * @throws DAOException
	 */
	public void updateForumBranch(int oldBranchId,int newbranchId) throws DAOException{
		try{
			super.executeHsql("update Forum set branchId='"+newbranchId+"' where branchId='"+oldBranchId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更新论坛版块branchId ["+newbranchId+"] 编号发生错误");
		}
	}	

	/**
	 * 查找分支是否存在
	 * @param branchId 分支编号
	 * @throws DAOException
	 */
	public boolean isExistBranch(int branchId) throws DAOException{
		List list = null;
		try{
			if(logger.isDebugEnabled()){
				logger.debug("检索分支["+branchId+"]是否存在.");
			}
			list = super.find("from Forum as forum where forum.branchId=?",new Integer(branchId));
			if(list!=null&&list.size()>0){
				return true;
			}
			else{
				return false;
			}
		}
		catch(DAOException daoException){
			throw new DAOException("判断分支是否存在发生错误.");
		}
	}
	
	/**
	 * 根据父类集合取得子论坛列表
	 * @return 子论坛集合
	 * @throws DAOException
	 */
	public List findForumsByParentEnum(String parentEnum) throws DAOException{
		List list = null;
		try{
			if(logger.isDebugEnabled()){
				logger.debug("检索条件parentEnum为 : "+parentEnum+".");
			}
			list = super.find("from Forum as forum where forum.parentEnum like '"+parentEnum+"%'");
		}
		catch(DAOException daoException){
			throw new DAOException("根据父类集合取得子论坛列表发生错误.");
		}
		return list;
	}
	
	public void doIsTop(int forumId,int isTop) throws DAOException {
		try{
			super.executeHsql("update Forum set isTop="+isTop+" where id="+forumId);
		}
		catch(DAOException de){
			throw new DAOException("设置首页显示操作发生错误...");
		}	
	}
	
	public void doIsMasters(int forumId,int isMasters) throws DAOException {
		try{
			super.executeHsql("update Forum set isMasters="+isMasters+" where id="+forumId);
		}
		catch(DAOException de){
			throw new DAOException("设置版主进入操作发生错误...");
		}	
	}
	
	public void doIsAdmin(int forumId,int isAdmin) throws DAOException {
		try{
			super.executeHsql("update Forum set isAdmin="+isAdmin+" where id="+forumId);
		}
		catch(DAOException de){
			throw new DAOException("设置管理员发帖操作发生错误...");
		}	
	}

	public List findForumsIsTop() throws DAOException {
		List forums=null; 
		try{
			forums=super.find("from Forum where isTop=1");
			for(int i=0;i<forums.size();i++){
				//把数据放在forum
				Forum forum=(Forum)forums.get(i);
				//List indexTopic=this.getSession().createCriteria(Topic.class)
				//.add(Restrictions.eq("forumId",forum.getId()))
				//.addOrder(Order.desc("isTop"))
				//.addOrder(Order.desc("id"))
				//.setMaxResults(4)
				//.list();
				
				//List indexTopic=this.getHibernateTemplate().find("from Topic where forumId="+forum.getId());
				List indexTopic=this.getSession().createCriteria(Topic.class)
				// .createAlias("Topic", "t")
				.add(Expression.eq("forumId", forum.getId().intValue()))
				.add(Expression.eq("isDelete", 0))
				.addOrder(Order.desc("isTop"))
				.addOrder(Order.desc("id"))
				.setMaxResults(4)
				.list();
				forum.setIndexTopic(indexTopic);
			}
		}
		catch(DAOException daoException){
			throw new DAOException("取得放在首页论坛版块发生错误...");
		}
		return forums;

	}
	
	public List getHotForums() throws DAOException {
		JDBCManager jdbcManager=new JDBCManager();
		Connection con=null;//这些东西提在try外面
		List list=null;
		Forum forum = null;
		try{
			list=new ArrayList();
			con=jdbcManager.getConnection();
			String sSql="";
			stList = con.createStatement();//这样就够了
			
			sSql="select * from (SELECT a.id,a.name,(select count(*) from ntsky_t_post b,ntsky_t_topic c where b.forum_id=a.id and b.topic_id=c.id and c.is_delete=0) as cnt_post FROM ntsky_t_forum a where a.parent_id!=0) t order by t.cnt_post desc limit 10";							

			rsList = stList.executeQuery(sSql);
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面
			
			//当rs有下一位
			while(rsList.next()){
				forum =new Forum();
				forum.setId(Long.parseLong(rsList.getString("id")));
				forum.setName(rsList.getString("name"));
				list.add(forum);
			}
		
			//con.close();它这里说不能达到的代码，就是说，如果try到某个地方死了，这个连接就不关闭了，不安全了
		}
		catch(Exception exception){
			logger.error(exception.getMessage());
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public TopicDAO getTopicDAO() {
		return topicDAO;
	}

	public void setTopicDAO(TopicDAO topicDAO) {
		this.topicDAO = topicDAO;
	}
}