<#--
<textarea name="textarea"></textarea>
-->
<#macro editor
	fckName="" name="" value="" height="200" noValue="false"
	fullPage="false"
	label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon="��" hasColon="true"
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
	>
<#if fckName=="">
<#local fckName=name?replace('.','_')>
</#if>
<#include "control.ftl"/><#rt/>
<script type="text/javascript">
var _fck_${fckName}=new FCKeditor('${name}');
_fck_${fckName}.BasePath='${base}/fckeditor/';
<#--_fck_${fckName}.Config["CustomConfigurationsPath"]="${base}/fckeditor/myconfig.js?"+(new Date()*1);-->
_fck_${fckName}.Config["CustomConfigurationsPath"]="${base}/fckeditor/myconfig.js?d="+(new Date()*1);
_fck_${fckName}.ToolbarSet='mydefault';
_fck_${fckName}.Height=${height};
<#if fullPage=="true">
_fck_${fckName}.Config["FullPage"]=true;
</#if>
_fck_${fckName}.Value="<#if value!="">${value?js_string}<#else>${(name?eval)!?js_string}</#if>";
_fck_${fckName}.Create();
</script>
<#include "control-close.ftl"/><#rt/>
</#macro>