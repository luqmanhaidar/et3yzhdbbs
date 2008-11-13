<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>论坛管理菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="StyleSheet" href="scripts/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="scripts/dtree/dtree.js"></script>
<style type="text/css">
body {
	background-color: #fff;	
}
</style>
</head>
<body>
<#assign isSession = Session["sessionUser"]?exists>
<div class="dtree">
  <script type="text/javascript">
		<!--
		d = new dTree('d','scripts/dtree/');
		d.add(0,-1,'（新技术论坛）');
		d.add(11000,0,'首页','index.action');		
		<#list forums as forum>
		<#if forum.depth==1>
		d.add(${forum.id},${forum.parentId},'${forum.name}');
		<#else>
		d.add(${forum.id},${forum.parentId},'${forum.name}','forum.action?forumId=${forum.id}');
		</#if>		
		</#list>
		d.add(10000,0,'用户管理');
		<#if isSession>
		d.add(10001,10000,'修改密码','editPassword-page.action');
		d.add(10002,10000,'修改注册信息','editUser-page.action');
		d.add(10003,10000,'我的帖子','myTopics.action');
		d.add(10003,10000,'我的收藏','myFavorites.action');
		d.add(10004,10000,'消息管理','myMessages.action');
		d.add(10005,10000,'文件管理','userAttachments.action');
		d.add(10006,10000,'退出登录','logout.action');
		<#else>
		d.add(10001,10000,'注册用户','signup-page.action');
		d.add(10002,10000,'用户登陆','login.action');
		d.add(10003,10000,'取回密码','recoverPassword-page.action');		
		</#if>
		
		<#--
		<#if isSession>
			<#assign roles = Session["sessionUser"].roles>
			<#if (roles=="1" || roles=="2" || roles=="3")>
			d.add(20000,0,'论坛管理');
			d.add(20001,20000,'论坛资料管理','admin-editForum.action?forumId=2');
			d.add(20002,20000,'公告管理');
			d.add(20003,20000,'帖子管理');
			d.add(20004,20000,'类别管理');			
			</#if>
		</#if>
		-->
		d.config.target = "main";
		d.config.closeSameLevel = true;
		//d.config.useIcons = true;
		document.write(d);
		//d.openTo(1);
		//-->
	</script>
</div>
</body>
</html>
