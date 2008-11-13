package com.ntsky.bbs.webfeed.element;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.ntsky.bbs.webfeed.Globals;
import com.ntsky.bbs.webfeed.DOMData;

import com.ntsky.bbs.webfeed.ElementException;
import com.ntsky.bbs.webfeed.ExceptionGlobals;

public class Atom03ElementImpl implements AtomElement {

    private static Logger __logger = Logger.getLogger(Atom03ElementImpl.class
            .getName());

    /**
     * 订阅Atom信息
     */
    public void feedAtom(Feed feed, String destXMLFile)
            throws ElementException{

        DOMData domData = DOMData.getInstance();

        domData.initDocument();
        Element efeed = null;
        String language = feed.getLanguage();
        if((language==null)||(Globals.EMPTY_STR.equals(language))){
            throw new ElementException(ExceptionGlobals.ATOM_COMMON_LANGUAGE);
        }
        else{
            efeed = domData.newAtomRoot(Globals.VERSION_ATOM_03,feed.getLanguage());
        }
        /*
         * Required channel elements
         */
        // Title
        String title =  feed.getTitle();
        if((title != null) && !(Globals.EMPTY_STR.equals(title))){
	        domData.addTextElement(efeed, Globals.FEED_TITLE, title);
	        title = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.ATOM_COMMON_TITLE);
        }        
        
        __logger.info("创建 link 信息...");
        
        // link
        Link link = feed.getLink();
        Map treeMap = new TreeMap();
        treeMap.put(Globals.FEED_LINK_REL,link.getRel());
        treeMap.put(Globals.FEED_LINK_TYPE,link.getType());
        treeMap.put(Globals.FEED_LINK_HREF,link.getHref());
        
        domData.addMoreAttElement(efeed,Globals.FEED_LINK,treeMap);
        
        __logger.info("创建modified信息...");       
        // modified
        String modified =  feed.getModified();
        if((modified != null) && !(Globals.EMPTY_STR.equals(modified))){
	        domData.addTextElement(efeed, Globals.FEED_MODIFIED, modified);
	        modified = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.ATOM_COMMON_MODIFIED);
        }        
        __logger.info("创建modified信息...");
        
        // tagline
        String tagline = feed.getTagline();
        if((tagline != null) && !(Globals.EMPTY_STR.equals(tagline))){
	        domData.addTextElement(efeed, Globals.FEED_TAGLINE, tagline);
	        tagline = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.ATOM_COMMON_TAGLINE);
        }         
        
        /*
         * Item信息
         */
        try{
            List list = feed.getItems();
            if(list!=null){
	            Object[] object = list.toArray();     
		        for (int i = 0; i < object.length; i++) {
		            Item item = (Item) object[i];
		            addItem(domData, efeed, item);
		            item = null;
		        }
            }
        }
        catch(ElementException elementException){
            __logger.error(elementException.getMessage());
            throw new ElementException(elementException.getMessage());
        }

        domData.outPutXML(destXMLFile);
    }

    /**
     * item的数据信息
     * 
     * @param domData
     * @param parElement
     * @param item
     */
    private void addItem(DOMData domData, Element parElement, Item item) throws ElementException{
        Element eitem = domData.addElement(parElement, Globals.ITEM);
        // title
        String title = item.getTitle();
        if ((title != null) && !(Globals.EMPTY_STR.equals(title))) {
            domData.addTextElement(eitem, Globals.ITEM_TITLE, title);
            title = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_TITLE);
        }
        
        // link 
        String link = item.getLink();
        if ((link != null) && !(Globals.EMPTY_STR.equals(link))){
            domData.addTextElement(eitem, Globals.ITEM_LINK,link);
            link = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_LINK);
        }
        
        // description 
        String description  = item.getDescription();
        if ((description != null) && !(Globals.EMPTY_STR.equals(description))){ 
            domData.addCDATAElement(eitem, Globals.ITEM_DESCRIPTION,description);
            description = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_DESCRIPTION);
        }
        
        // author
        String author  = item.getAuthor();
        if ((author != null) && !(Globals.EMPTY_STR.equals(author))){ 
            domData.addCDATAElement(eitem, Globals.ITEM_AUTHOR,author);
            author = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_DESCRIPTION);
        } 
        
        // pubDate
        String pubDate  = item.getPubDate();
        if ((pubDate != null) && !(Globals.EMPTY_STR.equals(pubDate))){ 
            domData.addCDATAElement(eitem, Globals.ITEM_PUBDATE,pubDate);
            author = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_DESCRIPTION);
        }         
    }
}