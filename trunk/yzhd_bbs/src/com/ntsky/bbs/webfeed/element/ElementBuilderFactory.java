/*
 * 创建日期 2005-5-5
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.ntsky.bbs.webfeed.element;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.ntsky.bbs.webfeed.Version;
import com.ntsky.bbs.webfeed.ElementException;

public class ElementBuilderFactory {
    
    private final static Logger __logger = Logger.getLogger(ElementBuilderFactory.class);
    private static Hashtable hashtable = new Hashtable();
    
	public static ElementIF newInstance(String version) throws ElementException{
	    ElementIF elementIF = null;
        try {
            elementIF = (ElementIF)hashtable.get(version);
            if(elementIF == null){
                String className = (String)Version.VERSION_MAP.get(version);
                __logger.info("加载的rss类为 ：" + className);
	            Class c = Class.forName(className);
	            elementIF = (ElementIF)c.newInstance();
	            hashtable.put(version,elementIF);
            }
        }
        catch (ClassNotFoundException e) {
            __logger.error(e.getMessage());
            throw new ElementException("加载 " + version + "错误");
        }
        catch (IllegalAccessException e1) {
            __logger.error(e1.getMessage());
            throw new ElementException("加载 " + version + "错误");
        }
        catch (InstantiationException e2) {
            __logger.error(e2.getMessage());
            throw new ElementException("加载 " + version + "错误");
        }
        return elementIF;
	}
}