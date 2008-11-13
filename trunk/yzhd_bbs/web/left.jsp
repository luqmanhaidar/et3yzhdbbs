<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ntsky.bbs.domain.Forum"%>
<%@ page import="com.ntsky.bbs.domain.Topic"%>
<%@ page import="com.ntsky.bbs.util.memory.ForumSingleton"%>
<DIV class=left>
	<DIV class=hhz1>
		<IMG alt="" src="images/hhz1.gif">
	</DIV>
	<DIV class=menu>
		<DIV class=function>
			<UL class=menutop>
				<LI>
					<A href="#" target=_blank>最新回复</A>
				</LI>
				<LI>
					<A href="#" target=_blank>最新主题</A>
				</LI>
				<LI>
					<A href="help.jsp" target=_blank>新手帮助</A>
				</LI>
				<LI>
					<A href="login.action">登录</A>
				</LI>
				<LI>
					<A href="signup-page.action">注册</A>
				</LI>
				<LI>
					<A href="recoverPassword-page.action" target=_blank>忘记密码</A>
				</LI>
			</UL>
		</DIV>


		<%
			List forums1 = ForumSingleton.getInstance().getForums();
			for (int i = 0; i < forums1.size(); i++) {
				Forum forum1 = (Forum) forums1.get(i);
				if (forum1.getDepth() == 1) {
		%>
		<DIV class=zt style="CURSOR: pointer; LINE-HEIGHT: 27px"
			onclick="openDiv('202')">
			<%=forum1.getName()%>
		</DIV>
		<UL class=sort id=Child_Board202>
			<%
						List forums2 = ForumSingleton.getInstance().getChildForums(
						forum1.getId().intValue());
						for (int j = 0; j < forums2.size(); j++) {
					Forum forum2 = (Forum) forums2.get(j);
			%>
			<LI>
				<A class=red_1 href="forum.action?forumId=<%=forum2.getId()%>"><%=forum2.getName()%>
				</A>
			</LI>
			<%
			}
			%>
		</UL>
		<%
			}

			}
		%>



		<DIV>
			&nbsp;
		</DIV>
	</DIV>
</DIV>
<!--left end-->
