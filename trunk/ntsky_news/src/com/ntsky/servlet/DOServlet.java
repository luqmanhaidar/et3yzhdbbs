package com.ntsky.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ntsky.common.*;
/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description:ȡֵ & ��ת</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */

public class DOServlet{

	private String str;
    private int itr;

    /**
     * ȡֵ
     * @param request
     * @param varStr
     */
    //�ַ���
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
    //����
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
    //��ת
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