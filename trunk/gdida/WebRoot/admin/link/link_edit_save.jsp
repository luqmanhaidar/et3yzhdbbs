<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
request.setCharacterEncoding("GBK");

String lid = request.getParameter("lid");
String sname = request.getParameter("lname");
String stypeid = request.getParameter("typeid");
String surl = request.getParameter("lurl");
	
Table.execSql("update j_link set SLINKTITLE='" + sname + "', ITYPEID="
	+ stypeid + ", SURL='" + surl + "' where ILINKID = " + lid);

response.sendRedirect("link_list.jsp");
%>

