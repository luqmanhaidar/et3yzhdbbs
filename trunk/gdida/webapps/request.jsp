<%@ page contentType="text/html;charset=GBK" %> 
<%@ page import = "java.util.*"%>
<%@ page import = "java.security.Principal"%>
<%
//设置页面不缓存
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<table>
<tr><%="<td>request.getAuthType():</td><td>"+request.getAuthType()%></td></tr>
<tr><%="<td>request.getCharacterEncoding():</td><td>"+request.getCharacterEncoding()%></td></tr>
<tr><%="<td>request.getContentLength():</td><td>"+request.getContentLength()%></td></tr>
<tr><%="<td>request.getContentType():</td><td>"+request.getContentType()%></td></tr>
<tr><%="<td>request.getContextPath():</td><td>"+request.getContextPath()%></td></tr>
<tr><%="<td>request.getLocalAddr():</td><td>"+request.getLocalAddr()%></td></tr>
<tr><%="<td>request.getLocalName():</td><td>"+request.getLocalName()%></td></tr>
<tr><%="<td>request.getLocalPort():</td><td>"+request.getLocalPort()%></td></tr>
<tr><%="<td>request.getMethod():</td><td>"+request.getMethod()%></td></tr>
<tr><%="<td>request.getPathInfo():</td><td>"+request.getPathInfo()%></td></tr>
<tr><%="<td>request.getPathTranslated():</td><td>"+request.getPathTranslated()%></td></tr>
<tr><%="<td>request.getProtocol():</td><td>"+request.getProtocol()%></td></tr>
<tr><%="<td>request.getQueryString():</td><td>"+request.getQueryString()%></td></tr>
<tr><%="<td>request.getRemoteAddr():</td><td>"+request.getRemoteAddr()%></td></tr>
<tr><%="<td>request.getRemoteHost():</td><td>"+request.getRemoteHost()%></td></tr>
<tr><%="<td>request.getRemotePort():</td><td>"+request.getRemotePort()%></td></tr>
<tr><%="<td>request.getRemoteUser():</td><td>"+request.getRemoteUser()%></td></tr>
<tr><%="<td>request.getRequestedSessionId():</td><td>"+request.getRequestedSessionId()%></td></tr>
<tr><%="<td>request.getRequestURI():</td><td>"+request.getRequestURI()%></td></tr>
<tr><%="<td>request.getScheme():</td><td>"+request.getScheme()%></td></tr>
<tr><%="<td>request.getServerName():</td><td>"+request.getServerName()%></td></tr>
<tr><%="<td>request.getServerPort():</td><td>"+request.getServerPort()%></td></tr>
<tr><%="<td>request.getServletPath():</td><td>"+request.getServletPath()%></td></tr>
<tr><%="<td>request.hashCode():</td><td>"+request.hashCode()%></td></tr>
<tr><%="<td>request.isRequestedSessionIdFromCookie():</td><td>"+request.isRequestedSessionIdFromCookie()%></td></tr>
<tr><%="<td>request.isRequestedSessionIdFromURL():</td><td>"+request.isRequestedSessionIdFromURL()%></td></tr>
<tr><%="<td>request.isRequestedSessionIdValid():</td><td>"+request.isRequestedSessionIdValid()%></td></tr>
<tr><%="<td>request.isSecure():</td><td>"+request.isSecure()%></td></tr>
<tr><%="<td>application.getRealPath:</td><td>"+application.getRealPath("")%></td></tr>
<%
Enumeration e, es;
e = request.getHeaderNames();
while (e.hasMoreElements()){
	String s = (String)e.nextElement();
%>
<tr><%="<td>(Header)"+s+":</td><td>"+request.getHeader(s)%></td></tr>
<%
}
e = request.getParameterNames();
while (e.hasMoreElements()){
	String s = (String)e.nextElement();
%>
<tr><%="<td>(Parameter)"+s+":</td><td>"+request.getParameter(s)%></td></tr>
<%
}
e = request.getAttributeNames();
while (e.hasMoreElements()){
	String s = (String)e.nextElement();
%>
<tr><%="<td>(Attribute)"+s+":</td><td>"+request.getAttribute(s)%></td></tr>
<%
}
Cookie[] c = request.getCookies();
for (int i=0;c!=null&&i<c.length;i++){
%>
<tr><%="<td>cookies["+i+"].getName():</td><td>"+c[i].getName()%></td></tr>
<tr><%="<td>cookies["+i+"].getValue():</td><td>"+c[i].getValue()%></td></tr>
<tr><%="<td>cookies["+i+"].getPath():</td><td>"+c[i].getPath()%></td></tr>
<tr><%="<td>cookies["+i+"].getVersion():</td><td>"+c[i].getVersion()%></td></tr>
<tr><%="<td>cookies["+i+"].getComment():</td><td>"+c[i].getComment()%></td></tr>
<tr><%="<td>cookies["+i+"].getDomain():</td><td>"+c[i].getDomain()%></td></tr>
<tr><%="<td>cookies["+i+"].getMaxAge():</td><td>"+c[i].getMaxAge()%></td></tr>
<tr><%="<td>cookies["+i+"].getSecure():</td><td>"+c[i].getSecure()%></td></tr>
<%
}
e = request.getLocales();
while (e.hasMoreElements()){
	Locale s = (Locale)e.nextElement();
%>
<tr><%="<td>s.getCountry():</td><td>"+s.getCountry()%></td></tr>
<tr><%="<td>s.getDisplayCountry():</td><td>"+s.getDisplayCountry()%></td></tr>
<tr><%="<td>s.getDisplayLanguage():</td><td>"+s.getDisplayLanguage()%></td></tr>
<tr><%="<td>s.getDisplayName():</td><td>"+s.getDisplayName()%></td></tr>
<tr><%="<td>s.getDisplayVariant():</td><td>"+s.getDisplayVariant()%></td></tr>
<tr><%="<td>s.getISO3Country():</td><td>"+s.getISO3Country()%></td></tr>
<tr><%="<td>s.getISO3Language():</td><td>"+s.getISO3Language()%></td></tr>
<tr><%="<td>s.getLanguage():</td><td>"+s.getLanguage()%></td></tr>
<tr><%="<td>s.getVariant():</td><td>"+s.getVariant()%></td></tr>
<%
}
Principal p = request.getUserPrincipal();
%>
<tr><%="<td>request.getUserPrincipal():</td><td>"+p%></td></tr>
<%
e = session.getAttributeNames();
while (e.hasMoreElements()){
	String s = (String)e.nextElement();
%>
<tr><%="<td>(Session.Attribute)"+s+":</td><td>"+session.getAttribute(s)%></td></tr>
<%
}
%>
<tr><%="<td>session.getCreationTime():</td><td>"+session.getCreationTime()%></td></tr>
<tr><%="<td>session.getId():</td><td>"+session.getId()%></td></tr>
<tr><%="<td>session.getLastAccessedTime():</td><td>"+session.getLastAccessedTime()%></td></tr>
<tr><%="<td>session.getMaxInactiveInterval():</td><td>"+session.getMaxInactiveInterval()%></td></tr>
</body>
</html>