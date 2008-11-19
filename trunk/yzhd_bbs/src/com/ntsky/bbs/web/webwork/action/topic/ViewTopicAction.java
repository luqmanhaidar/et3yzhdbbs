package com.ntsky.bbs.web.webwork.action.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Link;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.AnnouncementService;
import com.ntsky.bbs.service.LinkService;
import com.ntsky.bbs.service.PostService;
import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.bbs.util.config.SystemConfig;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.util.memory.RoleSingleton;
import com.ntsky.bbs.util.page.Pagination;
import com.ntsky.bbs.util.page.QueryResult;
import com.ntsky.bbs.xml.bean.Badword;

/**
 * 创建主题信息Action
 *  
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.16 $ $Date: 2008/11/10 15:59:26 $
 */
public class ViewTopicAction extends TopicActionSupport {

	private List forums;
	private List announcements;
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private LinkService ls=(LinkService)bf.getBean("linkService");
	private PostService ps=(PostService)bf.getBean("postService");
	private AnnouncementService announcementService=(AnnouncementService)bf.getBean("announcementService");
	private int topicId;
	private List hotTopics;
	private List commendeTopics;
	private Topic randomTopic;
	private Topic randomTopic1;
	private Topic randomTopic2;
	private Topic randomTopic3;
	private List lastPostTopics;	
	private Link ad;//顶部ad
	private Link ad2;
	private Link ad3;
	private Link ad4;
	private int forumId;
	private List userNewlyTopic;
	private List interfixTopics;//相关主题

	//4张图片
	private Topic t1=null;
	private Topic t2=null;
	private Topic t3=null;
	private Topic t4=null;
	private String pic1=null;//麻辣
	private String pic2=null;//推荐
	private String pic3=null;//相关
	private String pic4=null;//正在

	private Map propertyMap;

	private int isThisForum = 0;



	public int getIsThisForum() {
		return isThisForum;
	}

	public void setIsThisForum(int isThisForum) {
		this.isThisForum = isThisForum;
	}

