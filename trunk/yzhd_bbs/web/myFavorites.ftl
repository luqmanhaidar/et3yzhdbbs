<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的收藏夹</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
		var deleteMoreFavorite = function(){
			System.batchExecute("myFavorites","确认删除选中的收藏夹吗？","deleteMoreFavorite.action");
		};
		var clearUserFavorite = function(){
			System.batchExecute("myFavorites","确认清空您收藏夹吗？","clearUserFavorite.action");
		};		
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; 我的收藏夹">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action" title="我的控制面板">我的控制面板</a></li>
      <li><a href="editUser-page.action" title="修改用户资料">资料修改</a></li>
      <li><a href="editPassword-page.action" title="密码修改">密码修改</a></li>
      <li><a href="myTopics.action" title="我的主题">我的主题</a></li>
      <li><span>我的收藏</span></li>
      <li><a href="myMessages.action" title="论坛短消息管理">消息管理</a></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <div>
		<h1>我的收藏夹</h1>
		<br />
        <table align="center" cellpadding="0" class="ltable" cellspacing="1" style="margin:0px;width:100%;">
          <tr class="ltrt" style="background-color:#f1f1f1;">
            <td width="6%">选中</td>
            <td>名称</td>
            <td width="20%">日期</td>
            <td width="8%">操作</td>
          </tr>
          <form action="#" method="post" id="myFavorites">
          <#list favorites as favorite>
          <tr class="ltrv">
            <td width="6%"><input type="checkbox" name="id" value="${favorite.id}" /></td>
            <td style="text-align:left;padding-left:8px;"><a href="${favorite.url}" title="${favorite.name}">${favorite.name}</a></td>
            <td width="20%">${favorite.dateCreated}</td>
            <td width="8%"><a href="javascript:Util.del('确认删除${favorite.name}吗?','deleteFavorite.action?favId=${favorite.id}');" title="删除${favorite.name}"><img src="images/delete.gif" alt="删除${favorite.name}" width="45" height="18" /></a></td>
          </tr>
          </#list>
          </form>
		  <tr class="ltrv" style="background-color:#ffffff;">
            <td colspan="5" style="text-align:right;"><@fn.simplePager pagination=pagination /></td>
          </tr>		  	  
          <tr class="ltrv">
            <td colspan="5" align="left" style="padding-left:10px;"><input type="checkbox" name="checkbox2" value="checkbox" onClick="if(this.checked){Forms.checkedAllBox('myFavorites');}else{Forms.unCheckedAllBox('myFavorites');}"/>
              选中全部的收藏夹<input type="button" value="删除选中的收藏夹" name="agree" class="b" onClick="deleteMoreFavorite();"/><input type="button" value="清空全部收藏夹" name="agree" class="b" onClick="clearUserFavorite();"/></td>
          </tr>		  
        </table>
      </div>
      <!-- explain begin -->
    </div>
  </div>
  <!-- favorites info begin -->
<#include "includes/footer.ftl">  
</body>
</html>
