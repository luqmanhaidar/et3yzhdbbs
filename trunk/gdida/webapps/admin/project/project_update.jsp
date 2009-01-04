<%@ page contentType="text/html;charset=GBK" %>
<jsp:useBean id="gdie" class="com.gdie.gdjrb.Project" scope="page"/>
<%
if (!gdie.updateProject(request.getParameter("projectid"))) {
%>
	<script language="javascript">
		alert("ณ๖ดํมห");
	    window.history.back(1);
	</script>
<%
} else
	{
		if(gdie.selectProject(request.getParameter("projectid")))
		{	//System.out.println(gdie.selectProject(request.getParameter("projectid")));
			//System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDd"+request.getParameter("project_Parent_Id"));
			response.sendRedirect("project_detail.jsp?projectId="+request.getParameter("project_Parent_Id"));
	
		}
		else{
			response.sendRedirect("project_list.jsp?pageno="+request.getParameter("pageno"));
		}
}
%>