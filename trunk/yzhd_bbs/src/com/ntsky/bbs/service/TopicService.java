package com.ntsky.bbs.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 主题模块业务处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface TopicService extends BaseService{
	
	/**
	 * 创建主题
	 * <ol>
	 * <li>创建主题</li>
	 * <li>创建第一条帖子信息</li>
	 * <li>投票信息</li>
	 * </ol>
	 * 
	 * @param topic 主题信息
	 * @param firstPost 第一条帖子信息 
	 * @param poll 投票信息 
	 */
	public void createTopic(Topic topic,Post firstPost,Poll poll) throws ServiceException;
	
	/**
	 * 修改主题
	 * @param topic 主题信息
	 * @param firstPost 第一条帖子信息 
	 */
	public void editTopic(Topic topic,Post firstPost) throws ServiceException;
	
	/**
	 * 根据主题编号删除主题(物理删除)
	 * @param topicId 主题编号 
	 */
	public void deleteTopic(int topicId) throws ServiceException;
	
	/**
	 * 删除主题,将主题丢到垃圾箱(逻辑删除)
	 * @param topicId
	 * @throws ServiceException
	 */
	public void trashTopic(int topicId) throws ServiceException ;	
	
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
	public QueryResult getUserTopics(String username, int forumId, String keyword, Map orderMap, Pagination pagination) throws ServiceException ;
	
	
	public QueryResult getRefTopics(int userId,int num , Pagination pagination) throws ServiceException;
	
	/**
	 * 根据主题编号取得主题信息
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public Topic getTopic(int topicId) throws ServiceException;
	
	/**
	 * 根据主题编号取得主题信息(该主题包含内容)
	 * @param topicId
	 * @return
	 * @throws ServiceException
	 */
	public Topic getEditedTopic(int topicId) throws ServiceException;
	
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
	public QueryResult viewTopic(int topicId,Pagination pagination) throws ServiceException;
	
	/**
	 * 取得制定论坛的贴子
	 * 
	 * @param forumId 论坛编号
	 * @param orderMap 排序数组
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws ServiceException
	 */
	public QueryResult getTopics(int forumId, Map orderMap,Pagination pagination) throws ServiceException;
	
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
	public QueryResult getTopics(int forumId ,int categoryId, Map orderMap, int status, Pagination pagination) throws ServiceException;	

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
	public QueryResult searchTopics(int forumId, String type, String keyword, int time, String way, Map orderMap, Pagination pagination,int status,int isMaster) throws ServiceException;
	
	/**
	 * 更新主题是否被删除状态
	 *
	 * @param topicId 主题编号
	 * @param isDelete 是否被删除
	 * isDelete 1 将主题丢弃到垃圾箱，0 主题状态正常
	 * @throws ServiceException
	 */
	public void updateTopicIsDelete(int topicId,int isDelete) throws ServiceException;
	
	/**
	 * 更新主题状态
	 *
	 * @param topicId 主题编号
	 * @param status 是否被置顶
	 * status 1 精华贴，2 被锁定的贴子
	 * @throws DAOException
	 */
	public void updateTopicStatus(int topicId,int status) throws ServiceException;	

	/**
	 * 移动主题
	 *
	 * @param ids 主题列表
	 * @param oldForumId 主题原来所在的论坛
	 * @param newForumId 主题所在新的论坛
	 * @throws DAOException
	 */
	public void moveTopic(int[] ids,int oldForumId,int newForumId) throws ServiceException;
	
	/**
	 * 更新主题是否被置顶
	 *
	 * @param topicId 主题编号
	 * @param isTop 是否被置顶
	 * isTop 1 置顶，0 主题状态正常
	 * @throws DAOException
	 */
	public void updateTopicIsTop(int topicId,int isTop) throws ServiceException;	

	/**
	 * 取得主题总数
	 * @return
	 * @throws ServiceException
	 */
	public int countTopic() throws ServiceException;

	/**
	 * 取得最新评论的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws ServiceException
	 */
	public Topic getLastPostTopic(int forumId,int dataNum) throws ServiceException;	
	
	/** * 取得最新回复的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws ServiceException
	 */
	public List getLastPostTopics(int forumId,int dataNum) throws ServiceException;	

	/**
	 * 取得最新发表的帖子
	 * @param forumId 论坛编号
	 * @param num 信息数
	 * @return 主题数
	 * @throws ServiceException
	 */
	public List getNewlyTopics(int forumId,int num) throws ServiceException;
	
	public List getDayTopics(int forumId,int num);
	
	/**
	 * 一周热门
	 * @param forumId
	 * @param num
	 * @return
	 */
	public List getWeekTopics(int forumId,int num);
	
	public List getGoodTopics(int forumId,int num);
	
	public List getHotTopics(int forumId,int num);
	public Topic getRandomTopics(int forumId);
	
	public List getNewlyTopicsByUser(String username,int num);
	
	public List getInterfixTopics(String title,int num);
	
	public QueryResult findTopicsByUser(String username,Pagination pagination) throws ServiceException;
	
}
