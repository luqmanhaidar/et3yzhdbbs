<%@ page contentType="text/html; charset=GBK" %>
<%@ page import='com.gdie.db.Table' %>
<%@ page import='com.gdie.gdjrb.Module' %>
<%
String sModuleid = request.getParameter("mid");
%>
<html>
<head>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script language="javascript" src="../../common/datacheck.js"></script>
<script LANGUAGE="JavaScript">
function getParentId() {
	with(document.all) {
		var vReturnValue = window.showModalDialog("module.jsp?master=0");
		if(vReturnValue!=null) {
			vReturnValues=vReturnValue.split(",");
			parentName.value=vReturnValues[1];
			parentId.value=vReturnValues[0];
		}
	}
}
</script>
<title>�༭��Ŀ</title>
</head>

<body>
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>��Ŀ����</u>>><u>�༭��Ŀ</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<form name="frm1" method="post" action="module_edit_save.jsp" onSubmit="return Validator.Validate(this, 3);">
  <table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">��Ŀ���ƣ�</div>
      </td>
      <td width="483">
        <input type="text" name="moduleName" size="30" maxlength="50" dataType=Require re="����д��Ŀ����"
         value="<%=Table.getValue("j_module","SMODULENAME","IMODULEID="+sModuleid,"") %>">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">������Ŀ��</div>
      </td>
      <td width="483">
      <%String sParentid = Table.getValue("j_module","IPARENTID","IMODULEID="+sModuleid,""); %>
		<input type="hidden" name="parentId" value="<%=sParentid %>">
        <input type="text" name="parentName" size="30" maxlength="50" readonly
         value="<%=sParentid.equals("")?"":Table.getValue("j_module","SMODULENAME","IMODULEID="+sParentid,"") %>">
		<input type="button" value="ѡ��" onclick="javascript: getParentId()"/>
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">˳&nbsp;&nbsp;&nbsp;&nbsp;��</div>
      </td>
      <td width="483">
        <input type="text" name="order" size="30" maxlength="30"
         value="<%=Table.getValue("j_module","IORDER","IMODULEID="+sModuleid,"") %>">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
    <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;ʶ��</td>
    <td>
    	<input name="identify" size="30" maxlength="30"
    	 type="text" value="<%=Table.getValue("j_module","IDENTIFY","IMODULEID="+sModuleid,"") %>">
    </td>
    </tr>
  </table>
<p align="center">
  <input type="hidden" name="moduleId" value="<%=sModuleid %>">
  <input type="submit" name="but_submit" value="ȷ��" style="cursor:hand;">
  <input type="reset" name="but_reset" value="����" style="cursor:hand;">
</p>
</form>
</body>

</html>
