<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");

String sname = request.getParameter("lname");
	
Table.insertRow("j_linktype", "STYPENAME", "'" + sname + "'");

response.sendRedirect("type_list.jsp");
%>