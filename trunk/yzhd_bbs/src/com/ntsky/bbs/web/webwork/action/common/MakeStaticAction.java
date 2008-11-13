package com.ntsky.bbs.web.webwork.action.common;

import java.util.List;

import com.opensymphony.xwork.Preparable;

import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

import com.ntsky.bbs.util.RssUtil;
import com.ntsky.bbs.service.LinkService;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.web.webwork.action.BasicActionSupport;

/**
 * 静态数据作成
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.1 $ $Date: 2008/10/19 13:31:24 $
 */
public class MakeStaticAction extends BasicActionSupport {

	private LinkService linkService;

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
	/**
	 * 作成友情链接静态数据
	 * <pre>
	 * 	执行成功迁移到 make.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doLink() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　作成友情链接静态数据
		if(!isPermisson("6_7")){
			setWarnMessage("您没有作成友情链接静态数据的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("作成友情链接静态数据");
		/* -------------------------------*/
		
		linkService.makeLinkJSData();
		
		super.setActionMessage("成功作成友情链接静态数据");
		return SUCCESS;
    }
	
	private AnnouncementService announcementService;
	public void setAnnouncementService(AnnouncementService announcementService){
		this.announcementService = announcementService;
	}
	
	/**
	 * 作成公告静态数据
	 * <pre>
	 * 	执行成功迁移到 make.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doAnnouce() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　作成公告静态数据
		if(!isPermisson("6_7")){
			setWarnMessage("您没有作成公告静态数据的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("作成公告静态数据");
		/* -------------------------------*/
		
		List forums = ForumSingleton.getInstance().getForums();
		Forum forum = null;
		for (int i = 0; i < forums.size(); i++) {
			forum = (Forum)forums.get(i);
			announcementService.makeAnnouncementJSData(forum.getId().intValue());
		}    
		
		super.setActionMessage("成功作成公告静态数据");
		return SUCCESS;
    }
	
	/**
	 * 生成RSS
	 * <pre>
	 * 	执行成功迁移到 make.ftl
	 * </pre>
	 * @return String success 
	 */
	public String doRss() throws Exception {
		
		/* ---------- 权限判断 ------------ */
		//　生成RSS
		if(!isPermisson("6_7")){
			setWarnMessage("您没有生成RSS的权限.");
			return NO_PERMISSION;
		}
		/* ------------------------------ */		
		
		/* ------------ 记录日志 -----------*/
		recordActLog("生成RSS");
		/* -------------------------------*/
		
		List forums = ForumSingleton.getInstance().getForums();
		Forum forum = null;
		for (int i = 0; i < forums.size(); i++) {
			forum = (Forum)forums.get(i);
			RssUtil.feedRSS20(forum);
		}    
		
		super.setActionMessage("成功生成RSS");
		return SUCCESS;
    }
	
	/**
	 * 打开生成静态页面
	 * @return
	 * @throws Exception
	 */
	public String doDefault() throws Exception {
		
		if(logger.isDebugEnabled()){
			logger.debug("打开静态数据作成页面...");
		}
		
		return SUCCESS;
	}
	
}
