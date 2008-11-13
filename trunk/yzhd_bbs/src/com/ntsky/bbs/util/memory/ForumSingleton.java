package com.ntsky.bbs.util.memory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.service.ForumService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;
import com.ntsky.framework.util.StringUtil;

public class ForumSingleton {
	
	private static Logger logger = Logger.getLogger(ForumSingleton.class);

	public static List forums = null;
	public static Map forumsMap = new HashMap();
	
	private static ForumSingleton forumSingleton = null;
	
	public static List ForumsInIndex=null;
	
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private ForumService forumService=(ForumService)bf.getBean("forumService");
	
	
	/**
	 * 取得论坛实例
	 * @return
	 */
	public synchronized static ForumSingleton getInstance(){
		if(forumSingleton==null){
			forumSingleton = new ForumSingleton();
		}
		return forumSingleton;
	}
	
	/**
	 * 重置Forums数据
	 * @param forums
	 */
	public void resetForums(List forums){
		this.forums = forums;
		Object[] forumArray = forums.toArray();
		Forum forum = null;
		for (int i = 0; i < forumArray.length; i++) {
			forum = (Forum)forumArray[i];
			forumsMap.put(forum.getId(),forum);
		}
	}
	
	// 设置Forum数据
	public void setForums(List list){
		forums = list;
		Object[] forumArray = forums.toArray();
		Forum forum = null;
		for (int i = 0; i < forumArray.length; i++) {
			forum = (Forum)forumArray[i];
			forumsMap.put(forum.getId(),forum);
		}
	}
	
	// 添加Forum
	public void addForum(Forum forum){
		forumsMap.put(forum.getId(), forum);
	}
	
	// 更新Forum
	public void resetForum(Forum forum){
		forumsMap.remove(forum.getId());
		forumsMap.put(forum.getId(), forum);
	}
	
	// 删除内存
	public void deleteForum(int forumId){
		forumsMap.remove(forumId);
	}
	
	/**
	 * 取得全部的论坛
	 * @return 论坛数据
	 */
	public List getForums(){
		return forums;
	}
	
	/**
	 * 取得首页的论坛信息
	 * @return
	 */
	public List getIndexForums(){
		return forums;
	}
	
	/**
	 * 设置最后更新主题
	 * 
	 * 后台有个监视机器人，每天24:00重置论坛状态
	 * 
	 * <pre>设置最后发表回复的主题，更新贴子状态</pre>
	 * @param topic
	 */
	public void setLastPostTopic( int forumId, Topic lastTopic ){
		Forum forum = (Forum)forumsMap.get(new Long(forumId));
		if(lastTopic==null){
			forum.setLastPostTopic(null);
			forum.setStatus(0);
		}
		else{
			forum.setLastPostTopic(lastTopic);
			// 今日有帖
			if(lastTopic.getDateCreated().getDate() == (new Date()).getDate()){
				forum.setStatus(1);
			}
			else{
				forum.setStatus(0);
			}
		}
	}
	
	/**
	 * 根据论坛编号取得论坛信息
	 * 
	 * @param forumId 论坛编号
	 * @return forum 论坛数据
	 */
	public Forum getForum(int forumId){
		if(forumId!=0)
		{
			return (Forum)forumService.getForum(forumId);
		}
		else
		{
			return null;
		}
	}
		
	/**
	 * 取得子论坛列表
	 * @param forumId
	 * @return
	 */
	public List getChildForums(int forumId){
		Object[] forumArray = forums.toArray();
		List childList = new ArrayList();
		Forum forum = null;
		for (int i = 0; i < forumArray.length; i++) {
			forum = (Forum)forumArray[i];
			if(forum.getParentId() == forumId){
				childList.add(forum);
			}
		}
		return childList;
	}
	
	/**
	 * 设置今日发贴总数
	 * @param forumId 论坛编号
	 * @param operator
	 */
	public void setTodayPostCount(int forumId,String operator){
		Forum forum = getForum(forumId);
		Object[] forumArray = null;
		if(Symbols.INCREASE.equals(operator)){
			// 设置当前论坛今日帖子总数
			forum.setTotalTodayPost(forum.getTotalTodayPost()+1);
			// 父类今日帖子总数
			if(!("0".equals(forum.getParentEnum()))){
				forumArray = StringUtil.splitStringToArray(forum.getParentEnum(),",");
				for (int i = 0; i < forumArray.length; i++) {
					setTodayPostCountTemp(Integer.parseInt((String) forumArray[i]),Symbols.INCREASE);
				}
			}
		}
		else{
			if(Symbols.DECREASE.equals(operator)){
				// 当删除帖子时，当今日帖子不为0时进行更新，如果为0则不进行操作
				if(forum.getTotalTodayPost()!=0){
					// 当前论坛
					forum.setTotalTodayPost(forum.getTotalTodayPost()-1);
				}
				
				// 父论坛
				if(!("0".equals(forum.getParentEnum()))){
					forumArray = StringUtil.splitStringToArray(forum.getParentEnum(),",");
					for (int i = 0; i < forumArray.length; i++) {
						setTodayPostCountTemp(Integer.parseInt((String) forumArray[i]),Symbols.DECREASE);
					}
				}
			}
		}
	}	
	
