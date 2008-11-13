<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>取得检索结果</title>
<#include "includes/head.ftl">
<style type="text/css">
	.box2 { padding: 0px; border: 1px solid silver; height:26px; background-color: #f7f7f7;line-height:150%;}
	.box2 .right { margin-right:4px;  padding:4px 0px; text-align:right; width:280px; height:14px; }
	.box2 .left { margin-left:4px; padding:4px 0px; text-align:left; height:14px; }
</style>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"search-page.action\" title=\"检索条件设定\">检索条件设定</a> &gt; 取得检索结果">
  <#include "includes/quick.ftl">
  <#assign GUEST = "guest">
  
  <div class="box4" style="text-align:center;">
  <form id="searchForm" action="search.action" method="post">
      <input name="order" type="hidden" value="${req.getParameter("order")?if_exists}" />
      <input name="type" type="hidden" value="${req.getParameter("type")?if_exists}" />
      <input name="forumId" type="hidden" value="${req.getParameter("forumId")?if_exists}" />
      <input name="time" type="hidden" value="${req.getParameter("time")?if_exists}" />
      <input name="way" type="hidden" value="${req.getParameter("way")?if_exists}" />
      <input name="sort" type="hidden" value="${req.getParameter("sort")?if_exists}" />
      <span class="bold">主题关键字 ：</span> 
      <input type="text" name="keyword" class="t" size="60" value="${req.getParameter("keyword")?if_exists}"/>
      <input type="submit" value=" 检 索 " name="search" class="b"/>
      <input type="hidden" name="start" value="0" id="start"/>
	  <span style="padding-left:16px;"><a href="search-page.action" title="多条件检索" class="bold" style="color:red" target="_blank">条件检索</a></span>	
  </form>

  </div>
  <!-- forum list begin -->
  <div class="box1">
    <div style=" background-color: #f7f7f7; height:26px;">
      <div class="lrbox" style="width:490px;">
        <div class="d" style="width:90px;">作者</div>
        <div class="d" style="width:50px;">回复</div>
        <div class="d" style="width:50px;">查看</div>
        <div class="d" style="width:260px;">最后发表</div>
      </div>
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
      <div class="llbox" style="text-align:left;border-right:0px;"><a href="topic.action?topicId=${topic.id}" title="${topic.title}">${topic.title}</a><#if DateUtil.isToday(topic.dateCreated?string("yyyy-MM-dd HH:mm:ss")[0..9])><img src="skins/default/topicstauts/new.gif" alt="新帖,${topic.title}"/></#if><@fn.topicPagination topicId=topic.id postNum=topic.replies?int /></div>
    </div>
    </#list>     
  </div>
  <div class="box1" style="clear:both;border:0px; height:26px; margin-top:4px;">
    <div class="dashed" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
		<@fn.pager pagination=pagination/>
    </div>
  </div>
  <!-- end #forum list -->
  <!-- location&orderby begin -->
  <div class="box5" style=" margin-top:16px;">
    <script type="text/javascript">
		//<![CDATA[
		function redirectForum(){
			var forumId = document.getElementById("forumId").value;
			if(forumId != ""){
				window.location.href="forum.action?forumId="+forumId;
			}
		}
		//]]>
	</script>
    <div class="ftl" style="padding-left:4px;">跳转论坛 ：
      <select name="forumId" onChange="redirectForum();" id="forumId">
        <option value="">选择论坛</option>
      	<#list forums as forum>
        <option value="<#if forum.depth==1>""<#else>${forum.id}</#if>">${""?left_pad((forum.depth-1)*2,"—")} ${forum.name}</option>
        </#list>
      </select>
    </div>
  </div>
  <!-- end location&orderby -->  
<#include "includes/footer.ftl"> 
</body>
</html>
