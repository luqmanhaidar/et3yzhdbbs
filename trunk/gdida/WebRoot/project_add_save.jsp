<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page"/>
<%
if (!gdie.addParent(pageContext)) {
%>
	<script language="javascript">
		alert("出错了");

	</script>
<%
} else {
	%>
	<script language="javascript">
		alert("项目发布成功，请等待管理员审核");
window.location="project_list.jsp?identify=PROJECT_CAR";
	</script>
<%
}
%>