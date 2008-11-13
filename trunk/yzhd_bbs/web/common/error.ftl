<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>论坛错误信息</title>
<#include "../includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		
	}
	//]]>
</script></head>
<body>
<#include "../includes/top.ftl">
  <#assign link = "错误信息">
  <#include "../includes/quick.ftl">  
  <!-- register info begin -->
  <div class="box1">
    <div class="title"> 错误信息 </div>
    <div class="content">
      <div style="padding:8px;">
      	<#if warnMessage?exists>
		${warnMessage}
		</#if>
      </div>
    </div>
  </div>
  <!-- end #register info -->
<#include "../includes/footer.ftl">  
</body>
</html>
