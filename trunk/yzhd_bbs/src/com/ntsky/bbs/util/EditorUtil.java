/*
 * Copyright (c) 2001-2005 by www.ntsky.com All rights reserved.
 */
package com.ntsky.bbs.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.ntsky.framework.util.StringUtil;
import com.ntsky.framework.util.xml.SAXHelper;

import com.ntsky.bbs.Symbols;
import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.xml.EditorHandler;
import com.ntsky.bbs.exception.XMLException;

public class EditorUtil {

    private static Map editorMap = null;

    /**
     * 设置CdataMap数据
     *
     */
    public static void setEditorMap() throws XMLException{
    	EditorHandler editorHandler = new EditorHandler();
        try {
            SAXHelper.parseXML(Application.getInstance().getFilePath(Symbols.XML_EDITOR), editorHandler);
        } catch (Exception exception) {
            throw new XMLException("解析Editor xml发生错误");
        }
        editorMap = editorHandler.getEditorMap();
    }

    /**
     * 判断该field是否要进行字符转换
     * 
     * @param object
     * @param field
     * @return
     */
    public static boolean isEditor(Object object,String field){
        String className = object.getClass().getName();
        
        TreeSet fieldsSet = (TreeSet)editorMap.get(className);
        if(fieldsSet!=null){
            if(fieldsSet.contains(field)){
                return true;
            }
        }
        return false;
    }
    
}