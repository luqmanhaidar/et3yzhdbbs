<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.util.Popedom" %>
<%
String uid=(String)session.getAttribute("userid");
boolean flag=Popedom.validatePopedomType(uid,"MODULE_MANAGE");
if(!flag){	
	%>	
	<script language="javascript">
		alert("你没有该权限");
	    window.history.back(1);
	</script>
	<%	
}else{
session.setAttribute("identify","MODULE_MANAGE");
}
%>
<html>
<head>
<title>栏目列表</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
</head>

<body>
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>栏目管理</u>>><u>栏目列表</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
<tr height=10>
<td><input type=button value="增加栏目" onClick="location='module_add.jsp'"></td>
</tr>
</table>
<%@ include file='module.jsp' %>
</body>
</html>