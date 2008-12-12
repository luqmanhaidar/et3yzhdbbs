<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*"%>
<%@ include file="check.jsp" %>

<jsp:useBean id="Column" scope="page" class="com.ntsky.news.manage.Column"/>

<body bgcolor="#799ae1">
<%
	int classId = servlet.requestInt(request,"classId");
	//Ö´ÐÐÉ¾³ý
	Column.delColumn(classId);
	servlet.responseUrl(response,"column.jsp");
%>
</body>