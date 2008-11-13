package com.ntsky.bbs.dao.hibernate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ntsky.bbs.dao.LinkDAO;
import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.exception.DAOException;

/**
 * 友情链接Hibernate数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/16 17:25:53 $
 */
public class LinkDAOHibernateImpl extends BaseDAOHibernateImpl implements LinkDAO {

	/**
	 * 查找全部的友情链接
	 * @param logo 是否为友情链接
	 * @return List 友情链接列表
	 */
	public List findLinks(int logo) throws DAOException {
		try{
			return find("from Link as link where link.isLogo='"+logo+"' order by link.displayOrder desc");
		}
		catch(DAOException daoException){
			throw new DAOException("列表所有的图片链接失败.");
		}
	}

	/**
	 * 根据ID查找链接数据
	 * @param linkId 链接编号
	 * @return Link 友情链接对象
	 */
	public Link findLink(int linkId) throws DAOException {
		try{
			return (Link)super.get(Link.class,new Long(linkId));
		}
		catch(DAOException daoException){
			throw new DAOException("查找链接信息发生错误.");
		}
	}

	/**
	 * 查询全部的友情链接列表
	 * @return List 友情链接列表
	 */
	public List findLinks() throws DAOException  {
		try{
			return super.find("from Link as link order by link.displayOrder desc");
		}
		catch(DAOException daoException){
			throw new DAOException("列表链接发生错误.");
		}
	}

	public Link findRandomLinkByType(int at) throws DAOException {
		Connection con=null;//这些东西提在try外面
		List list=null;
		ResultSet rs=null;
		Statement stmt=null;
		Link link=null;
		con=this.getSession().connection();
		String sql="select * from ntsky_t_link where adverType="+at+" order by rand() limit 1";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				link=new Link();
				link.setId(Long.parseLong(rs.getString("id")));
				link.setUrl(rs.getString("url"));
				link.setLogo(rs.getString("logo"));
			}
			return link;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}		
	}

}