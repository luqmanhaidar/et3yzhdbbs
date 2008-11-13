<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>论坛板块</title>
<div id="nav">
	<div class="con1">
	 <DIV class=navlink><A href="#" target=_blank>首页</A> <span>→</span> <A href="#" target=_blank>社区</A> <span>→</span> <A href="index.action" class="heng2" style="margin-top:3px;">论坛<span>
        <table>
          <tr>
            <td><ul>
            <#list application["frontTop"]["forums"] as forum>
  			<#if forum.depth==1>
            	<li class="heng2_li">${forum.name}</li>
            <#else>		
  			<#if forum.depth==2>
                <li><A href="forum.action?forumId=${forum.id}" target=_blank>${forum.name}</A> </li>
            </#if>
            </#if>
			</#list>             
              </ul></td>
          </tr>
        </table>
        </span> </a>
         <#if forum?exists>
        <span>→</span>
        <a href="forum.action?forumId=${forum.id}">${forum.name}</a>
        </#if>
         >> 欢迎您 
    		<#if Session["sessionUser"]?exists>
    			[${Session["sessionUser"].username}]
    			<#if noReadMessageNum?exists>
    				 您有<b style="color:red" onClick="window.open('myMessages.action')" style="cursor:hand">${noReadMessageNum}</b>封新消息
    				 <#if noReadMessageNum!=0>
    				 	<bgsound src="images/rank.wav" loop="1">
    			</#if>
    		</#if> 
    	<#else>[guest]</#if> 
        </DIV>
        
     <div class="mail">
     <#if topic?exists>
     <#else>
		<#if forum?exists>
			<a href="createTopic-page.action?forumId=${forum.id?if_exists}"><img src="images/au2.gif" />
			</a>&nbsp;&nbsp;
			<a href="createTopic-page.action?forumId=${forum.id?if_exists}&isVote=1"><img src="images/au5.gif" />
			</a>
		<#else>
			<a href="createTopic-page.action"><img src="images/au2.gif" />
			</a>&nbsp;&nbsp;
			<a href="createTopic-page.action?isVote=1"><img src="images/au5.gif" />
			</a>
		</#if>
	</#if>
		</div>
	
	<!-- 华丽的分隔线 -->
	
		
		
		
	</div>

	<div class="con2">
		<div class="bulletin">
			<img src="images/icon1.gif" align="absmiddle">
			&nbsp;社区公告：<marquee style="width:400px" scrollamount='1' scrolldelay='30' direction="left"
				onmouseover="this.stop()" onmouseout="this.start()" >
				<#list application["frontTop"]["announcements"] as announcement>
				<a href="${announcement.link?if_exists}" target="_black">${announcement.title}</a>&nbsp;&nbsp;
				</#list>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</marquee>
		</div>
		<div class="search">
			<form method="post" id="search" action="search.action" name="BBS_Search">
			   <input type="hidden" name="time" value="0" />
			   <input type="hidden" name="way" value="before" />
			   <input type="hidden" name="sort" value="dateCreated" />
			   <input type="hidden" name="order" value="desc" />
			   <select name="forumId" id="forumId">
					<option value="0">全部</option>         
	        			<#list application["frontTop"]["forums"] as forum>
	        				<#if forum.depth==1>
							<optgroup label="${forum.name}"> 
							<#else>
							<option value="${forum.id}">${forum.name}</option>
							</#if>	
	        			</#list>
               </select>
				&nbsp;&nbsp;
				<select name="type">
					<option value="title">
						主题搜索
					</option>
					<option value="username">
						作 者
					</option>
				</select>
				&nbsp;&nbsp;
				<input name="keyword" value="" size="16" "
					type="text">
				<input id="Butt" name="Butt" value="搜索" 
					type="submit">
			</form>
		</div>
	</div>
</div>