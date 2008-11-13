<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户登陆</title>
<#include "includes/head.ftl">
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "用户登陆">
  <#include "includes/quick.ftl">  
  <!-- login begin -->
  <div class="box1">
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
            <input type="text" name="vCode" class="t" size="4" maxlength="4"/>
          </div>
          <div style="padding-top:6px;"><img src="servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='servlet/validate.gif'"/></div>
        </div>
        <div class="ibox">
          <div class="it">Cookie 选项: * </div>
          <div class="iv">
            <select name="cookie">
              <option value="0" selected="true">不保存</option>
              <option value="1">保存一天</option>
              <option value="2">保存一月</option>
              <option value="3">保存一年</option>
            </select>
            <span style="padding-left:8px;">请选择你的 Cookie 保存时间，下次访问可以方便</span>输入。 </div>
        </div>
      </div>
      <div class="box3" style="text-align:center;">
        <div>
          <input type="hidden" name="action" value="login"/>
          <input type="submit" name="agree" value="登 陆" class="b"/>
          <input type="reset" value="重 填"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #login -->
  <#include "includes/footer.ftl">  
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
