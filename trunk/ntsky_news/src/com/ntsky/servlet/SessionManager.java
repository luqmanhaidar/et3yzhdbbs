package com.ntsky.servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: Session的控制</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class SessionManager{

    /**
     * 设置session
     */
    public void setSession(HttpSession session, String str, String username){
        session.setAttribute(str,username);
    }
    /**
     * 取得session的值
     * @param session
     * @param str
     * @return
     */
    public String getSession(HttpSession session,String str){
        String user=null;
        user=(String)session.getAttribute(str);
        return user;
    }
    /**
     * 取消session
     */
    public static void removeSession(HttpSession session,String user){
        session.removeAttribute(user);
    }
}