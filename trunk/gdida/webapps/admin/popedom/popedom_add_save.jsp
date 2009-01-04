<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
String con=request.getParameter("con");
String pop=request.getParameter("pop");
String tid=request.getParameter("tid");

Table.insertRow("popedom","tid,con,pop ",tid+",'"+con+"','"+pop+"'");

response.sendRedirect("popedom_list.jsp");
%>