<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ntsky.servlet.*" %>
<%@ page import="com.ntsky.news.manage.*"%>
<%@ page import="com.ntsky.persistence.*"%>
<%@ include file="check.jsp" %>

<jsp:useBean id="Column" scope="page" class="com.ntsky.news.manage.Column"/>
<jsp:useBean id="Kind" scope="page" class="com.ntsky.news.manage.Kind"/>
<jsp:useBean id="newsKind" scope="page" class="com.ntsky.persistence.NEWSKind"/>
<jsp:setProperty name="newsKind" scope="page" property="*"/>

<%
	/**
     * �������
     */
   	//��ȡkindId��ֵ
	int kindIdII = servlet.requestInt(request,"kindId");
	//ȡ��action��ֵ
	String action=servlet.requestStr(request,"submit");
	//�жϲ���
	if(action.equals("true")){
		//ִ�в���
		Kind.upKind(newsKind.getClassId(),newsKind.getContent(),kindIdII);
		servlet.responseUrl(response,"kind.jsp");
	}
%>
<html>
<head>
<title>�޸����</title>
<link rel="stylesheet" href="../../css/admin.css" type="text/css">
<script language="JavaScript" src="../../js/admin/column/editkind.js"></script>
</head>

<body>
<p>&nbsp;</p>
<p>&nbsp;</p>

<table width="595" height="7%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <form action="editkind.jsp?submit=true" method="post" name="editkind" onSubmit = "return check();">
  <tr>
      <td height="39" align="center">���������������� :&nbsp;
	  <%
	  	//��ȡkindId��ֵ
		int kindId = servlet.requestInt(request,"kindId");
		//��ľ����ֵ
		Iterator kind = Kind.editKind(kindId);
		while(kind.hasNext()){
			NEWSKind tableKind = (NEWSKind)kind.next();
	  %>
	  <select name="classId">
	  <%
		//��Ŀ��ֵ
		Iterator column = Column.getColumn();
		while(column.hasNext()){
			NEWSClass tableClass = (NEWSClass)column.next();		
      %>
		  <option value="<%=tableClass.getClassId()%>"<%if(tableClass.getClassId()==tableKind.getClassId()){out.print(" selected");}//ȡ�������޸ĵ���Ŀ%>><%=tableClass.getContent()%></option>
	 <%
		}
	 %>
        </select>
	   &nbsp;
        <input name="content" type="text" value="<%=tableKind.getContent()%>" size="20">
        &nbsp;&nbsp;
      <input type="hidden" name="kindId" value=<%=kindId%>>
	  <input name="button" type="submit" id="button" value="����"></td>
	  <%}%>
  </tr>
  </form>
</table>
<p>&nbsp;</p>
</body>
</html>