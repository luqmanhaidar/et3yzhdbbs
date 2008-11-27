<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<title>我的积分</title>
<head>
<link href="style/default.css" type="text/css" rel="stylesheet" />
<link href="style/menu.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
</head><body>
<div id="head">

<div id="top">
<#include "includes/header.ftl">
</div>


<#include "includes/front_top.ftl">


<!--中间开始-->
<DIV id=page>
<DIV class=zx1><img height=100 src="images/zx-p1.jpg" width=275 /><IMG 
height=100 src="images/zx-p2.jpg" width=215><IMG height=100 
src="images/zx-p3.jpg" width=250><IMG height=100 src="images/zx-p4.jpg" 
width=240></DIV>
<DIV class=zx2>
<#assign item=4>
<#include "includes/user_left.ftl">
<FORM id=Form1 name=Form1 onSubmit="javascript:return WebForm_OnSubmit();" 
action=SendSMS.aspx method=post>
<DIV><INPUT id=__EVENTTARGET type=hidden name=__EVENTTARGET> <INPUT 
id=__EVENTARGUMENT type=hidden name=__EVENTARGUMENT> <INPUT id=__VIEWSTATE 
type=hidden 
value=/wEPDwUKMTgyMDgxNjExN2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFB2J0blNlbmQgcCaJ6iDbSE6lrZLj6ng3BU0cmg== 
name=__VIEWSTATE> </DIV>
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
<div class="jf1">
        <div class="wellcome"> 欢迎您： <span class="f-red2">${user.username}</span></div>
        <div class="">
        <ul>
        <li>积分：<span class="f-red2">${user.money}</span> 分</li>
        <li>发表主题：<span class="f-red2">${user.totalTopic}</span> 篇</li>
        <li>等级：<span class="f-red2">${RoleSingleton.getInstance().getRole(user.roles).name}</span></li>
         <!--<li>已用积分：<span class="f-red2">50</span> 分</li>
        <li>冻结积分：<span class="f-red2">0</span> 分</li>
       <li>魅力值：<span class="f-red2">0</span> 分</li>
        <li>剩余积分：<span class="f-red2">${user.money}</span> 分</li>-->
        <li>回复：<span class="f-red2">${user.totalPost}</span> 篇</li>
        </ul>
        </div>
        </div>
</DIV>
<DIV class=zx3></DIV>
</DIV>


<DIV><INPUT id=__EVENTVALIDATION type=hidden 
value=/wEWBgL2zIZ7AsKE/MMNAuj+oZgOAobzyfoCAquavfUJAoXOuvwBc4dFCIyLEKE63XAZNFuN0aidzCA= 
name=__EVENTVALIDATION> </DIV>
</FORM></DIV></DIV>


<input name="SaveId" id="SaveId" type="hidden">

<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->




</body></html>