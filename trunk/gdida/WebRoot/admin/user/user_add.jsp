<%@ page contentType="text/html; charset=GBK" %>
<html>
<head>
<title>增加用户</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script type="text/javascript" src="../../common/datacheck.js"></script>
</head>

<body>
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>用户管理</u>>><u>新增用户</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<form name="frm1" method="post" action="user_add_save.jsp" onSubmit="return Validator.Validate(this, 3);">
  <table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">帐号：</div>
      </td>
      <td width="483">
        <input type="text" name="username" size="30" maxlength="30" dataType=Require re="请填写帐号">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">密码：</div>
      </td>
      <td width="483">
        <input type="password" name="userpassword" size="30" maxlength="32" value="" dataType="Password" re="请输入一个4～32位的密码">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">确认密码：</div>
      </td>
      <td width="483">
        <input type="password" name="userpasswordagain" size="30" maxlength="32" value="" dataType="Repeat" to="userpassword" re="两次输入的密码不一致">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">姓名：</div>
      </td>
      <td width="483">
        <input type="text" name="realname" size="30" maxlength="30" dataType=Require re="请填写姓名">
      </td>
    </tr>
  </table>
<p align="center">
  <input type="submit" name="but_submit" value="确定" style="cursor:hand;">
  <input type="reset" name="but_reset" value="重置" style="cursor:hand;">
</p>
</form>
</body>

</html>
