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
<jsp:useBean id="servlet" scope="page" class="com.ntsky.servlet.DOServlet"/>
<%
	String user=(String)session.getAttribute("sessionuser");
	if(user==null)
	servlet.responseUrl(response,"../error/noteerror.jsp");
%>