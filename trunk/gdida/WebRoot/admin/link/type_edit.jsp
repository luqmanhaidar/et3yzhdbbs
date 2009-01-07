<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import= "java.sql.ResultSet"%>
<%
String lid = request.getParameter("lid");
ResultSet rs = null;

if (lid != null && !lid.trim().equals("")) {
	rs = Table.getRecordsBySql("select * from j_linktype where ITYPEID="+ lid);
}
if (!rs.next()) {
	throw new Exception("找不到记录");
}
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>
<form name="form1" action="type_edit_save.jsp?lid=<%=lid %>" method="post" onSubmit="return Validator.Validate(this, 3);">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>链接管理</u>>><u>修改分类</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<p align=center>分类名称：<input type=text value='<%=rs.getString("STYPENAME") %>' name='lname' dataType="Require" re="名称不能为空!">
<p align="center"><input type="submit" value="提交" name="B1">
<input type="button" value="返回" name="B2" onclick='javascript:window.history.go(-1);'>
</form>

</div></body></html>
<% if (rs != null) Table.close(rs); %>