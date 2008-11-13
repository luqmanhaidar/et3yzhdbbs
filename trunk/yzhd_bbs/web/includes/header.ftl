<#assign isLogo = application["basic"]["logo"]?exists>
<DIV id=top>
<DIV class=logo><#if isLogo><a href="${application["basic"]["bbsDomain"]?if_exists}"><img width="147" height="61" src="${application["basic"]["logo"]?if_exists}" alt="网站LOGO"/></a></#if></DIV>
<DIV class=banner>
	  <#assign isSession = Session["sessionUser"]?exists>
	  <#assign sessionUser = Session["sessionUser"]?if_exists>
	  <#if isSession> 
	   <#assign adminRole = RoleSingleton.getInstance().getRole(RoleSingleton.getInstance().getRoleIdByName(sessionUser.username))?if_exists>
	   </#if>
<#if application["advertise"]["ad"]["logo"]?exists>
<A href="${application["advertise"]["ad"]["url"]?if_exists}" 
target=_blank><IMG alt="" src="${application["advertise"]["ad"]["logo"]?if_exists}" border="0" width="750" height="100"></A>
<#else>
<IMG alt="" src="images/adview.gif" border="0" width="760" height="90">
</#if>

</DIV></DIV>
