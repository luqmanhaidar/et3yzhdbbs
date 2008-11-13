package com.ntsky.bbs.util.memory;

import java.util.ArrayList;
import java.util.List;

import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;

public class TopicSingleton {
	private static TopicSingleton topicSingleton = null;

	/**
	 * 取得论坛实例
	 * 
	 * @return
	 */
	public synchronized static TopicSingleton getInstance() {
		if (topicSingleton == null) {
			topicSingleton = new TopicSingleton();
		}
		return topicSingleton;
	}

	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private TopicService topicService=(TopicService)bf.getBean("topicService");
	
	
	private List newlyTopic = new ArrayList();

	private List goodTopic = new ArrayList();

	public List getGoodTopic() {
		goodTopic=topicService.getGoodTopics(0, 20);
		return goodTopic;
	}

	public void setGoodTopic(List goodTopic) {
		this.goodTopic = goodTopic;
	}

	public List getNewlyTopic() {
		newlyTopic=topicService.getNewlyTopics(0, 20);
		return newlyTopic;
	}

	public void setNewlyTopic(List newlyTopic) {
		this.newlyTopic = newlyTopic;
	}

}
