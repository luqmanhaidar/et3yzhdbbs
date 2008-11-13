<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ntsky.bbs.domain.Link"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory" %>
<%@ page import="com.ntsky.bbs.service.LinkService" %>

	<%	 
		BeanFactory bf=BeanFactory.getInstance(request.getSession().getServletContext());
		LinkService ls=(LinkService)bf.getBean("linkService");
		Link ad=ls.findRandomLinkByType(4);
		if(ad==null){
		%>
			<A href="#" target=_blank><IMG alt="" src="images/adview(1).jpg" border=0></A>
		<%
		}else{
		 %>
		 	<A href="<%=ad.getUrl() %>" target=_blank><IMG width="210" height="90"  src="<%=ad.getLogo() %>" border=0></A>
		 <%
		}
	 %>	

