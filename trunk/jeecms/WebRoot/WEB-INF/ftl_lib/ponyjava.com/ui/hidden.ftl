<#--
<input type="hidden"/>
-->
<#macro hidden
	id="" name="" value=""
	>
<input type="hidden"<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if name!=""> name="${name}"</#if><#rt/>
<#if value!=""> value="${value}"<#elseif name!=""> value="${(name?eval)!}"</#if><#rt/>
/>
</#macro>
