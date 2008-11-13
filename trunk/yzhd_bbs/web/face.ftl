<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户头像</title>
<#include "includes/head.ftl">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		
	}
	
	// 设置用户头像
	function setUserFace(facePath){
		if(opener!=null){
			opener.document.getElementById("hiddenFace").value = facePath;
			opener.document.images["viewFace"].src = facePath;
			//opener.focus();
			window.close();
		}
	}

	// 展开或隐藏	
	function showOrHidden(id){
		if(document.getElementById("type"+id+"").style.display=="none"){
			Util.show("type"+id+"");
			document.images["button"+id].src = "images/collapsed.gif";
		}
		else{
			Util.hidden("type"+id);
			document.images["button"+id].src = "images/expanded.gif";
		}
	}
	
	//]]>
</script>
</head>
<body>
<div id="wrap">
	<#-- <#assign faceTypes = faceTypes>
	<#assign keys = faces?keys>
	<#list keys as key> -->
	<#list faceTypes as faceType>  
	  <h2 style="color: #0060bf; font-size:13px;margin:5px;padding:5px;"/>
	　   <img src="images/<#if faceType_index=0>collapsed.gif<#else>expanded.gif</#if>" id="button${faceType.id}" onclick="showOrHidden('${faceType.id}')"> 
		${faceType.name}
	  </h2>
	  <ul style="margin-top:0px;list-style:none;">
	    <div class="dashed" id="type${faceType.id}" style="display:<#if faceType_index=0>block;<#else>none;</#if>margin:10px;padding:10px;text-align:left;">
		 <#list faceType.faces as face>
		  <a href="#" title="${face.name}" onClick="javascript:setUserFace('${face.path}')"><img id="${face.id}" src="${face.path}" title="${face.name}"/></a>
		 </#list>
		</div>
	  </ul>
  </#list>
  <div style="text-align:center;padding:8px;"><input type="submit" value="关闭" name="agree" class="b" onclick="window.opener=null;window.close()"/></div>
</div>  
</body>
</html>
