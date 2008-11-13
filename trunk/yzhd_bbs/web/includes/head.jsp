<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ntsky.bbs.domain.Link"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory" %>
<%@ page import="com.ntsky.bbs.service.LinkService" %>

<%@ page import="java.util.List"%>
<%@ page import="com.ntsky.bbs.domain.User"%>
<%@ page import="com.ntsky.bbs.service.UserService" %>

<DIV id=top>
	<%
		HashMap logo=(HashMap)application.getAttribute("basic");
	 %>
	<DIV class=logo><A href="index.jsp"><img src="<%=logo.get("logo") %>"></A></DIV>
	<DIV class=banner>
	<%	 
		BeanFactory bf=BeanFactory.getInstance(request.getSession().getServletContext());
		LinkService ls=(LinkService)bf.getBean("linkService");
		Link ad=ls.findRandomLinkByType(1);
		if(ad==null){
		%>
			<A href="#" target=_blank><IMG alt="" src="images/adview.gif" border=0></A>
		<%
		}else{
		 %>
		 	<A href="<%=ad.getUrl() %>" target=_blank><IMG width="750" height="100"  src="<%=ad.getLogo() %>" border=0></A>
		 <%
		}
		//System.out.println("=============");
		//UserService userService=(UserService)bf.getBean("userService");
		//List stars=userService.findStarUser(2);
		//for(int i=0;i<stars.size();i++){
		//	User user=(User)stars.get(i);
		//	System.out.println(user.getUsername());
		//	System.out.println(user.getIndexTopics().size());
		//}
		
		
	 %>	
	</DIV>	
</DIV>

