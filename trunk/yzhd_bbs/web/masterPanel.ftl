<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>版主管理面板</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 版主管理面板">
  <#include "includes/quick.ftl">
  <br />
  <div id="tabs">
    <ul>
      <li><span>版主控制面板</span></li>
	  <li><a href="admin-editForum.action?id=${forum.id}" title="论坛信息管理">论坛信息管理</a></li>
      <li><a href="admin-announces.action?forumId=${forum.id}" title="${forum.name},公告管理">公告管理</a></li>
      <li><a href="admin-topics.action?forumId=${forum.id}" title="帖子管理">帖子管理</a></li>
	  <li><a href="admin-categories.action?forumId=${forum.id}" title="类别管理">类别管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <div>
		<h1>版主控制面板</h1>
		<br />
        <!-- <table align="center" cellpadding="0" class="ltable" cellspacing="1" style="margin:0px;width:100%;">
          <tr class="ltrt">
            <td width="6%">选中</td>
            <td width="10%">名称</td>
            <td>说明</td>
            <td width="20%">日期</td>
            <td width="8%">操作</td>
          </tr>
          <tr class="ltrv" style="background-color:#f1f1f1;">
            <td width="6%"><input type="checkbox" name="checkbox" value="checkbox" /></td>
            <td width="10%">11</td>
            <td><a href="message.html">tttt</a></td>
            <td width="20%">2006-05-04 00:00:00 </td>
            <td width="8%"><img src="images/delete.gif" alt="" width="45" height="18" /></td>
          </tr>
		  <tr class="ltrv" style="background-color:#ffffff;">
            <td width="6%"><input type="checkbox" name="checkbox2" value="checkbox" /></td>
            <td width="10%">22</td>
            <td>tttt</td>
            <td width="20%">2006-05-04 00:00:00 </td>
            <td width="8%"><img src="images/delete.gif" alt="" width="45" height="18" /></td>
          </tr>		  	  	  
        </table> -->
      </div>
      <!-- explain begin -->
    </div>
  </div>
  <!-- footer begin -->
<#include "includes/footer.ftl">
</body>
</html>
