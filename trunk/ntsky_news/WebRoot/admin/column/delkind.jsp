<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*"%>
<%@ include file="check.jsp" %>

<jsp:useBean id="Kind" scope="page" class="com.ntsky.news.manage.Kind"/>

<body bgcolor="#799ae1">
<%
	int kindId = servlet.requestInt(request,"kindId");
	//Ö´ÐÐÉ¾³ý
	Kind.delKind(kindId);
	servlet.responseUrl(response,"kind.jsp");
%>
</body>