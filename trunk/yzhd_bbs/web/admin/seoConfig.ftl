<#import "/lib/layout.ftl" as layout>
<@layout.html title="设置搜索引擎优化">
<!-- seo info begin -->
<div class="box1">
  <div class="title"> 搜索引擎优化设置 </div>
  <form action="seoConfig!set.action" method="post" id="setSeoForm" onSubmit="return Validator.validate(this,'admin');">
   <div class="content">
      <div class="ibox" style="height:128px;">
        <div class="it">网站标题: * <br/>
        <span class="red">网站的关键字(meta keywords信息)</span></div>
        <div class="iv" style="height:120px;">
          <textarea name="title" cols="60" style="height:120px;">${propertyMap["title"]}</textarea><br/>
           </div>
      </div>    
      <div class="ibox" style="height:128px;">
        <div class="it">站点关键字: * <br/><span class="red">网站的关键字(meta keywords信息)</span></div>
        <div class="iv" style="height:120px;">
          <textarea name="keywords" cols="60" style="height:120px;">${propertyMap["keywords"]}</textarea><br/>
        </div>
      </div>
      <div class="ibox" style="height:128px;">
        <div class="it">网站描述: * <br/><span class="red">网站介绍(meta description信息)</span></div>
        <div class="iv" style="height:120px;">
          <textarea name="description" cols="60" style="height:120px;">${propertyMap["description"]}</textarea><br/>
        </div>
      </div>	  	  	  	  	  
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="submit" value="设 定" name="agree" class="b"/>
      </div>
    </div>
  </form>
</div>
<!-- end #seo info -->
</@layout.html>