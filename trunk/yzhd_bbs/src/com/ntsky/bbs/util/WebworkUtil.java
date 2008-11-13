package com.ntsky.bbs.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ntsky.bbs.Symbols;
import com.ntsky.framework.util.HttpUtil;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

/**
 * WebWork工具类
 * @author Administrator
 *
 */
public class WebworkUtil {
	
	
    public static HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();  
    }
    
    /**
     * 取得request中parameter的值
     * @param parameter 参数
     * @return string 参数的值
     */
    public static String getParameter(String parameter) {
    	Object value = getParameters().get(parameter);
    	if(value!=null){
    		return ((String[])value)[0];
    	}
 		return "";
    }
    
	/**
	 * 取得参数数组
	 * @return Map 参数数组
	 */
    public static Map getParameters() {
        ActionContext actionContext = ActionContext.getContext();
    	Map parameters = (Map)actionContext.getParameters();    
    	return parameters;
    }
    
    /**
     * 取得request中parameter的整数值
     * @param parameter 参数
     * @return string 参数的值
     */
    /**
     * 取得整型参数的值
     * @param parameter 参数
     * @return int 当前类别
     */
    public static int getIntParameter(String parameter){
    	Object value = getParameters().get(parameter);
    	if(value!=null){
    		return Integer.parseInt(((String[])value)[0]);
    	}
 		return 0;    	
    }
    
    /**
     * 设置用户Cookie
     * @param userId
     */
    public static void setFCKeditorCookie(String userId){
    	HttpUtil.addCookie(ServletActionContext.getResponse(),Symbols.COOKIE_FCKEDITOR,userId,-1);
    }
    
}
