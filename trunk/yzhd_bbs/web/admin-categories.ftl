<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>类别管理</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){

	};
	//]]>
</script>
</head>
<body>	
<#include "includes/top.ftl">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 论坛类别管理">
  <#include "includes/quick.ftl">
  <br />
  <div id="tabs">
    <ul>
      <li><a href="masterPanel.action?forumId=${forum.id}" title="控制面板首页">版主控制面板</a></li>
	  <li><a href="admin-editForum.action?forumId=${forum.id}" title="论坛信息管理">论坛信息管理</a></li>
      <li><a href="admin-announces.action?forumId=${forum.id}" title="${forum.name},公告管理">公告管理</a></li>
      <li><a href="admin-topics.action?forumId=${forum.id}" title="帖子管理">帖子管理</a></li>
      <li><span>类别管理</span></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="subtabs">
      <ul>
        <li><a href="admin-createCategory-page.action?forumId=${forum.id}" title="添加类别">添加类别</a></li>
        <li><a href="admin-categories.action?forumId=${forum.id}" title="类别管理">类别管理</a></li>
      </ul>
    </div>
    <div id="tabmainbox">
      <div>
        <h1>类别管理</h1>
        <br />
			<div class="box1" style="margin:0px;">
			  <div style=" background-color: #f7f7f7; height:26px;">
				<div class="lrbox" style="width:220px;">
				  <div class="d" style="width:60px;">次序</div>
				  <div class="d" style="width:120px;">操作</div>
				</div>
				<div class="llbox" style="border-right:0px;">名称</div>
			  </div>
			  <#list categories as category>
			  <div class="lbox1" style="padding:0px;">
				<div class="lrbox" style="width:220px;">
				  <div class="d" style="width:60px;">${category.displayOrder}</div>
				  <div class="d" style="width:120px;">
					<input type="button" name="" class="tb" value="修改" onClick="window.location.href='admin-editCategory.action?categoryId=${category.id}&amp;forumId=${forum.id}';"/>
					<input type="button" name="" class="tb" value="删除" onClick="Util.del('确认删除 [${category.name?js_string}] 吗?','admin-deleteCategory.action?categoryId=${category.id}&amp;forumId=${forum.id}');"/>
				  </div>
				</div>
				<div class="llbox" style="border-right:0px;"><a href="topics.action?forumId=${forum.id}&amp;categoryId=${category.id}" title="${category.name}">${category.name}</a></div>
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