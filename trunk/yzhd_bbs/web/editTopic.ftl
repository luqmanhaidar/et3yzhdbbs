<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改主题 : ${topic.title}</title>
<#include "includes/head.ftl">
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content","Default",".","400");
		Forms.selected("categoryId",${topic.categoryId});
		Forms.selected("mood",${topic.mood});
	};
	//]]>
</script>
</head>
<body>
<#include "includes/top.ftl">
  <#assign link = "<a href=\"forum.action?forumId="+forum.id+"\" title=\""+forum.name+"\">"+forum.name+"</a> &gt; <a href=\"topic.action?topicId="+topic.id+"\" title=\""+topic.title+"\">"+topic.title+"</a> &gt; 修改主题">
  <#include "includes/quick.ftl">
  <div class="box1 dashed">
    <h5>发帖说明</h5>
    <div> 请自觉遵守发帖规则，不要发表不良信息. </div>
  </div>
  <!-- editTopic info begin -->
  <div class="box1 mtq">
    <div class="title"> 修改主题 </div>
    <form action="updateTopic.action" method="post" id="editTopic" onSubmit="return Validator.validate(this);">
    <div class="content">
      <div class="ibox">
        <div class="it" style="width:20%;">标题: * </strong> </div>
        <div class="iv">
        <#if categories.size()!=0>
           <select name="categoryId" id="categoryId">
           	  <option value="0">选择分类</option>	
              <#list categories as category>
              <option value="${category.id}">${category.name}</option>
              </#list>
            </select>
        </#if>            
            <input type="text" name="title" class="t" size="60" value="${topic.title}"/>
        </div>
      </div>
      <div class="ibox">
        <div class="it" style="width:20%;">发帖心情: *  </div>
        <div class="iv">
          <input type="radio" id="mood" name="mood" value="1" checked/><img src="images/mood/smile.gif" alt="高兴"/>
		  <input type="radio" id="mood" name="mood" value="2" /><img src="images/mood/sex.gif" alt="色"/>
		  <input type="radio" id="mood" name="mood" value="3" /><img src="images/mood/cry.gif" alt="哭"/>
		  <input type="radio" id="mood" name="mood" value="4" /><img src="images/mood/lol.gif" alt="大笑"/>
		  <input type="radio" id="mood" name="mood" value="5" /><img src="images/mood/huff.gif" alt="发怒"/>
        </div>
      </div>
      <div class="ibox" style="height:408px;">
        <div class="it" style="width:20%;">内容: *  </div>
        <div class="iv" style="height:400px; width:76%">
          <textarea name="content" id="content">${topic.firstPost.content}</textarea>
        </div>
      </div>
      </div>
      <div class="box3" style="text-align:center">
        <div>
          <input type="hidden" value="${forum.id}" name="forumId"/>
          <input type="hidden" value="${topic.id}" name="id"/>
          <input type="hidden" value="${topic.username}" name="username"/>
          <input type="hidden" value="${topic.firstPost.id}" name="firstPostId"/>
          <input type="submit" value="更新主题" name="agree" class="b"/>
          <input type="button" value="预览主题" name="preview" class="b" onClick="Util.popupWindow('common/process.jsp','editTopic','common/preview.jsp','500','300');"/>
          <input onclick="history.back(-1)" type="reset" value="重置"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #editTopic info -->
<#include "includes/footer.ftl">
</body>
</html>
