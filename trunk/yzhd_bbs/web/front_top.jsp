<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ntsky.bbs.domain.*"%>
<%@ page import="com.ntsky.bbs.util.BeanFactory"%>
<%@ page import="com.ntsky.bbs.service.LinkService"%>
<%@ page import="com.ntsky.bbs.util.memory.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ntsky.bbs.domain.User"%>
<%@ page import="com.ntsky.bbs.service.*"%>
<div id="nav">
	<div class="con1">

		<div class="navlink">



			<a href="#">首页</a> →
			<a href="#">社区</a> →
			<a href="#">论坛</a> →
			<a href="#">powerhawk</a>
		</div>
		<div class="mail">
			<a href="#"><img src="images/au2.gif" />
			</a>&nbsp;&nbsp;
			<a href="#"><img src="images/au5.gif" />
			</a>


		</div>
	</div>

	<div class="con2">
		<div class="bulletin">
			<img src="images/icon1.gif" align="absmiddle">
			&nbsp;社区公告：<marquee scrollamount='1' scrolldelay='30' direction="left"
				onmouseover="this.stop()" onmouseout="this.start()" >
				<%
					HashMap logo=(HashMap)application.getAttribute("basic");
							BeanFactory bf=BeanFactory.getInstance(request.getSession().getServletContext());
							AnnouncementService ls=(AnnouncementService)bf.getBean("announcementService");
							List anns =ls.getAnnouncements(-1,0);

				if(anns!=null){
		
				for (int i = 0; i < anns.size(); i++) {
				Announcement ann = (Announcement) anns.get(i);
					//while(rs.next()){
				%>

				<a href="#"><%=ann.getTitle()%></a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<%
					}
				}
				%>
			</marquee>
		</div>
		<div class="search">
			<form method="post" id="search" action="" name="BBS_Search">
				<select name="Topic1:Categorys" id="Topic1_Categorys">
					<option value="0">
						全部
					</option>
					<%
						List forums = ForumSingleton.getInstance().getForums();

						if (forums != null) {
							for (int i = 0; i < forums.size(); i++) {
								Forum forum = (Forum) forums.get(i);
					%>
					<option value=<%=forum.getId()%>>
						<%=forum.getName()%>
					</option>
					<%
						}
						}
					%>

				</select>
				&nbsp;&nbsp;
				<select name="Search_Type">
					<option value="0">
						主题搜索
					</option>
					<option value="2">
						作 者
					</option>
				</select>
				&nbsp;&nbsp;
				<input name="SearchKeys" value="" size="16" onkeydown="ClickUp();"
					type="text">
				<input id="Butt" name="Butt" value="搜索" onclick="searchStart1();"
					type="button">
			</form>
		</div>
	</div>
</div>
