<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看短消息</title>

<link rel="stylesheet" href="styles/default.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/tooltip/tooltip.js"></script>
<script type="text/javascript">
    	window.onload = function(){
		replaceMyTextarea("content","Basic",".");
	};
	//<![CDATA[
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
</head>
<body>
<#include "includes/header.ftl">
<#include "includes/front_top.ftl">
<DIV id=page>
<DIV class=zx1><IMG height=100 src="images/zx-p1.jpg" width=275><IMG 
height=100 src="images/zx-p2.jpg" width=215><IMG height=100 
src="images/zx-p3.jpg" width=250><IMG height=100 src="images/zx-p4.jpg" 
width=240></DIV>
<DIV class=zx2>
<#assign item=6>
<#include "includes/user_left.ftl">
<#assign isSend = request.getParameter("isSend")?if_exists>
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
    <#if isSend="1">
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
href="myMessages.action">收件箱</A></TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j4.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <TD width=6 height=31><IMG height=31 src="images/zx-j1.gif" 
width=6></TD>
    <TD class=tit1>发件箱</TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j2.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <#else>
    <TD width=6 height=31><IMG height=31 src="images/zx-j1.gif" 
width=6></TD>
    <TD class=tit1>收件箱</TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j2.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
      href="sendedMessages.action">发件箱</A></TD>
    <TD width=4 height=31><IMG height=31 src="images/zx-j4.gif" 
width=4></TD>
    <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
    </#if>
    <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
    <TD class=tit2><A 
      href="writeMessage-page.action">写信息</a></TD>
    <TD width=3 height=31><IMG height=31 src="images/zx-j4.gif" 
  width=3></TD></TR></TBODY></TABLE>
 <input type="hidden" name="status" value="1"/>
<DIV class=zx4>

<table width="700" border="0" cellspacing="10" cellpadding="0">
      <tr>
        <td colspan="2" align="right">&nbsp;</td>
        </tr>
      <tr>
        <td width="112" align="right">标 题：</td>
        <td width="550"><input name="textfield"  readonly="readonly" type="text" class="headline" value="${message.title}" /></td>
      </tr>
      <tr>
        <td width="112" align="right">发件人：</td>
        <td width="550"><input name="textfield" type="text"  readonly="readonly" class="bd1" value="${message.sender}" /></td>
      </tr>
      <tr>
        <td width="112" align="right">发送时间：</td>
        <td width="550">${message.sendTime?string("yyyy-MM-dd HH:mm:ss")}</td>
      </tr>
      <tr>
        <td align="right">内　容：</td>
        <td rowspan="2">
          <label>
          <textarea name="content" class="ly1"  readonly="readonly">${message.content}</textarea>
          </label>
        </td>
      </tr>
      <tr>
        <td height="70">&nbsp;</td>
        </tr>
      <tr>
        <td height="70">&nbsp;</td>
        <td valign="top">
        <img src="images/icon18.gif"  onClick="location='replyMessage.action?messageId=${message.id}';" style="cursor:hand"/>
      </td>
      </tr>
    </table></DIV></DIV>
<DIV class=zx3>
<DIV class=an2>&nbsp;&nbsp;
</DIV></DIV></DIV>


<DIV></DIV>
</DIV></DIV>
 
  <!--<div id="tabmainbox">
      <div>
        <br />
                <div class="box1" style="margin:0px;border-bottom:0px;">
          <div class="title">查看短消息</div>
            <div class="content">
              <div class="ibox">
                <div class="it" style="width:96%; text-align:center;"><a href="replyMessage.action?messageId=${message.id}" title="回复短消息"><img src="images/m_re.gif" alt="回复短消息" width="40" height="40" /></a> <a href="writeMessage-page.action" title="撰写短消息"><img src="images/m_to.gif" alt="" width="40" height="40" /></a> <a href="deleteMessage-receiver.action?messageId=${message.id}" title="删除短消息"><img src="images/m_delete.gif" alt="" width="40" height="40" /></a> <a href="replyMessage.action?messageId=${message.id}&amp;forward=true" title="转发短消息"><img src="images/m_fw.gif" alt="" width="40" height="40" /></a> </div>
              </div>
              <div class="ibox">
                <div class="it">消息标题: *</div>
                <div class="iv">${message.title}</div>
              </div>
              <div class="ibox" style="height:208px;">
                <div class="it">消息内容: * </div>
                <div class="iv" style="height:200px; width:68%">${message.content}</div>
              </div>
            </div>
            <!-- end #content -->
        </div>
      <!-- explain begin -->
   <!-- </div> -->
  <!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
</body>
</html>
