<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page"/>
<%
if (!gdie.addParent(pageContext)) {
%>
	<script language="javascript">
		alert("������");

	</script>
<%
} else {
	%>
	<script language="javascript">
		alert("��Ŀ�����ɹ�����ȴ�����Ա���");
window.location="project_list.jsp?identify=PROJECT_CAR";
	</script>
<%
}
%>