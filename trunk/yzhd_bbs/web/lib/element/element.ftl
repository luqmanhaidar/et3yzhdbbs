<#macro input name extra...>
<input name="${name}"
 <#list extra?keys as attr>
	<#if attr = "value">
		${attr}="${extra[attr]?default('')}"
	<#else>
		${attr}="${extra[attr]}"
	</#if>
 </#list> />
</#macro>