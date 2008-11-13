<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>控制面板</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "控制面板">
  <#include "includes/quick.ftl">
  <br/>
  <div id="tabs">
    <ul>
      <li><span>我的控制面板</span></li>
      <li><a href="editUser-page.action" title="修改用户资料">资料修改</a></li>
      <li><a href="editPassword-page.action" title="修改用户密码">密码修改</a></li>
      <li><a href="myTopics.action" title="我的主题">我的主题</a></li>
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><a href="myMessages.action" title="论坛短消息管理">消息管理</a></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <div>
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td colspan="3" align="center" valign="top"><table align="center" cellpadding="0" class="ltable" cellspacing="1" style="margin:0px;padding:0px;width:98%;">
                <tr class="ltrt">
                  <td style="background-color:#f6f6f6">基本资料</td>
                </tr>
                <tr class="ltrv" style="background-color:#ffffff;">
                  <td><img src="${Session["sessionUser"].face?if_exists}" alt="${Session["sessionUser"].username?if_exists}的头像" id="h"/>
				  <br />
				  用户名:${Session["sessionUser"].username?if_exists}<br />
				  积分:${Session["sessionUser"].experience?if_exists}<br />
				  等级:${RoleSingleton.getInstance().getRole(Session["sessionUser"].roles).name}<br />
				  </td>
                </tr>
              </table></td>
            <td width="76%" colspan="2" align="center" valign="top"><table align="center" cellpadding="0" class="ltable" cellspacing="1" style="margin:0px;width:98%;">
                <tr class="ltrt">
                  <td width="6%">已读</td>
                  <td width="16%">发件人</td>
                  <td width="44%">短消息主题</td>
                  <td width="20%">日期</td>
                </tr>
                <#list messages as message>
                <tr class="ltrv" style="background-color:<#if message.isRead=0>#f7f7f7;<#else>#fff;</#if>">
	        	    <td width="6%"><img src="images/m_<#if message.isRead=0>news<#else>olds</#if>.gif" alt="" width="21" height="14" /></td>
    	        	<td width="16%">${message.sender}</td>
        	    	<td align="left" style="padding-left:4px;"><a href="message.action?messageId=${message.id}">${message.title}</a></td>
		            <td width="20%">${message.sendTime}</td>
                </tr>
                </#list>
              </table></td>
          </tr>
        </table>
      </div>
      <!-- explain end -->
    </div>
  </div>
  <!-- controlPanel info end -->
<script type="text/javascript">
	//<![CDATA[
	var imgs = document.getElementsByTagName("img");
	for (var i=0; i<imgs.length; i++) {
		if (imgs[i].id == "h" && imgs[i].clientWidth > 120 && imgs[i].clientWidth> imgs[i].clientHeight)
			imgs[i].width = 120;
		if (imgs[i].id == "h" && imgs[i].clientWidth < imgs[i].clientHeight &&imgs[i].clientHeight > 120)
			imgs[i].height = 120;
	}
 	//]]>
</script> 
<#include "includes/footer.ftl">
</body>
</html>
