<%@ page contentType="text/html; charset=GBK" %>
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
<form name="form1" action="partner_add_save.jsp" method="post">
<table border=0 width="90%" cellspacing="0" cellpadding="0" align="center">
<tr height=28>
<td><u>合作伙伴管理</u>>><u>合作伙伴新增</u></td>
</tr>
<tr height=10>
<td></td>
</tr>
</table>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" width="594" id="table1">
	<tr>
		<td width="115" align="center" height="35">合作伙伴</td>
		<td width="476" height="35"><input type="text" name="stitle" size="45" dataType="Require" re="请填写标题"></td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">链&nbsp;&nbsp;&nbsp;接</td>
		<td width="476" height="35"><input type=text name='link' size="45" dataType="Require" re="请填入链接">
		</td>
	</tr>
	<tr>
		<td width="115" align="center" height="35">图片</td>
		<td width="476" height="35">

		<input type="text" name="sattm" readonly onmouseout='hidden()' onmouseover='showmsg(this,this.value)'>
		<input type=button value='选择图片' onclick='getImages(document.all.sattm);'><span id=myImg></span>
		</span>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="39">
		<p align="center"><input type="button" value="提交" name="B1" onclick='gosubmit(document.all.form1);'>
		 <input type="reset" value="重置" name="B2"></td>
	</tr>
</table>
</form>
	<div id='MsgDiv' style='position:absolute;display:none' onMouseOut="this.style.display='none';" onMouseOver="this.style.display='';">
	<table width="153" height="73" cellspacing="1" style="background-color: #000000"><tr class='showImg'>
	<td>&nbsp;图片预览：<br><img id='showimg'></td></tr></table>
	</div>
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