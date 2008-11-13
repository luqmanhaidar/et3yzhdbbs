<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>

<FORM METHOD=POST ACTION="test.jsp?a=1">
<INPUT TYPE="text" NAME="aa">
<INPUT TYPE="text" NAME="bb">
<INPUT TYPE="text" NAME="cc">
<INPUT TYPE="text" NAME="dd">
<INPUT TYPE="text" NAME="dd">
<INPUT TYPE="submit" name="tttt" value="aa">
</FORM>

<script type="text/javascript"><!--
google_ad_client = "pub-4719823006842312";
google_ad_output = "textlink";
google_ad_format = "ref_text";
google_cpa_choice = "CAAQpaa1_wEaCJLRmDXibQ6BKJ2R4YcB";
google_ad_channel = "";
//--></script>
<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script>


<%
	//String[] strArray = request.getParameterValues("dd");
	//out.print("values length : " + strArray.length);
	//out.println("par values : " + request.getParameterValues("dd"));
	out.println("Protocol: " + request.getProtocol() + "<br>");
	out.println("Scheme: " + request.getScheme() + "<br>");
	out.println("Server Name: " + request.getServerName() + "<br>" );
	out.println("Server Port: " + request.getServerPort() + "<br>");
	out.println("Protocol: " + request.getProtocol() + "<br>");
	out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo() + "<br>");
	out.println("Remote Addr: " + request.getRemoteAddr() + "<br>");
	out.println("Remote Host: " + request.getRemoteHost() + "<br>");
	out.println("Character Encoding: " + request.getCharacterEncoding() + "<br>");
	out.println("Content Length: " + request.getContentLength() + "<br>");
	out.println("Content Type: "+ request.getContentType() + "<br>");
	out.println("Auth Type: " + request.getAuthType() + "<br>");
	out.println("HTTP Method: " + request.getMethod() + "<br>");
	out.println("Path Info: " + request.getPathInfo() + "<br>");
	out.println("Path Trans: " + request.getPathTranslated() + "<br>");
	out.println("Query String: " + request.getQueryString() + "<br>");
	out.println("Remote User: " + request.getRemoteUser() + "<br>");
	out.println("Session Id: " + request.getRequestedSessionId() + "<br>");
	out.println("Request URI: " + request.getRequestURI() + "<br>");
	out.println("Request URL: " + request.getRequestURL() + "<br>");
	out.println("Servlet Path: " + request.getServletPath() + "<br>");
	out.println("Accept: " + request.getHeader("Accept") + "<br>");
	out.println("Host: " + request.getHeader("Host") + "<br>"); 
	out.println("Referer : " + request.getHeader("Referer") + "<br>"); 
	out.println("Accept-Language : " + request.getHeader("Accept-Language") + "<br>"); 
	out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding") + "<br>"); 
	out.println("User-Agent : " + request.getHeader("User-Agent") + "<br>"); 
	out.println("Connection : " + request.getHeader("Connection") + "<br>"); 
	out.println("Cookie : " + request.getHeader("Cookie") + "<br>"); 
	out.println("Created : " + session.getCreationTime() + "<br>"); 
	out.println("LastAccessed : " + session.getLastAccessedTime() + "<br>"); 

	out.println("--------------");
	Enumeration enu = request.getHeaderNames();
	while (enu.hasMoreElements()) {
		String element = (String) enu.nextElement();	
		out.println(element + " :  "+request.getHeader(element)+"<br>");
	}
	
   
%> 
