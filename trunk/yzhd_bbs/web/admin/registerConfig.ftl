<#import "/lib/layout.ftl" as layout>
<@layout.html title="设定注册信息">
<!-- explain begin -->
<!-- 
<div class="box1 dashed">
  <h2>补充说明</h2>
  <div>
    <ol>
      <li>新用户无法注册这些用户名，多个名字间请用半角逗号 "," 分割，可使用通配符 "*" 如 "*ass*"</li>
      <li>新用户无法注册这些用户名，多个名字间请用半角逗号 "," 分割，可使用通配符 "*" 如 "*ass*"</li>
    </ol>
  </div>
</div>
 -->
<!-- end #explain -->
<!-- basic info begin -->
<div class="box1">
  <div class="title"> 注册设置 </div>
  <form action="registerConfig!set.action" method="post" id="setRegisterForm" onSubmit="return Validator.validate(this,'admin');">
    <div class="content">
      <div class="ibox">
        <div class="it">允许新用户注册: *</div>
        <div class="iv">
          <input name="isOpen" type="radio" value="true" <#if propertyMap["isOpen"]=="true">checked="true"</#if>/>
          是
          <input name="isOpen" type="radio" value="false" <#if propertyMap["isOpen"]=="false">checked="true"</#if>/>
          否 <span class="red">选择“否”将禁止游客注册成为会员，但不影响过去已注册的会员的使用</span> </div>
      </div>
	  <div class="ibox" style="height:142px;">
          <div class="it">关闭注册原因: * </div>
          <div class="iv" style="height:142px; width:68%">
            <textarea name="closeInfo" cols="50" style="height:120px;">${propertyMap["closeInfo"]}</textarea>
            <br/>
            <span class="red">当不允许新用户注册时,填写关闭注册的原因</span> </div>
        </div>	        
      <!--
      <div class="ibox">
        <div class="it">保留字符: * </div>
        <div class="iv">
          <input type="text" name="keywords" class="t" size="40" value="${propertyMap["keywords"]}"/>
          <span class="red">*1</span> </div>
      </div>-->
      <input type="hidden" name="keywords" class="t" size="40" value="${propertyMap["keywords"]}"/>
      <!-- <div class="ibox">
        <div class="it">网站 URL: * </div>
        <div class="iv">
          <input type="text" name="comfirmpassword" class="t" size="40"/>
          <span class="red">网站 URL，将作为链接显示在页面底部</span> </div>
      </div> -->
      <div class="ibox">
        <div class="it">发送欢迎短消息: * </div>
        <div class="iv">
          <input name="isWelcome" type="radio" value="true" <#if propertyMap["isOpen"]=="true">checked="true"</#if>/>
          是
          <input name="isWelcome" type="radio" value="false" <#if propertyMap["isOpen"]=="false">checked="true"</#if>/>
          否 <span class="red">选择“是”将自动向新注册用户发送一条欢迎短消息</span> </div>
      </div>
      <div class="ibox" style="height:222px;">
        <div class="it">短消息内容: * </div>
        <div class="iv" style="height:214px; width:68%">
          <textarea name="welcomeInfo" style="height:214px; width:100%" id="content">${propertyMap["welcomeInfo"]}</textarea>
          <span class="red">系统发送的欢迎短消息的内容</span> </div>
      </div>
      <div class="ibox">
        <div class="it">是否先阅读条款: * </div>
        <div class="iv">
          <input name="isRead" type="radio" value="true" <#if propertyMap["isRead"]=="true">checked="true"</#if>/>
          是
          <input name="isRead" type="radio" value="false" <#if propertyMap["isRead"]=="false">checked="true"</#if>/>
          否 <span class="red">选择“否”将直接跳到会员资料填写页面</span> </div>
      </div>      
      <div class="ibox" style="height:222px;">
        <div class="it">注册许可协议内容: * </div>
        <div class="iv" style="height:214px; width:68%">
          <textarea name="agreement" style="height:214px; width:100%" id="agreement">${propertyMap["agreement"]}</textarea>
          <span class="red">注册许可协议的详细内容</span> </div>
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
		// 内容
		replaceMyTextarea('content') ;
		
		// 注册条款
		replaceMyTextarea('agreement') ;
	}
	//]]>
</script>
</@layout.html>
