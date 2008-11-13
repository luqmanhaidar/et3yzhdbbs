<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改管理员信息">
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>
  <table cellspacing="0" align="center" class="mtable" id="editAdmin" style="margin-top:16px;">
    <tr>
      <td colspan="2" class="mt">修改管理员信息</td>
    </tr>
    <form action="updateAdmin.action" method="post" id="updateAdmin" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="id" value="${admin.id}"/>
    <input type="hidden" name="adminId" value="${admin.id}"/>
    <input type="hidden" name="username" value="${admin.username}"/>
    <tr>
      <td width="22%" class="mtdt">管理员名称： *</td>
      <td width="78%" class="mtdv"><span class="red"><strong>${admin.username}</strong></span><!-- <input name="username" type="text" class="t" value="${admin.username}" style="width:200px;"/><span class="remark">管理员名不为空,长度为2~12</span>--></td>
    </tr>
    <!-- <tr>
      <td width="22%" class="mtdt">管理员密码： *</td>
      <td width="78%" class="mtdv"><input name="password" type="password" class="t" value="${admin.password}" style="width:200px;"/><span class="remark">密码不为空,长度为6~20</span></td>
    </tr>    
    <tr>
      <td width="22%" class="mtdt">重复管理员密码： *</td>
      <td width="78%" class="mtdv"><input name="repeatPassword" type="password" class="t" value="${admin.username}" style="width:200px;"/><span class="remark">确保两次输入的密码一致</span></td>
    </tr>-->    
    <tr>
      <td class="mtdt">管理权限列表: *</td>
      <td class="mtdv"><!-- tab-pane begin -->
        <div class="tab-pane" id="tabPane1">
          <#assign adminPerArray = admin.permissions?split(",")>
          <#list resources as resource>
          <div class="tab-page" id="tabPage${resource.id}">
            <h2 class="tab">${resource.name}</h2>
            <table cellspacing="0" align="center" class="mtable">
	          <#assign permissions = resource.permissionMap>
              <#assign keys = permissions?keys>
			  <#list keys as key>
	            <tr>
			      <td class="mtdt" style="width:40%;">${permissions[key]}： </td>
			      <td class="mtdv"><input name="permission" type="checkbox" value="${key}"${adminPerArray?seq_contains(key)?string(" checked","")}/></td>
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
      <td colspan="2" class="mtda"><input type="submit" name="Submit" value="更新管理员信息" class="b"/></td>
    </tr>
    </form>
  </table>
</@layout.html>
