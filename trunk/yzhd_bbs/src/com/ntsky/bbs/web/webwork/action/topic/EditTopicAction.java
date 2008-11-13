package com.ntsky.bbs.web.webwork.action.topic;

import java.util.List;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.memory.ForumSingleton;

/**
 * 修改主题信息
 * <ul>
 * 	<li>edit -- 检索主题</li>
 *  <li>execute -- 更新主题</li>
 * </ul>
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/11/10 15:59:26 $
 */
public class EditTopicAction extends TopicActionSupport implements ModelDriven{

	private Topic topic = new Topic();
	private XmlDataService xmlDataService;
	private Map propertyMap;
	private int topicId;
	public void setTopicId(int topicId){
		this.topicId = topicId;
	}
	public int getTopicId(){
		return this.topicId;
	}	
	
	/**
	 * 修改主题(更新主题数据)
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("更新主题 [' "+topic.getTitle()+" '] 信息");
		}
		// 如果该帖子不是原用户发表
		if(!(topic.getUsername().equals(getSessionUser().getUsername()))){
			if(logger.isInfoEnabled()){
				logger.info("如果不是该用户发表的帖子,判断是否是有权限用户编辑帖子");
			}
			if(super.isAccess("canEditOther")==0){
				setWarnMessage("您没有修改帖子[' "+topic.getTitle()+" ']的权限.");
				return NO_PERMISSION;
			}
		}
		try{
			Post firstPost = new Post();
			firstPost.setId(topic.getFirstPostId());
			firstPost.setTitle(topic.getTitle());
			firstPost.setContent(topic.getContent());
			topicService.editTopic((Topic)BeanUtil.format(topic),(Post)BeanUtil.format(firstPost));
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
	    
		topicId = topic.getId().intValue();
		forum = ForumSingleton.getInstance().getForum(super.getIntParameter(Symbols.PARA_FORUM_ID));
	    return SUCCESS;
    }

	/**
	 * 主题数据
	 */
	public Object getModel() {
		return topic;
	}
	
	private int forumId;
	public void setForumId(int forumId){
		this.forumId = forumId;
	}
	
	private Forum forum = null;
	public Forum getForum(){
		return this.forum;
	}
	
	private List categories;
	public List getCategories(){
		return this.categories;
	}
	
	/**
	 * 主题数据
	 */
	public Topic getTopic(){
		return this.topic;
	}
	
	/**
	 * 修改主题(查找主题数据)
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception{
		if(logger.isInfoEnabled()){
			logger.info("修改主题(检索Topic编号为['"+topicId+"']的主题.)");
		}
		
		try{
			topic = topicService.getEditedTopic(topicId);
			
			// ---------- 权限 ------------
			//if(super.isAccess("canEditPost")==0){
			//	return NO_PERMISSION;
		//	}	
		//	else{
				if(logger.isInfoEnabled()){
					logger.info("判断帖子是否该用户发表");
				}
				// 如果该帖子不是原用户发表
				if(!(topic.getUsername().equals(getSessionUser().getUsername()))){
					if(logger.isInfoEnabled()){
						logger.info("如果不是该用户发表的帖子,判断是否是有权限用户编辑帖子");
					}
					if(super.isAccess("canEditOther")==0){
						setWarnMessage("您没有修改帖子[' "+topic.getTitle()+" ']的权限.");
						return NO_PERMISSION;
					}
				}
			//}			
			
			forum = ForumSingleton.getInstance().getForum(forumId);
	        this.propertyMap = xmlDataService.select(Symbols.CONFIG_TOPIC);
			// 主题帖将修改主题信息
			categories = categoryService.getCategories(0);
			
			
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}		
		return SUCCESS;
	}
	public Map getPropertyMap() {
		return propertyMap;
	}
	public void setPropertyMap(Map propertyMap) {
		this.propertyMap = propertyMap;
	}
	public XmlDataService getXmlDataService() {
		return xmlDataService;
	}
	public void setXmlDataService(XmlDataService xmlDataService) {
		this.xmlDataService = xmlDataService;
	}

}
