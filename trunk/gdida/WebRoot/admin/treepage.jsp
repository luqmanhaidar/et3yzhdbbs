<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.gdie.db.Table"%>
<%@ page import="com.gdie.gdjrb.Module"%>
<%@ page import="java.sql.ResultSet"%>

<html>
<head>
<title>a</title>
<link rel="stylesheet" href="../common/admin.css" type="text/css">
<script src="../js/SpryCollapsiblePanel.js" type="text/javascript"></script>
<link href="../style/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../common/module.css" type="text/css">
<script language="javascript" src="../common/module.js"></script>
 <SCRIPT LANGUAGE="JavaScript">
  <!--
	function clkit(childobj,thisobj,id,iLevel)
	{	
	var
		i=-1;
		str="";
	i=thisobj.src.indexOf("close");
	if (i>0) //收起
		{
		document.all(childobj).style.display="none";
		thisobj.src="../images/admin/open.gif";
	}
	else//展开
		{
		document.all(childobj).style.display="";
		thisobj.src="../images/admin/close.gif";
		if (document.all(childobj).innerHTML=="") //用AJAX获取数据
			{
			str=getData(id,iLevel);
			document.all(childobj).innerHTML=str;
		}
	}
	}

	//=======
	function getData(sParentid,iLevel)
	{
	 try {
     request = new XMLHttpRequest();
   } catch (trymicrosoft) {
     try {
       request = new ActiveXObject("Msxml2.XMLHTTP");
     } catch (othermicrosoft) {
       try {
         request = new ActiveXObject("Microsoft.XMLHTTP");
       } catch (failed) {
         request = false;
       }  
     }
   }

   if (!request)
     alert("Error initializing XMLHttpRequest!");
   var url = "getModule.jsp?sParentid=" + sParentid+"&iLevel="+iLevel;
	request.open("GET", url, false);
    // request.onreadystatechange = updatePage;
	request.setRequestHeader("If-Modified-Since","0");
    request.send(null);
	return request.responseText;
	}
	
  //-->
  </SCRIPT>
</head>

<body background="../images/admin/left.gif" leftmargin="18" style="background-repeat: no-repeat">

<div id="CollapsiblePanel1" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">栏目管理</font></div>
  <div class="CollapsiblePanelContent">
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="module/module_list.jsp" target='mainFrame'>栏目列表</a>
  </div>
</div>



<div id="CollapsiblePanel2" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">信息管理</font></div>
  <div class="CollapsiblePanelContent">

<%	
String master = request.getParameter("master");
if (master==null) master = "1";
ResultSet rs=null;
ResultSet rs1=null;
int childCount=0;
try{	
	rs = Table.getRecordsBySql("select * from j_module where IPARENTID is null order by IORDER asc");
	while(rs.next()){		
		rs1=Table.getRecordsBySql("select count(*) from j_module where IPARENTID="+rs.getInt("IMODULEID"));
		if (rs1.next())	childCount=rs1.getInt(1);
		Table.close(rs1);		
		int iModuleid = rs.getInt("IMODULEID");
		String sModuleName = rs.getString("SMODULENAME");
		if (childCount==0){
			out.print("<div>&nbsp;<IMG SRC='../images/admin/file.gif'/><a href='information/information_filter.jsp?IMODULEID="+iModuleid+"&identify="+rs.getString("IDENTIFY")+"' target='mainFrame'>" +sModuleName+"</a></div>"); 
		}else{
		out.print("<div>&nbsp;<IMG SRC='../images/admin/open.gif' onclick=\"javascript:clkit('child" + iModuleid + "',this,'" + iModuleid +"','0')\" style='cursor:hand' title='" + sModuleName +"'/>" +sModuleName+"</div>"); 
		out.print("<span id='child" + iModuleid + "'></span>");
		}
	}
}catch(Exception e){
    throw new Exception(e.getMessage());
}finally {
    if (rs != null) Table.close(rs);
}
%>	
  </div>
</div>

<div id="CollapsiblePanel6" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">专家管理</font></div>
  <div class="CollapsiblePanelContent">
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="expert/info_list.jsp" target='mainFrame'>专家列表</a>
  </div>
 
</div>

<div id="CollapsiblePanel3" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">链接管理</font></div>
  <div class="CollapsiblePanelContent">
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="link/link_list.jsp" target='mainFrame'>链接列表</a><br>
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="link/type_list.jsp" target='mainFrame'>分类列表</a>
  </div>
 
</div>

<div id="CollapsiblePanel4" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">用户管理</font></div>
  <div class="CollapsiblePanelContent">
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="user/user_list.jsp" target='mainFrame'>用户列表</a>
	
  </div>
</div>
<div id="CollapsiblePanel7" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">项目管理</font></div>
  <div class="CollapsiblePanelContent">
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="project/project_list.jsp" target='mainFrame'>项目列表</a>
	
  </div>
</div>
<!-- 
<div id="CollapsiblePanel5" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0"><IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13"><font style="font-size: 12px;">权限管理</font></div>
  <div class="CollapsiblePanelContent">
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="popedom/type_list.jsp" target='mainFrame'>权限模块</a><br>
	<IMG SRC="../images/admin/blank.gif" BORDER=0>&nbsp;<a href="popedom/popedom_list.jsp" target='mainFrame'>权限列表</a>
  </div>
</div>
 -->
<div class="parent">
    <a href="logout.jsp">
  		<IMG SRC="../images/admin/plus.gif" BORDER=0 width="26" height="13">退出
    </a>
</div>

<script type="text/javascript">
<!--
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1", {contentIsOpen:false});
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2", {contentIsOpen:false});
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3", {contentIsOpen:false});
var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4", {contentIsOpen:false});
//var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5", {contentIsOpen:false});
var CollapsiblePanel6 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6", {contentIsOpen:false});
var CollapsiblePanel7 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel7", {contentIsOpen:false});
//-->
</script>

</body>

</html>

