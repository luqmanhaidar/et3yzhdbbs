<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>
<form name="form1" action="power.jsp" method="post">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>Ȩ�޹���</u>>><u>����Ȩ��</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1" height="150">
	<tr>
		<td width="115" align="center" height="37">Ȩ��������</td>
		<td width="476" height="37">
		<input type=text name='con1' size=30>
		<input type=text name='con2' size=30>
		<input type=text name='con3' size=30>
		<input type=text name='con4' size=30></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">Ȩ�ޱ�ʶ��</td>
		<td width="476" height="37">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">�������ࣺ</td>
		<td width="476" height="37">
		</td>
	</tr>
	
	<tr align=center><td colspan=2><input type="submit" value="�ύ" name="B1">
		 <input type="button" value="����" name="B2" onclick='javascript:window.history.go(-1);'>
	</td></tr>
</table></form>

</div></body></html>