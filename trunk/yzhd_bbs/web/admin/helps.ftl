<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="帮助管理">
  <!-- help search begin -->
  <div class="box1">
    <div class="title"> 检索帮助 </div>
    <div class="content">
    	<script type="text/javascript">
			//<![CDATA[	
			//]]>
		</script>      
      <form action="helps.action" method="post" name="searchForm" id="searchForm">
      <div class="ibox" style="border-bottom:0px;">
        <div class="it">帮助标题: *</div>
        <div class="iv">
          <input name="wd" type="text" class="t" size="40" value="${req.getParameter("wd")?if_exists}"/>
          <input type="hidden" name="start" value="0" id="start"/>
          <input type="submit" value="查找" name="search" class="b"/>
        </div>
      </div>
      </form>
    </div>
  </div>
  <!-- end #help search -->
  <!-- begin list help -->
  <div class="box1">
    <div style=" background-color: #f7f7f7; height:26px;">
      <div class="lrbox" style="width:344px;">
        <div class="d" style="width:100px;">调用方式</div>
        <div class="d" style="width:140px;">分类</div>
        <div class="d" style="width:100px;">操作</div>
      </div>
      <div class="llbox" style="border-right:0px; padding-left:20px;">帮助标题</div>
    </div>
    <#list helps as help>
    <div class="lbox1" style="padding:0px;">
      <div class="lrbox" style="width:344px; ">
        <div class="d" style="width:100px;"><#if help.isHand=1><span class="red">手动</span><#else>自动</#if></div>
        <div class="d" style="width:140px;">${help.common.name}</div>
        <div class="d" style="width:100px;">
          <input type="button" name="" class="tb" value="修改" onClick="window.location.href='${base}/admin/editHelp.action?helpId=${help.id}';"/>
          <input type="button" name="" class="tb" value="删除" onClick="Util.del('您确认进行删除吗？','${base}/admin/deleteHelp.action?helpId=${help.id}');"/>
        </div>
      </div>
      <div class="llbox" style="text-align:left;border-right:0px;"><a href="${base}/admin/editHelp.action?helpId=${help.id}" title="${help.title}">${help.title}</a></div>
    </div>
    </#list>
  </div>
  <!-- end #list help -->
  <!-- begin #pagination -->
  <div class="box1" style="clear:both;border:0px; height:26px; margin-top:4px;">
    <div class="dashed" style="float:right; margin:0px;height:18px; padding:4px;line-height:150%;">
    	<@fn.pager pagination=pagination/>
    </div>
  </div>
  <!-- end #pagination -->	
  <div class="box1" id="createHelp">
    <div class="title"> 添加帮助 </div>
    <form action="${base}/admin/createHelp.action" method="post" id="help" onSubmit="return Validator.validate(this,'admin');">
    <input type="hidden" name="urlArray" value="
     帮助管理:${base}/admin/helps.action,
     添加帮助:${base}/admin/helps.action#createHelp"/>
      <div class="content">
        <div class="ibox">
          <div class="it">帮助标题: *</div>
          <div class="iv">
            <input name="title" type="text" class="t" value="" size="60"/>
          </div>
        </div>
        <div class="ibox">
          <div class="it">帮助类别: * </div>
          <div class="iv">
            <select name="type">
              <#list helpTypes as helpType>
              <option value="${helpType.id}">${helpType.name}</option>
              </#list>
            </select>
          </div>
        </div>
        <div class="ibox" style="height:408px;">
          <div class="it">帮助内容: * </div>
          <div class="iv" style="height:400px; width:76%">
            <textarea name="content" id="content"　style="height:400px; width:100%"></textarea>
          </div>
        </div>
        <div class="ibox">
          <div class="it">手动调用: * </div>
          <div class="iv">
			<input type="checkbox" name="isHand" value="1"/><span class="required">选择手动调用时,不会在前台帮助里显示,只有管理员可以拷贝URL进行显示</span>
          </div>
        </div>        
      </div>
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="添加帮助" name="agree" class="b"/>
        </div>
      </div>
    </form>
  </div>
	<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
	<script type="text/javascript">
		//<![CDATA[	
		window.onload = function(){
			replaceMyTextarea("content","Default","..","400") ;
		}
		//]]>
	</script>
</@layout.html>
