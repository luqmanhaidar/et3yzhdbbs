<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>�����û�</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script type="text/javascript" src="../../common/datacheck.js"></script>
</head>

<body>
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>�û�����</u>>><u>�����û�</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<form name="frm1" method="post" action="user_add_save.jsp" onSubmit="return Validator.Validate(this, 3);">
  <table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">�ʺţ�</div>
      </td>
      <td width="483">
        <input type="text" name="username" size="30" maxlength="30" dataType=Require re="����д�ʺ�">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">���룺</div>
      </td>
      <td width="483">
        <input type="password" name="userpassword" size="30" maxlength="32" value="" dataType="Password" re="������һ��4��32λ������">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">ȷ�����룺</div>
      </td>
      <td width="483">
        <input type="password" name="userpasswordagain" size="30" maxlength="32" value="" dataType="Repeat" to="userpassword" re="������������벻һ��">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">������</div>
      </td>
      <td width="483">
        <input type="text" name="realname" size="30" maxlength="30" dataType=Require re="����д����">
      </td>
    </tr>
  </table>
<p align="center">
  <input type="submit" name="but_submit" value="ȷ��" style="cursor:hand;">
  <input type="reset" name="but_reset" value="����" style="cursor:hand;">
</p>
</form>
</body>

</html>
