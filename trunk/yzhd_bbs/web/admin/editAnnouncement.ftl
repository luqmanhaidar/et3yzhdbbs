<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改公告">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content");
	}
	//]]>
</script>
<!-- basic info begin -->
<div class="box1">
  <div class="title"> 修改公告</div>
  <form action="updateAnnouncement.action" method="post" id="annoucementForm" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="id" value="${announcement.id}"/>
    <div class="content">
      <div class="ibox">
        <div class="it">公告标题: *</div>
        <div class="iv">
          <input name="title" type="text" class="t" value="${announcement.title}" size="40"/>
          <span class="red"></span> </div>
      </div>
      <div class="ibox">
        <div class="it">公告位置: * </div>
        <div class="iv">
          <select name="forumId">
          	<option value="0">论坛首页</option>
            <#list forums as forum>
	        <option value="${forum.id}"<#if forum.id=announcement.forumId> selected</#if>>${""?left_pad(forum.depth-1,"——")}${forum.name}</option>	
	        </#list>
          </select>
          <span class="red"></span> </div>
      </div>
      <div class="ibox" >
        <div class="it">公告链接: * </div>
        <div class="iv" style=" width:68%; ">
        <input name="link" type="text" class="t" value="${announcement.link?if_exists}" size="40"/>
          <span class="red"></span>
          </div>
      </div>
    </div>
    <div style="display:none">
    <textarea name="content" id="content">公告：${announcement.content}</textarea>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="更新公告" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
</@layout.html>