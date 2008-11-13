package com.ntsky.bbs.web.webwork.action.annoucement;

import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;

/**
 * 公告信息列表
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/19 17:33:21 $
 */
public class AnnoucementsAction extends AnnouncementActionSupport {
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private ForumService forumService=(ForumService)bf.getBean("forumService");
	

	/**
	 * 公告列表
	 * 操作者 : 管理员
	 * <pre>
	 * 		执行成功迁移到 annoucements.ftl
	 * </pre>
	 * @return String success 
	 */
	public String execute() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　列表公告　
		if(!isPermisson("6_1")){
			setWarnMessage("您没有列表公告的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("列表公告");
		/* -------------------------------*/
		
		if(logger.isDebugEnabled()){
			logger.debug("列表公告信息");
		}
		try{
			// 公告信息
			super.setAnnouncements(announcementService.getAnnouncements(-1,0));
			// 论坛信息
			super.setForms(super.forumService.getForums());
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		return SUCCESS;
    }


	public ForumService getForumService() {
		return forumService;
	}
	
}
