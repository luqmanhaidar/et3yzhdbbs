<#macro html title>
<html>
<head>
<title> BBS系统 </title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="Robots" content="all" />
<meta name="author" content="yntsky@gmail.com" />
<meta name="Copyright" content="www.ntsky.com" />
<meta http-equiv="Content-Language" content="UTF-8"/>
<meta name="description" content="open source j2ee bbs" />
<meta name="MSSmartTagsPreventParsing" content="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="keywords" content="xslt,xml,rss,java,jsp,j2ee,javascript,sax,dom,mysql,oracle,postgres,linux,css2,xhtml" />
<link rev="made" href="mailto:yntsky@gmail.com" />
<link rel="Shortcut Icon" type="image/x-icon" href="/favicon.ico" />
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
<script type="text/javascript" src="../scripts/prototype.js"></script>
<script type="text/javascript" src="../scripts/ntsky/global.js"></script>
<script type="text/javascript" src="../scripts/ntsky/system.js"></script>
<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="../scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="../scripts/tooltip/tooltip.js"></script>
</head>
<body style="margin: 0px;padding:0px;">
<div id="wrap">
	<#nested/>
	<!-- footer begin -->
	<div id="footer">
	  <div id="copyleft">${application["basic"]["copyright"]}</div>
	</div>
	<!-- end #footer -->
</div>
<iframe frameborder="0" id="Stat" name="Stat" src="createStat.action" width="0" height="0"> </iframe>
<noscript><iframe src="*.htm"></iframe></noscript>
</body>
</html> 
</#macro>