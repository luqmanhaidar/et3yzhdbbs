<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
String id = request.getParameter("lid");
String strSql = "delete from popedom where id="+id;
if (!Table.execSql2(strSql)) {
%>
	<script language="javascript">
		alert("ณ๖ดํมห");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("popedom_list.jsp");
}
%>