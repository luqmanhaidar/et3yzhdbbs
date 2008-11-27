<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改角色">
<script type="text/javascript">
	//<![CDATA[
	
	var changeType = function(){
		var typeValue = $("type").value;
		if(typeValue == 2){
			$("minTopic").value="-1";
			$("minTopic").disabled=true;
		}
		else{
			$("minTopic").disabled=false;
			$("minTopic").value="";
		}
	}

	function doSelectIcon(){
		// 自定义图片
		if("custom"==$("selectIcon").value){
			Util.show("imageControl");
		}
		else{
			Util.hidden("imageControl");
			$("viewicon").src="${base}/skins/default/level/"+$("selectIcon").value;
			$("icon").value = $("selectIcon").value;
		}
	}

	//]]>
</script>

<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>
  <div class="box1">
    <div class="title"> 修改等级</div>
    <form action="editLevel.action" id="level" method="post" onSubmit="return Validator.validate(this,'admin');">
      <input name="id" type="hidden" class="t" value="${level.id}"/>
      <div class="content">
        <div class="ibox">
          <div class="it">等级名称: *</div>
          <div class="iv">
            <input name="name" type="text" class="t" value="${level.name}" size="40"/></div>
        </div>
        <div class="ibox" style="height:120px;">
          <div class="it">等级描述: </div>
          <div class="iv" style="height:112px; width:68%">
			<textarea name="description" cols="50" rows="7" id="copyright_d">${level.description}</textarea>            
		  </div>
        </div>         
        <div class="ibox">
          <div class="it">最少积分数: * </div>
          <div class="iv">
            <input id="money" name="money" type="text" class="t" value="${level.money}"/></div>
        </div>
        <div class="ibox">
          <div class="it">等级图片: * </div>
          <div class="iv">
			<input name="icon" type="hidden" class="t" value="${level.icon}" id="icon"/>
	       	<img src="${base}/skins/default/level/${level.icon}" id="viewicon" name="viewicon"/>
	       	<select name="selectIcon" onChange="doSelectIcon();">
	            <option value="level1.gif">level1.gif</option>
	            <option value="level2.gif">level2.gif</option>
	            <option value="level3.gif">level3.gif</option>
	            <option value="level4.gif">level4.gif</option>
	            <option value="level5.gif">level5.gif</option>
	            <option value="level6.gif">level6.gif</option>
	            <option value="level7.gif">level7.gif</option>
	            <option value="level8.gif">level8.gif</option>
	            <option value="level9.gif">level9.gif</option>
	            <option value="custom">自定义图片</option>
	        </select>
	       	<label class="red" id="imageControl" style="display:none"> 
	        	<a href="#" onClick="javascript:Util.openWindow('../common/uploadRoleIcon.jsp?element=icon&preview=yes',480,240);">上传角色图片</a> | 
	          	<!-- <a href="#" onClick="javascript:Util.openWindow('../common/deleteImage.jsp?element=icon&imagePath='+$('icon').value,360,140);">删除</a> -->
	        </label>         
          </div>
        </div>      
       
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="提交" name="submit" class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end # edit role -->
</@layout.html>
