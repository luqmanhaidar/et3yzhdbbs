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
<td><u>���ӹ���</u>>><u>��������</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<p align=center>�������ƣ�<input type=text name='lname' dataType="Require" re="���Ʋ���Ϊ��!">
<p align="center"><input type="submit" value="�ύ" name="B1">
<input type="button" value="����" name="B2" onclick='javascript:window.history.go(-1);'>
</form>

</div></body></html>