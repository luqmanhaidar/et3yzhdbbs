<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改贴子</title>

<link rel="stylesheet" href="styles/default.css" type="text/css" media="all"/>
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="scripts/ntsky/validator.js"></script>
<script type="text/javascript" src="scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript" src="scripts/ntsky/global.js"></script>
<script type="text/javascript" src="scripts/ntsky/system.js"></script>
<script type="text/javascript" src="scripts/tooltip/tooltip.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea("content","Default",".","400");
	};
	//]]>
</script>
</head>
<body>
<#include "includes/header.ftl">
<#include "includes/front_top.ftl">
  <!-- register info begin -->
  <div class="box1 mtq">
    <div class="title"> 修改贴子 </div>
    <form action="updatePost.action" method="post" id="post" onSubmit="return Validator.validate(this);">
    <div class="content">
      <!--<div class="ibox">
        <div class="it" style="width:20%;">标题: * </strong> </div>
        <div class="iv">
            <input type="text" name="title" class="t" size="60" value="${post.title?if_exists}"/>
        </div>
      </div>-->
      <input type="hidden" name="title" class="t" size="60" value="${post.title?if_exists}"/>
      <div class="ibox" style="height:408px;">
        <div class="it" style="width:20%;">内容: *  </div>
        <div class="iv" style="height:400px; width:76%">
          <textarea name="content" id="content" style="height:400px;width:100%;">${post.content}</textarea>
        </div>
      </div>
      </div>
      <div class="box3" style="text-align:center">
        <div>
          <input type="hidden" value="${post.topicId}" name="topicId"/>
          <input type="hidden" value="${post.id}" name="id"/>
          <input type="hidden" value="${post.userId}" name="userId"/>
          <input type="hidden" value="${post.forumId}" name="forumId"/>
          <input type="submit" value="更新帖子" name="agree" class="b"/>
          <input type="button" value="预览主题" name="preview" class="b" onClick="Util.popupWindow('common/process.jsp','post','common/preview-post.jsp','500','300');"/>
          <input type="reset" value="重置"  class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end #post info -->
<#include "includes/bottom.ftl">  
</body>
</html>
