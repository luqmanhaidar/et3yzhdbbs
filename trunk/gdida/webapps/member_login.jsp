<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="common/datacheck.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>��Աע��</title>
<link href="style/gdida.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<table width="1002" border="0" cellspacing="0" cellpadding="0"
	align="center">

	<tr>
		<td width="51" align=center height="26" bgcolor="#f7f3ef"><img
			src="images/gdida_sub_30.gif" width="17" height="17" /></td>
		<td width="951" align="left" bgcolor="#f7f3ef">�����ڣ��㶫ʡ��ҵ��չ�о�Ժ��ҳ
		&gt; ��Աע��</td>
	</tr>
<form name="frm1" method="post" action="login.jsp?flag=pw" onSubmit="return Validator.Validate(this, 3);">
  <table border=0 align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">�û���</div>
      </td>
      <td width="483" align="left">
        <input type="text" name="username" size="30" maxlength="30" dataType=Require re="����д�ʺ�">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">���룺</div>
      </td>
      <td width="483" align="left">
        <input type="password" name="password" size="30" maxlength="32" value="" dataType="Password" re="������һ��4��32λ������">
      </td>
    </tr>
   </table>
<p align="center">
  <input type="submit" name="but_submit" value="��½" style="cursor:hand;">
  <input type="button" name="but_reset" value="����" style="cursor:hand;" onclick="window.history.back(1);">
</p>
	<tr>
		<td colspan="2">
		<table width="1002" border="0" cellspacing="0" cellpadding="0"
			align="center">
			<jsp:include page="footer.jsp" flush="true" />
		</table>		</td>
	</tr>
</table>
</form>
</body>
</html>
