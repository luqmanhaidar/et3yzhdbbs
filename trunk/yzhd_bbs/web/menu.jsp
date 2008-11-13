<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="com.ntsky.bbs.domain.Forum"%>
<%@ page import="com.ntsky.bbs.util.memory.ForumSingleton"%>
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
<div class="dtree">
  <script type="text/javascript">
		<!--
		d = new dTree('d','scripts/dtree/');

		d.add(0,-1,'论坛 （New Technology bbs）');
		<%
		Forum fourm = null;
		Object[] forumArray = ForumMemory.getForums().toArray();
		for(int i=0;i<forumArray.length();i++){
			fourm = (Forum)forumArray[i];
		%>
		d.add(1,0,'论坛列表');
		d.add(101,1,'注册用户','signup-page.action');
		d.add(102,1,'用户登陆','login.action');
		d.add(103,1,'取回密码','recoverPassword-page.action');
		<%
		}
		%>		
		d.add(2,0,'用户管理');
		d.add(101,2,'注册用户','signup-page.action');
		d.add(102,2,'用户登陆','login.action');
		d.add(103,2,'取回密码','recoverPassword-page.action');
		
		/*
		d.add(104,1,'修改密码','login.action');
		d.add(105,1,'修改资料','login.action');
		d.add(2,0,'论坛设置');
		d.add(21,2,'添加论坛','example01.html');
		d.add(22,2,'编辑论坛','example01.html');
		d.add(23,2,'合并论坛','example01.html');
		d.add(3,0,'控制面板');
		d.add(31,3,'资料修改','example01.html');
		d.add(32,3,'密码修改','example01.html');
		d.add(4,0,'用户管理');
		d.add(41,4,'主题图标','example01.html');
		d.add(42,4,'词语过滤','example01.html');
		d.add(5,0,'附件管理');
		d.add(51,5,'登陆','login.html');
		d.add(52,5,'首页','index.html');
		d.add(53,52,'首页附件管','index.html');
		d.add(54,52,'首页附件管','index.html');
		d.add(6,0,'其他设置');
		d.add(61,6,'公告设置','example01.html');
		d.add(62,6,'友情链接','example01.html');
		d.add(63,6,'短信管理','example01.html');
		d.add(64,6,'短信管理','example01.html');
		d.add(65,6,'短信管理','example01.html');
		d.add(66,6,'短信管理','example01.html');
		d.add(67,6,'短信管理','example01.html');
		d.add(68,6,'短信管理','example01.html');
		d.add(69,6,'短信管理','example01.html');

		d.add(7,0,'退出登录','example01.html');
		d.add(72,6,'友情链接','example01.html');
		d.add(73,6,'短信管理','example01.html');
		d.add(74,6,'短信管理','example01.html');
		d.add(75,6,'短信管理','example01.html');
		d.add(76,6,'短信管理','example01.html');
		d.add(77,6,'短信管理','example01.html');
		d.add(78,6,'短信管理','example01.html');
		d.add(79,6,'短信管理','example01.html');*/
		/*d.add(9,0,'My Pictures','example01.html','Pictures I\'ve taken over the years','','','../js/dtree/img/imgfolder.gif');
		d.add(10,9,'The trip to Iceland','example01.html','Pictures of Gullfoss and Geysir');
		d.add(11,9,'Mom\'s birthday','example01.html');
		d.add(12,0,'Recycle Bin','example01.html','','','../js/dtree/img/trash.gif');*/

		d.config.target = "main";
		d.config.closeSameLevel = true;
		//d.config.useIcons = true;
		document.write(d);		
		//d.openTo(100);
		//-->
	</script>
</div>
</body>
</html>
