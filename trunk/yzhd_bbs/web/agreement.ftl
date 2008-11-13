<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>论坛 : 注册说明条款</title>
<#include "includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		top.document.title = "Java开源论坛 : 注册说明条款";
	}
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "注册说明条款">
  <#include "includes/quick.ftl">
  <!-- register info begin -->
  <div class="box1">
    <div class="title"> <a href="#"> 用户注册说明条款 </a></div>
    <div class="content">
      <div style="padding:8px;"> <b>注册前请仔细阅读，您只有无条件接受以下所有服务条款，才能继续申请：</b><br/>
        <br/>
        ${infoMessage}
      </div>
    </div>
    <div class="box3" style="border-top: 1px solid #c2c2c5; text-align:center">
      <form method="post" action="signup-page.action?isRead=true">
        <div>
          <input type="submit" value="请认真查看&lt;服务条款和声明&gt;(4 秒后继续)" name="agree" id="agree" class="b"/>
          <input onclick="history.back(-1)" type="reset" value="我 不 同 意"  class="b"/>
        </div>
      </form>
    </div>
  </div>
  <!-- end #register info -->
  <script type="text/javascript">
	//<![CDATA[
 	var update = function(num) {
		if(num == secs) {
		    document.getElementById("agree").value =" 我 同 意 ";
 		    document.getElementById("agree").disabled = false;
 		}
		else {
		    spareTime = secs-num; 
		    document.getElementById("agree").value = "请认真查看<服务条款和声明> (" + spareTime +" 秒后继续)"; 
		}
	}
    var secs = 4;
    document.getElementById("agree").disabled = true;
    for(i=1;i<=secs;i++) {
        window.setTimeout("update(" + i + ")", i * 1000);
    }
	//]]>
  </script>
<#include "includes/footer.ftl">  
</body>
</html>
