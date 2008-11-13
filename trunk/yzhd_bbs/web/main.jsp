<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ntsky.bbs.domain.Forum" %>
<%@ page import="com.ntsky.bbs.domain.Topic" %>
<%@ page import="com.ntsky.bbs.domain.User" %>
<%@ page import="com.ntsky.bbs.domain.UserFace" %>
<%@ page import="com.ntsky.bbs.util.memory.ForumSingleton" %>
<%@ page import="com.ntsky.bbs.util.memory.StarUserSingleton" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>论坛</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<link href="styles/defaulty.css" type="text/css" rel="stylesheet" />
<link href="styles/menu.css" type="text/css" rel="stylesheet" />
<META content=0pQ+dy/Dy5827YQVNUpbjdHOSOZ8W3mhWELbvwp4sAI= name=verify-v1>
<META content="MSHTML 6.00.6000.16705" name=GENERATOR></HEAD>
<BODY>

<DIV><INPUT id=__VIEWSTATE type=hidden 
value=/wEPDwUKMTYzODkyNzA5MGRkC4QPFjibrGVyxelBTfpu8p1qLFQ= name=__VIEWSTATE> 
</DIV>
<DIV id=head>
<jsp:include flush="true" page="includes/head.jsp"></jsp:include>
<!--  <DIV id=nav>
<DIV class=con1>
<DIV class=navlink><A href="#" target=_blank>首页</A> → 
<A href="#" target=_blank>社区</A> → <A id=NavHome 
onmouseover=showMenu(this.id); onmouseout=showMenu(this.id); 
href="#" target=_blank>论坛</A> 
<DIV class=navhomemenu id=NavHome_menu style="DISPLAY: none">
<DIV id=homewindow>
<DD><A href="#" target=_blank>论坛公告</A> 
</DD>
<DD><A href="#" target=_blank>活动中心</A> 
</DD>
<DD><A href="#" target=_blank>意见反馈</A> 
</DD>
<DD><A href="#" target=_blank>招募申请</A> 
</DD>
<DD><A href="#" target=_blank>站务交流</A> 
<DT>主题论坛 </DT>
<DD><A href="#" target=_blank>未准妈妈</A> 
</DD>
<DD><A href="#" target=_blank>孕期滋味</A> 
</DD>
<DD><A href="#" target=_blank>育儿心经</A> 
</DD>
<DD><A href="#" target=_blank>0－3早教</A> 
</DD>
<DD><A href="#" target=_blank>宝宝学艺</A> 
</DD>
<DD><A href="#" target=_blank>小学生活</A> 
</DD>
<DD><A href="#" target=_blank>宝宝贴图</A> 
</DD>
<DD><A href="#" target=_blank>夫妻之间</A> 
</DD>
<DD><A href="#" target=_blank>婆媳关系</A> 
</DD>
<DD><A href="#" target=_blank>单亲家庭</A> 
</DD>
<DD><A href="#" target=_blank>产品评价</A> 
</DD>
<DD><A href="#" target=_blank>漂亮妈妈</A> 
</DD>
<DD><A href="#" target=_blank>时尚家居</A> 
</DD>
<DD><A href="#" target=_blank>美食厨房</A> 
</DD>
<DD><A href="#" target=_blank>随意聊吧</A> 
</DD>
<DD><A href="#" target=_blank>休闲旅游</A> 
</DD>
<DD><A href="#" target=_blank>律师热线</A> 
</DD>
<DD><A href="#" target=_blank>玛丽医生</A> 
</DD>
<DD><A href="#" target=_blank>跳蚤市场</A> 
</DD>
<DD><A href="#" target=_blank>团购天地</A> 
</DD>
<DD><A href="#" target=_blank>打折信息</A> 
</DD>
<DD><A href="#" target=_blank>两性学堂</A> 
<DT>城市论坛 </DT>
<DD><A href="#" target=_blank>北京论坛</A> 
</DD>
<DD><A href="#" target=_blank>沈阳论坛</A> 
</DD>
<DD><A href="#" target=_blank>大连论坛</A> 
</DD>
<DD><A href="#" target=_blank>长春论坛</A> 
</DD>
<DD><A href="#" target=_blank>山东论坛</A> 
</DD>
<DD><A href="#" target=_blank>天津论坛</A> 
</DD>
<DD><A href="#" target=_blank>武汉论坛</A> 
</DD>
<DD><A href="#" target=_blank>上海论坛</A> 
</DD>
<DD><A href="#" target=_blank>南京论坛</A> 
</DD>
<DD><A href="#" target=_blank>无锡论坛</A> 
</DD>
<DD><A href="#" target=_blank>苏州论坛</A> 
</DD>
<DD><A href="#" target=_blank>宁波论坛</A> 
</DD>
<DD><A href="#" target=_blank>杭州论坛</A> 
</DD>
<DD><A href="#" target=_blank>广州论坛</A> 
</DD>
<DD><A href="#" target=_blank>深圳论坛</A> 
</DD>
<DD><A href="#" target=_blank>成都论坛</A> 
</DD>
<DD><A href="#" target=_blank>西安论坛</A> 
</DD>
<DD><A href="#" 
target=_blank>太原论坛</A><SPAN style="COLOR: red">new</SPAN> </DD>
<DD><A href="#" 
target=_blank>石家庄坛</A><SPAN style="COLOR: red">new</SPAN> </DD>
<DD><A href="#" 
target=_blank>郑州论坛</A><SPAN style="COLOR: red">new</SPAN> 
<DT>用户中心 </DT>
<DD class=dd2><A 
href="#">登录</A> </DD>
<DD class=dd2><A 
href="#">注册</A> 
</DD>
<DD class=dd2><A href="#" 
target=_blank>忘记密码</A> </DD></DIV></DIV></DIV>
<div class="mail">
		<a href="#"><img src="images/au2.gif" /></a>&nbsp;&nbsp;<a href="#"><img src="images/au5.gif" /></a>
			
			
		</div></DIV>
