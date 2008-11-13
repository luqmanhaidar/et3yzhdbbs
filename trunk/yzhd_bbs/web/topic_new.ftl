<#import "/lib/function.ftl" as fn>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"><HTML xmlns="http://www.w3.org/1999/xhtml">
<TITLE>${forum.name} — ${topic.title}</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<META content=0pQ+dy/Dy5827YQVNUpbjdHOSOZ8W3mhWELbvwp4sAI= name=verify-v1>
<META content="MSHTML 6.00.6000.16705" name=GENERATOR>
<head>
<title>${application["seo"]["title"]}:${forum.name} — ${topic.title}</title>
<link rel="stylesheet" href="styles/default.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>	
<script type="text/javascript" src="scripts/menu/menu.js"></script>
<link rel="stylesheet" href="scripts/menu/menu.css" type="text/css" media="all"/>
<style type="text/css">
	div .box2_h { margin:16px 2% 0px; border: 1px solid silver; height:26px;  background-color: #f8f8f8; line-height: 150%;}
	.box2_h div.right_h { float:right; margin-right:4px;  padding:4px 0px; text-align:right; width:120px; height:14px; }
	.box2_h div.left_h { float:left; margin-left:4px; padding:4px 0px; text-align:left; height:14px; }
  	.box2ul ul{ margin-left: 6px; padding-left: 6px; list-style: none; }
	.box2ul li{ font-weight: normal; font-size: 1em; padding-top: 2px; }
	.imgzoom img {behavior: url("${base}/styles/imgzoom.htc");
  }
</style>
<script type="text/javascript">
	//<![CDATA[
	function addFavorite(){
  	    $("favorite").url.value = location.href;
	    $("favorite").name.value = $("topicTitle").innerHTML;
		Forms.submit("favorite","createFavorite.action","HiddenFavorite");
		window.external.AddFavorite(window.location,document.title);
	}
	//]]>
</script>  
</HEAD>
<BODY>


		<DIV id=head>
        <#include "includes/header.ftl">
		</DIV>
		<#include "includes/front_top.ftl">
		<DIV id=wrap>
			<div id="main">
<#assign startStr = request.getParameter("start")?if_exists>
  <#assign start = 0>
  <#if startStr != "">
  	<#assign start = startStr?number>
  </#if>
  <#assign isSession = Session["sessionUser"]?exists>
  <#assign sessionUser = Session["sessionUser"]?if_exists>
  <#assign GUEST = "guest">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; "+topic.title>
  <#include "includes/quick.ftl">
  <#if topic.isVote == 1>
  <script type="text/javascript">
	//<![CDATA[
	function isCheckedRadio(){
  		if( !Forms.isCheckedRadio( "pollForm" ) ) {
			alert("请先选择一个投票");
			return false;
		}
		return true;
	}
	//]]>
  </script> 
  <!-- poll begin -->
  <div class="box1">
    <form action="hidden-vote.action" method="post" target="HiddenPoll" id="pollForm" onSubmit="return isCheckedRadio();">
    <input type="hidden" name="pollId" value="${poll.id}"/>
    <input type="hidden" name="topicId" value="${topic.id}"/>  
    <div class="title"> 投票标题: ${poll.content} </div>
    <div class="content" id="poll">
     <#assign totalVote = 0>
     <#list poll.pollResults as pollResult>
     	 <#assign totalVote = totalVote + pollResult.votes>
     </#list>
     <#list poll.pollResults as pollResult>
     <div class="ibox">
        <div class="it" style="padding:3px 8px;"><input type="radio" name="id" value="${pollResult.id}"/>${pollResult.optionText}</div>
        <div class="iv" style="width:60%;"> <img src="skins/default/poll/bar${pollResult_index+1}.gif" width="<#if totalVote==0>0%<#else>${((pollResult.votes?int*400)/totalVote)}px</#if>" height="11"> ${pollResult.votes} 票(<#if totalVote==0>0%<#else>${((pollResult.votes?int*100)/totalVote)}%</#if>)</div>
      </div>
      </#list>
    </div>
    <div class="box3" style="clear:both">
        <div><input type="submit" value="投 票" name="agree" class="b"/> <span style="padding-left:8px;">[<a href="" title="查看已投票用户">查看已投票用户</a>]</span></div>
    </div>
	</form>
  </div>    
  <!-- poll end -->
  </#if>
  
  <div class="dashed" style="margin:16px 2%; height:18px; padding:4px;">
    <div class="left">
    	<a href="replyTopic-page.action?forumId=${forum.id}&amp;topicId=${topic.id}" title="回复本贴"><img alt="回复本贴" src="images/reply_post.jpg"/></a>
    	<a href="createTopic-page.action?forumId=${forum.id}" title="发表新贴"><img alt="发表新贴" src="images/new_post.jpg"/></a>
    	<a href="createTopic-page.action?forumId=${forum.id}&amp;isVote=1" title="发表新投票"><img alt="发表新投票" src="images/new_poll.jpg"/></a>
    </div>
	<div class="ftr">
		<@fn.simplePager pagination=pagination/>
	</div>
  </div> 
  
  <div class="box5">
    <div class="ftr" style="width:440px; padding:2px 8px;">
    	<span style="margin-right:12px;">（贴子数：<span class="red">${topic.replies?int}</span> | 点击数：<span class="red">${topic.views?int}</span>）</span>
    	<span class="pl"><img src="images/print.gif" alt="打印" align="absmiddle"/><a href="#" onClick="javascript:window.print();" style="color:#030303">打印帖子</a> </span>
    	<span class="pl"><img src="images/sendmail.gif" alt="发送本页" align="absmiddle"/><a href="mailto:?subject=【论坛】${forum.name?replace("&"," ")}帖子：${topic.title}&body=我在【婴知岛论坛】上看到${forum.name?replace("&"," ")}：${topic.title}这篇帖子，特向您推荐http://bbs.ntsky.com/topic.action?topic.action?topicId=${topic.id}" style="color:#030303">发送给好友</a> </span>
    	<span class="pl"><img src="images/favs.gif" alt="收藏本页" align="absmiddle"/><a href="#" onClick="javascript:addFavorite();" style="color:#030303">收藏本页</a> </span></div>
    <div class="ftl" style="font-weight: bold; padding-left:4px;"> <span id="topicTitle">${topic.title}</span> </div>
  </div>
  <#list posts as post>
  <#assign user = post.user>
  <#assign role = RoleSingleton.getInstance().getRole(user.roles)>
  <div class="box2" style="min-height:240px!important; padding: 4px; border-bottom: 1px solid silver;">
    <a name="#${post.id}"></a>
    <div class="ftl" style="position: absolute; width:170px; ">
      <div style="padding-left:4px;" class="box2ul">
        <ul>
          <li><#if user.username!=GUEST><a href="user.action?username=${URLEncoder.encode(user.username)}" title="查看${user.username}的详细信息">${user.username}</a><#else>guest</#if></li>
          <li><#if user.username!=GUEST><img id="h" src="${user.face}" alt="${user.username}的头像"/><#else><img src="images/face/default.gif" alt="匿名用户"/></#if></li>
          <#if user.username!=GUEST>
          <li><img src="${base}/skins/default/level/${role.icon}" alt="等级图标"/></li>
          <li>等级：${role.name}</li>
          <li>主题：${user.totalTopic}</li>
          <li>帖子：${user.totalPost}</li>
          <li>注册时间 : ${user.registerTime?date}</li>
          </#if>
        </ul>
      </div>
    </div>
    <div style="text-align:left; padding-left:170px; margin:0px; padding-top:0px; height:100%; ">
      <div style=" margin:0px; padding-left:4px; border-left:#c2c2c5 1px solid; height:100%; ">
        <!-- head begin -->
        <div style="height:22px; margin:0px; padding:2px; line-height:150%;">
          <#if user.username!=GUEST><div class="ftl"> <span class="pl"><a href="writeMessage-page.action?receiver=${URLEncoder.encode(post.username)}" title="写信给${post.username}"><img src="images/pm.gif" alt="写信给${post.username}"/></a></span><span class="pl"><a href="user.action?username=${URLEncoder.encode(post.username)}" title=""><img src="images/info.gif" alt="信息"/></a></span><#if !((user.website=="") || (user.website=="http://"))><span class="pl"><a href="${user.website}" title="${user.website}"><img src="images/homepage.gif" alt="个人主页"/></a></span></#if><#if user.imQq!=""><span class="pl"><a href="http://search.tencent.com/cgi-bin/friend/user_show_info?ln=${user.imQq}"><img src="images/oicq.gif" alt="QICQ"/></a></span></#if><#if user.gmail!=""><span class="pl"><a href="http://mail.google.com/mail/?view=cm&fs=1&to=${user.gmail}" alt="${user.gmail}" title="发送Mail给${user.username}"><img src="images/gmail.gif" alt="电子邮箱"/></a></span></#if> </div></#if>
          <div style="text-align:right;"> No.<span style="font-weight:bold;color:red">${start+post_index+1}</span> </div>
        </div>
        <!-- end #head -->
        <!-- content begin -->
        <div style="border-top: 1px solid #c2c2c5; border-bottom:1px solid #c2c2c5; margin:0px; padding:4px; min-height:180px; word-wrap : break-word ;word-break : break-all; height: expression(Browser.isIE?'180px':'auto');" class="imgzoom">${post.content}</div>
        <div style="font-size: 12px; margin: 0px; padding:2px; height:14px; line-height:150%; word-wrap : break-word ;word-break : break-all ;">
          <div style="text-align:left;float:left;padding-top:4px;">时间 :${post.dateCreated} <!--<span style="padding-left:20px;">IP : ${IPLocation.getLocation(post.ip)}</span>--> </div>
          <#if isSession>
          <#assign adminRole = RoleSingleton.getInstance().getRole(sessionUser.roles)>
          <script type="text/javascript">
          var menus${post_index} = new Array();
          <#assign point = -1>
          <#if post_index=0>
          	<#if adminRole.permissionMap["canTopTopic"]=="1">
          	menus${post_index}[${point+1}] = "<a href='admin-manageTopic!isTop.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;is=1' title='贴子置顶'>贴子置顶</a>";
          	menus${post_index}[${point+2}] = "<a href='admin-manageTopic!isTop.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;is=0' title='取消置顶'>取消置顶</a>";
          	<#assign point = point+2>
          	</#if>
          	<#if adminRole.permissionMap["canBestTopic"]=="1">
          	menus${post_index}[${point+1}] = "<a href='admin-manageTopic!status.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;status=1' title='设置精华'>设置精华</a>";
			<#assign point = point+1>
          	</#if>
          	<#if adminRole.permissionMap["canLockTopic"]=="1">
          	menus${post_index}[${point+1}] = "<a href='admin-manageTopic!status.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;status=2' title='锁定贴子'>锁定贴子</a>";
          	<#assign point = point+1>
          	</#if>
          	<#if adminRole.permissionMap["canDelete"]=="1">
          	menus${post_index}[${point+1}] = "<a href='admin-trashTopic.action?topicId=${topic.id}&amp;forumId=${topic.forumId}' title='删除主题'>删除主题</a>";
          	</#if>
          <#else>
          	<#if adminRole.permissionMap["canDelete"]=="1">
            menus${post_index}[0] = "<a href='deletePost.action?postId=${post.id}&amp;forumId=${post.forumId}&amp;topicId=${topic.id}' title='删除贴子'>删除贴子</a>";
          	<#assign point = point+1>
          	</#if>
          </#if>
          </script>
          </#if>
          <div style="text-align:right;"><span class="pl"><a href="replyTopic-page.action?forumId=${forum.id}&amp;topicId=${topic.id}" title="回复贴子"><img src="images/reply.gif" alt="回复贴子"/></a></span><span class="pl"><a href="replyTopic-page.action?forumId=${forum.id}&amp;topicId=${topic.id}&amp;qutoeId=${post.id}" alt="引用"><img src="images/quote.gif" alt="引用"/></a></span><span class="pl"><a href="<#if post_index=0>editTopic.action?topicId=${topic.id}&amp;forumId=${forum.id}<#else>editPost.action?postId=${post.id}</#if>" title="编辑${post.title?if_exists}"><img src="images/edit.gif" alt="修改" style="margin-top:4px;"/></a></span><#if isSession><#if point gt -1><span class="pl"><a href="#${post.id}" onMouseover="dropdownmenu(this, event, menus${post_index}, '80px')" onMouseout="delayhidemenu()"><img src="images/topicManage.gif" alt="帖子操作"/></a></span></#if></#if><span style="padding-left:40px;"><a href="#" title="向上"><img src="images/page_top.gif" alt="向上" style="margin-top:4px;"/></a></span></div>
        </div>
        <!-- end #content -->
      </div>
    </div>
  </div>
  </#list>
<script type="text/javascript">
	//<![CDATA[
	var imgs = document.getElementsByTagName("img");
	for (var i=0; i<imgs.length; i++) {
		if (imgs[i].id == "h" && imgs[i].clientWidth > 120 && imgs[i].clientWidth> imgs[i].clientHeight)
			imgs[i].width = 120;
		if (imgs[i].id == "h" && imgs[i].clientWidth < imgs[i].clientHeight &&imgs[i].clientHeight > 120)
			imgs[i].height = 120;
	}
 	//]]>
</script> 
  <!-- post list begin -->
  <div class="box1" style="clear:both;border:0px; height:26px; margin-top:4px;">
    <div class="dashed" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
		<@fn.simplePager pagination=pagination/>
    </div>
  </div>
  <!-- end #post list -->  
  <!-- reply begin -->
  <div class="box1">
    <div class="title">快速回复</div>
    <div class="content">
      <div class="lbox1" style="height:300px; border-top:0px; ">
        <div class="ftl" style="width:200px;position: absolute;">
          <div class="dashed" style="margin: 12px;">
            <!--<h2>回复说明 : </h2>
            <div style="margin:0px;padding:0px;">
              <ol>
                <li>支持html</li>
                <li>支持图片</li>
              </ol>
            </div>-->
          </div>
        </div>
        <!-- begin llbox -->
        <div style="text-align:left; padding-left:200px;">
          <div class="box1" style="margin-top:12px;">
            <div class="content" style="border-top:0px;">
              <form action="createPost.action" method="post" id="post" onSubmit="return Validator.validate(this);">
              <div class="ibox" style="border-bottom:0px; height:208px;">
                <div class="it" style="width:10%;">内容: * </div>
                <div class="iv" style="height:200px; width:76%">
                  <textarea name="content" id="content" style="height:200px;width:100%;"></textarea>
                </div>
              </div>
              <div class="box3" style="text-align:center; border-top:1px solid silver;">
                <div>
				  <input type="hidden" name="title" value=""/>
                  <input type="hidden" name="forumId" value="${topic.forumId}"/>
                  <input type="hidden" name="topicId" value="${topic.id}"/>
                  <input type="submit" value="回 复" name="agree" class="b"/>
                  <input type="reset" value="重 填"  class="b"/>
                </div>
              </div>
              </form>
            </div>
          </div>
          <!-- end box1 -->
        </div>
        <!-- end #llbox -->
      </div>
      <!-- end /lbox1 -->
    </div>
  </div>
  <!-- end #reply -->
<form id="favorite" method="post" action="" target="HiddenFavorite">
	<input type="hidden" name="url" value=""/>
	<input type="hidden" name="name" value=""/>
</form> 
<script type="text/javascript">
	//<![CDATA[  
	window.onload = function(){
		replaceMyTextarea("content","Basic",".");
	};			
	//]]>
</script> 

			</div>


			<DIV id=sub>
				<!--麻辣话题 -->
				<DIV class=subox>
					<H4 class=ic01>
						麻辣话题
					</H4>
					<DIV class=subpic>
						<A href="#" target=_blank><IMG height=106
								src="images/8135a8b0-ea85-43c7-96c2-50b3624bab4f.jpg" width=196>
						</A>
					</DIV>
					<UL>
					    <#list application["indexRight"]["hotTopics"] as hotTopics>
						<LI><A href="topic.action?topicId=${hotTopics.id}" target=_blank title="${weekTopic.title}">${weekTopic.shortTitle}</A></LI>
						</#list>
					</UL>
				</DIV>
				<!--本版推荐 -->
				<DIV class=subox>
					<H4 class=ic01>
						本版版主推荐帖子
						<A href="#" target=_blank><IMG alt=rss src="images/rss.gif">
						</A>
					</H4>
					<DIV class=subpic>
						<A href="#" target=_blank><IMG
								src="images/5f6d8f01-1837-445a-a0d4-fb0338f45432.jpg"> </A>
					</DIV>
					<UL>
						<LI>
							<A title="发贴时间:2008-9-16 9:16:19" href="#" target=_blank>一次就成功的经验分享给大家</A>
						<LI>
							<A title="发贴时间:2008-6-14 0:44:55" href="#" target=_blank>我的好孕经验</A>
						<LI>
							<A title="发贴时间:2008-5-10 15:35:52" href="#" target=_blank>放松心态---未准妈妈们一定会</A>
						<LI>
							<A title="发贴时间:2008-5-8 20:37:48" href="#" target=_blank>我的怀孕历程</A>
						<LI>
							<A title="发贴时间:2008-5-7 16:07:48" href="#" target=_blank>我是这样当上妈妈的</A>
						<LI>
							<A title="发贴时间:2008-5-6 12:04:38" href="#" target=_blank>教你万试万灵的有“计划”怀</A>
						<LI>
							<A title="发贴时间:2008-4-17 9:31:22" href="#" target=_blank>来这个新版块发第一个贴，祝</A>
						<LI>
							<A title="发贴时间:2008-4-25 15:26:05" href="#" target=_blank>怀孕记</A>
						</LI>
					</UL>
				</DIV>
				<!--相关主题开始-->
				<DIV class=subox>
					<H4 class=ic01>
						相关主题
					</H4>
					<DIV class=subpic>
						<A href="#" target=_blank><IMG alt=""
								src="images/adview(2).jpg" border=0>
						</A>
					</DIV>
					<UL>
						<LI>
							<A title="发贴时间:2008-9-2 9:27:51" href="#" target=_blank>体温计用什么样子的</A>
						<LI>
							<A title="发贴时间:2008-3-20 14:05:04" href="#" target=_blank>大家都给宝宝用什么样的体温</A>
						<LI>
							<A title="发贴时间:2008-5-3 20:32:43" href="#" target=_blank>亲都用什么婴儿体温计呵</A>
						<LI>
							<A title="发贴时间:2008-2-5 20:51:21" href="#" target=_blank>急!宝宝用什么体温计好!</A>
						<LI>
							<A title="发贴时间:2008-2-13 10:23:01" href="#" target=_blank>害人的电子体温计</A>
						<LI>
							<A title="发贴时间:2008-3-7 10:48:50" href="#" target=_blank>有必要买电子体温计吗？</A>
						<LI>
							<A title="发贴时间:2008-7-9 15:03:54" href="#" target=_blank>日常用品大甩卖
								理发器 训</A>
						<LI>
							<A title="发贴时间:2008-2-5 13:59:20" href="#" target=_blank>小宝宝用什么样的碗</A>
						<LI>
							<A title="发贴时间:2008-4-30 10:13:15" href="#" target=_blank>宝宝用什么样的牙刷</A>
						<LI>
							<A title="发贴时间:2008-3-1 22:09:43" href="#" target=_blank>想给女儿买个坐便器,什么样的</A>
						</LI>
					</UL>
				</DIV>
				<!--楼主最近查看的帖子-->
				<!--大家正在看的主题-->
				<DIV class=subox>
					<H4 class=ic01>
						大家正在浏览的帖子
					</H4>
					<DIV class=subpic>
						<A href="#" target=_blank><IMG
								src="images/3bc18dde-adf1-4285-ac0f-699a55fa8aa2.jpg"> </A>
						<DIV class=subtitle>
							<A href="#"></A>
						</DIV>
					</DIV>
					<UL>
						<LI>
							<A title="发贴时间:2008-7-29 14:12:44" href="#" target=_blank>小蚂蚁/小彩虹品牌秋衣套*三</A>
						<LI>
							<A title="发贴时间:2008-9-28 10:05:37" href="#" target=_blank>宝宝不善与人交流</A>
						<LI>
							<A title="发贴时间:2007-11-19 20:18:48" href="#" target=_blank>这次,我没有再忍婆婆~~~</A>
						<LI>
							<A title="发贴时间:2008-9-26 10:12:33" href="#" target=_blank>好运气接二连三
								又接个获奖电</A>
						<LI>
							<A title="发贴时间:2008-9-4 15:39:14" href="#" target=_blank>求:油票.可以用我的木耳交换</A>
						<LI>
							<A title="发贴时间:2008-9-27 22:04:14" href="#" target=_blank>2008年10月份《瑞丽家居设计</A>
						<LI>
							<A title="发贴时间:2008-7-9 16:47:59" href="#" target=_blank>买外贸玩具的好地儿～便宜又</A>
						<LI>
							<A title="发贴时间:2007-7-16 9:44:13" href="#" target=_blank>动物园省钱大法---二则！！！</A>
						<LI>
							<A title="发贴时间:2008-9-20 4:33:23" href="#" target=_blank>禹儿妈惊爆价处理全新童装,剩</A>
						<LI>
							<A title="发贴时间:2008-9-27 17:43:35" href="#" target=_blank>微软的环保购物袋到了</A>
						</LI>
					</UL>
				</DIV>
				<DIV class=subox>
					<!--D5-->
					<A href="#" target=_blank><IMG alt=""
							src="images/adview(3).jpg" border=0>
					</A>
				</DIV>
				<!--D5-->
			</DIV>
			<BR class=clear>
		</DIV>

		<!--底部开始-->
	<#include "includes/bottom.ftl">
		<!--底部结束-->
	</BODY>
</HTML>
