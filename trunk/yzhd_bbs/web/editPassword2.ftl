<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户密码修改</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles/default.css" type="text/css" media="all"/>
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
</head>
<body>
<div id="head">
<#include "includes/header.ftl">
<DIV id=nav>
<#include "includes/front_top.ftl">
<!--头部结束-->
<!--中间开始-->
<#if actionMessage?exists>
		<@fn.message content=actionMessage type="success"/>
<#else>
<DIV id=page>
  <DIV class=zx1><img height=100 src="images/zx-p1.jpg" width=275 /><IMG 
height=100 src="images/zx-p2.jpg" width=215><IMG height=100 
src="images/zx-p3.jpg" width=250><IMG height=100 src="images/zx-p4.jpg" 
width=240></DIV>

  <DIV class=zx2>
  <#assign item=8>
  <#include "includes/user_left.ftl">
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
			<form action="editPassword.action" method="post" id="editPassword" onSubmit="return Validator.validate(this);">
		  <div class="ibox">
              <div class="it">原始密码: * </div>
              <div>
                <input type="password" name="oldPassword" class="t" style="width:160px;"/>
                <span class="">原始密码不为空</span> </div>
            </div>
            <div class="ibox">
              <div class="it">新密码: * </div>
              <div>
                <input type="password" name="password" class="t" style="width:160px;"/>
                <span class="">密码长度为6~20位</span> </div>
            </div>
            <div class="ibox">
              <div class="it">确认新密码: * </div>
              <div>
                <input type="password" name="repeatPassword" class="t" style="width:160px;"/>
                <span class="">重新输入新密码</span> </div>
            </div>
            <div style="text-align:center; clear:both; margin:0 auto">
              <div>
              	<input type="image" name="submit" src="images/zx-b3.gif"/>
              </div>
            </div>
            </form>
		 		
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
</#if>

<input name="SaveId" id="SaveId" type="hidden">
<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	  	<#if warnMessage?if_exists!="">
		alert("${warnMessage?if_exists}");
		</#if>	
	}
	//]]>
  </script>
</body>
</html>
