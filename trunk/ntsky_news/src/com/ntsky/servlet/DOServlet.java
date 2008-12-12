package com.ntsky.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ntsky.common.*;
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description:取值 & 跳转</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */

public class DOServlet{

	private String str;
    private int itr;

    /**
     * 取值
     * @param request
     * @param varStr
     */
    //字符串
	public String requestStr(HttpServletRequest request, String varStr){
		try{
			str = CodeFilter.toHtml(request.getParameter(varStr));
       }
       catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("DOServlet RequestStr(), Exception Occured ! Info :" + e.getLocalizedMessage());
	   }
       return str;
    }
    //整数
    public int requestInt(HttpServletRequest request, String varStr){
        try{
            itr = Integer.parseInt(request.getParameter(varStr));
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("DOServlet RequestStr(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        return itr;
    }
    //跳转
	public static void responseUrl(HttpServletResponse response,String url){
		try{
            response.sendRedirect(url);
        }
        catch(Exception e){
            e.printStackTrace(System.out);
            Debug.writeLog("DOServlet responseUrl(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
	}
}