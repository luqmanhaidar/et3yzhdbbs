<%@ page language="java" contentType="text/html;charset=utf-8"%><%@ page import="java.util.Map"%>
<%	Map seoMap = (Map)application.getAttribute("seo");	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><%=seoMap.get("title")%></title>
<meta name="Robots" content="all" />
<meta http-equiv="Pragma" content="no-cache" />
<meta name="author" content="yntsky@gmail.com" />
<meta name="Copyright" content="www.ntsky.com" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="<%=seoMap.get("description")%>" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta name="keywords" content="<%=seoMap.get("keywords")%>" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		
		top.location.href="index.action";
		
	}
	//]]>
</script>
</head>
<body>
	<h1 style="display:none"><strong>java论坛,jsp论坛,xhtml论坛,xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml</strong></h1>
</body>
</html>
