<%@ page contentType="text/html; charset=GBK" %>
<%@ page import = "java.io.*"%>
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

<jsp:useBean id="syslogin" scope="page" class="com.ntsky.persistence.NEWSAdmin"/>
	<jsp:setProperty name="syslogin" property="*"/>
<jsp:useBean id="isIn" class="com.ntsky.news.manage.ISLogin"/>
<jsp:useBean id="SessionManager" class="com.ntsky.servlet.SessionManager"/>
<body bgcolor="#799ae1">
<%
	if(isIn.isUsernameOk(syslogin.getUserName())){//�û����ж�
		if(isIn.isPasswordOk(syslogin.getUserName(),syslogin.getPassWd())){//�����ж�
			SessionManager.setSession(session,"user",syslogin.getUserName());
			isIn.upTimeAndIp(request.getRemoteAddr());
			DOServlet.responseUrl(response,"default.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('��������ȷ������!');JavaScript:history.back();</Script>");
		}
	}
	else{
		out.println("<Script language=JavaScript>alert('��������ȷ���û���!');JavaScript:history.back();</Script>");
	}
%>
</body>