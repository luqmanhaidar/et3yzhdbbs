<#import "/lib/function.ftl" as fn>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"><HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>我的收藏</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>	
<script type="text/javascript" src="scripts/menu/menu.js"></script>
<script type="text/javascript" src="scripts/menu/menu.js"></script>
<META content=0pQ+dy/Dy5827YQVNUpbjdHOSOZ8W3mhWELbvwp4sAI= name=verify-v1>
<META content="MSHTML 6.00.6000.16705" name=GENERATOR>
<script type="text/javascript">
	//<![CDATA[
	//]]>
</script>
</HEAD>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head><body>
<div id="head">
<#include "includes/header.ftl">

<DIV id=nav>

<#include "includes/front_top.ftl">
<!--头部结束-->

<!--中间开始-->
<DIV id=page>
<DIV class="zx1"><IMG height=100 src="images/zx-p1.jpg" width=275><IMG 
height=100 src="images/zx-p2.jpg" width=215><IMG height=100 
src="images/zx-p3.jpg" width=250><IMG height=100 src="images/zx-p4.jpg" 
width=240></DIV>
 <#assign item=7>
<DIV class="zx2"><#include "includes/user_left.ftl">
<DIV><INPUT id=__EVENTTARGET type=hidden name=__EVENTTARGET> <INPUT 
id=__EVENTARGUMENT type=hidden name=__EVENTARGUMENT> <INPUT id=__VIEWSTATE 
type=hidden 
value=/wEPDwUKMTgyMDgxNjExN2QYAQUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgEFB2J0blNlbmQgcCaJ6iDbSE6lrZLj6ng3BU0cmg== 
name=__VIEWSTATE> </DIV>
<SCRIPT type=text/javascript>
//<![CDATA[
var theForm = document.forms['Form1'];
if (!theForm) {
    theForm = document.Form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
//]]>
</SCRIPT>

<SCRIPT src="images/WebResource.axd" type=text/javascript></SCRIPT>

<SCRIPT src="images/WebResource(1).axd" type=text/javascript></SCRIPT>

<SCRIPT type=text/javascript>
//<![CDATA[
function WebForm_OnSubmit() {
if (typeof(ValidatorOnSubmit) == "function" && ValidatorOnSubmit() == false) return false;
return true;
}
//]]>
</SCRIPT>

					<DIV class=part2>
						<DIV class=con>
							
							<div class="dxlist">
								<ul class="biaotou2">
									<li class="state">
										状态
									</li>
									<li class="welcome">
										回复/人气
									</li>
									<li class="subject">
										主 题
									</li>
									<li class="name">
										最后回复
									</li>
									<li class="time">
										回复日期
									</li>
								</ul>
								<ul class="biaoge2">
								<#list favoriteTopic as topic>
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
									<li class="welcome">
										<span>${topic.replies?int}</span> / <span>${topic.views}</span>
									</li>
									<li class="subject">
										<a anyid="0" target="_blank" href="topic.action?topicId=${topic.id}" title="发贴用户:${UserUtil.getAliasByUsername(topic.lastPostUser)?if_exists} 发贴时间:${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")}">${topic.title} <a href="javascript:Util.del('确认删除${topic.title}吗?','deleteFavorite.action?favId=${topic.favId}');"> <img border="0" title="从收藏夹删除" alt="从收藏夹删除" src="images/del.gif"/> </a>
									</li>
									<li class="name">
										<a title="发贴用户：${UserUtil.getAliasByUsername(topic.lastPostUser)?if_exists}" href="user.action?username=${URLEncoder.encode(topic.lastPostUser?if_exists)}">${UserUtil.getAliasByUsername(topic.lastPostUser)?if_exists}</a>
									</li>
									<li class="time">
										${topic.lastPostTime?string("yyyy-MM-dd HH:mm:ss")}
									</li>
																	</#list>
									</ul>

								

									<div class="ftr"><@fn.simplePager pagination=pagination /></div>
							</div>
							<div class="an1">

							</div>
						</DIV>
						<DIV class=zx3></DIV>
					</DIV>
					<DIV>
						<INPUT id=__EVENTVALIDATION type=hidden
							value=/wEWBgL2zIZ7AsKE/MMNAuj+oZgOAobzyfoCAquavfUJAoXOuvwBc4dFCIyLEKE63XAZNFuN0aidzCA=
							name=__EVENTVALIDATION>
					</DIV>
			</DIV>
		</DIV>

<!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束-->




</body></html>