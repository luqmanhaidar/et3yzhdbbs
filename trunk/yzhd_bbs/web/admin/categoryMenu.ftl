<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>论坛管理树</title>
<link rel="StyleSheet" href="../scripts/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="../scripts/dtree/dtree.js"></script>
</head>
<body>
<div class="dtree" style="padding-bottom:4px;">
  <!-- <p><a href="javascript: d.openAll();"><font color="#FF0000">open all</font></a> | <a href="javascript: d.closeAll();"><font color="#FF0000">close all</font></a></p> -->
  <script type="text/javascript">
	<!--
		d = new dTree('d','../scripts/dtree/');

		d.add(0,-1,'论坛版块');
		<#list forums as forum>
		<#if forum.depth==1>
		d.add(${forum.id},${forum.parentId},'${forum.name}');
		<#else>
		d.add(${forum.id},${forum.parentId},'${forum.name}','categories.action?forumId=${forum.id}');
		</#if>		
		</#list>
		
		d.config.closeSameLevel = true;
		d.config.target = "categories";
		document.write(d);

		d.openTo(1);
		//d.oAll(true);
	//-->
  </script>
</div>
<div style="font-size:12px;"><a href="mailto: yntsky@gmail.com">www.ntsky.com</a></div>
</body>
</html>



