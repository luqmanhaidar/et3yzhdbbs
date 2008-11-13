<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${forum.name} — 帖子管理</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
		function moveTopic(){	
			if(""==document.getElementById("newForumId").value) {
				alert("请先选择论坛");
				return 
			}
			System.batchExecute('topics','确认将选中的主题移到其它论坛吗？','adminManageTopics!move.action?forumId=${forum.id}&newForumId='+document.getElementById("newForumId").value);
		}				
	//]]>
</script>		
</head>
<body>
<#include "includes/top.ftl">
  <#assign GUEST = "guest">
  <#assign sessionExist = Session["sessionUser"]?exists>
  <#assign sessionUser = Session["sessionUser"]?if_exists>

  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 贴子列表">
  <#include "includes/quick.ftl">

  <br />
  <div id="tabs">
    <ul>
      <li><a href="masterPanel.action?forumId=${forum.id}" title="版主控制面板">版主控制面板</a></li>
	  <li><a href="admin-editForum.action?forumId=${forum.id}" title="论坛信息管理">论坛信息管理</a></li>
      <li><a href="admin-announces.action?forumId=${forum.id}" title="${forum.name},公告管理">公告管理</a></li>
      <li><span>帖子管理</span></li>
	  <li><a href="admin-categories.action?forumId=${forum.id}" title="类别管理">类别管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox" >
        <h1>帖子管理</h1>
        <br />
        <div class="box1 mtq" style="margin:0px;">
		    <div style=" background-color: #f7f7f7; height:26px;">
		      <div class="lrbox" style="width:490px;">
		        <div class="d" style="width:90px;">作者</div>
		        <div class="d" style="width:50px;">回复</div>
		        <div class="d" style="width:50px;">查看</div>
		        <div class="d" style="width:260px;">最后发表</div>
		      </div>
		      <div class="llbox" style="width:24px;">选择</div>
		      <div class="llbox" style="border-right:0px;">主题</div>
		    </div>
		    <form action="#" method="post" id="topics">
		    <#list topics as topic>
		    <div class="lbox1" style="padding:0px;">
		      <div class="lrbox" style="width:490px; ">
		        <div class="d" style="width:90px;"><#if topic.username!=GUEST><a href="user.action?username=${URLEncoder.encode(topic.username?if_exists)}" rel="external">${topic.username?if_exists}</a><#else>${topic.username?if_exists}</#if></div>
		        <div class="d" style="width:50px;">${topic.replies?int}</div>
		        <div class="d" style="width:50px;">${topic.views}</div>
		        <div class="d" style="width:260px;text-align:left;padding-left:8px;">${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")} | <#if topic.username!=GUEST><a href="user.action?username=${URLEncoder.encode(topic.lastPostUser?if_exists)}" rel="external" title="查看${topic.lastPostUser}的资料">${topic.lastPostUser}</a><#else>guest</#if></div>
		      </div>
		      <div class="llbox" style="width:24px;"><input type="checkbox" name="id" value="${topic.id}"/></div>
		      <div class="llbox" style="text-align:left;border-right:0px;"><a href="topic.action?topicId=${topic.id}" title="${topic.title}">${topic.title}</a><#if DateUtil.isToday(topic.dateCreated?string("yyyy-MM-dd HH:mm:ss")[0..9])><img src="skins/default/topicstauts/new.gif" alt="新帖,${topic.title}"/></#if><@fn.topicPagination topicId=topic.id postNum=topic.replies?int /></div>
		    </div>
		    </#list>
		    </form>
	    </div>
      	<div class="box5" style="margin:4px 0px;">
      	 	<span style="padding-left:6px;"><input type="checkbox" name="selectAll" value="全选" onClick="System.checkedAll(this,'topics');"/></span>
      	 	<input type="button" name="delete" class="b" value="删 除"  onClick="System.batchExecute('topics','确认将选中的主题丢到垃圾箱吗？','admin-trashTopics.action?forumId=${forum.id}');"/>
      	 	<input type="button" name="delete" class="b" value="推 精" onClick="System.batchExecute('topics','确认设置选中的主题为精华吗？','admin-manageTopics!statusMore.action?forumId=${forum.id}&status=1');"/>
      	 	<input type="button" name="top" class="b" value="置 顶" onClick="System.batchExecute('topics','确认将选中的主题置顶吗？','admin-manageTopics!isTopMore.action?forumId=${forum.id}&is=0');"/>
      	 	<input type="button" name="unTop" class="b" value="取消置顶" onClick="System.batchExecute('topics','确认将选中的主题取消置顶吗？','admin-manageTopics!isTopMore.action?forumId=${forum.id}&is=0');"/>    	 	
      		<select id="newForumId">
				<option value="">选择论坛</option>
		      	<#list forums as forum>
		      	<#if forum.depth==1>
				<optgroup label="${forum.name}"> 
				<#else>
				<option value="${forum.id}"><#list 1..(forum.depth-1) as i> —— </#list>${forum.name}</option>
				</#if>
		        </#list>
		    </select>
      	 	<input type="button" name="top" class="b" value="移动帖子" onClick="moveTopic();"/> 
      	</div> 	 	    
	    <div class="box1" style="clear:both;border:0px; height:26px; margin:4px 0px;">
		    <div class="dashed" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
				<@fn.simplePager pagination=pagination />
		    </div>
	  	</div> 	
      </div>
  </div>
<#include "includes/footer.ftl">  
</body>
</html>
