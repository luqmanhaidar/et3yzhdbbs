<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign isNewLy = request.getParameter("isNewLy")?if_exists>
<title><#switch isNewLy><#case "1">最新帖子<#break><#case "0">最新回复<#break><#case "2">精华区<#break><#default>帖子搜索</#switch></title>

<script type="text/javascript" src="scripts/menu/menu.js"></script>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="scripts/menu/menu.css" type="text/css" media="all"/>
<style type="text/css">
	/*.box2 { padding: 0px; border: 1px solid silver; height:26px; background-color: #f5f5f5;line-height:150%;}
	.box2 { padding: 0px; border: 1px solid silver; height:26px; background-color: #f5f5f5;line-height:150%;}
	.box2 .right { margin-right:4px;  padding:4px 0px; text-align:right; width:280px; height:14px; }
	.box2 .left { margin-left:4px; padding:4px 0px; text-align:left; height:14px; }*/
</style>
</head>
<body><body>
<div id="head">
<#include "includes/header.ftl">

<div id="nav"><#include "includes/front_top.ftl"></div>
<!--头部结束-->

<!--中间开始-->
<div id="page">
<#include "includes/left.ftl">

<div class="right">
    <div class="top2">
    <div class="button1">
    
    <ul>
        <li><a href="#"></a>
        </li>
        <li><a href="#"></a></li>
        <li><a href="#"></a></li>
        <li><a href="#"></a></li>
       
    </ul>
    
    </div>
    <div class="state1">
      <#assign totalUser = OnlineUserSingleton.getInstance().getOnlineUserSize()>
    <#assign onlineUsers = OnlineUserSingleton.getInstance().getOnlineUser()>
   
<img src="images/icon3.gif">
总在线<span class="f-red2">${totalUser?int}</span>人，
有<span class="f-red2">${onlineUsers.size()?int}</span>位会员
    </div>
    </div>
    
    <div class="homelist">
    <div class="biaotou">
    <ul>
    <li class="state">状态</li>
    <li class="welcome">回复/人气</li>
    <li class="subject">主 题
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="#"></a>
    </li>
    <li class="author">作 者</li>
    <li class="time">回复日期</li>
    <li class="name">最后回复</li>
    </ul>
    </div>
    
    <div class="biaoge">
    <ul>
    
		
		 <#list topics as topic>
		 
			  <li class="state">				
       			<#switch topic.status>
				  <#case 1>
				     <img src="images/icon10.gif" alt="精华帖,${topic.title}" width="16" height="16"/>
				     <#break>				 
				  <#default>
				     <#if topic.isTop=2>
				        <img src="images/icon5.gif" alt="总固顶帖,${topic.title}" width="16" height="16"/>
				     <#elseif topic.isTop=1>
				     	<img src="images/icon6.gif" alt="固顶帖,${topic.title}" width="16" height="16"/>		
				     <#elseif topic.isVote=1>
				     	<img src="images/1.gif" alt="投票帖,${topic.title}" width="16" height="16"/>				   
				     <#else>
				        <#switch topic.mood>
				        <#case 0>
				        <#break>
				        <#default>
				     	<img src="images/${topic.mood}.gif" alt="普通题,${topic.title}" width="16" height="16"/>
				     	</#switch>
				     </#if>
		      </#switch>
		        </li>
				<li class="welcome"><span>${topic.replies?int}</span> / <span>${topic.views}</span></li>
				
				<li class="subject">
				<a anyid="0" target="_blank" href="topic.action?topicId=${topic.id}">				
				[${topic.category.name}]${topic.title}				
				</a>
				</li>
				
				<li class="author">
					<a href="user.action?username=${URLEncoder.encode(topic.username?if_exists)}" rel="external">${topic.username?if_exists}</a>
				</li>
				
				<li class="time">${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")}</li>
				
				<li class="name"><a href="#"> ${topic.lastPostUser} </a></li>
		
		</#list>
		
	</ul>
	</div><div class="ftr"><@fn.simplePager pagination=pagination /></div>
</div>

<#include "includes/newAndGood.ftl">

</div>
</div>


<input name="SaveId" id="SaveId" type="hidden">

<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->




</body>
</html>
