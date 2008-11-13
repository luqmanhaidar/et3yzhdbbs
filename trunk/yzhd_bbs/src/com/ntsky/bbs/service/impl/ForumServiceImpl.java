package com.ntsky.bbs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.dao.ForumDAO;
import com.ntsky.bbs.dao.PostDAO;
import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.dao.CategoryDAO;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 论坛模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class ForumServiceImpl extends BaseServiceImpl implements ForumService{

	private static Logger logger = Logger.getLogger(ForumServiceImpl.class);
	
	private ForumDAO forumDAO;
	private TopicDAO topicDAO;
	private PostDAO postDAO;
	private CategoryDAO categoryDAO;
	
	// 论坛数据处理对象
	public void setForumDAO(ForumDAO forumDAO){
		this.forumDAO = forumDAO;
	}
	//　主题数据处理对象
	public void setTopicDAO(TopicDAO topicDAO){
		this.topicDAO = topicDAO;
	}
	public void setPostDAO(PostDAO postDAO){
		this.postDAO = postDAO;
	}
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}	
	
	
	/**
	 * 取得最大的分枝编号
	 * @return
	 */
	/*private int getMaxBranchId() {
		return forumDAO.findMaxBranchId();
	}*/
	

	/**
	 * 创建论坛
	 * <ul>
	 * 	<li>创建论坛信息</li>
	 *  <li>创建管理员</li>
	 *  <li>添加信息到内存</li>
	 * </ul>
	 * @param forum 论坛数据
	 */
	public void createForum(Forum forum) throws ServiceException {
		
		// 父类集合(第一层的情况) 
		if(forum.getParentId()==0){
			forum.setParentEnum("0");
			forum.setDepth(1);
			int maxBranchId = 0;
			try {
				maxBranchId = forumDAO.findMaxBranchId()+1;
			}
			catch(DAOException daoException){
				throw new ServiceException(daoException.getMessage());
			}
			/*if(logger.isDebugEnabled()){
				logger.debug("max branch no is " + maxBranchId);
			}*/
			forum.setBranchId(maxBranchId);
			forum.setDisplayOrder(1);
		}
		else{
			// 第二层以后的场合
			// 父节点信息
			Forum tempForum = null;
			try {
				tempForum = forumDAO.findForum(forum.getParentId());
			}
			catch(DAOException daoException){
				throw new ServiceException(daoException.getMessage());
			}
			// 深度(父节点+1)
			forum.setDepth(tempForum.getDepth()+1);
			// 如果父节点的父节点为0 说明是第一层分类
			if("0".equals(tempForum.getParentEnum())){
				forum.setParentEnum(String.valueOf(tempForum.getId())+",");
			}
			else{
				// 父节点的父节点的分类+父节点ID
				forum.setParentEnum(tempForum.getParentEnum()+tempForum.getId()+",");
			}			
			forum.setBranchId(tempForum.getBranchId());
			
			// 显示次序
			// 取的下一层中最大的分支编号
			int displayOrder = forumDAO.findChildMaxOrder(forum.getParentId());
			if(displayOrder==0){
				displayOrder = tempForum.getDisplayOrder();
			}
			/*if(logger.isDebugEnabled()){
				logger.debug("父节点["+tempForum.getName()+"]下的最大子节点编号 : " + displayOrder);
			}*/
			// 所有比displayOrder大的排序编号都+1
			forumDAO.batchUpdateDisplayOrder(forum.getBranchId(),displayOrder);
			// 当前最大编号为父类下的子类的最大编号+1
			forum.setDisplayOrder(displayOrder+1);
		}
		forum.setDateCreated(new Date());
		// 保存论坛信息到数据库
		try{
			forumDAO.save(forum);
		}
		catch(DAOException daoException){
			throw new ServiceException("保存论坛版块["+forum.getName()+"]发生错误.");
		}
		// 同步内存
		ForumSingleton.getInstance().addForum(forum);
	}
	
	/**
	 * 修改论坛
	 * 
	 * <pre>
	 * 　
	 * </pre>
	 * <ol>
	 * 	<li>则修改论坛信息</li>
	 *  <li>
	 *  	更新版主角色
	 *  	<ul>
	 *  		<li>更新旧版主角色为注册用户</li>
	 *  		<li>更新新版主角色</li>
	 *  	</ul>
	 *  </li>
	 *  <li>更新内存</li>
	 * </ol>
	 * @param forum 论坛数据
	 */
	public void editForum(Forum forum) throws ServiceException {
		Forum tempForum = null;
		try{
			tempForum = forumDAO.findForum(forum.getId().intValue());
			tempForum.setName(forum.getName());
			tempForum.setDisplayOrder(forum.getDisplayOrder());
			tempForum.setDescription(forum.getDescription());
			tempForum.setParentId(forum.getParentId());
			String oldMasters = tempForum.getMasters();
			tempForum.setMasters(forum.getMasters());
			tempForum.setRules(forum.getRules());
			tempForum.setSignImage(forum.getSignImage());
			forumDAO.update(tempForum);

			// 如果新的版主和旧的不一致
			if (oldMasters != forum.getMasters()) {
				// 更新旧版主信息
				String[] oldMasterArray = StringUtil.splitStringToArray(forum
						.getMasters(), ",");
				for (int i = 0; i < oldMasterArray.length; i++) {
					userDAO.updateRole(String.valueOf(oldMasterArray[i]), "4");
				}

				// 更新新版主信息
				String[] masterArray = StringUtil.splitStringToArray(forum
						.getMasters(), ",");
				for (int i = 0; i < masterArray.length; i++) {
					userDAO.updateRole(String.valueOf(masterArray[i]), "3");
				}
			}
			// 更新内存
			ForumSingleton.getInstance().resetForum(tempForum);
		} catch (DAOException daoException) {
			throw new ServiceException("更新论坛版块[" + forum.getName() + "]发生错误.");
		}
	}
	
	/**
	 * 删除论坛
	 * 
	 * <ul>
	 * 	<li>删除数据库中论坛信息</li>
	 *  <li>删除内存中论坛信息</li>
	 * </ul>
	 * 
	 * @param forumId 论坛编号
	 */
	public void deleteForum(int forumId) throws ServiceException {
		try {
			Forum forum=getForum(forumId);
			List forumChildren=this.getForums(forumId);
			for(int i=0;i<forumChildren.size();i++){
				Forum child=(Forum)forumChildren.get(i);
				forumDAO.delete(child);
				// 删除论坛对应的帖子
				topicDAO.deleteTopicByForum(child.getId().intValue());
				// 删除内存中信息
				ForumSingleton.getInstance().deleteForum(child.getId().intValue());
			}			
			forumDAO.delete(forum);
			// 删除论坛对应的帖子
			topicDAO.deleteTopicByForum(forumId);
			// 删除内存中信息
			ForumSingleton.getInstance().deleteForum(forumId);
		}
		catch(DAOException daoException){
			throw new ServiceException("删除forumId[\""+forumId+"\"]对应论坛版块发生错误.");
		}
	}
	
	/**
	 * 取得全部的论坛信息和每个论坛最新发表的贴子
	 * 
	 * <pre>功能列表</pre>
	 * <ul>
	 *  	<li>WEB初始化将Forums数据载入内存</li>
	 *  	<li>取得论坛每个版块最新发表的贴子</li>
	 *  	<li>将今日帖子总数、主题总数、贴子总数载入内存</li>
	 * </ul>
	 * 
	 * @return List 论坛信息集合
	 */
	public List getForums() throws ServiceException {
		try {
			List forums = forumDAO.findForums();
			
			String date = DateUtil.getDate("yyyy-MM-dd");
			String beginTime = date+" 00:00:00";
			String endTime = date+" 23:59:59";
			// 内存数据填充
			Object[] forumArray = forums.toArray();
			Forum forum = null;
			for (int i = 0; i < forumArray.length; i++) {
				// 论坛最新发表的贴子
				forum = (Forum)forumArray[i];
				List list = topicDAO.findTopic(forum.getId().intValue(),1);
				if(list!=null&&list.size()>0){
					forum.setLastPostTopic((Topic)list.toArray()[0]);
					// 如果有今天发表的帖子
					if(((Topic)list.toArray()[0]).getDateCreated().getDate() == (new Date()).getDate()){
						// 论坛状态设为1
						forum.setStatus(1);
					}
					else{
						// 论坛状态设为0
						forum.setStatus(0);
					}					
				}
				else{
					forum.setLastPostTopic(null);
				}
				
				// 今日帖子总数、主题总数、贴子总数载入内存
				// 根据Forum_enum取得forum对应的子Forum
				List children = null;
				if(forum.getParentId()==0){
					children = forumDAO.findForumsByParentEnum(forum.getId() + ",");
					// 自身
					children.add(forum);
				}
				else{
					children = forumDAO.findForumsByParentEnum(forum.getParentEnum() + forum.getId() + ",");
					children.add(forum);
				}
				forum.setTotalTopic(topicDAO.countTopic(children));
				forum.setTotalPost(postDAO.countPost(children));
				forum.setTotalTodayPost(postDAO.countTodayPost(children,beginTime,endTime));
			}
			
			return forums;
		}
		catch(DAOException daoException){
			logger.error("列表论坛，载入内存发生错误");
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 根据论坛编号取得论坛某条全部信息
	 * 
	 * @param forumId 论坛标示
	 * @return Forum 论坛数据
	 */	
	public Forum getForum(int forumId) throws ServiceException {
		try {
			return forumDAO.findForum(forumId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	/**
	 * 合并论坛
	 * <ol>
	 * 	<li>将主题源信息中论坛编号更新成目标论坛的编号</li>
	 *  <li>将Category信息中论坛编号更新成目标论坛的编号</li>
	 * 	<li>删除源论坛数据</li>
	 * </ol>
	 * 
	 * @param sourceForum 源论坛编号
	 * @param toForum　目标论坛编号
	 * @return
	 */
	public void uniteForum(int sourceForum, int toForum) throws ServiceException {
		
		//1、 将源信息中论坛编号更新成新的论坛编号
		ForumSingleton forumSingleton = ForumSingleton.getInstance();
		Forum forum = forumSingleton.getForum(sourceForum);
		try {
			// 根据Forum_enum取得forum对应的子Forum
			List children = null;
			if(forum.getParentId()==0){
				children = forumDAO.findForumsByParentEnum(forum.getId() + ",");
				// 自身
				children.add(forum);
			}
			else{
				children = forumDAO.findForumsByParentEnum(forum.getParentEnum() + forum.getId() + ",");
				children.add(forum);
			}
			Forum tempForum = null;
			// 将forum和子forum的forumId编号更新成新的forumId编号
			for (int i = 0; i < children.size(); i++) {
				tempForum = (Forum)children.get(i);
				topicDAO.updateTopicForum(tempForum.getId().intValue(), toForum);
			}
			
			//2、更新类别信息中的Category为新的category
			for (int i = 0; i < children.size(); i++) {
				tempForum = (Forum)children.get(i);
				categoryDAO.updateCategoryForum(tempForum.getId().intValue(), toForum);
			}		
			
			//3、删除原来的forum和子forum的信息
			for (int i = 0; i < children.size(); i++) {
				tempForum = (Forum)children.get(i);
				forumDAO.delete(tempForum);
			}
		}
		catch(DAOException ex){
			logger.error("合并论坛发生错误..",ex);
			throw new ServiceException("合并论坛发生错误");
		}
		// 重新读取论坛内存
		forumSingleton.resetForums(this.getForums());
	}
		
	/**
	 * 根据父类编号取得论坛列表
	 * @param parentId 父类编号
	 * @return 论坛列表
	 * @throws ServiceException
	 */
	public List getForums(int parentId) throws ServiceException{
		try {
			return forumDAO.findForums(parentId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}	
	
	/**
	 * 根据热门论坛
	 * @return 论坛列表
	 * @throws ServiceException
	 */
	public List getHotForums() throws ServiceException{
		try {
			return forumDAO.getHotForums();
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}	
	
	/**
	 * 更新论坛版块序号
	 * 
	 * @param oldBranchId 旧分支编号
	 * @param newbranchId 新分支编号
	 * @throws DAOException
	 */
	public boolean updateForumBranch(int oldBranchId,int newbranchId) throws ServiceException{
		boolean isUpdateSuccess = false;
		try {
			// 如果当前的分支存在
			if(!forumDAO.isExistBranch(newbranchId)){
				/*if(logger.isDebugEnabled()){
					logger.debug("更新旧分支 ["+oldBranchId+"] 编号为 : " + newbranchId);
				}*/
				forumDAO.updateForumBranch(oldBranchId,newbranchId);
				isUpdateSuccess = true;
			}
			
			// 重新刷新内存
			ForumSingleton.getInstance().resetForums(getForums());
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
		return isUpdateSuccess;  
	}
	
	public void doIsTop(int forumId,int isTop) throws ServiceException{
		try{
			forumDAO.doIsTop(forumId, isTop);
		}
		catch(DAOException DAOException){
			throw new ServiceException("设置首页显示操作发生错误...");
		}	
	}
	
	public void doIsAdmin(int forumId,int isAdmin) throws ServiceException{
		try{
			forumDAO.doIsAdmin(forumId, isAdmin);
		}
		catch(DAOException DAOException){
			throw new ServiceException("设置管理员发帖操作发生错误...");
		}	
	}
	
	public void doIsMasters(int forumId,int isMasters) throws ServiceException{
		try{
			forumDAO.doIsMasters(forumId, isMasters);
		}
		catch(DAOException DAOException){
			throw new ServiceException("设置版主进入操作发生错误...");
		}	
	}
	public List findForumsIsTop() throws DAOException {
		try {
			return forumDAO.findForumsIsTop();
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
}
