<html>
<head>
<title>隐藏页面 - 论坛提示信息</title>
</head>
<body>
<script type="text/javascript">
	//<![CDATA[
	<#if actionMessage?exists>
 	alert("${actionMessage}");
	</#if>
	<#if warnMessage?exists>
 	alert("${warnMessage}");
	</#if>	
	//]]>
</script>
</body>
</html>