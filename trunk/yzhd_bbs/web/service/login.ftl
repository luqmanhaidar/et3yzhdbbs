<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>社区服务 : 用户登陆</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="../styles/default.css" type="text/css" media="all"/>
<script type="text/javascript" src="../scripts/prototype.js"></script>
<script type="text/javascript" src="../scripts/ntsky/validator.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	}
	//]]>
</script>
</head>
<body style="margin:0px;padding:0px;background-color:#fff;">
<!-- login info begin -->
  <div class="box1" style="margin:0px;padding:0px;">
    <div class="title"> 社区登陆 </div>
      <form action="login.action" method="post" id="login" onSubmit="return Validator.validate(this);">
      <div class="content" style="background-color:#fff;">
        <div class="ibox">
          <div class="it">用户名: *</div>
          <div class="iv">
            <input type="text" name="username" class="t" style="width:80px;"/>
            <span style="padding-left:8px;"><a href="${base}/signup-page.action" title="注册论坛用户" target="_blank">新注册?</a></span>
            </div>
        </div>
        <div class="ibox">
          <div class="it">密 码: * </div>
          <div class="iv">
            <input type="password" name="password" class="t" style="width:80px;"/>
            <span style="padding-left:8px;"><a href="${base}/recoverPassword-page.action" title="忘记了论坛密码" target="_blank">取密码?</a></span>
          </div>
        </div>
        <div class="ibox">
          <div class="it">验证码: * </div>
          <div class="iv">
            <input type="text" name="vCode" class="t" size="4" maxlength="4"/>
          </div>
          <div style="padding-top:6px;"><img src="../servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='../servlet/validate.gif'"/></div>
        </div>
        <input type="hidden" name="cookie" value="0"/>
        <!--
        <div class="ibox">
          <div class="it">Cookie: * </div>
          <div class="iv">
            <select name="cookie">
              <option value="0" selected="true">不保存</option>
              <option value="1">保存一天</option>
              <option value="2">保存一月</option>
              <option value="3">保存一年</option>
            </select>
           </div>
        </div>
        -->
      </div>
      <div class="box3" style="text-align:center;">
        <div>
          <input type="hidden" name="action" value="login"/>
          <input type="submit" value="登 陆" name="agree" class="b"/>
          <input type="reset" value="重 填"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #login info -->
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
