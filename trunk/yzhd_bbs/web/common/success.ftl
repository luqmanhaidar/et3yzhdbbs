<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<title>论坛提示信息</title>
<#include "../includes/header.ftl">
</head>
<body>
<#include "../includes/front_top.ftl">

  <!-- message begin -->
  <div class="box1">
    <div class="title"> 操作成功信息 </div>
    <div class="content">
      <div style="padding:8px;">
      	<ul>
	      	<li>
	      	<#if actionMessage?exists>
			${actionMessage}
			</#if>
			</li>
		</ul>
		<div style="text-align:center;margin:8px;"><a href="#" onClick="history.back(-1);return false;">返回到上一页</a> | <a href="index.action" title="首页">首页</a></div>
      </div>
    </div>
  </div>
  <!-- end #message -->
<#include "../includes/bottom.ftl">
</body>
</html>
