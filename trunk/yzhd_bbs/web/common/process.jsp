<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<title>系统执行中,请稍后......</title>
<meta name="author" content="yntsky@gmail.com"/>
<meta name="Copyright" content="www.ntsky.com"/>
<meta name="keywords" content="xml,xslt,xhtml,webwork,hibernate,spring,java,jsp,j2ee,sax,dom,mysql,linux,css2" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/default.css" type="text/css" media="all"/>
<meta name="description" content="News,Java技术,xml技术,XHTML+CSS2网站布局,xml,mysql" />
<script>// javascript:window.opener=null;window.close(); --></script>
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
		
		top.document.title = "系统执行中,请稍后......";
		//self.moveTo(0,0);
		//self.resizeTo(screen.availWidth,screen.availHeight);
	}
	//]]>
</script>

</head>
<body style="margin:0px;padding:0px;" onblur="this.focus();">
<div id="wrap">
	<!-- explain begin -->
	<div class="box1 dashed" style="margin-bottom:16px;">
	  <div class="title">系统执行中,请稍后......</div>
	  <div class="content" style="padding-top:8px;border-top: 1px dashed silver;">
		<div style="text-align:center"><img src="<%=request.getContextPath()%>/images/process.gif" /></div><br/>
		<div style="text-align:center"></div>
	  </div>
	</div>
	<!-- end #explain -->
</div>
</body>
</html>
