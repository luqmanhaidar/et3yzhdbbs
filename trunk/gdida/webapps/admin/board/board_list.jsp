<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%request.setCharacterEncoding("GBK"); %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<html>
<head>
<title>留言板</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script LANGUAGE="JavaScript">
	function openwin(url) {
		window.open (url, "newwindow", "height=320, width=480, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	//??
	function goPage(pageNo) {
		document.frmlist.pageno.value = pageNo;
		document.frmlist.submit();
	}
</script>
<%
String stitle=request.getParameter("title");
String modulename=request.getParameter("modulename");
String ewriter=request.getParameter("ewriter");
String sCondition = "";
if( stitle!= null && !stitle.equals("")){
    sCondition = sCondition + " and scontext like '%" + stitle + "%'";
}
if( ewriter!= null && !ewriter.equals("")){
    sCondition = sCondition + " and writer like '%" + ewriter + "%'";
}
if( modulename!= null && !modulename.equals("")){
    sCondition = sCondition + " and bip like '%" + modulename + "%'";
}


int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("J_BOARD", "1=1 " + sCondition);
String strSql = "SELECT * FROM J_BOARD WHERE boardid>0 " + sCondition +" order by boardid DESC";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);


Page objPage = new Page(rowCount,Page.pageViewCount,pageNo);

int pageCount = objPage.getPageCount();

pageNo = objPage.getPageNo();

int begin = objPage.getBegin();

int viewCount = objPage.getViewCount();

if (begin > 0)
    rs.absolute(begin);
%>


</head>


<body>
<table width="790" border="0" cellpadding="0" cellspacing="0" align="center"bgcolor="#FFFFFF">
<tr><td>
<form name="frmlist" action="board_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td>留言审核</td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	查找内容:
	  <input name="title" type="text" value="<%=Page.output(stitle) %>" size="20">
	查找IP:
	<input name="modulename" type="text" value="<%=Page.output(modulename) %>" size="20">
	查找姓名:
	  <input name="ewriter" type="text" value="<%=Page.output(ewriter) %>" size="20">
	
	<input type="submit" name="but_search" value="查找">
	<input type="reset" name="but_reset" value="重置">
	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;共<%=rowCount%>条留言，共<%=pageCount %>页</td>
    <td width="34%" align="center">
    </td>
    <td width="41%">
      <div align="right">
          第<input type="text" name="pageno" size="4" class="int" value="<%= pageNo %>">页 <input type="submit" name="but_gopage" value="转到" style="cursor:hand;">
	  	  			<%
	  				     out.println("<a href='javascript:goPage(1)'>第一页</a>");
	  					 out.println("<a href='javascript:goPage(" + (pageNo-1) + ")'>上一页</a>");
						 out.println("<a href='javascript:goPage(" + (pageNo+1) + ")'>下一页</a>");
        				 out.println("<a href='javascript:goPage(" + (pageCount) + ")'>最后一页</a>");
					%>
	  </div>
    </td>
  </tr>
</table>
<table width="90%" border="0" cellpadding="0" cellspacing="1" bgcolor="#FFFFFF" align="center" height="25">
  <tr><td>  
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
    	String writer = rs.getString("writer");
    	String scontext = rs.getString("scontext");
    	request.setAttribute("writer",writer);
    	request.setAttribute("scontext",scontext);
%><table width="100%" border="0" cellpadding="2" cellspacing="1" bgcolor="#336699" align="center" height="25">
	<tr bgcolor="#EEEEEE">
	<td><c:out value="${writer}"/><%=":"+rs.getString("bip") %></td>
	<td width="35%" colspan="2">发表时间:<%=rs.getDate("pubtime")+" "+rs.getTime("pubtime")%></td>
	</tr>
	
	
  	<tr bgcolor="#FFFFFF" height="25">
      <td colspan="2" height="50" rowspan="2"><div><c:out value="${scontext}"/></div></td>
      
      <td width="10%" align="center">
		<%
			if(rs.getString("isok").equals("Y")){
				%>已通过<%
			}else{
				%>
				<a href="board_update.jsp?boardid=<%=rs.getInt("boardid") %>" onclick="javascript:if (!confirm('您确定吗?')) return false;">通过</a>
				<%				
			}
		
		%>

	  </td>
  	</tr>
  	<tr bgcolor="#FFFFFF"><td align="center"><a href=javascript:if(confirm('确定删除！'))location='board_del.jsp?boardid=<%=rs.getInt("boardid") %>'>删除</a></td></tr>
<%
    }
}
%></table></td></tr>
</table>
</form></td></tr>
</table>
</body>
</html>
<%if (rs != null) Table.close(rs); %>