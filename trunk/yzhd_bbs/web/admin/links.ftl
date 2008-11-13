<#import "/lib/layout.ftl" as layout>
<@layout.html title="图片链接管理">
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>

<div class="tab-pane" id="tabPane1" style="margin:8px 2%;">
	<div class="tab-page" id="tabPage1">
    	<h2 class="tab">文字链接</h2>
    	<!-- textlink begin -->
		<div class="box1" style="margin-top:4px;height:26px;">
		  <div style=" background-color: #f7f7f7;">
		    <div class="lrbox" style="width:104px;">
		      <div class="d" style="width:100px;">操作</div>
		    </div>
		    <div class="llbox" style="width:250px;"><span class="it">链接名称</span></div>
			<div class="llbox" style="border-right:0px;overflow :hidden; padding-left:30px;">链接地址</div>
		  </div>
		  <#list textLinks as textLink>
		  <div class="lbox1" style="padding:0px; height:26px;">
		    <div class="lrbox" style="width:104px;">
		 	  <div class="d" style="width:100px;">
		        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editLink.action?linkId=${textLink.id}';"/>
		        <input type="button" name="" class="tb" value="删除" onClick="Util.del('确认删除该文字链接吗？','deleteLink.action?linkId=${textLink.id}',linkHidden);"/>
		      </div>
		    </div>
		    <div class="llbox" style="width:250px;line-height:150%;"><a href="${textLink.url}" title="${textLink.name}" target="_blank">${textLink.name}</a></div>
			<div class="llbox" style="border-right:0px;overflow :hidden;height:100%;"><a href="${textLink.url}" title="${textLink.name}" target="_blank">${textLink.url}</a></div>	
		  </div>
		  </#list>
		</div>
    	<!-- textlink end -->
    </div>
    <div class="tab-page" id="tabPage2">
    	<h2 class="tab">图片链接</h2>    
    	  <table align="center" cellpadding="0" class="ltable" cellspacing="1">
    		<tr class="ltrt">
    			<td width="200px">链接图片</td>
    			<td >链接地址</td>
    			<td >显示位置</td>
    			<td width="130px">操作</td>
    		</tr>
    		<#list picLinks as picLink>
    		<tr class="ltrv">
    			<td><a href="${picLink.url}" title="${picLink.description}">
    			<#if (picLink.logo)[0..3]=="http">
    			<img src="${picLink.logo}" height="50">
    			<#else>
    			<img src="../${picLink.logo}" height="50">
    			</#if>
    			</a></td>
    			<td><a href="${picLink.url}" title="${picLink.description}">${picLink.url}</a></td>
    			<td>
    				<#if picLink.adverType=1>
    				网页顶部
    				</#if>
    				<#if picLink.adverType=2>
    				首页中间
    				</#if>
    				<#if picLink.adverType=3>
    				帖子下面
    				</#if>
    				<#if picLink.adverType=4>
    				右下角
    				</#if>
    				<#if picLink.adverType=5>
    				友情链接
    				</#if>
    			</td>
    			<td>
			        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editLink.action?linkId=${picLink.id}&amp;point=6_2';"/>
			        <input type="button" name="" class="tb" value="删除" onClick="Util.del('确认删除该图片链接吗？','deleteLink.action?linkId=${picLink.id}&amp;point=6_2',linkHidden);"/>
				</td>
    		</tr>    
    		</#list>		
    	</table>
    </div>
</div>
    
<div class="box1" style="margin-top:30px;">
  <div class="title"> 选择链接类型 : 
  	<select name="linkType" onChange="showLink();" id="linkType">
		<option value="1">图片链接</option>
		<option value="0">文字链接</option>
	</select></div>
</div>

