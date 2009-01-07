<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.util.Popedom"%>
<%@ page import="java.sql.ResultSet"%>


<%request.setCharacterEncoding("GBK"); %>

<html>
<head>
<title>��Ϣ�б�</title>
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
String stitle=request.getParameter("title");
String modulename=request.getParameter("modulename");
String userid=(String)session.getAttribute("userid");
String identify=(String)session.getAttribute("identify");
String sCondition = "";
if( stitle!= null && !stitle.equals("")){
    sCondition = sCondition + " and SINFOTITLE like '%" + stitle + "%'";
}
if( modulename!= null && !modulename.equals("")){
    sCondition = sCondition + " and SMODULENAME like '%" + modulename + "%'";
}

//Ҫ��ʾ��ҳ��
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("jview_information", "1=1 " + sCondition);
String strSql = "SELECT * FROM jview_information WHERE IINFOID>0 " + sCondition +" order by IINFOID DESC";
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
<form name="frmlist" action="info_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>��Ϣ����</u>>><u>��Ϣ�б�</u></td>
</tr>
<tr height=10>
<td></td> 
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	��Ϣ����:<input name="title" type="text" value="<%=Page.output(stitle) %>" size="25">
	������Ŀ:<input name="modulename" type="text" value="<%=Page.output(modulename) %>" size="20">
	<input type="submit" name="but_search" value="��ѯ">
	<input type="reset" name="but_reset" value="����">
	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;��<%=rowCount%>����¼</td>
    <td width="34%" align="center">
	<%
		if(Popedom.validatePop(userid,identify,"EDIT")){
			
	%>
    <input type=button value="������Ϣ" onClick="location='info_add.jsp'">
    <%
		}
    %>
    
    </td>
    
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
      <td align="center" height="25">����</td>
      <td width="15%" align="center" height="25">������Ŀ</td>
      <td width="11%" align="center" height="25">����ʱ��</td>
      <td width="11%" align="center" height="25">������</td>
      <td width="15%" align="center" height="25">����</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td>
	  <p align="left"><a href="../../info.jsp?infoid=<%=rs.getInt("IINFOID") %>" target=_blank><c:out value="<%=Page.leftStr(rs.getString("SINFOTITLE"),50)%>" escapeXml="true" /></a></td>
      <td><%=rs.getString("SMODULENAME") %></td>
      <td><%=rs.getDate("DPUBDATE")+" "+rs.getTime("DPUBDATE") %></td>
      <td><%=rs.getString("SPUBUSERNAME") %></td>
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