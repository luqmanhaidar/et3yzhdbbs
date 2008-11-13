<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>网站关闭信息</title>
<#include "includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		
		top.document.title = "Java开源论坛 : 注册说明条款";
		
	}
	//]]>
</script></head>
<body>
<#include "includes/top.ftl">
  <#assign link = "网站关闭信息">
  <#include "includes/quick.ftl">
  <!-- register info begin -->
  <div class="box1">
    <div class="title"> <a href="#"> 用户注册说明条款 </a></div>
    <div class="content">
      <div style="padding:8px;">注册功能被管理员关闭
      </div>
    </div>
    <div class="box3" style="border-top: 1px solid #c2c2c5; text-align:center">
      <form method="post" action="register2.html">
        <div>
          <input type="submit" value="请认真查看&lt;服务条款和声明&gt;(4 秒后继续)" name="agree" class="b"/>
          <input onclick="history.back(-1)" type="reset" value="我 不 同 意"  class="b"/>
        </div>
      </form>
    </div>
  </div>
  <!-- end #register info -->
<#include "includes/footer.ftl"> 
</body>
</html>
