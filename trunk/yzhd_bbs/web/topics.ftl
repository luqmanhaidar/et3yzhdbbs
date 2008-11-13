<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${forum.name} —— 精华贴列表</title>
<#include "includes/head.ftl">
<style type="text/css">
	/*.box2 { padding: 0px; border: 1px solid silver; height:26px; background-color: #f5f5f5;line-height:150%;}
	.box2 { padding: 0px; border: 1px solid silver; height:26px; background-color: #f5f5f5;line-height:150%;}
	.box2 .right { margin-right:4px;  padding:4px 0px; text-align:right; width:280px; height:14px; }
	.box2 .left { margin-left:4px; padding:4px 0px; text-align:left; height:14px; }*/
</style>
<script type="text/javascript">
	//<![CDATA[

	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign GUEST = "guest">	
  <#if categoryId == -1>
	  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" href=\""+forum.name+"\">"+forum.name+"</a> &gt; 精华贴列表">
  <#else>
  	  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" href=\""+forum.name+"\">"+forum.name+"</a> &gt; <a href=\"topics.action?forumId="+forum.id+"&amp;categoryId="+categoryId+"\" title="+category.name+">专题["+category.name+"]</a> &gt; 专题["+category.name+"]的贴子列表">	
  </#if>
  <#include "includes/quick.ftl">
  <!-- master begin -->
  <div class="box5">
    <div class="ftr" style="padding-right:4px;"><a href="topics.action?forumId=${forum.id}&amp;status=1" title="查看本版精华">查看本版精华</a></div>
    <div class="ftl" style="padding-left:4px;">版主 ：<#list forum.masters?split(",") as master><a title="查看版主${master}的资料" href="user.action?username=${URLEncoder.encode(master)}" rel="_blank">${master}</a> </#list></div>
  </div>
  <!-- end #master -->
  <div class="box2" style="margin:16px 2%;">
  	<div class="left"> <a href="createTopic-page.action?forumId=${forum.id}" title="发表新贴"><img alt="发表新贴" src="images/new_post.jpg"/></a><span style="margin-left:8px;"><a href="undo.html" title="发表新投票"><img alt="发表新投票" src="images/new_poll.jpg"/></a></span> </div>
	<div class="right" style="height:22px;"><#if categories.size() != 0><strong>专题 : </strong> <#list categories as category><a href="topics.action?forumId=${forum.id}&amp;categoryId=${category.id}" title="${category.name}">${category.name}</a> </#list></#if></div>
  </div> 
  <!-- forum list begin -->
  <div class="box1 mtq">
    <div style=" background-color: #f7f7f7; height:26px;">
      <div class="lrbox" style="width:490px;">
        <div class="d" style="width:90px;">作者</div>
        <div class="d" style="width:50px;">回复</div>
        <div class="d" style="width:50px;">查看</div>
        <div class="d" style="width:260px;">最后发表</div>
      </div>
      <div class="llbox" style="width:24px;">状态</div>
      <div class="llbox" style="border-right:0px;">主题</div>
    </div>
    <#list topics as topic>
    <div class="lbox1" style="padding:0px;">
      <div class="lrbox" style="width:490px; ">
        <div class="d" style="width:90px;"><#if topic.username!=GUEST><a href="user.action?username=${URLEncoder.encode(topic.username?if_exists)}" rel="external">${topic.username?if_exists}</a><#else>${topic.username?if_exists}</#if></div>
        <div class="d" style="width:50px;">${topic.replies?int}</div>
        <div class="d" style="width:50px;">${topic.views}</div>
        <div class="d" style="width:260px;text-align:left;padding-left:8px;">${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")} | <#if topic.username!=GUEST><a href="user.action?username=${URLEncoder.encode(topic.lastPostUser?if_exists)}" rel="external" title="查看${topic.lastPostUser}的资料">${topic.lastPostUser}</a><#else>guest</#if></div>
      </div>
      <div class="llbox" style="width:24px;">
      <#switch topic.status>
		  <#case 1>
		     <img src="skins/default/topicstauts/good.gif" alt="精华帖,${topic.title}"/>
		     <#break>
		  <#case 2>
		  	<img src="skins/default/topicstauts/lock.gif" alt="被锁定的帖,${topic.title}"/>
		     <#break>
		  <#default>
		     <#if topic.isTop=1>
		        <img src="skins/default/topicstauts/top.gif" alt="置顶帖,${topic.title}"/>
		     <#elseif topic.isVote=1>
		     	<img src="skins/default/topicstauts/poll.gif" alt="投票帖,${topic.title}"/>
		     <#elseif topic.replies?int gt 10>
		     	<img src="skins/default/topicstauts/hot.gif" alt="热门帖,${topic.title}"/>
		     <#else>
		     	<img src="skins/default/topicstauts/basic.gif" alt="普通题,${topic.title}"/>
		     </#if>
      </#switch> 
      </div>
      <div class="llbox" style="text-align:left;border-right:0px;"><a href="topic.action?topicId=${topic.id}" title="${topic.title}">${topic.title}</a><#if DateUtil.isToday(topic.dateCreated?string("yyyy-MM-dd HH:mm:ss")[0..9])><img src="skins/default/topicstauts/new.gif" alt="新帖,${topic.title}"/></#if><@fn.topicPagination topicId=topic.id postNum=topic.replies?int /></div>
    </div>
    </#list>
  </div>
  <div class="box1" style="clear:both;border:0px; height:26px; margin-top:4px;">
    <div class="dotted" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
		<@fn.simplePager pagination=pagination />
    </div>
  </div>
  <!-- end #forum list -->
  <#include "includes/location-order.ftl">
<#include "includes/footer.ftl">
</body>
</html>
