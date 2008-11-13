<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改类别</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		if(document.getElementById("content")!=null){
			replaceMyTextarea("content","Basic",".");
		}	
	};
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 添加论坛类别">
  <#include "includes/quick.ftl">
  <br />
  <div id="tabs">
    <ul>
      <li><a href="masterPanel.action?forumId=${forum.id}" title="版主控制面板">版主控制面板</a></li>
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
      <#if actionMessage?exists>
		<@html.message content=actionMessage type="success"/>
	  <#else>    
      <div>
        <h1> 修改类别 </h1>
        <br />
        <div class="box1" style="margin:0px;">
          <div class="title"> 修改类别 </div>
          <form action="admin-updateCategory.action" method="post" id="categoryForm" onSubmit="return Validator.validate(this);">
            <input type="hidden" name="forumId" value="${forum.id}"/>
            <input type="hidden" name="id" value="${category.id}"/>
            <div class="content">
              <div class="ibox">
                <div class="it" style="width:20%;">类别名称: *</div>
                <div class="iv">
                  <input name="name" type="text" class="t" value="${category.name}" size="40"/></div>
              </div>
              <div class="ibox">
                <div class="it" style="width:20%;">显示次序: *</div>
                <div class="iv">
                  <input name="displayOrder" type="text" class="t" value="${category.displayOrder}" size="4"/> 值越大,位置越前</div>
              </div>  
            </div>
            <!-- end #content -->
            <div class="box3" style="text-align:center; clear:both">
              <div>
                <input type="submit" value="更新类别" name="agree" class="b"/>
              </div>
            </div>
          </form>
        </div>
      </div>
      </#if>
      <!-- explain begin -->
    </div>
  </div>
<#include "includes/footer.ftl">
</body>
</html>