<DIV class=con2> -->

<!-- 社区公告 start -->
<!-- <DIV class=bulletin><IMG src="images/icon1.gif" 
align=absMiddle>&nbsp;社区公告：<A href="#" 
target=_blank>说说宝宝最爱的玩具◆参与讨论就有机会上目录！</A></DIV>
<!-- 社区广告 end  -->

<!-- 查找 start --> 
<!-- <DIV class=search><FORM id=search name="BBS_Search" action="" 
method="post"><SELECT id=Topic1_Categorys name=Topic1:Categorys> <OPTION 
  value=0 selected>全部</OPTION> <OPTION value=400>未准妈妈</OPTION> <OPTION 
  value=6>孕期滋味</OPTION> <OPTION value=2>育儿心经</OPTION> <OPTION 
  value=19>0－3早教</OPTION> <OPTION value=401>宝宝学艺</OPTION> <OPTION 
  value=102>小学生活</OPTION> <OPTION value=1>宝宝贴图</OPTION> <OPTION 
  value=101>夫妻之间</OPTION> <OPTION value=15>婆媳关系</OPTION> <OPTION 
  value=402>单亲家庭</OPTION> <OPTION value=5>产品评价</OPTION> <OPTION 
  value=80>漂亮妈妈</OPTION> <OPTION value=215>时尚家居</OPTION> <OPTION 
  value=3>美食厨房</OPTION> <OPTION value=14>随意聊吧</OPTION> <OPTION 
  value=104>休闲旅游</OPTION> <OPTION value=403>律师热线</OPTION> <OPTION 
  value=8>玛丽医生</OPTION> <OPTION value=13>跳蚤市场</OPTION> <OPTION 
  value=131>团购天地</OPTION> <OPTION value=21>打折信息</OPTION> <OPTION 
  value=21>两性学堂</OPTION> <OPTION value=404>论坛公告</OPTION> <OPTION 
  value=405>活动中心</OPTION> <OPTION value=4>意见反馈</OPTION> <OPTION 
  value=406>招募申请</OPTION> <OPTION value=217>站务交流</OPTION> <OPTION 
  value=9>北京论坛</OPTION> <OPTION value=10>沈阳论坛</OPTION> <OPTION 
  value=110>大连论坛</OPTION> <OPTION value=410>长春论坛</OPTION> <OPTION 
  value=50>山东论坛</OPTION> <OPTION value=7>天津论坛</OPTION> <OPTION 
  value=30>武汉论坛</OPTION> <OPTION value=20>上海论坛</OPTION> <OPTION 
  value=40>南京论坛</OPTION> <OPTION value=140>无锡论坛</OPTION> <OPTION 
  value=130>苏州论坛</OPTION> <OPTION value=221>宁波论坛</OPTION> <OPTION 
  value=150>杭州论坛</OPTION> <OPTION value=100>广州论坛</OPTION> <OPTION 
  value=120>深圳论坛</OPTION> <OPTION value=60>成都论坛</OPTION> <OPTION 
  value=222>西安论坛</OPTION> <OPTION value=219>湖南论坛</OPTION> <OPTION 
  value=411>太原论坛</OPTION> <OPTION value=412>石家庄坛</OPTION> <OPTION 
  value=413>郑州论坛</OPTION></SELECT> &nbsp;&nbsp; <SELECT name=Search_Type> 
  <OPTION value=0 selected>主题搜索</OPTION> <OPTION value=2>作 
