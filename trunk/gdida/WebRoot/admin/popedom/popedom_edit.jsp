<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%
String id = request.getParameter("lid");
ResultSet rs = null;

if (id != null && !id.trim().equals("")) {
	rs = Table.getRecordsBySql("select * from popedom where id="+ id);
}
if (!rs.next()) {
	throw new Exception("找不到记录");
}
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>
<form name="form1" action="popedom_edit_save.jsp?id=<%=id %>" method="post" onSubmit="return Validator.Validate(this, 3);">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>权限管理</u>>><u>修改权限</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1" height="150">
	<tr>
		<td width="115" align="center" height="37">权限描述：</td>
		<td width="476" height="37">
		<input type=text name='con' size=30 value="<%=rs.getString("con") %>"></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">权限标识：</td>
		<td width="476" height="37">
		<input type=text name='pop' size=30 value="<%=rs.getString("pop") %>" dataType="Require" re="标识不能为空"></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">所属分类：</td>
		<td width="476" height="37">
		<select name='tid' dataType="SelectBox" re="请选择分类">
			<option value=''>(请选择)</option>
	    <%
	    String sql = "select * from popedomtype order by id asc";
        ResultSet rs1 = Table.getRecordsBySql(sql);
        while (rs1.next()) {
	    %>
	    	<option value='<%=rs1.getInt("id") %>' <%=rs.getInt("tid")==rs1.getInt("id")?"selected":"" %>><%=rs1.getString("type") %></option>
	    <%}
	    Table.close(rs1);%>
	    </select></td>
	</tr>
	
	<tr align=center><td colspan=2><input type="submit" value="提交" name="B1">
		 <input type="button" value="返回" name="B2" onclick='javascript:window.history.go(-1);'>
	</td></tr>
</table></form>

</div></body></html>
<% if (rs != null) Table.close(rs); %>