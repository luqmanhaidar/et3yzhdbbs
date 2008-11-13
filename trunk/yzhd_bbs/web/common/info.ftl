<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>论坛提示信息</title>
<#include "../includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		
	}
	//]]>
</script></head>
<body>
<#include "../includes/top.ftl">
  <#assign link = "提示信息">
  <#include "../includes/quick.ftl">  
  <!-- register info begin -->
  <div class="box1">
    <div class="title"> 提示信息 :  </div>
    <div class="content">
      <div style="padding:8px;">
      	<#if infoMessage?exists>
		${infoMessage}
		</#if>
      </div>
    </div>
  </div>
  <!-- end #register info -->
<#include "../includes/footer.ftl">  
</body>
</html>
