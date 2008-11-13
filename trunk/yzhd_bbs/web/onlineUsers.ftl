<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>在线用户列表</title>
<#include "includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "在线用户列表">
  <#include "includes/quick.ftl">
  <#assign GUEST = "guest">
  <!-- #OnlineUsers list begin -->
  <div class="box1" style="padding:0px;">
    <div>
      <div class="lrbox" style="background-color: #f7f7f7;">
        <div class="d" style="width:15%; border-left:0px;">用户名</div>
        <div class="d" style="width:20%;">登陆时间</div>
        <div class="d" style="width:30%;">登陆地点</div>
        <div class="d" style="width:30%;">所在论坛</div>					
      </div>
	</div>
	<#list onlineUsers as onlineUser>
    <div class="lbox1">
      <div class="lrbox">
        <div class="d" style="width:15%; border-left:0px;"><#if onlineUser.username!=GUEST><a href="user.action?username=${URLEncoder.encode(onlineUser.username)}" title="查看${onlineUser.username}的资料">${onlineUser.username}</a><#else>guest</#if></div>
        <div class="d" style="width:20%;"><#if onlineUser.date?exists>${onlineUser.date?datetime}<#else>——</#if></div>
        <div class="d" style="width:30%;">${IPLocation.getLocation(onlineUser.ipAddress)}</div>
        <div class="d" style="width:30%;border-right:0px;"><#if onlineUser.forum?exists>${onlineUser.forum?if_exists}<#else>——</#if></div>		
      </div>
	</div>	
	</#list>		
    <div class="box3" style="border-top: 1px solid #c2c2c5; text-align:right;clear:both">
    	<@fn.simplePager pagination=pagination />
    </div>
  </div>
  <!-- end #OnlineUsers list -->
<#include "includes/footer.ftl">
</body>
</html>