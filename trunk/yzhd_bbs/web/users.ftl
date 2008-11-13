<#import "/lib/function.ftl" as fn>
<#assign sort = request.getParameter("sort")?if_exists>
<#assign order = request.getParameter("order")?if_exists>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>会员列表</title>
<#include "includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	function setOrderBy(value){
		$("order").value = value;
	}
	window.onload = function(){
		setOrderBy($("orderRadio").value);
	}
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "会员列表">
  <#include "includes/quick.ftl">  
  <!-- member search bar begin -->
  <div class="box4">
    <form method="post" action="users.action" id="searchForm" method="post">	
      <input type="hidden" name="order" value="asc" id="order"/>
       <div style="text-align:center;">
        	按 <select name="sort" id="sort">
          <option value="username"<#if "username"==sort> selected</#if>>用户名</option>
          <option value="totalPost"<#if "totalPost"==sort> selected</#if>>发贴数</option>
          <option value="registerTime"<#if "registerTime"==sort> selected</#if>>注册日期</option>
          <option value="integral"<#if "integral"==sort> selected</#if>>积分</option>
          <option value="roles"<#if "roles"==sort> selected</#if>>管理头衔</option>
        </select>
         升 序<input type="radio" name="orderRadio" onClick="setOrderBy('asc');"<#if "asc"==order> checked="true"</#if>/>
         降 序<input type="radio" name="orderRadio" onClick="setOrderBy('desc');"<#if "desc"==order> checked="true"</#if>/>
         用户名 : <input type="text" name="username" value="${req.getParameter("username")?if_exists}" class="t"/>
        <input type="hidden" name="start" value="0" id="start"/>
        <input type="submit" name="searchUser" class="b" value="检索" onClick="$('searchForm').submit();"/>
      </div>
    </form>
  </div>
  <!-- 
  	<div style="height:18px; line-height:150%;background:#FF0000;padding:4px;">asdfadfadfs</div> 
  -->
  <!-- end #member search bar -->
  <div class="box1" style="padding:0px;">
    <div>
      <div class="lrbox" style="background-color: #f7f7f7;">
        <div class="d" style="width:15%; border-left:0px;">用户名</div>
        <div class="d" style="width:15%;">注册日期</div>
        <div class="d" style="width:5%;">主题数</div>
        <div class="d" style="width:5%;">帖子数</div>		
        <div class="d" style="width:10%;">管理头衔</div>
        <div class="d" style="width:20%;">最后登陆时间</div>
        <div class="d" style="width:20%;border-right:0px;">最后登陆地点</div>				
      </div>
	</div>
	<#list users as user>
    <div class="lbox1">
      <div class="lrbox">
        <div class="d" style="width:15%; border-left:0px;"><a href="user.action?username=${URLEncoder.encode(user.username)}" title="查看${user.username}的资料">${user.username}</a></div>
        <div class="d" style="width:15%;">${user.registerTime?string("yyyy-MM-dd HH:mm:ss")}</div>
        <div class="d" style="width:5%;">${user.totalTopic}</div>
        <div class="d" style="width:5%;">${user.totalPost}</div>		
        <div class="d" style="width:10%;">${RoleSingleton.getInstance().getRole(user.roles).name}</div>
        <div class="d" style="width:20%;">${user.lastLoginTime?string("yyyy-MM-dd HH:mm:ss")}</div>
        <div class="d" style="width:20%;border-right:0px;text-align:left;">${IPLocation.getLocation(user.lastLoginIp)}</div>				
      </div>
	</div>	
	</#list>		
    <div class="box3" style="border-top: 1px solid #c2c2c5; text-align:right;clear:both">
    	<@fn.pager pagination=pagination/>
    </div>
  </div>
  <!-- end #member list -->
<#include "includes/footer.ftl">   
</body>
</html>
