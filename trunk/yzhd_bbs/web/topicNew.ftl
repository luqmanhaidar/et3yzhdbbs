<#import "/lib/function.ftl" as fn>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0042)http://bbs.redbaby.com.cn/BBS-t754017.html -->
<HTML 
xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>${application["seo"]["title"]}:${forum.name} — ${topic.title}</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<style type="text/css">
	div .box2_h { margin:16px 2% 0px; border: 1px solid silver; height:26px;  background-color: #f8f8f8; line-height: 150%;}
	.box2_h div.right_h { float:right; margin-right:4px;  padding:4px 0px; text-align:right; width:120px; height:14px; }
	.box2_h div.left_h { float:left; margin-left:4px; padding:4px 0px; text-align:left; height:14px; }
  	.box2ul ul{ margin-left: 6px; padding-left: 6px; list-style: none; }
	.box2ul li{ font-weight: normal; font-size: 1em; padding-top: 2px; }
	.imgzoom img {behavior: url("${base}/styles/imgzoom.htc");
  }
</style>


<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>	
<script type="text/javascript" src="scripts/menu/menu.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<link rel="stylesheet" href="scripts/menu/menu.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
	
			<#if warnMessage?exists>
		 	alert("${warnMessage}");
			</#if>	
			replaceMyTextarea("content","Basic",".","250","680");	
		}
	function addFavorite(){
  	    $("favorite").url.value = location.href;
	    $("favorite").name.value = $("topicTitle").innerHTML;
		Forms.submit("favorite","createFavorite.action","HiddenFavorite");
		window.external.AddFavorite(window.location,document.title);
	}
	
	function onCheck(){
	var oEditor = FCKeditorAPI.GetInstance('content') ;
    var oDOM = oEditor.EditorDocument ;

    var iLength ;

    if ( document.all )		// If Internet Explorer.
    {
	    iLength = oDOM.body.innerText.length ;
    }
    else					// If Gecko.
    {
	    var r = oDOM.createRange() ;
	    r.selectNodeContents( oDOM.body ) ;
	    iLength = r.toString().length ;
    }
	if(iLength==0)
	{
		alert("请写上内容");
		return false;
	}
	if(iLength<${propertyMap["minWord"]})
	{
		alert("内容不能小于${propertyMap["minWord"]}个字符！");
		return false;
	}
	if(iLength>${propertyMap["maxWord"]})
	{
		alert("内容不能大于${propertyMap["maxWord"]}个字符！");
		return false;
	}

	}
	//]]>
	
</script>
</HEAD>
<BODY>
<DIV id=head>
  <#include "includes/header.ftl">
  <DIV id=nav>
    <#include "includes/front_top.ftl">
