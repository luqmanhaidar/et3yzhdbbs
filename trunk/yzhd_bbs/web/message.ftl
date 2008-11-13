<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看短消息</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; <a href=\"myMessages.action\" title=\"短消息管理\">短消息管理</a> &gt; 查看短消息">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action">我的控制面板</a></li>
      <li><a href="editUser-page.action">资料修改</a></li>
      <li><a href="editPassword-page.action">密码修改</a></li>
      <li><a href="myFavorites.action">我的收藏</a></li>
      <li><span>消息管理</span></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <#include "includes/subtabs.ftl">  
    <div id="tabmainbox">
      <div>
        <br />
        <div class="box1" style="margin:0px;border-bottom:0px;">
          <div class="title">查看短消息</div>
            <div class="content">
              <div class="ibox">
                <div class="it" style="width:96%; text-align:center;"><a href="replyMessage.action?messageId=${message.id}" title="回复短消息"><img src="images/m_re.gif" alt="回复短消息" width="40" height="40" /></a> <a href="writeMessage-page.action" title="撰写短消息"><img src="images/m_to.gif" alt="" width="40" height="40" /></a> <a href="deleteMessage-receiver.action?messageId=${message.id}" title="删除短消息"><img src="images/m_delete.gif" alt="" width="40" height="40" /></a> <a href="replyMessage.action?messageId=${message.id}&amp;forward=true" title="转发短消息"><img src="images/m_fw.gif" alt="" width="40" height="40" /></a> </div>
              </div>
              <div class="ibox">
                <div class="it">消息标题: *</div>
                <div class="iv">${message.title}</div>
              </div>
              <div class="ibox" style="height:208px;">
                <div class="it">消息内容: * </div>
                <div class="iv" style="height:200px; width:68%">${message.content}</div>
              </div>
            </div>
            <!-- end #content -->
        </div>
      </div>
      <!-- explain begin -->
    </div>
  </div>
<#include "includes/footer.ftl">
</body>
</html>
