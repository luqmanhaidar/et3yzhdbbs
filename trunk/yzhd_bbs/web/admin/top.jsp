<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.Map"%>
<%@ page import="com.ntsky.bbs.Symbols"%>
<%@ page import="com.ntsky.bbs.domain.Admin"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory"%>
<%@ page import="com.ntsky.framework.util.HttpUtil"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory"%>
<%@ page import="com.ntsky.bbs.util.IPLocation" %>
<%@ page import="com.ntsky.bbs.service.AdminService"%>
<%
	Object object = session.getAttribute(Symbols.SESSION_ADMIN);
	String username = "";
	String lastLoginTime = "";
	String lastLoginIp = "";
	if( object == null ){
		username = HttpUtil.getCookieValue(request, Symbols.COOKIE_ADMIN);
		// cookie存在的场合
		AdminService adminService = (AdminService) BeanFactory.getInstance(getServletConfig().getServletContext()).getBean("adminService");
		Admin admin = adminService.getAdmin(username);
		if(admin!=null){
			username = admin.getUsername();
			lastLoginTime = admin.getLastLoginTime().toString();
			lastLoginIp = IPLocation.getLocation(admin.getLastLoginIp());
		}
	}	
	else{
		username = ((Admin)object).getUsername();
		lastLoginTime = ((Admin)object).getLastLoginTime().toString();
		lastLoginIp = IPLocation.getLocation(((Admin)object).getLastLoginIp());
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<title>后台管理</title>

<meta name="keywords" content="xml,xslt,xhtml,webwork,hibernate,spring,java,jsp,j2ee,sax,dom,mysql,linux,css2" />
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
<meta name="description" content="News,Java技术,xml技术,XHTML+CSS2网站布局,xml,mysql" />
<script>// javascript:window.opener=null;window.close(); --></script>
</head>
<body style="margin:0px;padding:0px;">

<div style="height:30px;border-bottom: 1px dashed silver;padding:0px;margin-top:4px;">
  <div class="lrbox" style="height:30px;">
    <div class="d" style="width:31%; border-left:0px;">用户名: <font class="red"><%=username%></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
    <div class="d" style="width:31%; border-left:0px;">最后登陆时间: <font class="red"><%=lastLoginTime%></font></div>
    
  </div>
</div>
</body>
</html>
