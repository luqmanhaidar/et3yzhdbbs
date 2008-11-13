<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>论坛提示信息</title>
<#include "../includes/head.ftl">
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
</head>
<body>
<#include "../includes/top.ftl">
  <#assign link = "论坛提示信息">
  <#include "../includes/quick.ftl">

  <#assign sessionExist = Session["sessionUser"]?exists>
  <#assign sessionUser = Session["sessionUser"]?if_exists>

  <!-- user info begin -->
  <div class="box1">
    <div class="title"> 论坛提示信息 </div>
    <div class="content">
      <div style="padding:8px;">
		<div><strong>您无权进行当前操作，这可能因以下原因之一造成:</strong></div>
		<ol>
		<li>您所在的角色组(<#if sessionExist>${RoleSingleton.getInstance().getRole(sessionUser.roles).name?if_exists}<#else>Guest</#if>)无法进行此操作。 </li>
		<li>您还没有登录，请填写下面的登录表单后再尝试访问。 </li>
		</ol>
		  <div class="box1" style="margin-bottom:16px;">
		    <div class="title"> 用户登陆 </div>
		    <form action="login.action" method="post" id="login" onSubmit="return Validator.validate(this);">
		      <div class="content">
		        <div class="ibox">
		          <div class="it">用户名: *</div>
		          <div class="iv">
		            <input type="text" name="username" class="t" style="width:160px;"/>
		            <span style="padding-left:8px;"><a href="signup-page.action" title="注册论坛用户">没有注册?</a></span> </div>
		        </div>
		        <div class="ibox">
		          <div class="it">密 码: * </div>
		          <div class="iv">
		            <input type="password" name="password" class="t" style="width:160px;"/>
		            <span style="padding-left:8px;"><a href="recoverPassword-page.action" title="忘记了论坛密码">忘记了密码?</a></span> </div>
		        </div>
		        <div class="ibox">
		          <div class="it">验证码: * </div>
		          <div class="iv">
		            <input type="text" name="vCode" class="t" size="4" maxlength="4"/><input type="hidden" name="cookie" value="0"/>
		          </div>
		          <div style="padding-top:6px;"><img src="servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='servlet/validateImg'"/></div>
		        </div>
		      <div class="box3" style="text-align:center;">
		        <div>
		          <input type="hidden" name="redirectURL" value="${req.requestURI+"?"+req.queryString?if_exists}"/>
		          <input type="hidden" name="action" value="login"/>
		          <input type="submit" name="agree" value="登 陆" class="b"/>
		          <input type="reset" value="重 填"  class="b"/>
		        </div>
		      </div>
		    </form>
		  </div>
		</div>      
      </div>
    </div>
  </div>
  <!-- end #register info -->
  <#include "../includes/footer.ftl">
</body>
</html>
