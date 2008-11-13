<#macro pager pagination>
		<script type="text/javascript">
			//<![CDATA[	
			var validatePagination = function(){
				
			}
			//]]>
		</script>
        <form action="" method="post" onSubmit="validatePagination();">
        <span title="主题总数" style="padding: 0px 4px;">${pagination.totalRecord}</span><span title="本页主题" style="padding: 0px 4px;"><#if pagination.start = 0>1<#else>${pagination.start/pagination.range+1}</#if>/${pagination.totalPage}页</span>
        <span style="padding: 0px 4px;"><a href="?start=0"><img src="${base}/images/first.gif" alt="首页"/></a></span>
        <#if pagination.totalPage<=10>
        	<#if pagination.totalPage!=0>
		        <#-- 1...10 -->
		        <#list 1..pagination.totalPage as i>
				  <a href="?start=<#if pagination.start=(i-1)>0<#else>${(i-1)*pagination.range}</#if>" title="第${i}页" style="padding:0px 2px;"><#if pagination.start=((i-1)*pagination.range)><strong style="color:red">${i}</strong><#else>${i}</#if></a>
				</#list> 
        	</#if>			
		<#elseif pagination.totalPage&gt;10>	
			<#assign tempStart=((pagination.start/pagination.range)/10)?int>
			<#if tempStart!=((pagination.totalPage/10)?int)>
				<#if tempStart!=0>
					<span style="padding: 0px 4px;"><a href="?start=${((tempStart-1)*10)*pagination.range}" title="后十页"><img src="${base}/images/previous.gif" alt="前十页"/></a></span>
				</#if>
				<#-- middle page -->
				<#list 1..10 as i>
					<#assign pageLabel=(tempStart*10)+i>			
					<#assign start=((tempStart*10)+i-1)*pagination.range>				
					<a href="?start=${start}" title="第${pageLabel}页" style="padding:0px 2px;"><#if pagination.start=(((tempStart*10)+i-1)*pagination.range)><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
				</#list>
				<#-- if the page is 10*n -->
				<#if tempStart!=((pagination.totalPage/10)?int-1)>
					<span style="padding: 0px 4px;"><a href="?start=${((tempStart+1)*10)*pagination.range}" title="后十页"><img src="${base}/images/next.gif" alt="后十页"/></a></span>
				</#if>
			<#else>
				<#-- add previous.gif pic to return front 10 page -->
				<span style="padding: 0px 4px;"><a href="?start=${((tempStart-1)*10)*pagination.range}" title="前十页"><img src="${base}/images/previous.gif" alt="后十页"/></a></span>
				<#-- last page -->
				<#list 1..(pagination.totalPage%10)?int as i>
					<#assign pageLabel=(((tempStart*10)+i))>			
					<#assign start=((tempStart*10)+i-1)*pagination.range>				
					<a href="?start=${start}" title="第${pageLabel}页" style="padding:0px 2px;"><#if pagination.start=(((tempStart*10)+i-1)*pagination.range)><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
				</#list>				
			</#if>
		</#if>				
		<!-- <span style="padding: 0px 4px;"><img src="../images/next.gif" alt="后十页"/></span> -->
		<span style="padding: 0px 4px;"><a href="?start=${(pagination.totalPage-1)*pagination.range}" title="尾页"><img src="${base}/images/last.gif" alt="尾页"/></a></span>
	    <input type="text" name="page" size="1" value="1" class="t" />
        <input type="submit" value="GO" name="submit" class="b" />
      </form>
</#macro> 