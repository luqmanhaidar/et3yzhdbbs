<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
String id = request.getParameter("userid");
String strSql = "delete from j_user where IUSERID="+id;
if (!Table.execSql2(strSql)) {
%>
	<script language="javascript">
		alert("ณ๖ดํมห");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("user_list.jsp");
}
%>