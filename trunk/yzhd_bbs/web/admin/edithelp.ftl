<#import "/lib/layout.ftl" as layout>
<@layout.html title="修改帮助">
  <!-- edit help begin -->
  <div class="box1">
    <div class="title"> 修改帮助 </div>
    <form action="updateHelp.action" method="post" id="help" onSubmit="return Validator.validate(this,'admin');">
      <input name="id" type="hidden" value="${help.id}"/>
      <div class="content">
        <div class="ibox">
          <div class="it">帮助标题: *</div>
          <div class="iv">
            <input name="title" type="text" class="t" value="${help.title}" size="40"/>
          </div>
        </div>
        <div class="ibox">
          <div class="it">帮助类别: * </div>
          <div class="iv">
            <select name="type" id="type">
			 <#list helpTypes as helpType>
              <option value="${helpType.id}">${helpType.name}</option>
              </#list>
            </select>
          </div>
        </div>
        <div class="ibox" style="height:408px;">
          <div class="it">帮助内容: * </div>
          <div class="iv" style="height:400px; width:76%">
            <textarea name="content" id="content" style="height:400px; width:100%">${help.content}</textarea>
          </div>
        </div>
      </div>
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="更新帮助" name="agree" class="b"/>
          <input type="button" value="返回" name="agree" class="b" onClick="history.back(-1);"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end # edit help -->
</div>
<script type="text/javascript" src="../scripts/FCKeditor/fckeditor.js"></script>
<script type="text/javascript">
	//<![CDATA[	
	
	// 初始时设置文本编辑器和标题以及选择框的值
	window.onload = function(){
	
		replaceMyTextarea("content","Default","",400);
		
		Forms.selected("type",${help.type});
		
	}
	//]]>
</script>
</@layout.html>
