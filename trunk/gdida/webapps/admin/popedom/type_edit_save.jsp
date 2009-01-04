<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");

String lid = request.getParameter("lid");
String lname = request.getParameter("lname");
String identify=request.getParameter("identify");
	
Table.execSql("update popedomtype set type = '" + lname + "',identify='"+identify+"' where id = " + lid);

response.sendRedirect("type_list.jsp");
%>

