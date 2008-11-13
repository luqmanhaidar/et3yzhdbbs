<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的文件</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
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
      <li><a href="editPassword-page.action" title="修改用户密码">密码修改</a></li>
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏夹</a></li>
      <li><a href="myMessages.action" title="论坛短消息管理">消息管理</a></li>
      <li><span>文件管理</span></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <div>
		<h1>文件管理</h1>
		<br />
        <table align="center" cellpadding="0" class="ltable" cellspacing="1" style="margin:0px;width:100%;">
          <tr class="ltrt" style="background-color:#f1f1f1;">
            <td width="6%">选中</td>
            <td>名称</td>
            <td width="20%">日期</td>
            <td width="8%">操作</td>
          </tr>
          <form action="#" method="post" id="attachments">
          <#list attachments as attachment>
          <tr class="ltrv">
            <td width="6%"><input type="checkbox" name="id" value="${attachment.id}" /></td>
            <td style="text-align:left;padding-left:8px;"><a href="attachment.action?attId=${attachment.id}" title="查看${attachment.filename}的详细信息">${attachment.filename}</a></td>
            <td width="20%">${attachment.dateCreated}</td>
            <td width="8%"><a href="javascript:Util.del('确认删除文件[${attachment.filename}]吗?','deleteAttachment.action?attachmentId=${attachment.id}');" title="删除${attachment.filename}"><img src="images/delete.gif" alt="删除${attachment.filename}" width="45" height="18" /></a></td>
          </tr>
          </#list>
          </form>
		  <tr class="ltrv" style="background-color:#ffffff;">
            <td colspan="5" style="text-align:right;"><@fn.simplePager pagination=pagination /></td>
          </tr>		  	  
          <tr class="ltrv">
            <td colspan="5" align="left" style="padding-left:10px;"><input type="checkbox" name="checkbox2" value="checkbox" onClick="if(this.checked){Forms.checkedAllBox('attachments');}else{Forms.unCheckedAllBox('attachments');}"/>
              选中全部的文件<input type="button" value="删除选中的文件" name="agree" class="b" onClick="System.batchExecute('attachments','确认删除选中的文件吗？','deleteMoreAttachment.action');"/></td>
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