	public Map getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map propertyMap) {
		this.propertyMap = propertyMap;
	}
	private XmlDataService xmlDataService;
	public List getInterfixTopics() {
		return interfixTopics;
	}

	public XmlDataService getXmlDataService() {
		return xmlDataService;
	}

	public void setXmlDataService(XmlDataService xmlDataService) {
		this.xmlDataService = xmlDataService;
	}

	public void setInterfixTopics(List interfixTopics) {
		this.interfixTopics = interfixTopics;
	}

	public List getUserNewlyTopic() {
		return userNewlyTopic;
	}

	public void setUserNewlyTopic(List userNewlyTopic) {
		this.userNewlyTopic = userNewlyTopic;
	}

	public void setTopicId(int topicId){
		this.topicId = topicId;
	}

	private Forum forum;
	public List getAnnouncements() {
		return announcements;
	}

	public List getForums() {
		return forums;
	}

	public Forum getForum(){
		return this.forum;
	}

	/**
	 * 主题数据
	 */
	private Topic topic;
	public Topic getTopic(){
		return this.topic;
	}

	// 投票
	private Poll poll;
	public Poll getPoll(){
		return this.poll;
	}

	/**
	 * 查询主题信息
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		//forums = ForumSingleton.getInstance().getIndexForums();
		//announcements = announcementService.getAnnouncements(-1, 0);

		if(logger.isInfoEnabled()){
			logger.info("查询主题 ['"+topicId+"'] 的信息.");
		}
		// ---------- 权限 ------------
		if(super.isAccess("canViewTopic")==0){
			return NO_PERMISSION;
		}
		try{
			QueryResult queryResult = topicService.viewTopic(topicId,new Pagination(getPaginationStart(),SystemConfig.getInstance().getIntPropertyValue(Symbols.PAGINATION,Symbols.PAGINATION_POST)));

			// 贴子
			posts = queryResult.getItems();
			// 分页
			setPagination(queryResult.getPagination());
			// 主题
			topic = topicService.getTopic(topicId);



			if(topic.getIsDelete()!=1)
			{
				if(topic.getStatus()==1)
				{
					if(super.isAccess("canViewBestTopic")==0){
						return NO_PERMISSION;
					}
				}

				if(ForumSingleton.getInstance().getForum(topic.getForumId()).getIsMasters()==1){
					if(getSessionUser()==null || getSessionUser().getUsername().equals("guest"))
					{
						setWarnMessage("该帖子需要版主以上级别才能访问");
						return NO_PERMISSION;
					}
					else
					{
						String roles=RoleSingleton.getInstance().getRoleIdByName(super.getSessionUser().getUsername());
						if(roles.equals("1")||roles.equals("2")||roles.equals("3")){

						}else{
							setWarnMessage("该帖子需要版主以上级别才能访问");
							return NO_PERMISSION;
						}
					}
				}

				if(getSessionUser()!=null || !getSessionUser().getUsername().equals("guest"))
				{
					if(super.isForumManage(topic.getForumId())){

						isThisForum=1;
					}		
				}
				//脏话和帖子属性
				propertyMap = xmlDataService.select(Symbols.CONFIG_TOPIC);


				userNewlyTopic=topicService.getNewlyTopicsByUser(topic.getUsername(), 5);

				interfixTopics=topicService.getInterfixTopics(topic.getTitle(), 10);

				hotTopics = topicService.getHotTopics(topic.getForumId(), 10);
				if(hotTopics==null){
					hotTopics= new ArrayList();
				}

				commendeTopics = topicService.getGoodTopics(topic.getForumId(), 10);
				if(commendeTopics==null){
					commendeTopics= new ArrayList();
				}

				lastPostTopics =topicService.getLastPostTopics(0, 10);

				category = categoryService.getCategory(topic.getCategoryId());
				/*randomTopic=topicService.getRandomTopics(topic.getForumId());
		if(randomTopic==null){
			randomTopic= new Topic();
		}
		randomTopic1=topicService.getRandomTopics(topic.getForumId());
		if(randomTopic1==null){
			randomTopic1= new Topic();
		}
		randomTopic2=topicService.getRandomTopics(topic.getForumId());
		if(randomTopic2==null){
			randomTopic2= new Topic();
		}
		randomTopic3=topicService.getRandomTopics(topic.getForumId());
		if(randomTopic3==null){
			randomTopic3= new Topic();
		}*/




				if(topic.getIsVote()==1){
					poll = pollService.findPoll(topicId);
				}

				if(hotTopics.size()>0){
					t1=(Topic)hotTopics.get(0);
					Post post=(Post)ps.findPosts(t1.getId().intValue(), 1).get(0);
					t1.setContent(post.getContent());
					pic1=t1.getFirstPicPath();
				}

				if(commendeTopics.size()>0){
					t2=(Topic)commendeTopics.get(0);
					Post post=(Post)ps.findPosts(t2.getId().intValue(), 1).get(0);
					t2.setContent(post.getContent());
					pic2=t2.getFirstPicPath();
				}
				if(interfixTopics.size()>0){
					t3=(Topic)interfixTopics.get(0);
					Post post=(Post)ps.findPosts(t3.getId().intValue(), 1).get(0);
					t3.setContent(post.getContent());
					pic3=t3.getFirstPicPath();
				}
				if(lastPostTopics.size()>0){
					t4=(Topic)lastPostTopics.get(0);
					Post post=(Post)ps.findPosts(t4.getId().intValue(), 1).get(0);
					t4.setContent(post.getContent());
					pic4=t4.getFirstPicPath();
				}




				// 论坛
				forum = ForumSingleton.getInstance().getForum(topic.getForumId());
			}
			else
			{
				setWarnMessage("您没权限去访问已经被删除的帖子");
				return NO_PERMISSION;
			}
		}
		catch(ServiceException se){
			throw new ActionException(se);
		}

		return SUCCESS;
	}

	public Link getAd() {
		return ls.findRandomLinkByType(1);
	}

	public void setAd(Link ad) {
		this.ad = ad;
	}

	public Link getAd2() {
		return ls.findRandomLinkByType(2);
	}

	public void setAd2(Link ad2) {
		this.ad2 = ad2;
	}

	public Link getAd3() {
		return ls.findRandomLinkByType(3);
	}

	public void setAd3(Link ad3) {
		this.ad3 = ad3;
	}

	public Link getAd4() {
		return ls.findRandomLinkByType(4);
	}

	public void setAd4(Link ad4) {
		this.ad4 = ad4;
	}

	public List getHotTopics() {

		return hotTopics;
	}

	public List getCommendeTopics() {
		return commendeTopics;
	}
	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public void setCommendeTopics(List commendeTopics) {
		this.commendeTopics = commendeTopics;
	}

	public void setHotTopics(List hotTopics) {
		this.hotTopics = hotTopics;
	}

	public List getLastPostTopics() {
		return lastPostTopics;
	}

	public void setLastPostTopics(List lastPostTopics) {
		this.lastPostTopics = lastPostTopics;
	}

	public Topic getRandomTopic() {
		return randomTopic;
	}

	public void setRandomTopic(Topic randomTopic) {
		this.randomTopic = randomTopic;
	}

	public Topic getRandomTopic1() {
		return randomTopic1;
	}

	public void setRandomTopic1(Topic randomTopic1) {
		this.randomTopic1 = randomTopic1;
	}

	public Topic getRandomTopic2() {
		return randomTopic2;
	}

	public void setRandomTopic2(Topic randomTopic2) {
		this.randomTopic2 = randomTopic2;
	}

	public Topic getRandomTopic3() {
		return randomTopic3;
	}

	public void setRandomTopic3(Topic randomTopic3) {
		this.randomTopic3 = randomTopic3;
	}

	public String getPic1() {

		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {

		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {


		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPic4() {

		return pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	public Topic getT1() {
		return t1;
	}

	public void setT1(Topic t1) {
		this.t1 = t1;
	}

	public Topic getT2() {
		return t2;
	}

	public void setT2(Topic t2) {
		this.t2 = t2;
	}

	public Topic getT3() {
		return t3;
	}

	public void setT3(Topic t3) {
		this.t3 = t3;
	}

	public Topic getT4() {
		return t4;
	}

	public void setT4(Topic t4) {
		this.t4 = t4;
	}



}
