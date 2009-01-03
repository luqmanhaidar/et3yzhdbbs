package com.ntsky.bbs.dao.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ntsky.bbs.dao.FavoriteTopicDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.util.jdbc.JDBCManager;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.domain.*;

/**
 * 帮助信息Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.6 $ $Date: 2008/10/26 06:46:52 $
 */
public class FavoriteTopicDAOHibernateImpl extends BaseDAOHibernateImpl implements FavoriteTopicDAO {

	private Statement st = null;//一定要私有private，这样才安全
	private ResultSet rs = null;
	private Statement stList = null;//一定要私有private，这样才安
	private ResultSet rsList = null;
	public QueryResult findFavoriteTopic (int userId, final Pagination pagination) throws DAOException {
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
			count="select count(*) as cnt from ntsky_t_topic a,ntsky_t_user b,ntsky_t_user_favorite_topic c where a.id=c.topic_id and b.id=c.user_id and c.user_id="+userId+" and a.is_delete=0 order by a.date_created";							
			
			sSql="select c.id as fav_id,a.* from ntsky_t_topic a,ntsky_t_user b,ntsky_t_user_favorite_topic c where a.id=c.topic_id and b.id=c.user_id and c.user_id="+userId+" and a.is_delete=0 order by a.date_created limit "+pagination.getStart()+","+pagination.getRange()+"";							

			rs = st.executeQuery(count);
			rs.next();
//			System.out.println("检索的起始位置@@@@@pagination.getRange():"+pagination.getRange());
			int totalRecord=rs.getInt("cnt");
//			logger.debug("检索的起始位置@@totalRecord:"+totalRecord);
//			logger.debug("检索的起始位置@@@@pagination.getStart():"+pagination.getStart());
//			logger.debug("检索的起始位置@@@@@pagination.getRange():"+pagination.getRange());
//			logger.debug("检索的起始位置@@@@@@@@sSql:"+sSql);
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
				topic.setFavId(Integer.parseInt(rsList.getString("fav_id")));
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
	
	public void deleteFavoriteTopic(int favTopicId) throws DAOException {
		try{
			super.executeHsql("delete from FavoriteTopic where id='"+favTopicId+"'");
		}
		catch(DAOException de){
			throw new DAOException("删除指定的收藏夹信息发生错误");
		}
	}
	
	public FavoriteTopic getFavoriteTopicByUserTopic(int userId,int topicId) throws DAOException {
		try{
			List list = super.find("from FavoriteTopic as favoriteTopic where favoriteTopic.userId="+userId+" and favoriteTopic.topicId="+topicId+"");
			if(list!=null&&list.size()>0){
				return (FavoriteTopic)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException de){
			throw new DAOException("查找收藏夹信息失败");
		}
	}
	
}