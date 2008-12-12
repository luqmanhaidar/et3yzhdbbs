<%
/**
 * <p>Title: NTsky新闻发布v1.0正式版</p>
 * <p>Description: Session的清除</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory 姚君林
 * @version 1.0
 */
%>
<%@ page import = "com.ntsky.servlet.*" %>
<%@ include file="check.jsp" %>

<%
	SessionManager.removeSession(session,"user");
	DOServlet.responseUrl(response,"index.jsp");
%>