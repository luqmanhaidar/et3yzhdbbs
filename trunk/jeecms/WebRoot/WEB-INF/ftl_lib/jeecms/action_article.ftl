<#--
文章列表（自定义内容）
	chnlId：栏目ID。（默认：当前栏目ID）
	searchKey：搜索标题、tags和描述。用于搜索页。（默认空）
	orderBy：排序方式。【0：发布时间降序；1：发布时间升序；2：点击次数降序；3：点击次数升序】（默认0）
	recommend：是否推荐。【0：全部；1：推荐文章】（默认0）

	style：标签内部样式。如果指定sysContent或userContent，则该项无效。【1：普通列表；】（默认1）
-->
<#macro ArtiList chnlId=chnl.id attr='' searchKey='' hasImg='0' recommend='0' orderBy='0'
	titLen='20' target='0' headMark='0' lineHeight='' bottomLine='0' ctgForm='0' ctgClass='' dateFormat='0' datePosition='1'
	picWidth='24.9' picHeight='110'
	rollDisplayHeight='28' rollLineHeight='28' rollCols='1' rollSpeed='1' isSleep='1' rollSleepTime='50' rollCount='1' rollSpan='1'
	flashWidth='296' flashHeight='200' textHeight='20'
	isPage='0' count='20' firstResult='0' pageNo=pageNo
	style='1' inner='0' isLoop='1' cssClass='' cssStyle=''
	sysTpl='1' sysContent='0' userContent='' sysPage='0' userPage='' upSolution='' upWebRes='' pageClass='' pageStyle='' custom=[]>
<#if inner=='0'>
	<#local customs = "">
	<#list custom as s>
		<#local customs = customs+s>
		<#if s_has_next><#local customs = customs+"|"></#if>
	</#list>
	<@s.action name='ArtiList' namespace='/jeecms/tag/article' executeResult='true'
		chnlId=chnlId attr=attr searchKey=searchKey hasImg=hasImg recommend=recommend orderBy=orderBy
		titLen=titLen target=target headMark=headMark lineHeight=lineHeight bottomLine=bottomLine ctgForm=ctgForm ctgClass=ctgClass dateFormat=dateFormat datePosition=datePosition
		picWidth=picWidth picHeight=picHeight
		rollDisplayHeight=rollDisplayHeight rollLineHeight=rollLineHeight rollCols=rollCols rollSpeed=rollSpeed isSleep=isSleep rollSleepTime=rollSleepTime rollCount=rollCount rollSpan=rollSpan
		flashWidth=flashWidth flashHeight=flashHeight textHeight=textHeight
		isPage=isPage count=count firstResult=firstResult pageNo=pageNo
		style=style cssClass=cssClass cssStyle=cssStyle
		sysTpl=sysTpl sysContent=sysContent userContent=userContent sysPage=sysPage userPage=userPage pageClass=pageClass pageStyle=pageStyle customs=customs
		/>
<#else>
	<@s.action name='ArtiListInner' namespace='/jeecms/tag/article' executeResult='false'
		chnlId=chnlId searchKey=searchKey hasImg=hasImg recommend=recommend orderBy=orderBy
		isPage=isPage count=count firstResult=firstResult pageNo=pageNo
		/>
	<#if isLoop=='1'>
  		<#list n_pagination.list as item>
			<#nested item,item_index,item_has_next/>
		</#list>
	<#else>
		<#nested n_pagination/>
	</#if>
</#if>
</#macro>