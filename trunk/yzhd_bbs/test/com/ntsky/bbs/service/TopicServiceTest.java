package com.ntsky.bbs.service;

import java.util.Date;

import com.ntsky.bbs.domain.Post;
import com.ntsky.bbs.domain.Topic;

public class TopicServiceTest extends NTskyServiceTestCase{

	private TopicService topicService;
	private Topic topic = new Topic();;
	
    protected void setUp() throws Exception {
    	topicService = (TopicService)super.getBean("topicService");
    }
	
	public void testCreateTopic(){
    	topic.setMood(1);
    	topic.setForumId(1);
    	topic.setTitle("ntksy's bbs published!");
    	topic.setDateCreated(new Date());

    	//topicService.createTopic(topic);
	}
	
	public void testEditTopic(){
		topicService.editTopic(topicService.getTopic(1),new Post());
	}
	
	public void testGetTopic(){
		//assertEquals(topicService.getTopic(1).getAuthor(),"ntsky");
	}
	
	public void testDeleteTopic(){
		topicService.deleteTopic(1);
	}
	
	public void setDown(){
		topic = null;
		topicService = null;
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TopicServiceTest.class);
	}		
	
}
