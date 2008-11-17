<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd"><HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>${application["basic"]["bbsName"]}-论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<META content=0pQ+dy/Dy5827YQVNUpbjdHOSOZ8W3mhWELbvwp4sAI= name=verify-v1>
<META content="MSHTML 6.00.6000.16705" name=GENERATOR>

</HEAD>
<BODY>

<DIV><INPUT id=__VIEWSTATE type=hidden 
value=/wEPDwUKMTYzODkyNzA5MGRkC4QPFjibrGVyxelBTfpu8p1qLFQ= name=__VIEWSTATE> 
</DIV>
<DIV id=head>
<#include "includes/header.ftl">

<DIV id=nav>

<#include "includes/front_top.ftl">
<DIV id=page>
<#include "includes/left.ftl">

<DIV class=right>
<DIV id=home>
<DIV class=left1>
<DIV class=nr1>
<DIV class=lgg>
<#include "admid.ftl">
</DIV>
<DIV class=star>
<DIV class=redborder1>
<DIV class=hometit1><IMG src="images/icon12.gif"> 论坛之星</DIV>
<DIV class=homebody>
<TABLE cellSpacing=4 cellPadding=0 width=358 border=0>
  <TBODY>
  <!-- 论坛之星 -->
  <#list stars as star>
  <TR></TR>
  <TR>
    <TD width=100>
      <DIV class=starpic><IMG height=95 
      src="${star.face}" 
      width=95></DIV></TD>
    <TD class=wz1 vAlign=top width=90>
      <DIV><A class=red_4 title="查看${star.username}的资料" href="user.action?username=${URLEncoder.encode(star.username)}" target="_blank">${star.username}</A></DIV>
      
      <DIV><IMG src="images/icon22.gif" align=absMiddle> 
      <A class=blue_2 href="writeMessage-page.action?receiver=${URLEncoder.encode(star.username)}" target="_black">发送信息</A></DIV></TD>
    <TD vAlign=top>
      <DIV class=liststyle1>
      <UL>
      <#list star.indexTopics as topic>
        <LI><A href="topic.action?topicId=${topic.id}" title="${topic.title}" target="_black">${topic.getAutoTitle(10)}</A>
      </LI>
      </#list>
      </UL>      
      </DIV></TD></TR>
  <TR></TR>
  </#list>
<!-- 论坛之星 -->
 </TBODY></TABLE></DIV></DIV></DIV></DIV>
 
<DIV class=homegg style="DISPLAY: none"><IMG 
src="images/homegg1.gif"></DIV>
<DIV class=nr1>
<#assign forsty=1/>
<#list forumsInIndex as forum>
<DIV class=greyborder${forsty}>
<#if forsty==1>
<#assign forsty=2/>
<#else>
<#assign forsty=1/>
</#if>
<DIV class=hometit2><A class=red_4 
href="forum.action?forumId=${forum.id}" target="_black">${forum.name}</A></DIV>
<DIV>
<TABLE cellSpacing=5 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD class=homepic><A class=red_4 
      href="forum.action?forumId=${forum.id}" target="_black">
      <#if forum.signImage?exists&&forum.signImage!="">
      <IMG height=85 src="${forum.signImage?if_exists}" width=100>
      <#else>
      <IMG height=85 src="images/none.jpg" width=100>
      </#if>
      
      </A></TD>
    <TD vAlign=top>
      <DIV class=liststyle2><UL><#list forum.indexTopic as topic> <LI><A href="topic.action?topicId=${topic.id}"  title="${topic.title}"  target=_blank>${topic.getAutoTitle(12)} </A></#list></LI></UL></DIV></TD></TR></TBODY></TABLE>
<DIV class=bbsbz><IMG src="images/icon24.gif"> 版主：&nbsp;&nbsp;
<#if forum.masters="">暂无版主<#else><#list forum.masters?split(",") as master><a title="查看版主${master}的资料" href="user.action?username=${URLEncoder.encode(master)}" target="_blank">${master}</a> </#list></#if>
</DIV></DIV></DIV>
</#list>



<DIV style="MARGIN-TOP: 5px"><!--B4--></DIV><!--B4--></DIV></DIV>
<DIV class=right1>

<DIV class=redborder2>
          <DIV class=hometit1><IMG height=14 src="images/icon26.gif" width=14> 热门论坛</DIV>
          <table  class="list01">
            <tbody>
		    <#assign cou=27>
		    <#assign with=75>
		    <#assign pic=40>
            <#list hotForums as hotForum>
             <#assign cou=cou+1>
             <#if pic!=44>
             <#assign pic=pic+1>
             </#if>
             <#assign with=with-6>
              <tr>
                <td class="ch_1"><img width="12" height="11" alt="" src="images/pic${cou}.gif"/></td>
                <td class="ch_2"><a target="_blank" href="forum.action?forumId=${hotForum.id}">${hotForum.name}</a></td>
                <td class="ch_3"><img width="${with}" height="5" alt="" src="images/pic${pic}.gif"/></td>
              </tr>
            </#list>
            </tbody>
          </table>
        </DIV>
        <DIV><IMG height=10 src="images/spear.gif" width=210></DIV>

<DIV class=redborder2>
<DIV class=hometit1><IMG height=14 src="images/icon26.gif" width=14> 
论坛24小时热点排行</DIV>

<DIV class=liststyle3>
<UL>
 <#list application["indexRight"]["dayTopics"] as dayTopic>
  <LI><A class=black_1 title="${dayTopic.title}"		
  href="topic.action?topicId=${dayTopic.id}">
  ${dayTopic.shortTitle} 
  </A> 
  </LI>
 </#list>
</UL></DIV></DIV>
<DIV><IMG height=10 src="images/spear.gif" width=210></DIV>
<DIV class=redborder2>
<DIV class=hometit1><IMG height=16 src="images/icon27.gif" width=14> 
一周热点话题排行</DIV>
<DIV class=liststyle3>
<UL>
  <#list application["indexRight"]["weekTopics"] as weekTopic>
  <LI><A class=black_1 title="${weekTopic.title}"		
  href="topic.action?topicId=${weekTopic.id}">${weekTopic.shortTitle}</A> 
  </LI>
 </#list>
</UL></DIV></DIV>
<DIV><IMG height=10 src="images/spear.gif" width=210></DIV>
<DIV class=redborder2>
<DIV class=hometit1><IMG height=14 src="images/icon28.gif" width=18> 
最新主题</DIV>
<DIV class=liststyle4>
<UL>
  <#list application["indexRight"]["newLyTopics"] as newLyTopic>
  <LI><A class=black_1 title="${newLyTopic.title}"		
  href="topic.action?topicId=${newLyTopic.id}">${newLyTopic.getAutoTitle(12)}</A>
  </LI>
  </#list>
</UL>
 </DIV>
</DIV>
<DIV style="MARGIN-TOP: 5px"><!--B5-->
<#include "includes/adright.ftl">
</DIV><!--B5--></DIV></DIV></DIV></DIV><INPUT id=SaveId 
type=hidden name=SaveId>

 <!--底部开始-->
<#include "includes/bottom.ftl">
<!--底部结束--></BODY></HTML>
