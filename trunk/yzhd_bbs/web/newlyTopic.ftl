<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#assign isNewLy = request.getParameter("isNewLy")?if_exists>
<title><#if isNewLy == "1">最新帖子<#else>最新回复</#if></title>

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
    
<img src="images/icon3.gif">
总在线<span class="f-red2">7795</span>人，有<span class="f-red2">1124</span>位会员；本版<span class="f-red2">210</span>人在线，会员<span class="f-red2">151</span>人
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
    
    <div class="new1"><img src="images/icon4.gif"> <span class="f-red1">本版公告：</span><a href="#">·未准妈妈·版规·发帖必读·</a></div>
    
    <div class="biaoge">
    <ul>
    
		
		 <#list topics as topic>
		 
				<li class="state"><img src="images/icon5.gif" title="总固顶贴子" width="16" height="16"></li>
				<li class="welcome"><span>${topic.replies?int}</span> / <span>${topic.views}</span></li>
				
				<li class="subject">
				<a anyid="0" target="_blank" href="topic.action?topicId=${topic.id}">				
				${topic.title}				
				</a>
				</li>
				
				<li class="author">
					<a href="user.action?username=${URLEncoder.encode(topic.username?if_exists)}" rel="external">${topic.username?if_exists}</a>
				</li>
				
				<li class="time">${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")}</li>
				
				<li class="name"><a href="#"> ${topic.lastPostUser} </a></li>
		
		</#list>
		
	</ul>
	</div><div class="ftr"></div>
</div>


<div class="ather">
<div class="con1">
<div class="tit">最新话题 <a href="#"></a> </div>
<div class="info">
<ul>
<table id="uctlTopic_TopNewTopic1_rptHotTopic" style="border-width: 0px; width: 100%; border-collapse: collapse;" border="0" cellspacing="0">
	<tbody><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	得到一张免费的试镜卡和早教试听
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	求婷美收腹带
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	通告：沈阳红孩子会员六群开通，
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	为什么论坛上所有照片我都看不到
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	实拍街头夫妻大战 现在的女人不能
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	转让9.29号鸟巢参观门票 ,门票价
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	转全新闲置（半岁之前夹棉衣）
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	O(∩_∩)O哈哈~ 又有乐扣拿了。。
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	维E不可多吃？
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	谁知道胶水多少钱一斤？
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	亲嘴！
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	十一期间商场的打折活动，新世界
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	北京10月11日起错峰上下班，好事
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	没收到小雨点定时器的妈妈，注意
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	有多少老公不记得老婆的生日，一
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	天津团购儿童摄影（大自然原版人
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	#####关于雅培最新消息0927#####
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	■■收到欧莱雅的厚礼啦！有PP~~
	
	</a>
	</li>
    </td>
	</tr><tr>
		<td>
	<li>
	<a class="black_1" href="#">
	
	火车票都被谁买去了
	
	</a>
	</li>
    </td><td>
	<li>
	<a class="black_1" href="#">
	
	不想再吃农药的请进（低价团美的
	
	</a>
	</li>
    </td>
	</tr>
</tbody></table>
</ul>
</div>
</div>
<div class="con2">
<div class="tit">精华主题<a href="#"></a></div>
<div class="info">
<ul>
    <table id="uctlTopic_TopNewTopic1_rptNewTopic" style="width: 100%; border-collapse: collapse;" border="0" cellspacing="0">
	<tbody><tr>
		<td>
	    <li class="black_1"><a href="#">

    中秋动物园之行，自拍！(新添加片

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    四世同堂的生活

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    三中心奶粉筛查经过

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    多多今天一岁了

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    请各位妈妈们不要过度恐慌！！！

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    我朋友拍的漂亮孕妇照 

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    美女依依两岁了，收祝福来啦！

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    正宗水煮鱼，家庭做法（图文）

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    9月27日我进入围城11周年，什么滋

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    紫玥妈的大胆写真照片

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    简单又好吃的苏打饼干……太好吃

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    一凡得秋季腹泻的全过程

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    颛颛的成长历程

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    长大后。。。二三事

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    我真的很幸福！！！

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    浩然在广东（好多照片哦）

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    产前全面检查一定要戴文胸啊

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    打疫苗发烧不用慌

    </a></li>
    </td>
	</tr><tr>
		<td>
	    <li class="black_1"><a href="#">

    可怕的眼神

    </a></li>
    </td><td>
	    <li class="black_1"><a href="#">

    **我的“十年”～～锡婚庆**

    </a></li>
    </td>
	</tr>
</tbody></table>
</ul>
</div>
</div>
</div>

</div>
</div>


<input name="SaveId" id="SaveId" type="hidden">

<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->




</body>
</html>
