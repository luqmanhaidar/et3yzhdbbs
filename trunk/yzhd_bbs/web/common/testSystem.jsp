<%@ page import="java.util.*"%>
<%@ page import="com.ntsky.bbs.*"%>
<%@ page import="com.ntsky.bbs.domain.*"%>
<%@ page import="com.ntsky.framework.upload.*"%>
<%@ page import="com.ntsky.framework.util.*"%>

<%
	User user = (User)HttpUtil.getAttribute(request.getSession(),Symbols.SESSION_USER);
	out.print(user!=null);
%>