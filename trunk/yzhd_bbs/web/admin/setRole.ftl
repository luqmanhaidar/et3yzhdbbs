<html>
<head>
<title>设置用户角色</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="stylesheet" href="default.css" type="text/css" media="all"/>
</head>
<body style="margin: 0px;padding:0px;">
<#if actionMessage?exists>
<script type="text/javascript">
	//<![CDATA[
	alert("${actionMessage}");
	window.close();
	//]]>
</script>
<#else>
<h2>设置用户角色</h2>
<br/>
<!-- basic info begin -->
<div class="box1">
  <div class="title"> 设置用户角色 </div>
  <form action="userManage!updateRole.action" method="post" onSubmit="if(document.getElementById('roles').value==''){alert('请先选择用户角色');return false;}">
  	<input type="hidden" value="${user.id}" name="userId"/>
    <div class="content">	
      <div class="ibox">
        <div class="it" style="width:35%;">用户名: *</div>
        <div class="iv"><span class="red">${user.username}</span></div>
      </div>
      <div class="ibox">
        <div class="it" style="width:35%;">用户角色: *  </div>
        <div class="iv">
            <select name="roles" id="roles">
              <option value="">选择用户角色</option>
              <optgroup label="管理角色"></optgroup>
              <#list managerRoles as role>
              <#if role.id!=0>
              <option value="${role.id}"<#if user.roles=role.id?string> selected</#if>> —— ${role.name}</option>
              </#if>
              </#list>
              <optgroup label="用户角色"></optgroup>
              <#list userRoles as role>
              <option value="${role.id}"<#if user.roles=role.id?string> selected</#if>> —— ${role.name}</option>
              </#list>              
            </select>
        </div>
      </div>      	
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="更新用户角色" name="update" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
</#if>
</body>
</html>