	private void setTodayPostCountTemp(int forumId,String operator){
		Forum forum = getForum(forumId);
		if(Symbols.INCREASE.equals(operator)){
			forum.setTotalTodayPost(forum.getTotalTodayPost()+1);
		}
		else{
			if(Symbols.DECREASE.equals(operator)){
				if(forum.getTotalTodayPost()!=0){
					forum.setTotalTodayPost(forum.getTotalTodayPost()-1);
				}
			}
		}
	}
	
	/**
	 * 设置论坛发贴总数
	 * @param forumId 论坛编号
	 * @param operator 操作(INCREASE,增加|DECREASE,减少)
	 */
	public void setPostCount(int forumId,String operator){
		Forum forum = getForum(forumId);
		Object[] forumArray = null;
		if(Symbols.INCREASE.equals(operator)){
			// 设置当前论坛帖子总数
			forum.setTotalPost(forum.getTotalPost()+1);
			// 父类帖子总数
			if(!("0".equals(forum.getParentEnum()))){
				forumArray = StringUtil.splitStringToArray(forum.getParentEnum(),",");
				for (int i = 0; i < forumArray.length; i++) {
					setPostCountTemp(Integer.parseInt((String) forumArray[i]),Symbols.INCREASE);
				}
			}
		}
		else{
			if(Symbols.DECREASE.equals(operator)){
				// 当前论坛
				forum.setTotalPost(forum.getTotalPost()-1);
				// 父论坛
				if(!("0".equals(forum.getParentEnum()))){
					forumArray = StringUtil.splitStringToArray(forum.getParentEnum(),",");
					for (int i = 0; i < forumArray.length; i++) {
						setPostCountTemp(Integer.parseInt((String) forumArray[i]),Symbols.DECREASE);
					}
				}
			}
		}
	}	
	public void setPostCountTemp(int forumId,String operator){
		Forum forum = getForum(forumId);
		if(Symbols.INCREASE.equals(operator)){
			getForum(forumId).setTotalPost(forum.getTotalPost()+1);
		}
		else{
			if(Symbols.DECREASE.equals(operator)){
				getForum(forumId).setTotalPost(forum.getTotalPost()-1);
			}
		}
	}
	
	/**
	 * 设置论坛主题总数
	 * @param forumId 论坛编号
	 * @param operator
	 */
	public void setTopicCount(int forumId,String operator){
		Forum forum = getForum(forumId);
		Object[] forumArray = null;
		if(Symbols.INCREASE.equals(operator)){
			// 设置当前论坛主题总数
			forum.setTotalTopic(forum.getTotalTopic()+1);
			// 父类主题总数
			if(!("0".equals(forum.getParentEnum()))){
				forumArray = StringUtil.splitStringToArray(forum.getParentEnum(),",");
				for (int i = 0; i < forumArray.length; i++) {
					setTopicCountTemp(Integer.parseInt((String) forumArray[i]),Symbols.INCREASE);
				}
			}
		}
		else{
			if(Symbols.DECREASE.equals(operator)){
				// 当前论坛
				forum.setTotalTopic(forum.getTotalTopic()-1);
				// 父论坛
				if(!("0".equals(forum.getParentEnum()))){
					forumArray = StringUtil.splitStringToArray(forum.getParentEnum(),",");
					for (int i = 0; i < forumArray.length; i++) {
						setTopicCountTemp(Integer.parseInt((String) forumArray[i]),Symbols.DECREASE);
					}
				}
			}
		}
	}	
	
	private void setTopicCountTemp(int forumId,String operator){
		Forum forum = getForum(forumId);
		if(Symbols.INCREASE.equals(operator)){
			forum.setTotalTopic(forum.getTotalTopic()+1);
		}		
		else{
			if(Symbols.DECREASE.equals(operator)){
				forum.setTotalTopic(forum.getTotalTopic()-1);
			}
		}
	}

	public List getForumsInIndex() {
		ForumsInIndex=forumService.findForumsIsTop();
		return ForumsInIndex;
	}

	public void setForumsInIndex(List forumsInIndex) {
		ForumsInIndex = forumsInIndex;
	}

	
}
