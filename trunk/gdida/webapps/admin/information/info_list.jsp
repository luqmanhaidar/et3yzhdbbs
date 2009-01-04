<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.common.Page"%>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="com.gdie.util.Popedom"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.gdie.gdjrb.Module"%>

<%request.setCharacterEncoding("GBK"); %>

<html>
<head>
<title>信息列表</title>
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
String identify=(String)session.getAttribute("identify");
String userid=(String)session.getAttribute("userid");
String IMODULEID="IMODULEID";
if ((String)session.getAttribute("IMODULEID")!=null) IMODULEID=(String)session.getAttribute("IMODULEID") ;
String mid=Table.getValue("j_module",IMODULEID,"identify='"+identify+"'","");

String dingOrNot=request.getParameter("dingOrNot");
//System.out.println(identify);
String sCondition = "";
if( stitle!= null && !stitle.equals("")){
    sCondition = sCondition + " and SINFOTITLE like '%" + stitle + "%'";
}

if(dingOrNot!=null && !dingOrNot.trim().equals("")){
	if(dingOrNot.equals("Y")){
		sCondition = sCondition + " and ding!=0 ";
	}else{
		sCondition = sCondition + " and ding=0 ";
	}
}

if (Module.hasSon(Integer.parseInt(mid))) {
	sCondition = sCondition + " AND IMODULEID in (select IMODULEID from j_module,jview_u_p WHERE j_module.IPARENTID ="+mid+" and jview_u_p.uid="+userid+" and jview_u_p.identify=j_module.identify)";
} else {
	sCondition = sCondition + " AND IMODULEID="+mid;
}


//要显示的页数
int pageNo = 0;
try{
    pageNo = Integer.parseInt(request.getParameter("pageno"));
}
catch(Exception e){
    pageNo = 1;
}
int rowCount = Table.getRecordCount("jview_information", "1=1 " + sCondition);
String strSql = "SELECT * FROM jview_information WHERE IINFOID>0 " + sCondition +" order by ding desc, IINFOID DESC";
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
<form name="frmlist" action="info_list.jsp" method="POST">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td valign="middle"> <img src="../../images/gdida_sub_30.gif" width="17" height="17" />&nbsp;&nbsp;你现在：<%=Module.getPathAdmin(Integer.parseInt(IMODULEID)) %></td>
</tr>
<tr height=10>
<td></td> 
</tr>
</table>
<table border=0 width="90%" align="center" cellspacing="1" cellpadding="1" bgcolor="#336699">
  <tr>
    <td width="100%" align="center" bgcolor="#EEEEEE">
	信息标题:<input name="title" type="text" value="<%=Page.output(stitle) %>" size="25">	
	
	&nbsp;
	是否置顶：<select name="dingOrNot">
	<%
	  if(dingOrNot==null) dingOrNot="";
	%>
				<option value="">不限</option>
				<option value="Y" <%=dingOrNot.equals("Y")?"selected":"" %>>是</option>
				<option value="N" <%=dingOrNot.equals("N")?"selected":"" %>>否</option>
			</select>
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
	<%
		if(Popedom.validatePop(userid,identify,"EDIT")){			
	%>
    <input type=button value="增加信息" onClick="location='info_add.jsp'"></td>
    <%
    } %>
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
      <td align="center" height="25">标题</td>
      <td width="11%" align="center" height="25">所属栏目</td>
      <td width="11%" align="center" height="25">发布时间</td>
      <td width="11%" align="center" height="25">发布者</td>
      <td width="15%" align="center" height="25">操作</td>
    </tr>
<%
for (int i=0;i<viewCount;i++) {
    if (rs.next()) {
%>
  	<tr bgcolor="#FFFFFF" align="center" height="25">
      <td>
	  <p align="left"><a href="../../info_detail.jsp?infoid=<%=rs.getInt("IINFOID") %>" target=_blank><c:out value="<%=Page.leftStr(rs.getString("SINFOTITLE"),50)%>" escapeXml="true" /></a>
	  <%
	  int ding=rs.getInt("ding");
	  if(ding!=0){
	  %>
	  <img src="../../images/ding_<%=ding %>.gif">
	  <%} 
	  if(rs.getString("SIFPIC").trim().equals("Y")){
	  %>
	  <img src="../../images/icon.gif">
	  <%} %>
	  </td>
      <td><%=rs.getString("SMODULENAME") %></td>
      <td><%=rs.getDate("DPUBDATE")+" "+rs.getTime("DPUBDATE") %></td>
      <td><%=rs.getString("SPUBUSERNAME") %></td>
      <td>
      
      <%
		if(Popedom.validatePop(userid,rs.getString("identify"),"EDIT")){			
		%>
      <a href='info_edit.jsp?infoid=<%=rs.getInt("IINFOID") %>'>编辑</a>
      <%if (rs.getInt("IMODULEID")!=2) { //机构设置不可删除%>
      <a href=javascript:if(confirm('确定删除！'))location='info_del.jsp?infoid=<%=rs.getInt("IINFOID") %>'>删除</a>
     
      
      <%} }
	   if(rs.getString("ISOK").equals("N")){
	   
		   if(Popedom.validatePop(userid,rs.getString("identify"),"AUDIT")){
	   %>	   
	   <a href=javascript:if(confirm('确定审批通过！'))location='info_update.jsp?iinfoid=<%=rs.getInt("IINFOID") %>&pageno=<%=pageNo%>'>审批</a>
		<%
		   }else{
			   %>
			   未审核
			   <%
		   }
		   }else{ %>
      
      已审批
            
      <%} %>
      </td>
  	</tr>
<%
    }
}if (rs != null) Table.close(rs);
%>
</table>
</form>
</body>
</html>