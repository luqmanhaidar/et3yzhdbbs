<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>${application["seo"]["title"]} — 论坛首页</title>
<#include "includes/head.ftl">

<style type="text/css">
	#amarea{ width:16px; float:left; font-weight:bold; font-size:13px; background-color:#f8f8f8; height:64px; layout-flow : vertical-ideographic 
;text-align:center; vertical-align : middle;border-left: 1px solid silver;border-right: 1px solid silver;}
	#am {	text-align: left; margin-left: 4px;	margin-bottom: 0px; }
	#am ul { margin-top: 4px;	margin-left: 10px;	margin-bottom: 0px;	padding-top: 4px; padding-left: 10px; list-style:none; }
	#am li { font-weight: normal;	font-size: 1em; }
	#bbsinfo { margin-left: 4px; margin-bottom: 0px; }
	#bbsinfo ul { margin-top: 4px; margin-left: 0px; margin-bottom: 0px; padding-top: 4px; padding-left: 10px; padding-bottom: 0px;	list-style:none;}
	#bbsinfo li { font-weight: normal; font-size: 1em; }
	#loginarea { background-color: #f9f9f9;padding:0px;margin:0px;border-top: 1px solid #c2c2c5;border-bottom: 1px solid #c2c2c5;height:24px;line-height:150%;padding-top:4px;}
	.linkarea {	margin: 0px; padding: 10px 20px; }
	.textlink {	float: left; width: 16%; height: 20px; text-align: left; }
	.imagelink{	float:left;	width: 16%;	height: 36px; text-align: left; }
</style>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript">
//<![CDATA[
	//window.onload = function(){
