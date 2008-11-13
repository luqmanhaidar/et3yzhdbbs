package com.ntsky.bbs.service;

import java.util.List;

import com.ntsky.bbs.domain.Help;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.exception.ServiceException;

public interface HelpService {

	/**
	 * 创建帮助信息
	 * 
	 * @param link 帮助
	 */
	public void createHelp(Help help) throws ServiceException ;
	
	/**
	 * 修改帮助信息
	 * 
	 * @param help 帮助
	 */
	public void editHelp(Help help) throws ServiceException ;
	
	/**
	 * 删除帮助信息
	 * 
	 * @param helpId 帮助编号
	 * @throws ServiceException
	 */
	public void deleteHelp(int helpId) throws ServiceException ;
	
	/**
	 * 取得帮助
	 * 
	 * @param helpId 帮助编号
	 * @return 编号对应的帮助信息
	 * @throws ServiceException 
	 */
	public Help getHelp(int helpId) throws ServiceException ;
	
	
	/**
	 * 根据帮助类型查找帮助
	 * @param type 帮助类型
	 * @return 帮助列表
	 * @throws ServiceException
	 */
	public List getHelps(int type) throws ServiceException;
	
	/**
	 * 分页取得全部的帮助信息
	 * 
	 * @param title 标题
	 * @param pagination 分页bean
	 * @return
	 */
	public QueryResult getHelps (String title, Pagination pagination);
	
}
