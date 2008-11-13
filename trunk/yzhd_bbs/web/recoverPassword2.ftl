<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>取回密码(提示问题答案检测)</title>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
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
  <#assign link = "取回密码(提示问题答案检测)">
  <!-- recoverPassword begin -->
  <div class="box1">
    <div class="title"> 取回密码(第一步：用户名) </div>
    <form action="recoverPassword.action" method="post" id="recoverPassword2" onSubmit="return Validator.validate(this);">
      <input type="hidden" name="id" value="${user.id}"/>
      <div class="content">
        <div class="ibox">
          <div class="it">密码提示问题: *  </div>
          <div class="iv"><span class="red">${user.question}</span></div>
        </div>
        <div class="ibox">
          <div class="it">请填写密码提示问题答案: *  </div>
          <div class="iv">
            <input type="text" name="answer" class="t" size="40"/>
          </div>
        </div>
        <div class="ibox">
          <div class="it">发送新密码到邮箱: *  </div>
          <div class="iv">
            <input type="checkbox" name="isSendMail" value="true"/>
          </div>
        </div>
      </div>
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="取回密码" name="agree" class="b"/>
          <input type="reset" value="重 填"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #recoverPassword -->
<#include "includes/footer.ftl">
  <script type="text/javascript">
	//<![CDATA[
		window.onload = function(){
			<#if newPassword?if_exists!="">
	  		alert("您的新密码 : ${newPassword?if_exists}");
			</#if>
			
			<#if warnMessage?if_exists!="">
	  		alert("${warnMessage?if_exists}");
			</#if>
		}
	//]]>
  </script>  
</body>
</html>