//]]>
</script>
</head>
<body>
  <#include "includes/header.ftl">
  <!-- enter begin -->
  <div class="box1">
    <div class="title"> 论坛入口 </div>
  </div>
  <div class="box2" style="height:64px;">
    <!-- announcements begin社区 -->
    <div class="ftr" style="width:320px;">
      <div id="amarea"> 公 告 </div>
      <div id="am">
        <ul id="announces"></ul>
      </div>
    </div>
    <!-- end #announcements -->
    <!-- bbsinfo begin -->
    <div class="ftl" id="bbsinfo">
      <ul>
        <li> 会员总数：${indexMap["countUser"]} 人 最新会员：[ <a href="user.action?username=${URLEncoder.encode(newlyUser.username)}" title="查看${newlyUser.username}详细资料">${newlyUser.username}</a> ]</li>
        <li> 主题总数：${indexMap["countTopic"]} 篇 帖子总数：${indexMap["countPost"]} 篇</li>
        <li> <a href="search.action?forumId=0&amp;sort=dateCreated&amp;order=desc&amp;time=0&amp;type=title&amp;keyword=" title="按发表时间降序">查看新贴</a> | <a href="search.action?forumId=0&amp;sort=views&amp;order=desc&amp;time=0&amp;type=title&amp;keyword=" title="按点击量降序">热门话题</a> | <a href="users.action?sort=totalPost&amp;order=desc" title="会员列表(按发贴量降序)">发贴排行</a> | <a href="users.action?sort=username&amp;order=asc" title="会员列表(按注册时间降序)">用户列表</a> </li>
      </ul>
    </div>
    <!-- end #bbsinfo -->
  </div>
  <div class="box2">
    <div class="box3" style="text-align:left;border-top: 1px solid #c2c2c5;border-bottom: 1px solid #c2c2c5;">
      <#if Session["sessionUser"]?exists>
            您的帐号: <a href="user.action?username=${URLEncoder.encode(Session["sessionUser"].username?if_exists)}" title="查看${Session["sessionUser"].username?if_exists}的资料"/>${Session["sessionUser"].username?if_exists}</a> <span class="pl">最后登陆时间: ${Session["sessionUser"].lastLoginTime?datetime?if_exists}</span> <span class="pl">最后登陆地点: ${IPLocation.getLocation(Session["sessionUser"].lastLoginIp?if_exists)}</span> <span class="pl">金钱: ${Session["sessionUser"].money?if_exists}</span>
      <#else>
      <form action="login.action" method="post" id="login" onSubmit="return Validator.validate(this);">
        <div style="padding:0px 10px;" class="fl"><strong>用户登录</strong>  用户名 :
          <input type="text" name="username" class="t" size="12" maxlength="12"/>
          密码 :
          <input type="password" name="password" class="t" size="12" maxlength="12"/>
          验证码  :
          <input type="text" name="vCode" class="t" size="2" maxlength="4"/>
          <img id="validateImg" src="servlet/validate.gif" alt="验证码,看不清楚?请点击刷新验证码" width="64px;" style="cursor:pointer;height:16px;" onclick="this.src='servlet/validate.gif'"/> Cookie  :
          <select name="cookie">
            <option value="0" selected="true">不保存</option>
            <option value="1">保存一天</option>
            <option value="2">保存一月</option>
            <option value="3">保存一年</option>
          </select>
          <input type="hidden" name="action" value="login"/>
          <input class="b" type="submit" name="login" value="登 陆" style="width:4em"/>
        </div>
      </form>
      </#if>
    </div>
  </div>
  <!-- end #enter -->
  <!-- board begin -->
  <#list forums as forum>
  <#if forum.depth==1>
  <div class="box1">
    <div class="title"> ${forum.name} </div>
  </div>
  <#else>
  <#if forum.depth==2>
  <div class="box2" style="height:56px;">
  	<#if forum.lastPostTopic?exists>
	  <#-- topicTitle -->
	  <#assign titleExists = forum.lastPostTopic.title?exists>
	  <#assign topicTitle = forum.lastPostTopic.title?if_exists>
	  <#if titleExists>
	  	<#if topicTitle.length() gt 14>
	  		<#assign topicTitle = topicTitle[0..14]+"...">
	  	</#if>
	  </#if>
    <div class="frtl" style="width: 240px;margin-top: 8px;height: 44px;">
      <div>主题：<a 
			href="topic.action?topicId=${forum.lastPostTopic.id?if_exists}" title="${topicTitle}">${topicTitle}</a></div>
      <div>发贴：<#if forum.lastPostTopic.username=="guest">guest<#else><a href="user.action?username=${URLEncoder.encode(forum.lastPostTopic.username?if_exists)}" title="${forum.lastPostTopic.username?if_exists}">${forum.lastPostTopic.username?if_exists}</a></#if></div>
      <div>日期：${forum.lastPostTopic.lastPostTime?if_exists?datetime}</div>
    </div>
    </#if>
    <div class="ftl" style="border-right: 1px solid silver; width: 70px; height: 56px;text-align: center;"> <img src="skins/default/forum_<#if forum.status==1>new.gif<#elseif forum.status==0>none.gif</#if>" alt="" style="margin-top:18px;"/></div>
    <div class="tl">
      <div style="padding-top:4px;"><span style="padding-left:4px;"><a href="forum.action?forumId=${forum.id}" title="${forum.name}"><strong>${forum.name}</strong></a> <a href="rss/rss-${forum.id}.xml" title="${forum.name}的RSS"><em>(RSS)</em></a></span></div>
      <div style="overflow:hidden;height:34px;line-height:130%;padding:3px 4px 1px;">${forum.description}</div>
    </div>
  </div>
  <div class="box2" style="border-bottom: 1px solid silver;height: 23px;background-color: #f9f9f9;">
    <div class="frtl" style="width: 240px; height: 23px"> <img style="margin-top: 8px" alt="今日发贴数" src="skins/default/forum_today.gif"/> <span class="red"> ${forum.totalTodayPost} </span> <img alt="主题贴数" src="skins/default/forum_topics.gif"/> ${forum.totalTopic} <img alt="发贴总数" src="skins/default/forum_posts.gif"/> ${forum.totalPost} </div>
    <div class="ftl" style="width: 70px;border-right: 1px solid silver;height: 23px;"></div>
    <div class="tl" style="padding-top:4px;padding-left:78px;"> 版主：<#if forum.masters="">暂无版主<#else><#list forum.masters?split(",") as master><a title="查看版主${master}的资料" href="user.action?username=${URLEncoder.encode(master)}" rel="_blank">${master}</a> </#list></#if></div>  
  </div>
  </#if>
  </#if>
  </#list>
  <!-- end #board -->
