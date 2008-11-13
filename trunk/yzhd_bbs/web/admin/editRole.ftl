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
<#setting number_format="0.######"/>
<link id="luna-tab-style-sheet" type="text/css" rel="stylesheet" href="../scripts/tabpane/css/luna/tab.css" />
<script type="text/javascript" src="../scripts/tabpane/js/tabpane.js"></script>
  <div class="box1">
    <div class="title"> 修改角色</div>
    <form action="updateRole.action" method="post">
      <input name="id" type="hidden" class="t" value="${role.id}"/>
      <div class="content">
        <div class="ibox">
          <div class="it">角色名称: *</div>
          <div class="iv">
            <input name="name" type="text" class="t" value="${role.name}" size="40"/></div>
        </div>
        <div class="ibox" style="height:120px;">
          <div class="it">角色描述: </div>
          <div class="iv" style="height:112px; width:68%">
			<textarea name="description" cols="50" rows="7" id="copyright_d">${role.description}</textarea>            
		  </div>
        </div>         
        <div class="ibox">
          <div class="it">发贴数: * </div>
          <div class="iv">
            <input id="minTopic" name="minTopic" type="text" class="t" value="${role.minTopic}"/></div>
        </div>
        <div class="ibox">
          <div class="it">角色图片: * </div>
          <div class="iv">
			<input name="icon" type="hidden" class="t" value="${role.icon}" id="icon"/>
	       	<img src="${base}/skins/default/level/${role.icon}" id="viewicon" name="viewicon"/>
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
        <#if role.type!=1>
        <div class="ibox">
          <div class="it">角色类型: * </div>
          <div class="iv">
          	<select name="type" onChange="changeType();">
		      <option value="0"<#if role.type==0> selected</#if>>注册用户组</option>
		      <option value="2"<#if role.type==2> selected</#if>>特殊用户组</option>
		    </select>
          </div>
        </div>
        <#else>
        <input type="hidden" name="type" value="1"/>  
        </#if>      
        <div class="ibox">
          <div class="it">权限列表: * </div>
          <div class="iv">
			<!-- tab-pane begin -->
	        <div class="tab-pane" id="tabPane1">
	          <div class="tab-page" id="tabPage1">
	            <h2 class="tab">查看权限</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以浏览论坛：</td>
				      <td class="mtdv"><input name="canViewForum" type="radio" value="1"<#if role.permissionMap["canViewForum"]=="1"> checked</#if>/>是 <input name="canViewForum" type="radio" value="0"<#if role.permissionMap["canViewForum"]=="0"> checked</#if>/>否</td> 
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">可以浏览主题：</td>
				      <td class="mtdv"><input name="canViewTopic" type="radio" value="1"<#if role.permissionMap["canViewTopic"]=="1"> checked</#if>/>是 <input name="canViewTopic" type="radio" value="0" <#if role.permissionMap["canViewTopic"]=="0"> checked</#if>/>否</td>
				    </tr>	
		            <tr>
				      <td class="mtdt" style="width:50%;">可以浏览精华主题：</td>
				      <td class="mtdv"><input name="canViewBestTopic" type="radio" value="1"<#if role.permissionMap["canViewBestTopic"]=="1"> checked</#if>/>是 <input name="canViewBestTopic" type="radio" value="0" <#if role.permissionMap["canViewBestTopic"]=="0"> checked</#if>/>否</td>
				    </tr>	   
				</table>
	          </div>
	          <#if role.id=0>
	          <input name="canPostNew" type="hidden" class="t" value="0"/>
	          <input name="canReplyPost" type="hidden" class="t" value="0"/>
	          <input name="canEditPost" type="hidden" class="t" value="0"/>
	          <input name="canDeleteOwnPost" type="hidden" class="t" value="0"/>
	          <input name="canPostPoll" type="hidden" class="t" value="0"/>
	          <input name="canVote" type="hidden" class="t" value="0"/>
	          <input name="canGetAttachment" type="hidden" class="t" value="0"/>
	          <input name="canUploadAttachment" type="hidden" class="t" value="0"/>
	          <input name="uploadAttachmentSize" type="hidden" class="t" value="500"/>
	          <input name="canViewUser" type="hidden" class="t" value="0"/>
	          <input name="canUseSignature" type="hidden" class="t" value="0"/>
	          <input name="canCustomFace" type="hidden" class="t" value="0"/>
	          <input name="canDelete" type="hidden" class="t" value="0"/>
	          <input name="canMove" type="hidden" class="t" value="0"/>
	          <input name="canTopTopic" type="hidden" class="t" value="0"/>
	          <input name="canEditOther" type="hidden" class="t" value="0"/>
	          <input name="canBestTopic" type="hidden" class="t" value="0"/>
	          <input name="canLockTopic" type="hidden" class="t" value="0"/>
	          <input name="canAnnounce" type="hidden" class="t" value="0"/>
	          <input name="canEditForum" type="hidden" class="t" value="0"/>
	          <input name="canCategory" type="hidden" class="t" value="0"/>
	          <input name="canBatchDelete" type="hidden" class="t" value="0"/>
	          <input name="canSendMessage" type="hidden" class="t" value="0"/>
	          <input name="mostReceiverUsers" type="hidden" class="t" value="0"/>
	          <input name="messageMaxSize" type="hidden" class="t" value="0"/>
	          <input name="inboxSize" type="hidden" class="t" value="0"/>

	          <#else>
			  <div class="tab-page" id="tabPage2">
	            <h2 class="tab">帖子权限</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以发表新主题：</td>
				      <td class="mtdv"><input name="canPostNew" type="radio" value="1"<#if role.permissionMap["canPostNew"]=="1"> checked</#if>/>是 <input name="canPostNew" type="radio" value="0"<#if role.permissionMap["canPostNew"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">可以回复帖子：</td>
				      <td class="mtdv"><input name="canReplyPost" type="radio" value="1"<#if role.permissionMap["canReplyPost"]=="1"> checked</#if>/>是 <input name="canReplyPost" type="radio" value="0"<#if role.permissionMap["canReplyPost"]=="0"> checked</#if>/>否</td>
				    </tr>		
				    <tr>
				      <td class="mtdt" style="width:50%;">可以修改自己的回复：</td>
				      <td class="mtdv"><input name="canEditPost" type="radio" value="1"<#if role.permissionMap["canEditPost"]=="1"> checked</#if>/>是 <input name="canEditPost" type="radio" value="0"<#if role.permissionMap["canEditPost"]=="0"> checked</#if>/>否</td>
				    </tr>	
				    <tr>
				      <td class="mtdt" style="width:50%;">可以删除自己的回复：</td>
				      <td class="mtdv"><input name="canDeleteOwnPost" type="radio" value="1"<#if role.permissionMap["canDeleteOwnPost"]=="1"> checked</#if>/>是 <input name="canDeleteOwnPost" type="radio" value="0"<#if role.permissionMap["canDeleteOwnPost"]=="0"> checked</#if>/>否</td>
				    </tr>
				    <!--<tr>
				      <td class="mtdt" style="width:50%;">可以移动自己的主题：</td>
				      <td class="mtdv"><input name="canMoveOwn" type="radio" value="1" <#if role.permissionMap["canMoveOwn"]=="1"> checked</#if>/>是 <input name="canMoveOwn" type="radio" value="0"<#if role.permissionMap["canMoveOwn"]=="0"> checked</#if>/>否</td>
				    </tr>-->		
				    <input name="canMoveOwn" type="hidden" value="0" />    			    			    		    		    
				</table>
	          </div>   

	          <div class="tab-page" id="tabPage3">
	            <h2 class="tab">投票权限</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以发表投票：</td>
				      <td class="mtdv"><input name="canPostPoll" type="radio" value="1"<#if role.permissionMap["canPostPoll"]=="1"> checked</#if>/>是 <input name="canPostPoll" type="radio" value="0"<#if role.permissionMap["canPostPoll"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">可以参与投票：</td>
				      <td class="mtdv"><input name="canVote" type="radio" value="1"<#if role.permissionMap["canVote"]=="1"> checked</#if>/>是 <input name="canVote" type="radio" value="0"<#if role.permissionMap["canVote"]=="0"> checked</#if>/>否</td>
				    </tr>	    			    			    		    		    
				</table>
	          </div>    	          
	          
	 
	         <div class="tab-page" id="tabPage4">
	            <h2 class="tab">附件权限</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以下载附件：</td>
				      <td class="mtdv"><input name="canGetAttachment" type="radio" value="1"<#if role.permissionMap["canGetAttachment"]=="1"> checked</#if>/>是 <input name="canGetAttachment" type="radio" value="0"<#if role.permissionMap["canGetAttachment"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">可以上传附件：</td>
				      <td class="mtdv"><input name="canUploadAttachment" type="radio" value="1"<#if role.permissionMap["canUploadAttachment"]=="1"> checked</#if>/>是 <input name="canUploadAttachment" type="radio" value="0"<#if role.permissionMap["canUploadAttachment"]=="0"> checked</#if>/>否</td>
				    </tr>	 
		            <tr>
				      <td class="mtdt" style="width:50%;">上传附件的大小(kb)：</td>
				      <td class="mtdv"><input name="uploadAttachmentSize" type="text" value="${role.permissionMap["uploadAttachmentSize"]}" size="10" class="t"/></td>
				    </tr>				       			    			    		    		    
				</table>
	          </div> 
	          <!-- <input name="canGetAttachment" type="hidden" value="1"/>
	          <input name="canUploadAttachment" type="hidden" value="1"/>
	          <input name="uploadAttachmentSize" type="hidden" value="500"/>	-->  
	          
	          
		      <!-- <div class="tab-page" id="tabPage5">
	            <h2 class="tab">用户信息</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以查看用户信息：</td>
				      <td class="mtdv"><input name="canViewUser" type="radio" value="1"<#if role.permissionMap["canViewUser"]=="1"> checked</#if>/>是 <input name="canViewUser" type="radio" value="0"<#if role.permissionMap["canViewUser"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">可以使用签名：</td>
				      <td class="mtdv"><input name="canUseSignature" type="radio" value="1"<#if role.permissionMap["canUseSignature"]=="1"> checked</#if>/>是 <input name="canUseSignature" type="radio" value="0"<#if role.permissionMap["canUseSignature"]=="0"> checked</#if>/>否</td>
				    </tr>	 
		            <tr>
				      <td class="mtdt" style="width:50%;">可以自定义头像：</td>
				      <td class="mtdv"><input name="canCustomFace" type="radio" value="1"<#if role.permissionMap["canCustomFace"]=="1"> checked</#if>/>是 <input name="canCustomFace" type="radio" value="0"<#if role.permissionMap["canCustomFace"]=="0"> checked</#if>/>否</td>
				    </tr>				       			    			    		    		    
				</table>
	          </div>  -->          	
	          <input name="canViewUser" type="hidden" value="1"/>
	          <input name="canUseSignature" type="hidden" value="1"/>
	          <input name="canCustomFace" type="hidden" value="1"/>	
	          
	          <div class="tab-page" id="tabPage6">
	            <h2 class="tab">管理权限</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以删除帖子：</td>
				      <td class="mtdv"><input name="canDelete" type="radio" value="1"<#if role.permissionMap["canDelete"]=="1"> checked</#if>/>是 <input name="canDelete" type="radio" value="0"<#if role.permissionMap["canDelete"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <!--<tr>
				      <td class="mtdt" style="width:50%;">可以移动帖子：</td>
				      <td class="mtdv"><input name="canMove" type="radio" value="1"<#if role.permissionMap["canMove"]=="1"> checked</#if>/>是 <input name="canMove" type="radio" value="0"<#if role.permissionMap["canMove"]=="0"> checked</#if>/>否</td>
				    </tr>	 
		            <tr>-->
		            <input name="canMove" type="hidden" value="1"/>
				      <td class="mtdt" style="width:50%;">可以置顶帖子：</td>
				      <td class="mtdv"><input name="canTopTopic" type="radio" value="1"<#if role.permissionMap["canTopTopic"]=="1"> checked</#if>/>是 <input name="canTopTopic" type="radio" value="0"<#if role.permissionMap["canTopTopic"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">可以编辑他人帖子：</td>
				      <td class="mtdv"><input name="canEditOther" type="radio" value="1"<#if role.permissionMap["canEditOther"]=="1"> checked</#if>/>是 <input name="canEditOther" type="radio" value="0"<#if role.permissionMap["canEditOther"]=="0"> checked</#if>/>否</td>
				    </tr>	
		            <tr>
				      <td class="mtdt" style="width:50%;">可以设置精华帖：</td>
				      <td class="mtdv"><input name="canBestTopic" type="radio" value="1"<#if role.permissionMap["canBestTopic"]=="1"> checked</#if>/>是 <input name="canBestTopic" type="radio" value="0"<#if role.permissionMap["canBestTopic"]=="0"> checked</#if>/>否</td>
				    </tr>	
		            <!--<tr>
				      <td class="mtdt" style="width:50%;">可以锁定主题：</td>
				      <td class="mtdv"><input name="canLockTopic" type="radio" value="1"<#if role.permissionMap["canLockTopic"]=="1"> checked</#if>/>是 <input name="canLockTopic" type="radio" value="0"<#if role.permissionMap["canLockTopic"]=="0"> checked</#if>/>否</td>
				    </tr>-->
				    <input name="canLockTopic" type="hidden" value="1"/>
		            <!--<tr>
				      <td class="mtdt" style="width:50%;">可以发布公告：</td>
				      <td class="mtdv"><input name="canAnnounce" type="radio" value="1"<#if role.permissionMap["canAnnounce"]=="1"> checked</#if>/>是 <input name="canAnnounce" type="radio" value="0"<#if role.permissionMap["canAnnounce"]=="0"> checked</#if>/>否</td>
				    </tr>-->
				    <input name="canAnnounce" type="hidden" value="1"/>
		            <!--<tr>
				      <td class="mtdt" style="width:50%;">可以编辑论坛信息：</td>
				      <td class="mtdv"><input name="canEditForum" type="radio" value="1"<#if role.permissionMap["canEditForum"]=="1"> checked</#if>/>是 <input name="canEditForum" type="radio" value="0"<#if role.permissionMap["canEditForum"]=="0"> checked</#if>/>否</td>
				    </tr>-->
				    <input name="canEditForum" type="hidden" value="1"/>
		            <!--<tr>
				      <td class="mtdt" style="width:50%;">可以管理论坛的分类：</td>
				      <td class="mtdv"><input name="canCategory" type="radio" value="1"<#if role.permissionMap["canCategory"]=="1"> checked</#if>/>是 <input name="canCategory" type="radio" value="0"<#if role.permissionMap["canCategory"]=="0"> checked</#if>/>否</td>
				    </tr>	-->
				     <input name="canCategory" type="hidden" value="1"/>
		            <!--<tr>
				      <td class="mtdt" style="width:50%;">可以批量删除帖子：</td>
				      <td class="mtdv"><input name="canBatchDelete" type="radio" value="1"<#if role.permissionMap["canBatchDelete"]=="1"> checked</#if>/>是 <input name="canBatchDelete" type="radio" value="0"<#if role.permissionMap["canBatchDelete"]=="0"> checked</#if>/>否</td>
				    </tr>	-->		
				    <input name="canBatchDelete" type="hidden" value="1"/>    			    			    		    				       			    			    		    		    
				</table>
	          </div>   
	          	  
	          <!--<div class="tab-page" id="tabPage7">
	            <h2 class="tab">短消息权限</h2>
	            <table cellspacing="0" align="center" class="mtable">
		            <tr>
				      <td class="mtdt" style="width:50%;">可以发送短消息：</td>
				      <td class="mtdv"><input name="canSendMessage" type="radio" value="1"<#if role.permissionMap["canSendMessage"]=="1"> checked</#if>/>是 <input name="canSendMessage" type="radio" value="0"<#if role.permissionMap["canSendMessage"]=="0"> checked</#if>/>否</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">最多接收用户：</td>
				      <td class="mtdv"><input name="mostReceiverUsers" type="text" size="4" class="t" value="${role.permissionMap["mostReceiverUsers"]}"/></td>
				    </tr>	 
		            <tr>
				      <td class="mtdt" style="width:50%;">短信内容大小限制：</td>
				      <td class="mtdv"><input name="messageMaxSize" type="text" size="4" class="t" value="${role.permissionMap["messageMaxSize"]}"/>byte</td>
				    </tr>
		            <tr>
				      <td class="mtdt" style="width:50%;">信箱大小限制：</td>
				      <td class="mtdv"><input name="inboxSize" type="text" size="4" class="t" value="${role.permissionMap["inboxSize"]}"/>kb</td>
				    </tr>	
				</table>
	          </div> -->	
	           <input name="canSendMessage" type="hidden" value="1"/>    
	            <input name="mostReceiverUsers" type="hidden" value="5"/>  
	            <input name="messageMaxSize" type="hidden" value="5"/>    
	             <input name="inboxSize" type="hidden" value="500"/>     	  
	          </#if>	          	                            
	        </div>
	        <!-- end #tab-pane1  -->     
          </div>
        </div>
      </div>
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="更新角色" name="submit" class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end # edit role -->
</@layout.html>
