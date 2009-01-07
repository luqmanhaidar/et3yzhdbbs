<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%request.setCharacterEncoding("GBK"); %>

<%
	String qid = request.getParameter("qid");
	String sql="delete from J_ITEMS where qid = "+qid;
	Table.execSql(sql);
	sql="delete from J_QUESTIONS where qid = "+qid;
	Table.execSql(sql);
	response.sendRedirect("vote_list.jsp");
%>