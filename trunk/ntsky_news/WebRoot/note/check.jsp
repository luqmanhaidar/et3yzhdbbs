<%
/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: Session�Ŀ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
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