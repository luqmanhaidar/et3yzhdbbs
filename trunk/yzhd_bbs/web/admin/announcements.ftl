<#import "/lib/layout.ftl" as layout>
<@layout.html title="公告列表">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		<#if actionMessage?exists>
  			alert("${actionMessage?if_exists}");
		</#if>
		replaceMyTextarea("content");
	}
	//]]>
</script>
<div class="box4">
	操作 : <input type="button" value="添加公告" id="btn_createAnnoucement" name="btn_createAnnoucement" class="tb" onClick="action_btn('createAnnoucement','添加公告');"/>
</div>
<!-- basic info begin -->
<div class="box1" id="createAnnoucement" style="display:none">
  <div class="title"> 添加公告 </div>
  <form action="createAnnouncement.action" method="post" id="annoucementForm" onSubmit="return Validator.validate(this,'admin');">
    <div class="content">
      <div class="ibox">
        <div class="it">公告标题: *</div>
        <div class="iv">
          <input name="title" type="text" class="t" value="" size="40"/>
          <span class="red"></span> </div>
      </div>
      <div class="ibox">
        <div class="it">公告位置: * </div>
        <div class="iv">
          <select name="forumId">
			<option value="0">论坛首页</option>         
	        <#list forums as forum>
	        	<#if forum.depth==1>
				<optgroup label="${forum.name}"> 
				<#else>
					<option value="${forum.id}"><#list 1..(forum.depth-1) as i> —— </#list>${forum.name}</option>
				</#if>	
	        </#list>
          </select>
          <span class="red"></span> </div>
      </div>
      <div class="ibox">
        <div class="it">公告链接: * </div>
        <div class="iv" style="width:68%; ">
          <input name="link" type="text" class="t" value="" size="40"/>
          <span class="red"></span>
         </div>
      </div>
    </div>
    
    <div style="display:none">
    <textarea name="content" id="content">公告：</textarea>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="添加公告" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:294px;">
      <div class="d" style="width:150px;">公告位置</div>
      <div class="d" style="width:140px;">操作</div>
    </div>
    <div class="llbox" style="border-right:0px;overflow :hidden;">标题</div>
  </div>
  <#list announcements as announcement>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:294px;">
      <div class="d" style="width:150px;"><#if announcement.forumId=0>论坛首页<#else>${forumService.getForum(announcement.forumId).name}</#if></div>
	  <div class="d" style="width:140px;">
        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='announcementManage!edit.action?id=${announcement.id}';"/>
        <input type="button" name="" class="tb" value="删除" onClick="window.location.href='announcementManage!delete.action?id=${announcement.id}';"/>
      </div>
    </div>
    <div class="llbox" style="border-right:0px;"><a href="${announcement.link?if_exists}" >${announcement.title}</a></div>
  </div>
  </#list>
</div>
</@layout.html>