<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ntsky.bbs.domain.*"%>
<%@ page import="com.ntsky.bbs.service.*"%>
<%@ page import="com.ntsky.bbs.service.impl.*"%>
<%@ page import="com.ntsky.bbs.util.memory.ForumSingleton"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
		<TITLE>论坛</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
		<link href="styles/menu.css" type="text/css" rel="stylesheet" />
		<META content=0pQ+dy/Dy5827YQVNUpbjdHOSOZ8W3mhWELbvwp4sAI=
			name=verify-v1>
		<META content="MSHTML 6.00.6000.16705" name=GENERATOR>
	</HEAD>

	<body>
		<%
	int forumId=5;

	Integer count=1;
	Topic topic = new Topic();
	TopicService topicService = new TopicServiceImpl();
	List list1 = topicService.getDayTopics(forumId,1);//TODO
	ListIterator iterator = list1.listIterator();
	 while(iterator.hasNext())
	 {
		 Object rows = (Object)iterator.next();
		 //user = (User)rows[0];
		 topic = (Topic)rows;
		 count++;
	System.out.println(topic.getTitle());
	}
	 %>

	</body>
</html>
