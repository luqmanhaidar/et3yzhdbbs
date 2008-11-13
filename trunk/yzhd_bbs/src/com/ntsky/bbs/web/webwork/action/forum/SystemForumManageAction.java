package com.ntsky.bbs.web.webwork.action.forum;

import java.util.List;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.opensymphony.xwork.Preparable;

/**
 * 论坛管理
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/11/05 16:54:38 $
 */
public class SystemForumManageAction extends ForumActionSupport implements Preparable{

	/**
	 * 论坛管理
	 * <pre>
	 * 	执行成功迁移到 forums.ftl
	 * </pre>
	 * @return String success 
	 */
	private int isTop;
	private int isMasters;
	private int isAdmin;
	public String execute() throws Exception {
		if(logger.isDebugEnabled()){
			logger.debug("列表全部论坛信息");
		}
		return SUCCESS;
    }
	
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIsMasters() {
		return isMasters;
	}

	public void setIsMasters(int isMasters) {
		this.isMasters = isMasters;
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
	 * 检索对应板块的数据
	 * 返回到修改论坛信息页面
	 * 操作者 ：管理员
	 * 
	 * @return String 执行信息
	 */
	public String doEdit() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　修改论坛
		if(!isPermisson("2_1")){
			setWarnMessage("您没有修改论的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("修改论");
		/* -------------------------------*/		
		
		if(logger.isInfoEnabled()){
			//logger.info("修改ForumId为" +forumId+ "的版块信息");
		}	
		initForumTree();
		try{
			this.forum = super.forumService.getForum(forumId);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return EDIT;
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
		
		/* ---------- 权限判断 ------------ */
		//　创建论坛
		if(!isPermisson("2_1")){
			setWarnMessage("您没有创建论坛的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("创建论坛");
		/* -------------------------------*/
		
		// 初始化论坛列表
		if(logger.isInfoEnabled()){
			logger.info("打开创建论坛页面..");
		}
		return CREATE;
	}

	public String doIsMasters() throws Exception {
		try{
			forumService.doIsMasters(forumId, isMasters);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.MANAGE;
	}
	
	public String doIsAdmin() throws Exception {
		try{
			forumService.doIsAdmin(forumId, isAdmin);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.MANAGE;
	}
	
	public String doIsTop() throws Exception {
		try{
			forumService.doIsTop(forumId, isTop);
			ForumSingleton.getInstance().setForumsInIndex(forumService.findForumsIsTop());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return super.MANAGE;
	}
	
	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	
}
