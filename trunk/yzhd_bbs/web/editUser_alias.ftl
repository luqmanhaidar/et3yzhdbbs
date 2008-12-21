<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户昵称设置</title>
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
  <#assign item=5>
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

							<TABLE style="BORDER-BOTTOM: #ccc 1px solid" cellSpacing=0
								cellPadding=0 width=769 border=0>
								<TBODY>
									<TR>
										<TD width=6 height=31>
											<IMG height=31 src="images/zx-j1.gif" width=6>
										</TD>
										<TD class=tit1>
											用户名
										</TD>
										<TD width=4 height=31>
											<IMG height=31 src="images/zx-j2.gif" width=4>
										</TD>
										<TD width=2 height=1>
											<IMG height=1 src="images/spear.gif" width=2>
										</TD>
										<TD width=6 height=31>
											<IMG height=31 src="images/zx-j3.gif" width=6>
										</TD>
										<TD class=tit2>
											<A href="editUser-info.action">资料</A>
										</TD>
										<TD width=4 height=31>
											<IMG height=31 src="images/zx-j4.gif" width=4>
										</TD>
										<TD width=2 height=1>
											<IMG height=1 src="images/spear.gif" width=2>
										</TD>
										<TD width=6 height=31>
											<IMG height=31 src="images/zx-j3.gif" width=6>
										</TD>
										<TD class=tit2>
											<A href="editUser-head.action">头像</A>
										</TD>
										<TD width=3 height=31>
											<IMG height=31 src="images/zx-j4.gif" width=3>
										</TD>
									</TR>
								</TBODY>
							</TABLE>

<form action="editUser.action" method="post" id="editUser" name="editUser" onSubmit="return Validator.validate(this);">
							<input type="hidden" name="face" value="${user.face?if_exists}" id="hiddenFace"/>
							<input type="hidden" name="email" class="t" size="40" value="${user.email?if_exists}"/>
							<input type="hidden" name="birthday" size="12" class="t" value="${user.birthday?if_exists?if_exists}"/>
							<input type="hidden" name="website" class="t" size="50" value="${user.website?if_exists}"/>
							<input type="hidden" name="imQq" class="t" size="20" value="${user.imQq?if_exists}"/>
							<input type="hidden" name="imMsn" class="t" size="40" value="${user.imMsn?if_exists}"/>
							<input type="hidden" name="imIcq" class="t" size="20" value="${user.imIcq?if_exists}"/>
							<input type="hidden" name="imYahoo" class="t" size="40" value="${user.imYahoo?if_exists}"/>
							<input type="hidden" name="gmail" class="t" size="40" value="${user.gmail?if_exists}"/>
							<input type="hidden" name="address" class="t" size="60" value="${user.address?if_exists}"/>
							<input type="hidden" name="address" class="t" size="60" value="${user.signature?if_exists}"/>
														<table width="700" cellspacing="10" cellpadding="0" border="0"
								align="center">
								<tbody>
									<tr>
										<td width="90">
											用户名:
										</td>
										<td width="580" colspan="3">
											<input type="text" class="bd1" disabled="disabled"
												id="txtMyName" value="${user.username}" name="txtMyName" />
											<span class="f-red1"> 此内容不可更改</span>
										</td>
									</tr>
									<tr>
										<td>
											密码：
										</td>
										<td colspan="3">
											<input type="text" class="bd1" disabled="disabled"
												id="txtPass" readonly="readonly" value="****************"
												name="txtPass" />
											<a target="_blank" href="#"><span class="f-red1">
													我的账户</span>
											</a>
										</td>
									</tr>
									<tr>
										<td>
											昵称：
										</td>
										<td colspan="3">
											<input type="text" name="alias" class="bd1" value="${user.alias?if_exists}" />
										</td>
									</tr>
								    <tr>
										<td align="center" colspan="4">
											<image style="border-width: 0px;cursor:hand;"
												src="images/zx-b3.gif"  onClick="editUser.submit();"/>
										</td>
									</tr>
								</tbody>
							</table>
						</DIV>
						<DIV class=zx3></DIV>
					</DIV>
					<DIV>
						<INPUT id=__EVENTVALIDATION type=hidden
							value=/wEWBgL2zIZ7AsKE/MMNAuj+oZgOAobzyfoCAquavfUJAoXOuvwBc4dFCIyLEKE63XAZNFuN0aidzCA=
							name=__EVENTVALIDATION>
					</DIV>
				</FORM>
			</DIV>
		</DIV>
</#if>
<input name="SaveId" id="SaveId" type="hidden">
<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
</body>
</html>
