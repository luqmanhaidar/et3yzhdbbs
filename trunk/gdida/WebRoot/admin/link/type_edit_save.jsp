<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");

String lid = request.getParameter("lid");
String lname = request.getParameter("lname");
	
Table.execSql("update j_linktype set STYPENAME = '" + lname + "' where ITYPEID = " + lid);

response.sendRedirect("type_list.jsp");
%>

