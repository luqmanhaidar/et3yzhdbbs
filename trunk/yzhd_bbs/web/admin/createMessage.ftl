<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="短消息管理">
<script type="text/javascript">
	//<![CDATA[	
	function doAppoint(isPoint) {
		if("1" == isPoint){
			Util.show("group");
			document.getElementById("receiver").disabled = true;
		}
		else{
			Util.hidden("group");
			document.getElementById("receiver").disabled = false;
		}		
	}
	
	function validate(){
		
		if(!document.getElementById("appoint").checked){
			if(document.getElementById("receiver").value==""){
				alert("消息接受者不为空");
				return false;
			}
		}
		if(document.getElementById("title").value==""){
			alert("消息标题不为空");
			return false;
		}
		if(FCKeditorAPI.GetInstance("content").GetXHTML(true)==""){
			alert("消息内容不为空");
			return false;
		}
		return true;
	}
	
	//]]>
</script>

<!-- explain begin -->
<div class="box1 dashed">
  <h2>短消息发送说明</h2>
  <ol>
    <li>CheckBox被选上,表示发送给全部用户,如果不选择在文本框里填写用户名称,中间用","分隔.</li>
  </ol>
</div>
<!-- end #explain -->

<!-- basic info begin -->
<div class="box1">
  <div class="title"> 添加系统短消息 </div>
  <form action="createMessage.action" method="post" id="messageForm" onSubmit="return validate();">
    <div class="content">
      <div class="ibox">
        <div class="it">消息接收者: *</div>
        <div class="iv">
          指定系统用户组: <input type="checkbox" name="appoint" id="appoint" value="1" onClick="if(this.checked){doAppoint('1');}else{doAppoint('0');}"><input name="receiver" type="text" class="t" value="" size="20" id="receiver"/></div>
      </div>
      <div id="group" style="display:none;">
      <div class="ibox">
        <div class="it">系统用户组: *</div>
        <div class="iv">
          <#list managerRoles as role><#if role.id != 0> ${role.name}<input name="roles" type="checkbox" value="${role.id}"/> </#if></#list>
        </div>
      </div>
	  <#-- 
	  <div class="ibox">
        <div class="it">用户组: *</div>
        <div class="iv">
          <#list UserRoles as role>${role.name}<input name="userRole" type="checkbox" value="${role.id}"/> </#list>
        </div>
      </div>	
      -->
      </div>  	  
      <div class="ibox">
        <div class="it">消息标题: *</div>
        <div class="iv">
          <input name="title" type="text" class="t" value="" size="60"/>
        </div>
      </div>
      <div class="ibox" style="height:208px;">
        <div class="it">消息内容: * </div>
        <div class="iv" style="height:200px; width:76%">
          <textarea name="content" id="content" style="height:200px; width:100%"></textarea></div>
      </div>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="添加系统短消息" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
</@layout.html>
<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[	
	window.onload = function(){
		replaceMyTextarea("content","Default","..","400") ;
	}
	<#if actionMessage?exists>
	alert("${actionMessage?if_exists}");	
	</#if>
	//]]>
</script>