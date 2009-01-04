<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.util.Popedom" %>
<!-- 
<%


//String uid=(String)session.getAttribute("userid");
//boolean flag=Popedom.validatePopedomType(uid,"LINK_MANAGE");
//if(!flag){	
	%>	
	<script language="javascript">
		alert("你没有该权限");
	    window.history.back(1);
	</script>
	<%	
//}else{
//session.setAttribute("identify","LINK_MANAGE");
//}
%>
 -->
<html>
<head>
<title>项目列表</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script LANGUAGE="JavaScript">
	function openwin(url) {
		window.open (url, "newwindow", "height=320, width=480, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	//转页
	function goPage(pageNo) {
		document.frmlist.pageno.value = pageNo;
		document.frmlist.submit();
	}
</script>
</head>

<%
String projectID = request.getParameter("projectId");
//String project_Parent_Id=projectID;
//String sModuleid=Module.getMidByIdentify(projectId);
String projectTitle=request.getParameter("projectTitle");
String project_mode =request.getParameter("project_mode");
String sCondition = "";
String strSql;
if( projectTitle!= null && !projectTitle.equals("")){
    sCondition = sCondition + " and PROJECTTITLE like '%" + projectTitle + "%'";
}
if( project_mode!= null && !project_mode.equals("")){
    sCondition = sCondition + " and PROJECT_MODE like '%" + project_mode + "%'";
}
//要显示的页数
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("j_project", "PARENTID ="+projectID+ sCondition);

if((projectTitle!= null && !projectTitle.equals(""))||( project_mode!= null && !project_mode.equals("")))
{strSql = " select * from j_project where PARENTID="+projectID+ sCondition+" order by PROJECTID DESC";}
else{strSql = " select * from j_project where PARENTID="+projectID+" order by PROJECTID DESC";}
System.out.println(projectID);
System.out.println(strSql);
//String strSql = " select * from j_project where PARENTID="+projectId+" + sCondition;
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//分页对象
Page objPage = new Page(rowCount,Page.pageViewCount,pageNo);
//总页数
int pageCount = objPage.getPageCount();
//当前页号
pageNo = objPage.getPageNo();
//开始条数
int begin = objPage.getBegin();
//显示条数
int viewCount = objPage.getViewCount();
//滚动记录集
if (begin > 0)
    rs.absolute(begin);
%>

<body>
<form name="frmlist" action="project_detail.jsp?projectId=<%=request.getParameter("projectId")%>" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>项目管理</u>>><u>项目列表</u>>><u>申请详情</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	项目名:<input name="projectTitle" type="text" value="<%=Page.output(projectTitle) %>" size="25">
	项目类别:<input name="project_mode" type="text" value="<%=Page.output(project_mode) %>" size="25">
	<input type="submit" name="but_search" value="查询">
	<input type="reset" name="but_reset" value="重置">
	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;共<%=rowCount%>条记录</td>
    <td width="34%" align="center"></td>
    <td width="41%">
      <div align="right">
          第<input type="text" name="pageno" size="4" class="int" value="<%= pageNo %>">页 <input type="submit" name="but_gopage" value="转到" style="cursor:hand;">
	  	  			<%
	  				     out.println("<a href='javascript:goPage(1)'>第一页</a>");
	  					 out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>上一页</a>");
						 out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>下一页</a>");
        				 out.println("<a href='javascript:goPage(" + (pageCount) + ")'>最后一页</a>");
					%>
	  </div>
    </td>
  </tr>
</table>
<table width="90%" border="0" cellpadding="2" cellspacing="1" bgcolor="#336699" align="center" height="25">
    <tr bgcolor="#FFFFFF">
      <td width="30%" align="center" height="25">投资项目标题</td>
      <td width="20%" align="center" height="25">项目类别</td>
      <td width="20%" align="center" height="25">申请人</td>
      <td width="20%" align="center" height="25">联系电话</td>
      <td width="30%" align="center" height="25">操作</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td height="25"><%=rs.getString("PROJECTTITLE") %></td>
	  <td height="25"><%=rs.getString("PROJECT_MODE") %></td>
	  <td height="25"><%=rs.getString("CONTACTS") %></td>
	  <td height="25"><%=rs.getString("TEL") %></td>
 <td>
      <a href=javascript:if(confirm('确定删除！'))location='info_del.jsp?projectid=<%=rs.getInt("PROJECTID") %>&parent=0&project_Parent_Id=<%=request.getParameter("projectId") %>'>删除</a>
     
      
      <%
	   if(rs.getString("ISOK").equals("N")){
	   
	   %>	   
	   <a href=javascript:if(confirm('确定审批通过！'))location='project_update.jsp?projectid=<%=rs.getInt("PROJECTID") %>&project_Parent_Id=<%=request.getParameter("projectId") %>&pageno=<%=pageNo%>'>审批</a>
		<%
		   }else{ %>
      
      已审批
            
      <%} %>
      </td>
  	</tr>
<%
    }
  }
Table.close(rs);
%>
</table>
</form>
</body>
</html>
<%//if (rs != null) Table.close(rs);我自己觉得不用的 %>