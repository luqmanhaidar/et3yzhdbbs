<%
/**
 * <p>Title: NTsky���ŷ���v1.0��ʽ��</p>
 * <p>Description: Session�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: NTsky</p>
 * @authory Ҧ����
 * @version 1.0
 */
%>
<%@ page import = "com.ntsky.servlet.*" %>
<%@ include file="check.jsp" %>

<%
	SessionManager.removeSession(session,"user");
	DOServlet.responseUrl(response,"index.jsp");
%>