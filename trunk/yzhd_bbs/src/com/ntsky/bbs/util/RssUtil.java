package com.ntsky.bbs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.domain.Forum;
import com.ntsky.bbs.domain.Topic;
import com.ntsky.bbs.service.TopicService;
import com.ntsky.bbs.web.schedule.RssTask;
import com.ntsky.bbs.webfeed.Feeder;
import com.ntsky.bbs.webfeed.RSSException;
import com.ntsky.bbs.webfeed.Version;
import com.ntsky.bbs.webfeed.element.Channel;
import com.ntsky.bbs.webfeed.element.Item;
import com.ntsky.framework.util.DateUtil;
import com.ntsky.framework.util.StringUtil;

public class RssUtil {

	public static Logger logger = Logger.getLogger(RssUtil.class.getName());
	
	/**
	 * 订阅rss,版本为rss2.0
	 * @param forum
	 */
	public static void feedRSS20(Forum forum){
		
		ServletContext sc = Application.getInstance().getServletContext();
		
		// 根据论坛编号取得论坛对应的RSS信息
		TopicService topicService = (TopicService)BeanFactory.getInstance(sc).getBean("topicService");
		List topics = topicService.getNewlyTopics(forum.getId().intValue(), 10);
		
		Map basicMap = (Map)sc.getAttribute(Symbols.BASIC);
		
		/**
		 * 整理RSS数据
		 */
		Channel channel = new Channel();
		StringBuffer sb = new StringBuffer();
		sb.append((String)basicMap.get("bbsName"));
		sb.append("-");
		sb.append(forum.getName());
        channel.setTitle(sb.toString());
        channel.setLink((String)basicMap.get("bbsDomain"));
        channel.setDescription((String)basicMap.get("bbsName"));
        channel.setLanguage("UTF-8");
        channel.setCopyright((String)basicMap.get("bbsName"));
        channel.setManagingEditor("yntsky@gmail.com");
        channel.setWebMaster((String)basicMap.get("masterEmail"));
        channel.setPubDate(DateUtil.getDate());
        
        List list = new ArrayList();
        Item item = null;
        Topic topic = null;
        for(int i=0;i<topics.size();i++){
        	topic = (Topic)topics.get(i);
	        item = new Item();
	        item.setTitle(topic.getTitle());
	        item.setDescription(topic.getTitle());
	        item.setLink((String)basicMap.get("bbsDomain")+"topic.action?topicId="+topic.getId());
	        item.setAuthor(topic.getUsername());
	        item.setPubDate(topic.getDateCreated().toLocaleString());
	        list.add(item);        
        }
        channel.setItems(list);
		
        try {
        	if(logger.isDebugEnabled()){
        		logger.debug(forum.getName() + " rss path : " + StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(), "rss/rss-"+forum.getId()+".xml"));
        	}
        	Feeder.feed(Version.RSS_200, channel, StringUtil.applyRelativePath(Application.getInstance().getWebRealPath(), "rss/rss-"+forum.getId()+".xml"));
        } catch (RSSException e) {
            logger.error(e.getMessage(),e);
        }
	}
	
}
