<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page"/>

<%

int tag=Integer.parseInt(request.getParameter("parent"));
System.out.println(tag);
if(tag==0)
{
	if (!gdie.deleteProject(request.getParameter("projectid"))) 
	{
%>
	<script language="javascript">
		alert("出错了");
	    window.history.back(1);
	</script>
<%
	}
	else
	{
%>
	<script language="javascript">
		alert("操作成功！请刷新查询1！");
    	window.history.back(1);
	</script>
<%
	response.sendRedirect("project_detail.jsp?projectId="+request.getParameter("project_Parent_Id"));
	}
}
else if(tag==1)
{
	if (!gdie.deleteProject_all(request.getParameter("projectid"))) 
	{
%>
	<script language="javascript">
		alert("出错了");
	    window.history.back(1);
	</script>
<%
	}
	else
	{
%>
	<script language="javascript">
		alert("操作成功！请刷新查询2！");
    	window.history.back(1);
	</script>
<%
	response.sendRedirect("project_list.jsp");
	}
}
%>