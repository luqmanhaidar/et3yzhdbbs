<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%
request.setCharacterEncoding("GBK");

String lid = request.getParameter("lid");
ResultSet rs = null;

if (lid != null && !lid.trim().equals("")) {
	rs = Table.getRecordsBySql("select * from j_link where ILINKID="+ lid);
}
if (!rs.next()) {
	throw new Exception("�Ҳ�����¼");
}
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>
<form name="form1" action="link_edit_save.jsp?lid=<%=lid %>" method="post" onSubmit="return Validator.Validate(this, 3);">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>�������</u>>><u>�޸�����</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1" height="150">
	<tr>
		<td width="115" align="center" height="37">�������ƣ�</td>
		<td width="476" height="37">
		<input type=text name='lname' size=30 value="<%=rs.getString("SLINKTITLE") %>" dataType="Require" re="���Ʋ���Ϊ��"></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">�������ࣺ</td>
		<td width="476" height="37">
		<select name='typeid' dataType="SelectBox" re="��ѡ�����">
			<option value=''>(��ѡ��)</option>
	    <%
	    String sql = "select * from j_linktype order by ITYPEID asc";
        ResultSet rs1 = Table.getRecordsBySql(sql);
        while (rs1.next()) {
	    %>
	    	<option value='<%=rs1.getInt("ITYPEID") %>' <%=rs.getInt("ITYPEID")==rs1.getInt("ITYPEID")?"selected":"" %>><%=rs1.getString("STYPENAME") %></option>
	    <%}
	    Table.close(rs1);%>
	    </select></td>
	</tr>
	<tr>
		<td width="115" align="center" height="37">���ӵ�ַ��</td>
		<td width="476" height="37">
		<input type=text name='lurl' size=30 value="<%=rs.getString("SURL") %>" dataType="Require" re="��ַ����Ϊ��"></td>
	</tr>
	<tr align=center><td colspan=2><input type="submit" value="�ύ" name="B1">
		 <input type="button" value="����" name="B2" onclick='javascript:window.history.go(-1);'>
	</td></tr>
</table></form>

</div></body></html>
<% if (rs != null) Table.close(rs); %>