<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>撰写短消息</title>

<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content","Basic",".");
	};
	var submitForm = function(status){
		// 检测接收消息的用户数
		function checkReceiverNum(){
			var receiver = document.getElementById("receiver").value;
			if(receiver.split(",").length>5){
				alert("同时最多只能发给5个接收者.");
				return false;
			}
			return true;
		}
		
		if(!checkReceiverNum()){
			return ;
		}
		
		// 设置状态 1,正常信息 2,草稿信息
		document.getElementById("status").value=status;
		with(document.getElementById("writeMessage")){
			action = "writeMessage.action";
			target = "_self";
			submit();
		}
	}
	//]]>
</script>
</head><body>
<div id="head">

<#include "includes/header.ftl">



<div id="nav">
<#include "includes/front_top.ftl">

<!--中间开始-->
<DIV id=page>
<DIV class=zx1><IMG height=100 src="images/zx-p1.jpg" width=275><IMG 
height=100 src="images/zx-p2.jpg" width=215><IMG height=100 
src="images/zx-p3.jpg" width=250><IMG height=100 src="images/zx-p4.jpg" 
width=240></DIV>
 <#assign item=6>
<DIV class=zx2><#include "includes/user_left.ftl">

<DIV><INPUT id=__EVENTTARGET type=hidden name=__EVENTTARGET> <INPUT 
id=__EVENTARGUMENT type=hidden name=__EVENTARGUMENT> <INPUT id=__VIEWSTATE 
type=hidden 
value=/wEPDwUKMTgyMDgxNjExN2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFB2J0blNlbmQgcCaJ6iDbSE6lrZLj6ng3BU0cmg== 
name=__VIEWSTATE> </DIV>



</SCRIPT>

<DIV class=part2>
<DIV class=con>
<TABLE style="BORDER-BOTTOM: #ccc 1px solid" cellSpacing=0 cellPadding=0 
width=769 border=0>
  <TBODY>
  <TR>
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
href="myMessages.action">收件箱</A></TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j4.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
      href="sendedMessages.action">发件箱</A></TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j4.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <TD width=6 height=31><IMG height=31 src="images/zx-j1.gif" 
width=6></TD>
    <TD class=tit1>写信息</TD>
    <TD width=3 height=31><IMG height=31 src="images/zx-j2.gif" 
  width=3></TD></TR></TBODY></TABLE>
<form action="#" method="post" id="writeMessage" onSubmit="return Validator.validate(this);">
 <input type="hidden" name="status" value="1"/>
<DIV class=zx4>

<TABLE cellSpacing=10 cellPadding=0 width=700 border=0>
  <TBODY>
  
    <TD class=sj width=112>收件人：</TD>
    <TD width=400><INPUT name="receiver" type="text" class=headline id="receiver" style="WIDTH: 400px" value="${message.receiver?if_exists}"
      maxLength=20 > </TD>
    <TD width=150></TD></TR>
  <TR>
    <TD class=sj width=112>标　题：</TD>
    <TD width=400><INPUT class="headline" type="text" style="WIDTH: 400px" 
      maxLength=20 name="title" id="title"></TD>
    <TD width=150></TD></TR>
  <TR>
    <TD class=sj vAlign=center>内　容：</TD>
    <TD rowSpan=2>
     <textarea name="content" style="WIDTH: 400px; HEIGHT: 260px" id="content"></textarea>
     <input type="hidden" name="flag" value="0"/>
    </TD>
    <TD></TD></TR>
  <TR>
    <TD height=180>&nbsp;</TD></TR></TBODY></TABLE></DIV></DIV>
<DIV class=zx3>
<DIV class=an2>&nbsp;&nbsp;
<image src="images/zx-b3.gif" style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; WIDTH: 90px; HEIGHT: 30px; BORDER-RIGHT-WIDTH: 0px" onClick="submitForm(1);" style="cursor:hand" /></DIV></DIV></DIV>
</form>

<DIV></DIV>
</DIV></DIV>


<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->




</body></html>