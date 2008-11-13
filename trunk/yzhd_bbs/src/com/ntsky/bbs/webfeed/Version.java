package com.ntsky.bbs.webfeed;

import java.util.HashMap;
import java.util.Map;

/**
 * Version的版本信息 
 */
public final class Version {
    
    /**
     * RSS
     */
    final public static String RSS_091 = "0.91";
    final public static String RSS_092 = "0.92";
    final public static String RSS_100 = "1.0";
    final public static String RSS_200 = "2.0";

    /**
     * Atom
     */
    final public static String ATOM_03 = "0.3";
    
    final public static String RSS_ELEMENT_091 = "com.ntsky.bbs.webfeed.element.RSS091ElementImpl";
    final public static String RSS_ELEMENT_092 = "com.ntsky.bbs.webfeed.element.RSS092ElementImpl";
    final public static String RSS_ELEMENT_100 = "com.ntsky.bbs.webfeed.element.RSS100ElementImpl";
    final public static String RSS_ELEMENT_200 = "com.ntsky.bbs.webfeed.element.RSS200ElementImpl";
    final public static String ATOM_ELEMENT_03 = "com.ntsky.bbs.webfeed.element.Atom03ElementImpl";
    
    public static Map VERSION_MAP = new HashMap();
    static {
        VERSION_MAP.put(RSS_091,RSS_ELEMENT_091);
        VERSION_MAP.put(RSS_092,RSS_ELEMENT_092);
        VERSION_MAP.put(RSS_100,RSS_ELEMENT_100);
        VERSION_MAP.put(RSS_200,RSS_ELEMENT_200);
        VERSION_MAP.put(ATOM_03,ATOM_ELEMENT_03);	
    }
}
