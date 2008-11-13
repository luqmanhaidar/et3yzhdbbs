/*
 * 创建日期 2005-5-5
 *
 * TODO 要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package com.ntsky.bbs.webfeed.element;

import com.ntsky.bbs.webfeed.ElementException;

/**
 * @author Administrator
 *
 * TODO 要更改此生成的类型注释的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public interface RSSElement extends ElementIF{

    /**
     * 订阅RSS 
     * @param document
     * @param channel
     * @throws ElementException
     */
    public void feedRSS(Channel channel, String destXMLFile) throws ElementException;

    
}
