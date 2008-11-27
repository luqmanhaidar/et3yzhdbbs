
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
  <DIV id=page>
<div style="height: auto;" class="info3">
    <div class="prompt"><div style="width:530px"><img width="530" height="14" src="images/dl-p11.gif"/></div>
    <div style="height: auto; min-height: 120px;" class="con">
<div><strong>您无权进行当前操作，这可能因以下原因之一造成:</strong></div>
<ol>
		<li>您所在的角色组<strong>(<#if sessionExist><#if RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username))?exists>${RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username)).name?if_exists}<#else>已经删除</#if><#else>Guest</#if>)</strong>无法进行此操作。</li>
		<#if warnMessage?exists>
			<li><span class="red">${warnMessage}</span></li>
		</#if>
		<li><a 
href="javascript:history.back(-1);">返回到上一步操作</a></li>
		</ol>
     </div>
    <div style="width:530px"><img src="images/dl-p12.gif"/></div>
    </div>
    </div>
</DIV>



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
