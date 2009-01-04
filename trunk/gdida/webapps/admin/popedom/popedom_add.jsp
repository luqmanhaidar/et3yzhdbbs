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
<form name="form1" action="popedom_add_save.jsp" method="post" onSubmit="return Validator.Validate(this, 3);">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>权限管理</u>>><u>新增权限</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1" height="150">
	<tr>
		<td width="115" align="center" height="37">权限描述：</td>
		<td width="476" height="37">
		<input type=text name='con' size=30></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">权限标识：</td>
		<td width="476" height="37">
		<input type=text name='pop' size=30 dataType="Require" re="标识不能为空"></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">所属分类：</td>
		<td width="476" height="37">
		<select name='tid' dataType="SelectBox" re="请选择分类">
			<option value=''>(请选择)</option>
	    <%
	    String sql = "select * from popedomtype order by id asc";
        ResultSet rs = Table.getRecordsBySql(sql);
        while (rs.next()) {
	    %>
	    	<option value='<%=rs.getInt("id") %>'><%=rs.getString("type") %></option>
	    <%}
	    Table.close(rs);%>
	    </select></td>
	</tr>
	
	<tr align=center><td colspan=2><input type="submit" value="提交" name="B1">
		 <input type="button" value="返回" name="B2" onclick='javascript:window.history.go(-1);'>
	</td></tr>
</table></form>

</div></body></html>