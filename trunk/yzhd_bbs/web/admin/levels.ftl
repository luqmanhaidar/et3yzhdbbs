<#import "/lib/layout.ftl" as layout>
<@layout.html title="公告列表">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		<#if actionMessage?exists>
  			alert("${actionMessage?if_exists}");
		</#if>
		
	}
	//]]>
</script>
<div class="box4">
	操作 : <input type="button" value="新增等级" id="btn_createAnnoucement" name="btn_createAnnoucement" class="tb" onClick="window.location.href='createLevel-page.action'"/>
</div>
<!-- basic info begin -->

<!-- end #basic info -->
<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:294px;">
      <div class="d" style="width:150px;">积分</div>
      <div class="d" style="width:140px;">操作</div>
    </div>
    <div class="llbox" style="border-right:0px;overflow :hidden;">等级</div>
  </div>
 <#list levels as level>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:294px;">
      <div class="d" style="width:150px;">${level.money}</div>
	  <div class="d" style="width:140px;">
        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editLevel!edit.action?id=${level.id}';"/>
        <input type="button" name="" class="tb" value="删除" onClick="Util.del('确认删除 ','levels!delete.action?id=${level.id}');"/>
      </div>
    </div>
    <div class="llbox" style="border-right:0px;">${level.name}</div>
  </div>
 </#list>
</div>
</@layout.html>