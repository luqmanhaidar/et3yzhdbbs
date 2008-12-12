<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*" %>
<%@ include file="check.jsp" %>

<%
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 删除指定新闻</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
%>

<jsp:useBean id="news" scope="page" class="com.ntsky.news.manage.News"/>

<%
	news.delNewsReply(servlet.requestInt(request,"replyId"));
	servlet.responseUrl(response,"newstalk.jsp");
%>