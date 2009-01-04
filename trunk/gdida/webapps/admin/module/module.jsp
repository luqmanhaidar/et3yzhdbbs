<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.sql.ResultSet"%>
<%request.setCharacterEncoding("GBK"); %>
<%
      response.setHeader("Pragma","No-Cache");
       response.setHeader("Cache-Control","No-Cache");
      response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" href="../../common/module.css" type="text/css">
<script language="javascript" src="../../common/module.js"></script>
<br>
<%
//master对栏目的使用权限
//1表示可管理，即可以见到后面的增删改操作
//2表示信息发布时选择栏目，不可选择有子栏目的栏目
String master = request.getParameter("master");
if (master==null) master = "1";
ResultSet rs=null;
try{
	int iLevel = 1;
	String sLevel = request.getParameter("level");
	if (sLevel!=null) {
		iLevel = Integer.parseInt(sLevel);
		iLevel++;
	}
	String sSpace = "";
	for (int i=0; i<iLevel; i++) {
		sSpace = sSpace.concat("&nbsp;&nbsp;");
	}
	
	String sParentid = request.getParameter("pid");
	if (sParentid==null) {
		
		rs = Table.getRecordsBySql("select * from j_module where IPARENTID is null order by IORDER asc");
	} else {
	
		rs = Table.getRecordsBySql("select * from j_module where IPARENTID="+sParentid+" order by IORDER asc");
	}
	while(rs.next()){
		
		int iModuleid = rs.getInt("IMODULEID");
		String sModuleName = rs.getString("SMODULENAME");
		if(Module.hasSon(rs.getInt("IMODULEID"))){%>
			<div><%=sSpace %><a onmouseover='this.style.cursor="hand"'
			 onClick="expandIt('A<%=iModuleid %>',document.all.A<%=iModuleid %>Child,'<%=iModuleid %>','<%=iLevel %>','<%=master %>');
			 return false"><img border="0" src="../../images/admin/44.gif"></a>
			<%if (master.equals("1")||master.equals("2")) { %><%=sModuleName %><%} else { %>
			<a href='#' onclick='javascript:RetValue("<%=iModuleid %>,<%=sModuleName %>")'><%=sModuleName %></a><%} %>
			<%if (master.equals("1")) { %>&nbsp;&nbsp;
			<a href='module_add.jsp?pid=<%=iModuleid %>'><img border=0 src="../../images/admin/add_a.gif" alt="新增"></a>
			<a href='module_edit.jsp?mid=<%=iModuleid %>'><img border=0 src="../../images/admin/edit_a.gif" alt="编辑"></a>
			<a href='module_del.jsp?mid=<%=iModuleid %>'><img border=0 src="../../images/admin/del_a.gif" alt="删除"></a>
			<%} %></div>
			<div id='A<%=iModuleid %>Child' class='child'></div>
<%		}else{%>
			<div><%=sSpace %><img border="0" src="../../images/admin/33.gif">
			<%if (master.equals("1")) { %><%=sModuleName %><%} else { %>
			<a href='#' onclick='javascript:RetValue("<%=iModuleid %>,<%=sModuleName %>")'><%=sModuleName %></a><%} %>
			<%if (master.equals("1")) { %>&nbsp;&nbsp;
			<a href='module_add.jsp?pid=<%=iModuleid %>'><img border=0 src="../../images/admin/add_a.gif" alt="新增"></a>
			<a href='module_edit.jsp?mid=<%=iModuleid %>'><img border=0 src="../../images/admin/edit_a.gif" alt="编辑"></a>
			<a href='module_del.jsp?mid=<%=iModuleid %>'><img border=0 src="../../images/admin/del_a.gif" alt="删除"></a>
			<%} %></div>
		<%}
	}
}catch(Exception e){
    throw new Exception(e.getMessage());
}finally {
    if (rs != null) Table.close(rs);
}
%>