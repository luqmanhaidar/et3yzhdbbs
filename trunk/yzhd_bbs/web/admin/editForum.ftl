<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改论坛版块信息">
<div class="box1">	
  <div class="title"> 更新论坛版块 </div>
  <form action="updateForum.action" method="post" id="forum" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="id" value="${forum.id}"/>
    <div class="content">
      <div class="ibox">
        <div class="it">论坛名称: *</div>
        <div class="iv">
          <input name="name" type="text" class="t" value="${forum.name}" size="40"/>
        </div>
      </div>
      <!--
      <div class="ibox">
        <div class="it">所属类别: * </div>
        <div class="iv">
          <select name="parentId">
            <option value="0">根节点</option>
            <#list forums as forumL>
            <option value="${forumL.id}"<#if forumL.id=forum.parentId> selected</#if>>${""?left_pad((forumL.depth-1)*2,"—")}${forumL.name}</option>
            </#list>
          </select>
        </div>
      </div>
      -->
      <input type="hidden" name="parentId" value="${forum.parentId}"/>
      <div class="ibox" style="height:208px;">
        <div class="it">版块介绍: * </div>
        <div class="iv" style="height:200px; width:68%">
          <textarea name="description" cols="50" rows="7" id="descriptionEditor">${forum.description}</textarea></div>
      </div>
      <div class="ibox" style="height:208px;">
        <div class="it">版块规则: * </div>
        <div class="iv" style="height:200px; width:68%">
          <textarea name="rules" cols="50" rows="7" id="remarkEditor">${forum.rules?if_exists}</textarea></div>
      </div>         
	  <div class="ibox">
        <div class="it">版块管理者:   </div>
        <div class="iv">
          <input name="masters" type="text" class="t" value="${forum.masters}"/>
          <span class="red">有多个管理员请用","分隔开</span> </div>
      </div>	
      <#if forum.depth!=1>
      <div class="ibox">
        <div class="it">二级版块顺序:   </div>
        <div class="iv">
          <input name="displayOrder" type="text" class="t" value="${forum.displayOrder}"/>
          <span class="red">二级论坛在首页显示的顺序，请填入数字</span> </div>
      </div>	
      <#else>
      <input name="displayOrder" type="hidden" class="t" value="${forum.displayOrder}"/>
      </#if>
	  <div class="ibox">
        <div class="it">版块标识图片:   </div>
        <div class="iv">
          <input name="signImage" type="text" class="t" value="${forum.signImage}" />
          <input type="button" name="" value="上传论坛标识图片" class="tb" onClick="javascript:Util.openWindow('../common/system-uploadImage.jsp?element=signImage&preview=false',480,240);"/>
          <input type="button" name="" value="预览" class="tb" onClick="javascript:Util.openWindow('../common/viewImage.jsp?element=signImage',1,1);"/>  
          <input type="button" name="" value="删除" class="tb" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=signImage&imagePath='+document.getElementById('signImage').value,360,140);"/> 
          <span class="red">用户可以自定义该版块的标示图片</span> </div>
      </div>	    	  
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="更新版块" name="agree" class="b"/>
		<input type="reset" value="重置" name="agree" class="b" onClick=""/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->
<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea( 'descriptionEditor' ) ;
		replaceMyTextarea( 'remarkEditor' ) ;			
	}
	//]]>
</script>
</@layout.html>
