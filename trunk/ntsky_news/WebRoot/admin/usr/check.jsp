<%
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: Session的控制</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
%>
<%@ page import = "com.ntsky.servlet.*"%>
<jsp:useBean id="sessionManager" class="com.ntsky.servlet.SessionManager"/>
<jsp:useBean id="servlet" scope="page" class="com.ntsky.servlet.DOServlet"/>

<%
	String adminName=sessionManager.getSession(session,"user");
	if(adminName==null)
	servlet.responseUrl(response,"../../error/error.jsp");
%>