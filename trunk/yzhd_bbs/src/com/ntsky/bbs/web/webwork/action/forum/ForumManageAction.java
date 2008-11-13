package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.opensymphony.xwork.Preparable;

/**
 * 论坛管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:30 $
 */
public class ForumManageAction extends ForumActionSupport implements Preparable{

	/**
	 * 论坛管理
	 * <pre>
	 * 	执行成功迁移到 forums.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("列表全部论坛信息");
		}
		return SUCCESS;
    }
	
	/**
	 * 执行此ManageAction的准备信息
	 * <ul>
	 * 	<li>取得论坛管理页面数据</li>
	 * </ul>
	 */
	public void prepare() throws Exception {
		setForums(forumService.getForums());
		if(logger.isDebugEnabled()){
			logger.debug("初始化论坛信息");		
		}
	}
	
	/**
	 * 检索对应板块的数据
	 * 返回到修改论坛信息页面
	 * 操作者 ：版主
	 * 
	 * @return String 执行信息
	 */
	public String doEdit() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("修改ForumId为" +forumId+ "的版块信息");
		}	
		
		// ---------- 权限 ------------
		if(super.isAccess("canEditForum")==0){
			setWarnMessage("您所属的角色没有修改论坛信息的权限.");
			return NO_PERMISSION;
		}
		// ---------------------------
		
		try{
			this.forum = super.forumService.getForum(forumId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return EDIT;
	}	
	
	private Forum forum = null;
	public Forum getForum(){
		return this.forum;
	}
	
	
	private int forumId;
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	
	/**
	 * 初始论坛版块列表数据
	 */ 
	private void initForumTree() throws Exception {
		try{
			setForums(forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	}	
	
	/**
	 * 创建论坛版块信息
	 * 
	 * @return String 执行信息
	 */
	public String doOpenCreatePage() throws Exception {
		// 初始化论坛列表
		if(logger.isInfoEnabled()){
			logger.info("打开创建论坛页面..");
		}
		return super.CREATE;
	}

	
}
