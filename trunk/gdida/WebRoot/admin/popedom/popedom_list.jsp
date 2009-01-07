<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.util.SelectUtil"%>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.util.Popedom" %>
<%
String uid=(String)session.getAttribute("userid");
boolean flag=Popedom.validatePopedomType(uid,"POPEDOM_MANAGE");
if(!flag){	
	%>	
	<script language="javascript">
		alert("你没有该权限");
	    window.history.back(1);
	</script>
	<%	
}else{
session.setAttribute("identify","POPEDOM_MANAGE");
}
%>
<html>
<head>
<title>链接列表</title>
<link rel="stylesheet" href="../../common/admin.css" type="text/css">
<script LANGUAGE="JavaScript">
	function openwin(url) {
		window.open (url, "newwindow", "height=320, width=480, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no")
	}
	//转页
	function goPage(pageNo) {
		document.frmlist.pageno.value = pageNo;
		document.frmlist.submit();
	}
</script>
</head>

<%
String stitle=request.getParameter("title");
String stype=request.getParameter("tid");
String sCondition = "";
if( stitle!= null && !stitle.equals("")){
    sCondition = sCondition + " and con like '%" + stitle + "%'";
}
int tid=0;
if( stype!= null && !stype.equals("")){
    sCondition = sCondition + " and tid=" + stype ;
    tid=Integer.parseInt(stype);
}

//要显示的页数
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("jview_popedom", "1=1 " + sCondition);
String strSql = "SELECT * FROM jview_popedom WHERE id>0 " + sCondition +" order by id DESC";
ResultSet rs = null;
rs = Table.getRecordsBySql(strSql);

//分页对象
Page objPage = new Page(rowCount,Page.pageViewCount,pageNo);
//总页数
int pageCount = objPage.getPageCount();
//当前页号
pageNo = objPage.getPageNo();
//开始条数
int begin = objPage.getBegin();
//显示条数
int viewCount = objPage.getViewCount();
//滚动记录集
if (begin > 0)
    rs.absolute(begin);
%>

<body>
<form name="frmlist" action="popedom_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>权限管理</u>>><u>权限列表</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	权限描述:<input name="title" type="text" value="<%=Page.output(stitle) %>" size="25">
	权限分类:<%=SelectUtil.getPopedom(tid) %>
	<input type="submit" name="but_search" value="查询">
	<input type="reset" name="but_reset" value="重置">
	</td>
  </tr>
</table>
<br>
<table width="90%" align="center">
  <tr>
    <td width="25%">&nbsp;共<%=rowCount%>条记录</td>
    <td width="34%" align="center">
    <input type=button value="增加权限" onClick="location='popedom_add.jsp'"></td>
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
<table width="90%" border="0" cellpadding="2" cellspacing="1" bgcolor="#336699" align="center" height="25">
    <tr bgcolor="#FFFFFF">
      <td width="" align="center" height="25">权限描述</td>
      <td width="" align="center" height="25">权限标识</td>
      <td width="" align="center" height="25">所属分类</td>
      <td width="20%" align="center" height="25">操作</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td height="25"><p align="left"><%=rs.getString("con") %></td>
      <td height="25"><%=rs.getString("pop") %></td>
	  <td height="25"><%=rs.getString("type") %></td>
      <td height="25"><a href='popedom_edit.jsp?lid=<%=rs.getInt("id") %>'>编辑</a>
		<a href=javascript:if(confirm('确定删除!'))location='popedom_del.jsp?lid=<%=rs.getInt("id") %>'>删除</a></td>
  	</tr>
<%
    }
}if (rs != null) Table.close(rs);
%>
</table>
</form>
</body>
</html>
