package com.ntsky.bbs.web.webwork.action.topic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Poll;
import com.ntsky.bbs.domain.PollResult;
import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.exception.ActionException;
import com.ntsky.bbs.exception.ServiceException;
import com.ntsky.bbs.service.XmlDataService;
import com.ntsky.bbs.util.BeanUtil;
import com.ntsky.bbs.util.memory.ForumSingleton;
import com.ntsky.bbs.xml.bean.Badword;
import com.opensymphony.xwork.ModelDriven;

/**
 * 修改主题信息
 * <ul>
 * <li>edit -- 检索主题</li>
 * <li>execute -- 更新主题</li>
 * </ul>
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.4 $ $Date: 2008/11/10 15:59:26 $
 */
public class EditTopicAction extends TopicActionSupport implements ModelDriven {

	private Topic topic = new Topic();

	private XmlDataService xmlDataService;

	private Map propertyMap;

	private int topicId;

	private List ballotOptionNum = new ArrayList();

	private Poll poll;

	private int optionNum = 0;
	
	private List badwords;

	public int getOptionNum() {
		return optionNum;
	}

	public void setOptionNum(int optionNum) {
		this.optionNum = optionNum;
	}

	public Poll getPoll() {
		return this.poll;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public int getTopicId() {
		return this.topicId;
	}

	/**
	 * 修改主题(更新主题数据)
	 * 
	 * @return String 执行信息
	 */
	public String execute() throws Exception {
		System.out.println("enter edit execute ====");
		if (logger.isInfoEnabled()) {
			logger.info("更新主题 [' " + topic.getTitle() + " '] 信息");
		}
		// 如果该帖子不是原用户发表
		if (!(topic.getUsername().equals(getSessionUser().getUsername()))) {
			if (logger.isInfoEnabled()) {
				logger.info("如果不是该用户发表的帖子,判断是否是有权限用户编辑帖子");
			}
			if (super.isAccess("canEditOther") == 0) {
				setWarnMessage("您没有修改帖子[' " + topic.getTitle() + " ']的权限.");
				return NO_PERMISSION;
			}
		}
		PollResult pollResult = null;
		try {
			badwords = xmlDataService.getBadwords();
			Post firstPost = new Post();
			firstPost.setId(topic.getFirstPostId());
			firstPost.setTitle(topic.getTitle());
			firstPost.setContent(topic.getContent());
			
			
			
			
			if(topic.getIsVote() == 1){
				
				
				poll = pollService.findPoll(topic.getId().intValue());				
				
				int optionId = 0;
				String optionText = "";
				if(logger.isInfoEnabled()){
					//logger.info("投票选项数: " + optionNum);
				}				
				//List list = new ArrayList();
				/*Set set = new LinkedHashSet();
				
				for (int i = 0; i < optionNum; i++) {
					pollResult = new PollResult();
					
					String indexTemp="optionText"+(i+1);					
					optionText = getRequest().getParameter(indexTemp);
					if(optionText!=null&&!optionText.equals("")){
						optionId++;
						for(int b=0;b<badwords.size();b++)
						{
							Badword badword= new Badword();
							badword = (Badword)badwords.get(b);
							optionText =optionText.replaceAll(badword.getOldStr(), badword.getReplaceStr());
						}
						
						pollResult.setOptionId(optionId);
						pollResult.setOptionText(optionText);
						pollResult.setPoll(poll);						
						set.add(pollResult);
					}
				}*/
				
				//--------------
				Set thisPollResults=poll.getPollResults();
				Object[] tpr=thisPollResults.toArray();
				//Iterator it=thisPollResults.iterator();
				
				int index=0;
				int index2=0;
				for(int i=0;i<tpr.length;i++){
					pollResult=(PollResult)tpr[i];
					
					optionText = getRequest().getParameter("optionText"+(++index));
					
					if(optionText!=null&&!optionText.equals("")){
						for(int b=0;b<badwords.size();b++)
						{
							Badword badword= new Badword();
							badword = (Badword)badwords.get(b);
							optionText =optionText.replaceAll(badword.getOldStr(), badword.getReplaceStr());
						}
						index2++;
						pollResult.setOptionId(index2);
						pollResult.setOptionText(optionText);
						pollResult.setPoll(poll);						
						thisPollResults.add(pollResult);
					}else{
						thisPollResults.remove(pollResult);
					}
				}
				
				while(index<=optionNum){
					pollResult=new PollResult();
					optionText = getRequest().getParameter("optionText"+(++index));
					if(optionText!=null&&!optionText.equals("")){
						for(int b=0;b<badwords.size();b++)
						{
							Badword badword= new Badword();
							badword = (Badword)badwords.get(b);
							optionText =optionText.replaceAll(badword.getOldStr(), badword.getReplaceStr());
						}
						index2++;
						pollResult.setOptionId(index2);
						pollResult.setOptionText(optionText);
						pollResult.setPoll(poll);						
						thisPollResults.add(pollResult);
					}
				}
				
				
				
				//-------------
				
				
				poll.setPollResults(thisPollResults);
				pollService.saveOrUpdatePoll(poll);	
			}
				
			
			topicService.editTopic((Topic) BeanUtil.format(topic),
					(Post) BeanUtil.format(firstPost));
			
			
		} catch (ServiceException se) {
			throw new ActionException(se);
		}

		topicId = topic.getId().intValue();
		forum = ForumSingleton.getInstance().getForum(
				super.getIntParameter(Symbols.PARA_FORUM_ID));
		return SUCCESS;
	}

	/**
	 * 主题数据
	 */
	public Object getModel() {
		return topic;
	}

	private int forumId;

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	private Forum forum = null;

	public Forum getForum() {
		return this.forum;
	}

	private List categories;

	public List getCategories() {
		return this.categories;
	}

	/**
	 * 主题数据
	 */
	public Topic getTopic() {
		return this.topic;
	}

	/**
	 * 修改主题(查找主题数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String doEdit() throws Exception {
		if (logger.isInfoEnabled()) {
			logger.info("修改主题(检索Topic编号为['" + topicId + "']的主题.)");
		}

		try {
			topic = topicService.getEditedTopic(topicId);

			// ---------- 权限 ------------
			// if(super.isAccess("canEditPost")==0){
			// return NO_PERMISSION;
			// }
			// else{

			if (topic.getIsVote() == 1) {
				poll = pollService.findPoll(topicId);
				int pollResultsNum = poll.getPollResults().size();

				this.propertyMap = xmlDataService.select(Symbols.CONFIG_TOPIC);
				String numTempStr = (String) propertyMap.get("ballotOptionNum");

				int numTemp = 0;// 规定最大选项

				if (numTempStr != null && !numTempStr.equals("")) {
					try {
						numTemp = Integer.parseInt(numTempStr);
					} catch (Exception e) {
						numTemp = 0;
					}
				}

				optionNum = pollResultsNum;
				if (numTemp > pollResultsNum) {
					optionNum = numTemp;
					int xunhuan = numTemp - pollResultsNum;
					for (int i = pollResultsNum + 1; i < numTemp + 1; i++) {
						ballotOptionNum.add(i);
					}
				}

			}

			// 如果该帖子不是原用户发表
			if (!(topic.getUsername().equals(getSessionUser().getUsername()))) {
				if (logger.isInfoEnabled()) {
					logger.info("如果不是该用户发表的帖子,判断是否是有权限用户编辑帖子");
				}
				if (super.isAccess("canEditOther") == 0) {
					setWarnMessage("您没有修改帖子[' " + topic.getTitle() + " ']的权限.");
					return NO_PERMISSION;
				}
			}
			// }

			forum = ForumSingleton.getInstance().getForum(forumId);
			this.propertyMap = xmlDataService.select(Symbols.CONFIG_TOPIC);
			// 主题帖将修改主题信息
			categories = categoryService.getCategories(0);

		} catch (ServiceException se) {
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

	public List getBallotOptionNum() {
		return ballotOptionNum;
	}

	public void setBallotOptionNum(List ballotOptionNum) {
		this.ballotOptionNum = ballotOptionNum;
	}

}
