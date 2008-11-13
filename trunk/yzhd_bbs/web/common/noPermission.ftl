
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>论坛</title>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
</head><body>
<#assign link = "论坛提示信息">

  <#assign sessionExist = Session["sessionUser"]?exists>
  <#assign sessionUser = Session["sessionUser"]?if_exists>
  
<div id="head">
<#include "../includes/header.ftl">

<DIV id=nav>

<#include "../includes/front_top.ftl">
<!--头部结束-->

<!--中间开始-->
<form action="login.action" method="post" id="login" onSubmit="return Validator.validate(this);">
<DIV id=page>
<DIV class=login2>
<DIV class=login><IMG class=tfimg height=40 src="images/dl-p1.gif" 
width=290> <IMG class=tfimg height=40 src="images/dl-p2.gif" width=262> <IMG 
class=tfimg height=40 src="images/dl-p3.gif" width=238> <IMG class=tfimg 
height=40 src="images/dl-p4.gif" width=190> </DIV>
<DIV class=login>
<DIV class=info>
<DIV><IMG height=14 src="images/dl-p11.gif" width=530></DIV>
<DIV class=con>
<DIV class=f-red3><IMG src="images/dl-icon1.gif" 
align=absMiddle>请输入您的用户名、密码登录 </DIV>
<DIV class=info2>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>用 户 名：</SPAN> 
<INPUT class=name id="username" name="username"> <SPAN id=RequiredFieldValidator1 
style="DISPLAY: none; COLOR: red"></SPAN></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>密　　码：</SPAN> 
<INPUT class=password id=Password type=password name="password"> <SPAN 
id=RequiredFieldValidator2 style="DISPLAY: none; COLOR: red"></SPAN><A 
class=red_3 
href="recoverPassword-page.action">忘记密码</A></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>验 证 码：</SPAN> 
<INPUT class=yzm name="vCode" size="4" maxlength="4"> 
<img src="servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='servlet/validate.gif?t=<%=Math.random() %>'"/> <SPAN id=Requiredfieldvalidator3 
style="DISPLAY: none; COLOR: red"></SPAN></DIV>



<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>保存登陆：</SPAN><SPAN class=f-red3>
			
			<select name="cookie" class="yzm">
              <option value="0" selected="true">不保存</option>
              <option value="1">保存一天</option>
              <option value="2">保存一月</option>
              <option value="3">保存一年</option>
            </select>
<LABEL for=IsSaveCookie>在此计算机上保存我的信息,以便下次可以方便登陆</LABEL></SPAN> 

<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV></DIV>
<DIV class=qt>
 <input type="hidden" name="redirectURL" value="${req.requestURI+"?"+req.queryString?if_exists}"/>
<input type="hidden" name="action" value="login"/>
<INPUT id=imgBtn
style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
type=image src="images/dl-b1.gif" name="submit"> 
<INPUT 
type=image src="images/dl-b2.gif" name=Input> </DIV></DIV></DIV>
<DIV><IMG src="images/dl-p12.gif"></DIV></DIV>
<DIV class=gg>
<DIV>
<div><strong>您无权进行当前操作，这可能因以下原因之一造成:</strong></div>
<ol>
		<li>您所在的角色组<strong>(<#if sessionExist><#if RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username))?exists>${RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username)).name?if_exists}<#else>已经删除</#if><#else>Guest</#if>)</strong>无法进行此操作。</li>
		<#if warnMessage?exists>
			<li><span class="red">${warnMessage}</span></li>
		</#if>
		</ol>
</DIV></DIV></DIV>
<DIV class=login><IMG class=tfimg height=39 src="images/dl-p7.gif" 
width=290> <IMG class=tfimg height=39 src="images/dl-p8.gif" width=262> <IMG 
class=tfimg height=39 src="images/dl-p9.gif" width=238> <IMG height=39 
src="images/dl-p10.gif" width=190> </DIV></DIV></DIV>


<input name="SaveId" id="SaveId" type="hidden">
</input>
</form>


<!--底部开始-->
<#include "../includes/bottom.ftl">
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



</body></html>
