<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>取回密码(用户名检测)</title>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<link rel="stylesheet" href="styles/default.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/tooltip/tooltip.js"></script>
</head>
<body>
<#include "includes/header.ftl">
<#include "includes/front_top.ftl">
  <#assign link = "取回密码(用户名检测)">
  <!-- recoverPassword begin -->
  <div class="box1">
    <div class="title"> 取回密码(第一步：用户名) </div>
    <form action="recoverPassword-checkUsername.action" method="post" id="recoverPassword" onSubmit="return Validator.validate(this);">
      <div class="content">
        <div class="ibox">
          <div class="it"><strong>用户名: * </strong> </div>
          <div class="iv">
            <input type="text" name="username" class="t"/>
            <span style="padding-left:8px;">
            <input type="text" name="vCode" class="t" size="4" maxlength="4"/><img src="servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='servlet/validate.gif'"/>
            </span> </div>
        </div>
      </div>
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="下一步" name="agree" class="b"/>
          <input type="reset" value="重 填"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #recoverPassword -->
  <script type="text/javascript">
	//<![CDATA[
		window.onload = function(){
			<#if warnMessage?if_exists!="">
	  		alert("${warnMessage?if_exists}");
			</#if>
		}
	//]]>
</script>
<#include "includes/footer.ftl"> 
</body>
</html>
