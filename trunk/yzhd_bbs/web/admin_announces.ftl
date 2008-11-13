<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>公告管理</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	};
	//]]>
</script>
</head>
<body>	
<#include "includes/top.ftl">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 公告管理">
  <#include "includes/quick.ftl">
  <br />
  <div id="tabs">
    <ul>
      <li><a href="masterPanel.action?forumId=${forum.id}" title="版主控制面板">版主控制面板</a></li>
	  <li><a href="admin-editForum.action?forumId=${forum.id}" title="论坛信息管理">论坛信息管理</a></li>
      <li><span>公告管理</span></li>
      <li><a href="admin-topics.action?forumId=${forum.id}" title="帖子管理">帖子管理</a></li>
	  <li><a href="admin-categories.action?forumId=${forum.id}" title="类别管理">类别管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="subtabs">
      <ul>
        <li><a href="admin-createAnnounce-page.action?forumId=${forum.id}">添加公告</a></li>
        <li><a href="admin-announces.action?forumId=${forum.id}">管理公告</a></li>
      </ul>
    </div>
    <div id="tabmainbox">
      <div>
        <h1>公告管理</h1>
        <br />
			<div class="box1" style="margin:0px;">
			  <div style=" background-color: #f7f7f7; height:26px;">
				<div class="lrbox" style="width:120px;">
				  <div class="d" style="width:120px;">操作</div>
				</div>
				<div class="llbox" style="border-right:0px;">标题</div>
			  </div>
			  <#list announcements as announcement>
			  <div class="lbox1" style="padding:0px;">
				<div class="lrbox" style="width:120px;">
				  <div class="d" style="width:120px;">
					<input type="button" name="" class="tb" value="修改" onClick="window.location.href='admin-editAnnounce.action?announcementId=${announcement.id}&amp;forumId=${forum.id}';"/>
					<input type="button" name="" class="tb" value="删除" onClick="Util.del('确认删除 [${announcement.title?js_string}] 吗?','admin-deleteAnnounce.action?announcementId=${announcement.id}&amp;forumId=${forum.id}');"/>
				  </div>
				</div>
				<div class="llbox" style="border-right:0px;"><a href="#" onClick="javascript:Util.openWindow('popup-announcement.action?id=${announcement.id}',420,200);" onMouseOver="ddrivetip('${announcement.content?replace("\"","")?js_string}',300,'${base}/scripts/tooltip/arrow.gif');" onMouseOut="hideddrivetip();">${announcement.title}</a></div>
			  </div>
			</#list>			  
			</div>
      </div>
      <!-- explain begin -->
    </div>
  </div>
<#include "includes/footer.ftl">
</body>
</html>