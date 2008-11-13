<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<title>系统测试结果</title>
<meta name="author" content="yntsky@gmail.com"/>
<meta name="Copyright" content="www.ntsky.com"/>
<meta name="keywords" content="xml,xslt,xhtml,webwork,hibernate,spring,java,jsp,j2ee,sax,dom,mysql,linux,css2" />
<link rel="stylesheet" href="${base}/styles/default.css" type="text/css" media="all"/>
<meta name="description" content="News,Java技术,xml技术,XHTML+CSS2网站布局,xml,mysql" />
<script>// javascript:window.opener=null;window.close(); --></script>
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
		
		top.document.title = "系统测试结果.....";
		//self.moveTo(0,0);
		//self.resizeTo(screen.availWidth,screen.availHeight);
	}
	//]]>
</script>

</head>
<!-- onblur="this.focus();" -->
<body style="margin:0px;padding:0px;">
	<!-- explain begin -->
	<div class="box1 dashed" style="margin-bottom:16px;">
	  <div class="title">系统测试结果 : </div>
	  <div class="content" style="padding-top:8px;border-top: 1px dashed silver; height:50px;">
		<div style="text-align:left;padding-lef:40px;">	      	
			<#if actionMessage?exists>
				${actionMessage}
			</#if>
			<#if warnMessage?exists>
				${warnMessage}
			</#if>			
		</div>
	  </div>
	</div>
	<!-- end #explain -->
</body>
</html>
