<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="作成静态数据">
<script type="text/javascript">
	//<![CDATA[
	window.onload = function(){
		<#if actionMessage?exists>
			alert("${actionMessage?if_exists}");
		</#if>
	}
	//]]>
</script>
<!-- explain begin -->
<div class="box1 dashed">
  <h2>说  明</h2>
    <ul>
      <li>如果前台链接和公告数据没有显示，请进行如下的静态数据生成操作</li>
    </ul>
</div>
<!-- end #explain -->

<div class="box1">
  <div style=" background-color: #f7f7f7; height:26px;">
    <div class="lrbox" style="width:204px;">
      <div class="d" style="width:200px;">操作</div>
    </div>
	<div class="llbox" style="border-right:0px;overflow :hidden; padding-left:30px;">说明</div>
  </div>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:204px;">
	  <div class="d" style="width:200px;">
        <input type="button" name="" class="tb" value="生成公告静态数据" onClick="window.location.href='makeStatic!annouce.action';"/>
      </div>
    </div>
	<div class="llbox" style="border-right:0px;overflow :hidden; height:100%;">公告部分对应的静态script数据</div>	
  </div>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:204px;">
	  <div class="d" style="width:200px;">
        <input type="button" name="" class="tb" value="生成链接静态数据" onClick="window.location.href='makeStatic!link.action';"/>
      </div>
    </div>
	<div class="llbox" style="border-right:0px;overflow :hidden; height:100%;">友情链接对应的静态script数据</div>	
  </div>
  <div class="lbox1" style="padding:0px;">
    <div class="lrbox" style="width:204px;">
	  <div class="d" style="width:200px;">
        <input type="button" name="" class="tb" value="生成RSS数据" onClick="window.location.href='makeStatic!rss.action';"/>
      </div>
    </div>
	<div class="llbox" style="border-right:0px;overflow :hidden; height:100%;">RSS源xml文件生成</div>	
  </div>  
</div>
</@layout.html>