<script type="text/javascript" src="scripts/system/announcement_0.js"></script>	
<script type="text/javascript">
//<![CDATA[
	// 公告
	$("announces").innerHTML = announcementClass.getAnnouncements();
//]]>
</script>   
  <!-- friend link -->
  <div class="box1">
    <div class="title"> 友情链接 </div>
    <div class="content">
      <script type="text/javascript" src="scripts/system/link.js"></script>
      <div class="linkarea">
      	<script type="text/javascript">
		//<![CDATA[
			linkClass.writeImageLink();
     	//]]>
		</script>	
      </div>
      <div class="cb"></div>
      <div class="linkarea">
        <script type="text/javascript">
		//<![CDATA[
			linkClass.writeTextLink();
     	//]]>
		</script>
      </div>
      <div class="cb"></div>
    </div>
  </div>
  <!-- end #link -->
  <!-- count begin -->
  <div class="box1">
    <div class="title"> 统计信息 </div>
    <div class="content">
      <div style="padding-top:10px;padding-left:20px;">来访信息 : [ 来自：${request.remoteAddr}，${IPLocation.getLocation(request.remoteAddr)} 。
      <script type="text/javascript">
		//<![CDATA[
			function getWindows(ua){
				if(ua.indexOf("NT 5.0")>-1)	return "windows 2000";
				if(ua.indexOf("NT 5.1")>-1)	return "windows XP";
				if(ua.indexOf("NT 5.2")>-1)	return "windows 2003";
				else return "Linux";
			}
			var broswer,os;
			var ua, s, i, version;
			ua = navigator.userAgent;
			//alert(ua);
			s = "MSIE";
			if ((i = ua.indexOf(s)) >= 0) {
				index = i + s.length;
				broswer = "Internet Explorer" + ua.substring(index,index+4);
				os = getWindows(ua);
			}
			s = "Gecko";
			if ((i = ua.indexOf(s)) >= 0) {
				index = ua.lastIndexOf(" ");
				broswer = ua.substring(index+1).replace("/"," ");
				os = getWindows(ua);
			}
			document.write("系统："+os+"，"+broswer);
		//]]>
		</script> ]
      </div>
      <div>
      	<#assign totalUser = OnlineUserSingleton.getInstance().getOnlineUserSize()>
      	<#assign onlineUsers = OnlineUserSingleton.getInstance().getOnlineUser()>
        <ul>
          <li>目前共有 <strong>${totalUser?int}</strong> 位朋友在线 : <strong>${onlineUsers.size()?int}</strong> 位会员和 <strong>${totalUser-onlineUsers.size()?int}</strong> 位游客  [ <span style="color:#ffa34f">论坛管理员</span> ] [ <span style="color:#79a34f">超级版主</span> ] [ <span style="color:#006600">版主</span> ]</li>
          <li>最高在线纪录是 ${OnlineUserSingleton.getInstance().getHighOnlineUserNum()} 人 发生在:${OnlineUserSingleton.getInstance().getHappenTime()}</li>
          <#if onlineUsers.size()?int gt 0>
          <li>注册会员：<#list OnlineUserSingleton.getInstance().getOnlineUser() as onlineUser><a href="user.action?username=${URLEncoder.encode(onlineUser.username)}" title="${onlineUser.username}" style="color:<#if onlineUser.roles=="1">#ffa34f<#elseif onlineUser.roles=="2">#79a34f<#elseif onlineUser.roles=="3">#006600<#else>#000</#if>">${onlineUser.username}</a> </#list> </li>
          </#if>
        </ul>
      </div>
    </div>
  </div>
  <!-- end #count --> 
  <div style="margin: 16px 2% 0px; text-align:center;line-height: 15pt">
  	<img src="skins/default/forum_new.gif" align="absMiddle" alt="有新帖的论坛"/>有新帖的论坛  <img src="skins/default/forum_none.gif" align="absMiddle" alt="没有新帖的论坛"/>没有新帖的论坛 
  </div>
<#include "includes/footer.ftl">
</body>
</html>
