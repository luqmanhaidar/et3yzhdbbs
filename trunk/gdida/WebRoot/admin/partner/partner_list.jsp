<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.sql.ResultSet"%>

<%request.setCharacterEncoding("GBK"); %>

<html>
<head>
<title>��������б�</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script LANGUAGE="JavaScript">
	function openwin(url) {
		window.open (url, "newwindow", "height=320, width=480, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	//תҳ
	function goPage(pageNo) {
		document.frmlist.pageno.value = pageNo;
		document.frmlist.submit();
	}
</script>
</head>

<%

//Ҫ��ʾ��ҳ��
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("jview_information", "1=1 ");
String strSql = "SELECT * FROM j_partners";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//��ҳ����
Page objPage = new Page(rowCount,Page.pageViewCount,pageNo);
//��ҳ��
int pageCount = objPage.getPageCount();
//��ǰҳ��
pageNo = objPage.getPageNo();
//��ʼ����
int begin = objPage.getBegin();
//��ʾ����
int viewCount = objPage.getViewCount();
//������¼��
if (begin > 0)
    rs.absolute(begin);
%>
<body>
<form name="frmlist" action="partner_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>����������</u>>><u>��������б�</u></td>
</tr>
<tr height=10>
<td></td> 
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">

	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;��<%=rowCount%>����¼</td>
    <td width="34%" align="center">
	
    <input type=button value="���Ӻ������" onClick="location='partner_add.jsp'"></td>
    <td width="41%">
      <div align="right">
          ��<input type="text" name="pageno" size="4" class="int" value="<%= pageNo %>">ҳ <input type="submit" name="but_gopage" value="ת��" style="cursor:hand;">
	  	  			<%
	  				     out.println("<a href='javascript:goPage(1)'>��һҳ</a>");
	  					 out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>��һҳ</a>");
						 out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>��һҳ</a>");
        				 out.println("<a href='javascript:goPage(" + (pageCount) + ")'>���һҳ</a>");
					%>
	  </div>
    </td>
  </tr>
</table>
<table width="90%" border="0" cellpadding="2" cellspacing="1" bgcolor="#336699" align="center" height="25">
    <tr bgcolor="#FFFFFF">
      <td width="37%" align="center" height="25">�������</td>
      <td  align="center" height="25">�������</td>
      <td width="11%" align="center" height="25">ͼƬ</td>
      <td width="15%" align="center" height="25">����</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td><%=rs.getString("PARTNER") %></td>
       <td>
	  <p align="left"><a href="<%=rs.getInt("LINK") %>" target=_blank><c:out value="<%=rs.getString("SINFOTITLE")%>" escapeXml="true" /></a></td>
      <td></td>
      <td>
      
      <a href='info_edit.jsp?infoid=<%=rs.getInt("IINFOID") %>'>�༭</a>
      <a href=javascript:if(confirm('ȷ��ɾ����'))location='info_del.jsp?infoid=<%=rs.getInt("IINFOID") %>'>ɾ��</a>
     
      
      <%
	
	
	   if(rs.getString("ISOK").equals("N")){%>
	   
	   <a href=javascript:if(confirm('ȷ������ͨ����'))location='info_update.jsp?iinfoid=<%=rs.getInt("IINFOID") %>&pageno=<%=pageNo%>'>����</a>
		<%}else{ %>
      
      ������
            
      <%} %>
      </td>
  	</tr>
<%
    }
}
%>
</table>
</form>
</body>
</html>
<%if (rs != null) Table.close(rs); %>