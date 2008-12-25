package com.ntsky.bbs.service.impl;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.dao.PollDAO;
import com.ntsky.bbs.dao.PollResultDAO;
import com.ntsky.bbs.dao.PostDAO;
import com.ntsky.bbs.dao.RoleDAO;
import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.PollResult;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Role;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.BadwordSingleton;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.framework.util.DateUtil;

/**
 * 主题模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class TopicServiceImpl extends BaseServiceImpl implements TopicService{
	
	private TopicDAO topicDAO;
	public void setTopicDAO(TopicDAO topicDAO){
		this.topicDAO = topicDAO;
	}

	private PostDAO postDAO;
	public void setPostDAO(PostDAO postDAO){
		this.postDAO = postDAO;
	}

	private RoleDAO roleDAO;
	public void setRoleDAO(RoleDAO roleDAO){
		this.roleDAO = roleDAO;
	}
	
	private PollDAO pollDAO;
	public void setPollDAO(PollDAO pollDAO){
		this.pollDAO = pollDAO;
	}
	
	private PollResultDAO pollResultDAO;
	public void setPollResultDAO(PollResultDAO pollResultDAO){
		this.pollResultDAO = pollResultDAO;
	}	
	
	/**
	 * 创建主题
	 * <ol>
	 * <li>创建主题</li>
	 * <li>创建第一条帖子信息</li>
	 * <li>投票信息</li>
	 * <li>更新论坛内存</li>
	 * <li>更新角色(根据文章总是判断是否要更新角色)</li>
	 * </ol>
	 * 
	 * @param topic 主题信息
	 * @param firstPost 第一条帖子信息
	 * @param poll 投票信息 
	 */
	public void createTopic(Topic topic,Post firstPost,Poll poll) throws ServiceException {
		try{
			Date date = new Date();
			// 创建主题
			topic.setDateCreated(date);
			/*if(logger.isDebugEnabled()){
				logger.debug("新帖标题 : " + topic.getTitle());
			}*/			
			topic.setLastPostUser(topic.getUsername());
			topic.setReplies(0);
			topic.setLastPostTime(date);
			topic.setTitle(BadwordSingleton.getInstance().replaceString(topic.getTitle()));
			topicDAO.save(topic);
			
			// 创建顶层帖子
			topic = topicDAO.findTopic(topic.getId());
			firstPost.setForumId(topic.getForumId());
			firstPost.setTopicId(topic.getId().intValue());
			firstPost.setForumId(topic.getForumId());
			firstPost.setTitle(BadwordSingleton.getInstance().replaceString(topic.getTitle()));
			firstPost.setContent(BadwordSingleton.getInstance().replaceString(firstPost.getContent()));
			firstPost.setDateCreated(date);
			firstPost.setTopicId(topic.getId().intValue());
			//topic.addPost(firstPost);
			postDAO.save(firstPost);

			// 投票帖
			if(topic.getIsVote()==1){
				poll.setTopicId(topic.getId().intValue());
				poll.setDateCreated(new Date());
				pollDAO.save(poll);
				
				//poll = (Poll) pollDAO.load(Poll.class,poll.getId());
				// 创建投票选项
				Object[] pollResultArray = poll.getPollResults().toArray();
				PollResult pollResult = null;
				for (int i = 0; i < pollResultArray.length; i++) {
					pollResult = (PollResult)pollResultArray[i];
					//pollResult.setPollId(poll.getId().intValue());
					//pollResult.setPoll(poll);
					poll.addPollResult(pollResult);
					pollResultDAO.save(pollResult);
				}
			}

			// 更新Forum内存 1、最后的提交的回复 2、主题总数 
			List list = topicDAO.findTopic(topic.getForumId(),1);
			Topic lastTopic = null;
			if(list!=null&&list.size()>0){
				lastTopic = (Topic)list.toArray()[0];
			}
			ForumSingleton.getInstance().setLastPostTopic(topic.getForumId(),lastTopic);
			ForumSingleton.getInstance().setTopicCount(topic.getForumId(),Symbols.INCREASE);
			ForumSingleton.getInstance().setPostCount(topic.getForumId(),Symbols.INCREASE);
			ForumSingleton.getInstance().setTodayPostCount(topic.getForumId(),Symbols.INCREASE);
			
			// 如果是guest用户就不需要如下操作
			if(!(Symbols.GUEST.equals(topic.getUsername()))){
				// 发表主题增加金钱数
				userDAO.updateMoney(topic.getUsername(),SystemConfig.getInstance().getIntPropertyValue(Symbols.MONEY,Symbols.MONEY_ADD_TOPIC));
				
				// 更新用户信息(用户发表贴子数+1,主题+1)
				userDAO.updateAboutPost(topic.getUsername(),Symbols.TOPIC,Symbols.INCREASE);
				userDAO.updateAboutPost(topic.getUsername(),Symbols.POST,Symbols.INCREASE);
	
				// 更新角色
				User user = userDAO.findUser(topic.getUsername());
				// 如果是注册用户组用户进行下面步骤
				if(RoleSingleton.getInstance().getRole(user.getRoles()).getType()==0){
					Role role = roleDAO.findRole(user.getTotalTopic());
					// 用户角色和判断出来的角色一致不更新用户角色
					if(!role.getId().toString().equals(user.getRoles())){
						userDAO.updateRole(user.getUsername(),role.getId().toString());
					}
				}
			}
		}
		catch(DAOException de){
			throw new ServiceException("创建主题 ['"+topic.getTitle()+"'] 发生错误.");
		}
		catch(Exception ex){
			logger.error("创建主题 ['"+topic.getTitle()+"'] 发生错误.");
			throw new ServiceException("创建主题 ['"+topic.getTitle()+"'] 发生错误.");
		}
	}
	
	/**
	 * 取得用户发表的帖子 
	 * @param username 用户名
	 * @param forumId 论坛序号
	 * @param keyword 标题关键字
	 * @param orderMap 排序map
	 * @param pagination 分页对象
	 * @return 查询结果
	 * @throws DAOException
	 */
	public QueryResult getUserTopics(String username, int forumId, String keyword, Map orderMap, Pagination pagination) throws ServiceException {
		try {
			return topicDAO.findUserTopics(username, forumId, keyword, orderMap, pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	
	public QueryResult getRefTopics(int userId,int num , Pagination pagination) throws ServiceException {
		try {
			return topicDAO.getReferTopics(userId, num, pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	/**
	 * 修改主题
	 * @param topic 主题信息
	 * @param firstPost 第一条帖子
	 */
	public void editTopic(Topic topic,Post firstPost) throws ServiceException {
		try{
			Topic tempTopic = topicDAO.findTopic(topic.getId());
			tempTopic.setTitle(BadwordSingleton.getInstance().replaceString(topic.getTitle()));
			tempTopic.setMood(topic.getMood());
			tempTopic.setCategoryId(topic.getCategoryId());
			topicDAO.update(tempTopic);
			
			// 修改post中第一个主题信息
			Post tempPost = postDAO.findPost(firstPost.getId());
			tempPost.setTitle(BadwordSingleton.getInstance().replaceString(firstPost.getTitle()));
			tempPost.setContent(BadwordSingleton.getInstance().replaceString(firstPost.getContent()));
			postDAO.update(tempPost);
		}
		catch(DAOException de){
			throw new ServiceException("修改主题 ['"+topic.getTitle()+"'] 发生错误.");
		}
	}
	
	/**
	 * 根据主题编号删除主题(物理删除)
	 * @param topicId 主题编号 
	 */
	public void deleteTopic(int topicId) throws ServiceException {
		try{
			Topic topic = topicDAO.findTopic(topicId);
			topicDAO.delete(topic);
		}
		catch(DAOException de){
			throw new ServiceException(de.getMessage());
		}
	}
	
	/**
	 * 删除主题,将主题丢到垃圾箱
	 * @param topicId
	 * @throws ServiceException
	 */
	public void trashTopic(int topicId) throws ServiceException {
		try{
			topicDAO.trashTopic(topicId);
			
			Topic topic = topicDAO.findTopic(new Long(topicId));
			// 更新Forum内存 1、最后的提交的回复 2、主题总数 
			List list = topicDAO.findTopic(topic.getForumId(),1);
			Topic lastPostTopic = null;
			if(list!=null&&list.size()>0){
				lastPostTopic = (Topic)list.toArray()[0];
			}
			ForumSingleton.getInstance().setLastPostTopic(topic.getForumId(),lastPostTopic);
			ForumSingleton.getInstance().setTopicCount(topic.getForumId(),Symbols.DECREASE);
			ForumSingleton.getInstance().setPostCount(topic.getForumId(),Symbols.DECREASE);
			ForumSingleton.getInstance().setTodayPostCount(topic.getForumId(),Symbols.DECREASE);			
			
			// 如果是guest用户就不需要如下操作
			if(!(Symbols.GUEST.equals(topic.getUsername()))){
				// 删除主题减少金钱数
				userDAO.updateMoney(topic.getUsername(),SystemConfig.getInstance().getIntPropertyValue(Symbols.MONEY,Symbols.MONEY_DELETE_TOPIC));
				
				// 更新用户信息(用户发表贴子数-1,主题-1)
				userDAO.updateAboutPost(topic.getUsername(),Symbols.TOPIC,Symbols.DECREASE);
				userDAO.updateAboutPost(topic.getUsername(),Symbols.POST,Symbols.DECREASE);
	
				// 更新角色
				User user = userDAO.findUser(topic.getUsername());
				// 如果是管理组用户不需要进行下面步骤
				if(RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(user.getUsername())).getType()==0){
					Role role = roleDAO.findRole(user.getTotalTopic());
					// 用户角色和判断出来的角色一致不更新用户角色
					if(!role.getId().toString().equals(user.getRoles())){
						userDAO.updateRole(user.getUsername(),role.getId().toString());
					}
				}
			}
		}
		catch(DAOException de){
			throw new ServiceException("删除主题topicId ['"+topicId+"'] 发生错误.");
		}
	}
	
	/**
	 * 根据主题编号取得主题信息
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public Topic getTopic(int topicId) throws ServiceException {
		try{
			Topic tmp=topicDAO.findTopic(topicId);			
			tmp.setAlias(userDAO.findUser(tmp.getUsername()).getAlias());
			return tmp;
			
		}
		catch(DAOException de){
			throw new ServiceException("查找主题topicId ['"+topicId+"'] 发生错误.");
		}
	}
	
	/**
	 * 根据主题编号取得主题信息(该主题包含内容)
	 * @param topicId
	 * @return
	 * @throws ServiceException
	 */
	public Topic getEditedTopic(int topicId) throws ServiceException{
		try{
			Topic topic = topicDAO.findTopic(topicId);
			List list = postDAO.findPosts(topicId,1);
			if(list!=null && list.size()>0){
				topic.setFirstPost(((Post)(list.toArray()[0])));
			}
			else{
				throw new ServiceException(null);
			}
			return topic;
		}
		catch(DAOException de){
			throw new ServiceException("查找主题topicId ['"+topicId+"'] 发生错误.");
		}
		catch(ServiceException se){
			throw new ServiceException("查找待修改的主题发生错误...");
		}
	}	
	
	/**
	 * 查看论坛贴子
	 * <ol>
	 * <li>更新贴子浏览次数</li>
	 * <li>列表主题对应的全部贴子</li>
	 * </ol>
	 * 
	 * @param topicId 贴子编号
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws ServiceException
	 */
	public QueryResult viewTopic(int topicId,Pagination pagination) throws ServiceException{		
		try {
			//更新贴子浏览次数
			topicDAO.updateTopicViews(topicId);
			QueryResult queryResult = postDAO.findPosts(topicId,pagination);
			List posts = queryResult.getItems();
			Object[] postArray = posts.toArray();
			Post post = null;
			for (int i = 0; i < postArray.length; i++) {
				post = (Post)postArray[i];
				// GUEST用户就不处理
				if(post.getUserId() != 0){
					post.setUser(userDAO.findUser(new Long(post.getUserId())));
				}
				else{
					// 设置Guest用户信息
					post.setUser(Symbols.getGuest());
				}
			}
			return queryResult;
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}	
	
	/**
	 * 根据论坛编号删除对应编号的帖子
	 * 
	 * @param forumId 论坛编号
	 */
	public void deleteTopicByForum(int forumId) throws ServiceException{
		try {
			topicDAO.deleteTopicByForum(forumId);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 根据贴子状态列表贴子
	 * 
	 * @param forumId 论坛编号
	 * @param categoryId 类别编号 (-1,表示不设定CategoryId)
	 * @param orderMap 排序数组
	 * @param status 贴子状态 (1 精华, 2 锁定)
	 * @param pagination 分页对象
	 * @return QueryResult 帖子集合
	 * @throws ServiceException 
	 */
	public QueryResult getTopics(int forumId ,int categoryId, Map orderMap, int status, Pagination pagination) throws ServiceException {
		try {
			return topicDAO.findTopics(forumId,categoryId,orderMap,status,pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	} 
	
	/**
	 * 帖子检索 
	 * @param forumId 论坛编号
	 * @param type 检索类型
	 * @param keywordValue 关键子的值 
	 * @param time 时间段
	 * @param way 时间方向
	 * @param orderMap 排序信息
	 * @param pagination 分页
	 * @return
	 * @throws ServiceException
	 */
	public QueryResult searchTopics(int forumId, String type, String keyword, int time, String way, Map orderMap, Pagination pagination,int status,int isMaster) throws ServiceException{
		try{
			String wayTime = null;
			if(time == 0){
				wayTime = "";
			}
			else{
				wayTime = DateUtil.getBeforeDate(DateUtil.getDate(),time);
			}
			return topicDAO.searchTopics(forumId,type,keyword,wayTime,way,orderMap,pagination,status,isMaster);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
	
	/**
	 * 取得制定论坛的贴子
	 * 
	 * @param forumId 论坛编号
	 * @param orderMap 排序数组
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws ServiceException
	 */
	public QueryResult getTopics(int forumId, Map orderMap, Pagination pagination) throws ServiceException{
		try{
			//return topicDAO.findTopics(forumId,orderMap,pagination);
			QueryResult qr=topicDAO.findTopics(forumId,orderMap,pagination);
			List topics = qr.getItems();
			Object[] topicArray = topics.toArray();
			Topic topic = null;
			for (int i = 0; i < topicArray.length; i++) {
				topic = (Topic)topicArray[i];
				topic.setAlias(userDAO.findUser(topic.getUsername()).getAlias());
			}
			return qr;
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}		

	/**
	 * 更新主题是否被删除状态
	 *
	 * @param topicId 主题编号
	 * @param isDelete 是否被删除
	 * isDelete 0 将主题丢弃到垃圾箱，1 主题状态正常
	 * @throws ServiceException
	 */
	public void updateTopicIsDelete(int topicId,int isDelete) throws ServiceException{
		try{
			topicDAO.updateTopicIsDelete(topicId,isDelete);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}		
	}
	
	/**
	 * 更新主题状态
	 *
	 * @param topicId 主题编号
	 * @param status 是否被置顶
	 * status 1 精华贴，2 被锁定的贴子
	 * @throws ServiceException
	 */
	public void updateTopicStatus(int topicId,int status) throws ServiceException{
		try{
			if(status == 1){
				// 设置精华帖增加金钱数
				userDAO.updateMoney(topicDAO.findTopic(topicId).getUsername(),SystemConfig.getInstance().getIntPropertyValue(Symbols.MONEY,Symbols.MONEY_ELITE_TOPIC));				
			}
			topicDAO.updateTopicStatus(topicId,status);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}			
	}	
	
	/**
	 * 更新主题是否被置顶
	 *
	 * @param topicId 主题编号
	 * @param isTop 是否被置顶
	 * isTop 1 置顶，0 主题状态正常
	 * @throws DAOException
	 */
	public void updateTopicIsTop(int topicId,int isTop) throws ServiceException{
		try{
			topicDAO.updateTopicIsTop(topicId,isTop);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}		
	}

	/**
	 * 取得主题总数
	 * @return
	 * @throws ServiceException
	 */
	public int countTopic() throws ServiceException {
		try{
			return topicDAO.countTopic();
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}
	
	/**
	 * 移动主题
	 *
	 * @param ids 主题列表
	 * @param oldForumId 主题原来所在的论坛
	 * @param newForumId 主题所在新的论坛
	 * @throws DAOException
	 */
	public void moveTopic(int[] ids,int oldForumId,int newForumId) throws ServiceException{
		try{
			for (int i = 0; i < ids.length; i++) {
				// 主题
				topicDAO.moveTopic(ids[i],newForumId);
				// 帖子
				postDAO.movePost(ids[i],newForumId);
			}
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}	
	
	/**
	 * 取得最新评论的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws ServiceException
	 */
	public Topic getLastPostTopic(int forumId,int dataNum) throws ServiceException{
		try{
			List list = topicDAO.findTopic(forumId,dataNum);
			if(list!=null&&list.size()>0){
				return (Topic)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}	
	
	/**
	 * 取得最新回复的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws ServiceException
	 */
	public List getLastPostTopics(int forumId,int dataNum) throws ServiceException{
		try{
			return topicDAO.findLastPostTopics(forumId,dataNum);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}	

	/**
	 * 取得最新发表的帖子
	 * @param forumId 论坛编号
	 * @param num 信息数
	 * @return 主题数
	 * @throws ServiceException
	 */
	public List getNewlyTopics(int forumId,int num) throws ServiceException{
		try{
			return topicDAO.findNewlyTopics(forumId,num);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}
	
	
	/**
	 * 取得24小时内回复最多的帖子
	 * @param forumId 论坛编号
	 * @param num 信息数
	 * @return 主题数
	 * @throws ServiceException
	 */
	public List getDayTopics(int forumId,int num){
		try{
			return topicDAO.getDayTopics(forumId,num);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}
	
	/**
	 * 取得一周内回复最多的帖子
	 * @param forumId 论坛编号
	 * @param num 信息数
	 * @return 主题数
	 * @throws ServiceException
	 */
	public List getWeekTopics(int forumId,int num){
		try{
			return topicDAO.getWeekTopics(forumId,num);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}
	
	/**
	 * 取得热门话题
	 * @param forumId 论坛编号
	 * @param num 信息数
	 * @return 主题数
	 * @throws ServiceException
	 */
	public List getHotTopics(int forumId,int num){
		try{
			return topicDAO.getHotTopics(forumId, num);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}	
	}

	public List getGoodTopics(int forumId, int num) {
		// TODO Auto-generated method stub
		return topicDAO.getGoodTopics(forumId, num);
	}

	public List getNewlyTopicsByUser(String username, int num) {
		
		return topicDAO.getNewlyTopicsByUser(username, num);
	}
	
	public Topic getRandomTopics(int forumId){
		return topicDAO.getRandomTopics(forumId);
	}

	public List getInterfixTopics(String title, int num) {
		return topicDAO.getInterfixTopics(title, num);
	}
	
	public QueryResult findTopicsByUser(String username,Pagination pagination) throws ServiceException{
		try{
			return topicDAO.findTopicsByUser(username, pagination);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}

	@Override
	public void doIsStatus(int topicId, int status) throws ServiceException {
		// TODO Auto-generated method stub
		try{
			 topicDAO.doIsStatus(topicId, status);
		}
		catch(DAOException daoException){
			throw new ServiceException(daoException.getMessage());
		}
	}
}
