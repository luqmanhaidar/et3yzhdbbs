<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
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
String sCondition = "";
if( stitle!= null && !stitle.equals("")){
    sCondition = sCondition + " and question like '%" + stitle + "%'";
}


//Ҫ��ʾ��ҳ��
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("J_QUESTIONS", "1=1 " + sCondition);
String strSql = "SELECT * FROM J_QUESTIONS WHERE qid>0 " + sCondition +" order by qid DESC";
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
<form name="frmlist" action="vote_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>ͶƱ����</u>>><u>ͶƱ�б�</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	ͶƱ����:
	  <input name="title" type="text" value="<%=Page.output(stitle) %>" size="25">
	<input type="submit" name="but_search" value="��ѯ">
	<input type="reset" name="but_reset" value="����">	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;��<%=rowCount%>����¼</td>
    <td width="34%" align="center">
    <input type=button value="����ͶƱ" onClick="location='vote_add.jsp'"></td>
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
      <td height="25" align="center">ͶƱ����</td><td width="11%" align="center">��ǰͶƱ</td>
      <td width="11%" align="center" height="25">����</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td>
	  <p align="left"><a href="vote_detail.jsp?qid=<%=rs.getInt("qid") %>"><%=rs.getString("question") %></a></td>
	  <td><%=rs.getString("is_open")%></td>
      <td>
      <a href='vote_edit.jsp?qid=<%=rs.getInt("qid") %>'>�༭</a>
      <a href=javascript:if(confirm('ȷ��ɾ����'))location='vote_del.jsp?qid=<%=rs.getInt("qid") %>'>ɾ��</a>
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