<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的消息</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; 我的短消息">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action" title="我的控制面板">我的控制面板</a></li>
      <li><a href="editUser-page.action" title="修改用户资料">资料修改</a></li>
      <li><a href="editPassword-page.action" title="修改用户密码">密码修改</a></li>
	  <li><a href="myTopics.action" title="我的主题">我的主题</a></li>    
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><span>消息管理</span></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>  
    </ul>
  </div>
  <div id="tabmain">
	<#include "includes/subtabs.ftl"> 
    <div id="tabmainbox">
      <div>
		<h1>收件箱</h1>
		<br />
        <table align="center" cellpadding="0" class="ltable" cellspacing="1" style="margin:0px;width:100%;">
          <tr class="ltrt" style="background-color:#f1f1f1;">
            <td width="6%">选中</td>
            <td width="6%">已读</td>
            <td width="16%">发件人</td>
            <td width="44%">短消息主题</td>
            <td width="20%">日期</td>
            <td width="8%">操作</td>
          </tr>
          <form action="#" method="post" id="trashMessages">
          <#list messages as message>
          <tr class="ltrv" style="background-color:<#if message.isRead=0>#f7f7f7;<#else>#fff;</#if>">
            <td width="6%"><input type="checkbox" name="id" value="${message.id}" /></td>
            <td width="6%"><img src="images/m_<#if message.isRead=0>news<#else>olds</#if>.gif" alt="" width="21" height="14" /></td>
            <td width="16%">${message.sender}</td>
            <td align="left" style="padding-left:4px;"><a href="message.action?messageId=${message.id}">${message.title}</a></td>
            <td width="20%">${message.sendTime}</td>
            <td width="8%"><a href="deleteMessage.action?messageId=${message.id}"><img src="images/delete.gif" alt="" width="45" height="18" /></a></td>
          </tr>
          </#list>
          </form>
		  <tr class="ltrv" style="background-color:#ffffff;">
            <td colspan="6">分页</td>
          </tr>		  	  
          <tr class="ltrv">
            <td colspan="6" align="left" style="padding-left:10px;"><input type="checkbox" name="checkbox2" value="checkbox" onClick="if(this.checked){Forms.checkedAllBox('trashMessages');}else{Forms.unCheckedAllBox('trashMessages');}"/>
              选中全部的短消息<input type="button" value="永久删除选中的短消息" name="agree" class="b" onClick="System.batchExecute('myMessages','deleteMoreMessage.action');"/><input type="button" value="永久删除全部短消息" name="agree" class="b" onClick="window.location.href='cleanTrash.action'"/></td>
          </tr>		  
        </table>
      </div>
      <!-- explain begin -->
    </div>
  </div>
  <!-- end my message -->
<#include "includes/footer.ftl">  
</body>
</html>
