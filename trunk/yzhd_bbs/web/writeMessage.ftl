<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>撰写短消息</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content","Basic",".");
	};
	var submitForm = function(status){
		// 检测接收消息的用户数
		function checkReceiverNum(){
			var receiver = document.getElementById("receiver").value;
			if(receiver.split(",").length>5){
				alert("同时最多只能发给5个接收者.");
				return false;
			}
			return true;
		}
		
		if(!checkReceiverNum()){
			return ;
		}
		
		// 设置状态 1,正常信息 2,草稿信息
		document.getElementById("status").value=status;
		with(document.getElementById("writeMessage")){
			action = "writeMessage.action";
			target = "_self";
			submit();
		}
	}
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; <a href=\"myMessages.action\" title=\"短消息管理\">短消息管理</a> &gt; 撰写短消息">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action" title="我的控制面板">我的控制面板</a></li>
      <li><a href="editUser-page.action" title="修改用户资料">资料修改</a></li>
      <li><a href="editPassword-page.action" title="修改用户密码">密码修改</a></li>
	  <li><a href="myTopics.action" title="我的主题">我的主题</a></li>      
      <li><a href="myFavorites.action" title="我的收藏夹">我的收藏</a></li>
      <li><span>消息管理</span></li>
      <li><a href="userAttachments.action" title="文件管理">文件管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
 	<#include "includes/subtabs.ftl">
    <div id="tabmainbox">
      <div>
        <h1>撰写短消息</h1>
        <br />
        <div class="box1" style="margin:0px;">
          <div class="title">撰写短消息</div>
          <form action="#" method="post" id="writeMessage" onSubmit="return Validator.validate(this);">
            <input type="hidden" name="status" value="1"/>
            <div class="content">
              <div class="ibox">
                <div class="it">收件人: *</div>
                <div class="iv">
                  <input name="receiver" type="text" class="t" value="${req.getParameter("receiver")?if_exists}" size="40" id="receiver"/>
                  <span class="red">接收者用户名,多个用户之间用","隔开</span> </div>
              </div>
              <div class="ibox">
                <div class="it">消息标题: *</div>
                <div class="iv">
                  <input name="title" type="text" class="t" value="" size="50"/>
                </div>
              </div>
              <div class="ibox" style="height:208px;">
                <div class="it">消息内容: * </div>
                <div class="iv" style="height:200px; width:68%">
                  <textarea name="content" style="height:200px;width:100%;" id="content"></textarea>
                </div>
              </div>
              <input type="hidden" name="flag" value="0"/>
            </div>
            <!-- end #content -->
            <div class="box3" style="text-align:center; clear:both">
              <div>
                <input type="button" value="发送短消息" name="agree" class="b" onClick="submitForm(1);"/>
                <input type="button" value="保存草稿" name="agree" class="b" onClick="submitForm(2);"/>
              </div>
            </div>
          </form>
        </div>
      </div>
      <!-- explain begin -->
    </div>
  </div>
<#include "includes/footer.ftl">  
</body>
</html>
