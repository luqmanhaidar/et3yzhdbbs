<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import= "java.sql.ResultSet"%>
<%
String lid = request.getParameter("lid");
ResultSet rs = null;

if (lid != null && !lid.trim().equals("")) {
	rs = Table.getRecordsBySql("select * from popedomtype where id="+ lid);
}
if (!rs.next()) {
	throw new Exception("�Ҳ�����¼");
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
<td><u>Ȩ�޹���</u>>><u>�޸�ģ��</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>

<table align="center">
	<tr>
	<td>Ȩ��ģ�����ƣ�</td><td><input type=text name='lname' value='<%=rs.getString("type") %>' dataType="Require" re="���Ʋ���Ϊ��!"></td>
	</tr>
	<tr>
	<td>Ȩ��ģ���ʶ��</td><td><input type=text name='identify' value='<%=rs.getString("identify") %>' dataType="Require" re="��ʶ����Ϊ��!"></td>
	</tr>
</table>

<p align="center"><input type="submit" value="�ύ" name="B1">
<input type="button" value="����" name="B2" onclick='javascript:window.history.go(-1);'>
</form>

</div></body></html>
<% if (rs != null) Table.close(rs); %>