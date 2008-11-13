package com.ntsky.bbs.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ntsky.bbs.domain.Category;
import com.ntsky.bbs.domain.User;
import com.ntsky.bbs.dao.CategoryDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ObjectExistException;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;

/**
 * 主题类别模块数据处理实现 
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.5 $ $Date: 2008/11/06 09:33:47 $
 */
public class CategoryDAOHibernateImpl extends BaseDAOHibernateImpl implements CategoryDAO {

	/**
	 * 根据论坛编号查找主题类别(默认按照显示次序有大到小显示)
	 * 
	 * @param forumId 论坛编号
	 * @return List 主题类别列表
	 */
	public List findCategories ( int forumId ) throws DAOException {
		try{
			if(forumId==-1)
			{

				return super.find("from Category as c order by c.displayOrder desc");
			}
			else
			{
				return super.find("from Category as c where c.forumId='"+forumId+"' order by c.displayOrder desc");
				
			}
		}
		catch(DAOException de){
			throw new DAOException("列表全部主题列别发生错误");
		}
	}
	

	public List findAllCategories () throws DAOException {
		try{
				return super.find("from Category as c order by c.displayOrder desc");
		}
		catch(DAOException de){
			throw new DAOException("列表全部主题列别发生错误");
		}
	}
	
	/**
	 * 根据类别编号查找类别数据
	 * @param catId 类别编号
	 * @return Category 类别对象
	 */	
	public Category findCategory( int catId ) throws DAOException{
		try{
			return  (Category)super.get(Category.class,new Long(catId));
    	}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得['"+catId+"']对应的类别信息发生错误，该信息可能被删除.");
		}
    	catch(Exception exception){
    		throw new DAOException("取得['"+catId+"']对应的类别信息发生错误");
    	}
	}
	
	/**
	 * 根据类别编号查找类别数据
	 * @param catId 类别编号
	 * @return Category 类别对象
	 */	
	public Category findCategoryByIdAndName( int catId,String name ) throws DAOException{
		try{
			List list = super.find("from Category as c where c.name='"+name+"' and c.id!="+catId+" order by c.displayOrder desc");
			if(list!=null&&list.size()>0){
				return (Category)list.toArray()[0];
			}
			else{
				return null;
			}
    	}
		catch(ObjectExistException objectExistException){
			throw new DAOException("取得['"+catId+"']对应的类别信息发生错误，该信息可能被删除.");
		}
    	catch(Exception exception){
    		throw new DAOException("取得['"+catId+"']对应的类别信息发生错误");
    	}
	}
	/**
	 * 根据文章类型名查找文章类型信息
	 * 
	 * @param name 名称
	 * @return Category 文章类型对象(查找的文章类型不存在时,返回null)
	 */
	public Category findCategoryByName(String name) throws DAOException {
		try{
			List list = super.find("from Category as category where category.name=?",new String[]{name});
			if(list!=null&&list.size()>0){
				return (Category)list.toArray()[0];
			}
			else{
				return null;
			}
		}
		catch(DAOException de){
			throw new DAOException("根据文章类型['"+name+"']查找文章类型信息失败");
		}
	}
	
	/**
	 * 删除指定ID的类别信息
	 * @param catId 类别编号
	 * @throws DAOException
	 */
	public void deleteCategory(int catId) throws DAOException {
		try{
			super.executeHsql("delete from Category where id='"+catId+"'");
		}
		catch(DAOException de){
			throw new DAOException("删除指定的类别信息发生错误");
		}
	}
	
	/**
	 * 更改类别所属论坛
	 * 
	 * @param oldForumId 旧的论坛编号
	 * @param newForumId 新的论坛编号
	 * @throws DAOException
	 */
	public void updateCategoryForum( int oldForumId, int newForumId ) throws DAOException {
		try{
			super.executeHsql("update Category set forumId='"+newForumId+"' where forumId='"+oldForumId+"'");
		}
		catch(DAOException daoException){
			throw new DAOException("更改类别所属论坛发生错误.");
		}		
	}

}