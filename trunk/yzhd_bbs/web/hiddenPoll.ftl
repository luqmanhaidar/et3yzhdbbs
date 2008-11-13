<html>
<head>
<title>poll信息输出</title>
<script type="text/javascript">
	//<![CDATA[
	<#if actionMessage?exists>
 	alert("${actionMessage}");
	</#if>
	<#if warnMessage?exists>
 	alert("${warnMessage}");
	</#if>	
	window.onload = function(){
		<#if poll?exists>
		parent.document.getElementById("poll").innerHTML = document.getElementById("poll").innerHTML;
		</#if>
	}	
	//]]>
</script>
</head>
<body>
<#if poll?exists>
   <div id="poll"> 
   <table cellspacing="0" cellpadding="3" border="0" id="vote">
       <tbody>   
    <tr>
    	<td colspan="2">投票标题: ${poll.content}</td>
    </tr>
    <#assign totalVote = 0>
     <#list poll.pollResults as pollResult>
     	 <#assign totalVote = totalVote + pollResult.votes>
     </#list>
     
      <#assign pollNum=1>
     <#list poll.pollResults as pollResult>
    	<tr>
    		<td width="40%" style="padding-left: 6px;">${pollNum}.<input type="radio" name="id" value="${pollResult.id}"/>${pollResult.optionText}</td>
    		<td width="60%"><img src="skins/default/poll/bar${pollResult_index+1}.gif" width="<#if totalVote==0>0%<#else>${((pollResult.votes?int*350)/totalVote)}px</#if>" height="11"> ${pollResult.votes} 票(<#if totalVote==0>0%<#else>${((pollResult.votes?int*100)/totalVote)}%</#if>)</td>
    	</tr>
    	<#assign pollNum=pollNum+1>
    </#list>
    
    	<tr>
    		<td colspan="2">
    			<input type="hidden" value="true" name="IsVote"/>
    			<input type="submit" value="投 票" name="agree"/>
				
			</td>
		</tr>
	
	</tbody>
</table>
</div>
</#if>
</body>
</html>