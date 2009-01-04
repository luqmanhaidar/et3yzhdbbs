<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");

String sname = request.getParameter("lname");
String identify=request.getParameter("identify");
	
Table.insertRow("popedomtype", "type,identify", "'" + sname + "','"+identify+"'");

response.sendRedirect("type_list.jsp");
%>