<%@ page contentType="text/html; charset=GBK"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "com.ntsky.servlet.*"%>
<%@ page import = "com.ntsky.news.manage.*"%>
<%
/**
 * <p>Title: NTsky���ŷ���</p>
 * <p>Description: �û���½</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
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
	if(userM.isUsernameOk(syslogin.getUserName())){//�û����ж�
		if(userM.isPasswordOk(syslogin.getPassWd())){//�����ж�
			userM.upLoadTime(syslogin.getUserName());
			sessionManager.setSession(session,"sessionuser",syslogin.getUserName());
			DOServlet.responseUrl(response,"personal.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('��������ȷ������!');JavaScript:history.back();</Script>");
		}
	}
	else{
		out.println("<Script language=JavaScript>alert('��������ȷ���û���!');JavaScript:history.back();</Script>");
	}
%>
