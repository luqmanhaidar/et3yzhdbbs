<#--
<select><option></option></select>
-->
<#macro select
	list multiple="" headerKey="" headerValue="" listKey="" listValue=""
	label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon="£º" hasColon="true"
	id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
	>
<#include "control.ftl"/><#rt/>
<select<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if multiple!=""> multiple="${multiple}"</#if><#rt/>
<#include "common-attributes.ftl"/><#rt/>
<#include "scripting-events.ftl"/><#rt/>
/><#rt/>
<#if headerKey!="" || headerValue!="">
	<option value="${headerKey}"<#if headerKey == (name?eval)!""> selected="selected"</#if>>${headerValue}</option><#t/>
</#if>
<#if list?is_sequence>
	<#if listKey!="" && listValue!="">
		<#list list as item>
			<option value="${item[listKey]}"<#if name!="" && item[listKey].equals((name?eval)!)> selected="selected"</#if>>${item[listValue]!}</option><#t/>
		</#list>
	<#else>
		<#list list as item>
			<option value="${item}"<#if item.equals((name?eval)!)> selected="selected"</#if>>${item}</option><#t/>
		</#list>
	</#if>
<#else>
	<#list list?keys as key>
		<option value="${key}"<#if key==(name?eval)!> selected="selected"</#if>>${list[key]}</option><#t/>
	</#list>
</#if>
</select>
<#include "control-close.ftl"/><#rt/>
</#macro>