者</OPTION></SELECT>&nbsp;&nbsp; <INPUT onkeydown=ClickUp(); size=16 
name=SearchKeys><INPUT id=Butt onclick=searchStart1(); type=button value=搜索 name=Butt> 
</FORM></DIV>
</DIV></DIV></DIV>
<!-- 查找 end -->
<jsp:include flush="true" page="front_top.jsp"></jsp:include>
<DIV id=page>
<jsp:include flush="true" page="left.jsp"></jsp:include>

<DIV class=right>

<DIV id=home>
<DIV class=left1>
<DIV class=nr1>
<DIV class=lgg>
<jsp:include flush="true" page="admid.jsp"></jsp:include>
</DIV>

<!-- 论坛之星 start -->
<DIV class=star>
	<DIV class=redborder1>
		<DIV class=hometit1><IMG src="images/icon12.gif"> 论坛之星</DIV>
		<DIV class=homebody>
		<TABLE cellSpacing=4 cellPadding=0 width=358 border=0>
		  <TBODY>
		  
		  <%
		  	List stars=StarUserSingleton.getInstance().getStars();
		  	if(stars!=null){
		  	for(int i=0;i<stars.size();i++){
		  		User star=(User)stars.get(i);
		  		UserFace uf=star.getUserFace();		  		
		  		List indexTopics=star.getIndexTopics();
		  		%>
		  <TR></TR>
		  <TR>
		    <TD width=100>
		      <DIV class=starpic>
		      <%
		      	if(uf==null){
		       %>
		      <IMG height=95 src="images/2007112814202931amypx2mdambwsk2banq5ue55.jpg" width=95>
		      <%
		      }else{
		       %>
		       <IMG height=95 src="<%=uf.getPath() %>" width=95>
		       <%
		       }
		        %>
		      </DIV></TD>
		    <TD class=wz1 vAlign=top width=90>
		      <DIV><A class=red_4 title=月月妈 href="#"><%=star.getUsername() %></A></DIV>
		      <DIV><IMG src="images/icon23.gif"> <A class=orange_2 
		      href="#">我的家</A></DIV>
		      <DIV><IMG src="images/icon22.gif" align=absMiddle> <A class=blue_2 
		      href="#">发送信息</A></DIV></TD>
		    <TD vAlign=top>
		      <DIV class=liststyle1>
		      <UL>
		        <%
		        	for(int j=0;j<indexTopics.size();j++){
		        	Topic topic=(Topic)indexTopics.get(j);
		         %>
		        <LI><A title=月月和他男友的第一次亲密接触！ 
		        href="#"><%=topic.getTitle() %></A></LI>
		        <%} %>
		        </UL></DIV></TD></TR>
		  <TR></TR>
		  <%
		  	}}
		   %>
		      </TBODY></TABLE>
		    </DIV>
		  </DIV>
		</DIV>
</DIV>      
<!-- 论坛之星 end -->

<%


 %>

<DIV class=homegg style="DISPLAY: none"><IMG 
src="images/homegg1.gif"></DIV>


<DIV class=nr1>


	<%
		int sty=2;
		List forums=ForumSingleton.getInstance().getForumsInIndex();
		
		for(int i=0;i<forums.size();i++){
			if(sty==2){
				sty=1;
			}else{
				sty=2;
			}
			
			Forum forum=(Forum)forums.get(i);
			
	%>
	<DIV class=greyborder<%=sty %>>
		<DIV class=hometit2><A class=red_4 href="#"><%=forum.getName() %></A></DIV>
		<DIV>
			<TABLE cellSpacing=5 cellPadding=0 width="100%" border=0>
		  	<TBODY>
			  <TR>
			    <TD class=homepic><A class=red_4 
			      href="#"><IMG height=85 
			      src="<%=forum.getSignImage().trim().equals("")?"skins/default/forum_none.gif":forum.getSignImage() %>" width=100></A></TD>
			    <TD vAlign=top>
			      <DIV class=liststyle2>
			      <UL>
			      <%
			      	List topics=forum.getIndexTopic();
			      	for(int m=0;m<topics.size();m++){
			      		Topic topic=(Topic)topics.get(m);
			      %>
			        <LI>
			        <A title=最好在12周前去健卡哦！我已经完成了，和大家分享！ 
			        href="#" 
			        target=_blank><%=topic.getTitle() %> </A>
			        </LI>
			      <%
			      }
			      %>
			      </UL>
			      </DIV></TD></TR>
		  	</TBODY>
		  	</TABLE>
			<DIV class=bbsbz>
			<IMG src="images/icon24.gif">
			版主：&nbsp;&nbsp;
			<%
			String masters=forum.getMasters();
			if(masters.trim().equals("")){
			
			%>			
			暂无版主
			<% 
			}else{			
			String[] masterArray=masters.split(",");			
			for(int n=0;n<masterArray.length;n++){			
			%>
			<A href="user.action?username=<%=masterArray[n] %>" target=_blank><%=masterArray[n] %></A>&nbsp;
			<%			
			}}
			 %>
			
			
			</DIV>
		</DIV>
	</DIV>
	<%	
		}
	 %>
	


	
