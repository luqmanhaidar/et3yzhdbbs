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

<jsp:useBean id="userN" scope="page" class="com.ntsky.news.UserNews"/>

<%
	int newsId=servlet.requestInt(request,"newsId");
	userN.delUserNews(servlet.requestInt(request,"newsId"));
	servlet.responseUrl(response,"personal.jsp");
%>