<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%
String id = request.getParameter("lid");
String strSql = "delete from j_link where ILINKID="+id;
if (!Table.execSql2(strSql)) {
%>
	<script language="javascript">
		alert("������");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("link_list.jsp");
}
%>