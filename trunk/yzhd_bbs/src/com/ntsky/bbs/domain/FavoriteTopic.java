package com.ntsky.bbs.domain;

import java.util.List;

/**
 * 投票类
 * 
 * @author ntsky
 * @link www.ntsky.com
 * 
 * @version $Revision: 1.2 $ $Date: 2008/10/24 02:47:42 $
 */
public class FavoriteTopic extends Entity{

	private int userId;
	private int topicId;
	private Topic topic;


	public int getTopicId() {
		return topicId;
	}
	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	


	
	
}
