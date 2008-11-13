<#macro pager pagination>
<script type="text/javascript">
	// 分页检索
	function doPagination(start){
		// 设定起始页
		$("start").value = start;
		// 检索
		//doSearch();
		$("searchForm").submit();
	}
			
	// 检索内容		
	function isRightPager( totalPage, range ){
		var pager = $F("pager");
		var RegExpNum = /^\d+$/;
		if(!RegExpNum.test(pager)){
			alert("请输入页数.");
			return;
		}
		if( pager < 1 ){
			alert("页数必须大于0");
			return;
		}
		else{
			if( pager > totalPage ){
				alert("您只能输入1~"+totalPage+"的数");
				return;
			}
		}
		doPagination((pager-1)*range);
	}
</script>
		<form onSubmit="return false;">
        <span title="记录总数" style="padding: 0px 4px;">记录总数: <strong>${pagination.totalRecord}</strong></span><span style="padding: 0px 4px;"><strong><#if pagination.start = 0>1<#else>${pagination.start/pagination.range+1}</#if></strong>/<strong>${pagination.totalPage}</strong>页</span>
        <span style="padding: 0px 4px;"><a href="javascript:doPagination(0);"><img src="${base}/images/first.gif" alt="首页"/></a></span>
        <#setting number_format="0.######"/>
        <#if pagination.totalPage<=10>
        	<#if pagination.totalPage!=0>
		        <#-- 1...10 -->
		        <#list 1..pagination.totalPage as i>
				  <a href="javascript:doPagination(<#if pagination.start=(i-1)>0<#else>${(i-1)*pagination.range}</#if>);" title="第${i}页" style="padding:0px 2px;"><#if pagination.start=((i-1)*pagination.range)><strong style="color:red">${i}</strong><#else>${i}</#if></a>
				</#list>
        	</#if>		
		<#elseif pagination.totalPage&gt;10>
			<#assign tempStart=((pagination.start/pagination.range)/10)?int>
			<#if tempStart!=((pagination.totalPage/10)?int)>
				<#if tempStart!=0>
					<span style="padding: 0px 4px;"><a href="javascript:doPagination(${((tempStart-1)*10)*pagination.range});" title="前十页"><img src="${base}/images/previous.gif" alt="前十页"/></a></span>
				</#if>
				<#-- middle page -->
				<#list 1..10 as i>
					<#assign pageLabel=(tempStart*10)+i>			
					<#assign start=((tempStart*10)+i-1)*pagination.range>				
					<a href="javascript:doPagination(${start});" title="第${pageLabel}页" style="padding:0px 2px;"><#if pagination.start=(((tempStart*10)+i-1)*pagination.range)><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
				</#list>
				<#-- if the page is 10*n -->
				<#if tempStart!=((pagination.totalPage/10)?int)>
					<span style="padding: 0px 4px;"><a href="javascript:doPagination(${((tempStart+1)*10)*pagination.range});" title="后十页"><img src="${base}/images/next.gif" alt="后十页"/></a></span>
				</#if>
			<#else>
				<#-- add previous.gif pic to return front 10 page -->
				<span style="padding: 0px 4px;"><a href="javascript:doPagination(${((tempStart-1)*10)*pagination.range});" title="前十页"><img src="${base}/images/previous.gif" alt="后十页"/></a></span>
				<#-- last page -->
				<#list 1..(pagination.totalPage%10)?int as i>
					<#assign pageLabel=(((tempStart*10)+i))>	
					<#assign start=((tempStart*10)+i-1)*pagination.range>				
					<a href="javascript:doPagination(${start});" title="第${pageLabel}页" style="padding:0px 2px;"><#if pagination.start=(((tempStart*10)+i-1)*pagination.range)><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
				</#list>				
			</#if>
		</#if>				
		<!-- <span style="padding: 0px 4px;"><img src="../images/next.gif" alt="后十页"/></span> -->
		<span style="padding: 0px 4px;"><a href="javascript:doPagination(${(pagination.totalPage-1)*pagination.range});" title="尾页"><img src="${base}/images/last.gif" alt="尾页"/></a></span>	    
	    <input type="text" id="pager" name="pager" size="1" value="" class="t" />
        <input type="button" value="GO" name="search" class="tb" onClick="isRightPager(${pagination.totalPage},${pagination.range});"/>
      </form>           
</#macro>

