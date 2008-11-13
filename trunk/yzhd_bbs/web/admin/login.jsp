<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="ww" uri="/webwork" %>
<!DOCTYPE html PUBliC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8"/>
<title>BBS管理--登陆</title>
<meta name="author" content="yntsky@gmail.com"/>
<meta name="Copyright" content="www.ntsky.com"/>
<meta name="keywords" content="xml,xslt,xhtml,webwork,hibernate,spring,java,jsp,j2ee,sax,dom,mysql,linux,css2" />
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
<meta name="description" content="News,Java技术,xml技术,XHTML+CSS2网站布局,xml,mysql" />
</head>
<body style="margin:0px;padding:0px;">
<div class="box1" style="margin:26%; margin-top:150px;">
  <div class="title" align="center">管理登陆</div>
  <form action="login.action" method="post">
    <div class="content">
      <div class="ibox">  
        <div class="it">用户名: *</div>
        <div class="iv">
          <input name="username" type="text" class="t" value="" style="width:140px;"/>
          <span class="remark">用户名不为空,长度为6~12</span> </div>
      </div>
      <div class="ibox">
        <div class="it">密 码: *</div>
        <div class="iv">
          <input name="password" type="password" class="t" value="" style="width:140px;"/>
          <span class="remark">密码不为空,长度为6~12</span> </div>
      </div>
      <div class="ibox">
        <div class="it">验证码: *</div>
        <div class="iv">
          <input name="vCode" type="text" class="t" value="" size="4"/>
          <span style="padding-left:6px;"><img src="../servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" style="cursor:pointer;height:16px;" onclick="this.src='<%=request.getContextPath()%>/servlet/validate.gif'"/></span>
          <span class="remark">验证码不为空</span> </div>
      </div>
      <div class="ibox">
        <div class="it">Cookie : </div>
        <div class="iv">
          <select name="cookie">
            <option value="0">不保存</option>
            <option value="1">保存一天</option>
            <option value="2">保存一月</option>
            <option value="3">保存一年</option>
          </select>
        </div>
      </div>	  
    </div>
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="登陆管理" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<script type="text/javascript">
	//<![CDATA[
	<ww:if test="warnMessage != null">
		alert("<ww:property value="warnMessage"/>");
    </ww:if>
	//]]>
</script>
</body>
</html>
