<%@ page contentType="text/html; charset=GBK" %>
<%@ include file="check.jsp"%>

<%
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: 删除用户留言</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
%>

<jsp:useBean id="manager" scope="page" class="com.ntsky.news.note.Manager"/>
	
<%	
	int noteId=servlet.requestInt(request,"noteId");
	manager.delNote(noteId);
	DOServlet.responseUrl(response,"default.jsp");
%>