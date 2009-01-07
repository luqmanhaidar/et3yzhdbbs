<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>
<form name="form1" action="type_add_save.jsp" method="post" onSubmit="return Validator.Validate(this, 3);">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>权限管理</u>>><u>新增模块</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>

<table align="center">
	<tr>
	<td>权限模块名称：</td><td><input type=text name='lname' dataType="Require" re="名称不能为空!"></td>
	</tr>
	<tr>
	<td>权限模块标识：</td><td><input type=text name='identify' dataType="Require" re="标识不能为空!"></td>
	</tr>
</table>
<p align="center"><input type="submit" value="提交" name="B1">
<input type="button" value="返回" name="B2" onclick='javascript:window.history.go(-1);'>
</form>

</div></body></html>