<#macro simplePager pagination>
		<#assign url = (req.requestURI+"?")>
		<#list req.parameterNames as parameterName>
			<#if parameterName!="start" && parameterName!="search" >
			<#assign url = (url + parameterName + "=" + req.getParameter(parameterName) + "&")>
			<#elseif parameterName="value">
			<#assign url = (url + parameterName + "=" + req.getParameter(parameterName)?url + "&")>
			</#if>
		</#list>
		<form action="#">
        <span title="记录总数" style="padding: 0px 4px;">记录总数: <strong>${pagination.totalRecord}</strong></span><span style="padding: 0px 4px;"><strong><#if pagination.start = 0>1<#else>${pagination.start/pagination.range+1}</#if></strong>/<strong>${pagination.totalPage}</strong>页</span>
        <span style="padding: 0px 4px;"><a href="${url}start=0"><img src="${base}/images/first.gif" alt="首页"/></a></span>
        <#setting number_format="0.######"/>
        <#if pagination.totalPage<=10>
        	<#if pagination.totalPage!=0>
		        <#-- 1...10 -->
		        <#list 1..pagination.totalPage as i>
				  <a href="${url}start=<#if pagination.start=(i-1)>0<#else>${(i-1)*pagination.range}</#if>" title="第${i}页" style="padding:0px 2px;"><#if pagination.start=((i-1)*pagination.range)><strong style="color:red">${i}</strong><#else>${i}</#if></a>
				</#list> 
        	</#if>			
		<#elseif pagination.totalPage&gt;10>	
			<#assign tempStart=((pagination.start/pagination.range)/10)?int>
			<#if tempStart!=((pagination.totalPage/10)?int)>
				<#if tempStart!=0>
					<span style="padding: 0px 4px;"><a href="${url}start=${((tempStart-1)*10)*pagination.range}" title="后十页"><img src="${base}/images/previous.gif" alt="前十页"/></a></span>
				</#if>
				<#-- middle page -->
				<#list 1..10 as i>
					<#assign pageLabel=(tempStart*10)+i>			
					<#assign start=((tempStart*10)+i-1)*pagination.range>				
					<a href="${url}start=${start}" title="第${pageLabel}页" style="padding:0px 2px;"><#if pagination.start=(((tempStart*10)+i-1)*pagination.range)><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
				</#list>
				<#-- if the page is 10*n -->
				<#if tempStart!=((pagination.totalPage/10)?int)>
					<span style="padding: 0px 4px;"><a href="${url}start=${((tempStart+1)*10)*pagination.range}" title="后十页"><img src="${base}/images/next.gif" alt="后十页"/></a></span>
				</#if>
			<#else>
				<#-- add previous.gif pic to return front 10 page -->
				<span style="padding: 0px 4px;"><a href="${url}start=${((tempStart-1)*10)*pagination.range}" title="前十页"><img src="${base}/images/previous.gif" alt="后十页"/></a></span>
				<#-- last page -->
				<#list 1..(pagination.totalPage%10)?int as i>
					<#assign pageLabel=(((tempStart*10)+i))>			
					<#assign start=((tempStart*10)+i-1)*pagination.range>				
					<a href="${url}start=${start}" title="第${pageLabel}页" style="padding:0px 2px;"><#if pagination.start=(((tempStart*10)+i-1)*pagination.range)><strong style="color:red">${pageLabel}</strong><#else>${pageLabel}</#if></a>	
				</#list>				
			</#if>
		</#if>				
		<!-- <span style="padding: 0px 4px;"><img src="../images/next.gif" alt="后十页"/></span> -->
		<span style="padding: 0px 4px;"><a href="${url}start=${(pagination.totalPage-1)*pagination.range}" title="尾页"><img src="${base}/images/last.gif" alt="尾页"/></a></span>
		<script type="text/javascript">
			//<![CDATA[	
			function isRightPager(){
				var pager = document.getElementById("pager").value;
				if( pager < 1 ){
					alert("页数必须大于0");
					return;
				}
				else{
					if( pager > ${pagination.totalPage} ){
						alert("您只能输入1~${pagination.totalPage}的数");
						return;
					}
				}
				window.location.href="${url}start="+(pager-1)*${pagination.range};
			}
			//]]>
		</script>	    
	    <input type="text" id="pager" name="pager" size="1" value="" class="t" />
        <input type="button" value="GO" name="search" class="tb" onClick="isRightPager();"/>
      </form>      
</#macro>

<#macro message content type>
  <!-- message begin -->
  <div class="box1">
    <div class="title"> 
    <#if type?exists>
	    <#switch type>
		  	<#case "success">操作成功信息<#break>
			<#case "info">论坛提示信息<#break>
		  	<#case "warn">论坛警告信息<#break>
		</#switch> 
	<#else>操作成功信息</#if>
	</div>
    <div class="content">
      <div style="padding:8px;">
      	<ul>
	      	<li>
			${content}
			</li>
		</ul>
		<div style="text-align:center;margin:8px;"><a href="#" onClick="history.back(-1);return false;">返回到上一页</a> | <a href="index.action" title="首页">首页</a></div>
      </div>
    </div>
  </div>
  <!-- end #message -->
</#macro>

<#macro topicPagination topicId postNum>
	<#assign pageNum = 0>
	<#if (postNum+1)%10==0>
		<#assign pageNum = ((postNum+1)/10)?int>
	<#else>
		<#assign pageNum = ((postNum+1)/10)?int+1>
	</#if>
  	<#if pageNum gt 1>
  		<span style="font-weight:bold;">( <#list 1..pageNum as i>
  			<a href="topic.action?topicId=${topicId}&amp;start=${(i-1)*10}" style="color:#000;">${i}</a> 
   		</#list> )</span>
  	</#if>  
</#macro>