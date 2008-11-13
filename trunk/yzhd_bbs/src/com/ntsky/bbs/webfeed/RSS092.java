package com.ntsky.bbs.webfeed;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ntsky.bbs.webfeed.element.Channel;
import com.ntsky.bbs.webfeed.element.Image;
import com.ntsky.bbs.webfeed.element.Item;
import com.ntsky.bbs.webfeed.element.*;

/**
 * DOM处理  
 */
public class RSS092{
    
    private final static Logger __logger = Logger.getLogger(RSS092.class);
    
    public void feed(Channel channel,String fileName){
        channel = new Channel();
        channel.setTitle("播客中国");
        channel.setLink("http://www.podcast.com.cn");
        channel.setDescription("sfdasfasfdsafsafsadf");
        channel.setLanguage("UTF-8");
        channel.setCopyright("11");
        channel.setManagingEditor("yntsky@gmail.com");
        channel.setWebMaster("yntsky@gmail.com");
        channel.setPubDate("2005-4-25 18:14:00");
        channel.setDocs("11111111111");
        
        Image image = new Image();
        image.setTitle("播客中国");
        image.setUrl("http://www.podcast.com.cn/images/logo.jpg");
        image.setLink("http://www.podcast.com.cn");
        image.setWidth("400");
        image.setHeight("200");
        image.setDescription("pppppp");
        
        channel.setImage(image);
        List list = new ArrayList();
        Item item = null;
        for(int i=0;i<5;i++){
	        item = new Item();
	        item.setTitle("播客中国");
	        item.setDescription("博客中国——每天五分钟，给思想加油");
	        item.setLink("http://www.podcast.com.cn");
	        item.setAuthor("ntsky");
	        item.setPubDate("2005-4-25 18:14:00");
	        list.add(item);        
        }
        
        channel.setItems(list);
        
        Feed feed = new Feed();
        feed.setTitle("afdsaf");
        feed.setModified("asdfas");
        feed.setLanguage("zh-cn");
        Link link = new Link();
        link.setRel("cc");
        link.setType("html");
        link.setHref("sfdsa");
        feed.setLink(link);
        feed.setTagline("asdfasf");

        
        AtomElement atom = null;
        try {
            atom = (AtomElement)(ElementBuilderFactory.newInstance(Version.ATOM_03));
            atom.feedAtom(feed,"c:/091.xml");
        } catch (ElementException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        
    }
}
