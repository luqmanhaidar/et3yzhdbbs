<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page"/>
<%
if (!gdie.addChildren(pageContext)) {
%>
	<script language="javascript">
		alert("出错了");

	</script>
<%
} else {
	%>
	<script language="javascript">
		alert("投资成功");

	</script>
<%
	response.sendRedirect("project_list.jsp?identify=PROJECT_CAR");
}
%>