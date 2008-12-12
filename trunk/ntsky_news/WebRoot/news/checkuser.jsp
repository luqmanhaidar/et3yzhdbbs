<%@ page contentType="text/html; charset=GBK"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "com.ntsky.servlet.*"%>
<%@ page import = "com.ntsky.news.manage.*"%>
<%
/**
 * <p>Title: NTsky新闻发布</p>
 * <p>Description: 用户登陆</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
%>

<jsp:useBean id="syslogin" scope="page" class="com.ntsky.persistence.NEWSUsr">
	<jsp:setProperty name="syslogin" property="*"/>
</jsp:useBean>
<jsp:useBean id="userM" class="com.ntsky.news.UserManage"/>
<jsp:useBean id="sessionManager" class="com.ntsky.servlet.SessionManager"/>
<body bgcolor="#799ae1">
<%
	if(userM.isUsernameOk(syslogin.getUserName())){//用户名判断
		if(userM.isPasswordOk(syslogin.getPassWd())){//密码判断
			userM.upLoadTime(syslogin.getUserName());
			sessionManager.setSession(session,"sessionuser",syslogin.getUserName());
			DOServlet.responseUrl(response,"personal.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('请输入正确的密码!');JavaScript:history.back();</Script>");
		}
	}
	else{
		out.println("<Script language=JavaScript>alert('请输入正确的用户名!');JavaScript:history.back();</Script>");
	}
%>
