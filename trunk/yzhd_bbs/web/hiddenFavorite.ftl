<html>
<head>
<title>poll信息输出</title>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	<#if actionMessage?exists>
 	alert("${actionMessage}");
	</#if>
	<#if warnMessage?exists>
 	alert("${warnMessage}");
	</#if>	
	}
	//]]>
</script>
</head>
<body>

</body>
</html>