<!-- Pic link begin -->
<div class="box1" id="picLink" style="display:block;">
  <div class="title">添加图片链接</div>
  <form action="createLink.action?linkType=1" method="post" target="linkHidden" id="imageLink" onSubmit="return Validator.validate(this,'admin');">
  <input type="hidden" name="point" value="6_2"/>
  <input type="hidden" name="isLogo" value="1"/>
    <div class="content">	
      <div class="ibox">
        <div class="it">图片链接: *</div>
        <div class="iv">
          <input name="logo" id="Logo" type="text" class="t" value="" /> 
          <label class="red"> 
          	<a href="#" onClick="javascript:Util.openWindow('../common/uploadLink.jsp?element=Logo&preview=false',480,240);">上传链接图片</a> | 
          	<a href="#" onClick="javascript:Util.openWindow('../common/system-viewImage.jsp?imagePath='+document.getElementById('Logo').value,1,1);">预览</a> | 
          	<a href="#" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=Logo&imagePath='+document.getElementById('Logo').value,360,140);">删除</a>
          </label>
        </div>
      </div>
      <div class="ibox">
        <div class="it">显示位置: *</div>
        <div class="iv">
    		<select name="adverType" onChange="" id="adverType">
				<option value="1">网页顶部</option>
				<option value="2">首页中间</option>
				<option value="3">帖子下面</option>
				<option value="4">右下角</option>
				<option value="5">友情链接</option>
			</select>
			<span class="red">选择该图片链接的广告类型，显示到不同的广告位</span>
		</div>
      </div>
      <div class="ibox">
        <div class="it">链接地址: *</div>
        <div class="iv">
          <input name="url" type="text" class="t" value="http://" size="40"/>
          <span class="red">网站URL(如:http://www.ET-3.com)</span> </div>
      </div>	  
       <div class="ibox" style="height:114px;">
        <div class="it">该网站简要说明: * </div>
        <div class="iv" style="height:114px; width:68%">
          <textarea name="description" cols="50" rows="7" id="description"></textarea> </div>
      </div>
       <!--<div class="ibox">
        <div class="it">排序编号: *</div>
        <div class="iv"> -->
          <input name="displayOrder" type="hidden" class="t" value="0" size="4"/>
          <!--<span class="red">链接地址的排列次序(值越高排在越前)</span> </div>
      </div>	-->  
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="添加图片链接" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #Pic link -->

<!-- text link begin -->
<div class="box1" id="textLink" style="display:none;">
  <div class="title"> <a href="#">添加文字链接</a></div>
  <form action="createLink.action?linkType=0" method="post" target="linkHidden" id="textLink" onSubmit="return Validator.validate(this,'admin');">
  <input type="hidden" name="point" value="6_2"/>
  <input type="hidden" name="isLogo" value="0"/>
  <input name="adverType" type="hidden" value="1"/>
    <div class="content">	
      <div class="ibox">
        <div class="it">链接名称: * </div>
        <div class="iv"><input name="name" type="text" class="t" value="" size="40"/>
          <span class="red">网站的名称(如:ET-3论坛)</span> </div>
      </div>
      <div class="ibox">
        <div class="it">链接地址: *</div>
        <div class="iv">
          <input name="url" type="text" class="t" value="http://" size="40"/>
          <span class="red">网站URL(如:http://www.ET-3.com)</span> </div>
      </div>
      <div class="ibox" style="height:114px;">
        <div class="it">该网站简要说明: * </div>
        <div class="iv" style="height:114px; width:68%">
          <textarea name="description" cols="50" rows="7" id="description"></textarea> </div>
      </div>
      <!--<div class="ibox">
        <div class="it">排序编号: *</div>
        <div class="iv">-->
          <input name="displayOrder" type="hidden" class="t" value="0" size="4"/>
          <!--<span class="red">链接地址的排列次序(值越高排在越前)</span> </div>
      </div>-->	  
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="添加文字链接" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #text link -->
<iframe frameborder="0" id="linkHidden" name="linkHidden" src="" width="0" height="0"> </iframe>
<script type="text/javascript">
	//<![CDATA[
	
	var linkArea = function(linkTypeValue){
		if("1"==linkTypeValue){
			Util.show("picLink");
			Util.hidden("textLink");
		}
		else{
			Util.show("textLink");
			Util.hidden("picLink");
		}		
	}
	
	var showLink = function(){
		var linkTypeValue = document.getElementById("linkType").value;
		linkArea(linkTypeValue);	
	}

	//]]>
</script>
</@layout.html>