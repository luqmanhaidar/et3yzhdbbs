
<%@ page import = "com.ntsky.servlet.*"%>
<jsp:useBean id="SessionManager" class="com.ntsky.servlet.SessionManager"/>
<jsp:useBean id="servlet" scope="page" class="com.ntsky.servlet.DOServlet"/>
<%
	String user=SessionManager.getSession(session,"sessionuser");
	if(user==null)
	servlet.responseUrl(response,"../error/personalerror.jsp");
%>