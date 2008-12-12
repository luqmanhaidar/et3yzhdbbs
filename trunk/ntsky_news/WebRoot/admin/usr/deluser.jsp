<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*"%>
<%@ include file="check.jsp"%>

<jsp:useBean id="user" class="com.ntsky.news.manage.Usr"/>
<%
	if(user.adminPurview(adminName)>1)
		servlet.responseUrl(response,"../../error/error.jsp");
%>
<body bgcolor="#799ae1">
<%
	//É¾³ýÓÃ»§
	user.delUser(servlet.requestStr(request,"userName"));
	servlet.responseUrl(response,"user.jsp");
%>
</body>