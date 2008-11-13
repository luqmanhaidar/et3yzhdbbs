<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="用户管理">
<div id="wrap">
<#assign start = req.getParameter("start")?if_exists>
<#assign roles = req.getParameter("roles")?if_exists>
<#if start=="">
	<#assign start = 0>
</#if>
<br/>
<script type="text/javascript">
	//<![CDATA[
	//]]>
  </script>
  <!-- user search begin -->
  <div class="box1">
    <div class="title"> 用户检索 </div>
    <div class="content" style="height:18px;line-height:150%;padding:4px;">
    	<script type="text/javascript">
			//<![CDATA[	
			var search = function(){
			}
			//]]>
		</script>      
      <form action="users.action" method="post" name="searchForm">
	        用户角色:
	       <select name="roles">
	        <option value="-1">全部角色</option>
			<optgroup label="系统用户组">
			<#list managerRoles as role>
	        <option value="${role.id}"<#if role.id?string==roles>selected</#if>>${role.name}</option>
	        </#list>
			<optgroup label="注册用户组">
	        <#list userRoles as role>
	        <option value="${role.id}"<#if role.id?string==roles>selected</#if>>${role.name}</option>
	        </#list>
			<optgroup label="特殊用户组">
	        <#list specialRoles as role>
	        <option value="${role.id}"<#if role.id?string==roles>selected</#if>>${role.name}</option>
	        </#list>	        
          </select> 
	        用户名: *   
          <input name="username" type="text" class="t" size="40" value="${req.getParameter("username")?if_exists}"/>
          <input type="hidden" name="start" value="0" id="start"/>
          <input type="submit" value="查找" name="search" class="b"/>
          <input type="button" value="添加用户" class="b" onClick="location='createUser-page.action'"/>
      </form>
    </div>
  </div>
  <!-- end #user search -->
<div class="box1 dashed" style="clear:both; height:18px; padding:4px; margin-top:8px;">
  <div>
    <div class="lrbox" style="width:500px;margin:0px;line-height:150%;">
      <!-- pager -->
      <@fn.pager pagination=pagination />
    </div>
    <div class="llbox" style="border-right:0px;">
      <input name="selectAll" type="checkbox" value="" class="cb" onClick="System.checkedAll(this,'users');"/>
      <input type="button" value="全删" class="tb" onClick="System.batchExecute('users','您确认删除选中的用户吗？','deleteUser.action');"/>
    </div>
  </div>
</div>  
<div class="box1" style="margin-top:4px;">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:610px;">
      <div class="d" style="width:30px;">性别</div>
	  <div class="d" style="width:60px;">发帖数</div>
	  <div class="d" style="width:60px;">登陆次数</div>
	  <div class="d" style="width:50px;">金钱</div>
      <div class="d" style="width:90px;">最后登陆时间</div>
      <div class="d" style="width:40px;">禁用</div>
      <div class="d" style="width:60px;">论坛之星</div>
      <div class="d" style="width:220px;">操作</div>
    </div>
    <div class="llbox" style="width:40px;"></div>
    <div class="llbox" style="border-right:0px;padding-left:20px;">用户名</div>
  </div>
  <form action="#" method="post" id="users">
  <#list users as user>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:610px; ">
      <div class="d" style="width:30px;"><#if user.sex=1>男<#elseif user.sex=2>女<#else>保密</#if></div>
	   <div class="d" style="width:60px;">${user.totalPost}</div>
	  <div class="d" style="width:60px;">${user.loginTimes}</div>
	  <div class="d" style="width:50px;">${user.money}</div>
      <div class="d" style="width:90px;">${user.lastLoginTime?string("yyyy-MM-dd")}</div>
      <div class="d" style="width:40px;"><#if user.isLock=1><input type="button" name="" class="tb" value="是" onClick="location.href='userManage!isLock.action?userId=${user.id}&amp;isLock=0&amp;start=${start}'"/><#else><input type="button" name="" class="tb" value="否" onClick="location.href='userManage!isLock.action?userId=${user.id}&amp;isLock=1&amp;start=${start}'"/></#if></div>
      <div class="d" style="width:60px;"><#if user.isStar=1><input type="button" name="" class="tb" value="是" onClick="location.href='userManage!isStar.action?userId=${user.id}&amp;isStar=0&amp;start=${start}'"/><#else><input type="button" name="" class="tb" value="否" onClick="location.href='userManage!isStar.action?userId=${user.id}&amp;isStar=1&amp;start=${start}'"/></#if></div>
      <div class="d" style="width:220px;">
	  	<input type="button" name="" class="tb" value="角色设置" onClick="Util.openWindow('userManage!setRole.action?userId=${user.id}',400,200);"/>
        <input type="button" name="" class="tb" value="删除" onClick="location.href='userManage!delete.action?userId=${user.id}&amp;start=${start}'"/>
      </div>
    </div>
    <div class="llbox" style="width:40px;"><input type="checkbox" name="id" value="${user.id}" class="cb"></div>
    <div class="llbox" style="text-align:left;border-right:0px;"><a href="../user.action?username=${URLEncoder.encode(user.username)}" title="查看${user.username}的资料" target="_blank">${user.username}</a></div>
  </div>
  </#list>
  </form>  
</div>
<div class="box1 dashed" style="clear:both; height:22px; padding:4px; margin-top:4px;">
  <div>
    <div class="lrbox" style="width:500px;margin:0px;line-height:150%;">
      <!-- pager -->
      <@fn.pager pagination=pagination />
    </div>
    <div class="llbox" style="border-right:0px;">
      <input name="selectAll" type="checkbox" value="" class="cb" onClick="System.checkedAll(this,'users');"/>
      <input type="button" value="全删" class="tb" onClick="System.batchExecute('users','您确认删除选中的用户吗？','deleteUser.action');"/>
    </div>
  </div>
</div>
 <script type="text/javascript">
	//<![CDATA[
		function moveTo(){
		
		var fid=document.getElementsByName("id").value;
		if(fid.length==0){
		alert(fid);
		}else{
			System.batchExecute('users','您确认删除选中的用户吗？','deleteUser.action?is='+fid');
		}
			
		}
  	//]]>
</script>	
</@layout.html>
