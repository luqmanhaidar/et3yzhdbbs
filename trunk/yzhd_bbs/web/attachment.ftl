<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查看附件${attachment.filename}的详细信息</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"controlPanel.action\" title=\"控制面板\">控制面板</a> &gt; <a href=\"attachments.action\" title=\"附件管理\">附件管理</a> &gt; 查看附件["+attachment.filename+"]的详细信息">
  <#include "includes/quick.ftl">  
  <br />
  <div id="tabs">
    <ul>
      <li><a href="controlPanel.action">我的控制面板</a></li>
      <li><a href="editUser-page.action">资料修改</a></li>
      <li><a href="editPassword-page.action">密码修改</a></li>
      <li><a href="myFavorites.action">我的收藏</a></li>
      <li><a href="myMessages.action" title="论坛短消息管理">消息管理</a></li>
      <li><span>我的文件</span></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <div>
      	<div class="box5" style="margin:0px; text-align:center;">
      	 	<input type="button" name="download" class="b" value="下 载" onClick="DownloadHidden.location.href='servlet/download?attId=${attachment.id}'"/>
      	 	<input type="button" name="delete" class="b" value="删 除" onClick="javascript:Util.del('确认删除文件[${attachment.filename}]吗?','deleteAttachment.action?attachmentId=${attachment.id}');"/>
      	</div>      
        <br />        
        <div class="box1" style="margin:0px;border-bottom:0px;">
          <div class="title">查看附件</div>
            <div class="content">
              <div class="ibox">
                <div class="it">附件名称: </div>
                <div class="iv"><a href="#" onClick="javascript:DownloadHidden.location.href='servlet/download?attId=${attachment.id}';" title="下载${attachment.filename}">${attachment.filename}</a></div>
              </div>
              <div class="ibox">
                <div class="it">附件大小: </div>
                <div class="iv">${attachment.filesize}</div>
              </div>  
              <div class="ibox">
                <div class="it">附件大小: </div>
                <div class="iv">${attachment.filetype}</div>
              </div>     
              <div class="ibox">
                <div class="it">下载次数: </div>
                <div class="iv">${attachment.downloadTimes}</div>
              </div>    
              <div class="ibox">
                <div class="it">创建日期: </div>
                <div class="iv">${attachment.dateCreated}</div>
              </div>                                                   
              <div class="ibox" style="height:208px;">
                <div class="it">附件描述: </div>
                <div class="iv" style="height:200px; width:68%">${attachment.comments}</div>
              </div>             
            </div>
            <!-- end #content -->
        </div>
      </div>
      <!-- explain begin -->
    </div>
  </div>
  <iframe frameborder="0" id="DownloadHidden" name="DownloadHidden" src="" width="0" height="0"> </iframe>
<#include "includes/footer.ftl"> 
</body>
</html>
