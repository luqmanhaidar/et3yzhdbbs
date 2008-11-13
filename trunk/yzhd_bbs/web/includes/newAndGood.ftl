<#assign newTopics = TopicSingleton.getInstance().getNewlyTopic()>
<#assign goodTopics = TopicSingleton.getInstance().getGoodTopic()>
<div class="ather">
	<div class="con1">
		<div class="tit">最新话题</div>
		<div class="info">
			<ul>
				<table id="uctlTopic_TopNewTopic1_rptHotTopic" style="border-width: 0px; width: 100%; border-collapse: collapse;" border="0" cellspacing="0">
					<tbody>
					<#assign newc=0>
					<#list newTopics as topic>
					<#if newc==0>
						<tr>
					</#if>
						<td>
							<li>
								<a class="black_1" href="topic.action?topicId=${topic.id}">${topic.getAutoTitle(13)}</a>
							</li>
				    	</td>
				    <#if newc==1>
						</tr>
					</#if>
					<#if newc==0>
					<#assign newc=1>	
					<#else>
					<#assign newc=0>
					</#if>
					</#list>				
					</tbody>
				</table>
			</ul>
		</div>
	</div>
<div class="con2">
<div class="tit">精华主题<a href="#"></a></div>
<div class="info">
<ul>
   	<table id="uctlTopic_TopNewTopic1_rptNewTopic" style="width: 100%; border-collapse: collapse;" border="0" cellspacing="0">
		<tbody>
			<#assign newc=0>
					<#list goodTopics as topic>
					<#if newc==0>
						<tr>
					</#if>
						<td>
							<li>
								<a class="black_1" href="topic.action?topicId=${topic.id}">${topic.shortTitle}</a>
							</li>
				    	</td>
				    <#if newc==1>
						</tr>
					</#if>
					<#if newc==0>
					<#assign newc=1>	
					<#else>
					<#assign newc=0>
					</#if>
					</#list>
		</tbody>
	</table>
</ul>
</div>
</div>
</div>