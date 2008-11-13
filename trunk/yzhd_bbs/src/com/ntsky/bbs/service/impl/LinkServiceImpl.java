package com.ntsky.bbs.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import com.ntsky.framework.reflect.ReflectUtil;
import com.ntsky.framework.util.FileUtil;
import com.ntsky.framework.util.StringUtil;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Admin;
import com.ntsky.bbs.domain.Announcement;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.dao.LinkDAO;
import com.ntsky.bbs.exception.DAOException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.service.LinkService;

/**
 * 友情链接模块业务处理实现
 * 
 * @author ntsky
 * @link www.ntsky.com
 */
public class LinkServiceImpl extends BaseServiceImpl implements LinkService{
	
	private LinkDAO linkDAO;

	public void setLinkDAO(LinkDAO linkDAO) {
		this.linkDAO = linkDAO;
	}

	/**
	 * 创建友情链接
	 * 
	 * @param link 友情链接
	 */
	public void createLink(Link link) throws ServiceException {
		try{
			linkDAO.save(link);
		}
		catch(DAOException daoException){
			throw new ServiceException("创建友情链接["+link.getName()+"]发生错误.");
		}
		makeLinkJSData();
	}
	
	/**
	 * 删除友情链接
	 * <p>
	 * 	当为图片链接时，同时删除友情链接的文件
	 * </p>
	 * 
	 * @param linkId 友情链接编号
	 */
	public void deleteLink(int linkId) throws ServiceException {
		try{
			Link tempLink = getLink(linkId);
			linkDAO.delete(tempLink);
			if((tempLink.getIsLogo()).intValue()==1){
				// 删除物理友情链接图片
				FileUtil.deleteFile(StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(),tempLink.getLogo()));
			}
			makeLinkJSData();
		}
		catch(DAOException daoException){
			throw new ServiceException("删除友情链接发生错误.");
		}
	}
	
	/**
	 * 修改友情链接信息
	 * 
	 * @param link 友情链接信息
	 */
	public void editLink(Link link) throws ServiceException {
		try{
				linkDAO.update(link);
		}
		catch(DAOException daoException){
			throw new ServiceException("更新友情链接发生错误.");
		}		
	}
	
	/**
	 * 取得友情链接
	 * 
	 * @param linkId 友情链接编号
	 */
	public Link getLink(int linkId) throws ServiceException {
		try{ 
			return linkDAO.findLink(linkId);
		}
		catch(DAOException daoException){
			throw new ServiceException("取得友情链接发生错误.");
		}	
	}
	
	/**
	 * 取得全部的友情链接列表
	 * 
	 * @return List 友情链接列表
	 */
	public List getLinks() throws ServiceException {
		try{
			return linkDAO.findLinks();
		}
		catch(DAOException daoException){
			throw new ServiceException("取得全部友情链接发生错误.");
		}	
	}

	/**
	 * 取得指定类型的友情链接列表
	 * @param isLogo　是否为图片友情链接
	 * @return List 友情链接列表
	 */
	public List getLinks(int isLogo) throws ServiceException {
		try{
			return linkDAO.findLinks(isLogo);
		}
		catch(DAOException daoException){
			throw new ServiceException("根据链接分类列表链接发生错误.");
		}	
	}
	
	/**
	 * 生成友情链接的Javascript格式的数据
	 * 构造JS内容，输出数据
	 */
	public void makeLinkJSData() {
		// 文本链接
		Object[] textlinks = getLinks(0).toArray();
		StringBuffer linkBuffer = new StringBuffer();
		Link link = null;
		String[] linkTypeArray = {"name","url","logo","description"}; 
		for (int i = 0; i < textlinks.length; i++) {
			link = (Link) textlinks[i];
			super.makeJsData(link,linkBuffer,"linkClass.addText",linkTypeArray);
		}
		// 图片链接
		Object[] imagelinks = getLinks(1).toArray();
		for (int i = 0; i < imagelinks.length; i++) {
			link = (Link) imagelinks[i];
			super.makeJsData(link,linkBuffer,"linkClass.addImage",linkTypeArray);
		}		
		FileUtil.writeFile(Application.getInstance().getJsDirectory()+"link.js",linkBuffer.toString());
	}

	public Link findRandomLinkByType(int at) throws DAOException {
		return linkDAO.findRandomLinkByType(at);
	}	
}
