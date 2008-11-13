<#import "/lib/layout.ftl" as layout>
<@layout.html title="系统管理员设置">
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>
<script type="text/javascript">
</script>
  <br/>
  <div class="box4">
	  <span class="bold">操作 :</span> <input type="button" value="添加后台管理员" id="btn_createAdmin" name="btn_createAdmin" class="tb" onClick="action_btn('createAdmin','添加后台管理员')"/><input type="button" value="管理员列表" name="list" class="tb" onClick="location.href='#manage'"/>
  </div>

  <div id="createAdmin" style="display:none;">
  <table cellspacing="0" align="center" class="mtable" style="margin-top:16px;">
    <tr>
      <td colspan="2" class="mt">添加管理员</td>
    </tr>
    <form action="createAdmin.action" method="post" id="adminInfo" onSubmit="return Validator.validate(this,'admin');">
    <tr>
      <td class="mtdt">管理员名称： *</td>
      <td class="mtdv"><input name="username" type="text" class="t" value="" style="width:200px;"/><span class="remark">管理员名不为空,长度为2~12</span></td>
    </tr>
    <tr>
      <td class="mtdt">管理员密码： *</td>
      <td class="mtdv"><input name="password" type="password" class="t" value="" style="width:200px;"/><span class="remark">密码不为空,长度为6~20</span></td>
    </tr>    
    <tr>
      <td class="mtdt">重复管理员密码： *</td>
      <td class="mtdv"><input name="repeatPassword" type="password" class="t" value="" style="width:200px;"/><span class="remark">确保两次输入的密码一致</span></td>
    </tr>    
    <tr>
      <td class="mtdt">管理权限列表: *</td>
      <td class="mtdv"><!-- tab-pane begin -->
        <div class="tab-pane" id="tabPane1">
          <#list resources as resource>
          <div class="tab-page" id="tabPage${resource.id}">
            <h2 class="tab">${resource.name}</h2>
            <table cellspacing="0" align="center" class="mtable">
	          <#assign permissions = resource.permissionMap>
              <#assign keys = permissions?keys>
			  <#list keys as key>
	            <tr>
			      <td class="mtdt" style="width:40%;">${permissions[key]}： </td>
			      <td class="mtdv"><input name="permission" type="checkbox" value="${key}"/></td>
			    </tr>
			   </#list>
			</table>
          </div>
          </#list>
        </div>
        <!-- end #tab-pane  -->
      </td>
    </tr>
    <tr>
      <td colspan="2" class="mtda"><input type="submit" name="Submit" value="添加管理员" class="b"/></td>
    </tr>
    </form>
  </table>
  </div>
  <!-- end # create admin -->
  <br/> 
  <a name="#manage"></a>
  <table align="center" cellpadding="0" class="ltable" cellspacing="1">
    <tr class="ltrt">
      <td width="20%">管理员名称</td>
      <td width="35%">最后登陆地址</td>
      <td width="25%">登陆时间</td>
      <td width="20%">操作</td>
    </tr> 
    <#list admins as admin>
    <tr class="ltrv">
      <td width="20%">${admin.username}<#if admin.username=="admin">(<span class="red">网站管理员</span>)</#if></td>
      <td width="35%">${admin.lastLoginIp?if_exists}</td>
      <td width="25%">${admin.lastLoginTime?if_exists}</td>
      <td width="20%">
        <!-- <input type="button" name="" class="tb" value="分配权限" onClick="window.location.href='users.html';"/> -->
        <#if admin.username!="admin"><input type="button" name="assignPermission" class="tb" value="分配权限" onClick="window.location.href='editAdmin.action?adminId=${admin.id}';"/></#if>
        <#if admin.username!="admin"><input type="button" name="delete" class="tb" value="删除" onClick="Util.del('确认删除[${admin.username}]吗？','deleteAdmin.action?adminId=${admin.id}');"/></#if></td>
    </tr>
    </#list>
  </table>
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
	<#if actionMessage?if_exists!="">
  		alert("${actionMessage?if_exists}");
	</#if>
	}
	//]]>
</script>
</@layout.html>
