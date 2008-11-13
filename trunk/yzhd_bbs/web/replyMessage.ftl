<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>撰写短消息</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content","Basic",".");
	};
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; <a href=\"myMessages.action\" title=\"短消息管理\">短消息管理</a> &gt; 撰写短消息">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action" title="我的控制面板">我的控制面板</a></li>
      <li><a href="editUser-page.action" title="修改用户资料">资料修改</a></li>
      <li><a href="editPassword-page.action" title="修改用户密码">密码修改</a></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>      
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><span>消息管理</span></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
 	<#include "includes/subtabs.ftl">
    <div id="tabmainbox">
      <div>
        <h1><#if req.getParameter("forward")?exists>转发短消息<#else>回复短消息</#if></h1>
        <br />
        <div class="box1" style="margin:0px;">
          <div class="title"><#if req.getParameter("forward")?exists>转发短消息<#else>回复短消息</#if></div>
          <form action="writeMessage.action" method="post" id="writeMessage" onSubmit="return Validator.validate(this);">
            <input type="hidden" name="status" value="1"/>
            <div class="content">
              <div class="ibox">
                <div class="it">收件人: *</div>
                <div class="iv">
                  <input name="receiver" type="text" class="t" value="<#if !req.getParameter("forward")?exists>${message.sender}</#if>" size="40"/></div>
              </div>
              <div class="ibox">
                <div class="it">消息标题: *</div>
                <div class="iv">
                  <input name="title" type="text" class="t" value="RE:${message.title}" size="50"/>
                </div>
              </div>
              <div class="ibox" style="height:208px;">
                <div class="it">消息内容: * </div>
                <div class="iv" style="height:200px; width:68%">
                  <textarea name="content" cols="50" rows="7" id="content">
                  <br/><br/><div style="padding-top:4px;border-top: 1px solid #000;">${message.sender}在${message.sendTime}的消息中写道 : <br/>${message.content}</div>
                  </textarea>
                </div>
              </div>
              <input type="hidden" name="flag" value="0"/>
            </div>
            <!-- end #content -->
            <div class="box3" style="text-align:center; clear:both">
              <div>
                <input type="submit" value="回复短消息" name="agree" class="b"/>
                <input type="button" value="返回" name="agree" class="b" onClick="history.back(-1);"/>
              </div>
            </div>
          </form>
        </div>
      </div>
      <!-- explain begin -->
    </div>
  </div>
<#include "includes/footer.ftl">  
</body>
</html>
