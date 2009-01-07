<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import= "com.gdie.db.Table"%>
<%@ page import="java.sql.ResultSet"%>
<%request.setCharacterEncoding("gb2312"); %>
<%
StringBuffer sBreakPage = new StringBuffer();
StringBuffer sHi_Value = new StringBuffer();
sBreakPage.append("&nbsp;<1>&nbsp;");
sHi_Value.append("<input type='hidden' name='cinfo' id='bodytext1'/>");
%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<LINK href="../../common/admin.css" type=text/css rel=stylesheet>
</head>
<style type="text/css">
    @import url(../../common/htmlarea/htmlarea.css);
</style>
<script type="text/javascript" src="../../common/htmlarea/htmlarea.js"></script>
<script type="text/javascript" src="../../common/htmlarea/lang/gb.js"></script>
<script type="text/javascript" src="../../common/htmlarea/dialog.js"></script>
<script type="text/javascript" src="../../common/infoAdd.js"></script>
<script type="text/javascript" src="../../common/datacheck.js"></script>
<body>
<div align=center>

<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>投票管理</u>>><u>投票明细</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>

<%
	String qid = request.getParameter("qid");
	String sql = "select * from J_QUESTIONS where qid = "+qid;
	ResultSet rs=Table.getRecordsBySql(sql);
	rs.next();
	int num=1;
	String is_open=rs.getString("is_open");
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1">
	<tr>
		<td width="115" align="center" height="35">投票问题</td>
		<td width="476" height="35">		<%=rs.getString("question") %></td>
	</tr>
	<%
		sql="select * from J_ITEMS where qid = "+qid;
		rs=Table.getRecordsBySql(sql);
		while(rs.next()){
	%>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;<%=num %></td>
		<td width="476" height="35"><%=rs.getString("item")%>:共<font color="#FF0000"><%=rs.getInt("itemcount")%></font>票
		</td>
	</tr>
	<%
	num++;
	}
	while(num<=5){
	%>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;<%=num %></td>
		<td width="476" height="35">&nbsp;		</td>
	</tr>
	<%
	num++;
	}
	%>
	<tr>
		<td width="115" align="center" height="35">首页显示</td>
		<td width="476" height="35">
		<%
			
			if(is_open.equals("Y")){
			%>首页显示<%
			}else{
		%>首页不显示<%}%>
		
		
		</td>
	</tr>
	
	<tr>
		<td colspan="2" height="39">
		<p align="center"><input type="submit" value="返回"onclick="window.location.href='vote_list.jsp'">
		 </td>
	</tr>
</table>

	
</div></body></html><%if (rs != null) Table.close(rs); %>
<script>
initEditor('bodytext');
function gosubmit(frm){
	if(Validator.Validate(frm, 3))
	{
		if (form_check()) frm.submit();
	}
}
function change(){
	with(document.all){
		if(itypeid.options(itypeid.selectedIndex).value==28){
			jy.style.display='';
		}else{
			jy.style.display='none';
		}
	}
}
function AddArea(Url)
{
	with(document.all)
	{
		var vReturnValue = window.showModalDialog(Url);
		if(vReturnValue!=null)
		{
				vReturnValues=vReturnValue.split(",");
				modulename.value=vReturnValues[1];
				moduleid.value=vReturnValues[0];
				/*
				if(moduleid.value=="020501"||moduleid.value=="020602"||moduleid.value=="020603"){
					np.style.display="";
					sattm.value="";}
				else{
					np.style.display="none";
					sattm.value="";
				}*/
		}
	}
}
function showmsg(target,img){
	var ss=getoffset(target);
	obj=document.getElementById("MsgDiv")
	document.all.showimg.src="../attachment/upload/"+img;
	obj.style.display='';
	obj.style.left=ss[1];
	obj.style.top=ss[0]+target.offsetHeight;
}
function hidden(){
	with(document.all){
		MsgDiv.style.display='none';
	}
}
function getoffset(obj) 
{ 
     var t=obj.offsetTop; 
     var l=obj.offsetLeft; 
     while(obj=obj.offsetParent) 
     { 
     t+=obj.offsetTop; 
     l+=obj.offsetLeft; 
     }
     var rec = new Array(1); 
     rec[0] = t; 
     rec[1] = l;
     return rec
}
function getImages(ctrlobj){
	retval = window.open("../attachment/upload.jsp", "", "toolbar=0,scrollbars=1,resizable=1 "  );
}
</script>