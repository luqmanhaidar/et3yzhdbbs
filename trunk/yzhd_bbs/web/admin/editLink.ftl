<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改图片链接">
<!-- Pic link begin -->
<div class="box1" id="picLink" style="display:none;">
  <div class="title"> 修改图片链接 </div>
  <form action="updateLink.action" method="post" id="imageLink" onSubmit="return Validator.validate(this,'admin');">
  <input name="linkId" type="hidden" value="${link.id}"/>
  <input name="isLogo" type="hidden" value="1"/>
    <div class="content">	
      <div class="ibox">
        <div class="it">图片链接: *</div>
        <div class="iv">
          <input name="logo" id="Logo" type="text" class="t" value="${link.logo?if_exists}"/><label class="red"> <a href="#" onClick="javascript:Util.openWindow('../common/uploadLink.jsp?element=Logo&preview=false',480,240);">上传链接图片</a> | <a href="#" onClick="javascript:Util.openWindow('../common/system-viewImage.jsp?imagePath=${link.logo?if_exists}',1,1);">预览</a> | <a href="#" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=Logo&imagePath='+document.getElementById('Logo').value,360,140);">删除</a></label>
        </div>
      </div>
      <div class="ibox">
        <div class="it">显示位置: *</div>
        <div class="iv">
    		<select name="adverType" onChange="" id="adverType"">
				<option value="1" <#if link.adverType =1>selected="selected"</#if>>网页顶部</option>
				<option value="2" <#if link.adverType =2>selected="selected"</#if>>首页中间</option>
				<option value="3" <#if link.adverType =3>selected="selected"</#if>>帖子下面</option>
				<option value="4" <#if link.adverType =4>selected="selected"</#if>>右下角</option>
				<option value="5" <#if link.adverType =5>selected="selected"</#if>>友情链接</option>
			</select>
			<span class="red">选择该图片链接的广告类型，显示到不同的广告位</span>
		</div>
      </div>
      <div class="ibox">
        <div class="it">链接地址: *</div>
        <div class="iv">
          <input name="url" type="text" class="t" value="${link.url}" size="40"/>
          <span class="red">网站URL(如:http://www.ET-3.com)</span> </div>
      </div>	  
       <div class="ibox" style="height:114px;">
        <div class="it">该网站详细说明: * </div>
        <div class="iv" style="height:114px; width:68%">
          <textarea name="description" cols="50" rows="7" id="description">${link.description}</textarea> </div>
      </div>
      <!--<div class="ibox">
        <div class="it">排序编号: *</div>
        <div class="iv">-->
          <input name="displayOrder" value="${link.displayOrder}" type="hidden" class="t" value="0" size="4"/>
          <!-- <span class="red">链接地址的排列次序(值越高排在越前)</span> </div> -->
      <!--</div>   -->
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="更新图片链接" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #Pic link -->

<!-- text link begin -->
<div class="box1" id="textLink" style="display:none;">
  <div class="title"> 修改文字链接 </div>
  <form action="updateLink.action" method="post" id="textLink" onSubmit="return Validator.validate(this,'admin');">
  <input name="linkId" type="hidden" value="${link.id}"/>
  <input name="adverType" type="hidden" value="${link.adverType}"/>
  <input name="isLogo" type="hidden" value="0"/>
    <div class="content">	
      <div class="ibox">
        <div class="it">链接名称: * </div>
        <div class="iv">
        <input name="name" type="text" class="t" value="${link.name?default('')}"/>
          <span class="red">网站的名称(如:ET-3论坛)</span> </div>
      </div>
      <div class="ibox">
        <div class="it">链接地址: *</div>
        <div class="iv">
          <input name="url" type="text" class="t" value="${link.url}" size="40"/>
          <span class="red">网站URL(如:http://www.ET-3.com)</span> </div>
      </div>
      <div class="ibox" style="height:114px;">
        <div class="it">该网站详细说明: * </div>
        <div class="iv" style="height:114px; width:68%">
          <textarea name="description" cols="50" rows="7" id="description">${link.description}</textarea> </div>
      </div>
      <!--<div class="ibox">
        <div class="it">排序编号: *</div>
        <div class="iv">-->
          <input name="displayOrder" type="hidden" class="t" value="${link.displayOrder}" size="4"/>
          <!-- <span class="red">链接地址的排列次序(值越高排在越前)</span> </div>
      </div>-->	  
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="更新文字链接" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #text link -->
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
		if(${link.isLogo}==1)
			Util.show("picLink");
		else
			Util.show("textLink");
	}
	//]]>
</script>
</@layout.html>