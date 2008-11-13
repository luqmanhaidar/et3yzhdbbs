package com.ntsky.bbs.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 主题模块数据处理接口
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public interface TopicDAO extends BaseDAO{
	
	/**
	 * 根据主题编号查找主题信息
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public Topic findTopic(long topicId) throws DAOException ;

	/**
	 * 根据主题编号查找主题信息
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public Topic viewTopic(long topicId) throws DAOException ;
	
	/**
	 * 根据贴子状态列表贴子
	 * 
	 * @param forumId 论坛编号
	 * @param categoryId 类别编号 (-1,表示不设定CategoryId)
	 * @param status 贴子状态 (1 是, 0 否)
	 * @param pagination 分页参数
	 * @return QueryResult 帖子集合
	 * @throws DataAccessException
	 */
	public QueryResult findTopics(int forumId, int categoryId, Map orderMap, int status, Pagination pagination) throws DAOException ; 
	
	
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
	public QueryResult findUserTopics(String username, int forumId, String keyword, Map orderMap, Pagination pagination) throws DAOException ;
	
	/**
	 * 取得制定论坛的贴子
	 * 
	 * @param forumId 论坛编号
	 * @param orderMap 排序数组
	 * @param pagination 分页对象
	 * @return QueryResult 论坛贴子列表
	 * @throws ServiceException
	 */
	public QueryResult findTopics (final int forumId, Map orderMap, final Pagination pagination) throws DAOException ;
	
	/**
	 * 检索帖子
	 * @param forumId 论坛编号
	 * @param type 检索类型
	 * @param keyword 检索关键字
	 * @param timePoint 时间点 当timePoint为""时，不进行时间设定
	 * @param way 时间方向
	 * @param orderMap 排序信息
	 * @param pagination 
	 * @return
	 * @throws DAOException
	 */
	public QueryResult searchTopics (int forumId, String type, String keyword, String timePoint, String way, Map orderMap,Pagination pagination,int status ) throws DAOException;	
	
	/**
	 * 根据论坛编号查找制定数目的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws DAOException
	 */
	public List findTopic(int forumId,int dataNum) throws DAOException;	
	
	/**
	 * 根据主题论坛版块编号删除主题
	 * 
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public void deleteTopicByForum(int forumId) throws DAOException ;	
	
	/**
	 * 更新贴子浏览次数
	 *  
	 * @param topicId 贴子编号
	 * @throws DAOException
	 */
	public void updateTopicViews(int topicId) throws DAOException;
	
	/**
	 * 取得主题总数
	 * @return
	 * @throws DAOException
	 */
	public int countTopic() throws DAOException;	
	
	/**
	 * 根据ForumId统计主题总数
	 * @param forums
	 * @return
	 * @throws DAOException
	 */
	public int countTopic(List forums) throws DAOException;

	/**
	 * 更新主题是否被删除状态
	 *
	 * @param topicId 主题编号
	 * @param isDelete 是否被删除
	 * isDelete 1 将主题丢弃到垃圾箱，0 主题状态正常
	 * @throws DAOException
	 */
	public void updateTopicIsDelete(int topicId,int isDelete) throws DAOException;
	
	/**
	 * 更改主题所属论坛
	 * 
	 * @param oldForumId 旧的论坛编号
	 * @param newForumId 新的论坛编号
	 * @throws DAOException
	 */
	public void updateTopicForum( int oldForumId, int newForumId ) throws DAOException;
	
	/**
	 * 更新主题状态
	 *
	 * @param topicId 主题编号
	 * @param status 是否被置顶
	 * status 1 精华贴，2 被锁定的贴子
	 * @throws DAOException
	 */
	public void updateTopicStatus(int topicId,int status) throws DAOException;	
	
	/**
	 * 物理删除主题(不可恢复)
	 * @param topicId
	 * @throws DAOException
	 */
	public void deleteTopic(int topicId) throws DAOException;
	
	/**
	 * 移动主题
	 *
	 * @param ids 主题列表
	 * @param forumId 论坛编号
	 * @throws DAOException
	 */
	public void moveTopic(int topicId,int forumId) throws ServiceException;
	
	/**
	 * 将主题丢到垃圾箱
	 * @param topicId 主题编号
	 * @throws DAOException
	 */
	public void trashTopic(int topicId) throws DAOException;
	
	/**
	 * 更新主题是否被置顶
	 *
	 * @param topicId 主题编号
	 * @param isTop 是否被置顶
	 * isTop 1 置顶，0 主题状态正常
	 * @throws DAOException
	 */
	public void updateTopicIsTop(int topicId,int isTop) throws DAOException;	

	/**
	 * 取得最新发表的帖子
	 * @param forumId 论坛编号
	 * @param num 信息数
	 * @return 主题数
	 * @throws DAOException
	 */
	public List findNewlyTopics(int forumId,int num) throws DAOException;
	
	public List findLastPostTopics(int forumId,int num) throws DAOException;
	
	
	public List getDayTopics(int forumId,int num);
	
	public List getWeekTopics(int forumId,int num);
	
	public List getGoodTopics(int forumId,int num);
	public List getHotTopics(int forumId,int num);
	public List getNewlyTopicsByUser(String username,int num);
	public Topic getRandomTopics(int forumId);
	
	public QueryResult getReferTopics(int userId,int num,Pagination pagination);
	
	/**
	 * 相关主题
	 */
	public List getInterfixTopics(String title,int num);
	
	public QueryResult findTopicsByUser(String username, Pagination pagination) throws DAOException;
}
