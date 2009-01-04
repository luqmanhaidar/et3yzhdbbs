<%@ page contentType="text/html; charset=gb2312" %>
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
<form name="form1" action="vote_add_save.jsp" method="post">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>投票管理</u>>><u>投票发布</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1">
	<tr>
		<td width="115" align="center" height="35">投票问题</td>
		<td width="476" height="35"><textarea name="question" cols="50" rows="5"></textarea></td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;1</td>
		<td width="476" height="35"><input type="text" name='item1' size="60">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;2</td>
		<td width="476" height="35"><input type="text" name='item2' size="60">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;3</td>
		<td width="476" height="35"><input type="text" name='item3' size="60">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;4</td>
		<td width="476" height="35"><input type="text" name='item4' size="60">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">项&nbsp;目&nbsp;5</td>
		<td width="476" height="35"><input type="text" name='item5' size="60">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">首页显示</td>
		<td width="476" height="35"><input type="radio" name="is_open" value="Y" checked/>是&nbsp;<input type="radio" name="is_open" value="N"/>否
		</td>
	</tr>
	
	<tr>
		<td colspan="2" height="39">
		<p align="center"><input type="submit" value="提交">
		 <input type="reset" value="重置"></td>
	</tr>
</table>
</form>
	
</div></body></html>
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