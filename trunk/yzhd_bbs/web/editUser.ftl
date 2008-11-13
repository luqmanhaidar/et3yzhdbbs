<#import "/lib/function.ftl" as fn>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改用户资料</title>
<#include "includes/head.ftl">
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){

	}
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; 修改用户资料">
  <#include "includes/quick.ftl">   
  <br />
  <!-- begin editUser -->
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action" title="我的控制面板">我的控制面板</a></li>
      <li><span>资料修改</span></li>
      <li><a href="editPassword-page.action" title="修改用户密码">密码修改</a></li>
       <li><a href="myTopics.action" title="我的主题">我的主题</a></li>
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><a href="myMessages.action" title="论坛短消息管理">消息管理</a></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>  
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <#if actionMessage?exists>
		<@fn.message content=actionMessage type="success"/>
	  <#else>
      <h1>修改用户资料</h1>
	  <div class="box1" style="margin-left:0px;margin-right:0px;">
	   <form action="editUser.action" method="post" id="editUser" onSubmit="return Validator.validate(this);">
	   <div class="content" style="border-top:0px;">
		  <div class="ibox">
	        <div class="it">用户名: *  </div>
	        <div class="iv">
	          <span class="red"><strong>${user.username}</strong></span>
	        </div>
	      </div>
	      <div class="ibox">
	        <div class="it">电子邮件: *  </div>
	        <div class="iv">
	          <input type="text" name="email" class="t" size="40" value="${user.email}"/>
	          <span class="required">电子邮件不为空,参考格式yntsky@gmail.com</span>
	        </div>
	      </div>
		  <div class="ibox">
	        <div class="it">性别:  </div>
	        <div class="iv">男
	          <input name="sex" type="radio" value="1"<#if user.sex=1> checked="true"</#if>/>
	          女
	          <input name="sex" type="radio" value="2"<#if user.sex=2> checked="true"</#if>/>
	          保密
	          <input name="sex" type="radio" value="0"<#if user.sex=3> checked="true"</#if>/>
	        </div>
	      </div>
	      <div class="ibox">
	        <div class="it">生日:  </div>
	        <div class="iv">
	          <input type="text" name="birthday" size="12" class="t" value="${user.birthday?if_exists}"/>
	          </div>
	      </div>
	      <div class="ibox">
	        <div class="it">主页:  </div>
	        <div class="iv">
	          <input type="text" name="website" class="t" size="50" value="${user.website}"/>
	          <span class="required">参考格式:http://www.ntsky.com</span>
	        </div>
	      </div>
	      <div class="ibox">
	        <div class="it">QQ号码:  </div>
	        <div class="iv">
	          <input type="text" name="imQq" class="t" size="20" value="${user.imQq}"/>
	          <span class="required">只能为整数且5~12位</span>
	        </div>
	      </div>
	      <div class="ibox">
	        <div class="it">MSN:  </div>
	        <div class="iv">
	          <input type="text" name="imMsn" class="t" size="40" value="${user.imMsn}"/>
	        </div>
	      </div>
	      <div class="ibox">
	        <div class="it">ICQ:  </div>
	        <div class="iv">
	          <input type="text" name="imIcq" class="t" size="20" value="${user.imIcq}"/>
	        </div>
	      </div>  
	      <div class="ibox">
	        <div class="it">Yahoo!:  </div>
	        <div class="iv">
	          <input type="text" name="imYahoo" class="t" size="40" value="${user.imYahoo}"/>
	        </div>
	      </div> 
	      <div class="ibox">
	        <div class="it">Gmail:  </div>
	        <div class="iv">
	          <input type="text" name="gmail" class="t" size="40" value="${user.gmail}"/>
	        </div>
	      </div>                  
	      <div class="ibox">
	        <div class="it">来自:  </div>
	        <div class="iv">
	          <input type="text" name="address" class="t" size="60" value="${user.address}"/>
	        </div>
	      </div>
		  <#assign sessionExist = Session["sessionUser"]?exists>
		  <#assign sessionUser = Session["sessionUser"]?if_exists>
		  <div class="ibox" style="height:62px;">
	        <div class="it">头像:  </div>
	        <div class="iv" style="height:54px;">
	        	<input type="hidden" name="face" value="${user.face}" id="hiddenFace"/>
	         	<div style="margin:0px;clear:both;">
	        		<div style="margin:0px;padding:4px;float:left;"><img src="${user.face}" alt="默认头像" id="viewFace"/> </div>
	       			<div style="margin:0px;padding-left:8px;float:left;">
	          			<a href="#" onClick="javascript:Util.openWindow('popup-face.action',700,500);">选择更多头像</a><br/>
	        			<#if RoleSingleton.getInstance().getRole(sessionUser.roles).permissionMap["canCustomFace"]=="1">
	        			<input type="button" name="customFace" value="自定义头像" onClick="Util.openWindow('common/uploadFace.jsp?element=Face',500,240);" class="b" style="margin-top:4px;margin-left:0px;"/> 
	        			</#if>
			        </div>
			      </div>
	        </div>
	      </div>	
		  <#if sessionExist>
		  	<#if RoleSingleton.getInstance().getRole(sessionUser.roles).permissionMap["canUseSignature"]=="1">
		      <div class="ibox" style="height:148px; border-bottom: 1px solid #c2c2c5;">
		        <div class="it">个性签名:  </div>
		        <div class="iv" style="height:140px;">
		          <textarea name="signature" cols="60" rows="6" style="height:134px;">${user.signature?if_exists}</textarea>
		        </div>
		      </div>
		     </#if>
	      </#if>
	      <div class="ibox" style="height:148px; border-bottom: 1px solid #c2c2c5;">
	        <div class="it">自我介绍:  </div>
	        <div class="iv" style="height:140px;">
	          <textarea name="intro" cols="60" rows="6" style="height:134px;">${user.intro?if_exists}</textarea>
	        </div>
	      </div>	      
		</div>
	    <!-- end #content -->
	    <div class="box3" style="text-align:center; clear:both">
	        <div>
			  <input type="submit" value="修改用户资料" name="agree" id="agree" class="b"/>
	          <input type="reset" value="重 填"  class="b"/>
	        </div>
	    </div>
		</form>
	  </div>
	  </#if>
    </div>
  </div>
  <!-- end #editUser -->
<#include "includes/footer.ftl">
</body>
</html>
