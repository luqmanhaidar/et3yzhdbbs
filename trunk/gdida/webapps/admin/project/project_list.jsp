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
		alert("��û�и�Ȩ��");
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
<title>��Ŀ�б�</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script LANGUAGE="JavaScript">
	function openwin(url) {
		window.open (url, "newwindow", "height=320, width=480, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	//תҳ
	function goPage(pageNo) {
		document.frmlist.pageno.value = pageNo;
		document.frmlist.submit();
	}
</script>
</head>

<%

String projectTitle=request.getParameter("projectTitle");
String project_mode =request.getParameter("project_mode");
String sCondition = "";
if( projectTitle!= null && !projectTitle.equals("")){
    sCondition = sCondition + " and PROJECTTITLE like '%" + projectTitle + "%'";
}
if( project_mode!= null && !project_mode.equals("")){
    sCondition = sCondition + " and PROJECT_MODE like '%" + project_mode + "%'";
}
//Ҫ��ʾ��ҳ��
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("j_project", "PARENTID IS NULL" + sCondition);
String strSql = "SELECT *,(SELECT b.SUSERNAME FROM j_user b where b.IUSERID=a.IPUBUSERID) as IPUBUSERNAME  FROM j_project a WHERE PARENTID IS NULL " + sCondition +" order by PROJECTID DESC";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//��ҳ����
Page objPage = new Page(rowCount,Page.pageViewCount,pageNo);
//��ҳ��
int pageCount = objPage.getPageCount();
//��ǰҳ��
pageNo = objPage.getPageNo();
//��ʼ����
int begin = objPage.getBegin();
//��ʾ����
int viewCount = objPage.getViewCount();
//������¼��
if (begin > 0)
    rs.absolute(begin);
%>

<body>
<form name="frmlist" action="project_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>��Ŀ����</u>>><u>��Ŀ�б�</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	��Ŀ��:<input name="projectTitle" type="text" value="<%=Page.output(projectTitle) %>" size="25">
	��Ŀ���:<input name="project_mode" type="text" value="<%=Page.output(project_mode) %>" size="25">
	<input type="submit" name="but_search" value="��ѯ">
	<input type="reset" name="but_reset" value="����">
	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;��<%=rowCount%>����¼</td>
    <td width="34%" align="center"></td>
    <td width="41%">
      <div align="right">
          ��<input type="text" name="pageno" size="4" class="int" value="<%= pageNo %>">ҳ <input type="submit" name="but_gopage" value="ת��" style="cursor:hand;">
	  	  			<%
	  				     out.println("<a href='javascript:goPage(1)'>��һҳ</a>");
	  					 out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>��һҳ</a>");
						 out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>��һҳ</a>");
        				 out.println("<a href='javascript:goPage(" + (pageCount) + ")'>���һҳ</a>");
					%>
	  </div>
    </td>
  </tr>
</table>
<table width="90%" border="0" cellpadding="2" cellspacing="1" bgcolor="#336699" align="center" height="25">
    <tr bgcolor="#FFFFFF">
      <td width="40%" align="center" height="25">��Ŀ��</td>
      <td width="30%" align="center" height="25">��Ŀ���</td>
      <td width="10%" align="center" height="25">������</td>
      <td width="20%" align="center" height="25">����</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td height="25"><p align="left"><a target="_blank" href="../../project_detail.jsp?projectId=<%=rs.getInt("PROJECTID") %>"><%=rs.getString("PROJECTTITLE") %></a></td>
	  <td height="25"><%=rs.getString("PROJECT_MODE") %></td>
	  <td height="25"><%=rs.getString("IPUBUSERNAME") %></td>
 <td>
      <a href=javascript:if(confirm('ȷ��ɾ����'))location='info_del.jsp?projectid=<%=rs.getInt("PROJECTID") %>&parent=1'>ɾ��</a>
     
      
      <%
	   if(rs.getString("ISOK").equals("N")){
	   
	   %>	   
	   <a href=javascript:if(confirm('ȷ������ͨ����'))location='project_update.jsp?projectid=<%=rs.getInt("PROJECTID") %>&pageno=<%=pageNo%>'>����</a>
		<%
		   }else{ %>
      
      ������
            
      <%} %>
      <a href="project_detail.jsp?projectId=<%=rs.getInt("PROJECTID")%>">�鿴����</a>
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
<%//if (rs != null) Table.close(rs); %>