<DIV id=wrap>
  <div id="main">
    <div id="action">
      <ul>
        <li><a href="createTopic-page.action?forumId=${forum.id?if_exists}"><img width="76" height="20" alt="发表话题" src="images/ac001.gif"/></a></li>
        <li><a href="myTopics.action"><img width="76" height="20" alt="我的主题" src="images/au7.gif"/></a></li>
        <li><a href="search.action?forumId=0&sort=lastPostTime&order=desc&time=0&type=title&keyword=&isNewLy=2&status=1"><img width="76" height="20" alt="精华区" src="images/ac003.gif"/></a></li>
        <li><a href="createTopic-page.action?forumId=${forum.id?if_exists}&isVote=1"><img width="76" height="20" alt="发起投票" src="images/ac004.gif"/></a></li>
      </ul>
    </div>
    <!--发贴-->
      <#assign startStr = request.getParameter("start")?if_exists>
	  <#assign start = 0>
	  <#if startStr != "">
	  	<#assign start = startStr?number>
	  </#if>
	  <#assign isSession = Session["sessionUser"]?exists>
	  <#assign sessionUser = Session["sessionUser"]?if_exists>
	  <#if isSession> 
	   <#assign adminRole = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username))>
	   </#if>
	  <#assign GUEST = "guest">
	  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; "+topic.title>
	  <#if topic.isVote == 1>
	 <form action="hidden-vote.action" method="post" target="HiddenPoll" id="pollForm" onSubmit="return isCheckedRadio();">
    <input type="hidden" name="pollId" value="${poll.id}"/>
    <input type="hidden" name="topicId" value="${topic.id}"/>  
     <div id="poll"> 
   <table cellspacing="0" cellpadding="3" border="0" id="vote">
       <tbody>   
    <tr>
    	<td colspan="2">投票标题: ${poll.content}</td>
    </tr>
    <#assign totalVote = 0>
     <#list poll.pollResults as pollResult>
     	 <#assign totalVote = totalVote + pollResult.votes>
     </#list>
     
      <#assign pollNum=1>
     <#list poll.pollResults as pollResult>
    	<tr>
    		<td width="40%" style="padding-left: 6px;">${pollNum}.<input type="radio" name="id" value="${pollResult.id}"/>${pollResult.optionText}</td>
    		<td width="60%"><img src="skins/default/poll/bar${pollResult_index+1}.gif" width="<#if totalVote==0>0%<#else>${((pollResult.votes?int*350)/totalVote)}px</#if>" height="11"> ${pollResult.votes} 票(<#if totalVote==0>0%<#else>${((pollResult.votes?int*100)/totalVote)}%</#if>)</td>
    	</tr>
    	<#assign pollNum=pollNum+1>
    </#list>
    
    	<tr>
    		<td colspan="2">
    			<input type="hidden" value="true" name="IsVote"/>
    			<input type="submit" value="投 票" name="agree"/>
				
			</td>
		</tr>
	
	</tbody>
</table>
</div>
</form>
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
 
  <!-- poll end -->
  </#if>
    <div class="contener">
      <h2>
        <div id="Title">
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
          ${topic.title} </div>
        <span>阅读[${topic.views?int}] 回复[${topic.replies?int}]</span> </h2>
        <#if start!=0>
  			</div>
  		</#if>
      <#assign count=0>
      <#list posts as post>
      <#assign user = post.user>
  <#assign role = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(post.user.username))>
  		
  
      <#if start+post_index==0>
      <div class="uin">
        <div class="uinL">
        <img width="95" height="95" src="${user.face}"/>
          <ul class="smilies">
            <li><img width="100%" src="${base}/skins/default/level/${role.icon}" alt="等级图标"/></li>
          </ul>
        </div>
        <ul class="uinM">
          <li><a href="user.action?username=${URLEncoder.encode(user.username)}" class="red">${user.username}</a></li>
          <li>身  份：${role.name}</li>
          <li>文  章：${user.totalTopic}</li>
          <li>积  分：${user.money}</li>
          <li>注册时间：${user.registerTime?string("yyyy年MM月dd日 HH:mm:ss")}</li>
          
        </ul>
        <div class="uinR">
          <!--<ul class="action2">
            <li class="u01"><a href="createFavorite.action?favId=${topic.id}" target="HiddenFavorite"><span>收藏
              
              </span> </a> </li>
            <#if isSession>  

            <li class="u01"><a title="回复该帖" href="replyTopic-page.action?forumId=${forum.id}&amp;topicId=${topic.id}">回复</a></li>
            <li class="u02"><a target="_blank" title="给该用户发送消息" style="" href="writeMessage-page.action?receiver=${user.username}">留言</a></li>
            <li class="u03"><a title="修改贴子" href="editTopic.action?topicId=${topic.id}&amp;forumId=${forum.id}">修改</a></li>
            <#if adminRole.permissionMap["canDelete"]=="1"||topic.username==sessionUser.username>
            <li class="u05"> <a title="删除" href="admin-trashTopic.action?topicId=${topic.id}&amp;forumId=${topic.forumId}">删除</a> </li>
            </#if>
            <li class="u06"><a title="举报论坛内的虚假信息" target="_blank" style="" href="writeMessage-page.action?receiver=admin">举报</a></li>
            
            </#if>
            <li style="float: right;" class="uright">楼  主</li>
            <br class="clear"/>
          </ul>-->
          <UL class=action2>
            <#if isSession>  
            <#assign adminRole = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username))>
  <LI class=u01><A 
  href="createFavorite.action?favId=${topic.id}" 
  target=HiddenFavorite><SPAN>收藏 </SPAN></A></LI>
  <LI class=u01><A title=回复该帖 
  href="replyTopic-page.action?forumId=${forum.id}&amp;topicId=${topic.id}">回复</A> 
  </LI>
  <LI class=u02><A title=给该用户发送消息 
  href="writeMessage-page.action?receiver=${user.username}" 
  target=_blank>留言</A> </LI>
  <#if adminRole.permissionMap["canEditOther"]=="1" || topic.username==sessionUser.username>
  <LI class=u03><A title=修改贴子 
  href="editTopic.action?topicId=${topic.id}&amp;forumId=${forum.id}">修改</A> 
  </#if>
  </LI>
  <#if adminRole.permissionMap["canDelete"]=="1" || topic.username==sessionUser.username>
  <LI class=u05><A title=删除 
  href="admin-trashTopic.action?topicId=${topic.id}&amp;forumId=${topic.forumId}">删除</A> 
  </LI>
  </#if>
  <LI class=u06><A title=举报论坛内的虚假信息 
  href="writeMessage-page.action?receiver=admin" 
  target=_blank>举报</A> </LI>
  <!--修改部分-->
  <#if adminRole.permissionMap["canTopTopic"]=="1">
  <#if topic.isTop==0>
  <LI class=u07><A title=置顶 class=heng2
  href="admin-manageTopic!isTop.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;is=1" 
  >置顶<table style="width:42px;">
          <tr>
            <td><ul><A title=总置顶 
  href="admin-manageTopic!isTop.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;is=2">总置顶</A></ul>
            </td>
            <tr>
            </table></A></LI><#else><LI class=u07><A title=取消置顶 
  href="admin-manageTopic!isTop.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;is=0" 
  >取消置顶</A> </LI></#if>
  </#if>
  <#if adminRole.permissionMap["canBestTopic"]=="1">
  <#if topic.status==0><LI class=u08><A title=加精 
  href="admin-manageTopic!status.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;status=1" 
  >加精</A> </LI><#else><LI class=u08><A title=取消加精 
  href="admin-manageTopic!status.action?forumId=${topic.forumId}&amp;topicId=${topic.id}&amp;status=0" 
  >取消加精</A> </LI></#if>
  </#if>
  <!--修改结束-->
   </#if>
  <LI class=uright style="FLOAT: right">楼 主 <BR class=clear></LI></UL>
          <ul class="utlie">
          <#list userNewlyTopic as userNt>
            <li>[<a href="forum.action?forumId=${userNt.forumId}"><span class="red">${userNt.forum.name}</span></a>]<a title="发贴时间:${userNt.lastPostTime?string("yyyy-MM-dd HH:mm:ss")}" href="topic.action?topicId=${userNt.id}" target="_blank"> ${userNt.title?if_exists} </a> </li>
          </#list>
          </ul>
          <!-- -->
        </div>
      </div>
      <div class="uMain">
        <div>${post.content}</div>
       
      </div>
      <p class="tab"><span class="nbg01">我的美丽签名</span></p>
      <p class="tabtext">${user.signature?if_exists}</p>
      <div style="PADDING-RIGHT: 10px; FONT-SIZE: 12px; WIDTH: 746px; LINE-HEIGHT: 40px; HEIGHT: 40px; BACKGROUND-COLOR: #d8edf8; TEXT-ALIGN: right"><span class="red">${user.username}</span> 发表于 ${post.dateCreated?string("yyyy年MM月dd日 HH:mm:ss")}</div>
    </div>
    <!---->
     <#include "includes/adDetail.ftl">
    <h3>回复</h3>    
    <#else>
    
    <!--回复01-->
    
    <div class="contener3 conbg">
      <ul class="consub">
        <li class="uname"><a href="user.action?username=${URLEncoder.encode(user.username)}" class="red">${user.username}</a></li>
        <li class="upic"><img width="95" height="95" src="${user.face}"/></li>
        <li>
          <ul class="smilies">
             <li><img width="100%" src="${base}/skins/default/level/${role.icon}" alt="等级图标"/></li>
          </ul>
        </li>
          <li>身  份：${role.name}</li>
          <li>文  章：${user.totalTopic}</li>
          <li>积  分：${user.money}</li>
          <li>注册时间：${user.registerTime?string("yyyy年MM月dd日 HH:mm:ss")}</li>
          
      </ul>
      <div class="context">
        <ul class="action2">
          <li class="lay">${start+post_index}楼</li>
          <li class="time">发表于 ${post.dateCreated?string("yyyy年MM月dd日 HH:mm:ss")}</li>
          <#if isSession>  
          <#assign adminRole = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username))>
          <li class="u01"><a title="回复该帖" href="replyTopic-page.action?forumId=${forum.id}&amp;topicId=${topic.id}">回复</a></li>
          <li class="u02"><a target="_blank" title="给该用户发送消息" style="" href="writeMessage-page.action?receiver=${user.username}">留言</a></li>
           <#if adminRole.permissionMap["canEditPost"]=="1" || post.username==sessionUser.username>
          <li class="u03"><a title="修改贴子" href="editPost.action?postId=${post.id}">修改</a></li>
         	</#if>
         	<#if adminRole.permissionMap["canDelete"]=="1">
            <li class="u05"> <a title="删除" href="deletePost.action?postId=${post.id}&amp;forumId=${post.forumId}&amp;topicId=${topic.id}">删除</a> </li>
            <#else>
            <#if adminRole.permissionMap["canDeleteOwnPost"]=="1" >
            <#if post.username==sessionUser.username>
            <li class="u05"> <a title="删除" href="deletePost.action?postId=${post.id}&amp;forumId=${post.forumId}&amp;topicId=${topic.id}">删除</a> </li>
            </#if>
            </#if>
            </#if>
          <li class="u06"><a title="举报论坛内的虚假信息" target="_blank" style="" href="writeMessage-page.action?receiver=admin">举报</a></li>          
         </#if>
          <li class="uright">第 ${start+post_index} 层</li>
        </ul>
        <div class="contextc">
          <div>${post.content}</div>
        </div>
        <p class="tab"><span class="nbg01">我的美丽签名</span></p>
        <p class="tabtext">${user.signature?if_exists}</p>
      </div>
      <br class="clear"/>
    </div>
    </#if>
    <#assign count=count+1>
    </#list>
    <!--回复02-->
    <@fn.simplePager pagination=pagination/>
    <!--回复03-->
   
    <!--我要回复-->
    <form action="createPost.action" method="post" id="post" onSubmit="return onCheck();">
    <h3 class="re01">我有话要说</h3>
    <div class="retext">
      <textarea name="content" id="content" style="height:200px;width:90%;"></textarea>
      
      <p id="edtj">
      <input type="hidden" name="title" value=""/>
                  <input type="hidden" name="forumId" value="${topic.forumId}"/>
                  <input type="hidden" name="topicId" value="${topic.id}"/>
        <input type="image" name="submit" src="images/icon18.gif"/>
      </p>
      <br class="clear"/>
    </div>
  </div>
  </form>
  <DIV id=sub>
    <!--麻辣话题 -->
    <DIV class=subox>
      <H4 class=ic01>麻辣话题</H4>
	<#if pic1?exists>
      <DIV class=subpic><A href="topic.action?topicId=${t1.id?if_exists}" 
target=_blank title="${t1.title?if_exists}"><IMG height=106 
src="${pic1}" width=196></A></DIV>
	</#if>
      <UL>
		<#list hotTopics as hotTopic>
		<LI><A href="topic.action?topicId=${hotTopic.id}" target=_blank title="${hotTopic.title}">${hotTopic.shortTitle}</A></LI>
		</#list>
      </UL>
    </DIV>
    <!--本版推荐 -->
    <DIV class=subox>
      <H4 class=ic01>本版版主推荐帖子</H4>
      <#if pic2?exists>
      <DIV class=subpic><A href="topic.action?topicId=${t2.id?if_exists}" 
target=_blank title="${t2.title?if_exists}"><IMG height=106 
src="${pic2}" width=196> </A></DIV>
	</#if>
      <UL>
		<#list commendeTopics as commendeTopic>
		<LI><A href="topic.action?topicId=${commendeTopic.id}" target=_blank title="${commendeTopic.title}">${commendeTopic.shortTitle}</A></LI>
		</#list>       
      </UL>
    </DIV>
    <!--相关主题开始-->
    <DIV class=subox>
      <H4 class=ic01>相关主题</H4>
      <#if pic3?exists>
      <DIV class=subpic> <A href="topic.action?topicId=${t3.id?if_exists}" 
target=_blank title="${t3.title?if_exists}"><IMG height=106 
src="${pic3}" width=196></A></DIV>
	</#if>
      <UL>
      <#list interfixTopics as itopic>
        <LI><A title="${itopic.title}" 
  href="topic.action?topicId=${itopic.id}" 
  target=_blank>${itopic.shortTitle}</A>
		
		</#list>
</LI>
      </UL>
    </DIV>
    <!--楼主最近查看的帖子-->
    <!--大家正在看的主题-->
    <DIV class=subox>
      <H4 class=ic01>大家正在浏览的帖子</H4>
      <#if pic4?exists>
      <DIV class=subpic><A href="topic.action?topicId=${t4.id?if_exists}" 
target=_blank title="${t4.title?if_exists}"><IMG height=106 
src="${pic4}" width=196> </A>
       
      </DIV>
      </#if>
      <UL>
		<#list lastPostTopics as lastPostTopic>
		<LI><A href="topic.action?topicId=${lastPostTopic.id}" target=_blank title="${lastPostTopic.title}">${lastPostTopic.shortTitle}</A></LI>
		</#list>    
      </UL>
    </DIV>
    <DIV class=subox>
      <!--D5-->
<#include "includes/adright.ftl"></DIV>
    <!--D5-->
  </DIV>
  <BR class=clear>
</DIV>
<iframe frameborder="0" id="HiddenFavorite" name="HiddenFavorite" src="about:blank" width="0" height="0"> </iframe>
<iframe frameborder="0" id="HiddenPoll" name="HiddenPoll" src="about:blank" width="0" height="0"> </iframe>
<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->
</BODY>
</HTML>
