package com.ntsky.servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpSession;

/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: Session�Ŀ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class SessionManager{

    /**
     * ����session
     */
    public void setSession(HttpSession session, String str, String username){
        session.setAttribute(str,username);
    }
    /**
     * ȡ��session��ֵ
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
     * ȡ��session
     */
    public static void removeSession(HttpSession session,String user){
        session.removeAttribute(user);
    }
}