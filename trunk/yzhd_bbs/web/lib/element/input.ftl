<#macro input name extra...>
<input name="${name}" <#list extra?keys as attr>${attr}="${extra[attr]}" </#list> />
</#macro>