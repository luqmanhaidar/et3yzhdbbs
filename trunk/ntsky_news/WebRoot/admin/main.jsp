<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ntsky.persistence.*" %>
<jsp:useBean id="isIn" class="com.ntsky.news.manage.ISLogin"/>

<%@ include file="check.jsp"%>
<html>
<head>
<title>main</title>
<STYLE type=text/css>
BODY { BACKGROUND: #799ae1; FONT: 9pt ����; MARGIN: 0px }
TABLE { BORDER-BOTTOM: 0px; BORDER-LEFT: 0px; BORDER-RIGHT: 0px; BORDER-TOP: 0px }
TD { FONT: 12px ���� }
</style>
<base target="_self">
</head>

<body bgcolor="#F2F4F9">
<p>&nbsp;</p>
<table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr> 
    <td width="0" height="55" align="left" valign="middle"> <p><font color="red"><%=adminName%> 
        </font> ����ӭ������NTsky�������̨�� </p>
      <%
			/**
			Iterator rs = isIn.timeIp(adminName);
			while(rs.hasNext()){
				NEWSAdmin tableAdmin = (NEWSAdmin)rs.next();
			*/
		%>
    </td>
  </tr>
  <tr> 
    <td height="32" align="left" valign="middle">���ϴεĵ�½ʱ����:<font color="red"><%=isIn.strTime(adminName)%> 
      <%//=tableAdmin.getLastLogin()%>
      </font></td>
  </tr>
  <tr> 
    <td height="58" align="left" valign="middle">���ϴεĵ�½IP��:<font color="red"><%=isIn.strIp(adminName)%> 
      <%//=tableAdmin.getLastLoginIp()%>
      </font></td>
  </tr>
  <tr> 
    <td width="0" height="36" align="left" valign="middle"> <p><font color="#FFFFFF">&nbsp;&nbsp; 
        ��ϵͳ����JSP+JAVABEAN+MYSQL��Ŀǰ�İ汾Ϊ V1.0 �ȶ���</font></p>
      <p><font color="#FFFFFF">&nbsp; �������ʹ�ô�ϵͳ��ʱ������ʲô�����뵽 <font color="#000000"><a href="http://note.ntsky.com">note.ntsky.com</a></font>��������</font></p>
      <p><font color="#FFFFFF">���Ǹ��ҷ�E-mail �ҵ�email��<a href="redhatserver@tom.com">redhatserver@tom.com</a> 
        QQ��4932590��38998186ϣ������ܹ��������������</font></p></td>
  </tr>
</table>
<p><font color="#FFFFFF"></font> </p>
</body>

</html>