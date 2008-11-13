<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="加密关键字列表">
  <div class="box1">
    <div style=" background-color: #f7f7f7; height:26px;">
      <div class="lrbox" style="width:404px;">
        <div class="d" style="width:300px;">关键字</div>
        <div class="d" style="width:100px;">操作</div>
      </div>
      <div class="llbox" style="border-right:0px;padding-left:20px;">域名</div>
    </div>
	<#assign keys = securitys?keys>
	<#list keys as security>
    <div class="lbox1" style="padding:0px;">
      <div class="lrbox" style="width:404px; ">
        <div class="d" style="width:300px;">${security[key]}</div>
        <div class="d" style="width:100px;">
          <input type="button" name="" class="tb" value="查看代码" onClick="window.location.href='badwordManage!select.action?id=${badword.id}';"/>
        </div>
      </div>
      <div class="llbox" style="text-align:left;border-right:0px;">${key}</div>
    </div>
    </#list>
  </div>
</@layout.html>