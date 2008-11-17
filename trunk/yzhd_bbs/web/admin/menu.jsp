<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.ntsky.bbs.util.SAUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>论坛管理树</title>
<link rel="StyleSheet" href="../scripts/dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="../scripts/dtree/dtree.js"></script>
</head>
<body>
<div class="dtree">
  <!-- <p><a href="javascript: d.openAll();"><font color="#FF0000">open all</font></a> | <a href="javascript: d.closeAll();"><font color="#FF0000">close all</font></a></p> -->
  <script type="text/javascript">
	<!--
		d = new dTree('d','../scripts/dtree/');
		e = new dTree('e','../scripts/dtree/');
   
		d.add(0,-1,'论坛管理');
		d.add(101,0,'常规管理');
		d.add(102,101,'基本设置','basicConfig!open.action');
		/*d.add(105,101,'搜索引擎优化设置','seoConfig!open.action');*/		
		/*d.add(103,101,'注册设置','registerConfig!open.action');*/
		/*d.add(104,101,'系统设置','systemConfig!open.action');*/
		/*d.add(105,101,'邮件设置','emailConfig!open.action');*/
		d.add(106,101,'每页记录数设置','paginationConfig!open.action');
		d.add(108,101,'系统积分设置','moneyConfig!open.action');
		d.add(109,101,'修改密码','editPassword.action');
		/*d.add(109,101,'系统积分设置','experienceConfig.action');
		d.add(110,101,'IP设置','ipManage.html');*/
		d.add(201,0,'论坛模块');
		d.add(202,201,'添加论坛','forumManage!openCreatePage.action');
		d.add(203,201,'论坛管理','forums.action');
		d.add(204,201,'顶层类别排序','orderTopForum_page.action');
		/*d.add(205,201,'合并论坛','uniteForum_page.action');*/
		d.add(206,201,'帖子类型管理','categories.action');
		d.add(300,0,'权限模块');
		d.add(301,300,'添加系统用户','createUser-page.action');
		d.add(302,300,'用户管理','users.action');
		d.add(303,300,'系统角色管理','roles.action');
		d.add(304,300,'后台管理员管理','admins.action');
		d.add(400,0,'帖子模块');
		d.add(401,400,'帖子管理','topics.action?forumId=0&sort=dateCreated&order=desc&time=0&type=title&keyword=');
		d.add(402,400,'脏话过滤设置');
		d.add(4001,402,'添加过滤脏话','createBadword-page.action');
		d.add(4002,402,'所有过滤脏话','badwords.action');
		d.add(403,400,'帖子控制','topicConfig!open.action');
		/*
		d.add(5,0,'附件模块');
		d.add(51,5,'附件功能设置','attSetting.html');
		d.add(52,5,'附件管理','attachment.html');
		*/
		d.add(6,0,'其他模块');
		d.add(61,6,'公告管理','announcements.action');
		d.add(62,6,'图片文字链接','links.action');
		/*d.add(63,6,'短信','messages.action');
		d.add(631,63,'添加短信','createMessage-page.action');*/
		/*d.add(632,63,'短信管理','messages.action');*/
		//d.add(64,6,'帮助管理','helps.action');
		/*d.add(66,6,'访问记录管理','stats.action');*/
		/*d.add(67,6,'操作日志管理','actLogs.action');*/
		/*d.add(68,6,'作成静态数据','makeStatic-page.action');*/
		d.add(7,0,'退出登录','logout.action','退出登录','_parent');
		d.add(8,0,'返回首页','../index.action','返回首页','_parent');
		
		d.config.closeSameLevel = true;
		d.config.target = "main";
		document.write(d);
		d.openTo(101);
	//-->
  </script>
</div>
<br/>
</body>
</html>
