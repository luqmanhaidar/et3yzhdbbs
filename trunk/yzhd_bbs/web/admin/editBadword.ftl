<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="更新脏话过滤">
  <!-- create badword begin -->
  <div class="box1">
    <div class="title"> 更新脏话 </div>
    <form action="updateBadword.action" method="post" id="badwordForm" onSubmit="return Validator.validate(this,'admin');">
      <input type="hidden" name="id" value="${req.getParameter("id")}"/>
      <div class="content">
        <div class="ibox">
          <div class="it">待转化的文字: *</div>
          <div class="iv">
            <input name="oldStr" type="text" class="t" value="${badword.oldStr}" size="40"/>
            <span class="remark">请填写待转化文字</span> </div>
        </div>
		<div class="ibox">
          <div class="it">转换后的文字: *</div>
          <div class="iv">
            <input name="replaceStr" type="text" class="t" value="${badword.replaceStr}" size="40"/>
            <span class="remark">请填写转换后的文字</span> </div>
        </div>		
      </div>
      <!-- end #content -->
      <div class="box3" style="text-align:center; clear:both">
        <div>
          <input type="submit" value="更新脏话" name="agree" class="b"/>
        </div>
      </div>
    </form>
  </div>
  <!-- end # create badword -->
</@layout.html>
