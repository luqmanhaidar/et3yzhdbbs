<%@ page contentType="text/html; charset=GBK" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import= "java.sql.ResultSet"%>
<%@ page import= "com.gdie.util.Popedom"%>
<%
String suserid = request.getParameter("userid");
ResultSet rs = null;

if (suserid != null && !suserid.trim().equals("")) {
	rs = Table.getRecordsBySql("select * from j_user where IUSERID="+ suserid);
}
if (!rs.next()) {
	throw new Exception("�Ҳ�����¼");
}

String self = request.getParameter("self");
%>
<html>
<head>
<title>�޸��û�����</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script type="text/javascript" src="../../common/datacheck.js"></script>
</head>

<body>
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u><%=self==null?"�û�����":"���˹���" %></u>>><u>�༭�û�����</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<form name="frm1" method="post" action="user_edit_save.jsp" onSubmit="return Validator.Validate(this, 3);">
  <table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">�ʺţ�</div>
      </td>
      <td width="483">
        <input type="text" name="username" size="30" maxlength="30" <%=self==null?"":"readonly" %> value="<%=rs.getString("SUSERNAME") %>" dataType=Require re="����д�ʺ�">
      </td>
    </tr>
    <tr bgcolor="#FFFFFF" height=28>
      <td width="301">
        <div align="right">�����룺</div>
      </td>
      <td width="483">
        <input type="password" name="userpassword" size="30" maxlength="32" value="" dataType="Password" require=false re="������һ��4��32λ������">
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
        <input type="text" name="realname" size="30" maxlength="30" value="<%=rs.getString("SNAME") %>" dataType=Require re="����д����">
      </td>
    </tr>
	<tr bgcolor="#FFFFFF" height=28>
      <td width="301" align="right" valign="top">
        Ȩ�ޣ�
      </td>
      <td width="483">
        <%
        	String sqlPT="select * from popedomtype";
        	ResultSet rsPT=Table.getRecordsBySql(sqlPT);
        	while(rsPT.next()){
        %>
        <table width="100%" border=0  align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
        	<tr bgcolor="#C9FCCB">
        		<td colspan="2"><%=rsPT.getString("type") %></td>
        	</tr>
        	<%
        		String sqlP="select * from popedom where tid="+rsPT.getInt("id");
        		ResultSet rsP=Table.getRecordsBySql(sqlP);
        		while(rsP.next()){
        	%>
        	<tr bgcolor="#FFFFFF">
        		<td><%=rsP.getString("con") %></td>
        		<td width="5%">
        			<input type="checkbox" name="popedom" value="<%=rsP.getInt("id") %>" <%=(Popedom.validate(suserid,rsP.getInt("id")+"")?"checked":"") %>>
        		</td>
        	</tr>
        	<%}Table.close(rsP); %>
        </table>
        <%} Table.close(rsPT); %>
      </td>
    </tr>
  </table>
<p align="center"><font color=blue>�����������޸ģ������ÿա�</font></p>
<p align="center">
  <input type="hidden" name="userid" value="<%=suserid %>">
  <input type="hidden" name="self" value="<%=self %>">
  <input type="submit" name="but_submit" value="ȷ��">
  <input type="reset" name="but_reset" value="����">
</p>
</form>
</body>

</html>
<% if (rs != null) Table.close(rs); %>
