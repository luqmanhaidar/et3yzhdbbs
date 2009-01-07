<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.gdjrb.Module"%>
<%
request.setCharacterEncoding("GBK");
String mid = request.getParameter("mid");

if (Module.hasSon(Integer.parseInt(mid))) {
	response.sendRedirect("module_list.jsp?mid="+mid);
} else {
	response.sendRedirect("info_list.jsp?mid="+mid);
}
%>