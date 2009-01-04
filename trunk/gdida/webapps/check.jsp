<%@ page language="java" contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table" %>
<%@ page import="com.gdie.gdjrb.User"%>
<%
	request.setCharacterEncoding("gbk");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	if(User.verify(username, password)) {
		session.setAttribute("USERID",
			Table.getValue("j_user","IUSERID","SUSERNAME='"+username+"'",""));
		session.setAttribute("SPOWER",
			Table.getValue("j_user","SPOWER","SUSERNAME='"+username+"'",""));
		session.setAttribute("USERNAME", username);
		response.sendRedirect("index.jsp");
	} else {
		response.setCharacterEncoding("gbk");
		out.println("<script>alert('µÇÂ¼Ê§°Ü');location.href='index.jsp';</script>");
	}

%>