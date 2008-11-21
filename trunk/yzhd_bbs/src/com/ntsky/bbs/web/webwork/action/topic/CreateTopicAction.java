package com.ntsky.bbs.web.webwork.action.topic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork.ModelDriven;
import com.opensymphony.xwork.Preparable;

import com.ntsky.framework.util.HttpUtil;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.lumaqq.IPSeeker;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.PollResult;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.WebworkUtil;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.xml.bean.Badword;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;

/**
 * 创建主题信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.6 $ $Date: 2008/11/10 15:59:26 $
 */
public class CreateTopicAction extends TopicActionSupport implements ModelDriven,Preparable{

	private Topic topic = new Topic();
	private List badwords;
	private Badword badword;
	private XmlDataService xmlDataService;
	private Map propertyMap;
	private int topicId;
	public int getTopicId(){
		return this.topicId;
	}
	/**
	 * 创建统计信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		
		// ---------- 权限 ------------
		if(super.isAccess("canPostNew")==0){
			setWarnMessage("您没有发表帖子的权限.");
			return super.NO_PERMISSION;
		}
		// ---------------------------		
		
	
		
		String forumId=super.getRequest().getParameter("forumId");
		if(forumId!=null&&!(forumId.equals(""))){
			forum = ForumSingleton.getInstance().getForum(Integer.parseInt(forumId));
			//System.out.println(forum.getIsAdmin());
			if(forum.getIsAdmin()==1){
				String roles=RoleSingleton.getInstance().getRoleIdByName(super.getSessionUser().getUsername());
				//System.out.println(roles);
				if(roles.equals("1")){
					
				}else{
					return NO_PERMISSION;
				}
			}
		}

		
		Post firstPost = null;
		// 投票信息
		Poll poll = null;
		PollResult pollResult = null;
		try{
			badwords = xmlDataService.getBadwords();
			firstPost = new Post();
			firstPost.setIp(HttpUtil.getRemoteAddr(super.getRequest()));
			firstPost.setContent(WebworkUtil.getParameter("content"));
			firstPost.setUserId(getSessionUser().getId().intValue());
			firstPost.setUsername(getSessionUser().getUsername());
			topic.setUsername(getSessionUser().getUsername());
			if(topic.getIsVote() == 1){
				poll = new Poll();
				poll.setContent(super.getParameter("pollContent"));
				int optionNum = getIntParameter("optionNum");
				int optionId = 0;
				String optionText = "";
				if(logger.isInfoEnabled()){
					//logger.info("投票选项数: " + optionNum);
				}				
				//List list = new ArrayList();
				Set set = new HashSet();
				for (int i = 0; i < optionNum; i++) {
					pollResult = new PollResult();
					optionId = getIntParameter("optionId"+String.valueOf(i+1));
					optionText = getParameter("optionText"+String.valueOf(i+1));
					for(int b=0;b<badwords.size();b++)
					{
						badword= new Badword();
						badword = (Badword)badwords.get(b);
						optionText =optionText.replace(badword.getOldStr(), badword.getReplaceStr());
					}
					if(logger.isInfoEnabled()){
						//logger.info("投票选项信息如下: 投票编号[\""+optionId+"\"] 选项内容[\""+optionText+"\"]");
					}
					pollResult.setOptionId(optionId);
					pollResult.setOptionText(optionText);
					set.add(pollResult);
				}
				poll.setPollResults(set);
			}
		
			for(int i=0;i<badwords.size();i++)
			{
				badword= new Badword();
				badword = (Badword)badwords.get(i);
				
				topic.setTitle(topic.getTitle().replace(badword.getOldStr(), badword.getReplaceStr()));
				
				topic.setContent(topic.getContent().replace(badword.getOldStr(), badword.getReplaceStr()));
				firstPost.setContent(firstPost.getContent().replace(badword.getOldStr(), badword.getReplaceStr()));
			}
			topicService.createTopic((Topic)BeanUtil.format(topic),(Post)BeanUtil.format(firstPost),poll);
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}
		
		// 设置页面数据
		topicId = firstPost.getTopicId();
	    return SUCCESS;
    }

	/**
	 * 主题数据
	 */
	public Object getModel() {
		return topic;
	}
	
	// 论坛数据
	private Forum forum;
	public Forum getForum(){
		return this.forum;
	}
	
	private List categories;
	public List getCategories(){
		return this.categories;
	}
	
	/**
	 * 创建主题初始化
	 * @return
	 * @throws Exception
	 */
	public String doDefault() throws Exception{
		// ---------- 权限 ------------
		if(super.isAccess("canPostNew")==0){
			setWarnMessage("您没有发表帖子的权限.");
			return NO_PERMISSION;
		}			
		if(super.getIntParameter("isVote")==1){
			// 投票帖
			if(super.isAccess("canPostPoll")==0){
				setWarnMessage("您所属的角色没有发表投票帖的权限.");
				return NO_PERMISSION;
			}
		}
		// ---------------------------	
		
		//if(logger.isInfoEnabled()){
		//	logger.info("创建主题准备...");
		//}
		
		//categories = categoryService.getCategories(super.getIntParameter(Symbols.PARA_FORUM_ID));
    	categories = categoryService.getCategories(0);
        this.propertyMap = xmlDataService.select(Symbols.CONFIG_TOPIC);
		return SUCCESS;
	}

	public void prepare() throws Exception {
		forum = ForumSingleton.getInstance().getForum(super.getIntParameter(Symbols.PARA_FORUM_ID));
	}
	public Badword getBadword() {
		return badword;
	}
	public void setBadword(Badword badword) {
		this.badword = badword;
	}
	public List getBadwords() {
		return badwords;
	}
	public void setBadwords(List badwords) {
		this.badwords = badwords;
	}
	public XmlDataService getXmlDataService() {
		return xmlDataService;
	}
	public void setXmlDataService(XmlDataService xmlDataService) {
		this.xmlDataService = xmlDataService;
	}
	public Map getPropertyMap() {
		return propertyMap;
	}
	public void setPropertyMap(Map propertyMap) {
		
			this.propertyMap=propertyMap;
	}
	
}
