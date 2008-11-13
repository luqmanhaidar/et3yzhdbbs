<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改论坛信息</title>
<#include "includes/head.ftl">
<link rel="stylesheet" href="styles/tabs.css" type="text/css" media="all"/>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		<#if !actionMessage?exists>
		replaceMyTextarea( 'descriptionEditor',"Basic","." ) ;
		replaceMyTextarea( 'remarkEditor' ,"Basic","." ) ;				
		</#if>
	};
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; 修改论坛信息">
  <#include "includes/quick.ftl">
  <br />
  <div id="tabs">
    <ul>
      <li><a href="masterPanel.action?forumId=${forum.id}" title="版主控制面板">版主控制面板</a></li>
	  <li><span>论坛信息管理</span></li>
      <li><a href="admin-announces.action?forumId=${forum.id}" title="${forum.name},公告管理">公告管理</a></li>
      <li><a href="admin-topics.action?forumId=${forum.id}" title="帖子管理">帖子管理</a></li>
	  <li><a href="admin-categories.action?forumId=${forum.id}" title="类别管理">类别管理</a></li>
    </ul>
  </div>
  <div id="tabmain">
    <div id="tabmainbox">
      <#if actionMessage?exists>
		<@html.message content=actionMessage type="success"/>
	  <#else>    
      <div>
        <h1>修改论坛资料</h1>
        <br />
        <div class="box1" style="margin:0px;">
          <div class="title"> 修改论坛资料</div>
          <form action="admin-updateForum.action" method="post" id="annoucement" onSubmit="return Validator.validate(this);">
            <input type="hidden" name="id" value="${forum.id}"/>
            <input type="hidden" name="parentId" value="${forum.parentId}"/>
            <input type="hidden" name="masters" value="${forum.masters}"/>
            <div class="content">
		      <div class="ibox">
		        <div class="it">论坛名称: *</div>
		        <div class="iv">
		          <input name="name" type="text" class="t" value="${forum.name}" size="40"/>
		        </div>
		      </div>
		      <div class="ibox" style="height:208px;">
		        <div class="it">版块介绍: * </div>
		        <div class="iv" style="height:200px; width:68%">
		          <textarea name="description" style="height:200px; width:100%" id="descriptionEditor">${forum.description}</textarea></div>
		      </div>
		      <div class="ibox" style="height:208px;">
		        <div class="it">版块规则: * </div>
		        <div class="iv" style="height:200px; width:68%">
		          <textarea name="rules" style="height:200px; width:100%" id="remarkEditor">${forum.remark?if_exists}</textarea></div>
		      </div>  
		      <#--       
			  <div class="ibox">
		        <div class="it">版块管理者: * </div>
		        <div class="iv">
		          <input name="masters" type="text" class="t" value="${forum.masters}"/>
		          <span class="red">有多个管理员请用","分隔开</span> </div>
		      </div>	
			  <div class="ibox">
		        <div class="it">版块标识图片: * </div>
		        <div class="iv">
		          <input name="signImage" type="text" class="t" value="${forum.signImage}" readonly="true"/>
		          <span class="red">用户可以自定义该版块的标示图片</span> </div>
		      </div>  
		      -->            
            </div>
            <!-- end #content -->
            <div class="box3" style="text-align:center; clear:both">
              <div>
                <input type="submit" value="更新版块" name="agree" class="b"/>
              </div>
            </div>
          </form>
        </div>
      </div>
      </#if>
      <!-- explain begin -->
    </div>
  </div>
<#include "includes/footer.ftl">
</body>
</html>
