package com.ntsky.bbs.webfeed;

import org.apache.log4j.Logger;

import com.ntsky.bbs.webfeed.element.AtomElement;
import com.ntsky.bbs.webfeed.element.Feed;
import com.ntsky.bbs.webfeed.element.RSSElement;
import com.ntsky.bbs.webfeed.element.Channel;
import com.ntsky.bbs.webfeed.element.ElementBuilderFactory;

public class Feeder {
    
    private static Logger __logger = Logger.getLogger(Feeder.class);
    
    /**
     * 订阅rss
     * @param version
     * @param channel
     * @param destXMLFile
     * @throws RSSException
     */
    public static void feed(String version,Channel channel,String destXMLFile) throws RSSException{
        RSSElement rss = null;
        try {
            rss = (RSSElement)(ElementBuilderFactory.newInstance(version));
            rss.feedRSS(channel,destXMLFile);
        } catch (ElementException e) {
            __logger.error(e.getMessage());
            throw new RSSException(ExceptionGlobals.RSS_FEED_EXINFO);
        }  
    }
    
    /**
     * 订阅Atom
     * @param version
     * @param feed
     * @param destXMLFile
     * @throws AtomException
     */
    public static void feed(String version,Feed feed,String destXMLFile) throws AtomException{
        AtomElement atom = null;
        try {
            atom = (AtomElement)(ElementBuilderFactory.newInstance(Version.ATOM_03));
            atom.feedAtom(feed,destXMLFile);
        } catch (ElementException e) {
            __logger.error(e.getMessage());
            throw new AtomException(ExceptionGlobals.ATOM_FEED_EXINFO);
        }    
    }    
    
    
    
}
