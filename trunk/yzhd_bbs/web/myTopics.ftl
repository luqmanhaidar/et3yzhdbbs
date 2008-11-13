<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的收藏夹</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; 我发表的主题">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action">我的控制面板</a></li>
      <li><a href="editUser-page.action">资料修改</a></li>
      <li><a href="editPassword-page.action">密码修改</a></li>
      <li><span>我的主题</span></li>
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><a href="myMessages.action" title="消息管理">消息管理</a></li>
      <li><a href="attachments.action" title="文件管理">文件管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <div>
		<h1>我的收藏夹</h1>
		  <div class="box4" style="text-align:center;margin:16px 0%;">
			  <form id="searchForm" action="myTopics.action" method="post">

				  <select name="forumId" id="forumId">
					<option value="0">全部论坛</option>
			      	<#list forums as forum>
			      	<#if forum.depth==1>
					<optgroup label="${forum.name}"> 
					<#else>
					<option value="${forum.id}"><#list 1..(forum.depth-1) as i> —— </#list>${forum.name}</option>
					</#if>
			        </#list>
			      </select>  
			      
			      <span class="bold">主题 ：</span> 
			      <input type="text" name="wd" class="t" size="60" value="${req.getParameter("wd")?if_exists}"/>
			      <input type="submit" value=" 检 索 " name="search" class="b"/>
			      <input type="hidden" name="start" value="0" id="start"/>
				  <span style="padding-left:16px;"></span>	
			  </form>
		  </div>	
         <div class="box1 mtq" style="margin:0px;">
		    <div style=" background-color: #f7f7f7; height:26px;">
		      <div class="lrbox" style="width:490px;">
		        <div class="d" style="width:90px;">作者</div>
		        <div class="d" style="width:50px;">回复</div>
		        <div class="d" style="width:50px;">查看</div>
		        <div class="d" style="width:260px;">最后发表</div>
		      </div>
		      <div class="llbox" style="border-right:0px;">主题</div>
		    </div>
		    <form action="#" method="post" id="topics">
		    <#list topics as topic>
		    <div class="lbox1" style="padding:0px;">
		      <div class="lrbox" style="width:490px; ">
		        <div class="d" style="width:90px;">${topic.username?if_exists}</div>
		        <div class="d" style="width:50px;">${topic.replies?int}</div>
		        <div class="d" style="width:50px;">${topic.views}</div>
		        <div class="d" style="width:260px;text-align:left;padding-left:8px;">${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")} | <a href="user.action?username=${URLEncoder.encode(topic.lastPostUser?if_exists)}" rel="external" title="查看${topic.lastPostUser}的资料">${topic.lastPostUser}</a></div>
		      </div>
		      <div class="llbox" style="text-align:left;border-right:0px;"><a href="topic.action?topicId=${topic.id}" title="${topic.title}">${topic.title}</a><#if DateUtil.isToday(topic.dateCreated?string("yyyy-MM-dd HH:mm:ss")[0..9])><img src="skins/default/topicstauts/new.gif" alt="新帖,${topic.title}"/></#if><@fn.topicPagination topicId=topic.id postNum=topic.replies?int /></div>
		    </div>
		    </#list>
		    </form>
	    </div>
		  <div class="box1" style="clear:both;border:0px; height:26px; margin-top:4px;">
		    <div class="dashed" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
				<@fn.pager pagination=pagination />
		    </div>
		  </div>
  	    
       </div>
      <!-- explain begin -->
    </div>
  </div>
  <!-- favorites info begin -->
<#include "includes/footer.ftl">  
</body>
</html>
