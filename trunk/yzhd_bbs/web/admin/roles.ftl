<#import "/lib/layout.ftl" as layout>
<@layout.html title="角色管理">
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>
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
<div id="wrap">
  <br/>
  <!--
  <div class="box4">
	  <span class="bold">操作 :</span> <input type="button" value="添加角色" name="" id="btn_createRole" class="tb" onClick="action_btn('createRole','添加角色');"/><input type="button" value="角色管理" name="agree" class="tb" onClick="location.href='#manage'"/>
  </div>
  -->
  <table cellspacing="0" align="center" class="mtable" id="createRole" style="display:none;margin-top:16px;">
    <tr>
      <td colspan="2" class="mt">添加角色</td>
    </tr>
    <form action="createRole.action" method="post" id="createRoleForm" onSubmit="return Validator.validate(this,'admin');">
    <tr>
      <td width="22%" class="mtdt">角色名称： *</td>
      <td width="78%" class="mtdv"><input name="name" type="text" class="t" value="" size="40"/></td>
    </tr>
    <tr>
      <td width="22%" class="mtdt">角色描述： *</td>
      <td width="78%" class="mtdv"><textarea name="description" cols="50" rows="7" id="content"></textarea></td>
    </tr>    
    <tr id="posts">
      <td class="mtdt">发贴数： *</td>
      <td class="mtdv"><input id="minTopic" name="minTopic" type="text" class="t" value=""/></td>
    </tr>
    <tr>
      <td class="mtdt">角色类型： *</td>
      <td class="mtdv">
      	<select name="type" onChange="changeType();">
            <option value="0">注册用户</option>
            <!-- <option value="1">系统用户</option> -->
            <option value="2">特殊用户</option>
        </select>
      </td>
    </tr>    
    <tr>
      <td class="mtdt">角色图片: *</td>
      <td class="mtdv">
      	<input name="icon" type="hidden" class="t" value="level1.gif" id="icon"/>
       	<img src="${base}/skins/default/level/level1.gif" id="viewicon" name="viewicon"/>
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
       </td>
    </tr>
    <tr>
      <td class="mtdt">权限信息: *</td>
      <td class="mtdv"><!-- tab-pane begin -->
        <div class="tab-pane" id="tabPane1">
          <div class="tab-page" id="tabPage1">
            <h2 class="tab">查看权限</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以浏览论坛：</td>
			      <td class="mtdv"><input name="canViewForum" type="radio" value="1" checked/>是 <input name="canViewForum" type="radio" value="0"/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以浏览主题：</td>
			      <td class="mtdv"><input name="canViewTopic" type="radio" value="1" checked/>是 <input name="canViewTopic" type="radio" value="0"/>否</td>
			    </tr>	
	            <tr>
			      <td class="mtdt" style="width:50%;">可以浏览精华主题：</td>
			      <td class="mtdv"><input name="canViewBestTopic" type="radio" value="1" checked/>是 <input name="canViewBestTopic" type="radio" value="0"/>否</td>
			    </tr>			    		    
			</table>
          </div>
          <div class="tab-page" id="tabPage2">
            <h2 class="tab">帖子权限</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以发表新主题：</td>
			      <td class="mtdv"><input name="canPostNew" type="radio" value="1"/>是 <input name="canPostNew" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以回复主题：</td>
			      <td class="mtdv"><input name="canReplyPost" type="radio" value="1"/>是 <input name="canReplyPost" type="radio" value="0" checked/>否</td>
			    </tr>		
			    <tr>
			      <td class="mtdt" style="width:50%;">可以修改自己的回复：</td>
			      <td class="mtdv"><input name="canEditPost" type="radio" value="1"/>是 <input name="canEditPost" type="radio" value="0" checked/>否</td>
			    </tr>	
			    <tr>
			      <td class="mtdt" style="width:50%;">可以删除自己的回复：</td>
			      <td class="mtdv"><input name="canDeleteOwnPost" type="radio" value="1"/>是 <input name="canDeleteOwnPost" type="radio" value="0" checked/>否</td>
			    </tr>
			    <!--<tr>
			      <td class="mtdt" style="width:50%;">可以移动自己的主题：</td>
			      <td class="mtdv">-->	<input name="canMoveOwn" type="hidden" value="1"/><!--是 <input name="canMoveOwn" type="radio" value="0" checked/>否</td>
			    </tr>-->		    			    			    		    		    
			</table>
          </div>   
          <div class="tab-page" id="tabPage3">
            <h2 class="tab">投票权限</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以发表投票：</td>
			      <td class="mtdv"><input name="canPostPoll" type="radio" value="1"/>是 <input name="canPostPoll" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以参与投票：</td>
			      <td class="mtdv"><input name="canVote" type="radio" value="1"/>是 <input name="canVote" type="radio" value="0" checked/>否</td>
			    </tr>	    			    			    		    		    
			</table>
          </div>                   
 
          <div class="tab-page" id="tabPage4">
            <h2 class="tab">附件权限</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以下载附件：</td>
			      <td class="mtdv">--><input name="canGetAttachment" type="radio" value="1"/>是 <input name="canGetAttachment" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以上传附件：</td>
			      <td class="mtdv"> --><input name="canUploadAttachment" type="radio" value="1"/>是 <input name="canUploadAttachment" type="radio" value="0" checked/>否</td>
			    </tr>	 
	            <tr>
			      <td class="mtdt" style="width:50%;">上传附件的大小(kb)：</td>
			      <td class="mtdv">--><input name="uploadAttachmentSize" type="text" value="500" size="4" class="t"/></td>
			    </tr>				       			    			    		    		    
			</table>
          </div> 
 
         <!-- <div class="tab-page" id="tabPage5">
            <h2 class="tab">用户信息</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以查看用户信息：</td>
			      <td class="mtdv">--><input name="canViewUser" type="hidden" value="1" /><!-- 是 <input name="canViewUser" type="radio" value="0"/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以使用签名：</td>
			      <td class="mtdv">--><input name="canUseSignature" type="hidden" value="1" /><!--是 <input name="canUseSignature" type="radio" value="0"/>否</td>
			    </tr>	 
	            <tr>
			      <td class="mtdt" style="width:50%;">可以自定义头像：</td>
			      <td class="mtdv">--><input name="canCustomFace" type="hidden" value="1" /><!--是 <input name="canCustomFace" type="radio" value="0"/>否</td>
			    </tr>				       			    			    		    		    
			</table>
          </div> -->
          
          <!--<div class="tab-page" id="tabPage6">
            <h2 class="tab">管理权限</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以删除帖子：</td>
			      <td class="mtdv">--><input name="canDelete" type="hidden" value="0"/><!--是 <input name="canDelete" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以移动帖子：</td>
			      <td class="mtdv">--><input name="canMove" type="hidden" value="0"/><!--是 <input name="canMove" type="radio" value="0" checked/>否</td>
			    </tr>	 
	            <tr>
			      <td class="mtdt" style="width:50%;">可以置顶帖子：</td>
			      <td class="mtdv">--><input name="canTopTopic" type="hidden" value="0"/><!--是 <input name="canTopTopic" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以编辑他人帖子：</td>
			      <td class="mtdv">--><input name="canEditOther" type="hidden" value="0"/><!--是 <input name="canEditOther" type="radio" value="0" checked/>否</td>
			    </tr>	
	            <tr>
			      <td class="mtdt" style="width:50%;">可以设置精华帖：</td>
			      <td class="mtdv">--><input name="canBestTopic" type="hidden" value="0"/><!--是 <input name="canBestTopic" type="radio" value="0" checked/>否</td>
			    </tr>	
	            <tr>
			      <td class="mtdt" style="width:50%;">可以锁定主题：</td>
			      <td class="mtdv">--><input name="canLockTopic" type="hidden" value="0"/><!--是 <input name="canLockTopic" type="radio" value="0" checked/>否</td>
			    </tr>				    
	            <tr>
			      <td class="mtdt" style="width:50%;">可以发布公告：</td>
			      <td class="mtdv">--><input name="canAnnounce" type="hidden" value="0"/><!--是 <input name="canAnnounce" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以编辑论坛信息：</td>
			      <td class="mtdv">--><input name="canEditForum" type="hidden" value="0"/><!--是 <input name="canEditForum" type="radio" value="0" checked/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">可以管理论坛的分类：</td>
			      <td class="mtdv">--><input name="canCategory" type="hidden" value="0"/><!--是 <input name="canCategory" type="radio" value="0" checked/>否</td>
			    </tr>			    			    
	            <tr>
			      <td class="mtdt" style="width:50%;">可以批量删除帖子：</td>
			      <td class="mtdv">--><input name="canBatchDelete" type="hidden" value="0"/><!--是 <input name="canBatchDelete" type="radio" value="0" checked/>否</td>
			    </tr>			    			    			    		    				       			    			    		    		    
			</table>
          </div>-->   
          
         <!-- <div class="tab-page" id="tabPage7">
            <h2 class="tab">短消息权限</h2>
            <table cellspacing="0" align="center" class="mtable">
	            <tr>
			      <td class="mtdt" style="width:50%;">可以发送短消息：</td>
			      <td class="mtdv">--><input name="canSendMessage" type="hidden" value="1"/><!--是 <input name="canSendMessage" type="radio" value="0"/>否</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">最多接收用户：</td>
			      <td class="mtdv">--><input name="mostReceiverUsers" type="hidden" value="5" size="4" class="t"/><!--</td>
			    </tr>	 
	            <tr>
			      <td class="mtdt" style="width:50%;">短信内容大小限制：</td>
			      <td class="mtdv">--><input name="messageMaxSize" type="hidden" value="500" size="4" class="t"/><!--byte</td>
			    </tr>
	            <tr>
			      <td class="mtdt" style="width:50%;">信箱大小限制：</td>
			      <td class="mtdv">--><input name="inboxSize" type="hidden" value="" size="4" class="t"/><!--kb</td>
			    </tr>	
			</table>
          </div>                     
        </div>-->
        <!-- end #tab-pane1  -->       
      </td>
    </tr>
    <tr>
      <td colspan="2" class="mtda"><input type="submit" name="submit" value="添加角色" class="b"/></td>
    </tr>
    </form>
  </table>
  <!-- end # create help -->
  <br/> 
  <a name="manage"></a>
  <div class="tab-pane" id="tabPane2" style="margin:8px 2%;">
     <div class="tab-page" id="tabPage21">
     	<h2 class="tab">系统角色</h2>
	   <table align="center" cellpadding="0" class="ltable" cellspacing="1">
	    <tr class="ltrt">
	      <td width="20%">角色名</td>
	      <td width="16%">最少主题</td>
	      <td width="34%">等级图片</td>
	      <td width="30%">操作</td>
	    </tr> 
	    <#list managerRoles as role>
	    <tr class="ltrv">
	      <td width="20%">${role.name}</td>
	      <td width="16%">不限</td>
	      <td width="34%"><img src="${base}/skins/default/level/${role.icon}" alt="${role.name}"/></td>
	      <td width="30%"><!-- <input type="button" name="" class="tb" value="列表此角色用户" onClick="window.location.href='users.html';"/> -->
	        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editRole.action?roleId=${role.id}';"/>
	    </tr>
	    </#list>
	  </table>    
     </div>
     
     <!-- 1.1
     <div class="tab-page" id="tabPage22">
     	<h2 class="tab">注册用户</h2>
	   <table align="center" cellpadding="0" class="ltable" cellspacing="1">
	    <tr class="ltrt">
	      <td width="20%">角色名</td>
	      <td width="16%">最少主题</td>
	      <td width="34%">等级图片</td>
	      <td width="30%">操作</td>
	    </tr> 
	    <#list userRoles as role>
	    <tr class="ltrv">
	      <td width="20%">${role.name}</td>
	      <td width="16%">${role.minTopic}</td>
	      <td width="34%"><img src="${base}/skins/default/level/${role.icon}" alt="新手上路"/></td>
	      <td width="30%">
	      1.1-->
	      <!-- <input type="button" name="" class="tb" value="列表此角色用户" onClick="window.location.href='users.html';"/> -->
	        <!--1.1
	        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editRole_common.action?roleId=${role.id}';"/>
	        <input type="button" name="" class="tb" value="删除" onClick="Util.del('删除角色需要更新部分用户等级,确认删除 [${role.name}] 吗?','deleteRole.action?roleId=${role.id}');"/></td>
	    </tr>
	    </#list>
	  </table>
     </div>    
     <div class="tab-page" id="tabPage23">
     	<h2 class="tab">特殊用户</h2>
	   <table align="center" cellpadding="0" class="ltable" cellspacing="1">
	    <tr class="ltrt">
	      <td width="20%">角色名</td>
	      <td width="16%">最少主题</td>
	      <td width="34%">等级图片</td>
	      <td width="30%">操作</td>
	    </tr> 
	   	<#list specialRoles as role>
	    <tr class="ltrv">
	      <td width="20%">${role.name}</td>
	      <td width="16%">${role.minTopic}</td>
	      <td width="34%"><img src="${base}/skins/default/level/${role.icon}" alt="新手上路"/></td>
	      <td width="30%">
	      1.1-->
	      <!-- <input type="button" name="" class="tb" value="列表此角色用户" onClick="window.location.href='users.html';"/> -->
	        <!--1.1
	        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editRole.action?roleId=${role.id}';"/>
	        <input type="button" name="" class="tb" value="删除" onClick="Util.del('删除角色需要更新部分用户等级,确认删除 [${role.name}] 吗?','deleteRole.action?roleId=${role.id}');"/></td>
	    </tr>
	    </#list>
	  </table>
     </div> 
     1.1-->
               
  </div>
</div>
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
	<#if actionMessage?if_exists!="">
  		alert("${actionMessage?if_exists}");
	</#if>
	}
	//]]>
</script>
</@layout.html>