<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Information" scope="page"/>
<%
	String iid=request.getParameter("iid");
	String mid=request.getParameter("mid");
	gdie.findRecord(Integer.parseInt(iid));
	
	gdie.setIModuleId(Integer.parseInt(mid));
	gdie.add();
	response.sendRedirect("info_list.jsp");
%>