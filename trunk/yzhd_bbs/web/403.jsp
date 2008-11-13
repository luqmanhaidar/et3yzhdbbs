<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>403 -- 访问被拒绝</title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="author" content="yntsky@gmail.com" />
<meta name="Copyright" content="www.ntsky.com" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<style type="text/css">
<!--
	body {
		font-family: Verdana, Arial, Sans-Serif;
		font-size: 12px;
		margin: 12px;
		/*background-color: #e5e5e5;*/
		width: expression(document.body.clientWidth < 760? "760px": "auto" );
	}
	#wrapper { background-color: #ffffff; border: 1px solid #98aab1; min-width: 760px !important; height: 100%; }
	h1,h2,h3,h4,h5,h6	{ margin:0; padding:0; color:#333 } 
	h1					{ font-size:22px; font-weight:bold }
	h2					{ font-size:16.8px; font-weight:bold; margin-top:8px; margin-left:8px; text-align:left }
-->	
</style>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	}
	//]]>
</script>
</head>
<body>
<%
	Map basicMap = (Map)application.getAttribute("basic");
%>
<div id="wrapper" style="margin:auto; width: 80%;padding: 32px;margin-top:100px;">
    <h1 style="color: #904">抱歉! 您当前角色无权限查看此页面。请联系系统管理员，获得相应的权限。</h1>
	<h2 style="margin:4px 0px;">Details</h2>
    <p style="padding-top:4px; margin:0px; border-top:1px solid #ddd; ">感谢您访问本站,如有问题请联系管理员<a href="mailto:yntsky@gmail.com">yntsky@gmail.com</a>, Thank you!</p>
    <p style="margin-top:24px"><a 
href="<%=basicMap.get("bbsDomain")%>">返回网站首页</a></p>
</div>
<!-- end #warpper -->
</body>
</html>