<DIV style="MARGIN-TOP: 5px"><!--B4--></DIV><!--B4--></DIV></DIV>
<DIV class=right1>
<DIV class=redborder2>
<DIV class=hometit1><IMG height=14 src="images/icon26.gif" width=14> 
论坛24小时热点排行</DIV>
<DIV class=liststyle3>
<UL>
  <LI><A class=black_1 title=你办了几张信用卡？信用卡带来的烦恼。。。 
  href="#">信用卡到底能为你带来什么？</A> 
  <LI><A class=black_1 title=谈谈开家长会的感受 
  href="#">谈谈开家长会的感受</A> 
  <LI><A class=black_1 title=上海女白领自述：为了还房贷款，我只好业余做“小姐” 
  href="#">为还房贷 只好业余做“小姐”</A> 
  <LI><A class=black_1 title="你们不再使用的银行卡，会去办理注销吗？ " 
  href="#">不再使用的银行卡，你会怎么办</A> 
  <LI><A class=black_1 title=如果离婚，我不要宝宝 
  href="#">离婚了，宝宝怎么办？</A> 
  <LI><A class=black_1 title=生三胎吗 
  href="#">生三胎，你想过吗？</A> 
  <LI><A class=black_1 title=如何相处婆媳关系！ 
  href="#">如何相处婆媳关系！</A> 
  <LI><A class=black_1 title=有孩子的幸福感觉 
  href="#">有孩子的幸福感觉</A> 
  <LI><A class=black_1 title=儿研所气死我了.! 
  href="#">儿研所气死我了.!</A> 
  <LI><A class=black_1 title=大家绝对想不到的郊游工具 
  href="#">大家绝对想不到的郊游工具</A> 
</LI></UL></DIV></DIV>


<!-- 一周热点话题排行 -->
<jsp:include flush="true" page="oneWeekTopics.jsp"></jsp:include>
<!-- 一周热点话题排行 end-->


<DIV><IMG height=10 src="images/spear.gif" width=210></DIV>
<DIV class=redborder2>
<DIV class=hometit1><IMG height=14 src="images/icon28.gif" width=18> 
最新主题</DIV>
<DIV class=liststyle4>
<UL>
  <LI><A class=black_1 title=红孩子的小车不错~ 
  href="#">红孩子的小车不错~</A> 
  <LI><A class=black_1 title=家庭破裂,孩子无罪! 
  href="#">家庭破裂,孩子无罪!</A> 
  <LI><A class=black_1 title=提醒见面交易的亲。。。。。。。。。。。。。。。。。。 
  href="#">提醒见面交易的朋友们</A> 
  <LI><A class=black_1 title=要孩子前你们去医院查了吗？ 
  href="#">要孩子前你们去医院查了吗？</A> 
  <LI><A class=black_1 title=娃娃幼儿园汇报 
  href="#">娃娃上了幼儿园汇报总结</A> 
  <LI><A class=black_1 title=美旺的衣服千万别买了 
  href="#">美旺的衣服千万别买了</A> 
  <LI><A class=black_1 title=老公不让我自己出门 
  href="#">老公不让我自己出门</A> 
  <LI><A class=black_1 title=职场人生：三十岁女人绕过年龄修成精 
  href="#">职场人生：三十岁女人绕过年</A> 
  <LI><A class=black_1 title=奥运会的瞬间 
  href="#">奥运会的瞬间</A> 
  <LI><A class=black_1 title=巧点点评还得分吗 
  href="#">巧点点评还得分吗</A> 
</LI></UL></DIV></DIV>
<DIV style="MARGIN-TOP: 5px"><!--B5-->
<jsp:include flush="true" page="adright.jsp"></jsp:include>
</DIV><!--B5--></DIV></DIV></DIV></DIV><INPUT id=SaveId 
type=hidden name=SaveId>

 <!--底部开始-->
<DIV id=bottom>
<DIV class=about><A href="#"></A><A 
class=red_2 href="#">客服中心</A> | <A 
class=red_2 href="#">配送说明</A> | <A 
class=red_2 href="#">加盟合作</A> | <A 
class=red_2 href="#">公司介绍</A> | <A 
class=red_2 href="#">诚聘英才</A> | <A 
class=red_2 href="#">与红孩子合作</A>| <A 
class=red_2 href="#">联系我们</A> | <A 
class=red_2 href="#">友情链接</A> | <A 
class=red_2 href="#">广告服务</A> | <A 
class=red_2 href="#"><B>意见反馈</B></A></DIV>
<DIV class=copyright>版权所有 广州博商软件技术有限公司 <A class=red_1 
href="#">京ICP证060411号</A> Copyright &copy; 2008</DIV>
</DIV><!--底部结束--></BODY></HTML>

