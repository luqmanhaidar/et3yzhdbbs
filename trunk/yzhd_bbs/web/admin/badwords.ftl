<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="脏话过滤列表">
  <div class="box1">
    <div style=" background-color: #f7f7f7; height:26px;">
      <div class="lrbox" style="width:404px;">
        <div class="d" style="width:300px;">转换后的文字</div>
        <div class="d" style="width:100px;">操作</div>
      </div>
      <div class="llbox" style="border-right:0px;padding-left:20px;">待转化文字</div>
    </div>
    <#list badwords as badword>
    <div class="lbox1" style="padding:0px;">
      <div class="lrbox" style="width:404px; ">
        <div class="d" style="width:300px;">${badword.replaceStr}</div>
        <div class="d" style="width:100px;">
          <input type="button" name="" class="tb" value="修改" onClick="window.location.href='badwordManage!select.action?id=${badword.id}';"/>
          <input type="button" name="" class="tb" value="删除" onClick="window.location.href='deleteBadword.action?id=${badword.id}';"/>
        </div>
      </div>
      <div class="llbox" style="text-align:left;border-right:0px;">${badword.oldStr}</div>
    </div>
    </#list>
  </div>
</@layout.html>