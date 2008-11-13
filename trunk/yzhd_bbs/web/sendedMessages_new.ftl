<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>已发送消息</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/ntsky/common.js"></script>
</head>
<body>
<div id="head">
<#include "includes/header.ftl">
<DIV id=nav>
<#include "includes/front_top.ftl">
<!--头部结束-->
<!--中间开始-->
<DIV id=page>
  <DIV class=zx1><img height=100 src="images/zx-p1.jpg" width=275 /><IMG 
height=100 src="images/zx-p2.jpg" width=215><IMG height=100 
src="images/zx-p3.jpg" width=250><IMG height=100 src="images/zx-p4.jpg" 
width=240></DIV>
 <#assign item=6>
  <DIV class=zx2><#include "includes/user_left.ftl">
      <DIV>
        <INPUT id=__EVENTTARGET type=hidden name=__EVENTTARGET>
        <INPUT 
id=__EVENTARGUMENT type=hidden name=__EVENTARGUMENT>
        <INPUT id=__VIEWSTATE 
type=hidden 
value=/wEPDwUKMTgyMDgxNjExN2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFB2J0blNlbmQgcCaJ6iDbSE6lrZLj6ng3BU0cmg== 
name=__VIEWSTATE>
      </DIV>
      <SCRIPT type=text/javascript>
//<![CDATA[
var theForm = document.forms['Form1'];
if (!theForm) {
    theForm = document.Form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
//]]>
function CheckAll(chkName)
{
    var chk=document.getElementsByName(chkName);
    for (var i=0;i<chk.length;i++)
    {
        var e = chk.item(i);
        if(e.checked==true)
        {
            e.checked = false;
        }
        else
        {
            e.checked = true;
        }
    }
}
</SCRIPT>
      <SCRIPT src="images/WebResource.axd" type=text/javascript></SCRIPT>
      <SCRIPT src="images/WebResource(1).axd" type=text/javascript></SCRIPT>
      <SCRIPT type=text/javascript>
//<![CDATA[
function WebForm_OnSubmit() {
if (typeof(ValidatorOnSubmit) == "function" && ValidatorOnSubmit() == false) return false;
return true;
}
//]]>
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
                <TD width=6 height=31><IMG height=31 src="images/zx-j1.gif" 
width=6></TD>
                <TD class=tit1>发件箱</TD>
                <TD width=4 height=31><IMG height=31 src="images/zx-j2.gif" 
width=4></TD>
                <TD width=2 height=1><IMG height=1 src="images/spear.gif" width=2></TD>
                <TD width=6 height=31><IMG height=31 src="images/zx-j3.gif" 
width=6></TD>
                <TD class=tit2><A 
href="writeMessage-page.action">写信息</A></TD>
                <TD width=3 height=31><IMG height=31 src="images/zx-j4.gif" 
  width=3></TD>
              </TR>
            </TBODY>
          </TABLE>
          <div class="dxlist">
								<ul class="biaotou2">
									<li class="operate">
										操作
									</li>
									<li class="state">
										状态
									</li>
									<li class="person">
										收件人
									</li>
									<li class="content">
										标题
									</li>
									<li class="time">
										日 期
									</li>
								</ul>
								<div class="mail1">
									共有短消息：${pagination.totalRecord} 条
								</div>
								<ul class="biaoge2">
								<form action="#" method="post" id="messageList">
                                <#list messages as message>
									<li class="operate">
										<input type="checkbox" name="id" value="${message.id}"/>
									</li>
									<li class="state">
										<img src="images/m_<#if message.isRead=0>news<#else>olds</#if>.gif" alt="" width="21" height="14" />
									</li>
									<li class="person">
										${message.receiver}
									</li>
									<li class="content">
										<a href="message.action?messageId=${message.id}&isSend=1">${message.title}</a>
									</li>
									<li class="time">
										${message.sendTime}
									</li>
								</#list>
          						</form>
								</ul>
							</div>
							<ul class="biaoge2">
              				<center>
              					  <@fn.simplePager pagination=pagination />
              				</center>
           					 </ul>
							<div class="an1"><img src="images/zx-b1.gif" style="cursor:hand" width="90" height="30"  name="checkbox2"  onClick="CheckAll('id');" /> <img style="cursor:hand" width="90" height="30" src="images/zx-b2.gif" onClick="System.batchExecute('messageList','确认是否删除选中的消息','deleteMoreMessage-sender.action');" /> </div>
						</DIV>
						<DIV class=zx3></DIV>
      </DIV>
      <DIV>
        <INPUT id=__EVENTVALIDATION type=hidden 
value=/wEWBgL2zIZ7AsKE/MMNAuj+oZgOAobzyfoCAquavfUJAoXOuvwBc4dFCIyLEKE63XAZNFuN0aidzCA= 
name=__EVENTVALIDATION>
      </DIV>
  </DIV>
</DIV>
<input name="SaveId" id="SaveId" type="hidden">
<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
</body>
</html>
