<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改公告</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="scripts/tooltip/tooltip.js"></script>
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
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 添加公告">
  <#include "includes/quick.ftl">
  <br />
  <div id="tabs">
    <ul>
      <li><a href="masterPanel.action?forumId=${forum.id}" title="版主控制面板">版主控制面板</a></li>
	  <li><a href="admin-editForum.action?forumId=${forum.id}" title="论坛信息管理">论坛信息管理</a></li>
      <li><span>公告管理</span></li>
      <li><a href="adminListTopic.action?forumId=${forum.id}" title="帖子管理">帖子管理</a></li>
	  <li><a href="adminListCategory.action?forumId=${forum.id}" title="类别管理">类别管理</a></li>
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
      <#if actionMessage?exists>
		<@fn.message content=actionMessage type="success"/>
	  <#else>    
      <div>
        <h1>修改公告</h1>
        <br />
        <div class="box1" style="margin:0px;">
          <div class="title"> 修改公告</div>
          <form action="admin-updateAnnounce.action" method="post" id="annoucement" onSubmit="return Validator.validate(this);">
            <input type="hidden" name="id" value="${announcement.id}"/>
            <input type="hidden" name="forumId" value="${forum.id}"/>
            <div class="content">
              <div class="ibox">
                <div class="it" style="width:20%;">公告标题: *</div>
                <div class="iv">
                  <input name="title" type="text" class="t" value="${announcement.title}" size="60"/></div>
              </div>
              <div class="ibox" style="height:208px;">
                <div class="it" style="width:20%;">公告内容: * </div>
                <div class="iv" style="height:200px; width:76%">
                  <textarea name="content" id="content" style="height:200px; width:100%">${announcement.content}</textarea>
                </div>
              </div>
            </div>
            <!-- end #content -->
            <div class="box3" style="text-align:center; clear:both">
              <div>
                <input type="submit" value="更新公告" name="agree" class="b"/>
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
