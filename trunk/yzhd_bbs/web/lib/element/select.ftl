<#macro select name extra...>
<select name="${name}"<#list extra?keys as key> ${key}="${extra[key]} "</#list>>
	<#nested/>
</select>
</#macro> 