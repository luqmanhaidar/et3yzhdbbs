package com.ntsky.bbs.dao.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.ntsky.bbs.dao.TopicDAO;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.jdbc.JDBCManager;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 帮助信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.28 $ $Date: 2008/10/26 16:41:30 $
 * @version $Revision: 1.28 $ $Date: 2008/10/26 16:41:30 $
 */
public class TopicDAOHibernateImpl extends BaseDAOHibernateImpl implements TopicDAO {

	/**
	 * 取得制定论坛的贴子
	 * 
	 * @param forumId 论坛编号
	 * @param categoryId 类别编号 (-1,表示不设定CategoryId)
	 * @param status 贴子状态 (1 精华, 0 全部)
	 * @param pagination 分页参数
	 * @return QueryResult 帖子集合
	 * @throws DataAccessException
	 */
	private Statement st = null;//一定要私有private，这样才安全
	private ResultSet rs = null;
	private Statement stList = null;//一定要私有private，这样才安
	private ResultSet rsList = null;
	public QueryResult findTopics(int forumId, int categoryId, Map orderMap, int status, Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			detachedCriteria.add(Restrictions.eq("forumId",forumId));
			if(status!=0){
				detachedCriteria.add(Restrictions.eq("status",status));
			}
			if(categoryId != -1){
				detachedCriteria.add(Restrictions.eq("categoryId",categoryId));
			}
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			orderMap.put("lastPostTime","desc");
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("列表主题发生错误");
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
	public QueryResult findUserTopics(String username, int forumId, String keyword, Map orderMap, Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			detachedCriteria.add(Restrictions.eq("username",username));
			if( forumId!=0 ){
				detachedCriteria.add(Restrictions.eq("forumId",forumId));
			}
			detachedCriteria.add(Restrictions.like("title","%"+keyword+"%"));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			orderMap.put("lastPostTime","desc");
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("列表主题发生错误");
		}
	}

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
	public QueryResult searchTopics (int forumId, String type, String keyword, String timePoint, String way, Map orderMap, Pagination pagination,int status,int isMaster) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			if(forumId != 0){
				detachedCriteria.add(Restrictions.eq("forumId",new Integer(forumId)));
			}
			detachedCriteria.createAlias("forum", "forum");
			detachedCriteria.createAlias("category", "category");
			detachedCriteria.add(Restrictions.like(type,"%"+keyword+"%"));
			//过滤只有管理员才能看到的主题
			if(isMaster==0){
				detachedCriteria.add(Restrictions.eq("forum.isMasters", 0));
			}
			if(!("".equals(timePoint))){
				if("after".equals(way)){
					detachedCriteria.add(Restrictions.gt("dateCreated",timePoint));
				}
				else{
					detachedCriteria.add(Restrictions.lt("dateCreated",timePoint));
				}
			}
			//System.out.println(status);
			//if(status==0)
			//{
			//	detachedCriteria.add(Restrictions.eq("status",new Integer(0)));
			//}
			if(status==1)
			{
				detachedCriteria.add(Restrictions.eq("status",new Integer(1)));
			}
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("列表主题发生错误");
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
	public QueryResult findTopics (final int forumId, Map orderMap, final Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			detachedCriteria.add(Restrictions.eq("forumId",forumId));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("列表贴子发生错误");
		}

	}	

	/**
	 * 根据主题编号查找主题信息
	 * 
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public Topic findTopic(long topicId) throws DAOException {
		try{
			return (Topic)super.get(Topic.class,new Long(topicId));
		}
		catch(ObjectExistException objectExistException){
			logger.error("取得 topicId(" +topicId+ ") 对应的帖子信息发生错误，该信息可能被删除.");
			throw new DAOException("取得 topicId(" +topicId+ ") 对应的帮助信息发生错误，该信息可能被删除.");
		}
		catch(Exception exception){
			logger.error("取得 topicId(" +topicId+ ") 对应的帮助信息发生错误 : " + exception.getMessage());
			throw new DAOException("取得 topicId(" +topicId+ ") 对应的帮助信息发生错误");
		}
	}

	/**
	 * 根据主题编号查找主题信息
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public Topic viewTopic(long topicId) throws DAOException {
		try{
			return (Topic)super.find("from Topic as t left outer join fetch t.posts where t.id="+topicId).get(0);
		}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得 topicId(" +topicId+ ") 对应的帮助信息发生错误，该信息可能被删除.");
		}
		catch(Exception exception){
			throw new DAOException("取得 topicId(" +topicId+ ") 对应的帮助信息发生错误");
		}
	}

	/**
	 * 根据主题论坛版块编号删除主题
	 * 
	 * @param topicId 主题编号
	 * @return Topic 主题对象
	 */	
	public void deleteTopicByForum(int forumId) throws DAOException {
		try{
			super.executeHsql("delete from Topic where forumId='"+forumId+"'");
		}
		catch(DAOException daoException){
			logger.error("根据主题论坛版块编号topicId(" +forumId+ ")删除对应的帖子信息发生错误.");
			throw new DAOException("根据主题论坛版块编号forumId(" +forumId+ ")删除对应的帖子信息发生错误.");
		}
	}

	/**
	 * 更新贴子浏览次数
	 *  
	 * @param topicId 贴子编号
	 * @throws DAOException
	 */
	public void updateTopicViews(int topicId) throws DAOException{
		try{
			super.executeHsql("update Topic set views=views+1 where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("增加贴子topicId(" +topicId+ ")被浏览次数失败.");
		}
	}	

	/**
	 * 更新主题是否被删除状态
	 *
	 * @param topicId 主题编号
	 * @param isDelete 是否被删除
	 * isDelete 1 将主题丢弃到垃圾箱，0 主题状态正常
	 * @throws DAOException
	 */
	public void updateTopicIsDelete(int topicId,int isDelete) throws DAOException{
		try{
			super.executeHsql("update Topic set isDelete='"+isDelete+"' where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更新主题是否被删除状态发生错误.");
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
	public void updateTopicIsTop(int topicId,int isTop) throws DAOException{
		try{
			super.executeHsql("update Topic set isTop='"+isTop+"' where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更新主题是否被置顶发生错误.");
		}
	}

	/**
	 * 更改主题所属论坛
	 * 
	 * @param oldForumId 旧的论坛编号
	 * @param newForumId 新的论坛编号
	 * @throws DAOException
	 */
	public void updateTopicForum( int oldForumId, int newForumId ) throws DAOException {
		try{
			super.executeHsql("update Topic set forumId='"+newForumId+"' where forumId='"+oldForumId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更改主题所属论坛发生错误.");
		}		
	}

	/**
	 * 更新主题状态
	 *
	 * @param topicId 主题编号
	 * @param status 是否被置顶
	 * status 1 精华贴，2 被锁定的贴子
	 * @throws DAOException
	 */
	public void updateTopicStatus(int topicId,int status) throws DAOException{
		try{
			super.executeHsql("update Topic set status='"+status+"' where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更新主题状态发生错误.");
		}
	}

	/**
	 * 物理删除主题(不可恢复)
	 * @param topicId
	 * @throws DAOException
	 */
	public void deleteTopic(int topicId) throws DAOException{
		try{
			super.executeHsql("delete from Topic where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("物理删除主题(删除后不可恢复)发生错误.");
		}
	}

	/**
	 * 将主题丢到垃圾箱
	 * @param topicId 主题编号
	 * @throws DAOException
	 */
	public void trashTopic(int topicId) throws DAOException {
		try{
			super.executeHsql("update Topic set isDelete=1 where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("将主题删除,丢到垃圾箱....");
		}
	}

	/**
	 * 取得主题总数
	 * @return
	 * @throws DAOException
	 */
	public int countTopic() throws DAOException{
		try{
			return (Integer)super.findByAggregate("select count(t.id) from Topic as t where t.isDelete=0");
		}
		catch(DAOException daoException){
			throw new DAOException("统计贴子总数失败.");
		}
	}

	/**
	 * 根据ForumId统计主题总数
	 * @param forums
	 * @return
	 * @throws DAOException
	 */
	public int countTopic(List forums) throws DAOException{
		Forum forum = null;
		String forumEnum = "";
		try{
			for (int i = 0; i < forums.size(); i++) {
				forum = (Forum)forums.get(i);
				if(forums.size()-1==i){
					forumEnum = forumEnum + "t.forumId='"+forum.getId()+"'";
				}
				else{
					forumEnum = forumEnum + "t.forumId='"+forum.getId()+"' or ";
				}
			}
			return (Integer)super.findByAggregate("select count(t.id) from Topic as t where ("+forumEnum+") and t.isDelete=0");
		}
		catch(DAOException daoException){
			throw new DAOException("统计贴子总数失败.");
		}		
	}	

	/**
	 * 根据论坛编号查找制定数目的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws DAOException
	 */
	public List findTopic(int forumId,int dataNum) throws DAOException{
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			detachedCriteria.add(Restrictions.eq("forumId",forumId));
			detachedCriteria.addOrder(Order.desc("lastPostTime"));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			return super.find(detachedCriteria,dataNum);
		}
		catch(DAOException daoException){
			throw new DAOException("根据论坛编号查找制定数目的主题失败");
		}
	}


	/**
	 * 根据论坛编号查找制定数目的主题
	 * @param forumId 论坛编号
	 * @param dataNum 结果数
	 * @return List 主题列表
	 * @throws DAOException
	 */
	public List findTopic2(int forumId,int dataNum) throws DAOException{
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			detachedCriteria.add(Restrictions.eq("forumId",forumId));
			detachedCriteria.addOrder(Order.desc("lastPostTime"));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			return super.find(detachedCriteria,dataNum);
		}
		catch(DAOException daoException){
			throw new DAOException("根据论坛编号查找制定数目的主题失败");
		}
	}

	/**
	 * 移动主题
	 *
	 * @param ids 主题列表
	 * @param forumId 论坛编号
	 * @throws DAOException
	 */
	public void moveTopic(int topicId,int forumId) throws ServiceException{
		try{
			super.executeHsql("update Topic set forumId='"+forumId+"' where id="+topicId);
		}
		catch(DAOException daoException){
			throw new DAOException("根据论坛编号查找制定数目的主题失败");
		}
	}

	/**
	 * 取得最新发表的帖子
	 * @param forumId 论坛编号 forumId 0 全部论坛查找
	 * @param num 信息数
	 * @return 主题数
	 * @throws DAOException
	 */
	public List findNewlyTopics(int forumId,int num) throws DAOException{
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			if(forumId!=0){
				detachedCriteria.add(Restrictions.eq("forumId",forumId));
			}
			detachedCriteria.addOrder(Order.desc("dateCreated"));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			return super.find(detachedCriteria,num);
		}
		catch(DAOException daoException){
			throw new DAOException("列表最新发表的帖子失败");
		}		
	}

	/**
	 * 取得最新回复的帖子
	 * @param forumId 论坛编号 forumId 0 全部论坛查找
	 * @param num 信息数
	 * @return 主题数
	 * @throws DAOException
	 */
	public List findLastPostTopics(int forumId,int num) throws DAOException{
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			if(forumId!=0){
				detachedCriteria.add(Restrictions.eq("forumId",forumId));
			}
			detachedCriteria.addOrder(Order.desc("lastPostTime"));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			return super.find(detachedCriteria,num);
		}
		catch(DAOException daoException){
			throw new DAOException("列表得最新回的帖子失败");
		}		
	}

	public List getDayTopics(int forumId,int num){
		JDBCManager jdbcManager=new JDBCManager();
		Connection con=null;//这些东西提在try外面
		List list=null;
		Topic topic = null;
		try{
			list=new ArrayList();
			con=jdbcManager.getConnection();
			String sSql="";
			if(forumId ==0){
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.date_created between DATE_ADD(now(), Interval -1 day) and now()) as cnt_post "+
				"FROM ntsky_t_topic b where b.is_delete=0 order by cnt_post desc limit "+num+"";							
			}else{
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.date_created between DATE_ADD(now(), Interval -1 day) and now()) as cnt_post "+
				"FROM ntsky_t_topic b where b.forum_id="+forumId+" and b.is_delete=0 order by cnt_post desc limit "+num+"";				
			}			
			st = con.createStatement();//这样就够了
			rs = st.executeQuery(sSql);
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面

			//当rs有下一位
			while(rs.next()){
				topic =new Topic();
				topic.setId(Long.parseLong(rs.getString("id")));
				topic.setTitle(rs.getString("title"));
				topic.setCntPost(Integer.parseInt(rs.getString("cnt_post")));	
				list.add(topic);
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

	public List getWeekTopics(int forumId,int num){
		JDBCManager jdbcManager=new JDBCManager();
		Connection con=null;//这些东西提在try外面
		List list=null;
		Topic topic = null;
		try{
			list=new ArrayList();
			con=jdbcManager.getConnection();
			String sSql="";
			if(forumId ==0){
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.date_created between DATE_ADD(now(), Interval -7 day) and now()) as cnt_post "+
				"FROM ntsky_t_topic b where b.is_delete=0 order by cnt_post desc limit "+num+"";							
			}else{
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.date_created between DATE_ADD(now(), Interval -7 day) and now()) as cnt_post "+
				"FROM ntsky_t_topic b where b.forum_id="+forumId+" and  b.is_delete=0 order by cnt_post desc limit "+num+"";				
			}			
			st = con.createStatement();//这样就够了
			rs = st.executeQuery(sSql);
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面

			//当rs有下一位
			while(rs.next()){
				topic =new Topic();
				topic.setId(Long.parseLong(rs.getString("id")));
				topic.setTitle(rs.getString("title"));
				topic.setCntPost(Integer.parseInt(rs.getString("cnt_post")));	
				list.add(topic);
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


	public List getHotTopics(int forumId,int num){
		JDBCManager jdbcManager=new JDBCManager();
		Connection con=null;//这些东西提在try外面
		List list=null;
		Topic topic = null;
		try{
			list=new ArrayList();
			con=jdbcManager.getConnection();
			String sSql="";
			if(forumId ==0){
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id) as cnt_post "+
				"FROM ntsky_t_topic b where b.is_delete=0 order by cnt_post desc limit "+num+"";							
			}else{
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id) as cnt_post "+
				"FROM ntsky_t_topic b where b.forum_id="+forumId+" and  b.is_delete=0 order by cnt_post desc limit "+num+"";				
			}			
			st = con.createStatement();//这样就够了
			rs = st.executeQuery(sSql);
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面

			//当rs有下一位
			while(rs.next()){
				topic =new Topic();
				topic.setId(Long.parseLong(rs.getString("id")));
				topic.setTitle(rs.getString("title"));
				topic.setCntPost(Integer.parseInt(rs.getString("cnt_post")));	
				list.add(topic);
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

	public List getGoodTopics(int forumId, int num) {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			if(forumId!=0){
				detachedCriteria.add(Restrictions.eq("forumId",forumId));
			}
			detachedCriteria.addOrder(Order.desc("dateCreated"));
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			detachedCriteria.add(Restrictions.eq("status", new Integer(1)));
			return super.find(detachedCriteria,num);
		}
		catch(DAOException daoException){
			throw new DAOException("列表最新发表的帖子失败");
		}		
	}

	public List getNewlyTopicsByUser(String username, int num) {
		List topics=null;		
		topics= this.getSession().createCriteria(Topic.class)
		.add(Expression.eq("isDelete", 0))
		.add(Expression.eq("username", username))
		.addOrder(Order.desc("dateCreated"))
		.setMaxResults(num)
		.list();

		for(int i=0;i<topics.size();i++){
			Topic topic=(Topic)topics.get(i);
			Forum forum=(Forum)this.getHibernateTemplate().find("from Forum where id="+topic.getForumId()).get(0);
			topic.setForum(forum);
		}

		return topics;
	}

	public Topic getRandomTopics(int forumId) {
		JDBCManager jdbcManager=new JDBCManager();
		Connection con=null;//这些东西提在try外面

		Topic topic = null;
		try{

			con=jdbcManager.getConnection();
			String sSql="";
			if(forumId ==0){
				sSql="select * from (SELECT a.forum_id,a.id,a.topic_id,b.title,a.content,(select count(*) as pm from ntsky_t_post c "+
				"where a.id>c.id and c.topic_id =b.id) as pm FROM ntsky_t_topic b,ntsky_t_post a where b.is_delete=0 and "+
				"b.id=a.topic_id order by a.topic_id,b.id) t where t.pm=0 and t.content like '%<img%' order by rand() limit 1";							
			}else{
				sSql="select * from (SELECT a.forum_id,a.id,a.topic_id,b.title,a.content,(select count(*) as pm from ntsky_t_post c "+
				"where a.id>c.id and c.topic_id =b.id) as pm FROM ntsky_t_topic b,ntsky_t_post a where b.is_delete=0 and "+
				"b.id=a.topic_id order by a.topic_id,b.id) t where t.pm=0 and t.content like '%<img%' and t.forum_id="+forumId+" order by rand() limit 1";				
			}			
			st = con.createStatement();//这样就够了
			rs = st.executeQuery(sSql);
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面

			//当rs有下一位
			if(rs.next()){
				topic =new Topic();
				topic.setId(Long.parseLong(rs.getString("topic_id")));
				topic.setTitle(rs.getString("title"));
				String content=rs.getString("content");
				String cont[]=content.split("\"");
				for(int i=0;i<cont.length;i++){
					if(cont[i].trim().indexOf("<img")!=-1){
						content=cont[i+7];
					}

				}
				topic.setContent(content);

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
		return topic;	
	}

	public List getInterfixTopics(String title, int num) {
		List topics=null;	
		title=title.replaceAll("[，。“”：‘？?,.!！]", "");
		int length=title.length();
		String temp[]=new String[length-1];

		String sql="from Topic where isDelete=0 ";

		for(int i=2;i<=length;i++){
			temp[i-2]=title.substring(i-2, i);
			if(i==2){
				sql=sql+" and (title like '%"+temp[i-2]+"%' ";
			}else{
				sql=sql+" or title like '%"+temp[i-2]+"%'";
			}			
		}

		sql=sql+") order by id desc";

		Query query=this.getSession().createQuery(sql);
		query.setMaxResults(num);
		topics=query.list();

		return topics;
	}

	/*public List getWeekTopics(int forumId,int num){
		Connection con=null;//这些东西提在try外面
		System.out.println("=============");
		List list=null;
		try{
			list=new ArrayList();
			con=this.getSession().connection();
			String sSql="";
			if(forumId ==0){
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.date_created between DATE_ADD(now(), Interval -7 day) and now()) as cnt_post "+
					"FROM ntsky_t_topic b order by cnt_post desc limit "+num+"";							
			}else{
				sSql="SELECT b.id,b.title,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.date_created between DATE_ADD(now(), Interval -7 day) and now()) as cnt_post "+
					"FROM ntsky_t_topic b where b.forum_id="+forumId+" order by cnt_post desc limit "+num+"";				
			}	
			System.out.println("=============");
			st = con.createStatement();//这样就够了
			System.out.println("=============2");
			rs = st.executeQuery(sSql);
			System.out.println("=============3");
			//con.commit();
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面

			//当rs有下一位
			while(rs.next()){
				//rs.getString("xxx");
				//通过getString getInt等方法获得数据库中数据
				//例如现在这里是Topic的东西
				Topic topic=new Topic();
				topic.setId(rs.getLong(1));
				topic.setTitle(rs.getString(2));
				topic.setCntPost(rs.getInt(3));
				list.add(topic);		

			}	
			return list;
			//con.close();它这里说不能达到的代码，就是说，如果try到某个地方死了，这个连接就不关闭了，不安全了
		}
		catch(Exception exception){
			logger.error(exception.getMessage());
			return null;
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}*/
	public QueryResult getReferTopics(int userId,int num,Pagination pagination){
		JDBCManager jdbcManager=new JDBCManager();
		Connection con=null;//这些东西提在try外面
		List list=null;
		Topic topic = null;
		try{
			list=new ArrayList();
			con=jdbcManager.getConnection();
			String sSql="";
			String count="";
			/*if(logger.isDebugEnabled()){
				logger.debug("检索的起始位置 : " + pagination.getStart());
			}*/
			st = con.createStatement();//这样就够了
			stList = con.createStatement();//这样就够了
			count="select count(*) as cnt from (SELECT b.*,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.user_id="+userId+") as cnt_post"+
			" FROM ntsky_t_topic b where b.is_delete=0 order by b.last_post_time desc) t where t.cnt_post>0";							

			sSql="select * from (SELECT b.*,(select count(*) from ntsky_t_post a where a.topic_id=b.id and a.user_id="+userId+") as cnt_post"+
			" FROM ntsky_t_topic b where b.is_delete=0 order by b.last_post_time desc) t where t.cnt_post>0 limit "+pagination.getStart()+","+pagination.getRange()+"";							

			rs = st.executeQuery(count);
			rs.next();
			int totalRecord=rs.getInt("cnt");
			//logger.debug("检索的起始位置@@totalRecord:"+totalRecord);
			//logger.debug("检索的起始位置@@@@pagination.getStart():"+pagination.getStart());
			//logger.debug("检索的起始位置@@@@@pagination.getRange():"+pagination.getRange());
			//logger.debug("检索的起始位置@@@@@@@@sSql:"+sSql);
			rsList = stList.executeQuery(sSql);
			//return rs;我们也一定不要做return rs的事情，我们把提取出来的东西放在List里面

			//当rs有下一位
			while(rsList.next()){
				topic =new Topic();
				topic.setId(Long.parseLong(rsList.getString("id")));
				topic.setTitle(rsList.getString("title"));
				topic.setStatus(Integer.parseInt(rsList.getString("status")));
				topic.setReplies(Integer.parseInt(rsList.getString("replies")));
				topic.setViews(Integer.parseInt(rsList.getString("views")));
				topic.setLastPostUser(rsList.getString("last_post_user"));
				topic.setIsTop(Integer.parseInt(rsList.getString("is_top")));
				topic.setIsVote(Integer.parseInt(rsList.getString("is_vote")));
				topic.setMood(Integer.parseInt(rsList.getString("mood")));
				SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ); 

				topic.setLastPostTime(sdf.parse(rsList.getString("last_post_time")));
				list.add(topic);
			}


			pagination.setTotalRecord(totalRecord);
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
		return new QueryResult(pagination,list);	
	}
	public QueryResult findTopicsByUser(String username, Pagination pagination) throws DAOException {
		try{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Topic.class); 
			if(username!=null){
				detachedCriteria.add(Restrictions.like("username",username));
			}
			detachedCriteria.add(Restrictions.not(Restrictions.eq("isDelete",new Integer(1))));
			Map orderMap = new TreeMap();
			orderMap.put("isTop","desc");
			orderMap.put("lastPostTime","desc");
			return super.findItemsByCriteria(detachedCriteria,orderMap,pagination);
		}
		catch(DAOException de){
			throw new DAOException("列表主题发生错误");
		}
	}

	@Override
	public void doIsStatus(int topicId, int status) throws DAOException {
		// TODO Auto-generated method stub

		try{
			super.executeHsql("update Topic set status="+status+" where id='"+topicId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("将主题删除,丢到垃圾箱....");
		}


	}
}