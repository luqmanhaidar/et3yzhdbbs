<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="操作日志管理">
<script type="text/javascript">
	function doSearch(){
		
	}
</script>
<!-- actlog search begin -->
<div class="box1">
  <div class="title"> 检索操作日志 </div>
  <div class="content">
    <div class="ibox" style="border-bottom:0px;">
      <div class="it">操作名称: *</div>
      <div class="iv">
        <form action="actLogs.action" method="post" name="searchForm" id="searchForm" onSubmit="return doSearch();"/>
	        <input name="wd" type="text" class="t" size="40" value="${req.getParameter("wd")?if_exists}"/>
	        <input type="submit" value="查找" name="search" class="b"/>
			<input type="hidden" name="start" value="0" id="start"/>
        </form>
	  </div>
    </div>
  </div>
</div>
<!-- end #log search -->
<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:504px;">
      <div class="d" style="width:100px;">操作用户</div>
      <div class="d" style="width:250px;">操作地点</div>
      <div class="d" style="width:150px;">操作时间</div>
    </div>
    <div class="llbox" style="width:40px;"> </div>
    <div class="llbox" style="border-right:0px;overflow :hidden; padding-left:20px;">操作名称</div>
  </div>
  <form action="#" method="post" id="actLogs">
  <#list actLogs as actLog>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:504px; ">
      <div class="d" style="width:100px;">${actLog.username}</div>
      <div class="d" style="width:250px;">${IPLocation.getLocation(actLog.ip)}</div>
      <div class="d" style="width:150px;">${actLog.time?string("yyyy-MM-dd HH:mm:ss")}</div>
    </div>
    <div class="llbox" style="width:40px;">
      <input name="id" type="checkbox" value="${actLog.id}" class="cb">
    </div>
    <div class="llbox" style="text-align:left;border-right:0px;overflow :hidden;height:100%;">
    	<a href="#" onMouseOver="ddrivetip('${actLog.name?replace("|","\r\n")?js_string}',300,'${base}/scripts/tooltip/arrow.gif');" onMouseOut="hideddrivetip();">${actLog.name[0..actLog.name?last_index_of("|")-1]}</a>
    </div>
  </div>
  </#list>
  </form>
</div>
<div class="box1 dashed" style="clear:both; height:22px; padding:4px; margin-top:4px;">
  <div>
    <div class="lrbox" style="width:500px;margin:0px;line-height:150%;">
      <!-- pager -->
      <@fn.pager pagination=pagination />
    </div>
    <div class="llbox" style="border-right:0px;">
      <input name="d" type="checkbox" value="" id="allId" class="cb" onClick="System.checkedAll(this,'actLogs');"/>
      <input type="button" value="删除" class="tb" onClick="System.batchExecute('actLogs','您确认删除选中的日志吗？','deleteActLog.action');"/>
      <input type="button" value="清空全部记录" class="tb" onClick="Util.del('您确认清空所有的日志吗？','deleteActLog.action?isAll=1');"/>
    </div>
  </div>
</div>
</@layout.html>