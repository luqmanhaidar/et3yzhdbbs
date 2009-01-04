<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
String id = request.getParameter("mid");
String strSql = "delete from j_module where IMODULEID="+id;
if (!Table.execSql2(strSql)) {
%>
	<script language="javascript">
		alert("ณ๖ดํมห");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("module_list.jsp");
}
%>
