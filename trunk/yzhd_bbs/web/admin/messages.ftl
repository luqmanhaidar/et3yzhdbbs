<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="短消息管理">
<script type="text/javascript">
	//<![CDATA[
	//]]>
</script>
<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:260px;">
      <div class="d" style="width:160px;">发送时间</div>
      <div class="d" style="width:100px;">操作</div>
    </div>
    <div class="llbox" style="border-right:0px;padding-left:30px;">消息标题</div>
  </div>
  <#list messages as message>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:260px;">
      <div class="d" style="width:160px;">${message.sendTime}</div>    
      <div class="d" style="width:100px;">
        <input type="button" name="" class="tb" value="修改" onClick="window.location.href='editMessage.html';"/>
        <input type="button" name="" class="tb" value="删除" onClick="DeleteFrame.location.href='delete.html';"/>
      </div>
    </div>
    <div class="llbox" style="border-right:0px;overflow :hidden;height:100%;">
		<a href="#" onMouseOver="ddrivetip('${message.content?js_string}',300,'${base}/scripts/tooltip/arrow.gif');" onMouseOut="hideddrivetip();">${message.title}</a>
	</div>
  </div>
  </#list>
</div>
</@layout.html>
