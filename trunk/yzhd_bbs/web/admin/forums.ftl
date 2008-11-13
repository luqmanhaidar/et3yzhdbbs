<#import "/lib/layout.ftl" as layout>
<@layout.html title="论坛管理">
<script type="text/javascript">
	//<![CDATA[
		<#if successMsg?if_exists!="">
  			alert("${successMsg}");
		</#if>
	//]]>
</script>	
<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:680px;">
      <div class="d" style="width:80px;">版主</div>
      <div class="d" style="width:80px;">主题总数</div>
      <div class="d" style="width:90px;">贴子总数</div>
      <div class="d" style="width:52px;">首页显示</div>
      <div class="d" style="width:42px;">仅版主</div>
      <div class="d" style="width:70px;">管理员发贴</div>
      <div class="d" style="width:30px;">顺序</div>
      <div class="d" style="width:180px;">操作</div>
    </div>
    <div class="llbox" style="border-right:0px;overflow :hidden; padding-left:20px;">版块名称</div>
  </div>
  <#list forums as forum>
  <#-- <#assign line="-">
  <#list 1..forum.depth as i>
  	line
  </#list> -->
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:680px; ">
      <div class="d" style="width:80px;">
      <#if forum.masters=="">
      	暂无版主
      <#else>
	      <#list forum.masters?split(",") as x>
			<a href="../user.action?username=${URLEncoder.encode(x)}" title="查看${x}的资料" target="_blank"/>${x}</a>
		  </#list>
	  </#if>
      </div>
      <div class="d" style="width:80px;">${forum.totalTopic}</div>
      <div class="d" style="width:90px;">${forum.totalPost}</div>
      <div class="d" style="width:52px;">
      	<#if forum.depth!=1>
      	<#if forum.isTop=1><input type="button" name="" class="tb" value="是" onClick="location.href='forumManage!isTop.action?forumId=${forum.id}&amp;isTop=0&amp;start=${start}'"/><#else><input type="button" name="" class="tb" value="否" onClick="location.href='forumManage!isTop.action?forumId=${forum.id}&amp;isTop=1&amp;start=${start}'"/></#if>
      	</#if>
      </div>
      <div class="d" style="width:42px;">
        <#if forum.depth!=1>
      	<#if forum.isMasters=1><input type="button" name="" class="tb" value="是" onClick="location.href='forumManage!isMasters.action?forumId=${forum.id}&amp;isMasters=0&amp;start=${start}'"/><#else><input type="button" name="" class="tb" value="否" onClick="location.href='forumManage!isMasters.action?forumId=${forum.id}&amp;isMasters=1&amp;start=${start}'"/></#if>
      	</#if>  
      </div>
      <div class="d" style="width:70px;">
        <#if forum.depth!=1>
      	<#if forum.isAdmin=1><input type="button" name="" class="tb" value="是" onClick="location.href='forumManage!isAdmin.action?forumId=${forum.id}&amp;isAdmin=0&amp;start=${start}'"/><#else><input type="button" name="" class="tb" value="否" onClick="location.href='forumManage!isAdmin.action?forumId=${forum.id}&amp;isAdmin=1&amp;start=${start}'"/></#if>
      	</#if>  
      </div>
      <div class="d" style="width:30px;">
        <#if forum.depth!=1>
      	${forum.displayOrder}
      	</#if>  
      </div>
      <div class="d" style="width:180px;">
      	<input type="button" name="" class="tb" value="标识图片" onClick="javascript:Util.openWindow('../common/system-viewImage.jsp?imagePath=${forum.signImage}',1,1);"/>
        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='forumManage!edit.action?forumId=${forum.id}';"/>
        <input type="button" name="" class="tb" value="删除" onClick="Util.del('确认删除 [${forum.name}] 吗?\r\n删除栏目会删除其子栏目以及栏目下对应的帖子','deleteForum.action?forumId=${forum.id}');"/>
     </div>
    </div>
    <div class="llbox" style="text-align:left;border-right:0px;"><a href="forumManage!edit.action?forumId=${forum.id}" onMouseOver="ddrivetip('${forum.description?replace("\"","")?js_string}',300,'${base}/scripts/tooltip/arrow.gif');" onMouseOut="hideddrivetip();">${""?left_pad((forum.depth-1)*2,"—")}${forum.name}</a></div>
  </div>
  </#list>
</div>
</@layout.html>
