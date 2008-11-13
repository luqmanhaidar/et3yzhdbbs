package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.domain.Forum;

/**
 * 删除论坛版块
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class DeleteForumAction extends ForumActionSupport {

	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	/**
	 * 删除论坛论坛版块
	 * 
	 * <ol>
	 * 	<li>删除编号为forumId的版块</li>
	 *  <li>取得论坛版块列表</li>
	 * </ol>
	 * @throws Exception
	 */
	public String execute() throws Exception {
		
		// 删除论坛
		super.forumService.deleteForum(forumId);
		// 列表全部的论坛	
		super.setForums(super.forumService.getForums());
		
		ForumSingleton.getInstance().setForums(super.getForums());
		
		return SUCCESS;
	}
	
}
