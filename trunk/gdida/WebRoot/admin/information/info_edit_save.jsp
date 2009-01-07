<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Information" scope="page"/>
<%
if (!gdie.edit(pageContext)) {
%>
	<script language="javascript">
		alert("ณ๖ดํมห");
	    window.history.back(1);
	</script>
<%
} else {
	response.sendRedirect("info_list.jsp");
}
%>