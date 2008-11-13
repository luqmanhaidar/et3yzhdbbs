package com.ntsky.bbs.util;

import java.util.ArrayList;
import java.util.List;

import com.ntsky.bbs.service.TopicService;

public class IndexRightUtil {
	private List newLyTopics;
	private List dayTopics;
	private List weekTopics;
    private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private TopicService topicService=(TopicService)bf.getBean("topicService");
	public List getDayTopics() {
		dayTopics = topicService.getDayTopics(0, 10);
		if(dayTopics==null){
			dayTopics= new ArrayList();
		}
		return dayTopics;
	}

	public List getNewLyTopics() {
		newLyTopics = topicService.getNewlyTopics(0, 10);
		if(newLyTopics==null){
			newLyTopics= new ArrayList();
		}
		return newLyTopics;
	}

	public List getWeekTopics() {
		weekTopics = topicService.getWeekTopics(0, 10);
		if(weekTopics==null){
			weekTopics= new ArrayList();
		}
		return weekTopics;
	}

	

	
	
}
