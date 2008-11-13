<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改密码</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; 修改密码">
  <#include "includes/quick.ftl">   
  <br />
  <!-- begin editPassword -->
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action" title="我的控制面板">我的控制面板</a></li>
      <li><a href="editUser-page.action" title="修改用户资料">资料修改</a></li>
      <li><span>密码修改</span></li>
      <li><a href="myTopics.action" title="我的主题">我的主题</a></li>
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><a href="myMessages.action">消息管理</a></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>  
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <#if actionMessage?exists>
		<@fn.message content=actionMessage type="success"/>
	  <#else>
      <h1>密码修改</h1>
      <div class="box1" style="margin-left:0px;margin-right:0px;">
          <div class="content" style="border-top:0px;">
          <form action="editPassword.action" method="post" id="editPassword" onSubmit="return Validator.validate(this);">
		  <div class="ibox">
              <div class="it">原始密码: * </div>
              <div class="iv">
                <input type="password" name="oldPassword" class="t" style="width:160px;"/>
                <span class="required">原始密码不为空</span> </div>
            </div>
            <div class="ibox">
              <div class="it">新密码: * </div>
              <div class="iv">
                <input type="password" name="password" class="t" style="width:160px;"/>
                <span class="required">密码长度为6~20位</span> </div>
            </div>
            <div class="ibox">
              <div class="it">确认新密码: * </div>
              <div class="iv">
                <input type="password" name="repeatPassword" class="t" style="width:160px;"/>
                <span class="required">两次输入的新密码一致</span> </div>
            </div>
            <div class="box3" style="text-align:center; clear:both">
              <div>
                <input type="submit" value="修改密码" name="agree" class="b"/>
                <input name="reset" type="reset"  class="b" value="重 填"/>
              </div>
            </div>
            </form>
          </div>
      </div>	
	  </#if>      
    </div>
  </div>
  <!-- end #editPassword -->
<#include "includes/footer.ftl">
  <script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	  	<#if warnMessage?exists>
		alert("${warnMessage}");
		</#if>	
	}
	//]]>
  </script>
</body>
</html>
