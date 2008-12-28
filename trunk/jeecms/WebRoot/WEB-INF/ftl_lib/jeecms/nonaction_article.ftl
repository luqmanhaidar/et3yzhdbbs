<#--文章内容-->
<#macro ArtiContent>
<#include arti.relPath(pageNo)/>
</#macro>
<#--文章上一篇、下一篇
	side：【pre：上一篇；next：下一篇】
	notExist：文章不存在时的提示信息。（默认：没有了）
-->
<#macro ArtiSide side notExist="没有了">
<#if side=="pre" && arti.pre??>
<a href="${arti.pre.url}" target="_blank" title="${arti.pre.title}">${arti.pre.tit(200)}</a>
<#elseif side=="next" && arti.next??>
<a href="${arti.next.url}" target="_blank" title="${arti.next.title}">${arti.next.tit(200)}</a>
<#else>
<span>${notExist}</span>
</#if>
</#macro>
