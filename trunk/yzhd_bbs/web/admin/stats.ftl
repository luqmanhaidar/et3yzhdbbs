<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="访问日志管理">
<!-- log search begin -->
<div class="box1">
  <div class="title"> 检索日志 </div>
  <div class="content">
    <div class="ibox" style="border-bottom:0px;">
      <div class="it">访问页面: *</div>
      <div class="iv">
      <script type="text/javascript">
		//<![CDATA[
			function validateSearchForm(){
				if(document.getElementById("field").value==""){
					alert("请选择查询条件");
					return false;
				}
				return true;
				//window.location.href="searchStat.action?field="+document.getElementById("field").value+"&wd="+escape(document.getElementById("wd").value);
			}
		//]]>
	  </script>
      <form action="searchStat.action" method="post" onSubmit="return validateSearchForm();" id="searchForm"/>
        <select name="field" id="field">
          <option value="">请选择查询条件</option>
          <option value="place" <#if "place" = req.getParameter("field")?if_exists>selected</#if>>访问地点</option>
          <option value="referer" <#if "referer" = req.getParameter("field")?if_exists>selected</#if>>访问页面</option>
        </select>
        <input id="wd" name="wd" type="text" class="t" size="40" value="${req.getParameter("wd")?if_exists}<#--${req.getParameter("value")?if_exists?url('iso-8859-1')}-->"/>
        <input type="submit" value="查找" name="search" class="b"/>
      	<input type="hidden" name="start" value="0" id="start"/>
      </form>  
	  </div>
    </div>
  </div>
</div>
<!-- end #log search -->
<div class="box1">
  <form action="#" method="post" id="stats">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:504px;">
      <div class="d" style="width:80px;">访问用户</div>
      <!--<div class="d" style="width:200px;">浏览器</div>-->
      <div class="d" style="width:330px;">访问地点</div>
      <div class="d" style="width:90px;">访问时间</div>
    </div>
    <div class="llbox" style="width:40px;"> </div>
    <div class="llbox" style="border-right:0px;overflow :hidden; padding-left:20px;">访问页面</div>
  </div>
  <#list stats as stat>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:504px; ">
      <div class="d" style="width:80px;">${stat.username}</div>
      <!--<div class="d" style="width:200px;">${stat.agent[0..25]}</div>-->
      <div class="d" style="width:330px;">
      <#-- <#assign ipL=stat.place?index_of("(")> ${stat.place[0..(ipL-1)]}-->
      <a href="#" title="${stat.place}">${stat.place}</a></div>
      <div class="d" style="width:90px;">${stat.viewTime?date}</div>
    </div>
    <div class="llbox" style="width:40px;">
      <#-- ${stat_index+1}. --><input name="id" type="checkbox" value="${stat.id}" class="cb">
    </div>
    <div class="llbox" style="text-align:left;border-right:0px;"><a href="#" onMouseOver="ddrivetip('${stat.agent?js_string}',300,'${base}/scripts/tooltip/arrow.gif');" onMouseOut="hideddrivetip();">${stat.referer}</a></div>
  </div>
  </#list>
  </form>
</div>
<div class="box1 dashed" style="clear:both; height:22px; padding:4px; margin-top:4px;">
  <div>
    <div class="lrbox" style="width:460px;margin:0px;line-height:150%;">
		<!-- pager -->
		<#-- ${req.getParameter("value")?if_exists}
		<@html.pager pagination=pagination url=req.requestURI+"?"/> -->
		<@fn.pager pagination=pagination/>
    </div>
    <div class="llbox" style="border-right:0px;">
      <input name="d" type="checkbox" value="" class="cb" onClick="System.checkedAll(this,'stats');"/>
      <input type="button" value="删除" class="tb" onClick="System.batchExecute('stats','您确认删除选中的访问记录吗？','statManage!delete.action');"/>
      <input type="button" value="清空全部记录" class="tb" onClick="Util.del('您确认清空所有的访问记录吗？','statManage!deleteAll.action');"/>
    </div>
  </div>
</div>
</@layout.html>
