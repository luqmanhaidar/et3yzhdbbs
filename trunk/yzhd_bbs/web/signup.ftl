<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<script type="text/javascript">
	//<![CDATA[
		
		var detailInfoArea = function(){
			var fillinDetail = document.getElementById("fillinDetail");
			if(fillinDetail.checked){
				// 显示详细信息
				Util.show("detailInfo");
				document.getElementById("agree").value="注 册";
			}
			else{
				// 隐藏详细信息
				Util.hidden("detailInfo");
				document.getElementById("agree").value="快速注册";
			}
		};
		
		var checkUser = function(){
			var username = document.getElementById("username").value;
			if((username==null)||(""==username)){
				alert("用户名不为空,请填写您的用户名");
				return false;
			}
			if(username.length<2 || username.length>12){
				alert("用户名长度在2~12之间");
				return false;
			}			
			var checkUserForm = document.getElementById("checkUserForm");
			checkUserForm.username.value=username;
			checkUserForm.action="hidden-checkUser.action";
			checkUserForm.submit();
		};
		
		window.onload = function(){
			<#if actionMessage?exists>
		 	alert("${actionMessage}");
			</#if>		
		}
		
		function readProtocol(){
			// 已阅读条款
			if(!document.getElementById("isReadProtocol").checked){
				document.getElementById("agree").disabled=true;
			}
			else{
				document.getElementById("agree").disabled=false;
			}
		}
		
	//]]>
</script>


<title>论坛</title>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
</head><body>
<div id="head">
<#include "includes/header.ftl">

<DIV id=nav>

<#include "includes/front_top.ftl">
<!--头部结束-->
<form action="signup.action" method="post" id="signup" onSubmit="return Validator.validate(this);">
<input type="hidden" name="face" value="images/face/default.gif" id="hiddenFace"/>
<!--中间开始-->
<DIV id=page>
<DIV class=login2>
<DIV class=login><IMG class=tfimg height=40 src="images/dl-p112.gif" 
width=290> <IMG class=tfimg height=40 src="images/dl-p2.gif" width=262> <IMG 
class=tfimg height=40 src="images/dl-p3.gif" width=238> <IMG class=tfimg 
height=40 src="images/dl-p4.gif" width=190> </DIV>
<DIV class=login>
<DIV class=info>
<DIV><IMG height=14 src="images/dl-p11.gif" width=530></DIV>
<DIV class=con>

<DIV class=info2>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>用 户 名：</SPAN> 
 <input type="text" name="username" id="username" class="name"/><input type="button" value="检测用户" name="checkuser" class="b" onClick="checkUser();"/></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>密　　码：</SPAN> 
<input type="password" name="password" class="password"/>
          <span class="required">用户密码6~20位</span></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>确认密码：</SPAN> 
 <input type="password" name="repeatPassword" class="password"/>
          <span class="required">再次输入用户密码</span></DIV>
<DIV><IMG src="images/dl-icon4.gif"> <SPAN class=f-black2>验 证 码：</SPAN> 
<INPUT class=yzm name="vCode" size="4" maxlength="4"> 
<img src="servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='servlet/validate.gif?t=<%=Math.random() %>'"/></DIV>
<DIV class=qt></DIV>
<DIV class=qt>
  <INPUT onclick="" 
type=image src="images/dl-b2.gif" name=Input> </DIV></DIV></DIV>
<DIV><IMG src="images/dl-p12.gif"></DIV></DIV>
<DIV class=gg>
<DIV><BR><BR><BR><BR><BR><BR><BR><BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;尊敬的客户，如果您无法正常登录我们的网站，请您检查系统时间是否为当前日期，如果还登录不上，请检查您的浏览器是否限制我们写入cookies。 
</DIV></DIV></DIV>
<DIV class=login><IMG class=tfimg height=39 src="images/dl-p7.gif" 
width=290> <IMG class=tfimg height=39 src="images/dl-p8.gif" width=262> <IMG 
class=tfimg height=39 src="images/dl-p9.gif" width=238> <IMG height=39 
src="images/dl-p10.gif" width=190> </DIV></DIV></DIV>

</form>
<form id="checkUserForm" method="post" action="#" target="SignupHidden">
  	<input type="hidden" name="username" value=""/>
  </form>
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


</body></html>