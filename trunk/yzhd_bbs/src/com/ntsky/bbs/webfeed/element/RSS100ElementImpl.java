package com.ntsky.bbs.webfeed.element;

import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import com.ntsky.bbs.webfeed.ElementException;
import com.ntsky.bbs.webfeed.ExceptionGlobals;
import com.ntsky.bbs.webfeed.Globals;
import com.ntsky.bbs.webfeed.DOMData;

public class RSS100ElementImpl extends RSSElementCommon implements RSSElement {

    private static Logger __logger = Logger.getLogger(RSS100ElementImpl.class
            .getName());

    /**
     * 订阅RDF信息
     */
    public void feedRSS(Channel channel, String destXMLFile)
            throws ElementException {

        DOMData domData = DOMData.getInstance();

        domData.initDocument();

        Element root = domData.newRDFRoot();
        Element echannel = domData.addElement(root, Globals.CHANNEL);
        /*
         * Required channel elements
         */
        // Title
        String title =  channel.getTitle();
        if((title != null) && !(Globals.EMPTY_STR.equals(title))){
	        domData.addTextElement(echannel, Globals.CHANNEL_TITLE, title);
	        title = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_TITLE);
        }
        // description
        
        String description = channel.getDescription();
        if((description != null) && !(Globals.EMPTY_STR.equals(description))){
            domData.addCDATAElement(echannel, Globals.CHANNEL_DESCRIPTION, description);
        	description = null;
    	}
	    else{
	        throw new ElementException(ExceptionGlobals.RSS_COMMON_DESCRIPTION);
	    }
        // link
        String link = channel.getLink();
        if((link != null) && !(Globals.EMPTY_STR.equals(link))){
            domData.addTextElement(echannel, Globals.CHANNEL_LINK,link);
            link = null;
        }
        else{
            throw new ElementException(ExceptionGlobals.RSS_COMMON_LINK);
        }
        // language
        String language = channel.getLanguage();
        if((language != null) && !(Globals.EMPTY_STR.equals(language))){
            domData.addTextElement(echannel, Globals.CHANNEL_LANGUAGE,language);
        	language = null;
    	}
    	else{
    	    throw new ElementException(ExceptionGlobals.RSS_COMMON_LANGUAGE);
    	}

        /*
         * Item信息
         */
        try {
            List list = channel.getItems();
            if(list!=null){
	            Object[] object = list.toArray();     
		        for (int i = 0; i < object.length; i++) {
		            Item item = (Item) object[i];
		            addItem(domData, echannel, item);
		            item = null;
		        }
            }
        } catch (ElementException elementException) {
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
    private void addItem(DOMData domData, Element parElement, Item item)
            throws ElementException {
        Element eitem = domData.addElement(parElement, Globals.ITEM);
        // Title
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
    }

}

