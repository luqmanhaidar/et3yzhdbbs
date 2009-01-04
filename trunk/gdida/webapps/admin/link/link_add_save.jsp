<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");

String sname = request.getParameter("lname");
String stypeid = request.getParameter("typeid");
String surl = request.getParameter("lurl");

Table.insertRow("j_link", "SLINKTITLE, ITYPEID, SURL",
	"'" + sname + "', " + stypeid + ", '" + surl + "'");

response.sendRedirect("link_list.jsp");
%>