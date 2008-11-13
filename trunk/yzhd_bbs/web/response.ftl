<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>成功保存贴子</title>

<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
</head>
<body>
<#include "includes/header.ftl">
  <!-- add begin -->
<#include "includes/front_top.ftl">
  <!-- end #add -->
  
  
  
  <DIV id=page>
<div style="height: auto;" class="info3">
    <div class="prompt"><div style="width:530px"><img width="530" height="14" src="images/dl-p11.gif"/></div>
    <div style="height: auto; min-height: 120px;" class="con">
    <table width="460" cellspacing="10" cellpadding="0" border="0">
      <tbody><tr>
        <td height="30" align="center" class="f-red4"><img width="31" height="31" align="absmiddle" src="images/error1.gif"/> 发表成功</td>
      </tr>
      <tr>
        <td style="line-height: 22px;">
            
            <ul><li><a href="index.action">返回首页</a></li>
            <li><a href="topic.action?topicId=${topicId}">您发表的帖子</a></li>
            </ul>
        </td>
      </tr>
    </tbody></table>
                        <br/>
                        <h5 id="moveInfo">本页面将在2秒后自动返回您发布的帖子</h5>
     </div>
    <div style="width:530px"><img src="images/dl-p12.gif"/></div>
    </div>
    </div>
</DIV>
  <!-- -->
  <script type="text/javascript">
	//<![CDATA[
 	var move = function(num) {
		spareTime = secs-num; 
		document.getElementById("moveInfo").innerText = "本页面将在"+spareTime+"秒后自动返回您发布的帖子"; 
		if(spareTime==0){
			location.href="topic.action?topicId=${topicId}";
		}
	}
    var secs = 2;
    for(i=1;i<=secs;i++) {
		window.setTimeout("move(" + i + ")", i * 1000);
    }
	//]]>
  </script>  
<#include "includes/bottom.ftl">
</body>
</html>
