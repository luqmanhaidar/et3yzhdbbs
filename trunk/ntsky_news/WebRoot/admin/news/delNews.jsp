<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*" %>
<%@ include file="check.jsp" %>

<%
/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: ɾ��ָ������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
%>

<jsp:useBean id="news" scope="page" class="com.ntsky.news.manage.News"/>

<%
	int newsId=servlet.requestInt(request,"newsId");
	news.delNews(servlet.requestInt(request,"newsId"));
	servlet.responseUrl(response,"newsList.jsp");
%>