<#import "/lib/layout.ftl" as layout>
<#import "/lib/function.ftl" as fn>
<@layout.html title="合并论坛">
<script type="text/javascript">
	//<![CDATA[
		function doSubmit(){
			if(confirm('合并论坛会删除源论坛信息，并将源论坛数据合并到目标论坛 \r\n 您确认合并论坛吗？')){
				$("uniteForm").submit();
			}
			else{
				return ;
			}
		}
	//]]>
  </script>
<div class="box1 dashed">
  <h2>合并论坛</h2>
    <ol>
      <li>合并论坛，会将被合并的论坛信息给删除</li>
    </ol>
</div>

<!-- basic info begin -->
<div class="box1">
  <div class="title"> 合并论坛 </div>
  <form action="uniteForum.action" method="get" id="uniteForm">
    <div class="content">
      <div class="ibox" style="padding-left:50px; height:26px; line-height:150%;"> 
	 	<div class="it" style="width:100%;">
	    合并论坛
	     <select name="sourceForum">
	    	<#list forums as forum>
            <option value="${forum.id}">${""?left_pad((forum.depth-1)*2,"—")}&nbsp;${forum.name}</option>
            </#list>
          </select>
            到  
          <select name="toForum">
	    	<#list forums as forum>
            <option value="${forum.id}">${""?left_pad((forum.depth-1)*2,"—")}&nbsp;${forum.name}</option>
            </#list>
          </select> 
	    </div> 
      </div>
    </div>
    <!-- end #content -->
    <div class="box3" style="text-align:center; clear:both">
      <div>
        <input type="button" value="合并论坛" name="agree" class="b" onClick="doSubmit();"/>
      </div>
    </div>
  </form>
</div>
<!-- end #basic info -->

</@layout.html>