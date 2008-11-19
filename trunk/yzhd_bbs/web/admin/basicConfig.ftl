<#import "/lib/layout.ftl" as layout>
<@layout.html title="设定基本资料">
<#--
${stack.findValue("new java.util.Date()").date}
${Session["sessionAdmin"].username}

${statics["java.lang.System"].currentTimeMillis()}
${DateUtil.getDate()}
${DateUtil.getDate()}
${statics["java.lang.System"].currentTimeMillis()}
-->
<!-- basic info begin -->
<div class="box1">
  <div class="title">  基本设置 </div>
  <form action="basicConfig!set.action" method="post" id="setBasicForm" onSubmit="return Validator.validate(this,'admin');">
    <div class="content">
      <div class="ibox">
        <div class="it">论坛名称: *</div>
        <div class="iv">
          <input type="text" name="bbsName" class="t" size="40" value="${propertyMap["bbsName"]}"/>
          <span class="red">论坛名称将显示在导航条和标题中</span> </div>
      </div>
      <div class="ibox">
        <div class="it">论坛URL: *</div>
        <div class="iv">
          <input type="text" name="bbsDomain" class="t" size="40" value="${propertyMap["bbsDomain"]}"/>
          <span class="red">访问论坛的域名</span> </div>
      </div>	  
      <div class="ibox">
        <div class="it">网站名称: * </div>
        <div class="iv">
          <input type="text" name="webName" class="t" size="40" value="${propertyMap["webName"]}"/>
          <span class="red">网站名称，将显示在页面底部的联系方式处</span> </div>
      </div>
      <div class="ibox">
        <div class="it">网站 URL: * </div>
        <div class="iv">
          <input type="text" name="webDomain" class="t" size="40" value="${propertyMap["webDomain"]}"/>
          <span class="red">网站 URL，将作为链接显示在页面底部</span> </div>
      </div>
      <div class="ibox">
        <div class="it">网站 Logo: * </div>
        <div class="iv">
          <input type="text" id="logo" name="logo" class="t" value="${propertyMap["logo"]}" readonly="readonly" />
	          <input type="button" name="" value="上传Logo" class="tb" onClick="javascript:Util.openWindow('../common/system-uploadImage.jsp?action=uploadLogo&element=logo&preview=false',480,240);"/>
	          <!--<input type="button" name="" value="预览" class="tb" onClick="javascript:Util.openWindow('../common/viewImage.jsp?element=logo',1,1);"/>  -->
	          <input type="button" name="" value="删除" class="tb" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=logo&imagePath='+$('logo').value,360,140);"/> 
          <span class="red">网站 Logo</span> 
        </div>
      </div>
      <!-- <div class="ibox"> -->
      <!--  <div class="it">网站 Banner: * </div>-->
        <!-- <div class="iv">-->
          <input type="hidden" name="banner" class="t" value="${propertyMap["banner"]}" disabled="true"/>
	      <!--<input type="button" name="" value="上传Banner" class="tb" onClick="javascript:Util.openWindow('../common/system-uploadImage.jsp?action=uploadLogo&element=banner&preview=false',480,240);"/>-->
	      <!--<input type="button" name="" value="预览" class="tb" onClick="javascript:Util.openWindow('../common/viewImage.jsp?element=banner',1,1);"/> --> 
	      <!--<input type="button" name="" value="删除" class="tb" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=signImage&imagePath='+$('banner').value,360,140);"/> -->
          <!--<span class="red">网站顶部横幅广告</span> </div>-->
      <!-- </div>  -->
      <!-- <div class="ibox"> -->
      <!--   <div class="it">网站 Banner 链接* </div> -->
       <!--  <div class="iv"> -->
          <input type="hidden" name="bannerUrl" class="t" size="40" value="${propertyMap["bannerUrl"]}"/>
       <!--    <span class="red">网站顶部横幅广告对应的链接</span> </div> -->
      <!-- </div> -->	                 
      <div class="ibox">
        <div class="it">论坛管理员邮箱* </div>
        <div class="iv">
          <input type="text" name="masterEmail" class="t" size="40" value="${propertyMap["masterEmail"]}"/>
          <span class="red">管理员邮箱，将作为链接显示在页面底部</span> </div>
      </div>	  
      <div class="ibox">
        <div class="it">论坛关闭: * </div>
        <div class="iv">
          <input name="isClose" type="radio" value="true" <#if propertyMap["isClose"]=="true">checked="true"</#if>/>
          是
          <input name="isClose" type="radio" value="false" <#if propertyMap["isClose"]=="false">checked="true"</#if>/>
          否 <span class="red">暂时将论坛关闭，其他人无法访问，但不影响管理员访问</span> </div>
      </div>
      <div class="ibox" style="height:222px;">
        <div class="it">关闭论坛原因: * </div>
        <div class="iv" style="height:214px; width:68%">
          <textarea name="closeInfo" style="width:100%;height:214px;" id="content">${propertyMap["closeInfo"]}</textarea>
          <span class="red">暂时将论坛关闭，其他人无法访问，但不影响管理员访问</span> </div>
      </div>
      <div class="ibox" style="height:222px;">
        <div class="it">版权信息: * </div>
        <div class="iv" style="height:214px; width:68%">
          <textarea name="copyright" style="width:100%;height:214px;" id="copyright_d">${propertyMap["copyright"]}</textarea>
          <span class="red">网站版权信息</span> </div>
      </div>	  	  	  	  	  
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设 定" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
<script type="text/javascript">
	//<![CDATA[	
	<#if actionMessage?exists>
		alert("${actionMessage?if_exists}");
	</#if>
	window.onload = function(){
		replaceMyTextarea("content");
		// 区分系统copyright，不使用别名Fckeditor执行错误
		replaceMyTextarea("copyright_d");
	}
	//]]>
</script>
</@layout.html>
