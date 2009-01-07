<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
String id = request.getParameter("id");
String con=request.getParameter("con");
String pop=request.getParameter("pop");
String tid=request.getParameter("tid");

Table.execSql("update popedom set con='" + con + "', tid="
	+ tid + ", pop='" + pop + "' where id = " + id);

response.sendRedirect("popedom_list.jsp");
%>

