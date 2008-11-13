<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>跳转页面</title>
<#include "../includes/head.ftl">
</head>
<body>
<script type="text/javascript">
	//<![CDATA[
	<#if redirectURL?exists>
 	top.location.href="${redirectURL?if_exists}";
	</#if>
	//]]>
</script>
</body>
</html>
