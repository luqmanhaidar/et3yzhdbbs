<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">



<title>用户信息</title>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
</head><body>
<div id="head">
<#include "includes/header.ftl">

<div id="nav">
<#include "includes/front_top.ftl">
<!--头部结束-->

<!--中间开始-->
<DIV id=page>
<TABLE cellSpacing=0 cellPadding=0 width=980 border=0>
  <TBODY>
  <TR>
    <TD colSpan=5 height=20>&nbsp;</TD></TR>
  <TR>
    <TD width=141><IMG height=100 src="images/zl-p1.gif" width=141></TD>
    <TD width=29><IMG height=100 src="images/zl-p2.gif" width=30></TD>
    <TD vAlign=top rowSpan=2>
      <TABLE cellSpacing=0 cellPadding=0 width=675 border=0>
        <TBODY>
        <TR>
          <TD background=images/zl-p7.gif colSpan=2 height=40><IMG 
            height=40 src="images/zl-p7.gif" width=1></TD></TR>
        <TR>
          <TD class=zl1 vAlign=top width=191>
            <DIV class=tx><IMG height=95 
            src="${user.face?if_exists}" 
            width=95></DIV>
            <DIV class=xx><IMG src="images/zl-i2.gif"> <A class=white_1 
            href="writeMessage-page.action?receiver=${URLEncoder.encode(user.username)}" 
            target=_blank>给该用户发送短消息</A></DIV>
            <DIV class=xx><IMG src="images/zl-i2.gif"> <A class=white_1 
            href="userTopic.action?username=${URLEncoder.encode(user.username)}">查看其发布的所有话题</A> 
            </DIV></TD>
          <TD class=zl2 vAlign=top width=484>
            <UL class=info1>
              <LI>用户名：<SPAN class=b>${user.username}</SPAN> </LI>
              <LI>昵称：<SPAN class=f-red2>${user.alias}</SPAN> </LI>
              <LI>性别： <#if user.sex=1><IMG src="images/zl-f.gif">男<#elseif user.sex=2>女<#else>保密</#if></LI>
              <LI>身份：<SPAN class=b>${RoleSingleton.getInstance().getRole(user.roles).name}</SPAN></LI>
              <LI>最近登录：${user.lastLoginTime} </LI>
              <LI>注册日期：${user.registerTime} </LI>
              <LI>个人等级：<SPAN class=b>${user.level.name?if_exists}</SPAN> </LI>
              <LI>QQ： <SPAN class=b>0</SPAN> </LI></UL>
            <DIV class=clear></DIV>
            <UL class=info1>
              <!--<LI>魅力值：<SPAN class=b>3229</SPAN> </LI>-->
              <LI>积分：<SPAN class=b>${user.money?if_exists}</SPAN> </LI>
              <LI>发表主题帖数：<SPAN class=b>${user.totalTopic?if_exists}</SPAN> </LI>
              <!--<LI>已使用积分：<SPAN class=b>0</SPAN> </LI>-->
              <LI>回复帖数：<SPAN class=b>${user.totalPost?if_exists}</SPAN> </LI>
             <!-- <LI>剩余积分：<SPAN class=b>${user.integral?if_exists}</SPAN> </LI>
              <LI>冻结积分：<SPAN class=b>0</SPAN> </LI>--></UL>
            <DIV class=qt>
            <UL>
              <LI>主页：<SPAN class=b><A title=http:// 
              href="#" onClick="window.open('${user.website?if_exists}')" >${user.website?if_exists}</A></SPAN> </LI></UL></DIV></TD></TR>
        <TR>
          <TD></TD>
          <TD vAlign=top>
            <DIV class=zl3>
            <DIV class=sign>个人签名：${user.signature?if_exists}</DIV></DIV></TD></TR>
        <TR>
          <TD vAlign=bottom colSpan=2><IMG height=51 
            src="images/zl-p8.gif" width=675></TD></TR></TBODY></TABLE></TD>
    <TD width=30><IMG height=100 src="images/zl-p9.gif" width=30></TD>
    <TD width=105 background=images/zl-p13.gif>&nbsp;</TD></TR>
  <TR>
    <TD background=images/zl-p3.gif>&nbsp;</TD>
    <TD><IMG height=299 src="images/zl-p4.gif" width=30><BR><IMG 
      height=251 src="images/zl-p5.gif" width=30><BR><IMG height=51 
      src="images/zl-p6.gif" width=30></TD>
    <TD><IMG height=299 src="images/zl-p10.gif" width=30><BR><IMG 
      src="images/zl-p11.gif"><BR><IMG src="images/zl-p12.gif"></TD>
    <TD background=images/zl-p14.gif>&nbsp;</TD></TR></TBODY></TABLE></DIV>


<input name="SaveId" id="SaveId" type="hidden">

<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->




</body></html>