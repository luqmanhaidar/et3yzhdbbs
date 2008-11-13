<html>
<head>
<title>发生错误</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="${base}/styles/default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">
<style type="text/css">
	.success { text-align: left; margin-left: 3%; }
</style>
<!-- action result begin -->
<div class="box1 dashed">
  <div class="title" style="background:#FFFFFF">操作结果: <span class="red">失败......!</span></div>
  <div class="content" style="clear:both; border-top: 1px dashed silver; ">
  	
	<table width="100%" border="0" cellpadding="0" cellspacing="0" style="padding:4px;">
	  <tr>
		<td width="14%" align="center"><div style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=../images/error.png);width:48px;height:48px"></div></td>
		<td width="86%">
		<ol>
			<li>${actionMessage?if_exists}</li>
		</ol>
		<#if urls?if_exists>
		<ol>
			<#assign urls = urls>
			<#assign keys = urls?keys>
			<#list keys as key>
			<li>迁移到<a href="${urls[key]}">${key}</a>页面</li>
			</#list>
			<li><a href="#" onClick="history.back(-1);return false;">返回到上一页</a></li>
		</ol>
		</#if>		
		</td>
	  </tr>
	</table>
  </div>
</div>
<!-- end #action result -->
</body>
</html> 
