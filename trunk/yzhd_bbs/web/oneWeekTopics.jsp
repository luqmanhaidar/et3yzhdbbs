<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory" %>

<%@ page import="java.util.List"%>
<%@ page import="com.ntsky.bbs.domain.Topic"%>
<%@ page import="com.ntsky.bbs.service.TopicService" %>
<DIV><IMG height=10 src="images/spear.gif" width=210></DIV>
<DIV class=redborder2>
<DIV class=hometit1><IMG height=16 src="images/icon27.gif" width=14> 
一周热点话题排行</DIV>
<DIV class=liststyle3>
<UL>
	<%
		//BeanFactory bf=BeanFactory.getInstance(request.getSession().getServletContext());
		//TopicService topicService=(TopicService)bf.getBean("topicService");
		//List weekTopics=topicService.getWeekTopics(0,10);
		//for(int i=0;i<weekTopics.size();i++){
		//Topic topic=(Topic)weekTopics.get(i);
	 %>
  	<LI><A class=black_1 href="#"></A></LI>
	<%
	//} %>
</UL></DIV></DIV>

