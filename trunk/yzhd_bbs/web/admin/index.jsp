<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="session.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<title>婴知岛BBS后台管理</title>
<meta name="author" content="yntsky@gmail.com"/>
<meta name="Copyright" content="www.ntsky.com"/>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
<meta name="keywords" content="xml,xslt,xhtml,webwork,hibernate,spring,java,jsp,j2ee,sax,dom,mysql,linux,css2" />
<meta name="description" content="News,Java技术,xml技术,XHTML+CSS2网站布局,xml,mysql" />
</head>
<frameset name="layout" framespacing="0" border="0" frameborder="no" cols="160,13,*">
  <frame name="menu" src="menu.jsp" scrolling="no" style="border-right:1px;"/>
  <frame name="bar" src="bar.jsp" noResize scrolling="no"/>
  <frameset border="0" frameSpacing="0" frameBorder="no" rows="35,*">
    <frame name="top" src="top.jsp" noResize scrolling="no"/>
	<frameset rows="*" cols="*" frameSpacing="0" frameBorder="no" border="0">
      <frame name="main" src="welcome-iframe.jsp" noResize scrolling="auto"></frame>
  </frameset>
</frameset>
<noframes>
<div align="center"><strong><em><font color="red">Warning:</font></em> 您的浏览器不支持Iframe,请检查您的浏览器</strong></div>
</noframes>
</html>
