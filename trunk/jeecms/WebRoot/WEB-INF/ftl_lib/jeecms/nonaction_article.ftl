<#--��������-->
<#macro ArtiContent>
<#include arti.relPath(pageNo)/>
</#macro>
<#--������һƪ����һƪ
	side����pre����һƪ��next����һƪ��
	notExist�����²�����ʱ����ʾ��Ϣ����Ĭ�ϣ�û���ˣ�
-->
<#macro ArtiSide side notExist="û����">
<#if side=="pre" && arti.pre??>
<a href="${arti.pre.url}" target="_blank" title="${arti.pre.title}">${arti.pre.tit(200)}</a>
<#elseif side=="next" && arti.next??>
<a href="${arti.next.url}" target="_blank" title="${arti.next.title}">${arti.next.tit(200)}</a>
<#else>
<span>${notExist}</span>
</#if>
</#macro>
