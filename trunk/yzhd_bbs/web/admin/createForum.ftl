<#import "/lib/layout.ftl" as layout>
<@layout.html title="创建论坛">
<script type="text/javascript">
	//<![CDATA[
		<#if actionMessage?if_exists!="">
  			alert("${actionMessage}");
		</#if>
	//]]>
</script>	
<div class="box1">
	<div class="title"> 添加论坛 </div>
	  <form action="createForum.action" method="post" id="createForum" onSubmit="return Validator.validate(this,'admin');">
	    <div class="content">
	      <div class="ibox">
	        <div class="it">论坛名称: *</div>
	        <div class="iv">
	          <input name="name" type="text" class="t" value="" size="40"/>
	        </div>
	      </div>
	      <div class="ibox">
	        <div class="it">所属类别: * </div>
	        <div class="iv">
	          <select name="parentId">
	            <option value="0">根节点</option>
	            <#-- <#if forums?if_exists!=null> -->
	            <#list forums as forum>
	            
	            <#-- <option value="${forum.id}">${""?left_pad((forum.depth-1)*2,"—")}${forum.name}</option> -->
	            <#if forum.depth==1>
	            <option value="${forum.id}">--${forum.name}</option>
	            </#if>
	            
	            </#list>
	          </select>
	        </div>
	      </div>
	      <div class="ibox" style="height:208px;">
	        <div class="it">版块介绍: * </div>
	        <div class="iv" style="height:200px; width:68%">
	          <textarea name="description" style="height:200px; width:100%;" id="descriptionEditor"></textarea></div>
	      </div>
	      <div class="ibox" style="height:208px;">
	        <div class="it">版块规则: </div>
	        <div class="iv" style="height:200px; width:68%">
	          <textarea name="rules" style="height:200px; width:100%;" id="rules"></textarea></div>
	      </div>      
		  <div class="ibox">
	        <div class="it">版块管理者:  </div>
	        <div class="iv">
	          <input name="masters" type="text" class="t" value=""/>
	          <span class="red">有多个管理员请用","分隔开</span> </div>
	      </div>	
		  <div class="ibox">
	        <div class="it">版块标识图片: </div>
	        <div class="iv">
	          <input name="signImage" id="signImage" type="text" class="t" value="" readonly="true"/>
	          <input type="button" name="" value="上传论坛标识图片" class="tb" onClick="javascript:Util.openWindow('../common/system-uploadImage.jsp?element=signImage&preview=false',480,240);"/>
	          <input type="button" name="" value="预览" class="tb" onClick="javascript:Util.openWindow('../common/viewImage.jsp?element=signImage',1,1);"/>  
	          <input type="button" name="" value="删除" class="tb" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=signImage&imagePath='+document.getElementById('signImage').value,360,140);"/> 
	          <span class="red">用户可以自定义该版块的标示图片</span> 
	        </div>
	      </div>	    	  
	    </div>
	    <!-- end #content -->
	    <div class="box3" style="text-align:center; clear:both">
	      <div>
	        <input type="submit" value="添加新版块" name="agree" class="b"/>
			<input type="reset" value="重置" name="agree" class="b" onClick=""/>
	      </div>
	    </div>
	  </form>
	</div>
</div>
<!-- end #create forum -->
<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		replaceMyTextarea( 'descriptionEditor' ) ;	
		replaceMyTextarea( 'rules' ) ;		
	}
	//]]>
</script>
</@layout.html>