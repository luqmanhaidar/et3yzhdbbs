<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page"/>
<%
if (!gdie.addChildren(pageContext)) {
%>
	<script language="javascript">
		alert("������");

	</script>
<%
} else {
	%>
	<script language="javascript">
		alert("Ͷ�ʳɹ�");

	</script>
<%
	response.sendRedirect("project_list.jsp?identify=PROJECT_CAR");
}
%>